/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.api;

import com.bt.betalab.callcentre.loadgenerator.config.Config;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;

public class CallRequest {
    private Timestamp arrivalTime = new java.sql.Timestamp((new Date()).getTime());

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

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public boolean isEasy() {
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
