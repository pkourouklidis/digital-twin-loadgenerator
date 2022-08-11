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

    private ConnectionFactory factory = new ConnectionFactory();
    private ObjectMapper mapper = new ObjectMapper();

    public void fire(QueueConfig config) {
        if (Config.isOn() && config.queueConfigIsValid()) {
            handle(new CallRequest(), config);
        }
    }

    public void handle(CallRequest request, QueueConfig config) {
        try {
            mapper.registerModule(new JavaTimeModule());
            factory.setHost(config.getQueueAddress());
            factory.setPort(config.getQueuePort());
            factory.setUsername(config.getQueueUser());
            factory.setPassword(config.getQueuePassword());

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(config.getQueueName(), true, false, false, null);

            channel.basicPublish("", config.getQueueName(), null, mapper.writeValueAsBytes(request));

            channel.close();
            connection.close();
        } catch (IOException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
        } catch (TimeoutException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
        }
    }

}
