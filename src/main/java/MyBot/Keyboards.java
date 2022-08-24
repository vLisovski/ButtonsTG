package MyBot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keyboards {

    public static InlineKeyboardMarkup getStartMenu(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText("Картинки");
        button.setCallbackData("images");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Документ из папки");
        button.setCallbackData("document_from_folder");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Гиффка");
        button.setCallbackData("gif");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();

        button.setText("Смайл мод");
        button.setCallbackData("smile_mod");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Музыка");
        button.setCallbackData("mp3_audio");
        row.add(button);

        keyboard.add(row);

        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText("Найти количество букв в тексте");
        button.setCallbackData("letters");
        row.add(button);

        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }

    public static InlineKeyboardMarkup getImagesMenu(){
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row = new ArrayList<>();

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("Рандомная картинка");
        button.setCallbackData("random_pic");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("Картинка из папки проекта");
        button.setCallbackData("pic_from_folder");
        row.add(button);

        button = new InlineKeyboardButton();
        button.setText("В главное меню");
        button.setCallbackData("start_menu");
        row.add(button);

        keyboard.add(row);

        InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);

        return replyKeyboardMarkup;
    }
}
