package Dolphin.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deposit")
public class DepositController {
    private final DepositRepository depositRepository;

    @Autowired
    public DepositController(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createDeposit(@RequestBody Deposit deposit) {
        long depositId = depositRepository.createDeposit(deposit);
        sendEmailToBankEmployee(depositId);
        return new ResponseEntity<>("Deposit created with ID: " + depositId, HttpStatus.CREATED);
    }

    @PostMapping("/approve/{depositId}")
    public ResponseEntity<String> approveDeposit(@PathVariable long depositId) {
        Deposit deposit = depositRepository.getDepositById(depositId);
        if (deposit != null && deposit.getStatus() == DepositStatus.CREATED) {
            // тут дополнительные проверки

            deposit.setStatus(DepositStatus.APPROVED);
            withdrawMoneyFromClient(deposit);
            depositRepository.saveDeposit(deposit);
            sendEmailToClient(deposit);
            return new ResponseEntity<>("Deposit with ID " + depositId + " has been approved.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to approve deposit with ID " + depositId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/decline/{depositId}")
    public ResponseEntity<String> declineDeposit(@PathVariable long depositId) {
        Deposit deposit = depositRepository.getDepositById(depositId);
        if (deposit != null && deposit.getStatus() == DepositStatus.CREATED) {
            deposit.setStatus(DepositStatus.DECLINED);
            sendEmailToClient(deposit);
            depositRepository.removeDeposit(depositId);
            return new ResponseEntity<>("Deposit declined with ID: " + depositId, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to decline deposit with ID " + depositId, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/done/{depositId}")
    public ResponseEntity<String> markDepositAsDone(@PathVariable long depositId) {
        Deposit deposit = depositRepository.getDepositById(depositId);
        if (deposit != null && deposit.getStatus() == DepositStatus.APPROVED) {
            deposit.setStatus(DepositStatus.DONE);
            returnMoneyToClient(deposit, deposit.getAmount());
            depositRepository.saveDeposit(deposit);
            sendEmailToClient(deposit);
            return new ResponseEntity<>("Deposit with ID " + depositId + " has been marked as done.",
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to mark deposit with ID " + depositId + " as done.",
                    HttpStatus.NOT_FOUND);
        }
    }

    private void withdrawMoneyFromClient(Deposit deposit) {
        System.out.println("Реализация снятия денег с клиента");
    }

    private void returnMoneyToClient(Deposit deposit, int amount) {
        System.out.println("Реализация возврата денег клиенту");
    }


    private void sendEmailToBankEmployee(long depositId) {
        String host = "smtp.gmail.com";
        int port = 465;
        String senderEmail = "bankdeposittest@gmail.com";
        String senderPassword = "Skywalker21";
        String recipientEmail = "524dolphin524@gmail.com";
        String subject = "New Deposit Request";
        String body = "New deposit request with ID: " + depositId;

        EmailSender emailSender = new EmailSender(host, port, senderEmail, senderPassword);
        emailSender.sendEmail(recipientEmail, subject, body);
    }

    private void sendEmailToClient(Deposit deposit) {
        EmailSender emailSender = new EmailSender("smtp.gmail.com", 465, "bankdeposittest@gmail.com",
                "Skywalker21");
        String to = "524dolphin524@gmail.com";
        String subject = "Deposit Update";
        String body = "Your deposit " + deposit.getId() + " has been " +
                deposit.getStatus().toString();
        emailSender.sendEmail(to, subject, body);
    }
}
