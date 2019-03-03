package com.online.training.controller;

import com.online.training.model.EnrollmentInfo;
import com.online.training.model.Result;
import com.online.training.service.EnrollmentService;
import com.online.training.table.model.Course;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class EnrollmentController {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentController.class);
    @Autowired
    EnrollmentService enrollmentService;

    @GetMapping("/enrolled/course")
    public ResponseEntity<List<Course>> getCoursesEnrolledByStudent() {
        return new ResponseEntity<List<Course>>(enrollmentService.getCoursesEnrolledByStudent(), HttpStatus.OK);
    }

    @PostMapping("/enroll/course")
    public ResponseEntity<?> enrollCourse(@RequestBody EnrollmentInfo enrollmentInfo) {
        enrollmentService.enrolCourse(enrollmentInfo.getCourseName());
        return ResponseEntity.ok(new Result("SUCCESS"));
    }

}
