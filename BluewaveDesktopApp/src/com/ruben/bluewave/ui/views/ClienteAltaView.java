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

import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.model.EstadoCliente;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.CiudadService;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.EstadoClienteService;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.service.PaisService;
import com.ruben.bluewave.service.ProvinciaService;
import com.ruben.bluewave.service.impl.CiudadServiceImpl;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.service.impl.EstadoClienteServiceImpl;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;
import com.ruben.bluewave.service.impl.PaisServiceImpl;
import com.ruben.bluewave.service.impl.ProvinciaServiceImpl;
import com.ruben.bluewave.ui.controller.AbstractController;
import com.ruben.bluewave.ui.renderer.EstadoClienteCBRenderer;
import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
import com.toedter.calendar.JDateChooser;

public class ClienteAltaView extends AbstractView {

	private ClienteService clienteService;
	private PaisService paisService;
	private ProvinciaService provinciaService;
	private CiudadService ciudadService;
	private DireccionService direccionService;
	private GeneroService generoService;
	private EstadoClienteService estadoClienteService;

	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField apellido2TF;
	private JTextField dniTF;
	private JTextField telefonoTF;
	private JTextField emailTF;
	private JDateChooser fechaNacimientoDC;
	private JComboBox<Genero> generoCB;
	private JComboBox<EstadoCliente> estadoClienteCB;

	private JComboBox<Pais> paisCB;
	private JComboBox<Provincia> provinciaCB;
	private JComboBox<Ciudad> ciudadCB;
	private JTextField calleTF;
	private JTextField numeroTF;
	private JTextField pisoTF;
	private JTextField puertaTF;
	private JTextField codigoPostalTF;

	private boolean editable = false;
	private JButton guardarButton;

	public ClienteAltaView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initServices() {
		clienteService = new ClienteServiceImpl();
		paisService = new PaisServiceImpl();
		provinciaService = new ProvinciaServiceImpl();
		ciudadService = new CiudadServiceImpl();
		direccionService = new DireccionServiceImpl();
		generoService = new GeneroServiceImpl();
		estadoClienteService = new EstadoClienteServiceImpl();
	}

