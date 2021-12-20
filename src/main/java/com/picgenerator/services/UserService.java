package com.picgenerator.services;

import com.picgenerator.entities.User;
import com.picgenerator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(Integer id) {
        userRepository.delete(getById(id));
    }

}
