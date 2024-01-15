package cw2.miniwebproject.gevs.config;

import cw2.miniwebproject.gevs.model.Voter;
import cw2.miniwebproject.gevs.repository.VoterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private VoterRepository voterRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Voter voter = voterRepository.findByEmail(email);

        if (voter != null) {
            return new CustomVoterDetails(voter);
        }

        throw new UsernameNotFoundException("user not available");
    }

}