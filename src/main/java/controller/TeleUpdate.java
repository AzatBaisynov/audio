package controller;
import javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.Voice;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import javax.sound.sampled.*;
import java.io.*;

public class TeleUpdate {
    public static void runUpdate(Update update) throws TelegramApiException, IOException, UnsupportedAudioFileException {
        Bot bot = new Bot();
        Message message = update.getMessage();

        if (message.getVoice() != null) {
            Voice audio = message.getVoice();

            String fileId = message.getVoice().getFileId();
            String response1 = getFilePath("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", fileId);

            String parsed = parserSite(response1);
            String filePath = downloadFile("1099581804:AAHh4rvoqN93b26IMGSg0ihb5uoTRNxu9n4", parsed);

            String output = filePath.replace(".oga", ".ogg");
            AudioDecoderFFjpeg.decode(filePath,output);
            String text = sendToVoiceApi(new File(output));
            text = text.replace("{\"entities\":{},\"intents\":[],\"text\":\"","");
            text = text.replace("\",\"traits\":{}}", "");
            System.out.println(text);
            if(text.contains("привет")){
                bot.sendMsg(message, "приветик");
            }
            File file1 = new File(filePath);
            file1.delete();
            File file2 = new File(output);
            file2.delete();

        }
    }

    public static String sendToVoiceApi(Object file) throws IOException {
        HttpResponse response = Unirest.post("https://api.wit.ai/speech")
                .header("Content-Type", "audio/ogg")
                .header("Authorization", "Bearer ORNQQ25XWFYH2LIV7BKMK5ZGDGWNIPLR")
                .field("audio", file)
                .asJson();
        return response.getBody().toString();
    }

    public static String getFilePath(String token, String fileId){
        HttpResponse response = Unirest.get("https://api.telegram.org/bot" + token + "/getFile?file_id=" + fileId)
                .header("cache-control", "no-cache")
                .asJson();
        return response.getBody().toString();
    }

    public static String downloadFile(String token, String webPath){
        String pathName = "C:\\Users\\Азат\\Desktop\\audio\\"+ System.currentTimeMillis() + ".oga";
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
