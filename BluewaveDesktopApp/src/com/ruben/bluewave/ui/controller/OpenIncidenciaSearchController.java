package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;

import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.IncidenciaSearchView;

public class OpenIncidenciaSearchController extends AbstractAction {

    public OpenIncidenciaSearchController() {
        super("Buscar incidencia...", new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        IncidenciaSearchView view = new IncidenciaSearchView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}