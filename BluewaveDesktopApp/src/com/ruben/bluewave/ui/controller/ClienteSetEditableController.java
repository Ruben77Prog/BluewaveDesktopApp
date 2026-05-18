package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.ui.views.ClienteAltaView;

public class ClienteSetEditableController extends AbstractController {

    private ClienteDTO clienteDTO;

    public ClienteSetEditableController(ClienteAltaView view) {
        super(view);
        this.clienteDTO = null;
    }

    public ClienteSetEditableController(ClienteAltaView view, ClienteDTO clienteDTO) {
        super(view);
        this.clienteDTO = clienteDTO;
    }

    @Override
    public void doAction() {
        ClienteAltaView view = (ClienteAltaView) getView();
        if (clienteDTO != null) {
            view.cargarCliente(clienteDTO);
        }
        view.setEditable(true);
        view.setAgreeController(new ClienteUpdateController(view, clienteDTO));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        doAction();
    }
}