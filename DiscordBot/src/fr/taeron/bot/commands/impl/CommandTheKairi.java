package fr.taeron.bot.commands.impl;

import fr.taeron.bot.commands.Command;
import fr.taeron.bot.utils.Utils;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandTheKairi extends Command {

	public CommandTheKairi() {
		super("tk", "you fils de pute", "tk");
	}

	@Override
	public boolean execute(TextChannel c, String[] args) {
		Utils.sendFileAsync(c, Utils.imageFromUrl("http://dev.qqindustries.xyz/YOUUUUUUUUUU.mp4"), "YOU.mp4", null);
		return true;
	}
}
