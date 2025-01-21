package com.infosys.rewardsProgram.services;

import com.infosys.rewardsProgram.model.RewardPoints;
import com.infosys.rewardsProgram.model.RewardPointsResponse;
import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.repo.ResourceNotFoundException;
import com.infosys.rewardsProgram.repo.RewardPointsRepository;
import com.infosys.rewardsProgram.repo.TransactionRepository;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.query.JSqlParserUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardService {

    public TransactionRepository transactionRepository;
    public RewardPointsRepository rewardPointsRepository;

    public RewardService(TransactionRepository transactionRepository, RewardPointsRepository rewardPointsRepository) {
        this.transactionRepository = transactionRepository;
        this.rewardPointsRepository = rewardPointsRepository;
    }


    /**
     * Calculates the reward points for a given customer Id.
     * Aggregates the points as per month and saves the results in the RewardPoints table.
     *
     * @param customerId the ID of the customer
     * @return the reward points as per month and the total reward points as per requirements
     */


    public RewardPointsResponse calculateRewardPoints(Long customerId) {
        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);
        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No transactions found for customer ID: " + customerId);
        }

        Map<String, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getAmount() == null) {
                continue;                                 // Skip transactions with null amount
            }
            int points = calculatePoints(transaction.getAmount());
            String month = transaction.getDate().getMonth().toString();

            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
            totalPoints += points;
        }

          return new RewardPointsResponse(customerId, monthlyPoints, totalPoints);
    }


    /**
     * Calculates the reward points for a given transaction amount.
     * for calculation logic refer README.md
     *
     * @param amount the transaction amount
     * @return the calculated reward points
     */

    public int calculatePoints(double amount) {
        int points = 0;

        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }

        if (amount > 50) {
            points += (amount - 50);
        }

        return points;
    }


    /**
     * Calculates the reward points .
     * for calculation logic refer README.md
     *
     * @return the calculated reward points for all customers
     */

    public List<RewardPointsResponse> calculateAndRetrieveRewardsForAllCustomers() {
        List<RewardPointsResponse> rewardPointsResponses = new ArrayList<>();
        List<Long> CustomerIds = transactionRepository.findAllUniqueCustomerIds();
        for(Long customerId : CustomerIds){
            RewardPointsResponse rewardPointsResponse = calculateRewardPoints(customerId);
            rewardPointsResponses.add(rewardPointsResponse);
        }
        rewardPointsResponses.sort((r1, r2) -> r1.getCustomerId().compareTo(r2.getCustomerId()));
        return rewardPointsResponses;
    }


}
