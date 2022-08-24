package MyBot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MaximumInlineBot extends TelegramLongPollingBot {

    Photos photos = new Photos();

    public MaximumInlineBot() throws IOException {
    }

    private void sendMessage(String chatId, String text) {

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private int countLetters(String text, char letter) {
        int counter = 0;
        char[] letters = text.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == letter) {
                counter++;
            }
        }
        return counter;
    }

    private void sendLettersCount(String chat_id, String text, char letter) {
        sendMessage(chat_id, Integer.toString(countLetters(text, letter)));
    }

    private void send_mp3AudioFileFromFolder(String chat_id, String fileName) {
        SendAudio audio;
        audio = new SendAudio(chat_id, new InputFile(new File(fileName)));
        audio.setCaption(fileName);
        try {
            execute(audio);
        } catch (TelegramApiException e) {
            e.printStackTrace();

        }
    }

    private void sendFileFromFolder(String chat_id, String fileName) {

        SendDocument document = new SendDocument(chat_id, new InputFile(new File(fileName)));
        try {
            execute(document);
        } catch (TelegramApiException e) {
            e.printStackTrace();
            sendMessage(chat_id, "Файл с таким именем не найден.");
        }
    }

    private void sendGif(String chat_id, String fileName) {
        SendAnimation gif = null;
            gif = new SendAnimation();
            gif.setAnimation(new InputFile(new File(fileName)));
            gif.setChatId(chat_id);
            gif.setCaption(fileName);

        try {
            execute(gif);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void sendSmile(String chat_id, String smileName) {

        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        String smile = EmojiParser.parseToUnicode(":" + smileName + ":");
        message.setText(smile);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendPhotoBackWithId(String chat_id, String f_id, String caption) throws IOException {

        InputFile inputFile = new InputFile(f_id);
        SendPhoto message = new SendPhoto();
        message.setChatId(chat_id);
        message.setPhoto(inputFile);
        message.setCaption(caption);
        photos.addPhotoId(f_id);

        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendRandomPhoto(String chat_id, String caption) {

        InputFile inputFile = new InputFile(photos.getRandomPhotoId());
        SendPhoto message = new SendPhoto();
        message.setChatId(chat_id);
        message.setPhoto(inputFile);
        message.setCaption(caption);

        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private boolean smileNameScan(String text) throws IOException {

        List<String> smiles = new ArrayList<>();
        FileReader fileReader = new FileReader("smiles.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while (bufferedReader.readLine() != null) {
            smiles.add(bufferedReader.readLine());
        }

        return smiles.contains(text);
    }

    private void sendPhotoFromProjectFolder(String chat_id, String caption, String fileName) {

        InputFile inputFile = new InputFile(new File(fileName));
        SendPhoto message = new SendPhoto();
        message.setChatId(chat_id);
        message.setPhoto(inputFile);
        message.setCaption(caption);

        try {
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }
    private void sendStartKeyboard(String chat_id){
        InlineKeyboardMarkup inlineKeyboardMarkup = Keyboards.getStartMenu();
        SendMessage message = new SendMessage();
        message.setChatId(chat_id);
        message.setText("Главное меню:");
        message.setReplyMarkup(inlineKeyboardMarkup);
        try {
            execute(message);
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

            if (update.getMessage().getText().equals("/randomPic")) {
                String chat_id = update.getMessage().getChatId().toString();
                try {
                    sendRandomPhoto(chat_id, "RANDOM PHOTO:");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (update.getMessage().getText().equals("/folderPic")) {
                String chatId = update.getMessage().getChatId().toString();
                sendPhotoFromProjectFolder(chatId, "PHOTO:", "Chelik.jpg");
            } else if (update.getMessage().getText().equals("/start")) {
               sendStartKeyboard(update.getMessage().getChatId().toString());
            } else if (update.getMessage().getText().contains(".txt")) {
                String chatId = update.getMessage().getChatId().toString();
                sendFileFromFolder(chatId, update.getMessage().getText());
                sendStartKeyboard(chatId);
            } else if (update.getMessage().getText().contains(".mp3")) {
                String chatId = update.getMessage().getChatId().toString();
                send_mp3AudioFileFromFolder(chatId, update.getMessage().getText());
                sendStartKeyboard(chatId);
            } else if (update.getMessage().getText().contains(".jpg")) {
                String chatId = update.getMessage().getChatId().toString();
                sendPhotoFromProjectFolder(chatId, update.getMessage().getText(), update.getMessage().getText());
                sendStartKeyboard(chatId);
            } else if (update.getMessage().getText().contains(".gif")) {
                String chatId = update.getMessage().getChatId().toString();
                sendGif(chatId, update.getMessage().getText());
                sendStartKeyboard(chatId);
            } else {
                if (update.getMessage().getText().length() > 1) {
                    try {
                        if (!smileNameScan(update.getMessage().getText())) {
                            Main.scanText = update.getMessage().getText();
                            sendMessage(update.getMessage().getChatId().toString(), "Введите букву:");
                        } else {
                            sendSmile(update.getMessage().getChatId().toString(), update.getMessage().getText());
                            sendStartKeyboard(update.getMessage().getChatId().toString());
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    char letter = update.getMessage().getText().charAt(0);
                    sendLettersCount(update.getMessage().getChatId().toString(), Main.scanText, letter);
                }
            }

            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

            try {
                this.sendMessage(chatId, receivedText);

            } catch (Exception e) {
                this.sendMessage(chatId, "Бот не смог обработать ваш запрос");
            }

            //endregion
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {

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
                sendPhotoBackWithId(chat_id, f_id, caption); // Call method to send the photo with caption
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (update.hasCallbackQuery()) {

            String callbackData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();

            InlineKeyboardMarkup inlineKeyboardMarkup = null;
            String text = "Перешёл успешно";

            switch (callbackData) {
                case "images": {
                    text = "Меню картинки:";
                    inlineKeyboardMarkup = Keyboards.getImagesMenu();
                }
                break;
                case "start_menu": {
                    text = "Главное меню:";
                    inlineKeyboardMarkup = Keyboards.getStartMenu();
                }
                break;
                case "document_from_folder": {
                    sendMessage(chatId, "Введите имя файла с расширением.");
                }
                break;
                case "gif": {
                    sendMessage(chatId, "Введите имя файла с gif расширением.");
                }
                break;
                case "smile_mod": {
                    sendMessage(chatId, "Введите имя смайла.");
                }
                break;
                case "mp3_audio": {
                    sendMessage(chatId, "Введите имя файла с музыкой в формате \"автор - название.mp3\".");
                }
                break;
                case "letters": {
                    sendMessage(chatId, "Введите текст, в котором нужно подсчитать количество букв:");
                }
                break;
                case "random_pic": {
                    sendRandomPhoto(chatId, "Рандомное фото:");
                }
                break;
                case "pic_from_folder": {
                    sendMessage(chatId, "Введите имя файла с расширением:");
                }
                break;
            }

            EditMessageText message = new EditMessageText();
            message.setChatId(chatId);
            message.setMessageId(messageId);
            message.setText(text);
            message.setReplyMarkup(inlineKeyboardMarkup);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

        }
    }
}
