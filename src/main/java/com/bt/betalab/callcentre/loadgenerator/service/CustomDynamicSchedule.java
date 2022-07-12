/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.service;

import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

import java.util.Date;

public class CustomDynamicSchedule extends DynamicSchedule implements Trigger {

    private long delayInterval;

    @Override
    public synchronized void increaseDelayInterval(Long delay) {
        this.delayInterval += delay;
    }

    @Override
    public synchronized void decreaseDelayInterval(Long delay) {
        this.delayInterval += delay;
    }

    @Override
    public synchronized void delay(Long delay) {
        this.delayInterval = delay;
    }

    @Override
    public Date nextExecutionTime(TriggerContext triggerContext) {
        Date lastTime = triggerContext.lastActualExecutionTime();
        return (lastTime == null) ? new Date() : new Date(lastTime.getTime() + delayInterval);
    }
}
