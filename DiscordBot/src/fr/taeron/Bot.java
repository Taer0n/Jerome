package fr.taeron;

import javax.security.auth.login.LoginException;

import fr.taeron.bot.commands.CommandManager;
import fr.taeron.bot.gui.GUI;
import fr.taeron.bot.listeners.ChatListener;
import net.dv8tion.jda.bot.JDABot;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;

public class Bot {

	private JDABot bot;
	private JDABuilder builder;
	private static Bot instance;
	private CommandManager commandManager;
	
	public static void main(String[] args) {
		try {
			new Bot();
		} catch (LoginException | InterruptedException e) {
			e.printStackTrace();
			System.err.println("Unnable to connect the bot. Exiting in 5 seconds.");
			new Thread() {
				public void run() {
					try {
						sleep(5000);
						System.exit(0);
					} catch (InterruptedException e) {
						System.exit(0);
						e.printStackTrace();
					}
				}
			}.start();
		}
	}
	
	public Bot() throws LoginException, InterruptedException {
		new GUI(this).init();
		this.commandManager = new CommandManager();
		instance = this;
		builder = new JDABuilder(AccountType.BOT).setToken(Streamxd.token);
		builder.addEventListener(new ChatListener());
		builder.setGame(Game.watching("Use =help"));
		bot = builder.buildBlocking().asBot();
		//FinderUtil.findTextChannel("general", Bot.getInstance().getBot().getJDA().getGuilds().get(0)).get(0).sendMessage("Hello my d00ds").complete();
	}
	
	public JDABot getBot() {
		return bot;
	}
	
	public void shutdown() {
		//FinderUtil.findTextChannel("general", Bot.getInstance().getBot().getJDA().getGuilds().get(0)).get(0).sendMessage("Bye..").complete();
		bot.getJDA().shutdownNow();
	}
	
	public static Bot getInstance() {
		return instance;
	}
	
	public CommandManager getCommandManager() {
		return commandManager;
	}
}
