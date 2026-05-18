package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.ui.views.ClienteSearchView;

public class ClienteDeleteController extends AbstractController {

    private ClienteService clienteService;
    private ClienteSearchView searchView;
    private ClienteDTO cliente;

    public ClienteDeleteController(ClienteSearchView searchView, ClienteDTO cliente) {
        super("Eliminar", null);
        this.searchView = searchView;
        this.cliente = cliente;
        this.clienteService = new ClienteServiceImpl();
    }

    @Override
    public void doAction() {
        int confirm = JOptionPane.showConfirmDialog(searchView,
                "¿Está seguro de eliminar al cliente " + cliente.getNombre() + " " + cliente.getApellido1() + "?",
                "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Cliente clienteEntity = new Cliente();
                clienteEntity.setId(cliente.getId());
                boolean eliminado = clienteService.delete(clienteEntity);
                if (eliminado) {
                    JOptionPane.showMessageDialog(searchView, "Cliente eliminado correctamente");
                    searchView.buscar();
                } else {
                    JOptionPane.showMessageDialog(searchView, "Error al eliminar cliente", "Error", JOptionPane.ERROR_MESSAGE);
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