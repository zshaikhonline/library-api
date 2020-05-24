package com.transistorwebservices.libraryapi.security;

import com.transistorwebservices.libraryapi.exception.LibraryResourceNotFoundException;
import com.transistorwebservices.libraryapi.user.User;
import com.transistorwebservices.libraryapi.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author: coffee@2am
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;

        try {
            user = userService.getUserByUserName(username);
        } catch (LibraryResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
        return user;
    }
}
