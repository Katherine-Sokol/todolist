package com.goit.service;

import com.goit.entity.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class NoteServiceTests {
    Map<Long, Note> notes;

    @BeforeEach
    void beforeEach() {
        notes = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setId(i);
            note.setTitle("Title " + i);
            note.setContent("Content " + i);
            notes.put(note.getId(), note);
        }
    }

    @Test
    void listAll() {
        NoteService noteService = new NoteService(notes);
        List<Note> noteList = noteService.listAll();
        Assertions.assertEquals(noteList.size(), notes.size());
    }

    @Test
    void add() {
        NoteService noteService = new NoteService(notes);
        Note note = new Note();
        note.setTitle("New title");
        note.setContent("New content");
        Note added = noteService.add(note);
        assertTrue(notes.containsValue(added));
    }

    @Test
    void deleteById() {
        NoteService noteService = new NoteService(notes);
        noteService.deleteById(5L);
        assertEquals(9, notes.size());
        assertFalse(notes.containsKey(5L));
    }

    @Test
    void update() {
        NoteService noteService = new NoteService(notes);
        Note note = noteService.getById(7);
        String updatedContent = "Updated content";
        note.setContent(updatedContent);
        noteService.update(note);
        Assertions.assertEquals(noteService.getById(7).getContent(), updatedContent);
    }

    @Test
    void getById() {
        NoteService noteService = new NoteService(notes);
        Note note = noteService.getById(3);
        assertEquals(note, notes.get(3L));
        assertTrue(notes.containsValue(note));
    }
}