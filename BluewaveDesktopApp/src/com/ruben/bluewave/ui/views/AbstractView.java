package com.ruben.bluewave.ui.views;

import javax.swing.JPanel;

public class AbstractView  extends JPanel implements View {

	private String name = null;

	public AbstractView() {

	}

	public AbstractView(String name) {
		setName(name);
		

	}
	
	@Override
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
