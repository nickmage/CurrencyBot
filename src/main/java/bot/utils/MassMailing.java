package bot.utils;

import bot.Bot;

public class MassMailing extends Thread {

    @Override
    public void run() {
        Bot bot = new Bot();
        for (int i = 0; i < 25; i++) {
            bot.sendResponse("-330345140", "Running " + i);//"431215789");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}