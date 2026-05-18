package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ruben.bluewave.model.Ciudad;
import com.ruben.bluewave.model.Cliente;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.service.CiudadService;
import com.ruben.bluewave.service.ClienteService;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.service.PaisService;
import com.ruben.bluewave.service.ProvinciaService;
import com.ruben.bluewave.service.impl.CiudadServiceImpl;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;
import com.ruben.bluewave.service.impl.PaisServiceImpl;
import com.ruben.bluewave.service.impl.ProvinciaServiceImpl;
import com.ruben.bluewave.ui.renderer.CiudadCBRenderer;
import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
import com.ruben.bluewave.ui.renderer.PaisCBRenderer;
import com.ruben.bluewave.ui.renderer.ProvinciaCBRenderer;
import com.toedter.calendar.JDateChooser;

public class NuevoClienteView extends AbstractView {
	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField direccionTF;
	private JTextField emailTF;
	private JTextField dniTF;
	private JTextField apellido2TF;
	private JTextField direccionCalleTF;
	private JTextField numeroPisoTF;
	private JTextField telefonoTF;
	private JTextField postalTF;

	private JComboBox<Pais> paisCB;
	private JComboBox<Provincia> provinciaCB;
	private JComboBox<Ciudad> ciudadCB;
	private JComboBox<Genero> generoCB;

	private JDateChooser fechaNacimientoDateChooser;

	private PaisService paisService;
	private ProvinciaService provinciaService;
	private CiudadService ciudadService;
	private GeneroService generoService;
	private ClienteService clienteService;

