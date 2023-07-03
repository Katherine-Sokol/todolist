package com.goit.controllers;

import com.goit.entity.Note;
import com.goit.service.NoteService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private static final String REDIRECT = "redirect:/note/list";

    @GetMapping(value = "/list")
    public ModelAndView getListNotes() {
        ModelAndView result = new ModelAndView("note/list");
        List<Note> notes = noteService.listAll();
        result.addObject("notes", notes);
        return result;
    }

    @PostMapping(value = "/list/delete/{id}")
    public String deleteNoteById(@PathVariable Long id){
        noteService.deleteById(id);
        return REDIRECT;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView getEditPage(@PathVariable Long id){
        ModelAndView result = new ModelAndView("note/edit");
        Note note = noteService.getById(id);
        result.addObject("note", note);
        return result;
    }



    @PostMapping(value = "/edit/{id}")
    public String editNote(@PathVariable Long id, HttpServletRequest request){
        String content = request.getParameter("content");
        Note note = noteService.getById(id);
        note.setContent(content);
        noteService.update(note);
        return REDIRECT;
    }

    @GetMapping(value = "/add")
    public ModelAndView getEditPage(){
        return new ModelAndView("note/add");
    }


    @PostMapping(value = "/add")
    public String createNewNote(HttpServletRequest request){
        Note note = new Note();
        note.setTitle(request.getParameter("title"));
        note.setContent(request.getParameter("content"));
        noteService.add(note);
        return REDIRECT;
    }
}
