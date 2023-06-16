package com.company.WeGoDent.repositories;

import com.company.WeGoDent.entity.GroupRole;
import com.company.WeGoDent.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRoleRepository extends JpaRepository<GroupRole,Long> {

    GroupRole findByCode(UserType code);

}
