package io.pivotal.dbreload.repository;

import io.pivotal.dbreload.model.AogGpsProviderResult;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.util.Date;

@Repository
public class AogGpsProviderRepository {

    EntityManager aogEntityManager;

    public AogGpsProviderRepository(EntityManager aogEntityManager) {
        this.aogEntityManager = aogEntityManager;
    }

    public AogGpsProviderResult gpsProvdrMobil(Integer responseId,  String eventType,
                                               Date eventTime, String latitude, String longitude,
                                               Integer eta, String gpsAccuracy, String  jobList,
                                               Date sentTime,  String channelId, Long driverId,
                                               Long transId, Integer appId)
    {

        StoredProcedureQuery proc = aogEntityManager.createStoredProcedureQuery("AOG_MESSG.AOG_PKG_GPS_PROVDR.AGP_GPS_PROVDR_MOBIL");
        proc.registerStoredProcedureParameter(1, Integer.class, ParameterMode.IN); //responseId
        proc.registerStoredProcedureParameter(2, String.class, ParameterMode.IN); //eventType
        proc.registerStoredProcedureParameter(3, Date.class, ParameterMode.IN);//eventTime
        proc.registerStoredProcedureParameter(4, String.class, ParameterMode.IN);//lat
        proc.registerStoredProcedureParameter(5, String.class, ParameterMode.IN);//long
        proc.registerStoredProcedureParameter(6, Integer.class, ParameterMode.IN);//eta
        proc.registerStoredProcedureParameter(7, Date.class, ParameterMode.IN);//sentTIme
        proc.registerStoredProcedureParameter(8, String.class, ParameterMode.IN);//channelId
        proc.registerStoredProcedureParameter(9, Long.class, ParameterMode.IN);//driverId
        proc.registerStoredProcedureParameter(10, Long.class, ParameterMode.IN);//transId
        proc.registerStoredProcedureParameter(11, Integer.class, ParameterMode.IN);//appId
        proc.registerStoredProcedureParameter(12, String.class, ParameterMode.IN);//gpsAccuracy
        proc.registerStoredProcedureParameter(13, String.class, ParameterMode.IN);//jobList
        proc.registerStoredProcedureParameter(14, String.class, ParameterMode.OUT);//providerId
        proc.registerStoredProcedureParameter(15, String.class, ParameterMode.OUT);//jobStatus
        proc.registerStoredProcedureParameter(16, String.class, ParameterMode.OUT);//usageId
        proc.registerStoredProcedureParameter(17, Integer.class, ParameterMode.OUT);//result
        proc.registerStoredProcedureParameter(18, String.class, ParameterMode.OUT);//error

        proc.setParameter(1, responseId);
        proc.setParameter(2, eventType);
        proc.setParameter(3, eventTime);
        proc.setParameter(4, latitude);
        proc.setParameter(5, longitude);
        proc.setParameter(6, eta);
        proc.setParameter(7, sentTime);
        proc.setParameter(8, channelId);
        proc.setParameter(9, driverId);
        proc.setParameter(10, transId);
        proc.setParameter(11, appId);
        proc.setParameter(12, gpsAccuracy);
        proc.setParameter(13, jobList);

        proc.execute();

        return AogGpsProviderResult.builder()
                .usageId((String) proc.getOutputParameterValue(16))
                .providerId((String) proc.getOutputParameterValue(14))
                .jobStatus((String) proc.getOutputParameterValue(15))
                .result((Integer) proc.getOutputParameterValue(17))
                .error((String) proc.getOutputParameterValue(18))
                .build();
    }

