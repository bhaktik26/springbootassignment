package com.online.training.service;

import com.online.training.model.AssignmentInfo;
import com.online.training.repository.AssignmentRepository;
import com.online.training.table.model.Assignment;
import com.online.training.table.model.Enrollment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class AssignmentService {

    private static final Logger logger = LoggerFactory.getLogger(AssignmentService.class);
    @Autowired
    AssignmentRepository assignmentRepository;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    EnrollmentService enrollmentService;

    @Autowired
    CourseService courseService;


    public void createAndAssignAssignment(AssignmentInfo assignmentInfo) {
        Assignment assignment = new Assignment();
        assignment.setName(assignmentInfo.getAssignmentName());
        assignment.setCreatedBy(tokenProvider.getUsernameFromSecurityContext());
        assignment.setCourseName(assignmentInfo.getCourse());
        JSONObject json = new JSONObject(assignmentInfo);
        String assignmentStr = json.getJSONArray("assignment").toString();
        logger.info("ASSIGNMENT STR = {}", assignmentStr);
        assignment.setAssignmentInfo(assignmentStr);
        assignment.setCreatedOn(new Date(System.currentTimeMillis()));
        assignmentRepository.save(assignment);
        //TODO
        //FIND STUDENTS ENROLLED IN COURSE
        List<Enrollment> enrollment = enrollmentService.getUsersEnrolledByCourseId(
                courseService.findCourseByName(assignmentInfo.getCourse()).getId());

        logger.info("ENROLLMENTS = {}", enrollment);

    }
}
