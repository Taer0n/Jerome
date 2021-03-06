package fr.taeron.bot.listeners;

import fr.taeron.Bot;
import fr.taeron.Config;
import fr.taeron.bot.utils.FinderUtil;
import fr.taeron.bot.utils.Utils;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

	String[] arrayOfGayReactions = new String[] {"Le PD a dit %msg%, et tout le monde désaprouve ce que dit le pd"};

	
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
		} else {
			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), event.getTextChannel().getName(),
					event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
			if (event.getMember().getUser().getId().equalsIgnoreCase("140207775077761024")
					&& event.getMessage().getContentDisplay().startsWith(Config.PREFIX)) {
				event.getChannel()
						.sendMessage("Ta gueule sale pd t'as interdiction de t'approcher de Jérome sale malade mental").queue();
				return;
			} else if (event.getMessage().getContentRaw().startsWith(Config.PREFIX)) {
				Bot.getInstance().getCommandManager().handleCommand(event.getTextChannel(),
						event.getMessage().getContentRaw());
				event.getMessage().delete().complete();
			}
			if (event.getMember().getUser().getId().equalsIgnoreCase("317669104536387586") && !event.getMessage().getContentDisplay().startsWith(Config.PREFIX)) {
				String msg = event.getMessage().getContentDisplay();
				Message m = event.getChannel().sendMessage(
						arrayOfGayReactions[Utils.randInt(0, arrayOfGayReactions.length - 1)].replaceAll("%msg%", msg))
						.complete();
				Utils.delayedRemove(m, 5000);
			}
			if(event.getMember().getUser().getId().equalsIgnoreCase("235088799074484224") && event.getChannel().getName().equalsIgnoreCase("general")){
			    event.getMessage().delete().queue();
            }
            if(event.getMessage().getContentDisplay().startsWith("!play") && !event.getChannel().getName().equalsIgnoreCase("bot-commands")){
			    event.getMessage().delete().queue();
			    Utils.delayedRemove(event.getChannel().sendMessage("Hey " + event.getMessage().getMember().getAsMention() + ", va vite dans " + FinderUtil.findTextChannel("bot-commands").get(0).getAsMention() + " pour poster tes commandes avant que j'insulte tous tes morts").complete(), 5000);
            }
            if(!event.getMember().getUser().isBot() && event.getChannel().getName().equalsIgnoreCase("partages") && !event.getMessage().getContentDisplay().contains("http://") && !event.getMessage().getContentDisplay().contains("https://")){
				event.getMessage().delete().queue();
				Utils.delayedRemove(event.getChannel().sendMessage("Hey " + event.getMessage().getMember().getAsMention() + ", le principe du channel de partage c'est de partager des liens pas d'ouvrir ta grande gueule donc bouge").complete(), 5000);
			}
		}
	}
}
