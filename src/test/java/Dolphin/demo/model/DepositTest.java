package Dolphin.demo.model;

import dolphin.demo.model.Deposit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DepositTest {
    Deposit deposit;

    @BeforeEach
    void setDeposit() {

        deposit = new Deposit(1000, "USD", 0.5f, 6);
    }

    @Test
    void getAmountTest() {
        Assertions.assertEquals(1000, deposit.getAmount());
    }

    @Test
    void getCurrency() {

        Assertions.assertEquals("USD", deposit.getCurrency());
    }

    @Test
    void getInterest() {

        Assertions.assertEquals(0.5f, deposit.getInterest());
    }

    @Test
    void getTill() {

        Assertions.assertEquals(6, deposit.getTill());
    }

}
