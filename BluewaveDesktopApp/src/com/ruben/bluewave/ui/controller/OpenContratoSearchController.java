package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.ContratoSearchView;

public class OpenContratoSearchController extends AbstractAction {

    public OpenContratoSearchController() {
        super(null, new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ContratoSearchView view = new ContratoSearchView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}