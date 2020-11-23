package com.example.gvdw.repositories;

import com.example.gvdw.models.Comment;
import com.example.gvdw.models.Post;
import com.example.gvdw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
  List<Comment> findByPost(Post post);
  List<Comment> findAllByUser(User user);
}
