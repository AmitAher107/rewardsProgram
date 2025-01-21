package com.infosys.rewardsProgram.repo;

import com.infosys.rewardsProgram.model.RewardPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardPointsRepository extends JpaRepository<RewardPoints,Long> {
    boolean existsByCustomerIdAndMonth(Long customerId, String month);
}
