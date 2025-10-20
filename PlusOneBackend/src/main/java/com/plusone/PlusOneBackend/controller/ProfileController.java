package com.plusone.PlusOneBackend.controller;

import java.time.LocalDateTime;
import java.util.List;

import com.plusone.PlusOneBackend.dto.ProfileResponse;
import com.plusone.PlusOneBackend.dto.ProfileUpdateRequest;
import com.plusone.PlusOneBackend.model.Profile;
import com.plusone.PlusOneBackend.model.User;
import com.plusone.PlusOneBackend.repository.PostRepository;
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
    User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    var posts = postRepo.findByUserIdOrderByCreatedAtDesc(userId);
    return toResponse(user, posts);
  }

  @PutMapping("/{userId}/profile")
  public ProfileResponse updateProfile(
      @PathVariable String userId,
      @RequestBody ProfileUpdateRequest request
  ) {
    User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    Profile currentProfile = user.getProfile();
    currentProfile = mergeProfile(currentProfile, request.getProfile());
    user.setProfile(currentProfile);
    user.setUpdatedAt(LocalDateTime.now());

    User.Onboarding onboarding = user.getOnboarding();
    if (onboarding == null) {
      onboarding = new User.Onboarding(false, 1, null);
    }
    if (request.getStep() != null) {
      onboarding.setStep(request.getStep());
    }
    if (request.getCompleted() != null) {
      boolean completed = request.getCompleted();
      onboarding.setCompleted(completed);
      onboarding.setCompletedAt(completed ? LocalDateTime.now() : null);
    }
    user.setOnboarding(onboarding);

    userRepo.save(user);
    var posts = postRepo.findByUserIdOrderByCreatedAtDesc(userId);
    return toResponse(user, posts);
  }

  private Profile mergeProfile(Profile existing, Profile incoming) {
    Profile result = existing != null ? existing : new Profile();
    if (incoming == null) {
      return result;
    }

    result.setGender(incoming.getGender());
    result.setAge(incoming.getAge());

    Profile.Location existingLocation = result.getLocation() != null ? result.getLocation() : new Profile.Location();
    Profile.Location incomingLocation = incoming.getLocation();
    if (incomingLocation != null) {
      if (incomingLocation.getCity() != null) {
        existingLocation.setCity(incomingLocation.getCity());
      }
      if (incomingLocation.getState() != null) {
        existingLocation.setState(incomingLocation.getState());
      }
      if (incomingLocation.getCountry() != null) {
        existingLocation.setCountry(incomingLocation.getCountry());
      }
      existingLocation.setLatitude(incomingLocation.getLatitude());
      existingLocation.setLongitude(incomingLocation.getLongitude());
    }
    result.setLocation(existingLocation);

    Profile.Job existingJob = result.getJob() != null ? result.getJob() : new Profile.Job();
    Profile.Job incomingJob = incoming.getJob();
    if (incomingJob != null) {
      if (incomingJob.getTitle() != null) {
        existingJob.setTitle(incomingJob.getTitle());
      }
      if (incomingJob.getCompaniesName() != null) {
        existingJob.setCompaniesName(incomingJob.getCompaniesName());
      }
      if (incomingJob.getCompanyId() != null) {
        existingJob.setCompanyId(incomingJob.getCompanyId());
      }
    }
    result.setJob(existingJob);

    if (incoming.getInterests() != null) {
      result.setInterests(incoming.getInterests());
    }

    Profile.Photo existingPhoto = result.getProfilePhoto() != null ? result.getProfilePhoto() : new Profile.Photo();
    Profile.Photo incomingPhoto = incoming.getProfilePhoto();
    if (incomingPhoto != null) {
      if (incomingPhoto.getStorage() != null) {
        existingPhoto.setStorage(incomingPhoto.getStorage());
      }
      if (incomingPhoto.getKey() != null) {
        existingPhoto.setKey(incomingPhoto.getKey());
      }
      if (incomingPhoto.getUrl() != null) {
        existingPhoto.setUrl(incomingPhoto.getUrl());
      }
    }
    result.setProfilePhoto(existingPhoto);

    return result;
  }

  private ProfileResponse toResponse(User user, List<com.plusone.PlusOneBackend.model.Post> posts) {
    ProfileResponse response = new ProfileResponse();
    response.userId = user.getId();
    response.firstName = user.getFirstName();
    response.lastName = user.getLastName();
    response.connectionsCount = user.getNumConnections();
    response.requestsCount = user.getNumRequests();
    response.postsCount = posts.size();
    response.posts = posts;
    response.profile = user.getProfile();
    response.onboarding = user.getOnboarding();
    return response;
  }
}
