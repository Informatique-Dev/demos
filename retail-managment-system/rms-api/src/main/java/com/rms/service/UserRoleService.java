package com.rms.service;

import com.rms.domain.security.Role;
import com.rms.domain.security.UserRole;
import com.rms.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleService {

    private UserRoleRepository userRoleRepository ;


    public Optional<UserRole> getById(Integer id)
    {
        return userRoleRepository.findById(id);
    }
    public UserRole save(UserRole userRole)
    {
        return userRoleRepository.save(userRole);
    }

    public void delete(UserRole userRole)
    {
        userRoleRepository.delete(userRole);
    }

    public Optional<Integer> getRoleIdByUserIdAndRoleId(Integer userId,Integer roleId){
        return userRoleRepository.findRoleIdByUserIdAndRoleId(userId,roleId);
    }

    public Page<UserRole> getByUserId(Integer userId ,Integer page , Integer  size){
        return userRoleRepository.findUserRoleByUserId(userId , PageRequest.of(page,size));
    }
    public List<Role> getRoleByUsername(String username){
        return userRoleRepository.findByUserName(username);
    }

}
