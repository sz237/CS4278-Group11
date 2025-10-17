package com.plusone.PlusOneBackend.controller;

import com.plusone.PlusOneBackend.model.Post;
import com.plusone.PlusOneBackend.repository.PostRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}, allowCredentials = "true")
public class PostController {

  private final PostRepository repo;

  public PostController(PostRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Post> list(@RequestParam String userId) {
    return repo.findByUserIdOrderByCreatedAtDesc(userId);
  }

  @PostMapping
  public Post create(@RequestBody Post p) {
    p.setId(null);
    return repo.save(p);
  }

  @PutMapping("/{id}")
  public Post update(@PathVariable String id, @RequestBody Post p) {
    p.setId(id);
    return repo.save(p);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable String id) {
    repo.deleteById(id);
  }
}