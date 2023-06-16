package com.company.WeGoDent.mapper;

import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDTO copyUserEntityToDto(User userEntity) {
        var userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        userDTO.setRoles(userEntity.getRoles().stream().map(this::copyGroupRoleToGroupRoleDTO).collect(Collectors.toList()));
        return userDTO;
    }

    public User copyUserDtoToEntity(UserDTO dto) {
        var user = new User();
        BeanUtils.copyProperties(dto, user);
        user.setRoles(dto.getRoles().stream().map(this::copyGroupRoleDTOToGroupRole).collect(Collectors.toList()));
        BeanUtils.copyProperties(dto.getRoles(), user.getRoles());
        return user;
    }
    public GroupRole copyGroupRoleDTOToGroupRole(GroupRoleDTO dto) {
        var groupRole = new GroupRole();
        BeanUtils.copyProperties(dto, groupRole);
        return groupRole;
    }
    public GroupRoleDTO copyGroupRoleToGroupRoleDTO(GroupRole entity) {
        var groupRoleDTO = new GroupRoleDTO();
        BeanUtils.copyProperties(entity, groupRoleDTO);
        return groupRoleDTO;
    }
}