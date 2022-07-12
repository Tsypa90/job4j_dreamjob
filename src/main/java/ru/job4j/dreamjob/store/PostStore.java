package ru.job4j.dreamjob.store;

import jdk.jfr.Threshold;
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
    private final AtomicInteger id = new AtomicInteger(0);

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
    }

    public static PostStore instOf() {
        return INST;
    }

    public void add(Post post) {
        posts.putIfAbsent(id.getAndIncrement(), post);
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
