package com.abc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: bmakarand
 * Date: 8/3/12
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
@Component
 public class StupidAuthenticationProvider  extends AbstractUserDetailsAuthenticationProvider {

    private UmsUserDetailsService umsUserDetailsService = new UmsUserDetailsService();

    @Autowired
    public void setUmsUserDetailsService(UmsUserDetailsService umsUserDetailsService1){
        this.umsUserDetailsService = umsUserDetailsService1;
    }
    public UmsUserDetailsService getUmsUserDetailsService(){
        return umsUserDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        return;
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        UserDetails result = umsUserDetailsService.loadUserByUsername(username);
        if (result == null) {
            throw new UsernameNotFoundException("user not found");
        }
        if(authentication.getCredentials().toString().equals(result.getPassword()))
        {
            return result;
        }
        else
             throw new BadCredentialsException("Bad credentials");

    }


}