	private void initialize() {
		setName("Alta de cliente");
		setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
		gbl.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		formPanel.setLayout(gbl);

		addLabel(formPanel, "Nombre:", 0, 0);
		nombreTF = addTextField(formPanel, 1, 0);
		addLabel(formPanel, "Primer Apellido:", 2, 0);
		apellido1TF = addTextField(formPanel, 3, 0);

		addLabel(formPanel, "Segundo Apellido:", 0, 1);
		apellido2TF = addTextField(formPanel, 1, 1);
		addLabel(formPanel, "DNI:", 2, 1);
		dniTF = addTextField(formPanel, 3, 1);

		addLabel(formPanel, "Teléfono:", 0, 2);
		telefonoTF = addTextField(formPanel, 1, 2);
		addLabel(formPanel, "Email:", 2, 2);
		emailTF = addTextField(formPanel, 3, 2);

		addLabel(formPanel, "Fecha Nacimiento:", 0, 3);
		fechaNacimientoDC = addDateChooser(formPanel, 1, 3);
		addLabel(formPanel, "Género:", 2, 3);
		generoCB = addCombo(formPanel, 3, 3);

		addLabel(formPanel, "Estado Cliente:", 0, 4);
		estadoClienteCB = addCombo(formPanel, 1, 4);

		addLabel(formPanel, "País:", 0, 5);
		paisCB = addCombo(formPanel, 1, 5);
		paisCB.addActionListener(e -> cargarProvincias());

		addLabel(formPanel, "Provincia:", 2, 5);
		provinciaCB = addCombo(formPanel, 3, 5);
		provinciaCB.addActionListener(e -> cargarCiudades());

		addLabel(formPanel, "Ciudad:", 0, 6);
		ciudadCB = addCombo(formPanel, 1, 6);

		addLabel(formPanel, "Calle:", 0, 7);
		calleTF = addTextField(formPanel, 1, 7);
		addLabel(formPanel, "Número:", 2, 7);
		numeroTF = addTextField(formPanel, 3, 7);

		addLabel(formPanel, "Piso / Bloque:", 0, 8);
		pisoTF = addTextField(formPanel, 1, 8);
		addLabel(formPanel, "Puerta / Apartamento:", 2, 8);
		puertaTF = addTextField(formPanel, 3, 8);

		addLabel(formPanel, "Código Postal:", 0, 9);
		codigoPostalTF = addTextField(formPanel, 1, 9);

		JScrollPane scrollPane = new JScrollPane(formPanel);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(scrollPane, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		guardarButton = new JButton("Guardar");
		guardarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1511_mount_zip_mount_zip.png")));
		guardarButton.addActionListener(e -> crearCliente());
		buttonPanel.add(guardarButton);

		JButton limpiarButton = new JButton("Limpiar");
		limpiarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png")));
		limpiarButton.addActionListener(e -> limpiarCampos());
		buttonPanel.add(limpiarButton);

		add(buttonPanel, BorderLayout.SOUTH);
	}

	private void postInitialize() {
		cargarGeneros();
		cargarEstadosCliente();
		cargarPaises();
	}

	private void cargarGeneros() {
		try {
			List<Genero> generos = generoService.findAll();
			DefaultComboBoxModel<Genero> model = new DefaultComboBoxModel<>();
			Genero placeholder = new Genero();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (Genero g : generos)
				model.addElement(g);
			generoCB.setModel(model);
			generoCB.setRenderer(new GeneroCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarEstadosCliente() {
		try {
			List<EstadoCliente> estados = estadoClienteService.findAll();
			DefaultComboBoxModel<EstadoCliente> model = new DefaultComboBoxModel<>();
			EstadoCliente placeholder = new EstadoCliente();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (EstadoCliente ec : estados)
				model.addElement(ec);
			estadoClienteCB.setModel(model);
			estadoClienteCB.setRenderer(new EstadoClienteCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarPaises() {
		try {
			List<Pais> paises = paisService.findAll();
			DefaultComboBoxModel<Pais> model = new DefaultComboBoxModel<>();
			Pais placeholder = new Pais();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar país");
			model.addElement(placeholder);
			for (Pais p : paises)
				model.addElement(p);
			paisCB.setModel(model);
			paisCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
				public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
						int index, boolean isSelected, boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					if (value instanceof Pais)
						setText(((Pais) value).getNombre());
					return this;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error cargando países", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarProvincias() {
		Pais pais = (Pais) paisCB.getSelectedItem();
		if (pais == null || pais.getId() == null) {
			provinciaCB.setModel(new DefaultComboBoxModel<>());
			ciudadCB.setModel(new DefaultComboBoxModel<>());
			return;
		}
		try {
			List<Provincia> provincias = provinciaService.findByPais(pais.getId());
			DefaultComboBoxModel<Provincia> model = new DefaultComboBoxModel<>();
			Provincia placeholder = new Provincia();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar provincia");
			model.addElement(placeholder);
			for (Provincia p : provincias)
				model.addElement(p);
			provinciaCB.setModel(model);
			provinciaCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
				public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
						int index, boolean isSelected, boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					if (value instanceof Provincia)
						setText(((Provincia) value).getNombre());
					return this;
				}
			});
			ciudadCB.setModel(new DefaultComboBoxModel<>());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error cargando provincias", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarCiudades() {
		Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
		if (provincia == null || provincia.getId() == null) {
			ciudadCB.setModel(new DefaultComboBoxModel<>());
			return;
		}
		try {
			List<Ciudad> ciudades = ciudadService.findByProvinciaId(provincia.getId());
			DefaultComboBoxModel<Ciudad> model = new DefaultComboBoxModel<>();
			Ciudad placeholder = new Ciudad();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar ciudad");
			model.addElement(placeholder);
			for (Ciudad c : ciudades)
				model.addElement(c);
			ciudadCB.setModel(model);
			ciudadCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
				public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
						int index, boolean isSelected, boolean cellHasFocus) {
					super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					if (value instanceof Ciudad)
						setText(((Ciudad) value).getNombre());
					return this;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error cargando ciudades", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void crearCliente() {
		try {
			Pais pais = (Pais) paisCB.getSelectedItem();
			Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
			Ciudad ciudad = (Ciudad) ciudadCB.getSelectedItem();

			if (pais == null || pais.getId() == null || provincia == null || provincia.getId() == null || ciudad == null
					|| ciudad.getId() == null) {
				JOptionPane.showMessageDialog(this, "Debe seleccionar país, provincia y ciudad.",
						"Dirección incompleta", JOptionPane.WARNING_MESSAGE);
				return;
			}

			Direccion direccion = new Direccion();
			direccion.setCalle(calleTF.getText().trim());
			direccion.setNumero(numeroTF.getText().trim());
			direccion.setPiso(pisoTF.getText().trim());
			direccion.setPuerta(puertaTF.getText().trim());
			direccion.setCodigoPostal(codigoPostalTF.getText().trim());
			direccion.setCiudadId(ciudad.getId());

			Direccion direccionGuardada = direccionService.create(direccion);
			if (direccionGuardada == null || direccionGuardada.getId() == null) {
				JOptionPane.showMessageDialog(this, "Error al guardar la dirección", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			Cliente cliente = getClienteFromForm();
			if (cliente == null)
				return;

			cliente.setDireccionId(direccionGuardada.getId());

			Long idCliente = clienteService.create(cliente);
			if (idCliente != null) {
				JOptionPane.showMessageDialog(this, "Cliente creado correctamente con ID: " + idCliente);
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(this, "Error al crear el cliente", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	public Cliente getClienteFromForm() {
		if (nombreTF.getText().trim().isEmpty() || apellido1TF.getText().trim().isEmpty()
				|| dniTF.getText().trim().isEmpty() || emailTF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Nombre, primer apellido, DNI y email son obligatorios.",
					"Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return null;
		}

		Cliente cliente = new Cliente();
		cliente.setNombre(nombreTF.getText().trim());
		cliente.setApellido1(apellido1TF.getText().trim());
		cliente.setApellido2(apellido2TF.getText().trim());
		cliente.setDni(dniTF.getText().trim());
		cliente.setTelefono(telefonoTF.getText().trim());
		cliente.setEmail(emailTF.getText().trim());
		cliente.setFechaNacimiento(fechaNacimientoDC.getDate());
		cliente.setContrasena("generar-password");

		Genero genero = (Genero) generoCB.getSelectedItem();
		if (genero != null && genero.getId() != null) {
			cliente.setGeneroId(genero.getId());
		} else {
			cliente.setGeneroId(1L);
		}

		EstadoCliente estado = (EstadoCliente) estadoClienteCB.getSelectedItem();
		if (estado != null && estado.getId() != null) {
			cliente.setEstadoClienteId(estado.getId());
		} else {
			cliente.setEstadoClienteId(1L);
		}

		if (cliente.getEmpleadoAsignadoId() == null) {
			cliente.setEmpleadoAsignadoId(1L);
		}

		Pais pais = (Pais) paisCB.getSelectedItem();
		Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
		Ciudad ciudad = (Ciudad) ciudadCB.getSelectedItem();

		if (pais != null && pais.getId() != null && provincia != null && provincia.getId() != null && ciudad != null
				&& ciudad.getId() != null) {
			Direccion direccion = new Direccion();
			direccion.setCalle(calleTF.getText().trim());
			direccion.setNumero(numeroTF.getText().trim());
			direccion.setPiso(pisoTF.getText().trim());
			direccion.setPuerta(puertaTF.getText().trim());
			direccion.setCodigoPostal(codigoPostalTF.getText().trim());
			direccion.setCiudadId(ciudad.getId());
			cliente.setDireccion(direccion);
		}

		return cliente;
	}

	public Direccion getDireccionFromForm() {
		Pais pais = (Pais) paisCB.getSelectedItem();
		Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
		Ciudad ciudad = (Ciudad) ciudadCB.getSelectedItem();

		if (pais == null || pais.getId() == null || provincia == null || provincia.getId() == null || ciudad == null
				|| ciudad.getId() == null) {
			return null;
		}

		Direccion direccion = new Direccion();
		direccion.setCalle(calleTF.getText().trim());
		direccion.setNumero(numeroTF.getText().trim());
		direccion.setPiso(pisoTF.getText().trim());
		direccion.setPuerta(puertaTF.getText().trim());
		direccion.setCodigoPostal(codigoPostalTF.getText().trim());
		direccion.setCiudadId(ciudad.getId());

		return direccion;
	}

	public void limpiarCampos() {
		nombreTF.setText("");
		apellido1TF.setText("");
		apellido2TF.setText("");
		dniTF.setText("");
		telefonoTF.setText("");
		emailTF.setText("");
		fechaNacimientoDC.setDate(null);
		generoCB.setSelectedIndex(0);
		estadoClienteCB.setSelectedIndex(0);
		calleTF.setText("");
		numeroTF.setText("");
		pisoTF.setText("");
		puertaTF.setText("");
		codigoPostalTF.setText("");
		paisCB.setSelectedIndex(0);
		provinciaCB.setModel(new DefaultComboBoxModel<>());
		ciudadCB.setModel(new DefaultComboBoxModel<>());
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
		nombreTF.setEditable(editable);
		apellido1TF.setEditable(editable);
		apellido2TF.setEditable(editable);
		dniTF.setEditable(editable);
		telefonoTF.setEditable(editable);
		emailTF.setEditable(editable);
		calleTF.setEditable(editable);
		numeroTF.setEditable(editable);
		pisoTF.setEditable(editable);
		puertaTF.setEditable(editable);
		codigoPostalTF.setEditable(editable);
		fechaNacimientoDC.setEnabled(editable);
		generoCB.setEnabled(editable);
		estadoClienteCB.setEnabled(editable);
		paisCB.setEnabled(editable);
		provinciaCB.setEnabled(editable);
		ciudadCB.setEnabled(editable);

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

	public void cargarCliente(ClienteDTO cliente) {
		nombreTF.setText(cliente.getNombre() != null ? cliente.getNombre() : "");
		apellido1TF.setText(cliente.getApellido1() != null ? cliente.getApellido1() : "");
		apellido2TF.setText(cliente.getApellido2() != null ? cliente.getApellido2() : "");
		dniTF.setText(cliente.getDni() != null ? cliente.getDni() : "");
		telefonoTF.setText(cliente.getTelefono() != null ? cliente.getTelefono() : "");
		emailTF.setText(cliente.getEmail() != null ? cliente.getEmail() : "");
		fechaNacimientoDC.setDate(cliente.getFechaNacimiento());

		if (cliente.getGeneroId() != null) {
			for (int i = 0; i < generoCB.getItemCount(); i++) {
				Genero g = generoCB.getItemAt(i);
				if (g.getId() != null && g.getId().equals(cliente.getGeneroId())) {
					generoCB.setSelectedIndex(i);
					break;
				}
			}
		}

		if (cliente.getEstadoClienteId() != null) {
			for (int i = 0; i < estadoClienteCB.getItemCount(); i++) {
				EstadoCliente ec = estadoClienteCB.getItemAt(i);
				if (ec.getId() != null && ec.getId().equals(cliente.getEstadoClienteId())) {
					estadoClienteCB.setSelectedIndex(i);
					break;
				}
			}
		}

		if (cliente.getDireccionId() != null) {
			try {
				Direccion direccion = direccionService.findById(cliente.getDireccionId());
				if (direccion != null) {
					calleTF.setText(direccion.getCalle() != null ? direccion.getCalle() : "");
					numeroTF.setText(direccion.getNumero() != null ? direccion.getNumero() : "");
					pisoTF.setText(direccion.getPiso() != null ? direccion.getPiso() : "");
					puertaTF.setText(direccion.getPuerta() != null ? direccion.getPuerta() : "");
					codigoPostalTF.setText(direccion.getCodigoPostal() != null ? direccion.getCodigoPostal() : "");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}

	public void mostrarError(String error) {
		JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
	}

	private void addLabel(JPanel panel, String text, int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(new JLabel(text), gbc);
	}

	private JTextField addTextField(JPanel panel, int x, int y) {
		JTextField field = new JTextField();
		field.setColumns(12);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(field, gbc);
		return field;
	}

	private JDateChooser addDateChooser(JPanel panel, int x, int y) {
		JDateChooser chooser = new JDateChooser();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(chooser, gbc);
		return chooser;
	}
	
	private <T> JComboBox<T> addCombo(JPanel panel, int x, int y) {
		JComboBox<T> combo = new JComboBox<>();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = x;
		gbc.gridy = y;
		panel.add(combo, gbc);
		return combo;
	}
}