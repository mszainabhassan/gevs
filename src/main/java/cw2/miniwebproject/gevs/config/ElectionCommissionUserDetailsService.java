package cw2.miniwebproject.gevs.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ElectionCommissionUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("election@shangrila.gov.sr".equals(username)) {
            return new ElectionCommissionUserDetails();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}

