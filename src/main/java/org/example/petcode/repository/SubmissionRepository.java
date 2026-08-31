package org.example.petcode.repository;

import org.example.petcode.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserIdAndTaskIdOrderByExecutedAtDesc(Long userId, Long taskId);
}