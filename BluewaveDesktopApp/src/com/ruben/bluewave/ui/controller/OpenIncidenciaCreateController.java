package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;

public class OpenIncidenciaCreateController extends AbstractAction {

    public OpenIncidenciaCreateController() {
        super(null, new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
    }

    public void doAction() {
        NuevaIncidenciaView view = new NuevaIncidenciaView();
        view.setAgreeController(new IncidenciaCreateController(view));
        MainWindow.getInstance().addView(view.getName(), view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}