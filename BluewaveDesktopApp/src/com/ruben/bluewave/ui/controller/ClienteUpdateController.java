package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.ui.views.ClienteAltaView;

public class ClienteUpdateController extends AbstractController {

    private ClienteAltaView view;
    private ClienteService clienteService;
    private DireccionService direccionService;
    private ClienteDTO clienteDTO;

    public ClienteUpdateController(ClienteAltaView view, ClienteDTO clienteDTO) {
        super(view);
        this.view = view;
        this.clienteDTO = clienteDTO;
        this.clienteService = new ClienteServiceImpl();
        this.direccionService = new DireccionServiceImpl();
    }

    @Override
    public void doAction() {
        Cliente cliente = view.getClienteFromForm();

        if (cliente == null) {
            return;
        }

        if (clienteDTO == null || clienteDTO.getId() == null) {
            JOptionPane.showMessageDialog(view, "Error: cliente sin ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        cliente.setId(clienteDTO.getId());

        try {
           
            if (clienteDTO.getDireccionId() != null) {
              
                Direccion direccion = view.getDireccionFromForm();
                if (direccion != null) {
                    direccion.setId(clienteDTO.getDireccionId());
                    direccionService.update(direccion);
                    cliente.setDireccionId(clienteDTO.getDireccionId());
                } else {
                    // Para mantener DIreccion existente
                    cliente.setDireccionId(clienteDTO.getDireccionId());
                }
            } else {
              
                Direccion direccion = view.getDireccionFromForm();
                if (direccion != null) {
                    Direccion nuevaDireccion = direccionService.create(direccion);
                    if (nuevaDireccion != null && nuevaDireccion.getId() != null) {
                        cliente.setDireccionId(nuevaDireccion.getId());
                    }
                }
            }

            boolean updated = clienteService.update(cliente);

            if (updated) {
                JOptionPane.showMessageDialog(view, "Cliente actualizado correctamente.", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
                view.setEditable(false);
                view.setAgreeController(new ClienteSetEditableController(view));
                view.limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(view, "Error al actualizar el cliente", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Error en base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}