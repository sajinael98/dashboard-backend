package com.saji.dashboard_backend.shared.mappers;

import com.saji.dashboard_backend.shared.dtos.BaseRequest;
import com.saji.dashboard_backend.shared.dtos.BaseResponse;
import com.saji.dashboard_backend.shared.entites.BaseEntity;

public interface BaseMapper<T extends BaseEntity> {
    default <T extends BaseEntity> T convertRequestToEntity(BaseRequest req) {
        return null;
    }

    default BaseResponse convertEntityToResponse(T entity) {
        return null;
    }
}
