package com.exam.examserver.service.impl;

import com.exam.examserver.entity.Role;
import com.exam.examserver.entity.User;

import com.exam.examserver.entity.UserRole;
import com.exam.examserver.repo.RoleRepository;
import com.exam.examserver.repo.UserRepository;
import com.exam.examserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        User existingUser =null;
        existingUser=userRepository.findByUserName(user.getUserName());
        if(existingUser!=null){
            log.error("User already exist !");
            try {
                throw new Exception("User already exist !!");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        else {
            List<UserRole> userRoleSet = new ArrayList<>();

            UserRole userRole = new UserRole();
            userRole.setUser(user);
            Role role=validateRoleName("NORMAL");
            if(role==null)
            {
             Role newRole = new Role();
             newRole.setRoleName("NORMAL");
             userRole.setRole(newRole);
             newRole.setRoles(userRoleSet);
            }
            else {
                userRole.setRole(role);
                role.setRoles(userRoleSet);
            }
            userRoleSet.add(userRole);
            user.setRoles(userRoleSet);
            existingUser = userRepository.save(user);

            return existingUser;
        }
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(User updatedUser,Long userId) {
         Optional<User> optionalUser = userRepository.findById(userId);
         if(optionalUser.isEmpty()){
             try {
                 throw new Exception("No User found with the given id");
             } catch (Exception e) {
                 throw new RuntimeException(e);
             }
         }
        updatedUser.setId(optionalUser.get().getId());
        return userRepository.save(updatedUser);
    }

    @Override
    public Role validateRoleName(String roleName) {

        Role role=null;
        role=roleRepository.findByRoleName(roleName);
        return role;
    }

//    @Override
//    public User createUsers(User user) {
//        User existingUser = null;
//        existingUser= userRepository.findByUserName(user.getUserName());
//                if(existingUser!=null){
//            log.error("User already exist !");
//            try {
//                throw new Exception("User already exist !!");
//            } catch (Exception e) {
//                throw new RuntimeException(e.getMessage());
//            }
//        }
//        user.setRoles(List.of(new Role("ADMIN")));
//        return userRepository.save(user);
//    }
}
