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

    /**
     * Calculate rewards for a specific customer.
     *
     * @param customerId the ID of the customer whose rewards are to be calculated.
     * @return ResponseEntity containing the calculated reward points for the specified customer.
     */

    @GetMapping("/calculate/{customerId}")
    public ResponseEntity<?> calculateRewards(@PathVariable Long customerId) {
        return ResponseEntity.ok( rewardService.calculateRewardPoints(customerId));
    }

    /**
     * Calculate rewards for all customers.
     *
     * @return List of RewardPointsResponse containing the calculated reward points for all customers.
     */

    @GetMapping("/calculate-all")
    public List<RewardPointsResponse> calculateAndRetrieveRewardsForAllCustomers() {
        return rewardService.calculateAndRetrieveRewardsForAllCustomers();
    }

}
