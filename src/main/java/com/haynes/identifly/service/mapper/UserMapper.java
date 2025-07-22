package com.haynes.identifly.service.mapper;

import com.haynes.identifly.service.dto.request.UserCreateRequest;
import com.haynes.identifly.service.dto.request.UserUpdateRequest;
import com.haynes.identifly.service.dto.response.UserResponse;
import com.haynes.identifly.service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreateRequest request);
    @Mapping(source = "dcb", target = "dcb")
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
