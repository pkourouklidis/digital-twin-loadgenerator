/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.api;

import com.bt.betalab.callcentre.loadgenerator.config.Config;

import java.util.Random;

public class Customer {

    private boolean isHappyToWait;
    private boolean isHappyToWaitForService;
    private boolean isUnderstanding;

    public Customer() {
        Random rand = new Random();
        isHappyToWait = rand.nextInt(100) > Config.getWaitTimeBias();
        isHappyToWaitForService = rand.nextInt(100) > Config.getServiceTimeBias();
        isUnderstanding = rand.nextInt(100) > Config.getUnderstandingBias();
    }

    public boolean isHappyToWait() {
        return isHappyToWait;
    }

    public boolean isHappyToWaitForService() {
        return isHappyToWaitForService;
    }

    public boolean isUnderstanding() {
        return isUnderstanding;
    }
}
