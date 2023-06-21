package Dolphin.demo;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Component
public class DepositScheduler {
    private final DepositRepository depositRepository;

    public DepositScheduler(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }

    @Scheduled(fixedRate = 60000) // Проверять каждую минуту
    public void checkDeposits() {
        Map<Long, Deposit> deposits = depositRepository.getDeposits();
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (Deposit deposit : deposits.values()) {
            if (deposit.getStatus() == DepositStatus.APPROVED &&
                    convertToDateTime(deposit.getTill()).isBefore(currentDateTime)) {
                deposit.setStatus(DepositStatus.DONE);
                depositRepository.saveDeposit(deposit);
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
        // Реализация возврата денег клиенту
    }

    private void sendEmailToClient(Deposit deposit) {
        // Реализация отправки уведомления клиенту по электронной почте
    }
}
