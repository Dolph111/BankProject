package Dolphin.demo;

import java.util.Map;

public interface DepositRepository {
    void saveDeposit(Deposit deposit);

    Map<Long, Deposit> getDeposits();

    Deposit getDepositById(long depositId);

    void removeDeposit(long depositId);

    long createDeposit(Deposit deposit);
}
