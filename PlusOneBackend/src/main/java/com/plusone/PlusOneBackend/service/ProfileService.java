package com.plusone.PlusOneBackend.service;

import com.plusone.PlusOneBackend.dto.ProfileResponse;
import com.plusone.PlusOneBackend.model.User;
import com.plusone.PlusOneBackend.repository.ConnectionRepository;
import com.plusone.PlusOneBackend.repository.ConnectionRequestRepository;
import com.plusone.PlusOneBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ConnectionRequestRepository connectionRequestRepository;

    /**
     * Get user profile with counts
     */
    public ProfileResponse getProfile(String userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            throw new RuntimeException("User not found for userId: " + userId);
        }

        User user = userOpt.get();

        // Count connections for this user
        int connectionsCount = 0;
        try {
            connectionsCount = connectionRepository.countConnectionsForUser(userId);
        } catch (Exception e) {
            System.err.println("Error counting connections for user " + userId + ": " + e.getMessage());
        }

        // Count pending requests received by this user
        int requestsCount = 0;
        try {
            requestsCount = connectionRequestRepository.countByToUserIdAndStatus(userId, "PENDING");
        } catch (Exception e) {
            System.err.println("Error counting requests for user " + userId + ": " + e.getMessage());
        }

        // For now, posts count is 0 since we don't have posts implemented yet
        int postsCount = 0;

        return ProfileResponse.builder()
            .userId(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .connectionsCount(connectionsCount)
            .requestsCount(requestsCount)
            .postsCount(postsCount)
            .posts(new ArrayList<>()) // Empty for now
            .onboarding(ProfileResponse.OnboardingData.builder()
                .completed(user.getOnboarding() != null ? user.getOnboarding().isCompleted() : false)
                .step(user.getOnboarding() != null ? user.getOnboarding().getStep() : 1)
                .completedAt(user.getOnboarding() != null && user.getOnboarding().getCompletedAt() != null 
                    ? user.getOnboarding().getCompletedAt().toString() : null)
                .build())
            .build();
    }
}
