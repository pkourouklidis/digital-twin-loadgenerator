/**
 * @author Joost Noppen (611749237), BetaLab, Applied Research
 * Date: 04/07/2022
 * Copyright (c) British Telecommunications plc 2022
 **/


package com.bt.betalab.callcentre.loadgenerator.config;

import com.bt.betalab.callcentre.loadgenerator.logging.LogLevel;
import com.bt.betalab.callcentre.loadgenerator.logging.Logger;
import com.bt.betalab.callcentre.loadgenerator.service.CreateCallTask;
import com.bt.betalab.callcentre.loadgenerator.service.CustomDynamicSchedule;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import javax.annotation.PostConstruct;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
@EnableScheduling
public class DynamicSchedulingConfig implements SchedulingConfigurer {

    @Autowired
    QueueConfig config;

    private com.rabbitmq.client.Channel channel;

    @PostConstruct
    public void initialiser() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(config.getQueueAddress());
        factory.setPort(config.getQueuePort());
        factory.setUsername(config.getQueueUser());
        factory.setPassword(config.getQueuePassword());

        Connection connection = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(config.getQueueName(), true, false, false, null);
            Logger.log("Connected to message queue", LogLevel.INFO);
        } catch (Exception e) {
            Logger.log("Could not connect to message queue: " + e.getMessage(), LogLevel.ERROR);
        }
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        if (channel != null) {
            taskRegistrar.setScheduler(taskScheduler());
            taskRegistrar.addTriggerTask(() -> myTask().fire(config, channel), myTrigger());
        } else {
            Logger.log("No valid message queue could be connected to", LogLevel.ERROR);
        }
    }

    @Bean(destroyMethod="shutdown")
    public Executor taskScheduler() {
        return Executors.newScheduledThreadPool(42);
    }

    @Bean
    public CustomDynamicSchedule myTrigger() {
        return new CustomDynamicSchedule();
    }

    @Bean
    public CreateCallTask myTask() {
        return new CreateCallTask();
    }

}
