package fr.taeron.bot.commands;

import java.util.ArrayList;
import java.util.Arrays;

import fr.taeron.Config;
import fr.taeron.bot.commands.impl.CommandDox;
import fr.taeron.bot.commands.impl.CommandEncoreEux;
import fr.taeron.bot.commands.impl.CommandHelp;
import fr.taeron.bot.commands.impl.CommandTheKairi;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandManager {

	private ArrayList<Command> registeredCommands;
	
	public CommandManager() {
		registerCommands(new CommandDox(), new CommandTheKairi(), new CommandHelp(), new CommandEncoreEux());
	}
	
	private void registerCommands(Command... commands) {
		this.registeredCommands = new ArrayList<>();
		this.registeredCommands.addAll(Arrays.asList(commands));
	}
	
	public void handleCommand(TextChannel ch, String input) {
		String cleanedCommand = input.replace(Config.PREFIX, "");
		String[] args = cleanedCommand.split(" ");
		for(Command c : this.registeredCommands) {
			if(c.getName().equalsIgnoreCase(args[0])) {
				System.out.println("Dispatching command " + c.getName());
				if(!c.execute(ch, args)) {
					ch.sendMessage("Usage: " + Config.PREFIX + c.getUsage());
				}
				return;
			}
		}
		ch.sendMessage("Unknown command.").complete();
	}
	
	public ArrayList<Command> getRegisteredCommands() {
		return registeredCommands;
	}
}
