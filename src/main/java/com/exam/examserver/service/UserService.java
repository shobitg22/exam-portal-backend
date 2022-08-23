package com.exam.examserver.service;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;
import com.exam.examserver.entity.UserRole;


import java.util.List;
import java.util.Set;

public interface UserService {

    public User createUser(User user) ;

    public User getUser(String userName);

    public void deleteUser(Long userId);

    public User updateUser(User updatedUser,Long userId);

    Role validateRoleName(String normal);

//    User createUsers(User user);
}
