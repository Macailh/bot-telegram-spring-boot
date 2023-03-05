package com.salvadorgerman.bottelegramspringboot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Service
public class TelegramBoot extends TelegramLongPollingBot {
    private final static Logger LOGGER = LoggerFactory.getLogger(TelegramBoot.class);

    @Value("${botToken}")
    private String botToken;

    @Value("${botName}")
    private String botName;

    @Override
    public String getBotUsername() {
        LOGGER.info(botName);
        return botName;
    }

    @Override
    public String getBotToken() {
        LOGGER.info(botName);
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Get the message from the user
        final String messageTextReceived = update.getMessage().getText();
        LOGGER.info("Message received " + messageTextReceived);

        // Get user id
        final long chatId = update.getMessage().getChatId();

        // Create message object
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText("Thanks for writing to us");

        try {
            // Send message
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
