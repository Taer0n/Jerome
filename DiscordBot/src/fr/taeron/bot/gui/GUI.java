package fr.taeron.bot.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

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
    }
    
    public void init() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jérome");
        JTabbedPane tabs = new JTabbedPane();
        tabs.add("Bot Chat", new CommandPanel());
        console = new ConsolePanel();

        tabs.add("Console", console);
        getContentPane().add(tabs);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setMaximumSize(getSize());
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
