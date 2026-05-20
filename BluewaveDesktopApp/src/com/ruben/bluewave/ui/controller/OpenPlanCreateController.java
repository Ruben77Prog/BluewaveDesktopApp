package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.PlanAltaView;

public class OpenPlanCreateController extends AbstractAction {

    public OpenPlanCreateController() {
        super(null, new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PlanAltaView view = new PlanAltaView();
        MainWindow.getInstance().addView(view.getName(), view);
    }
}
