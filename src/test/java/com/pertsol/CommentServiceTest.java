package com.pertsol;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.pertsol.model.Comment;
import com.pertsol.repository.CommentRepository;
import com.pertsol.service.ICommentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    CommentRepository commentRepository;

    @InjectMocks
    ICommentServiceImpl comService;

    @Test
    public void testAddComment() {

        Comment newComment = new Comment();
        newComment.setCreatedBy("dipul");
        newComment.setText("testing addComment");

        Comment savedComment = new Comment();
        savedComment.setCommentId(1);
        savedComment.setCreatedBy(newComment.getCreatedBy());
        savedComment.setText(newComment.getText());

        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        Comment returnedComment = comService.addComment(newComment);

        assertNotNull(returnedComment);
        assertEquals(savedComment.getCommentId(), returnedComment.getCommentId());
        assertEquals(newComment.getCreatedBy(), returnedComment.getCreatedBy());
        assertEquals(newComment.getText(), returnedComment.getText());

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    public void testGetAllComments() {

        Comment comment1 = new Comment();
        comment1.setCommentId(1);
        comment1.setCreatedBy("user1");
        comment1.setText("Comment 1");

        Comment comment2 = new Comment();
        comment2.setCommentId(2);
        comment2.setCreatedBy("user2");
        comment2.setText("Comment 2");

        when(commentRepository.findAll()).thenReturn(Arrays.asList(comment1, comment2));

        List<Comment> commentList = comService.getAllComments();

        assertNotNull(commentList);
        assertEquals(2, commentList.size());

        verify(commentRepository, times(1)).findAll();
    }

    @Test
    public void testGetByName() {

        String username = "user1";
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setCreatedBy(username);
        comment.setText("User's comment");

        when(commentRepository.findByName(username)).thenReturn(comment);

        Comment returnedComment = comService.getByName(username);

        assertNotNull(returnedComment);
        assertEquals(username, returnedComment.getCreatedBy());

        verify(commentRepository, times(1)).findByName(username);
    }

    @Test
    public void testGetById() {
        // Prepare data
        int commentId = 1;
        Comment comment = new Comment();
        comment.setCommentId(commentId);
        comment.setCreatedBy("user1");
        comment.setText("Test comment");

        when(commentRepository.findById(commentId)).thenReturn(java.util.Optional.of(comment));

        // Call the service method
        Comment returnedComment = comService.getById(commentId);

        assertNotNull(returnedComment);
        assertEquals(commentId, returnedComment.getCommentId());

        verify(commentRepository, times(1)).findById(commentId);
    }
}