package com.online.training.repository;

import com.online.training.table.model.Course;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    public Course findByName(String name);
    public List<Course> findByCreatedBy(String name);
}
