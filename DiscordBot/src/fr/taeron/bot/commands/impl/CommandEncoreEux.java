package fr.taeron.bot.commands.impl;

import fr.taeron.bot.commands.Command;
import fr.taeron.bot.utils.Utils;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandEncoreEux extends Command {

	public CommandEncoreEux() {
		super("encoreeux", "Arabes de merde", "encoreeux");
	}

	@Override
	public boolean execute(TextChannel c, String[] args) {
		Utils.sendFileAsync(c, Utils.imageFromUrl("http://dev.qqindustries.xyz/encoreeux.jpg"), "arabes.png", null);
		return true;
	}
}
