package fr.taeron.bot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.taeron.Bot;
import fr.taeron.bot.utils.FinderUtil;

public class CommandPanel extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5384340893844526646L;

	public CommandPanel() {
		JTextField field = new JTextField(50);
		field.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent event) {
		    	FinderUtil.findTextChannel("general", Bot.getInstance().getBot().getJDA().getGuilds().get(0)).get(0).sendMessage(field.getText()).complete();
		        field.setText("");
		    }
		});
		add(field);
    }
}
