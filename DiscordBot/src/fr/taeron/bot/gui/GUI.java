package fr.taeron.bot.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.WindowConstants;

import fr.taeron.Bot;

public class GUI extends JFrame {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -6561686975864823456L;
	private ConsolePanel console;
    private Bot bot;
    
    public GUI(Bot bot) {
        super();
        this.bot = bot;
        console = new ConsolePanel();
    }
    
    public void init() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jérome");
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Console", console);
        tabs.add("Bot Chat", new CommandPanel());
        getContentPane().add(tabs);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowListener() {
            @Override public void windowOpened(WindowEvent e) {}
            @Override public void windowClosing(WindowEvent e) {bot.shutdown();}
            @Override public void windowClosed(WindowEvent e) {}
            @Override public void windowIconified(WindowEvent e) {}
            @Override public void windowDeiconified(WindowEvent e) {}
            @Override public void windowActivated(WindowEvent e) {}
            @Override public void windowDeactivated(WindowEvent e) {}
        });
    }
}
