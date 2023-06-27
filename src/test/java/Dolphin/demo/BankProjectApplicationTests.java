package Dolphin.demo;

import dolphin.demo.controller.DepositController;
import dolphin.demo.model.Deposit;
import dolphin.demo.model.DepositStatus;
import dolphin.demo.repository.DepositRepository;
import lombok.experimental.StandardException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	DepositRepository depositRepository;

	@Mock
	Deposit deposit;


	@Test
	void testCreateDeposit() {
		DepositStatus expectedStatus  = DepositStatus.CREATED;

		Mockito
				.when(depositRepository.createDeposit(deposit))
				.thenReturn(DepositStatus.CREATED);

		DepositStatus actualStatus = depositRepository.createDeposit(deposit);

		Assertions.assertEquals(expectedStatus, actualStatus);

	}

	@Test
	void testApproveDeposit() {
		DepositStatus expectedStatus  = DepositStatus.APPROVED;

		Mockito
				.when(depositRepository.approveDeposit(deposit))
				.thenReturn(DepositStatus.APPROVED);

		DepositStatus actualStatus = depositRepository.approveDeposit(deposit);
		Assertions.assertEquals(expectedStatus, actualStatus);
	}

	@Test
	void testDeclineDeposit() {
		DepositStatus expectedStatus  = DepositStatus.DECLINED;

		Mockito
				.when(depositRepository.declineDeposit(deposit))
				.thenReturn(DepositStatus.DECLINED);

		DepositStatus actualStatus = depositRepository.declineDeposit(deposit);
		Assertions.assertEquals(expectedStatus, actualStatus);
	}

	@Test
	void testDoneDeposit() {
		DepositStatus expectedStatus  = DepositStatus.DONE;

		Mockito
				.when(depositRepository.doneDeposit(deposit))
				.thenReturn(DepositStatus.DONE);
		DepositStatus actualStatus = depositRepository.doneDeposit(deposit);
		Assertions.assertEquals(expectedStatus, actualStatus);
	}

	@Test
	void testDeposit() {
		Mockito
				.when(deposit.getAmount())
				.thenReturn(20);

		Mockito
				.when(deposit.getCurrency())
				.thenReturn("USD");

		Mockito
				.when(deposit.getInterest())
				.thenReturn(7F);

		Mockito
				.when(deposit.getTill())
				.thenReturn(9L);

		Assertions.assertEquals(20, deposit.getAmount());
		Assertions.assertEquals("USD", deposit.getCurrency());
		Assertions.assertEquals(7, deposit.getInterest());
		Assertions.assertEquals(9, deposit.getTill());
	}
}
