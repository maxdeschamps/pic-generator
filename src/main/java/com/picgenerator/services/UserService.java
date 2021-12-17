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

    public User getById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    private User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
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

    public void deleteById(Integer id) {
        userRepository.delete(getById(id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.getByEmail(email);
        if (user != null) {
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
        throw new UsernameNotFoundException("User '" + email + "' not found or inactive");
    }

    public void setPassword(Integer id, String oldPassword, String newPassword) throws IllegalAccessException {
        User user = this.getById(id);
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
