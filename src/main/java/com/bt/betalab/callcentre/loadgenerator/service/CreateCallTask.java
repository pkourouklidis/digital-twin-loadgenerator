/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.service;

import com.bt.betalab.callcentre.loadgenerator.api.CallRequest;
import com.bt.betalab.callcentre.loadgenerator.config.Config;
import com.bt.betalab.callcentre.loadgenerator.config.QueueConfig;
import com.bt.betalab.callcentre.loadgenerator.logging.LogLevel;
import com.bt.betalab.callcentre.loadgenerator.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateCallTask {
    private ObjectMapper mapper = new ObjectMapper();

    public void fire(QueueConfig config, Channel channel) {
        if (Config.isOn()) {
            handle(new CallRequest(), config, channel);
        }
    }

    public void handle(CallRequest request, QueueConfig config, Channel channel) {
        try {
            mapper.registerModule(new JavaTimeModule());
            channel.basicPublish("", config.getQueueName(), null, mapper.writeValueAsBytes(request));
        } catch (IOException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
        }
    }

}
