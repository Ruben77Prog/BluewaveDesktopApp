package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.ClienteAltaView;

public class ClienteOpenCreateController extends AbstractAction {

    public ClienteOpenCreateController() {
        super("Añadir cliente");
    }

    public void doAction() {
        ClienteAltaView view = new ClienteAltaView();
        view.setAgreeController(new ClienteCreateController(view));
        MainWindow.getInstance().addView(view.getName(), view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}