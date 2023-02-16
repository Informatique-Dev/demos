package com.rms.service;

import com.rms.domain.security.User;
import com.rms.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository ;

    public Page<User> getAll(Integer page , Integer size)
    {
        return userRepository.findAll(PageRequest.of(page,size));
    }

    public Optional<User> getById(int id)
    {
        return userRepository.findById(id);
    }

    public User save (User user)
    {
        return userRepository.save(user);
    }

    public Optional<User> findUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public void delete(User user)
    {
        userRepository.delete(user);
    }
}
