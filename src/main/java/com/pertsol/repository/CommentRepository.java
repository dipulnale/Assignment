package com.pertsol.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pertsol.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
    @Query("SELECT c FROM Comment c WHERE c.createdBy = :username")
    Comment findByName(String username);

    @Query("SELECT c FROM Comment c WHERE c.dateOfComment = :date")
    List<Comment> findByDate(LocalDate date);

}
