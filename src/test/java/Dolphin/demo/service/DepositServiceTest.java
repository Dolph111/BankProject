package Dolphin.demo.service;

import dolphin.demo.model.*;
import dolphin.demo.service.DepositService;
import dolphin.demo.service.EmailService;
import dolphin.demo.service.MoneyTransactionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class DepositServiceTest {

    @InjectMocks
    private DepositService depositService;
    private Deposit deposit;

    @Mock
    private EmailService emailService;
    private final Map<Long, Deposit> deposits = new HashMap<>();


    @BeforeEach
    void setDepositService() {
        depositService = new DepositService();
    }

    @BeforeEach
    void setDeposit() {
        deposit = new Deposit(1000, "USD", 0.5f, 6);
        deposit.setId(1L);
    }

    @Test
    void createDepositTest() {
        CreateDepositResponse createDepositResponse = new CreateDepositResponse();
        Assertions.assertEquals(createDepositResponse, depositService.createDeposit(deposit));
    }

    @Test
    void approveDepositTest() {
        ApproveDepositResponse approveDepositResponse = new ApproveDepositResponse();
        depositService.saveDeposit(deposit);
        Assertions.assertEquals(approveDepositResponse, depositService.approveDeposit(deposit.getId()));
    }

    @Test
    void declineDepositTest() {
        DeclineDepositResponse declineDepositResponse = new DeclineDepositResponse();
        depositService.saveDeposit(deposit);
        Assertions.assertEquals(declineDepositResponse, depositService.declineDeposit(deposit.getId()));
    }

    @Test
    void doneDepositTest() {
        DoneDepositResponse doneDepositResponse = new DoneDepositResponse();
        depositService.saveDeposit(deposit);
        Assertions.assertEquals(doneDepositResponse, depositService.doneDeposit(deposit.getId()));
    }

    @Test
    void getDepositByIdTest() {
        depositService.saveDeposit(deposit);
        Assertions.assertEquals(deposit, depositService.getDepositById(deposit.getId()));
    }

    @Test
    void removeDepositTest() {
        depositService.saveDeposit(deposit);
        assertNull(deposits.get(deposit.getId()));
    }

    @Test
    void saveDepositTest() {
        deposits.put(deposit.getId(), deposit);
        Assertions.assertEquals(deposits.get(deposit.getId()), deposit);
    }

    @Test
    void getDepositsTest() {
        Map<Long, Deposit> newDepositsMap = new HashMap<>();
        newDepositsMap.put(deposit.getId(), deposit);
        depositService.saveDeposit(deposit);
        Assertions.assertEquals(newDepositsMap, depositService.getDeposits());
    }
}
