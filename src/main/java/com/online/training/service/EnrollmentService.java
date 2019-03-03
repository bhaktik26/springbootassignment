package com.online.training.service;

import com.online.training.repository.CourseRepository;
import com.online.training.repository.EnrollmentRepository;
import com.online.training.table.model.Course;
import com.online.training.table.model.Enrollment;
import com.online.training.table.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnrollmentService {

    private static final Logger logger = LoggerFactory.getLogger(EnrollmentService.class);
    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    CourseRepository courseRepository;

    public List<Course> getCoursesEnrolledByStudent() {
        List<Course> courses = new ArrayList<>();
        long studentId = tokenProvider.getIdFromSecurityContext();
        List<Enrollment> enrollment =  enrollmentRepository.getCoursesEnrolledByUserId(studentId);
        enrollment.forEach(enroll -> {
            logger.info("ENROLLMENT COURSE = {}", enroll.getCourse());
            courses.add(enroll.getCourse());
        });
        return courses;
    }

    public Enrollment enrolCourse(String courseName) {
        long id = courseRepository.findByName(courseName).getId();
        logger.info("COURSE_ID = {}", id);
        Enrollment enrollment = new Enrollment();
        enrollment.setDate(new Date(System.currentTimeMillis()));
        Course course = new Course();
        course.setId(id);
        enrollment.setCourse(course);
        User user = new User();
        user.setId(tokenProvider.getIdFromSecurityContext());
        enrollment.setUser(user);
        return enrollmentRepository.save(enrollment);
    }

    public List<Enrollment> getUsersEnrolledByCourseId(Long courseId) {
       return enrollmentRepository.getUsersEnrolledByCourseId(courseId);
    }
}
