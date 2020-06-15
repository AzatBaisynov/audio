package controller;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

//import static controller.TeleButtons.setButtons;
import java.io.IOException;

import static controller.TeleUpdate.runUpdate;


public class Bot extends TelegramLongPollingBot {

    public  void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
//            setButtons(sendMessage);
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void onUpdateReceived(Update update) {
        try {
            runUpdate(update);
        } catch (TelegramApiException | IOException e) {
            e.printStackTrace();
        }
    }



    public String getBotUsername() {
        return "Easy Storage";
    }

    public String getBotToken() {
        return "1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4";
    }
}
