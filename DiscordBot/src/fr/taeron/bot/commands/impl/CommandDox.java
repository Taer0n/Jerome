package fr.taeron.bot.commands.impl;

import fr.taeron.bot.commands.Command;
import fr.taeron.bot.utils.Utils;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandDox extends Command {

	public CommandDox() {
		super("dox", "Displays random dox picture for memes", "dox");
	}

	@Override
	public boolean execute(TextChannel c, String[] args) {
		int rand = Utils.randInt(1, 19);
		c.sendFile(Utils.imageFromUrl("http://149.202.230.170/" + rand + ".png"), "meme.png", null).queue();
		return true;
	}
}
