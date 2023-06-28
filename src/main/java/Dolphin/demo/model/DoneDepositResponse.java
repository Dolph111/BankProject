package dolphin.demo.model;


import lombok.Data;

@Data
public class DoneDepositResponse {
    DepositStatus depositStatus = DepositStatus.DONE;
}
