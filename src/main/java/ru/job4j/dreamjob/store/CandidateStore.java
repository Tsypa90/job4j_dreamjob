package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.model.Post;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();
    private final AtomicInteger id = new AtomicInteger(1);

    private final ConcurrentHashMap<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
    }

    public static CandidateStore getInst() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return posts.values();
    }

    public void add(Candidate candidate) {
        candidate.setCreated(LocalDate.now());
        candidate.setId(id.getAndIncrement());
        posts.putIfAbsent(candidate.getId(), candidate);
    }

    public void update(Candidate candidate) {
        posts.put(candidate.getId(), candidate);
    }

    public Candidate findById(int id) {
        return posts.get(id);
    }
}
