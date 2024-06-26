package com.pertsol.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pertsol.model.Comment;
import com.pertsol.repository.CommentRepository;

@Service
public class ICommentServiceImpl implements CommentService {

	@Autowired
	CommentRepository commentRepo;

	@Override
	public Comment addComment(Comment com) {
		return commentRepo.save(com);
	}

	@Override
	public Comment getByName(String name) {
		return commentRepo.findByName(name);
	}
	
	@Override
	public List<Comment> getByDate(LocalDate date) {
		List<Comment> commentList = commentRepo.findByDate(date);
		return commentList;
	}

	@Override
	public List<Comment> getAllComments() {
		List<Comment> commentList = commentRepo.findAll();
		return commentList;
	}

	@Override
	public Comment getById(int id) {
		return commentRepo.findById(id).orElseThrow();
	}

}
