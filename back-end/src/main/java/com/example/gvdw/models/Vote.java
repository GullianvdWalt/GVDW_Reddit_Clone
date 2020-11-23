package com.example.gvdw.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private long voteId;
  private VoteType voteType;
  @NotNull
  // many votes on one post
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="postId",referencedColumnName = "postId")
  private Post post;
  // Many users can upvote
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="userId",referencedColumnName = "userId")
  private User user;
}
