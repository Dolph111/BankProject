package dolphin.demo.service;

import dolphin.demo.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class DepositService {

    MoneyTransactionManager moneyTransactionManager = new MoneyTransactionManager();
    EmailService emailService = new EmailService("smtp.gmail.com", 465, "bankdeposittest@gmail.com", "Skywalker21");

    private final Map<Long, Deposit> deposits = new HashMap<>();
    private long depositIdCounter = 1;
    public CreateDepositResponse createDeposit(Deposit deposit) {
        long depositId = depositIdCounter++;
        deposit.setCreationDate(LocalDateTime.now());
        deposit.setStatus(DepositStatus.CREATED);
        deposits.put(depositId, deposit);
        log.info("Deposit created");
        emailService.sendEmailToBankEmployee(deposit);
        return new CreateDepositResponse();
    }

    public ApproveDepositResponse approveDeposit(long depositId){
        Deposit deposit = getDepositById(depositId);
        moneyTransactionManager.withdrawMoneyFromClient(deposit);
        saveDeposit(deposit);
        emailService.sendEmailToClient(deposit);
        deposit.setStatus(DepositStatus.APPROVED);
        log.info("Deposit has been approved");
        return new ApproveDepositResponse();

    }

    public DeclineDepositResponse declineDeposit(long depositId){
        Deposit deposit = getDepositById(depositId);
        deposit.setStatus(DepositStatus.DECLINED);
        emailService.sendEmailToClient(deposit);
        removeDeposit(depositId);
        log.info("Deposit declined");
        return new DeclineDepositResponse();
    }

    public DoneDepositResponse doneDeposit(long depositId){
        Deposit deposit = getDepositById(depositId);
        moneyTransactionManager.returnMoneyToClient(deposit, deposit.getAmount());
        saveDeposit(deposit);
        emailService.sendEmailToClient(deposit);
        log.info("Deposit has been marked as done");
        deposit.setStatus(DepositStatus.DONE);
        return new DoneDepositResponse();
    }

    public Deposit getDepositById(long depositId) {

        return deposits.get(depositId);
    }

    public void removeDeposit(long depositId) {

        deposits.remove(depositId);
    }

    public void saveDeposit(Deposit deposit) {

        deposits.put(deposit.getId(), deposit);
    }

    public Map<Long, Deposit> getDeposits() {

        return deposits;
    }
}
