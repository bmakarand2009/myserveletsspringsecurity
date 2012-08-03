package com.abc;

/**
 * Created by IntelliJ IDEA.
 * User: bmakarand
 * Date: 8/3/12
 * Time: 1:52 PM
 * To change this template use File | Settings | File Templates.
 */

import java.util.*;


import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UmsUserDetailsService implements UserDetailsService {

    public static final String ROLE_USER = "ROLE_USER";

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {

        List grantedAuthorities = new ArrayList<GrantedAuthority>();
        grantedAuthorities.add(new GrantedAuthorityImpl(ROLE_USER));
        if (username != null)
        {
            Vector<GrantedAuthority> userAuthorities = new Vector<GrantedAuthority>();
            User user = new User(username, "123456", true, true,
                    true, true, grantedAuthorities);
            return user;
        }

        throw new UsernameNotFoundException("Username " + username + " not found!");
    }
}
