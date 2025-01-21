package com.infosys.rewardsProgram.controller;


import com.infosys.rewardsProgram.model.RewardPointsResponse;
import com.infosys.rewardsProgram.services.RewardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }
    // Calculate rewards for a customer

    @GetMapping("/calculate/{customerId}")
    public ResponseEntity<?> calculateRewards(@PathVariable Long customerId) {
        return ResponseEntity.ok( rewardService.calculateRewardPoints(customerId));
    }

    // Calculate rewards for all customers

    @GetMapping("/calculate-all")
    public List<RewardPointsResponse> calculateAndRetrieveRewardsForAllCustomers() {
        return rewardService.calculateAndRetrieveRewardsForAllCustomers();
    }

}
