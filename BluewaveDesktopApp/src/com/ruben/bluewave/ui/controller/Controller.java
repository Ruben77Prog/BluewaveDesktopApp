package com.ruben.bluewave.ui.controller;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import com.ruben.bluewave.ui.views.View;

public interface Controller {

	public View getView();

	public void doAction();
}
