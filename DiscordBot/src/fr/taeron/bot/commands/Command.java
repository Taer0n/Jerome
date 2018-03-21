package fr.taeron.bot.commands;

import net.dv8tion.jda.core.entities.TextChannel;

public abstract class Command {

	private String name, description, usage;
	
	public Command(String name, String description, String usage) {
		this.name = name;
		this.description = description;
		this.usage = usage;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getUsage() {
		return usage;
	}
	
	public abstract boolean execute(TextChannel c, String[] args);
}
