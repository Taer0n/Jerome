package fr.taeron.bot.commands.impl;

import fr.taeron.Bot;
import fr.taeron.Config;
import fr.taeron.bot.commands.Command;
import net.dv8tion.jda.core.entities.TextChannel;

public class HelpCommand extends Command {

	public HelpCommand() {
		super("help", "Displays this thing", "help");
	}

	@Override
	public boolean execute(TextChannel c, String[] args) {
		String commands = "";
		for(Command co : Bot.getInstance().getCommandManager().getRegisteredCommands()) {
			commands = commands + "» " + Config.PREFIX + co.getName() + ": " + co.getDescription() + "\n";
		}
		c.sendMessage(commands).complete();
		return true;
	}
}
