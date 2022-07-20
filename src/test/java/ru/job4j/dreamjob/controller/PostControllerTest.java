package ru.job4j.dreamjob.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.service.CityService;
import ru.job4j.dreamjob.service.PostService;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PostControllerTest {
    private PostController controller;
    private PostService postService;
    private CityService cityService;
    private Model model;
    private HttpSession session;

    @Before
    public void setUp() {
        model = mock(Model.class);
        postService = mock(PostService.class);
        cityService = mock(CityService.class);
        session = mock(HttpSession.class);
        controller = new PostController(postService, cityService);
    }

    @Test
    public void whenFindAllPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New Post", "post", LocalDate.now()),
                new Post(2, "New Post", "post", LocalDate.now())
        );
        when(postService.findAll()).thenReturn(posts);
        String page = controller.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenCreatePost() {
        Post post = new Post(1, "New Post", "post", LocalDate.now());
        String page = controller.createPost(post, 1);
        verify(postService).add(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(1, "New Post", "post", LocalDate.now());
        String page = controller.updatePost(post, 2);
        verify(postService).update(post);
        assertThat(page, is("redirect:/posts"));
    }

    @Test
    public void whenAddPost() {
        String page = controller.addPost(model, session);
        assertThat(page, is("addPost"));
    }
}