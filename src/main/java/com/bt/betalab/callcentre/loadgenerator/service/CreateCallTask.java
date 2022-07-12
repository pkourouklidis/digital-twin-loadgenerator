/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.service;

import com.bt.betalab.callcentre.loadgenerator.api.CallRequest;
import com.bt.betalab.callcentre.loadgenerator.config.Config;
import com.bt.betalab.callcentre.loadgenerator.logging.LogLevel;
import com.bt.betalab.callcentre.loadgenerator.logging.Logger;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CreateCallTask {

    private ConnectionFactory factory = new ConnectionFactory();
    private ObjectMapper mapper = new ObjectMapper();

    public void fire() {
        if (Config.isOn() && Config.queueConfigIsValid()) {
            handle(new CallRequest());
        }
    }

    public void handle(CallRequest request) {
        try {
            factory.setHost(Config.getQueueAddress());
            factory.setPort(Config.getQueuePort());
            factory.setUsername(Config.getQueueUser());
            factory.setPassword(Config.getQueuePassword());

            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(Config.getQueueName(), false, false, false, null);

            channel.basicPublish("", Config.getQueueName(), null, mapper.writeValueAsBytes(request));

            channel.close();
            connection.close();
        } catch (IOException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
        } catch (TimeoutException e) {
            Logger.log(e.getMessage(), LogLevel.ERROR);
        }
    }

}
