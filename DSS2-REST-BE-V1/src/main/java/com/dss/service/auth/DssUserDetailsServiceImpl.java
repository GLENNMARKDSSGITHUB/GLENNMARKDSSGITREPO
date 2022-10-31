package com.dss.service.auth;

import com.dss.entity.user.Users;
import com.dss.repository.user.UsersRepository;
import com.dss.util.utils.CommonStringUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DssUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findUserByEmailAddress(username);
        if(user == null){
            throw new UsernameNotFoundException(CommonStringUtility.ERR_CODE_004_ACCT_NOT_EXISTING);
        }
        return new DssUserDetailsImpl(user);
    }
}
