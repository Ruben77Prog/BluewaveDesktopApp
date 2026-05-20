package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.ContratoAltaView;

public class OpenContratoCreateController extends AbstractAction {

    public OpenContratoCreateController() {
        super(null, new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ContratoAltaView view = new ContratoAltaView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}