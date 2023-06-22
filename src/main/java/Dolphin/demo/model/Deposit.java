package dolphin.demo.model;

import lombok.Data;

import java.time.LocalDateTime;
    @Data
    public class Deposit {
    private int amount;
    private String currency;
    private float interest;
    private long till;
    private DepositStatus status;
    private LocalDateTime creationDate;

    private Long id;

    public Deposit(int amount, String currency, float interest, long till) {
        this.amount = amount;
        this.currency = currency;
        this.interest = interest;
        this.till = till;
        this.status = DepositStatus.CREATED;
        this.creationDate = LocalDateTime.now();
    }
}
