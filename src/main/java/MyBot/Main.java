package MyBot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import java.util.Stack;

public class Main {

    public static Stack<String> buttonsStack;
    public static boolean connectedByBluetooth = false;
    public static boolean connectedByWiFi = false;

    public static String lastTappedButton;


    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TgBotManager());
            buttonsStack = new Stack<>();
            TgBotManager.initializeKeyboardMap();
            TgBotManager.initializeBackButtonMap();
            System.out.println("bot started");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
