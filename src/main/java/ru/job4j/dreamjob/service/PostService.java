package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Post;
import ru.job4j.dreamjob.store.PostDbStore;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Service
@ThreadSafe
public class PostService {
    private final PostDbStore store;

    private final CityService service;

    public PostService(PostDbStore store, CityService service) {
        this.store = store;
        this.service = service;
    }


    public void add(Post post) {
        post.setCreated(LocalDate.now());
        store.add(post);
    }

    public void update(Post post) {
        store.update(post);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public Collection<Post> findAll() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        service.findById(post.getCity().getId())
                )
        );
        return posts;
    }
}
