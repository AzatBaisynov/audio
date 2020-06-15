//package controller;
//
//import org.telegram.telegrambots.api.methods.send.SendMessage;
//import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
//import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
//import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TeleButtons {
//    static void setButtons(SendMessage sendMessage){
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//        List<KeyboardRow> keyboardRowList = new ArrayList<>();
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//
//        keyboardFirstRow.add(new KeyboardButton("/top10"));
//        keyboardFirstRow.add(new KeyboardButton("/kyrgyzstan"));
//
//        keyboardRowList.add(keyboardFirstRow);
//        replyKeyboardMarkup.setKeyboard(keyboardRowList);
//    }
//}
