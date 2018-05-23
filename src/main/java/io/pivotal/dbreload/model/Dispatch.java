package io.pivotal.dbreload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dispatch {
    private boolean allState = true;
    private Integer responseId;
    private String usageId;
    private String authorizationNbr;
    private String partnerCd;
    private String providerId;
    private Long transId;
    private Long driverId;
    private String jobStatus;
    private String sourceId;
    private String externalStatusId;
    private boolean jobExists;

    public Long getDriverId() {
        if (Objects.isNull(driverId))
            return 0l;
        else
            return driverId;
    }

    public Long getTransId() {
        if (Objects.isNull(transId))
            return 0l;
        else
            return transId;
    }
}
