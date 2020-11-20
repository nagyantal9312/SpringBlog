package hu.suaf.springblog.service;

import hu.suaf.springblog.model.Blogger;
import hu.suaf.springblog.model.Role;
import hu.suaf.springblog.repository.BloggerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;

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
        bloggerRepository.save(blogger);
    }

    public void editBlogger(Blogger blogger) {

        //System.out.println("SSERVICE: " + blogger.getUsername() + blogger.getName() + blogger.getEmail() + blogger.getRoles() + blogger.getPassword() + blogger.getBirthDate());
        blogger.setPassword(passwordEncoder.encode(blogger.getPassword()));
        bloggerRepository.save(blogger);
    }

    public void uploadPhoto(Blogger blogger, MultipartFile image) {

        byte[] fileContent = new byte[0];
        try {
            fileContent = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String encodedString = Base64
                .getEncoder()
                .encodeToString(fileContent);

        blogger.setPhoto(encodedString);
        bloggerRepository.save(blogger);
    }



    public Blogger findByUsername(String username) {
        return bloggerRepository.findByUsername(username);
    }



}
