package com.pertsol.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pertsol.exceptions.NotFoundException;
import com.pertsol.model.Comment;
import com.pertsol.service.CommentService;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {

	@Autowired
	CommentService comService;

	@PostMapping("/save")
	public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
		Comment commentObj = comService.addComment(comment);
		return new ResponseEntity<>(commentObj, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Comment>> getAllComments() {
		List<Comment> commentList = comService.getAllComments();
		return new ResponseEntity<>(commentList, HttpStatus.OK);
	}

	@GetMapping("/searchByUsername")
	public ResponseEntity<Comment> findByName(@RequestParam(value = "username", required = false) String username) {
		Comment commentObj = comService.getByName(username);
		if (commentObj == null) {
			throw new NotFoundException("Comment not found for " + username);
		}
		return new ResponseEntity<>(commentObj, HttpStatus.OK);
	}

	@GetMapping("/searchByDate")
	public ResponseEntity<List<Comment>> findByDate(@RequestParam(value = "date", required = false) String date)
			throws ParseException {
		LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		List<Comment> commentList = comService.getByDate(localDate);
		
		if (commentList.isEmpty()) {
			throw new NotFoundException("Comment not found for " + date);
		}
		return new ResponseEntity<>(commentList, HttpStatus.OK);
	}

	@PutMapping("/{commentId}")
	public ResponseEntity<Comment> updateComment(@PathVariable("commentId") int id, @RequestBody Comment comment) {
		Comment commentObj = comService.getById(id);
		if (commentObj == null) {
			throw new NotFoundException("Comment not found for " + id);
		}
		commentObj.setCreatedBy(comment.getCreatedBy());
		commentObj.setText(comment.getText());
		commentObj.setDateOfComment(comment.getDateOfComment());
		comService.addComment(commentObj);
		return new ResponseEntity<>(commentObj, HttpStatus.OK);
	}

}
