package com.goit.controllers;

import com.goit.entity.Note;
import com.goit.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NoteController {
    private static final NoteService noteService = new NoteService(getDefaultMapOfNotes());

    @GetMapping(value = "/note/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("note/list");
        List<Note> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @PostMapping(value = "/note/list/delete/{id}")
    public String deleteNoteById(@PathVariable Long id){
        noteService.deleteById(id);
        return "redirect:/note/list";
    }

    @GetMapping(value = "/note/edit/{id}")
    public ModelAndView getEditPage(@PathVariable Long id){
        ModelAndView result = new ModelAndView("note/edit");
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping(value = "/note/edit/{id}")
    public String editNote(@PathVariable Long id, HttpServletRequest request){
        String content = request.getParameter("content");
        Note note = noteService.getById(id);
        note.setContent(content);
        noteService.update(note);
        return "redirect:/note/list";
    }

    private static Map<Long, Note> getDefaultMapOfNotes() {
        Map<Long, Note> noteMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            Note note = new Note();
            note.setId(i);
            note.setTitle("Title " + i);
            note.setContent("Content " + i);
            noteMap.put(note.getId(), note);
        }
        return noteMap;
    }
}
