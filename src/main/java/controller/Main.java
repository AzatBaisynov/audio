package controller;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) throws TelegramApiRequestException {
//        FrameJ frameJ = new FrameJ();
//        frameJ.startJFrame();

//        System.out.println("before init");
        ApiContextInitializer.init();
//        System.out.println("after init");

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        telegramBotsApi.registerBot(new Bot());
    }
}
