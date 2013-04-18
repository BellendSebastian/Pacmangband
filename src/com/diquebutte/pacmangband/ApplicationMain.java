package com.diquebutte.pacmangband;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.diquebutte.pacmangband.screens.Screen;
import com.diquebutte.pacmangband.screens.StartScreen;

import asciiPanel.AsciiPanel;

public class ApplicationMain extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;

	private AsciiPanel terminal;
	private Screen screen;
	
	public ApplicationMain() {
		super();
		terminal = new AsciiPanel();
		add(terminal);
		pack();
		screen = new StartScreen();
		addKeyListener(this);
		repaint();
	}

	public void repaint() {
		terminal.clear();
		screen.displayOutput(terminal);
		super.repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		screen = screen.respondToUserInput(e);
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	public static void main(String[] args) {
		ApplicationMain app = new ApplicationMain();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setVisible(true);
		Dimension dm = Toolkit.getDefaultToolkit().getScreenSize();
		app.setLocation(dm.width / 2 - app.getSize().width / 2, dm.height /2 - app.getSize().height / 2);
		app.setTitle("Pacmangband");
	}
}
