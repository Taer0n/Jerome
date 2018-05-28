package fr.taeron.bot.anticheat;

import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.user.UserTypingEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author koalaQ_Q !!!!
 **/
public class PokecordAntiBot  extends ListenerAdapter {

    private List<String> typingUsers;
    private List<String> lastMessageWasCatchUsers;
    private HashMap<String, Integer> violationsMap;
    private boolean isPokemonSpawned;
    private long pokemonSpawnTimestamp;

    public PokecordAntiBot(){
        typingUsers = new ArrayList<>();
        violationsMap = new HashMap<>();
        lastMessageWasCatchUsers = new ArrayList<>();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        //Handling pokemon spawns
        if(!event.getMessage().getEmbeds().isEmpty()){
            MessageEmbed msg = event.getMessage().getEmbeds().stream().filter(embed -> embed.getTitle().equalsIgnoreCase("A wild pokémon has appeared!")).findFirst().orElse(null);
            if(msg != null){
                isPokemonSpawned = true;
                pokemonSpawnTimestamp = System.currentTimeMillis();
                System.out.println("[AntiBot] Pokemon spawned.");
            }
        }

        if(event.getMessage().getContentDisplay().startsWith("Congratulations ") && event.getAuthor().isBot()){
            isPokemonSpawned = false;
            System.out.println("[AntiBot] Pokemon caught.");
        }

        //Check #1: sending a message without typing
        if(!this.typingUsers.contains(event.getAuthor().getId()) && event.getMessage().getContentDisplay().startsWith("p!catch")){
            violationsMap.put(event.getAuthor().getId(), violationsMap.getOrDefault(event.getAuthor().getId(), 0) + 1);
            if(violationsMap.get(event.getAuthor().getId()) > 4){
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " was kicked for using a pokemon catcher bot").queue();
                event.getGuild().getController().kick(event.getMember(), "pokemon catcher bot").queue();
                System.out.println("[AntiBot] " + event.getAuthor().getName() + " was kicked for using a pokemon catcher bot");
            }
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " is using a pokemon catcher bot. Violations: " + violationsMap.get(event.getAuthor().getId())).queue();
            System.out.println("[AntiBot] " + event.getAuthor().getName() + " is using a pokemon catcher bot. Violations: " + violationsMap.get(event.getAuthor().getId()));

        }
        else if (event.getMessage().getContentDisplay().startsWith("p!catch")){
            violationsMap.put(event.getAuthor().getId(), 0);
        }
        this.typingUsers.remove(event.getAuthor().getId());

        //Check #2: answering quickly and never saying anything else than p!catch
        if(!event.getMessage().getContentDisplay().startsWith("p!catch")) {
            if(lastMessageWasCatchUsers.contains(event.getAuthor().getId())) lastMessageWasCatchUsers.remove(event.getAuthor().getId());
            return;
        }
        if(!lastMessageWasCatchUsers.contains(event.getAuthor().getId())){
            System.out.println("[AntiBot] " + event.getAuthor().getName() + " was added to the check-needed list");
            lastMessageWasCatchUsers.add(event.getAuthor().getId());
            return;
        }
        if(System.currentTimeMillis() - pokemonSpawnTimestamp < 850){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " might be using a pokemon catcher bot (Check #2)").queue();
            System.out.println("[AntiBot] " + event.getAuthor().getName() + " might be using a pokemon catcher bot (Check #2)");
        } else {
            System.out.println("[AntiBot] " + event.getAuthor().getName() + " seems to be legit (Check #2) Time: " + (System.currentTimeMillis() - pokemonSpawnTimestamp));
        }
    }

    @Override
    public void onUserTyping(UserTypingEvent event) {
        this.typingUsers.add(event.getUser().getId());
    }
}
