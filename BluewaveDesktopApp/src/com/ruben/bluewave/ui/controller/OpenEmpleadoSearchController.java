package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.EmpleadoSearchView;

public class OpenEmpleadoSearchController extends AbstractAction {

    private static final long serialVersionUID = 1L;

    public OpenEmpleadoSearchController() {
        super(null,
              new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }

    private void doAction() {
        EmpleadoSearchView view = new EmpleadoSearchView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}