	public NuevoClienteView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initialize() {
		setName("Nuevo cliente");
		setLayout(new BorderLayout(0, 0));

		JPanel contentPanel = new JPanel();
		add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JPanel formPanel = new JPanel();
		contentPanel.add(formPanel, BorderLayout.WEST);
		GridBagLayout gbl_formPanel = new GridBagLayout();
		gbl_formPanel.columnWidths = new int[] { 0, 146, 120, -261, 0, 0, 0, 0, 0 };
		gbl_formPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_formPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_formPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		formPanel.setLayout(gbl_formPanel);

		JLabel nombreLabel = new JLabel("Nombre:");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.anchor = GridBagConstraints.EAST;
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 0;
		gbc_nombreLabel.gridy = 0;
		formPanel.add(nombreLabel, gbc_nombreLabel);

		nombreTF = new JTextField();
		nombreTF.setBackground(new Color(255, 250, 205));
		nombreTF.setColumns(10);
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.gridx = 1;
		gbc_nombreTF.gridy = 0;
		formPanel.add(nombreTF, gbc_nombreTF);

		JLabel apellido1Label = new JLabel("Primer apellido:");
		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
		gbc_apellido1Label.anchor = GridBagConstraints.EAST;
		gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Label.gridx = 2;
		gbc_apellido1Label.gridy = 0;
		formPanel.add(apellido1Label, gbc_apellido1Label);

		apellido1TF = new JTextField();
		apellido1TF.setBackground(new Color(255, 250, 205));
		apellido1TF.setColumns(10);
		GridBagConstraints gbc_apellido1TF = new GridBagConstraints();
		gbc_apellido1TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TF.gridx = 3;
		gbc_apellido1TF.gridy = 0;
		formPanel.add(apellido1TF, gbc_apellido1TF);

		JLabel apellido2Label = new JLabel("Segundo apellido:");
		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
		gbc_apellido2Label.anchor = GridBagConstraints.EAST;
		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.gridx = 4;
		gbc_apellido2Label.gridy = 0;
		formPanel.add(apellido2Label, gbc_apellido2Label);

		apellido2TF = new JTextField();
		GridBagConstraints gbc_apellido2TF = new GridBagConstraints();
		gbc_apellido2TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TF.gridx = 5;
		gbc_apellido2TF.gridy = 0;
		formPanel.add(apellido2TF, gbc_apellido2TF);
		apellido2TF.setColumns(10);

		JLabel emailLabel = new JLabel("E-Mail:");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.anchor = GridBagConstraints.EAST;
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 0;
		gbc_emailLabel.gridy = 1;
		formPanel.add(emailLabel, gbc_emailLabel);

		emailTF = new JTextField();
		emailTF.setBackground(new Color(255, 250, 205));
		GridBagConstraints gbc_emailTF = new GridBagConstraints();
		gbc_emailTF.insets = new Insets(0, 0, 5, 5);
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.gridx = 1;
		gbc_emailTF.gridy = 1;
		formPanel.add(emailTF, gbc_emailTF);
		emailTF.setColumns(10);

		JLabel dniLabel = new JLabel("DNI:");
		GridBagConstraints gbc_dniLabel = new GridBagConstraints();
		gbc_dniLabel.anchor = GridBagConstraints.EAST;
		gbc_dniLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dniLabel.gridx = 2;
		gbc_dniLabel.gridy = 1;
		formPanel.add(dniLabel, gbc_dniLabel);

		dniTF = new JTextField();
		dniTF.setBackground(new Color(255, 250, 205));
		GridBagConstraints gbc_dniTF = new GridBagConstraints();
		gbc_dniTF.insets = new Insets(0, 0, 5, 5);
		gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniTF.gridx = 3;
		gbc_dniTF.gridy = 1;
		formPanel.add(dniTF, gbc_dniTF);
		dniTF.setColumns(10);

		JLabel paisLabel = new JLabel("País:");
		GridBagConstraints gbc_paisLabel = new GridBagConstraints();
		gbc_paisLabel.anchor = GridBagConstraints.EAST;
		gbc_paisLabel.insets = new Insets(0, 0, 5, 5);
		gbc_paisLabel.gridx = 0;
		gbc_paisLabel.gridy = 2;
		formPanel.add(paisLabel, gbc_paisLabel);

		paisCB = new JComboBox<>();
		GridBagConstraints gbc_paisCB = new GridBagConstraints();
		gbc_paisCB.insets = new Insets(0, 0, 5, 5);
		gbc_paisCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_paisCB.gridx = 1;
		gbc_paisCB.gridy = 2;
		formPanel.add(paisCB, gbc_paisCB);

		JLabel provinciaLabel = new JLabel("Provincia:");
		GridBagConstraints gbc_provinciaLabel = new GridBagConstraints();
		gbc_provinciaLabel.anchor = GridBagConstraints.EAST;
		gbc_provinciaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaLabel.gridx = 2;
		gbc_provinciaLabel.gridy = 2;
		formPanel.add(provinciaLabel, gbc_provinciaLabel);

		provinciaCB = new JComboBox<>();
		GridBagConstraints gbc_provinciaCB = new GridBagConstraints();
		gbc_provinciaCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_provinciaCB.insets = new Insets(0, 0, 5, 5);
		gbc_provinciaCB.gridx = 3;
		gbc_provinciaCB.gridy = 2;
		formPanel.add(provinciaCB, gbc_provinciaCB);

		JLabel ciudadLabel = new JLabel("Ciudad:");
		GridBagConstraints gbc_ciudadLabel = new GridBagConstraints();
		gbc_ciudadLabel.anchor = GridBagConstraints.EAST;
		gbc_ciudadLabel.insets = new Insets(0, 0, 5, 5);
		gbc_ciudadLabel.gridx = 4;
		gbc_ciudadLabel.gridy = 2;
		formPanel.add(ciudadLabel, gbc_ciudadLabel);

		ciudadCB = new JComboBox<>();
		GridBagConstraints gbc_ciudadCB = new GridBagConstraints();
		gbc_ciudadCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_ciudadCB.insets = new Insets(0, 0, 5, 0);
		gbc_ciudadCB.gridx = 5;
		gbc_ciudadCB.gridy = 2;
		formPanel.add(ciudadCB, gbc_ciudadCB);

		JLabel direccionLabel = new JLabel("Dirección:");
		GridBagConstraints gbc_direccionLabel = new GridBagConstraints();
		gbc_direccionLabel.anchor = GridBagConstraints.EAST;
		gbc_direccionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionLabel.gridx = 0;
		gbc_direccionLabel.gridy = 3;
		formPanel.add(direccionLabel, gbc_direccionLabel);

		direccionTF = new JTextField();
		direccionTF.setColumns(10);
		GridBagConstraints gbc_direccionTF = new GridBagConstraints();
		gbc_direccionTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionTF.insets = new Insets(0, 0, 5, 5);
		gbc_direccionTF.gridx = 1;
		gbc_direccionTF.gridy = 3;
		formPanel.add(direccionTF, gbc_direccionTF);

		JLabel direccionCalleLabel = new JLabel("Calle:");
		GridBagConstraints gbc_direccionCalleLabel = new GridBagConstraints();
		gbc_direccionCalleLabel.anchor = GridBagConstraints.EAST;
		gbc_direccionCalleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionCalleLabel.gridx = 2;
		gbc_direccionCalleLabel.gridy = 3;
		formPanel.add(direccionCalleLabel, gbc_direccionCalleLabel);

		direccionCalleTF = new JTextField();
		GridBagConstraints gbc_direccionCalleTF = new GridBagConstraints();
		gbc_direccionCalleTF.insets = new Insets(0, 0, 5, 5);
		gbc_direccionCalleTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_direccionCalleTF.gridx = 3;
		gbc_direccionCalleTF.gridy = 3;
		formPanel.add(direccionCalleTF, gbc_direccionCalleTF);
		direccionCalleTF.setColumns(10);

		JLabel direccionNumeroLabel = new JLabel("Nº:");
		GridBagConstraints gbc_direccionNumeroLabel = new GridBagConstraints();
		gbc_direccionNumeroLabel.anchor = GridBagConstraints.EAST;
		gbc_direccionNumeroLabel.insets = new Insets(0, 0, 5, 5);
		gbc_direccionNumeroLabel.gridx = 4;
		gbc_direccionNumeroLabel.gridy = 3;
		formPanel.add(direccionNumeroLabel, gbc_direccionNumeroLabel);

		numeroPisoTF = new JTextField();
		GridBagConstraints gbc_numeroPisoTF = new GridBagConstraints();
		gbc_numeroPisoTF.insets = new Insets(0, 0, 5, 5);
		gbc_numeroPisoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_numeroPisoTF.gridx = 5;
		gbc_numeroPisoTF.gridy = 3;
		formPanel.add(numeroPisoTF, gbc_numeroPisoTF);
		numeroPisoTF.setColumns(10);

		JLabel CodigoPostalLabel = new JLabel("Código Postal:");
		GridBagConstraints gbc_CodigoPostalLabel = new GridBagConstraints();
		gbc_CodigoPostalLabel.anchor = GridBagConstraints.EAST;
		gbc_CodigoPostalLabel.insets = new Insets(0, 0, 5, 5);
		gbc_CodigoPostalLabel.gridx = 0;
		gbc_CodigoPostalLabel.gridy = 4;
		formPanel.add(CodigoPostalLabel, gbc_CodigoPostalLabel);

		postalTF = new JTextField();
		GridBagConstraints gbc_postalTF = new GridBagConstraints();
		gbc_postalTF.insets = new Insets(0, 0, 5, 5);
		gbc_postalTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_postalTF.gridx = 1;
		gbc_postalTF.gridy = 4;
		formPanel.add(postalTF, gbc_postalTF);
		postalTF.setColumns(10);

	
		JLabel fechaNacimientoLabel = new JLabel("Fecha nacimiento:");
		GridBagConstraints gbc_fechaNacimientoLabel = new GridBagConstraints();
		gbc_fechaNacimientoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaNacimientoLabel.gridx = 0;
		gbc_fechaNacimientoLabel.gridy = 5;
		formPanel.add(fechaNacimientoLabel, gbc_fechaNacimientoLabel);

		fechaNacimientoDateChooser = new JDateChooser();
		GridBagConstraints gbc_fechaNacimientoDateChooser = new GridBagConstraints();
		gbc_fechaNacimientoDateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_fechaNacimientoDateChooser.fill = GridBagConstraints.HORIZONTAL;
		gbc_fechaNacimientoDateChooser.gridx = 1;
		gbc_fechaNacimientoDateChooser.gridy = 5;
		formPanel.add(fechaNacimientoDateChooser, gbc_fechaNacimientoDateChooser);

		JLabel GeneroLabel = new JLabel("Género:");
		GridBagConstraints gbc_GeneroLabel = new GridBagConstraints();
		gbc_GeneroLabel.anchor = GridBagConstraints.EAST;
		gbc_GeneroLabel.insets = new Insets(0, 0, 5, 5);
		gbc_GeneroLabel.gridx = 0;
		gbc_GeneroLabel.gridy = 6;
		formPanel.add(GeneroLabel, gbc_GeneroLabel);

		generoCB = new JComboBox<>();
		GridBagConstraints gbc_generoCB = new GridBagConstraints();
		gbc_generoCB.insets = new Insets(0, 0, 5, 5);
		gbc_generoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoCB.gridx = 1;
		gbc_generoCB.gridy = 6;
		formPanel.add(generoCB, gbc_generoCB);

		JLabel TelefonoLabel = new JLabel("Teléfono:");
		GridBagConstraints gbc_TelefonoLabel = new GridBagConstraints();
		gbc_TelefonoLabel.anchor = GridBagConstraints.EAST;
		gbc_TelefonoLabel.insets = new Insets(0, 0, 0, 5);
		gbc_TelefonoLabel.gridx = 0;
		gbc_TelefonoLabel.gridy = 7;
		formPanel.add(TelefonoLabel, gbc_TelefonoLabel);

		telefonoTF = new JTextField();
		GridBagConstraints gbc_telefonoTF = new GridBagConstraints();
		gbc_telefonoTF.insets = new Insets(0, 0, 0, 5);
		gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTF.gridx = 1;
		gbc_telefonoTF.gridy = 7;
		formPanel.add(telefonoTF, gbc_telefonoTF);
		telefonoTF.setColumns(10);

		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);

