package dolphin.demo.repository;

import dolphin.demo.model.Deposit;
import dolphin.demo.model.DepositStatus;

import java.util.Map;

public interface DepositRepository {
    void saveDeposit(Deposit deposit);

    Map<Long, Deposit> getDeposits();

    Deposit getDepositById(long depositId);

    void removeDeposit(long depositId);

    DepositStatus createDeposit(Deposit deposit);

    DepositStatus approveDeposit(Deposit deposit);

    DepositStatus declineDeposit(Deposit deposit);

    DepositStatus doneDeposit(Deposit deposit);
}