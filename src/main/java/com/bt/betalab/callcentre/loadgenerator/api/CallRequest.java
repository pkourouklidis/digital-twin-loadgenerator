/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.api;

import com.bt.betalab.callcentre.loadgenerator.config.Config;

import java.time.Instant;
import java.util.Random;

public class CallRequest {
    private Instant arrivalTime = Instant.now();

    private boolean isEasy;
    private Customer customer = new Customer();
    private String simulationId;
    private String simulationStartTime;

    public CallRequest() {
        simulationId = Config.getSimulationId();
        simulationStartTime = Config.getSimulationStartTime();
        Random rand = new Random();
        isEasy = rand.nextInt(100) > Config.getDifficultyBias();
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    public boolean getIsEasy() {
        return isEasy;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public String getSimulationStartTime() {
        return simulationStartTime;
    }
}