		JButton guardarButton = new JButton("Guardar");
		guardarButton.setIcon(
				new ImageIcon(NuevoClienteView.class.getResource("/nuvola/16x16/1849_spellcheck_spellcheck.png")));
		guardarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarCliente();
			}
		});
		buttonPanel.add(guardarButton);

		JButton cancelarButton = new JButton("Cancelar");
		cancelarButton
				.setIcon(new ImageIcon(NuevoClienteView.class.getResource("/nuvola/16x16/1250_delete_delete.png")));
		cancelarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarFormulario();
			}
		});
		buttonPanel.add(cancelarButton);
	}

	private void initServices() {
		paisService = new PaisServiceImpl();
		provinciaService = new ProvinciaServiceImpl();
		ciudadService = new CiudadServiceImpl();
		generoService = new GeneroServiceImpl();
		clienteService = new ClienteServiceImpl();
	}

	private void postInitialize() {
		cargarRenderer();
		cargarPaises();
		cargarGeneros();
		configurarListeners();
	}

	private void cargarPaises() {
		List<Pais> paises = null;
		try {
			paises = paisService.findAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		DefaultComboBoxModel<Pais> model = new DefaultComboBoxModel<>();
		model.addElement(null);
		for (Pais p : paises) {
			model.addElement(p);
		}
		paisCB.setModel(model);

	}

	private void cargarGeneros() {
		List<Genero> generos = null;
		try {
			generos = generoService.findAll();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		DefaultComboBoxModel<Genero> model = new DefaultComboBoxModel<>();
		model.addElement(null);
		for (Genero g : generos) {
			model.addElement(g);
		}
		generoCB.setModel(model);
	}

	private void configurarListeners() {
		paisCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Pais pais = (Pais) paisCB.getSelectedItem();
				cargarProvincias(pais != null ? pais.getId() : null);
			}
		});

		provinciaCB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
				cargarCiudades(provincia != null ? provincia.getId() : null);
			}
		});
	}

	private void cargarProvincias(Long paisId) {
		DefaultComboBoxModel<Provincia> model = new DefaultComboBoxModel<>();
		model.addElement(null);
		if (paisId != null) {
			List<Provincia> provincias = provinciaService.findByPais(paisId);
			for (Provincia p : provincias) {
				model.addElement(p);
			}
		}
		provinciaCB.setModel(model);

		cargarCiudades(null);
	}

	private void cargarCiudades(Long provinciaId) {
		DefaultComboBoxModel<Ciudad> model = new DefaultComboBoxModel<>();
		model.addElement(null);
		if (provinciaId != null) {
			List<Ciudad> ciudades = null;
			try {
				ciudades = ciudadService.findByProvinciaId(provinciaId);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			for (Ciudad c : ciudades) {
				model.addElement(c);
			}
		}
		ciudadCB.setModel(model);
	}

	private void guardarCliente() {
		
		
		
		if (nombreTF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El nombre es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (apellido1TF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El segunfdo apellido es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (dniTF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El DNI es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		if (emailTF.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El email es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Genero genero = (Genero) generoCB.getSelectedItem();
		if (genero == null) {
			JOptionPane.showMessageDialog(this, "El género es obligatorio", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Cliente cliente = new Cliente();
		cliente.setDni(dniTF.getText().trim());
		cliente.setNombre(nombreTF.getText().trim());
		cliente.setApellido1(apellido1TF.getText().trim());
		cliente.setApellido2(apellido2TF.getText().trim());
		cliente.setEmail(emailTF.getText().trim());
		cliente.setContrasena("abc123.");
		cliente.setTelefono(telefonoTF.getText().trim());
		cliente.setFechaNacimiento(fechaNacimientoDateChooser.getDate());
		cliente.setEstadoClienteId(1L);
		cliente.setDireccionId(1L);
		cliente.setEmpleadoAsignadoId(1L);
		cliente.setGeneroId(genero.getId());

		
		try {
			Long creado = clienteService.create(cliente);
			if (creado != null) {
				JOptionPane.showMessageDialog(this, "Cliente guardado correctamente", "Nuevo Cliente",
						JOptionPane.INFORMATION_MESSAGE);
				limpiarFormulario();
			} else {
				JOptionPane.showMessageDialog(this, "No se pudo guardar el cliente", "Nuevo Cliente",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		
		}
	}

	private void cargarRenderer() {
		generoCB.setRenderer(new GeneroCBRenderer());
		paisCB.setRenderer(new PaisCBRenderer());
		ciudadCB.setRenderer(new CiudadCBRenderer());
		provinciaCB.setRenderer(new ProvinciaCBRenderer());

	}

	private void limpiarFormulario() {
		nombreTF.setText("");
		apellido1TF.setText("");
		apellido2TF.setText("");
		emailTF.setText("");
		dniTF.setText("");
		direccionCalleTF.setText("");
		numeroPisoTF.setText("");
		direccionTF.setText("");
		postalTF.setText("");
		telefonoTF.setText("");
		fechaNacimientoDateChooser.setDate(null);
		paisCB.setSelectedIndex(0);
		provinciaCB.setSelectedIndex(0);
		ciudadCB.setSelectedIndex(0);
		generoCB.setSelectedIndex(0);
	}

}