    public AogGpsProviderResult gpsProviderProLink(Integer responseId, String usageId, String eventType,
                                                   Date eventTime, String latitude, String longitude,
                                                   Integer eta, String eventChannelInd, Integer lastUpdInterval,
                                                   Date sentTime, String authCd, String channelId, String partnerId,
                                                   String providerId, Long driverId, Long transId, Integer appId) {

        StoredProcedureQuery proc = aogEntityManager.createStoredProcedureQuery("AOG_MESSG.AOG_PKG_GPS_PROVDR.AGP_GPS_PROVDR_PROLNK");
        proc.registerStoredProcedureParameter("P_RESPONSE_ID", Integer.class, ParameterMode.IN);//responseId
        proc.registerStoredProcedureParameter("P_USAGE_ID", String.class, ParameterMode.INOUT);//usageId
        proc.registerStoredProcedureParameter("P_EVENT_TYPE", String.class, ParameterMode.IN);//eventType
        proc.registerStoredProcedureParameter("P_EVENT_TIME", Date.class, ParameterMode.IN);//eventTime
        proc.registerStoredProcedureParameter("P_LATITUDE", String.class, ParameterMode.IN);//lat
        proc.registerStoredProcedureParameter("P_LOGITUDE", String.class, ParameterMode.IN);//long
        proc.registerStoredProcedureParameter("P_ETA", Integer.class, ParameterMode.IN);//eta
        proc.registerStoredProcedureParameter("P_EVENT_CHANNEL_IND", String.class, ParameterMode.IN);//eventChannelId
        proc.registerStoredProcedureParameter("P_LAST_UPD_INTERVAL", Integer.class, ParameterMode.IN);//lastUpdInterval
        proc.registerStoredProcedureParameter("P_SENT_TIME", Date.class, ParameterMode.IN);//sentTime
        proc.registerStoredProcedureParameter("P_AUTH_CD", String.class, ParameterMode.IN);//authCd
        proc.registerStoredProcedureParameter("P_CHANNEL_ID", String.class, ParameterMode.IN);//channelId
        proc.registerStoredProcedureParameter("P_PARTNER_ID", String.class, ParameterMode.IN);//partnerId
        proc.registerStoredProcedureParameter("P_PROVIDER_ID", String.class, ParameterMode.INOUT);//providerId
        proc.registerStoredProcedureParameter("P_DRIVER_ID", Long.class, ParameterMode.IN);//driverId
        proc.registerStoredProcedureParameter("P_TRANS_ID", Long.class, ParameterMode.IN);//transId
        proc.registerStoredProcedureParameter("P_APP_ID", Integer.class, ParameterMode.IN);//appId
        proc.registerStoredProcedureParameter("P_JOB_STATUS", String.class, ParameterMode.OUT);//jobStatus
        proc.registerStoredProcedureParameter("P_RESULT", Integer.class, ParameterMode.OUT);//result
        proc.registerStoredProcedureParameter("P_ERROR", String.class, ParameterMode.OUT);//error

        proc.setParameter("P_RESPONSE_ID", responseId);
        proc.setParameter("P_USAGE_ID", usageId);
        proc.setParameter("P_EVENT_TYPE", eventType);
        proc.setParameter("P_EVENT_TIME", eventTime);
        proc.setParameter("P_LATITUDE", latitude);
        proc.setParameter("P_LOGITUDE", longitude);
        proc.setParameter("P_ETA", eta);
        proc.setParameter("P_EVENT_CHANNEL_IND", eventChannelInd);
        proc.setParameter("P_LAST_UPD_INTERVAL", lastUpdInterval);
        proc.setParameter("P_SENT_TIME", sentTime);
        proc.setParameter("P_AUTH_CD", authCd);
        proc.setParameter("P_CHANNEL_ID", channelId);
        proc.setParameter("P_PARTNER_ID", partnerId);
        proc.setParameter("P_PROVIDER_ID", providerId);
        proc.setParameter("P_DRIVER_ID", driverId);
        proc.setParameter("P_TRANS_ID", transId);
        proc.setParameter("P_APP_ID", appId);

        proc.execute();

        return AogGpsProviderResult.builder()
                .usageId((String) proc.getOutputParameterValue("P_USAGE_ID"))
                .providerId((String) proc.getOutputParameterValue("P_PROVIDER_ID"))
                .jobStatus((String) proc.getOutputParameterValue("P_JOB_STATUS"))
                .result((Integer) proc.getOutputParameterValue("P_RESULT"))
                .error((String) proc.getOutputParameterValue("P_ERROR"))
                .build();
    }
}

