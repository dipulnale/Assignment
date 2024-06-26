package com.pertsol.service;

import java.time.LocalDate;
import java.util.List;

import com.pertsol.model.Comment;

public interface CommentService {

	public Comment addComment(Comment com);

	public Comment getByName(String name);

	public List<Comment> getByDate(LocalDate date);

	public List<Comment> getAllComments();
	
	public Comment getById(int id);

}
