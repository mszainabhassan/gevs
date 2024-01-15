package cw2.miniwebproject.gevs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

public class ElectionCommissionUserDetails extends User {
    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    public ElectionCommissionUserDetails() {
        // Provide the Election Commission user details
        super("election@shangrila.gov.sr","$2a$10$o2oFJq7wPMh7LPfD/1mGFefNgjvCJdnSuYJcOiFZDPZV51sJ6GA7i", AuthorityUtils.createAuthorityList("ROLE_ELECTION_COMMISSION_OFFICER"));
    }
}

