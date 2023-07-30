package dolphin.demo.model;

import lombok.Data;

@Data
public class ApproveDepositResponse {
    DepositStatus depositStatus = DepositStatus.APPROVED;
}
