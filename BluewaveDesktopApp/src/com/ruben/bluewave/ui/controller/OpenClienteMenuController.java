package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.ClienteAltaView;

public class OpenClienteMenuController extends AbstractAction {

    public OpenClienteMenuController() {
        super("Nuevo cliente");
    }

    public void doAction() {
        ClienteAltaView clienteCreateView = new ClienteAltaView();
        clienteCreateView.setAgreeController(new ClienteCreateController(clienteCreateView));
        MainWindow.getInstance().addView(clienteCreateView.getName(), clienteCreateView);
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        doAction();
    }
}