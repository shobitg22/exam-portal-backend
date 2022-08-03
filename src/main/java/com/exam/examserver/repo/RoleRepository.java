package com.exam.examserver.repo;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRoleName(String userName);
}
