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
        //region ������
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("��������� ����������");
        keyboard.add(row);
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("������", replyKeyboardMarkup);
//endregion
        //region �����
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("��������� ���������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("��������� ����� � ������ ����������� �� Wi-Fi");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("���������������� ����� � ������� �� Bluetooth");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("�����", replyKeyboardMarkup);

        //region ��������� ���������
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("�������� ����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������ ����� ������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������� �������� �����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("��������� ���������", replyKeyboardMarkup);
        //endregion
        //region ��������� ����� � ������ ����������� �� Wi-Fi
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("������������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("��������� ����� � ������ ����������� �� Wi-Fi", replyKeyboardMarkup);
        //endregion
        //region ���������������� ����� � ������� �� Bluetooth
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("����������������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("���������������� ����� � ������� �� Bluetooth", replyKeyboardMarkup);
        //endregion

        //endregion
        //region ��������� ����������
        keyboard = new ArrayList<>();
        row = new KeyboardRow();
        row.add("���������� ������� ������ �������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("���������� �������� Bluetooth ����������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("���������� �������� Wi-Fi ����������");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("�����");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("������");
        keyboard.add(row);
        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        KEYBOARD_MAP.put("��������� ����������", replyKeyboardMarkup);
        //endregion

    }
    public static void initializeBackButtonMap(){

        BACK_BUTTON_MAP.put("�����","������");
        BACK_BUTTON_MAP.put("��������� ����������","������");

        BACK_BUTTON_MAP.put("��������� ���������","�����");
        BACK_BUTTON_MAP.put("��������� ����� � ������ ����������� �� Wi-Fi","�����");
        BACK_BUTTON_MAP.put("���������������� ����� � ������� �� Bluetooth","�����");

        BACK_BUTTON_MAP.put("�������� ����","�����");
        BACK_BUTTON_MAP.put("������ ����� ������","�����");
        BACK_BUTTON_MAP.put("������� �������� �����","�����");

        BACK_BUTTON_MAP.put("������������","�����");
        BACK_BUTTON_MAP.put("����������������","�����");

        BACK_BUTTON_MAP.put("���������� �������� Wi-Fi ����������","������");
        BACK_BUTTON_MAP.put("���������� �������� Bluetooth ����������","������");
        BACK_BUTTON_MAP.put("���������� ������� ������ �������","������");

    }

    private String makeMenuAction(String keyboardKey) {
        switch (keyboardKey) {
            case "��������� ����������":

                return "��������, ��� ����� ���������.";
            case "�����":

                return "��������, ��� ����� �������:";
            case "��������� ���������":

                return "��������, ��� ����� ���������:";
            case "��������� ����� � ������ ����������� �� Wi-Fi":

                return "���� ����������...������ ���������";
            case "������������":
                Main.connectedByWiFi = true;
                return "������������ � ���������...�������";
            case "���������������� ����� � ������� �� Bluetooth":

                return "����� ���������� �� Bluetooth...������� ����������� �������";
            case "����������������":
                Main.connectedByBluetooth = true;
                return "���������� ���������������� �������";
            case "���������� ������� ������ �������":
                return "67%";
            case "���������� �������� Bluetooth ����������":
                if (Main.connectedByBluetooth) {
                    return "4 �� 5";
                } else return "����� �� ���������� �� Bluetooth";
            case "���������� �������� Wi-Fi ����������":
                if (Main.connectedByWiFi) {
                    return "5 �� 5";
                } else return "����� �� ���������� �� Wi-Fi";
            case "�������� ����":
                return "���������� ������ ����";
            case "������ ����� ������":
                return "������ ����� ����� ������";
            case "������� �������� �����":
                return "������� ����� ���������� ��������";
            default:
                return "�� ���������: " + keyboardKey;
        }
    }

    private void sendMenuMessage(String chatId, String keyboardKey, String lastTappedButton) {
//�����

        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(makeMenuAction(keyboardKey));

            //�������� �����
        if(!Objects.equals(keyboardKey,"�����")){
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

             if (!Objects.equals(receivedText, "�����") && !Objects.equals(receivedText, "������������")
                    && !Objects.equals(receivedText, "����������������") && !Objects.equals(receivedText, "�������� ����")
                    && !Objects.equals(receivedText, "������ ����� ������")
                    && !Objects.equals(receivedText, "������� �������� �����")) {
                 Main.lastTappedButton = receivedText;//��� � ������ ����� ������
                 Main.buttonsStack.push(Main.lastTappedButton);
             }

            try{
                tgBotManager.sendMenuMessage(chatId, receivedText, Main.lastTappedButton);

            }catch (EmptyStackException e){
                tgBotManager.sendMenuMessage(chatId,"�� ��� ������ �� ��������", "������");
            }

            //endregion
        }
    }
}
