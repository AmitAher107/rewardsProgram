package com.infosys.rewardsProgram;

import com.infosys.rewardsProgram.model.RewardPoints;
import com.infosys.rewardsProgram.model.RewardPointsResponse;
import com.infosys.rewardsProgram.model.Transaction;
import com.infosys.rewardsProgram.repo.RewardPointsRepository;
import com.infosys.rewardsProgram.repo.TransactionRepository;
import com.infosys.rewardsProgram.services.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class RewardsProgramApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private RewardService rewardService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCalculateRewardPoints() {
		Long customerId = 1L;
		Transaction transaction1 = new Transaction(1L, customerId, 120.0, LocalDate.of(2023, 1, 15));
		Transaction transaction2 = new Transaction(2L, customerId, 80.0, LocalDate.of(2023, 2, 20));
		List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

		// Verify transactions list is not null and contains expected data
		assertNotNull(transactions);
		assertEquals(2, transactions.size());

		when(transactionRepository.findByCustomerId(customerId)).thenReturn(transactions);

		RewardPointsResponse rewardPointsResponse = rewardService.calculateRewardPoints(customerId);


		assertEquals(customerId, rewardPointsResponse.getCustomerId());
		assertEquals(2, rewardPointsResponse.getMonthlyPoints().size());
		assertEquals(90, rewardPointsResponse.getMonthlyPoints().get("JANUARY").intValue());
		assertEquals(30, rewardPointsResponse.getMonthlyPoints().get("FEBRUARY").intValue());
		assertEquals(120,rewardPointsResponse.getTotalPoints());
	}




	@Test
	public void testCalculatePoints() {
		assertEquals(90, rewardService.calculatePoints(120.0));
		assertEquals(25, rewardService.calculatePoints(75.0));
		assertEquals(250, rewardService.calculatePoints(200.0));
		assertEquals(0, rewardService.calculatePoints(50.0));
	}


	@Test
	public void testCalculateAndRetrieveRewardsForAllCustomers() {
		Long customerId1 = 1L;
		Long customerId2 = 2L;
		List<Long> customerIds = Arrays.asList(customerId1, customerId2);

		Transaction transaction1 = new Transaction(1L, customerId1, 120.0, LocalDate.of(2023, 1, 15));
		Transaction transaction2 = new Transaction(2L, customerId2, 80.0, LocalDate.of(2023, 2, 20));
		when(transactionRepository.findAllUniqueCustomerIds()).thenReturn(customerIds);
		when(transactionRepository.findByCustomerId(customerId1)).thenReturn(Collections.singletonList(transaction1));
		when(transactionRepository.findByCustomerId(customerId2)).thenReturn(Collections.singletonList(transaction2));

		List<RewardPointsResponse> responses = rewardService.calculateAndRetrieveRewardsForAllCustomers();

		assertEquals(2, responses.size());
		assertEquals(customerId1, responses.get(0).getCustomerId());
		assertEquals(90, responses.get(0).getTotalPoints());
		assertEquals(customerId2, responses.get(1).getCustomerId());
		assertEquals(30, responses.get(1).getTotalPoints());
	}
}
