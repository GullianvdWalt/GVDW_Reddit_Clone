package com.example.gvdw.repositories;

import com.example.gvdw.models.Post;
import com.example.gvdw.models.Subreddit;
import com.example.gvdw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
  List<Post> findAllBySubreddit(Subreddit subreddit);
  List<Post> findByUser(User user);
}
