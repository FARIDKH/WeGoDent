package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface UserMapper {

        UserDTO copyUserEntityToDto(User userEntity);
        User copyUserDtoToEntity(UserDTO dto);


}