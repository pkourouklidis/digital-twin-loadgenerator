/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.service;

import com.bt.betalab.callcentre.loadgenerator.api.LoadRequest;
import com.bt.betalab.callcentre.loadgenerator.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class LoadGeneratorService {
    @Autowired
    private CustomDynamicSchedule dynamicSchedule;

    @Autowired
    private Config config;

    @Scheduled(fixedDelay = 1000)
    private void process() {
        dynamicSchedule.delay(config.getCallDelay());
    }

    public void setupLoad(LoadRequest load) {
        config.setOn(false);
        config.setCallDelay(1000);

        config.setCallDelay(load.getCallDelay());
        config.setDifficultyBias(load.getDifficultyBias());
        config.setWaitTimeBias(load.getWaitTimeBias());
        config.setServiceTimeBias(load.getServiceTimeBias());
        config.setUnderstandingBias(load.getUnderstandingBias());
        config.setOn(load.isOn());
        config.setSimulationId(load.getSimulationId());
        config.setSimulationStartTime(load.getSimulationStartTime());
    }
}
