package fr.taeron.bot.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;

public class Utils {

    public static InputStream imageFromUrl(String url) {
        if (url == null)
            return null;
        try {
            URL u = new URL(url);
            URLConnection urlConnection = u.openConnection();
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.112 Safari/537.36");
            return urlConnection.getInputStream();
        } catch (IOException | IllegalArgumentException e) {
        }
        return null;
    }

    public static int randInt(int min, int max) {

        // NOTE: This will (intentionally) not run as written so that folks
        // copy-pasting have to think about how to initialize their
        // Random instance.  Initialization of the Random instance is outside
        // the main scope of the question, but some decent options are to have
        // a field that is initialized once and then re-used as needed or to
        // use ThreadLocalRandom (if using at least Java 1.7).
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void delayedRemove(Message m, long delay) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            new RemoveRunnable(delay, m).run();
        });
        if(executor.isTerminated()){
            executor.shutdownNow();
        }
    }

    public static void sendFileAsync(TextChannel channel, InputStream input, String fileName, Message message){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            channel.sendFile(input, fileName, message).queue();
        });
        if(executor.isTerminated()){
            executor.shutdownNow();
        }
    }
}
