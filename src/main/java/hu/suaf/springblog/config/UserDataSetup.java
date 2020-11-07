package hu.suaf.springblog.config;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Role;
import hu.suaf.springblog.repository.BloggerRepository;
import hu.suaf.springblog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.util.Arrays;

public class UserDataSetup implements ApplicationListener<ContextRefreshedEvent> {


    private BloggerRepository bloggerRepository;
    private RoleRepository roleRepository;
    boolean alreadySetup = false;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserDataSetup(BloggerRepository bloggerRepository, RoleRepository roleRepository) {
        this.bloggerRepository = bloggerRepository;
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if(alreadySetup){
            return;
        }

        createRoleIfNotExists("ADMIN");
        createRoleIfNotExists("USER");

        Role adminRole = roleRepository.findByName("ADMIN");
        Blogger blogger = new Blogger();

        blogger.setEnabled(true);
        blogger.setRoles(Arrays.asList(adminRole));
        blogger.setPassword(passwordEncoder.encode("asd"));
        blogger.setUsername("test");
        blogger.setEmail("test@test.com");
        bloggerRepository.save(blogger);

        alreadySetup = true;

    }

    @Transactional
    public Role createRoleIfNotExists(String name) {
        Role role = roleRepository.findByName(name);

        if(role == null){
            role = new Role(name);
            roleRepository.save(role);
        }
        return role;
    }



}
