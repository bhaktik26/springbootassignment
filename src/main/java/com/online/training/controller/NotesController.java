package com.online.training.controller;

import com.online.training.model.Result;
import com.online.training.service.NotesService;
import com.online.training.service.TokenProvider;
import com.online.training.table.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
public class NotesController {

    @Autowired
    NotesService notesService;

    @Autowired
    TokenProvider tokenProvider;

    @GetMapping("/notes/{course_name}")
    public ResponseEntity<List<Notes>> getNotesForCourse(@PathVariable("course_name") String courseName) {
        return new ResponseEntity<List<Notes>>(notesService.getNotesForCourse(courseName),HttpStatus.OK);
    }

    @PostMapping("/notes")
    public ResponseEntity<?> uploadNotes(@RequestParam("name") String name,
                                         @RequestParam("chapterName") String chapterName,
                                         @RequestParam("courseName") String courseName,
                                         @RequestParam("notes_file") MultipartFile file) throws IOException {
        Notes notes = new Notes();
        notes.setName(name);
        notes.setChapterName(chapterName);
        notes.setCourseName(courseName);
        notes.setProfessorName(tokenProvider.getUsernameFromSecurityContext());
        notes.setNotePdf(file.getBytes());
        notesService.uploadNotes(notes);
        return ResponseEntity.ok(new Result("SUCCESS"));
    }

    @GetMapping("/download/notes/{notes_name}")
    public ResponseEntity<ByteArrayResource> getNotes(@PathVariable("notes_name") String notesName) {
        Notes notes = notesService.findByNotesName(notesName);
        byte[] notesPdf = notes.getNotePdf();
        return ResponseEntity
                .ok()
                .contentLength(notesPdf.length)
                .body(new ByteArrayResource(notesPdf));
    }



}
