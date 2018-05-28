package fr.taeron.bot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

import fr.taeron.Bot;
import fr.taeron.bot.utils.FinderUtil;
import net.dv8tion.jda.core.entities.TextChannel;

public class CommandPanel extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5384340893844526646L;
	private String channelName = "";

	public CommandPanel() {
		JTextField field = new JTextField(50);
		field.addActionListener(event -> {
            FinderUtil.findTextChannel(channelName).get(0).sendMessage(field.getText()).complete();
            field.setText("");
        });
		add(field);
		JComboBox comboBox = new JComboBox();
		new Thread(() -> {
			try {
				Thread.sleep(3000);
				for(TextChannel textChannel : Bot.getInstance().getBot().getJDA().getGuilds().get(0).getTextChannels()){
					comboBox.addItem(textChannel.getName());
				}
				comboBox.addItemListener(e -> channelName = e.getItem().toString());
				add(comboBox);
			} catch (InterruptedException e1) {
				System.exit(0);
				e1.printStackTrace();
			}
		}).start();

    }
}
