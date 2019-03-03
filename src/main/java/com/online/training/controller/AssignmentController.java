package com.online.training.controller;

import com.online.training.model.AssignmentInfo;
import com.online.training.model.Result;
import com.online.training.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AssignmentController {

    @Autowired
    private  AssignmentService assignmentService;

    @PostMapping("/assignment")
    public ResponseEntity<?> createAssignment(@RequestBody AssignmentInfo assignmentInfo) {
        assignmentService.createAndAssignAssignment(assignmentInfo);
        return ResponseEntity.ok(new Result("SUCCESS"));
    }
}
