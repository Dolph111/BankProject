package dolphin.demo.controller;

import dolphin.demo.model.*;
import dolphin.demo.repository.DepositRepository;
import dolphin.demo.service.DepositService;
import dolphin.demo.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/deposit")
public class DepositController {
    private final DepositRepository depositRepository;
    private final DepositService depositService;

    @Autowired
    public DepositController(DepositRepository depositRepository, DepositService depositService) {
        this.depositRepository = depositRepository;
        this.depositService = depositService;
    }

    @PostMapping("/create")
    public CreateDepositResponse createDeposit(@RequestBody Deposit deposit) {
        return depositService.createDeposit(deposit);
    }


    @PostMapping("/approve/{depositId}")
    public ApproveDepositResponse approveDeposit(@PathVariable long depositId) {
        return depositService.approveDeposit(depositId);
    }



    @PostMapping("/decline/{depositId}")
    public DeclineDepositResponse declineDeposit(@PathVariable long depositId) {
        return depositService.declineDeposit(depositId);
    }


    @PostMapping("/done/{depositId}")
    public DoneDepositResponse markDepositAsDone(@PathVariable long depositId) {
        return depositService.doneDeposit(depositId);

    }
}
