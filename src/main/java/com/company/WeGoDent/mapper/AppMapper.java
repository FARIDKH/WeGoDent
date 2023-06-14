package com.company.WeGoDent.mapper;


import com.company.WeGoDent.dto.GroupRoleDTO;
import com.company.WeGoDent.dto.UserDTO;
import com.company.WeGoDent.models.GroupRole;
import com.company.WeGoDent.models.User;
import lombok.experimental.UtilityClass;
import org.springframework.beans.BeanUtils;

import java.util.stream.Collectors;

@UtilityClass
public class AppMapper {




    public static GroupRole copyGroupRoleDTOToGroupRole(GroupRoleDTO dto) {
        GroupRole groupRole = new GroupRole();
        BeanUtils.copyProperties(dto, groupRole);
        return groupRole;
    }

    public static GroupRoleDTO copyGroupRoleToGroupRoleDTO(GroupRole entity) {
        GroupRoleDTO groupRoleDTO = new GroupRoleDTO();
        BeanUtils.copyProperties(entity, groupRoleDTO);
        return groupRoleDTO;
    }

    public  static UserDTO copyUserEntityToDTO(User userEntity){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity,userDTO);
        userDTO.setRoles(userEntity.getRoles().stream().map(AppMapper::copyGroupRoleToGroupRoleDTO).collect(Collectors.toList()));
        return userDTO;
    }

    public  static User copyUserDTOtoEntity(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setRoles(userDTO.getRoles().stream().map(AppMapper::copyGroupRoleDTOToGroupRole).collect(Collectors.toList()));
        return user;
    }

}
