package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Role;
import hu.suaf.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BloggerService {

    private BloggerRepository bloggerRepository;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;


    @Autowired
    public BloggerService(BloggerRepository bloggerRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.bloggerRepository = bloggerRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    public void saveBlogger(Blogger blogger) {

        blogger.setEnabled(true);
        Role role = roleService.findByName("USER");
        blogger.setRoles(Arrays.asList(role));
        blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
        System.out.println(blogger.toString());
        bloggerRepository.save(blogger);
    }


    public Blogger findByUsername(String username) {
        return bloggerRepository.findByUsername(username);
    }


}
