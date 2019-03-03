package com.online.training.controller;

import com.online.training.model.Constants;
import com.online.training.model.Result;
import com.online.training.service.CourseService;
import com.online.training.table.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class CourseController {

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    CourseService courseService;

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        return new ResponseEntity<List<Course>>(courseService.getAllCourses(), HttpStatus.OK);
    }

    @PostMapping("/course")
    public ResponseEntity<?> createCourse(@RequestBody Course course) {
        courseService.createCourse(course);
        return ResponseEntity.ok(new Result("SUCCESS"));
    }

    @GetMapping("/courses/{professor_name}")
    public ResponseEntity<List<Course>> getCoursesCreatedByProfessor(@PathParam("professor_name") String professorName) {
        return new ResponseEntity<List<Course>>(courseService.getCoursesCreatedByProfessor(professorName), HttpStatus.OK);
    }
}
