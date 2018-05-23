package io.pivotal.dbreload.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AogGpsProviderResult {

    private String usageId;
    private String providerId;
    private String jobStatus;
    private int result;
    private String error;
}
