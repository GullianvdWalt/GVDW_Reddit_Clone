package com.example.gvdw.models;


import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long postId;
  @NotBlank(message = "Post Name cannot be empty or Null")
  private String postName;
  @Nullable
  private String url;
  @Nullable
  @Lob // Large Text
  private String description;
  private Integer voteCount;
  // One User Many Posts
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="userId",referencedColumnName = "userId")
  private User user;
  private Instant createdDate;
  // One Post Many subposts
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id", referencedColumnName = "id")
  private Subreddit subreddit;


}
