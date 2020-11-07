package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ContactUserDetailsService implements UserDetailsService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    public ContactUserDetailsService(BloggerRepository bloggerRepository) {
        this.bloggerRepository = bloggerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Blogger blogger = bloggerRepository.findByUsername(username);
        if(blogger == null) {
            throw new UsernameNotFoundException("Not able to find the user named: " + username);
        }
        return blogger;

    }
}
