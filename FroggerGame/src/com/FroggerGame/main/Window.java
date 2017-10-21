package com.FroggerGame.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends JFrame {
	
	private static final long serialVersionUID = -8255319694373975038L;

	public Window(int width, int height, String title, Main main) {
		super(title);
		
		setSize(new Dimension(width, height));
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		add(main);
		setVisible(true);
		
		getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
		
		setLocationRelativeTo(null);
	}
	
}
