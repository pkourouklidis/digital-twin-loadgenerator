/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value(value = "${loadgenerator.queueAddress}")
    private static String queueAddress;

    @Value(value = "${loadgenerator.queuePort}")
    private static String queuePortString;

    @Value(value = "${loadgenerator.queueUser}")
    private static String queueUser;

    @Value(value = "${loadgenerator.queuePassword}")
    private static String queuePassword;

    @Value(value = "${loadgenerator.queueName}")
    private static String queueName;

    private static long callDelay = 1000;

    private static long difficultyBias = 50;
    private static long waitTimeBias = 50;
    private static long serviceTimeBias = 50;
    private static long understandingBias = 50;

    private static String simulationId;

    private static String simulationStartTime;

    private static boolean on = false;

    public static String getQueueAddress() {
        return queueAddress;
    }

    public static String getQueuePortString() {
        return queuePortString;
    }

    public static int getQueuePort() {
        try {
            return Integer.valueOf(queuePortString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String getQueueUser() {
        return queueUser;
    }

    public static String getQueuePassword() {
        return queuePassword;
    }

    public static String getQueueName() {
        return queueName;
    }

    public static long getCallDelay() {
        return callDelay;
    }

    public static void setCallDelay(long delay) {
        callDelay = callDelay;
    }

    public static long getDifficultyBias() {
        return difficultyBias;
    }

    public static void setDifficultyBias(long difficultyBias) {
        Config.difficultyBias = difficultyBias;
    }

    public static long getWaitTimeBias() {
        return waitTimeBias;
    }

    public static void setWaitTimeBias(long waitTimeBias) {
        Config.waitTimeBias = waitTimeBias;
    }

    public static long getServiceTimeBias() {
        return serviceTimeBias;
    }

    public static void setServiceTimeBias(long serviceTimeBias) {
        Config.serviceTimeBias = serviceTimeBias;
    }

    public static long getUnderstandingBias() {
        return understandingBias;
    }

    public static void setUnderstandingBias(long understandingBias) {
        Config.understandingBias = understandingBias;
    }

    public static boolean isOn() {
        return on;
    }

    public static void setOn(boolean switchOn) {
        on = switchOn;
    }

    public static String getSimulationId() {
        return simulationId;
    }

    public static void setSimulationId(String id) {
        simulationId = id;
    }

    public static String getSimulationStartTime() {
        return simulationStartTime;
    }

    public static void setSimulationStartTime(String startTime) {
        simulationStartTime = startTime;
    }

    public static boolean queueConfigIsValid() {
        boolean addressOk = queueAddress != null;
        boolean portOk = getQueuePort() != -1;
        boolean userOk = queueUser != null;
        boolean passwordOk = queuePassword != null;
        boolean nameOk = queueName != null;

        return addressOk && portOk && userOk && passwordOk && nameOk;
    }
}
