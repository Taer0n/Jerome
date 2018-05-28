package fr.taeron.bot.commands.impl;

import java.awt.Color;

import fr.taeron.Bot;
import fr.taeron.Config;
import fr.taeron.bot.commands.Command;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandHelp extends Command {

	public CommandHelp() {
		super("help", "Displays this thing", "help");
	}

	@Override
	public boolean execute(TextChannel c, String[] args) {
		String commands = "";
		EmbedBuilder builder = new EmbedBuilder();
		builder.setAuthor("Jérome | Aide");
		builder.setColor(Color.RED);
		for(Command co : Bot.getInstance().getCommandManager().getRegisteredCommands()) {
			commands = commands + "» " + Config.PREFIX + co.getName() + ": " + co.getDescription() + "\n";
		}
		builder.setDescription(commands);
		c.sendMessage(builder.build()).queue();
		return true;
	}
}
