package com.saji.dashboard_backend.shared.controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.dtos.ListResponse;
import com.saji.dashboard_backend.shared.dtos.PaginationFilter;
import com.saji.dashboard_backend.shared.dtos.ValueFilter;
import com.saji.dashboard_backend.shared.entites.BaseEntity;
import com.saji.dashboard_backend.shared.services.BaseService;
import com.saji.dashboard_backend.shared.utils.FieldFilterExtractor;
import com.saji.dashboard_backend.shared.utils.PaginationFilterExtractor;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BaseController<T extends BaseEntity> {
    private final BaseService<T> service;

    public <X extends BaseResponse> ResponseEntity<X> create(BaseRequest request) {
        return ResponseEntity.ok().body(service.create(request));
    }

    public <X extends BaseResponse> ResponseEntity<X> update(Long id, BaseRequest request) {
        return ResponseEntity.ok().body(service.update(id, request));
    }

    public <X extends BaseResponse> ResponseEntity<X> getById(Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    public <X extends BaseResponse> ResponseEntity<ListResponse<X>> getList(@RequestParam Map<String, Object> params) {
        PaginationFilterExtractor paginationFilterExtractor = new PaginationFilterExtractor();
        PaginationFilter paginationFilter = paginationFilterExtractor.getFilters(params);
        if (paginationFilter.getPage() == null) {
            throw new IllegalArgumentException("page is required.");
        } else if (paginationFilter.getPage() < 1) {
            throw new IllegalArgumentException("page should not be less than 1.");
        }

        if (paginationFilter.getSize() == null) {
            throw new IllegalArgumentException("size is required.");
        } else if (paginationFilter.getSize() < 1) {
            throw new IllegalArgumentException("size should not be less than 1.");
        }

        FieldFilterExtractor fieldFilterExtractor = new FieldFilterExtractor();
        Collection<ValueFilter> valueFilters = fieldFilterExtractor.getFilters(params);
        ListResponse<X> response = service.getList(paginationFilter, valueFilters);

        // headers.set("Access-Control-Expose-Headers", "X-Total-Count");
        // headers.set("x-total-count", "" + response.getTotal());

        return ResponseEntity.ok().body(response);
    }

    public ResponseEntity<Void> deleteById(Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}