package dolphin.demo.service;

import dolphin.demo.model.Deposit;
import dolphin.demo.model.DepositStatus;
import dolphin.demo.repository.DepositRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Slf4j
@Component
public class DepositScheduler {
    private final DepositRepository depositRepository;
    private final DepositService depositService;

    public DepositScheduler(DepositRepository depositRepository, DepositService depositService) {

        this.depositRepository = depositRepository;
        this.depositService = depositService;
    }

    @Scheduled(fixedRate = 60000) // Проверять каждую минуту
    public void checkDeposits() {
        Map<Long, Deposit> deposits = depositService.getDeposits();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Deposit deposit : deposits.values()) {
            if (deposit.getStatus() == DepositStatus.APPROVED &&
                    convertToDateTime(deposit.getTill()).isBefore(currentDateTime)) {
                deposit.setStatus(DepositStatus.DONE);
                depositService.saveDeposit(deposit);
                returnMoneyToClient(deposit, deposit.getAmount());
                sendEmailToClient(deposit);
            }
        }
    }

    private LocalDateTime convertToDateTime(long till) {
        Instant instant = Instant.ofEpochMilli(till);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


    private void returnMoneyToClient(Deposit deposit, int amount) {
        log.info("Refunding the customer");
        // TODO Реализация возврата денег клиенту
    }

    private void sendEmailToClient(Deposit deposit) {
        log.info("Sending a notification to the customer by email");
        // TODO Реализация отправки уведомления клиенту по электронной почте
    }
}
