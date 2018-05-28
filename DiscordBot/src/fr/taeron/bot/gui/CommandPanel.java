package fr.taeron.bot.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;

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
		    	FinderUtil.findTextChannel("general").get(0).sendMessage(field.getText()).complete();
		        field.setText("");
		    }
		});
		add(field);
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("test");
		comboBox.addItem("test2");
		comboBox.addItem("test3");
		comboBox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				System.out.println("Item changé en " + e.getItem().toString());
			}
		});
		add(comboBox);
    }
}
