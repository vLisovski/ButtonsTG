package MyBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Objects;


public class BotSmesharik extends TelegramLongPollingBot {

    private void sendMenuMessage(String chatId, String receivedText) {
//Назад
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(receivedText);

        try {
            this.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhoto(String chat_id, String f_id,String caption){
        InputFile inputFile = new InputFile(f_id);

        SendPhoto message = new SendPhoto();
        message.setChatId(chat_id);
        message.setPhoto(inputFile);
        message.setCaption(caption);

        try {
            this.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendCreatedPhoto(String chat_id){

        InputFile inputFile = new InputFile("AgACAgIAAxkBAAIE72MDkGv0LmSAiAfpvRAolL8GMP4VAAKUwzEbe7wgSDRrOcUk_T8DAQADAgADeQADKQQ");
        SendPhoto message = new SendPhoto();
        message.setChatId(chat_id);
        message.setPhoto(inputFile);
        message.setCaption("PHOTO:");

        try {
            this.execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getBotUsername() {
        return "javaProgTest2028bot";
    }

    @Override
    public String getBotToken() {
        return "5555881412:AAFn8BugdhYHVVv13qN02BKP5Z7qyds94Eo";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            if(update.getMessage().getText().equals("/pic")){
                String chat_id = update.getMessage().getChatId().toString();
                try {
                    sendCreatedPhoto(chat_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

            try{
                this.sendMenuMessage(chatId, receivedText);

            }catch (Exception e){
                this.sendMenuMessage(chatId,"Бот не смог обработать ваш запрос");
            }

            //endregion
        }else if(update.hasMessage() && update.getMessage().hasPhoto()){
            // Message contains photo
            // Set variables
           String chat_id = update.getMessage().getChatId().toString();

            // Array with photo objects with different sizes
            // We will get the biggest photo from that array
            List<PhotoSize> photos = update.getMessage().getPhoto();
            // Know file_id
            String f_id = Objects.requireNonNull(photos.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getFileId();
            // Know photo width
            int f_width = Objects.requireNonNull(photos.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getWidth();
            // Know photo height
            int f_height = Objects.requireNonNull(photos.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize))
                    .orElse(null)).getHeight();
            // Set photo caption


            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);

            try {
                sendPhoto(chat_id,f_id,caption); // Call method to send the photo with caption
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
