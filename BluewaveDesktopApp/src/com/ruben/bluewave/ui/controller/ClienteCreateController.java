package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.ui.views.ClienteAltaView;

public class ClienteCreateController extends AbstractController {

    private ClienteService clienteService = new ClienteServiceImpl();
    private DireccionService direccionService = new DireccionServiceImpl();

    public ClienteCreateController(ClienteAltaView view) {
        super(view);
    }

    @Override
    public void doAction() {
        ClienteAltaView view = (ClienteAltaView) getView();
        
        try {
            Direccion direccion = view.getDireccionFromForm();
            if (direccion == null) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar país, provincia y ciudad.", 
                        "Dirección incompleta", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Direccion direccionGuardada = direccionService.create(direccion);
            if (direccionGuardada == null || direccionGuardada.getId() == null) {
                JOptionPane.showMessageDialog(view, "Error al guardar la dirección", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = view.getClienteFromForm();
            if (cliente == null) return;
            
            cliente.setDireccionId(direccionGuardada.getId());

            Long idCliente = clienteService.create(cliente);
            if (idCliente != null) {
                JOptionPane.showMessageDialog(view, "Cliente creado correctamente con ID: " + idCliente);
                view.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(view, "Error al crear el cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}