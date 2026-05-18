package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.ui.views.IncidenciaSearchView;

public class IncidenciaDeleteController extends AbstractController {

    private IncidenciaService incidenciaService;
    private IncidenciaSearchView searchView;
    private IncidenciaDTO incidencia;

    public IncidenciaDeleteController(IncidenciaSearchView searchView, IncidenciaDTO incidencia) {
        super(searchView);
        this.searchView = searchView;
        this.incidencia = incidencia;
        this.incidenciaService = new IncidenciaServiceImpl();
    }

    @Override
    public void doAction() {
        int confirm = JOptionPane.showConfirmDialog(searchView,
                "¿Está seguro de eliminar la incidencia " + incidencia.getNumeroIncidencia() + " - " + incidencia.getTitulo() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Incidencia incidenciaEntity = new Incidencia();
                incidenciaEntity.setId(incidencia.getId());
                boolean eliminado = incidenciaService.delete(incidencia.getId());
                if (eliminado) {
                    JOptionPane.showMessageDialog(searchView, "Incidencia eliminada correctamente");
                    searchView.buscar();
                } else {
                    JOptionPane.showMessageDialog(searchView, "Error al eliminar la incidencia", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(searchView, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}