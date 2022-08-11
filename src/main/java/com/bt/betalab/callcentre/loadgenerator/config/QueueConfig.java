/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 29/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="loadgenerator")
public class QueueConfig {
    private String queueAddress;

    private String queuePortString;

    private String queueUser;

    private String queuePassword;

    private String queueName;

    public String getQueueAddress() {
        return queueAddress;
    }

    public String getQueuePortString() {
        return queuePortString;
    }

    public int getQueuePort() {
        try {
            return Integer.valueOf(queuePortString);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getQueueUser() {
        return queueUser;
    }

    public String getQueuePassword() {
        return queuePassword;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueAddress(String queueAddress) {
        this.queueAddress = queueAddress;
    }

    public void setQueuePortString(String queuePortString) {
        this.queuePortString = queuePortString;
    }

    public void setQueueUser(String queueUser) {
        this.queueUser = queueUser;
    }

    public void setQueuePassword(String queuePassword) {
        this.queuePassword = queuePassword;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean queueConfigIsValid() {
        boolean addressOk = queueAddress != null;
        boolean portOk = getQueuePort() != -1;
        boolean userOk = queueUser != null;
        boolean passwordOk = queuePassword != null;
        boolean nameOk = queueName != null;

        return addressOk && portOk && userOk && passwordOk && nameOk;
    }
}
