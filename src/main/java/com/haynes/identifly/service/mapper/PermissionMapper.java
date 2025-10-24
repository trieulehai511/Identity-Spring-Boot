package com.haynes.identifly.service.mapper;

import com.haynes.identifly.service.dto.request.PermissionRequest;
import com.haynes.identifly.service.dto.response.PermissionResponse;
import com.haynes.identifly.service.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionRequest request);
    PermissionResponse toPermissionResponse(Permission permission);
}
