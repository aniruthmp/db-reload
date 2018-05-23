package io.pivotal.dbreload.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = "AGT_MST_SOURCE", schema = "AOG_MESSG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgtMstSource {
    @Id
    private String agtSourceId;
    private String agtSourceNm;
    private String agtSourceDesc;
    private String agtSystemCd;
    private String agtRemarks;
    private Time agtCreatedDt;
    private String agtCreatedBy;
    private Time agtUpdatedDt;
    private String agtUpdatedBy;
    private String amsExtrnlSourceId;
}