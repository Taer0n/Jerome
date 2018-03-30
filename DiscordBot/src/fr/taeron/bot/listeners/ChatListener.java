package fr.taeron.bot.listeners;

import fr.taeron.Bot;
import fr.taeron.Config;
import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
		} else {
			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), event.getTextChannel().getName(),
					event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
			if (event.getMember().getEffectiveName().equalsIgnoreCase("depardieuQ_Q") || event.getMember().getUser().getId().equalsIgnoreCase("140207775077761024")) {
				event.getChannel().sendMessage(
						"Ta gueule sale pd t'as interdiction de t'approcher de Jérome sale malade mental");
				return;
			} else if (event.getMessage().getContentRaw().startsWith(Config.PREFIX)) {
				Bot.getInstance().getCommandManager().handleCommand(event.getTextChannel(), event.getMessage().getContentRaw());
			}
		}
	}
}
