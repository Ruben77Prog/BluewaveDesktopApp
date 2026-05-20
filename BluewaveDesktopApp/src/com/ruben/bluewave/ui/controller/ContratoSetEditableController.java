package com.ruben.bluewave.ui.controller;

import java.awt.event.ActionEvent;

import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.ui.views.ContratoAltaView;

public class ContratoSetEditableController extends AbstractController {

	private ContratoDTO contratoDTO;

	public ContratoSetEditableController(ContratoAltaView view) {
		super(view);
		this.contratoDTO = null;
	}

	public ContratoSetEditableController(ContratoAltaView view, ContratoDTO contratoDTO) {
		super(view);
		this.contratoDTO = contratoDTO;
	}

	@Override
	public void doAction() {
		ContratoAltaView view = (ContratoAltaView) getView();
		if (contratoDTO != null) {
			view.cargarContrato(contratoDTO);
		}
		view.setEditable(true);
		view.setAgreeController(new ContratoUpdateController(view, contratoDTO));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		doAction();
	}
}