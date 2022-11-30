package com.example.forum_backend.Repository;

import com.example.forum_backend.Comment.Comment;
import com.example.forum_backend.Comment.CommentRepository;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    public void CommentRepository_SaveAll_ReturnSavedComment() {

        Comment comment = new Comment();
        comment.setComment("comment test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        Comment testComment = commentRepository.save(comment);

        Assertions.assertThat(testComment).isNotNull();
        Assertions.assertThat(testComment.getId()).isGreaterThan(0);

    }

    @Test
    public void CommentRepository_GetAll_ReturnMoreThanOneComment() {

        Comment comment = new Comment();
        comment.setComment("comment test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        Comment comment2 = new Comment();
        comment2.setComment("comment test 2");
        comment2.setOwner(1L);
        comment2.setCreatedAt(new Date());
        comment2.setUpdatedAt(new Date());

        commentRepository.save(comment);
        commentRepository.save(comment2);

        List<Comment> commentList = commentRepository.findAll();

        Assertions.assertThat(commentList).isNotNull();
        Assertions.assertThat(commentList.size()).isEqualTo(2);
    }

    @Test
    public void CommentRepository_FindById_ReturnComment() {

        Comment comment = new Comment();
        comment.setComment("comment test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        Comment testComment = commentRepository.save(comment);

        Comment commentList = commentRepository.findById(testComment.getId()).get();

        Assertions.assertThat(commentList).isNotNull();

    }

    @Test
    public void CommentRepository_UpdateComment_ReturnCommentNotNull() {

        Comment comment = new Comment();
        comment.setComment("comment test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        commentRepository.save(comment);

        Comment commentSave = commentRepository.findById(comment.getId()).get();
        commentSave.setComment("Updated comment");
        commentSave.setUpdatedAt(new Date());

        Comment updatedComment = commentRepository.save(commentSave);

        Assertions.assertThat(updatedComment.getComment()).isNotNull();
        Assertions.assertThat(updatedComment.getUpdatedAt()).isNotNull();

    }

    @Test
    public void CommentRepository_DeleteComment_ReturnEmptyComment() {
        Comment comment = new Comment();
        comment.setComment("comment test");
        comment.setOwner(1L);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        commentRepository.save(comment);

        commentRepository.deleteById(comment.getId());
        Optional<Comment> commentReturn = commentRepository.findById(comment.getId());

        Assertions.assertThat(commentReturn).isEmpty();
    }

}
