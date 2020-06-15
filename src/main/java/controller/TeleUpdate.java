package controller;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.objects.Audio;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.Voice;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.sound.sampled.*;
import java.io.*;
import java.util.Date;


public class TeleUpdate {
    public static void runUpdate(Update update) throws TelegramApiException, IOException {
        Bot bot = new Bot();
        Message message = update.getMessage();
        System.out.println(message.toString());


        if (message.getVoice() != null) {
            Voice audio = message.getVoice();
            String fileId = message.getVoice().getFileId();
            String response1 = getFilePath("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", fileId);
            String parsed = parserSite(response1);
            String filePath = downloadFile("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", parsed);

            sendToVoiceApi(filePath);
        }

        System.out.println("has voice");
    }
//        if(message != null && message.hasText()){
//            switch (message.getText()) {
//                case "/top10" :
//                    bot.sendMsg(message, "вывожу статистику:");
//                    break;
//                case "/kyrgyzstan" :
//                    bot.sendMsg(message, "Статистика по кыргызстану:");
//                    break;
//                case "/start" :
//                    bot.sendMsg(message, "Здравствуйте");
//                default:
//            }
//            if (message.getText().contains("пидр")){
//                bot.sendMsg(message, "Сам(а) ты пидр");
//            }
//        }
//        System.out.println(message.getText());



    public static void sendToVoiceApi(Object file) throws IOException {
        HttpResponse response = Unirest.post("https://api.wit.ai/speech")
                .header("Content-Type", "audio/mpeg3")
                .header("Authorization", "Bearer ORNQQ25XWFYH2LIV7BKMK5ZGDGWNIPLR")
                .field("audio", file)
                .asJson();

        System.out.println(response.getBody() + "\n" + response.getStatus() + "\n" + response.getCookies()
                + "\n" + response.getHeaders());
    }

    public static String getFilePath(String token, String fileId){
        HttpResponse response = Unirest.get("https://api.telegram.org/bot" + token + "/getFile?file_id=" + fileId)
                .header("cache-control", "no-cache")
                .asJson();
        return response.getBody().toString();
    }

    public static String downloadFile(String token, String webPath){
        String pathName = "C:\\Users\\Азат\\Desktop\\audio\\"+ System.currentTimeMillis() +".mpeg";
        HttpResponse response = Unirest.get("https://api.telegram.org/file/bot" + token + "/" + webPath)
                .header("cache-control", "no-cache")
                .asFile(pathName);
        return pathName;
    }

    public static String parserSite(String str){
        str = str.replace("\"file_path\":\"", "①");
        int index = str.indexOf("①");
        str = str.substring(index + 1, str.length()-3);
        return str;
    }
}
