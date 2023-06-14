package com.company.WeGoDent.repositories;

import com.company.WeGoDent.models.GroupRole;
import com.company.WeGoDent.models.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRoleRepository extends JpaRepository<GroupRole,Long> {

    GroupRole findByCode(UserType code);

}
