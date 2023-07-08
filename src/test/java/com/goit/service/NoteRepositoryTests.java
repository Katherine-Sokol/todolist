package com.goit.service;

import com.goit.entities.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NoteRepositoryTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private NoteRepository noteRepository;

    @BeforeEach
    @Transactional
    void beforeEach() {
        for (long i = 0; i < 10; i++) {
            Note note = new Note();
            note.setTitle("Title " + i);
            note.setContent("Content " + i);
            entityManager.persist(note);
        }
    }

    @Test
    void testThatFindAllWorksCorrectly() {
        Assertions.assertEquals(10, noteRepository.findAll().size());
    }

    @Test
    void testThatNoteFoundCorrectly() {
        Note note = new Note();
        note.setTitle("New title");
        note.setContent("New content");
        entityManager.persist(note);
        Note foundNote = noteRepository.findById(note.getId()).get();
        assertEquals(note, foundNote);
    }

    @Test
    void testThatNoteDeleted() {
        Note note = new Note();
        note.setTitle("Smth");
        note.setContent("Smth");
        entityManager.persist(note);
        long id = (long) entityManager.getId(note);
        Note foundNote = noteRepository.findById(id).get();
        noteRepository.deleteById(foundNote.getId());
        assertEquals(10, noteRepository.findAll().size());
        assertFalse(noteRepository.findAll().contains(foundNote));
    }

    @Test
    void testThatNoteUpdatedCorrectly() {
        Note note = new Note();
        note.setTitle("Smth");
        note.setContent("Smth");
        entityManager.persist(note);
        long id = (long) entityManager.getId(note);
        Note foundNote = noteRepository.findById(id).get();
        foundNote.setContent("Etc");
        Note saved = noteRepository.save(foundNote);
        assertEquals(saved, foundNote);
    }

    @Test
    void testThatNoteCreated() {
        Note note = new Note();
        note.setTitle("Smth");
        note.setContent("Smth");
        noteRepository.save(note);
        long id = (long) entityManager.getId(note);
        assertEquals(id, noteRepository.findById(note.getId()).get().getId());
    }
}
