package ru.job4j.dreamjob.store;

import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {
    private static final CandidateStore INST = new CandidateStore();

    private final ConcurrentHashMap<Integer, Candidate> posts = new ConcurrentHashMap<>();

    private CandidateStore() {
        posts.put(1, new Candidate(1, "Pavel", "java developer", LocalDate.now()));
        posts.put(2, new Candidate(2, "Andrey", "java developer", LocalDate.now()));
        posts.put(3, new Candidate(3, "Igor", "java developer", LocalDate.now()));
    }

    public static CandidateStore getInst() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return posts.values();
    }
}
