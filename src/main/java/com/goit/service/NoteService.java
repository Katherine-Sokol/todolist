package com.goit.service;

import com.goit.entity.Note;
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

    public Note add(Note note) {
        return noteRepository.save(note);
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }

    public void update(Note note) {
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
