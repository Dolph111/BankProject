package dolphin.demo.service;

import dolphin.demo.model.Deposit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MoneyTransactionManager {

    void withdrawMoneyFromClient(Deposit deposit) {
        log.info("Withdrawals from the client");
    }

    void returnMoneyToClient(Deposit deposit, int amount) {
        log.info("Refunding the customer");
    }
}
