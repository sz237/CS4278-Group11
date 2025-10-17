package com.plusone.PlusOneBackend.controller;

import com.plusone.PlusOneBackend.repository.PostRepository;
import com.plusone.PlusOneBackend.dto.ProfileResponse;
import com.plusone.PlusOneBackend.model.User;
import com.plusone.PlusOneBackend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
public class ProfileController {

  private final UserRepository userRepo;
  private final PostRepository postRepo;

  public ProfileController(UserRepository userRepo, PostRepository postRepo) {
    this.userRepo = userRepo;
    this.postRepo = postRepo;
  }

  @GetMapping("/{userId}/profile")
  public ProfileResponse getProfile(@PathVariable String userId) {
    User u = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    var posts = postRepo.findByUserIdOrderByCreatedAtDesc(userId);

    ProfileResponse r = new ProfileResponse();
    r.userId = u.getId();
    r.firstName = u.getFirstName();
    r.lastName = u.getLastName();
    r.connectionsCount = u.getNumConnections();
    r.requestsCount = u.getNumRequests();
    r.postsCount = posts.size();
    r.posts = posts;
    return r;
  }
}