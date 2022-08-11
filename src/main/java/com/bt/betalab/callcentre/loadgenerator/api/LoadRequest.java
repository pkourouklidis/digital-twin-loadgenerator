/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.api;

public class LoadRequest {
    private boolean on;
    private int callDelay;

    private int difficultyBias;

    private int waitTimeBias;

    private int serviceTimeBias;

    private int understandingBias;

    private String simulationId;

    private String simulationStartTime;

    public boolean getIsOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public long getCallDelay() {
        try {
            return Long.valueOf(callDelay);
        } catch (NumberFormatException e) {
            return 1000;
        }
    }

    public void setCallDelay(int callDelay) {
        this.callDelay = callDelay;
    }

    public long getDifficultyBias() {
        try {
            return Long.valueOf(difficultyBias);
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    public void setDifficultyBias(int difficultyBias) {
        this.difficultyBias = difficultyBias;
    }

    public long getWaitTimeBias() {
        try {
            return Long.valueOf(waitTimeBias);
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    public void setWaitTimeBias(int waitTimeBias) {
        this.waitTimeBias = waitTimeBias;
    }

    public long getServiceTimeBias() {
        try {
            return Long.valueOf(serviceTimeBias);
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    public void setServiceTimeBias(int serviceTimeBias) {
        this.serviceTimeBias = serviceTimeBias;
    }

    public long getUnderstandingBias() {
        try {
            return Long.valueOf(understandingBias);
        } catch (NumberFormatException e) {
            return 100;
        }
    }

    public void setUnderstandingBias(int understandingBias) {
        this.understandingBias = understandingBias;
    }

    public String getSimulationId() {
        return simulationId;
    }

    public void setSimulationId(String simulationId) {
        this.simulationId = simulationId;
    }

    public String getSimulationStartTime() {
        return simulationStartTime;
    }

    public void setSimulationStartTime(String simulationStartTime) {
        this.simulationStartTime = simulationStartTime;
    }
}
