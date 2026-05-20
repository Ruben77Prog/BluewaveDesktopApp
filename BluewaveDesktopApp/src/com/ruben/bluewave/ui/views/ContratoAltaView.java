package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.model.MetodoPago;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.EstadoContratoService;
import com.ruben.bluewave.service.MetodoPagoService;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.service.impl.EstadoContratoServiceImpl;
import com.ruben.bluewave.service.impl.MetodoPagoServiceImpl;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.ui.controller.AbstractController;
import com.ruben.bluewave.ui.renderer.ClienteCBRenderer;
import com.ruben.bluewave.ui.renderer.EstadoContratoCBRenderer;
import com.ruben.bluewave.ui.renderer.MetodoPagoCBRenderer;
import com.ruben.bluewave.ui.renderer.PlanCBRenderer;
import com.toedter.calendar.JDateChooser;

public class ContratoAltaView extends AbstractView {

	private ContratoService contratoService;
	private ClienteService clienteService;
	private PlanService planService;
	private MetodoPagoService metodoPagoService;
	private EstadoContratoService estadoContratoService;

	private JTextField numeroContratoTF;
	private JDateChooser fechaInicioDC;
	private JDateChooser fechaFinDC;
	private JTextField costeInstalacionTF;
	private JTextField costeMensualTF;
	private JComboBox<MetodoPago> metodoPagoCB;
	private JComboBox<ClienteDTO> clienteCB;
	private JComboBox<PlanDTO> planCB;
	private JComboBox<EstadoContrato> estadoContratoCB;

	private boolean editable = false;
	private JButton guardarButton;

	public ContratoAltaView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initServices() {
		contratoService = new ContratoServiceImpl();
		clienteService = new ClienteServiceImpl();
		planService = new PlanServiceImpl();
		metodoPagoService = new MetodoPagoServiceImpl();
		estadoContratoService = new EstadoContratoServiceImpl();
	}

