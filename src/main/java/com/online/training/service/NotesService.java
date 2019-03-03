package com.online.training.service;

import com.online.training.repository.CourseRepository;
import com.online.training.repository.NotesRepository;
import com.online.training.table.model.Course;
import com.online.training.table.model.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    NotesRepository notesRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Notes> getNotesForCourse(String courseName) {
        return notesRepository.findByCourseName(courseName);
    }

    public void uploadNotes(com.online.training.table.model.Notes notes) {
        notesRepository.save(notes);
    }

    public Notes findByNotesName(String notesName) {
        return notesRepository.findByName(notesName);
    }
}
