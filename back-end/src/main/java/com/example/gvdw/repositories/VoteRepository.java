package com.example.gvdw.repositories;

import com.example.gvdw.models.Post;
import com.example.gvdw.models.User;
import com.example.gvdw.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
  Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}
