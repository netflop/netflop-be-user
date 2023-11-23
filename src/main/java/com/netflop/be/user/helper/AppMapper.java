package com.netflop.be.user.helper;

import com.netflop.be.user.model.User;
import com.netflop.be.user.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppMapper
{
    @Mapping(source = "id", target = "id")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "firstName", target = "first_name")
    @Mapping(source = "lastName", target = "last_name")
    @Mapping(source = "phoneNumber", target = "phone_number")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "type", target = "type")
    @Mapping(source = "createdBy", target = "created_by")
    @Mapping(source = "createdAt", target = "created_at")
    @Mapping(source = "updatedBy", target = "updated_by")
    @Mapping(source = "updatedAt", target = "updated_at")
    @Mapping(source = "isDeleted", target = "is_deleted")
    UserResponse ConvertToUserResponse(User user);
}