package MyBot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;


public class TgBotManager extends TelegramLongPollingBot {

    private final static HashMap<String, ReplyKeyboardMarkup> KEYBOARD_MAP = new HashMap<>();
    private final static HashMap<String, String> BACK_BUTTON_MAP = new HashMap<>();

    public static void initializeKeyboardMap() {
        //region Начало
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Опции");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Состояние устройства");
        keyboard.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Начало", replyKeyboardMarkup);
//endregion
        //region Опции
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Настроить освещение");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Соединить ленту с другим устройством по Wi-Fi");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Синхронизировать ленту с музыкой по Bluetooth");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Начало");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Опции", replyKeyboardMarkup);

        //region Настроить освещение
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Изменить цвет");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Задать набор цветов");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Выбрать анимацию ленты");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Начало");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Настроить освещение", replyKeyboardMarkup);
        //endregion
        //region Соединить ленту с другим устройством по Wi-Fi
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Подключиться");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Начало");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Соединить ленту с другим устройством по Wi-Fi", replyKeyboardMarkup);
        //endregion
        //region Синхронизировать ленту с музыкой по Bluetooth
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Синхронизировать");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Начало");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Синхронизировать ленту с музыкой по Bluetooth", replyKeyboardMarkup);
        //endregion

        //endregion
        //region Состояние устройства
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("Посмотреть уровень заряда батареи");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Посмотреть качество Bluetooth соединения");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Посмотреть качество Wi-Fi соединения");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Назад");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Начало");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("Состояние устройства", replyKeyboardMarkup);
        //endregion

    }
    public static void initializeBackButtonMap(){

        BACK_BUTTON_MAP.put("Опции","Начало");
        BACK_BUTTON_MAP.put("Состояние устройства","Начало");

        BACK_BUTTON_MAP.put("Настроить освещение","Опции");
        BACK_BUTTON_MAP.put("Соединить ленту с другим устройством по Wi-Fi","Опции");
        BACK_BUTTON_MAP.put("Синхронизировать ленту с музыкой по Bluetooth","Опции");

        BACK_BUTTON_MAP.put("Изменить цвет","Опции");
        BACK_BUTTON_MAP.put("Задать набор цветов","Опции");
        BACK_BUTTON_MAP.put("Выбрать анимацию ленты","Опции");

        BACK_BUTTON_MAP.put("Подключиться","Опции");
        BACK_BUTTON_MAP.put("Синхронизировать","Опции");

        BACK_BUTTON_MAP.put("Посмотреть качество Wi-Fi соединения","Начало");
        BACK_BUTTON_MAP.put("Посмотреть качество Bluetooth соединения","Начало");
        BACK_BUTTON_MAP.put("Посмотреть уровень заряда батареи","Начало");

    }

    private String makeMenuAction(String keyboardKey) {
        switch (keyboardKey) {
            case "Состояние устройства":

                return "Выберите, что нужно проверить.";
            case "Опции":

                return "Выберите, что нужно сделать:";
            case "Настроить освещение":

                return "Выберите, что нужно настроить:";
            case "Соединить ленту с другим устройством по Wi-Fi":

                return "Ищем устройства...Найден компьютер";
            case "Подключиться":
                Main.connectedByWiFi = true;
                return "Подключаемся к копьютеру...Успешно";
            case "Синхронизировать ленту с музыкой по Bluetooth":

                return "Поиск устройства по Bluetooth...Найдена портативная колонка";
            case "Синхронизировать":
                Main.connectedByBluetooth = true;
                return "Устройства синхронизированы успешно";
            case "Посмотреть уровень заряда батареи":
                return "67%";
            case "Посмотреть качество Bluetooth соединения":
                if (Main.connectedByBluetooth) {
                    return "4 из 5";
                } else return "Лента не подключена по Bluetooth";
            case "Посмотреть качество Wi-Fi соединения":
                if (Main.connectedByWiFi) {
                    return "5 из 5";
                } else return "Лента не подключена по Wi-Fi";
            case "Изменить цвет":
                return "Установлен другой цвет";
            case "Задать набор цветов":
                return "Выбран новый набор цветов";
            case "Выбрать анимацию ленты":
                return "Выбрана новая интересная анимация";
            default:
                return "Вы отправили: " + keyboardKey;
        }
    }

    private void sendMenuMessage(String chatId, String keyboardKey, String lastTappedButton) {
//Назад

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(makeMenuAction(keyboardKey));

            //написано самим
        if(!Objects.equals(keyboardKey,"Назад")){
            message.setReplyMarkup(KEYBOARD_MAP.get(keyboardKey));
        }else{
            message.setReplyMarkup(KEYBOARD_MAP.get(BACK_BUTTON_MAP.get(Main.buttonsStack.pop())));
        }

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

        TgBotManager tgBotManager = new TgBotManager();

        if (update.hasMessage() && update.getMessage().hasText()) {

            String chatId = update.getMessage().getChatId().toString();
            String receivedText = update.getMessage().getText();

             if (!Objects.equals(receivedText, "Назад") && !Objects.equals(receivedText, "Подключиться")
                    && !Objects.equals(receivedText, "Синхронизировать") && !Objects.equals(receivedText, "Изменить цвет")
                    && !Objects.equals(receivedText, "Задать набор цветов")
                    && !Objects.equals(receivedText, "Выбрать анимацию ленты")) {
                 Main.lastTappedButton = receivedText;//это в теории можно убрать
                 Main.buttonsStack.push(Main.lastTappedButton);
             }

            try{
                tgBotManager.sendMenuMessage(chatId, receivedText, Main.lastTappedButton);

            }catch (EmptyStackException e){
                tgBotManager.sendMenuMessage(chatId,"ВЫ ЕЩЕ НИКУДА НЕ НАЖИМАЛИ", "Начало");
            }

            //endregion
        }
    }
}
