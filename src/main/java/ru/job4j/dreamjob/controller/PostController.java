package ru.job4j.dreamjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostStore;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Controller
public class PostController {
    private final PostStore store = PostStore.instOf();

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", store.findAll());
        return "posts";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
        return "addPost";
    }
    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        post.setCreated(LocalDate.now());
        store.add(post);
        return "redirect:/posts";
    }
}
