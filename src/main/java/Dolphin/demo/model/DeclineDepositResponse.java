package dolphin.demo.model;

import lombok.Data;

@Data
public class DeclineDepositResponse {
    DepositStatus depositStatus = DepositStatus.DECLINED;
}
