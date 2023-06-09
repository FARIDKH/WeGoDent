package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import com.company.WeGoDent.mapper.helper.EntityMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {GroupRoleMapper.class})
public interface UserMapper extends EntityMapper<UserDTO, User> {

    @Override
    @Mapping(source = "roles", target = "roleIds")
    UserDTO toDto(User entity);



    @Override
    @Mapping(source = "roleIds", target = "roles")
    User toEntity(UserDTO dto);



}