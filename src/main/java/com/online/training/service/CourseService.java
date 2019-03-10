package com.online.training.service;

import com.online.training.repository.CourseRepository;
import com.online.training.repository.EnrollmentRepository;
import com.online.training.table.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course createCourse(Course courseInfo) {
        courseInfo.setCreatedOn(new Date(System.currentTimeMillis()));
        courseInfo.setCreatedBy(tokenProvider.getUsernameFromSecurityContext());
        return courseRepository.save(courseInfo);
    }

    public List<Course> getCoursesCreatedByProfessor(String professorName) {
        return courseRepository.findByCreatedBy(professorName);
    }

    public Course findCourseByName(String courseName) {
        return  courseRepository.findByName(courseName);
    }
}
