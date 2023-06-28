package dolphin.demo.model;

import lombok.Data;

@Data
public class CreateDepositResponse {
    DepositStatus depositStatus = DepositStatus.CREATED;
}
