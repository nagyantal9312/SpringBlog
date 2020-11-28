package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private BloggerRepository bloggerRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private LoginAttemptService loginAttemptService;

    @Autowired
    private HttpServletRequest request;


    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Blogger blogger = bloggerRepository.findByUsername(username);
        if(blogger == null) {
            throw new UsernameNotFoundException("Not able to find the user named: " + username);
        }
        return blogger;

    }*/

    @Override
    public UserDetails loadUserByUsername(String username) throws RuntimeException  {

        Blogger blogger = bloggerRepository.findByUsername(username);
        if(blogger == null) {
            throw new UsernameNotFoundException("Not able to find the user named: " + username);
        }

        String ip = getClientIP();
        //System.out.println("IPCIM: " + ip);

        if(loginAttemptService.isBlocked(ip)){

            System.out.println("BLOKKOLVA VAB");
            throw new RuntimeException("blocked");

        }



        return blogger;

    }





    private String getClientIP(){
        String xfHeader = request.getHeader("X-Forwarded-For");
        if(xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }


}
