package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;

public class OpenNuevaIncidenciaController extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public OpenNuevaIncidenciaController() {
        super("Nueva incidencia", 
              new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NuevaIncidenciaView view = new NuevaIncidenciaView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}