package ru.job4j.dreamjob.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class CandidateStore {
    private final AtomicInteger id = new AtomicInteger(1);

    private final ConcurrentHashMap<Integer, Candidate> posts = new ConcurrentHashMap<>();

    public Collection<Candidate> findAll() {
        return posts.values();
    }

    public void add(Candidate candidate) {
        candidate.setCreated(LocalDate.now());
        candidate.setId(id.getAndIncrement());
        posts.putIfAbsent(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        posts.replace(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return posts.get(id);
    }
}
