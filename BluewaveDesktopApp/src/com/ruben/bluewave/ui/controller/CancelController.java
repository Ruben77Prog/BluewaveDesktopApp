package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.View;

public class CancelController extends AbstractController{

	public CancelController(View view) {
		super(view, "Cancelar", null );	
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
		
	}

	@Override
	public void doAction() {
		MainWindow.getInstance().removeView(getView());
		
	}

}
