package com.online.training.repository;

import com.online.training.table.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
    public List<Notes> findByCourseName(String courseName);

    public Notes findByName(String name);
}
