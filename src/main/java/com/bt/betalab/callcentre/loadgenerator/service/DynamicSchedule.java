/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.service;

public abstract class DynamicSchedule{
    /**
     * Delays scheduler
     * @param milliseconds - the time to delay scheduler.
     */
    abstract void delay(Long milliseconds);

    /**
     * Decreases delay period
     * @param milliseconds - the time to decrease delay period.
     */
    abstract void decreaseDelayInterval(Long milliseconds);

    /**
     * Increases delay period
     * @param milliseconds - the time to increase dela period
     */
    abstract void increaseDelayInterval(Long milliseconds);
}
