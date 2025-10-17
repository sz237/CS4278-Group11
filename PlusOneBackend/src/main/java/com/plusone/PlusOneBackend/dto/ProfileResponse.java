package com.plusone.PlusOneBackend.dto;

import com.plusone.PlusOneBackend.model.Post;
import java.util.List;

public class ProfileResponse {
  public String userId;
  public String firstName;
  public String lastName;
  public int connectionsCount;
  public int requestsCount;
  public int postsCount;
  public List<Post> posts;
}