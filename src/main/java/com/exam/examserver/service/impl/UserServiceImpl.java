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
    public User createUser(User user, List<UserRole> userRoleSet) {
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
            for(UserRole userRole:userRoleSet)
            {
//               Role role = roleRepository.findByRoleName(userRole.getRole().getRoleName());
//               if(role!=null && role.getRoleName()!=userRole.getRole().getRoleName()) {
                   roleRepository.save(userRole.getRole());
//               }
            }
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
