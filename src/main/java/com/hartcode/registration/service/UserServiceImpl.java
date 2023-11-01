package com.hartcode.registration.service;

import com.hartcode.registration.dao.RoleDao;
import com.hartcode.registration.dao.UserDao;
import com.hartcode.registration.entity.Role;
import com.hartcode.registration.entity.User;
import com.hartcode.registration.user.WebUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

// This is the implementation of the UserService interface and UserDetailsService from Spring Security.
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final RoleDao roleDao;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, RoleDao roleDao, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }

    @Override
    public User findByUserName(String userName) {
        // Check the database if the user already exists and retrieve user information by username.
        return userDao.findByUserName(userName);
    }

    @Override
    public void save(WebUser webUser) {

        User user = new User();

        user.setUserName(webUser.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(webUser.getPassword()));
        user.setFirstName(webUser.getFirstName());
        user.setLastname(webUser.getLastName());
        user.setEmail(webUser.getEmail());

        user.setRoles(Collections.singletonList(roleDao.findRoleByName("ROLE_ADMIN")));

        userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Load user details for authentication and authorization based on the provided username.
        User user = userDao.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        // Create a UserDetails object with the user's information and authorities.
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        // Map user roles to Spring Security authorities.
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
