package com.goit.service;

import com.goit.entities.Note;
import com.goit.exceptions.NoteNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;

    public List<Note> listAll() {
        return noteRepository.findAll();
    }

    public Note add(String title, String content) {
        Note note = new Note();
        note.setTitle(title);
        note.setContent(content);
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Long id, String content) {
        Note note = getById(id);
        note.setContent(content);
        noteRepository.save(note);
    }

    public Note getById(Long id) {
        Optional<Note> note = noteRepository.findById(id);
        if(note.isEmpty()){
            throw new NoteNotFoundException("Note not found");
        }
        return note.get();
    }
}
