package dolphin.demo.repository.impl;

import dolphin.demo.model.Deposit;
import dolphin.demo.model.DepositStatus;
import dolphin.demo.repository.DepositRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepositRepositoryImpl implements DepositRepository {

    private final Map<Long, Deposit> deposits = new HashMap<>();
    private long depositIdCounter = 1;

    @Override
    public DepositStatus createDeposit(Deposit deposit) {
        long depositId = depositIdCounter++;
        deposit.setCreationDate(LocalDateTime.now());
        deposit.setStatus(DepositStatus.CREATED);
        deposits.put(depositId, deposit);
        return deposit.getStatus();
    }

    @Override
    public Map<Long, Deposit> getDeposits() {
        return deposits;
    }

    @Override
    public Deposit getDepositById(long depositId) {
        return deposits.get(depositId);
    }

    @Override
    public void saveDeposit(Deposit deposit) {
        deposits.put(deposit.getId(), deposit);
    }

    @Override
    public void removeDeposit(long depositId) {
        deposits.remove(depositId);
    }

    @Override
    public DepositStatus approveDeposit(Deposit deposit){
        deposit.setStatus(DepositStatus.APPROVED);
        return DepositStatus.APPROVED;
    }

    @Override
    public DepositStatus declineDeposit(Deposit deposit){
        deposit.setStatus(DepositStatus.DECLINED);
        return DepositStatus.DECLINED;
    }

    @Override
    public DepositStatus doneDeposit(Deposit deposit){
        deposit.setStatus(DepositStatus.DONE);
        return DepositStatus.DONE;
    }
}