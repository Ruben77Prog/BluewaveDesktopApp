package com.ruben.bluewave.ui.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import org.jdesktop.swingx.JXHyperlink;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class SwingXTestWindow {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwingXTestWindow window = new SwingXTestWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SwingXTestWindow() {
		initialize();
		postInitialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void postInitialize() {
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		SwingXTestView swingXTestView = new SwingXTestView();
		frame.getContentPane().add(swingXTestView);
		
		JXHyperlink hprlnkNuevo = new JXHyperlink();
		frame.getContentPane().add(hprlnkNuevo);
		hprlnkNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		hprlnkNuevo.setText("Nuevo");
	}
	
}
