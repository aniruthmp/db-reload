
package io.pivotal.dbreload.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushGPSEvent {

    //TODO: After getting the sample XML request XML, define the required XmlProperty in all the POJOs
    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("eventTime")
    @NotNull()
    private Date eventTime;

    @JsonProperty("sentTime")
    @NotNull()
    private Date sentTime;

    @JsonProperty("providerId")
    private String providerId;

    @JsonProperty("lastUpdatedInterval")
    private int lastUpdatedInterval;

    @JsonProperty("responseId")
    private String responseId;

    @Length(max = 10)
    @JsonProperty("authorizationNumber")
    private String authorizationNumber;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("gpsAccuracy")
    private String gpsAccuracy;

    @JsonProperty("reasonCode")
    private String reasonCode;

    @JsonProperty("eta")
    private Integer eta;

    @JsonProperty("additionalInfo")
    private String additionalInfo;

    @JsonProperty("contactName")
    private String contactName;

    @JsonProperty("driverId")
    private String driverId;

    @JsonProperty("customerContacted")
    private String customerContacted;

    @JsonProperty("towRequired")
    private String towRequired;

}
