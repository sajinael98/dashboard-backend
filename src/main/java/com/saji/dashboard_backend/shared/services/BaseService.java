package com.saji.dashboard_backend.shared.services;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.dtos.ListResponse;
import com.saji.dashboard_backend.shared.dtos.PaginationFilter;
import com.saji.dashboard_backend.shared.dtos.ValueFilter;
import com.saji.dashboard_backend.shared.entites.BaseEntity;
import com.saji.dashboard_backend.shared.mappers.BaseMapper;
import com.saji.dashboard_backend.shared.repositories.BaseRepository;
import com.saji.dashboard_backend.shared.specifications.EntitySpecification;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class BaseService<T extends BaseEntity> {
    private final BaseRepository<T, Long> baseRepository;
    private final BaseMapper<T> baseMapper;

    @Transactional
    public BaseResponse create(BaseRequest request) {
        T object = baseMapper.convertRequestToEntity(request);
        validate(object);
        object = baseRepository.save(object);
        T savedObject = baseRepository.findById(object.getId()).get();
        return baseMapper.convertEntityToResponse(savedObject);
    }

    @Transactional
    public BaseResponse update(Long id, BaseRequest request) {
        if (!baseRepository.existsById(id)) {

        }
        T object = baseMapper.convertRequestToEntity(request);
        validate(object);
        object = baseRepository.save(object);
        return baseMapper.convertEntityToResponse(object);
    }

    public ListResponse<? extends BaseResponse> getList(PaginationFilter paginationFilter,
            Collection<ValueFilter> valueFilters) {
        Pageable pageable = PageRequest.of(paginationFilter.getPage() - 1,
                paginationFilter.getSize());
        Page<T> entities = baseRepository.findAll(EntitySpecification.findList(valueFilters), pageable);
        List<BaseResponse> list = entities.stream().map(entity -> baseMapper.convertEntityToResponse(entity))
                .toList();
        ListResponse<BaseResponse> response = new ListResponse<>();
        response.setData(list);
        response.setTotal(entities.getTotalElements());
        return response;
    }

    public BaseResponse getById(Long id) {
        var entity = baseRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(""));
        BaseResponse response = baseMapper.convertEntityToResponse(entity);
        return response;
    }

    public void deleteById(Long id) {
        if (!baseRepository.existsById(id)) {

        }
        baseRepository.deleteById(id);
    }

    public void validate(T object) {

    }
}
