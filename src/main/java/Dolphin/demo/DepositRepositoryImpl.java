package Dolphin.demo;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepositRepositoryImpl implements DepositRepository {

    private final Map<Long, Deposit> deposits = new HashMap<>();
    private long depositIdCounter = 1;

    @Override
    public long createDeposit(Deposit deposit) {
        long depositId = depositIdCounter++;
        deposit.setCreationDate(LocalDateTime.now());
        deposit.setStatus(DepositStatus.CREATED);
        deposits.put(depositId, deposit);
        return depositId;
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
}
