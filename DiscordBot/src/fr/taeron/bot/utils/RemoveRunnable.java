package fr.taeron.bot.utils;

import net.dv8tion.jda.core.entities.Message;

/**
 * @Author koalaQ_Q !!!!
 **/
public class RemoveRunnable implements  Runnable{

    private long creationTime;
    private long duration;
    private Message message;
    private boolean stop;

    public RemoveRunnable(long duration, Message m){
        this.duration = duration;
        creationTime = System.currentTimeMillis();
        message = m;
        stop = false;
    }

    @Override
    public void run() {
        while(!stop) {
            if (System.currentTimeMillis() - creationTime > duration) {
                message.delete().queue();
                stop = true;
            }
        }
    }
}
