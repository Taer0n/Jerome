package fr.taeron.bot.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.PrintStream;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsolePanel extends JPanel {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -5384340893844526646L;

	public ConsolePanel() {
        JTextArea text = new JTextArea();
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setEditable(false);
        PrintStream con = new PrintStream(new TextAreaOutputStream(text));
        System.setOut(con);
        System.setErr(con);
        
        JScrollPane pane = new JScrollPane();
        pane.setViewportView(text);
        
        this.setLayout(new GridLayout(1,1));
        add(pane);
        setPreferredSize(new Dimension(400,300));
    }
}
