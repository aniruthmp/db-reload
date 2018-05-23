package io.pivotal.dbreload.api;

import io.pivotal.dbreload.domain.AgtMstSource;
import io.pivotal.dbreload.model.AogGpsProviderResult;
import io.pivotal.dbreload.model.Dispatch;
import io.pivotal.dbreload.model.PushGPSEvent;
import io.pivotal.dbreload.repository.AgtMstSourceRepository;
import io.pivotal.dbreload.repository.AogGpsProviderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/demo")
@Slf4j
public class AogController {

    @Autowired
    private AgtMstSourceRepository agtMstSourceRepository;

    @Autowired
    private AogGpsProviderRepository aogGpsProviderRepository;

    @GetMapping(path = "/agts")
    public @ResponseBody
    Iterable<AgtMstSource> getAgtMstSources() {
        log.info("Came inside getAgtMstSources");
        return agtMstSourceRepository.findAll();
    }

    @GetMapping(path = "/agt/{agtSourceId}")
    public @ResponseBody
    AgtMstSource getTopByAgtSourceId(@PathVariable String agtSourceId) {
        log.info("Came inside getTopByAgtSourceId");
        return agtMstSourceRepository.getTopByAgtSourceId(agtSourceId);
    }

    @PostMapping(path = "/proc_1")
    public @ResponseBody
    AogGpsProviderResult testProc_1(@RequestBody PushGPSEvent pushGPSEvent) {
        log.info("Came inside testProc_1");
        String channelId = "CH1008";
        Dispatch dispatch = Dispatch.builder()
                .authorizationNbr("1034029530")
                .partnerCd("PR1011")
                .providerId("TX0200010")
                .responseId(2752095)
                .usageId("0028901741")
                .transId(519267l)
                .build();
        AogGpsProviderResult aogGpsProviderResult = null;
        try {
            aogGpsProviderResult = aogGpsProviderRepository.gpsProviderProLink(
                    dispatch.getResponseId(), dispatch.getUsageId(), pushGPSEvent.getEventType(),
                    pushGPSEvent.getEventTime(),
                    String.valueOf(pushGPSEvent.getLatitude()),
                    String.valueOf(pushGPSEvent.getLongitude()), pushGPSEvent.getEta(), channelId,
                    pushGPSEvent.getLastUpdatedInterval(),
                    pushGPSEvent.getSentTime(),
                    dispatch.getAuthorizationNbr(), channelId,
                    dispatch.getPartnerCd(), dispatch.getProviderId(), dispatch.getDriverId(),
                    dispatch.getTransId(),
                    null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return aogGpsProviderResult;
    }

    @PostMapping(path = "/proc")
    public @ResponseBody
    AogGpsProviderResult testProc(@RequestBody PushGPSEvent pushGPSEvent) {
        log.info("Came inside getTopByAgtSourceId");
        String channelId = "CH1008";
        Dispatch dispatch = Dispatch.builder()
                .driverId(777L)
                .transId(519267L)
                .build();
        String jobList = "2752095~S1028";
        AogGpsProviderResult aogGpsProviderResult = null;
        try {
            aogGpsProviderResult = aogGpsProviderRepository.gpsProvdrMobil(
                    null,
                    null,
                    pushGPSEvent.getEventTime(),
                    String.valueOf(pushGPSEvent.getLatitude()),
                    String.valueOf(pushGPSEvent.getLongitude()),
                    pushGPSEvent.getEta(),
                    null,
                    jobList,
                    pushGPSEvent.getSentTime(),
                    channelId,
                    dispatch.getDriverId(),
                    dispatch.getTransId(),
                    null
            );
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return aogGpsProviderResult;
    }
}
