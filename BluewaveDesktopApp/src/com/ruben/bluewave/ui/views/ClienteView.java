//package com.ruben.bluewave.ui.views;
//
//import java.awt.BorderLayout;
//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
//import java.awt.Insets;
//import java.util.List;
//
//import javax.swing.DefaultComboBoxModel;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.JTextField;
//
//import org.apache.commons.lang3.StringUtils;
//
//import com.ruben.bluewave.model.Ciudad;
//import com.ruben.bluewave.model.ClienteDTO;
//import com.ruben.bluewave.model.Direccion;
//import com.ruben.bluewave.model.EstadoCliente;
//import com.ruben.bluewave.model.Genero;
//import com.ruben.bluewave.model.Pais;
//import com.ruben.bluewave.model.Provincia;
//import com.ruben.bluewave.service.CiudadService;
//import com.ruben.bluewave.service.ClienteService;
//import com.ruben.bluewave.service.EstadoClienteService;
//import com.ruben.bluewave.service.GeneroService;
//import com.ruben.bluewave.service.PaisService;
//import com.ruben.bluewave.service.ProvinciaService;
//import com.ruben.bluewave.service.impl.CiudadServiceImpl;
//import com.ruben.bluewave.service.impl.ClienteServiceImpl;
//import com.ruben.bluewave.service.impl.EstadoClienteServiceImpl;
//import com.ruben.bluewave.service.impl.GeneroServiceImpl;
//import com.ruben.bluewave.service.impl.PaisServiceImpl;
//import com.ruben.bluewave.service.impl.ProvinciaServiceImpl;
//import com.ruben.bluewave.ui.controller.AbstractController;
//import com.ruben.bluewave.ui.controller.CancelController;
//import com.ruben.bluewave.ui.controller.ClienteCreateController;
//import com.ruben.bluewave.ui.controller.Controller;
//import com.ruben.bluewave.ui.renderer.EstadoClienteCBRenderer;
//import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
//
//public class ClienteView extends AbstractView {
//
//	private JTextField nombreTF;
//	private JTextField apellido1TF;
//	private JTextField apellido2TF;
//	private JTextField emailTF;
//	private JTextField dniTF;
//	private JTextField telefonoTF;
//
//	private JComboBox<Pais> paisCB;
//	private JComboBox<Provincia> provinciaCB;
//	private JComboBox<Ciudad> ciudadCB;
//	private JComboBox<Genero> generoCB;
//	private JComboBox<EstadoCliente> estadoCB;
//
//	private JButton agreeButton;
//	private JButton cancelButton;
//
//	// Servicios
//	private PaisService paisService;
//	private ProvinciaService provinciaService;
//	private CiudadService ciudadService;
//	private GeneroService generoService;
//	private EstadoClienteService estadoService;
//	private ClienteService clienteService;
//
//	public ClienteView() {
//		initServices();
//		initialize();
//		postInitialize();
//	}
//
//	private void initialize() {
//		setName("Nuevo Cliente");
//		setLayout(new BorderLayout());
//
//		JPanel contentPanel = new JPanel();
//		add(contentPanel, BorderLayout.NORTH);
//
//		GridBagLayout gbl = new GridBagLayout();
//		gbl.columnWidths = new int[] { 0, 0, 0, 0 };
//		gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
//		contentPanel.setLayout(gbl);
//
//		GridBagConstraints gbc;
//
//		// Nombre
//		gbc = new GridBagConstraints();
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		gbc.insets = new Insets(5, 5, 5, 5);
//		contentPanel.add(new JLabel("Nombre:"), gbc);
//
//		nombreTF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(nombreTF, gbc);
//
//		// Apellido 1
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		contentPanel.add(new JLabel("Apellido 1:"), gbc);
//
//		apellido1TF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(apellido1TF, gbc);
//
//		// Apellido 2
//		gbc.gridx = 0;
//		gbc.gridy = 2;
//		contentPanel.add(new JLabel("Apellido 2:"), gbc);
//
//		apellido2TF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(apellido2TF, gbc);
//
//		// Email
//		gbc.gridx = 0;
//		gbc.gridy = 3;
//		contentPanel.add(new JLabel("Email:"), gbc);
//
//		emailTF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(emailTF, gbc);
//
//		// DNI
//		gbc.gridx = 0;
//		gbc.gridy = 4;
//		contentPanel.add(new JLabel("DNI:"), gbc);
//
//		dniTF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(dniTF, gbc);
//
//		// Teléfono
//		gbc.gridx = 0;
//		gbc.gridy = 5;
//		contentPanel.add(new JLabel("Teléfono:"), gbc);
//
//		telefonoTF = new JTextField(15);
//		gbc.gridx = 1;
//		contentPanel.add(telefonoTF, gbc);
//
//		// Género
//		gbc.gridx = 0;
//		gbc.gridy = 6;
//		contentPanel.add(new JLabel("Género:"), gbc);
//
//		generoCB = new JComboBox<>();
//		gbc.gridx = 1;
//		contentPanel.add(generoCB, gbc);
//
//		// Estado
//		gbc.gridx = 0;
//		gbc.gridy = 7;
//		contentPanel.add(new JLabel("Estado:"), gbc);
//
//		estadoCB = new JComboBox<>();
//		gbc.gridx = 1;
//		contentPanel.add(estadoCB, gbc);
//
//		// Panel botones
//		JPanel buttons = new JPanel();
//		add(buttons, BorderLayout.SOUTH);
//
//		agreeButton = new JButton("Guardar");
//		agreeButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1339_kmag_kmag.png")));
//		buttons.add(agreeButton);
//
//		cancelButton = new JButton("Cancelar");
//		cancelButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1339_kmag_kmag.png")));
//		buttons.add(cancelButton);
//
//	}
//
//	private void postInitialize() {
//
//		generoCB.setRenderer(new GeneroCBRenderer());
//		estadoCB.setRenderer(new EstadoClienteCBRenderer());
//
//		cargarGeneros();
//		cargarEstados();
//
//		agreeButton.setAction(new ClienteCreateController(this));
//		cancelButton.setAction(new CancelController(this));
//		setEditable(true);
//	}
//
//	private void cargarGeneros() {
//		List<Genero> lista = null;
//		try {
//			lista = generoService.findAll();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		DefaultComboBoxModel<Genero> model = new DefaultComboBoxModel<>();
//
//		Genero placeholder = new Genero();
//		placeholder.setId(null);
//		placeholder.setNombre("Seleccione género");
//		model.addElement(placeholder);
//
//		for (Genero g : lista)
//			model.addElement(g);
//
//		generoCB.setModel(model);
//	}
//
//	private void cargarEstados() {
//		List<EstadoCliente> lista = null;
//		try {
//			lista = estadoService.findAll();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		DefaultComboBoxModel<EstadoCliente> model = new DefaultComboBoxModel<>();
//
//		EstadoCliente placeholder = new EstadoCliente();
//		placeholder.setId(null);
//		placeholder.setNombre("Seleccione estado");
//		model.addElement(placeholder);
//
//		for (EstadoCliente e : lista)
//			model.addElement(e);
//
//		estadoCB.setModel(model);
//	}
//
//	private void initServices() {
//		paisService = new PaisServiceImpl();
//		provinciaService = new ProvinciaServiceImpl();
//		ciudadService = new CiudadServiceImpl();
//		generoService = new GeneroServiceImpl();
//		estadoService = new EstadoClienteServiceImpl();
//		clienteService = new ClienteServiceImpl();
//	}
//
//	public ClienteDTO getModel() {
//		ClienteDTO c = new ClienteDTO();
//
//		c.setNombre(StringUtils.trimToNull(nombreTF.getText()));
//		c.setApellido1(StringUtils.trimToNull(apellido1TF.getText()));
//		c.setApellido2(StringUtils.trimToNull(apellido2TF.getText()));
//		c.setEmail(StringUtils.trimToNull(emailTF.getText()));
//		c.setDni(StringUtils.trimToNull(dniTF.getText()));
//		c.setTelefono(StringUtils.trimToNull(telefonoTF.getText()));
//
//		Genero g = (Genero) generoCB.getSelectedItem();
//		if (g != null && g.getId() != null) {
//			c.setGeneroId(g.getId());
//			c.setGeneroNombre(g.getNombre());
//		}
//
//		EstadoCliente e = (EstadoCliente) estadoCB.getSelectedItem();
//		if (e != null && e.getId() != null) {
//			c.setEstadoClienteId(e.getId());
//			c.setEstadoClienteNombre(e.getNombre());
//		}
//		
//		Pais p = (Pais) paisCB.getSelectedItem();
//		if (p != null && p.getId() != null) {
//		    c.setPaisId(p.getId());
//		    c.setPaisNombre(p.getNombre());
//		}
//
//		Provincia pr = (Provincia) provinciaCB.getSelectedItem();
//		if (pr != null && pr.getId() != null) {
//		    c.setProvinciaId(pr.getId());
//		    c.setProvinciaNombre(pr.getNombre());
//		}
//
//		Ciudad ci = (Ciudad) ciudadCB.getSelectedItem();
//		if (ci != null && ci.getId() != null) {
//		    c.setCiudadId(ci.getId());
//		    c.setCiudadNombre(ci.getNombre());
//		}
//		return c;
//	}
//
//	public void setEditable(boolean editable) {
//		nombreTF.setEditable(editable);
//		apellido1TF.setEditable(editable);
//		apellido2TF.setEditable(editable);
//		emailTF.setEditable(editable);
//		dniTF.setEditable(editable);
//		telefonoTF.setEditable(editable);
//		generoCB.setEnabled(editable);
//		estadoCB.setEnabled(editable);
//	}
//
//	public void setAgreeController(AbstractController c) {
//		agreeButton.setAction(c);
//	}
//
//	public void setDisagreeController(AbstractController c) {
//		cancelButton.setAction(c);
//	}
//}