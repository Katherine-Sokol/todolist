package com.goit.todolist.service;

import com.goit.todolist.entity.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final Map<Long, Note> notes;

    public List<Note> listAll() {
        return notes
                .values()
                .stream()
                .toList();
    }

    public Note add(Note note) {
        note.setId(new Random().nextInt());
        notes.put(note.getId(), note);
        return note;
    }

    public void deleteById(long id) {
        notes.remove(id);
    }

    public void update(Note note) {
        notes.replace(note.getId(), note);
    }

    public Note getById(long id) {
        return notes
                .values()
                .stream()
                .toList()
                .get((int) id);
    }
}
