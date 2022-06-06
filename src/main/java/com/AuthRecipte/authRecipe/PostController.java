package com.AuthRecipte.authRecipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    SiteUserRepo siteUserRepo;
    @Autowired
    PostRepo postRepo;

    @GetMapping("/")
    public String getHome(Model m){
//        List<Post> postList = postRepo.findAll();
//        m.addAttribute("posts",postList);
        List<SiteUser> userList = siteUserRepo.findAll();
        m.addAttribute("users",userList);
        return "index";
    }

    @PostMapping("/addPost")
    public RedirectView addPost(String text, String username){
        SiteUser siteUser = siteUserRepo.findByUsername(username);
        Post newPost = new Post(text,siteUser);
        postRepo.save(newPost);
        return new RedirectView("/");
    }
}