	private void initialize() {
		setName("Alta de contrato");
		setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		formPanel.setLayout(gbl);

		JLabel numeroLabel = new JLabel("Número Contrato:");
		GridBagConstraints gbcNumeroLabel = new GridBagConstraints();
		gbcNumeroLabel.insets = new Insets(0, 0, 5, 5);
		gbcNumeroLabel.gridx = 0;
		gbcNumeroLabel.gridy = 0;
		formPanel.add(numeroLabel, gbcNumeroLabel);

		numeroContratoTF = new JTextField();
		numeroContratoTF.setColumns(15);
		GridBagConstraints gbcNumeroTF = new GridBagConstraints();
		gbcNumeroTF.insets = new Insets(0, 0, 5, 5);
		gbcNumeroTF.fill = GridBagConstraints.HORIZONTAL;
		gbcNumeroTF.gridx = 1;
		gbcNumeroTF.gridy = 0;
		formPanel.add(numeroContratoTF, gbcNumeroTF);

		JLabel fechaInicioLabel = new JLabel("Fecha Inicio:");
		GridBagConstraints gbcFechaInicioLabel = new GridBagConstraints();
		gbcFechaInicioLabel.insets = new Insets(0, 0, 5, 5);
		gbcFechaInicioLabel.gridx = 0;
		gbcFechaInicioLabel.gridy = 1;
		formPanel.add(fechaInicioLabel, gbcFechaInicioLabel);

		fechaInicioDC = new JDateChooser();
		GridBagConstraints gbcFechaInicioDC = new GridBagConstraints();
		gbcFechaInicioDC.insets = new Insets(0, 0, 5, 5);
		gbcFechaInicioDC.fill = GridBagConstraints.HORIZONTAL;
		gbcFechaInicioDC.gridx = 1;
		gbcFechaInicioDC.gridy = 1;
		formPanel.add(fechaInicioDC, gbcFechaInicioDC);

		JLabel fechaFinLabel = new JLabel("Fecha Fin:");
		GridBagConstraints gbcFechaFinLabel = new GridBagConstraints();
		gbcFechaFinLabel.insets = new Insets(0, 0, 5, 5);
		gbcFechaFinLabel.gridx = 0;
		gbcFechaFinLabel.gridy = 2;
		formPanel.add(fechaFinLabel, gbcFechaFinLabel);

		fechaFinDC = new JDateChooser();
		GridBagConstraints gbcFechaFinDC = new GridBagConstraints();
		gbcFechaFinDC.insets = new Insets(0, 0, 5, 5);
		gbcFechaFinDC.fill = GridBagConstraints.HORIZONTAL;
		gbcFechaFinDC.gridx = 1;
		gbcFechaFinDC.gridy = 2;
		formPanel.add(fechaFinDC, gbcFechaFinDC);

		JLabel costeInstalacionLabel = new JLabel("Coste Instalación (€):");
		GridBagConstraints gbcCosteInstalacionLabel = new GridBagConstraints();
		gbcCosteInstalacionLabel.insets = new Insets(0, 0, 5, 5);
		gbcCosteInstalacionLabel.gridx = 0;
		gbcCosteInstalacionLabel.gridy = 3;
		formPanel.add(costeInstalacionLabel, gbcCosteInstalacionLabel);

		costeInstalacionTF = new JTextField();
		costeInstalacionTF.setColumns(10);
		GridBagConstraints gbcCosteInstalacionTF = new GridBagConstraints();
		gbcCosteInstalacionTF.insets = new Insets(0, 0, 5, 5);
		gbcCosteInstalacionTF.fill = GridBagConstraints.HORIZONTAL;
		gbcCosteInstalacionTF.gridx = 1;
		gbcCosteInstalacionTF.gridy = 3;
		formPanel.add(costeInstalacionTF, gbcCosteInstalacionTF);

		JLabel costeMensualLabel = new JLabel("Coste Mensual (€):");
		GridBagConstraints gbcCosteMensualLabel = new GridBagConstraints();
		gbcCosteMensualLabel.insets = new Insets(0, 0, 5, 5);
		gbcCosteMensualLabel.gridx = 0;
		gbcCosteMensualLabel.gridy = 4;
		formPanel.add(costeMensualLabel, gbcCosteMensualLabel);

		costeMensualTF = new JTextField();
		costeMensualTF.setColumns(10);
		GridBagConstraints gbcCosteMensualTF = new GridBagConstraints();
		gbcCosteMensualTF.insets = new Insets(0, 0, 5, 5);
		gbcCosteMensualTF.fill = GridBagConstraints.HORIZONTAL;
		gbcCosteMensualTF.gridx = 1;
		gbcCosteMensualTF.gridy = 4;
		formPanel.add(costeMensualTF, gbcCosteMensualTF);

		JLabel metodoPagoLabel = new JLabel("Método Pago:");
		GridBagConstraints gbcMetodoPagoLabel = new GridBagConstraints();
		gbcMetodoPagoLabel.insets = new Insets(0, 0, 5, 5);
		gbcMetodoPagoLabel.gridx = 0;
		gbcMetodoPagoLabel.gridy = 5;
		formPanel.add(metodoPagoLabel, gbcMetodoPagoLabel);

		metodoPagoCB = new JComboBox<>();
		GridBagConstraints gbcMetodoPagoCB = new GridBagConstraints();
		gbcMetodoPagoCB.insets = new Insets(0, 0, 5, 5);
		gbcMetodoPagoCB.fill = GridBagConstraints.HORIZONTAL;
		gbcMetodoPagoCB.gridx = 1;
		gbcMetodoPagoCB.gridy = 5;
		formPanel.add(metodoPagoCB, gbcMetodoPagoCB);

		JLabel clienteLabel = new JLabel("Cliente:");
		GridBagConstraints gbcClienteLabel = new GridBagConstraints();
		gbcClienteLabel.insets = new Insets(0, 0, 5, 5);
		gbcClienteLabel.gridx = 0;
		gbcClienteLabel.gridy = 6;
		formPanel.add(clienteLabel, gbcClienteLabel);

		clienteCB = new JComboBox<>();
		GridBagConstraints gbcClienteCB = new GridBagConstraints();
		gbcClienteCB.insets = new Insets(0, 0, 5, 5);
		gbcClienteCB.fill = GridBagConstraints.HORIZONTAL;
		gbcClienteCB.gridx = 1;
		gbcClienteCB.gridy = 6;
		formPanel.add(clienteCB, gbcClienteCB);

		JLabel planLabel = new JLabel("Plan:");
		GridBagConstraints gbcPlanLabel = new GridBagConstraints();
		gbcPlanLabel.insets = new Insets(0, 0, 5, 5);
		gbcPlanLabel.gridx = 0;
		gbcPlanLabel.gridy = 7;
		formPanel.add(planLabel, gbcPlanLabel);

		planCB = new JComboBox<>();
		GridBagConstraints gbcPlanCB = new GridBagConstraints();
		gbcPlanCB.insets = new Insets(0, 0, 5, 5);
		gbcPlanCB.fill = GridBagConstraints.HORIZONTAL;
		gbcPlanCB.gridx = 1;
		gbcPlanCB.gridy = 7;
		formPanel.add(planCB, gbcPlanCB);

		JLabel estadoLabel = new JLabel("Estado:");
		GridBagConstraints gbcEstadoLabel = new GridBagConstraints();
		gbcEstadoLabel.insets = new Insets(0, 0, 5, 5);
		gbcEstadoLabel.gridx = 0;
		gbcEstadoLabel.gridy = 8;
		formPanel.add(estadoLabel, gbcEstadoLabel);

		estadoContratoCB = new JComboBox<>();
		GridBagConstraints gbcEstadoCB = new GridBagConstraints();
		gbcEstadoCB.insets = new Insets(0, 0, 5, 5);
		gbcEstadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbcEstadoCB.gridx = 1;
		gbcEstadoCB.gridy = 8;
		formPanel.add(estadoContratoCB, gbcEstadoCB);

		JScrollPane scrollPane = new JScrollPane(formPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		guardarButton = new JButton("Guardar");
		guardarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1511_mount_zip_mount_zip.png")));
		guardarButton.addActionListener(e -> crearContrato());
		buttonPanel.add(guardarButton);

		JButton limpiarButton = new JButton("Limpiar");
		limpiarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png")));
		limpiarButton.addActionListener(e -> limpiarCampos());
		buttonPanel.add(limpiarButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void postInitialize() {
		cargarMetodosPago();
		cargarClientes();
		cargarPlanes();
		cargarEstadosContrato();
	}

	private void cargarMetodosPago() {
		try {
			List<MetodoPago> metodos = metodoPagoService.findAll();
			DefaultComboBoxModel<MetodoPago> model = new DefaultComboBoxModel<>();
			MetodoPago placeholder = new MetodoPago();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (MetodoPago mp : metodos) {
				model.addElement(mp);
			}
			metodoPagoCB.setModel(model);
			metodoPagoCB.setRenderer(new MetodoPagoCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarClientes() {
		try {
			List<ClienteDTO> clientes = clienteService.findAll();
			DefaultComboBoxModel<ClienteDTO> model = new DefaultComboBoxModel<>();
			ClienteDTO placeholder = new ClienteDTO();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (ClienteDTO c : clientes) {
				model.addElement(c);
			}
			clienteCB.setModel(model);
			clienteCB.setRenderer(new ClienteCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarPlanes() {
		try {
			List<PlanDTO> planes = planService.findActivos();
			DefaultComboBoxModel<PlanDTO> model = new DefaultComboBoxModel<>();
			PlanDTO placeholder = new PlanDTO();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (PlanDTO p : planes) {
				model.addElement(p);
			}
			planCB.setModel(model);
			planCB.setRenderer(new PlanCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarEstadosContrato() {
		try {
			List<EstadoContrato> estados = estadoContratoService.findAll();
			DefaultComboBoxModel<EstadoContrato> model = new DefaultComboBoxModel<>();
			EstadoContrato placeholder = new EstadoContrato();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (EstadoContrato ec : estados) {
				model.addElement(ec);
			}
			estadoContratoCB.setModel(model);
			estadoContratoCB.setRenderer(new EstadoContratoCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void crearContrato() {
		try {
			if (numeroContratoTF.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "El número de contrato es obligatorio.", "Datos incompletos",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

			ContratoDTO contrato = getModel();
			if (contrato == null)
				return;

			Long idContrato = contratoService.create(contrato);
			if (idContrato != null) {
				JOptionPane.showMessageDialog(this, "Contrato creado correctamente con ID: " + idContrato);
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(this, "Error al crear el contrato", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public ContratoDTO getModel() {
		if (numeroContratoTF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El número de contrato es obligatorio.", "Datos incompletos",
					JOptionPane.WARNING_MESSAGE);
			return null;
		}

		ContratoDTO contrato = new ContratoDTO();
		contrato.setNumeroContrato(numeroContratoTF.getText().trim());
		contrato.setFechaInicio(fechaInicioDC.getDate());
		contrato.setFechaFin(fechaFinDC.getDate());

		try {
			contrato.setCostoInstalacion(Double.parseDouble(costeInstalacionTF.getText().trim()));
		} catch (NumberFormatException e) {
			contrato.setCostoInstalacion(0.0);
		}

		try {
			contrato.setCostoMensual(Double.parseDouble(costeMensualTF.getText().trim()));
		} catch (NumberFormatException e) {
			contrato.setCostoMensual(0.0);
		}

		MetodoPago metodoPago = (MetodoPago) metodoPagoCB.getSelectedItem();
		if (metodoPago != null && metodoPago.getId() != null) {
			contrato.setMetodoPagoId(metodoPago.getId());
		}

		ClienteDTO cliente = (ClienteDTO) clienteCB.getSelectedItem();
		if (cliente != null && cliente.getId() != null) {
			contrato.setClienteId(cliente.getId());
		}

		PlanDTO plan = (PlanDTO) planCB.getSelectedItem();
		if (plan != null && plan.getId() != null) {
			contrato.setPlanId(plan.getId());
		}

		EstadoContrato estado = (EstadoContrato) estadoContratoCB.getSelectedItem();
		if (estado != null && estado.getId() != null) {
			contrato.setEstadoContratoId(estado.getId());
		}

		return contrato;
	}

	public void limpiarCampos() {
		numeroContratoTF.setText("");
		fechaInicioDC.setDate(null);
		fechaFinDC.setDate(null);
		costeInstalacionTF.setText("");
		costeMensualTF.setText("");
		metodoPagoCB.setSelectedIndex(0);
		clienteCB.setSelectedIndex(0);
		planCB.setSelectedIndex(0);
		estadoContratoCB.setSelectedIndex(0);
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		numeroContratoTF.setEditable(editable);
		fechaInicioDC.setEnabled(editable);
		fechaFinDC.setEnabled(editable);
		costeInstalacionTF.setEditable(editable);
		costeMensualTF.setEditable(editable);
		metodoPagoCB.setEnabled(editable);
		clienteCB.setEnabled(editable);
		planCB.setEnabled(editable);
		estadoContratoCB.setEnabled(editable);

		if (guardarButton != null) {
			guardarButton.setText(editable ? "Actualizar" : "Guardar");
		}
	}

	public void setAgreeController(AbstractController controller) {
		if (guardarButton != null) {
			for (ActionListener al : guardarButton.getActionListeners()) {
				guardarButton.removeActionListener(al);
			}
			guardarButton.addActionListener(controller);
		}
	}

	public void cargarContrato(ContratoDTO contrato) {
		numeroContratoTF.setText(contrato.getNumeroContrato() != null ? contrato.getNumeroContrato() : "");
		fechaInicioDC.setDate(contrato.getFechaInicio());
		fechaFinDC.setDate(contrato.getFechaFin());
		costeInstalacionTF
				.setText(contrato.getCostoInstalacion() != null ? String.valueOf(contrato.getCostoInstalacion()) : "");
		costeMensualTF.setText(contrato.getCostoMensual() != null ? String.valueOf(contrato.getCostoMensual()) : "");

		// Seleccionar método pago
		if (contrato.getMetodoPagoId() != null) {
			for (int i = 0; i < metodoPagoCB.getItemCount(); i++) {
				MetodoPago mp = metodoPagoCB.getItemAt(i);
				if (mp.getId() != null && mp.getId().equals(contrato.getMetodoPagoId())) {
					metodoPagoCB.setSelectedIndex(i);
					i = metodoPagoCB.getItemCount();
				}
			}
		}

		// Seleccionar cliente
		if (contrato.getClienteId() != null) {
			for (int i = 0; i < clienteCB.getItemCount(); i++) {
				ClienteDTO c = clienteCB.getItemAt(i);
				if (c.getId() != null && c.getId().equals(contrato.getClienteId())) {
					clienteCB.setSelectedIndex(i);
					i = clienteCB.getItemCount();
				}
			}
		}

		// Seleccionar plan
		if (contrato.getPlanId() != null) {
			for (int i = 0; i < planCB.getItemCount(); i++) {
				PlanDTO p = planCB.getItemAt(i);
				if (p.getId() != null && p.getId().equals(contrato.getPlanId())) {
					planCB.setSelectedIndex(i);
					i = planCB.getItemCount();
				}
			}
		}

		// Seleccionar estado
		if (contrato.getEstadoContratoId() != null) {
			for (int i = 0; i < estadoContratoCB.getItemCount(); i++) {
				EstadoContrato ec = estadoContratoCB.getItemAt(i);
				if (ec.getId() != null && ec.getId().equals(contrato.getEstadoContratoId())) {
					estadoContratoCB.setSelectedIndex(i);
					i = estadoContratoCB.getItemCount();
				}
			}
		}
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}
}