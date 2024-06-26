package com.pertsol.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Comments")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private int commentId;
	
	@Column(name="By")
	private String createdBy;
	
	@Column(name="Text")
	private String text;
	
	@Column(name="dateofcomment")
	private LocalDate dateOfComment;

	public Comment() {
		super();
	}

	public Comment(int commentId, String createdBy, String text, LocalDate dateOfComment) {
		super();
		this.commentId = commentId;
		this.createdBy = createdBy;
		this.text = text;
		this.dateOfComment = dateOfComment;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public LocalDate getDateOfComment() {
		return dateOfComment;
	}

	public void setDateOfComment(LocalDate dateOfComment) {
		this.dateOfComment = dateOfComment;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", createdBy=" + createdBy + ", text=" + text + ", dateOfComment="
				+ dateOfComment + "]";
	} 
	
	

}

