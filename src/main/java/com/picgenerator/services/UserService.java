package com.picgenerator.services;

import com.picgenerator.entities.User;
import com.picgenerator.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User getById(String username) {
        return userRepository.findById(username).orElse(null);
    }

    public User createOrUpdate(User user) {
        if (StringUtils.isNotEmpty(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public void deleteById(String username) {
        userRepository.delete(getById(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.getById(username);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + username + "' not found or inactive");
    }

    public void setPassword(String username, String oldPassword, String newPassword) throws IllegalAccessException {
        User user = this.getById(username);
        if (user != null) {
            String encodedOldPassword = passwordEncoder.encode(oldPassword);
            String encodedNewPassword = passwordEncoder.encode(newPassword);
            if (StringUtils.isEmpty(user.getPassword()) || StringUtils.equals(user.getPassword(), encodedOldPassword)) {
                user.setPassword(encodedNewPassword);
                userRepository.save(user);
            } else {
                throw new IllegalAccessException("Invalid old password");
            }
        }
    }
}
