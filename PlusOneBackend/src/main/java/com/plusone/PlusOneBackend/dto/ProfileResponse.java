package com.plusone.PlusOneBackend.dto;

import com.plusone.PlusOneBackend.model.Post;
import com.plusone.PlusOneBackend.model.Profile;
import com.plusone.PlusOneBackend.model.User;
import java.util.List;

public class ProfileResponse {
  public String userId;
  public String firstName;
  public String lastName;
  public int connectionsCount;
  public int requestsCount;
  public int postsCount;
  public List<Post> posts;
  public Profile profile;
  public User.Onboarding onboarding;
}
