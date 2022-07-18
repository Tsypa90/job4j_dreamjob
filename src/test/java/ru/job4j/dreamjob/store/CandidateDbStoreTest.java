package ru.job4j.dreamjob.store;

import org.junit.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CandidateDbStoreTest {

    @Test
    public void whenCandidateAdd() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Pavel", "Java", LocalDate.now());
        store.add(candidate);
        var candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getDesc(), is(candidate.getDesc()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDbStore store = new CandidateDbStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Pavel", "Java", LocalDate.now());
        store.add(candidate);
        candidate.setName("Igor");
        store.update(candidate);
        var candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }
}