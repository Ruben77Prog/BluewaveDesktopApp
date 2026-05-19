package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.EmpleadoAltaView;

public class OpenEmpleadoCreateController extends AbstractAction {

    public OpenEmpleadoCreateController() {
        super("Nuevo empleado", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
    }

    public void doAction() {
        EmpleadoAltaView view = new EmpleadoAltaView();
        view.setAgreeController(new EmpleadoCreateController(view));
        MainWindow.getInstance().addView(view.getName(), view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}