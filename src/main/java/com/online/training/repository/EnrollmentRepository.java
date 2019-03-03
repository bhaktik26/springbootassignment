package com.online.training.repository;

import com.online.training.table.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    public List<Enrollment> getCoursesEnrolledByUserId(@Param("user") Long userId);

    public List<Enrollment> getUsersEnrolledByCourseId(@Param("course") Long courseId);
}
