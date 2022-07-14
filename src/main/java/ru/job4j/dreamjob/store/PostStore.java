package ru.job4j.dreamjob.store;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {
    private static final PostStore INST = new PostStore();
    private final AtomicInteger id = new AtomicInteger(1);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
    }

    public static PostStore instOf() {
        return INST;
    }

    public void add(Post post) {
        post.setCreated(LocalDate.now());
        post.setId(id.getAndIncrement());
        posts.putIfAbsent(post.getId(), post);
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void update(Post post) {
        posts.put(post.getId(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
