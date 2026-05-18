package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.ruben.bluewave.dao.criteria.EmpleadoCriteria;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;
import com.ruben.bluewave.ui.controller.EmpleadoSearchController;
import com.ruben.bluewave.ui.model.EmpleadoTableModel;
import com.ruben.bluewave.ui.renderer.EmpleadoTableCellRenderer;
import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
import com.toedter.calendar.JDateChooser;

public class EmpleadoSearchView extends AbstractView {

	private EmpleadoService empleadoService;
	private GeneroService generoService;

	private JFormattedTextField idEmpleadoFTF;
	private JTextField nombreTF;
	private JTextField apellido1TF;
	private JTextField apellido2TF;
	private JTextField dniTF;
	private JTextField emailTF;
	private JTextField telefonoTF;

	private JDateChooser fechaContratacionDesdeDC;
	private JDateChooser fechaContratacionHastaDC;
	private JDateChooser fechaBajaDesdeDC;
	private JDateChooser fechaBajaHastaDC;

	// ComboBox de estado (activo/inactivo)
	private JComboBox<String> estadoCB;
	private JComboBox<Genero> generoCB;

	private JTable resultadosTable;
	private Results<EmpleadoDTO> fullModel;
	private int currentPage = 1;
	private int pageSize = 10;
	private int totalPages = 1;

	private JButton buscarButton;
	private JLabel totalResultadosLabel;
	private JButton firstButton, prevButton, nextButton, lastButton;
	private JLabel pageInfoLabel;

	public EmpleadoSearchView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initialize() {
		setName("Búsqueda de empleados");
		setLayout(new BorderLayout(0, 0));

		JPanel busquedaPanel = new JPanel();
		add(busquedaPanel, BorderLayout.NORTH);
		GridBagLayout gbl_busquedaPanel = new GridBagLayout();
		gbl_busquedaPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_busquedaPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_busquedaPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				0.0, Double.MIN_VALUE };
		gbl_busquedaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		busquedaPanel.setLayout(gbl_busquedaPanel);

		// Etiquetas primera fila
		JLabel idEmpleadoLabel = new JLabel("ID Empleado");
		GridBagConstraints gbc_idEmpleadoLabel = new GridBagConstraints();
		gbc_idEmpleadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idEmpleadoLabel.gridx = 1;
		gbc_idEmpleadoLabel.gridy = 0;
		busquedaPanel.add(idEmpleadoLabel, gbc_idEmpleadoLabel);

		JLabel nombreLabel = new JLabel("Nombre");
		GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
		gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nombreLabel.gridx = 2;
		gbc_nombreLabel.gridy = 0;
		busquedaPanel.add(nombreLabel, gbc_nombreLabel);

		JLabel apellido1Label = new JLabel("Apellido1");
		GridBagConstraints gbc_apellido1Label = new GridBagConstraints();
		gbc_apellido1Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1Label.gridx = 3;
		gbc_apellido1Label.gridy = 0;
		busquedaPanel.add(apellido1Label, gbc_apellido1Label);

		JLabel apellido2Label = new JLabel("Apellido2");
		GridBagConstraints gbc_apellido2Label = new GridBagConstraints();
		gbc_apellido2Label.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2Label.gridx = 4;
		gbc_apellido2Label.gridy = 0;
		busquedaPanel.add(apellido2Label, gbc_apellido2Label);

		JLabel dniLabel = new JLabel("DNI");
		GridBagConstraints gbc_dniLabel = new GridBagConstraints();
		gbc_dniLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dniLabel.gridx = 5;
		gbc_dniLabel.gridy = 0;
		busquedaPanel.add(dniLabel, gbc_dniLabel);

		JLabel emailLabel = new JLabel("Email");
		GridBagConstraints gbc_emailLabel = new GridBagConstraints();
		gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLabel.gridx = 6;
		gbc_emailLabel.gridy = 0;
		busquedaPanel.add(emailLabel, gbc_emailLabel);

		// Campos primera fila
		idEmpleadoFTF = new JFormattedTextField();
		GridBagConstraints gbc_idEmpleadoFTF = new GridBagConstraints();
		gbc_idEmpleadoFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idEmpleadoFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idEmpleadoFTF.gridx = 1;
		gbc_idEmpleadoFTF.gridy = 1;
		busquedaPanel.add(idEmpleadoFTF, gbc_idEmpleadoFTF);

		nombreTF = new JTextField(10);
		GridBagConstraints gbc_nombreTF = new GridBagConstraints();
		gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
		gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_nombreTF.gridx = 2;
		gbc_nombreTF.gridy = 1;
		busquedaPanel.add(nombreTF, gbc_nombreTF);

		apellido1TF = new JTextField(10);
		GridBagConstraints gbc_apellido1TF = new GridBagConstraints();
		gbc_apellido1TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido1TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido1TF.gridx = 3;
		gbc_apellido1TF.gridy = 1;
		busquedaPanel.add(apellido1TF, gbc_apellido1TF);

		apellido2TF = new JTextField(10);
		GridBagConstraints gbc_apellido2TF = new GridBagConstraints();
		gbc_apellido2TF.insets = new Insets(0, 0, 5, 5);
		gbc_apellido2TF.fill = GridBagConstraints.HORIZONTAL;
		gbc_apellido2TF.gridx = 4;
		gbc_apellido2TF.gridy = 1;
		busquedaPanel.add(apellido2TF, gbc_apellido2TF);

		dniTF = new JTextField(10);
		GridBagConstraints gbc_dniTF = new GridBagConstraints();
		gbc_dniTF.insets = new Insets(0, 0, 5, 5);
		gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_dniTF.gridx = 5;
		gbc_dniTF.gridy = 1;
		busquedaPanel.add(dniTF, gbc_dniTF);

		emailTF = new JTextField(15);
		GridBagConstraints gbc_emailTF = new GridBagConstraints();
		gbc_emailTF.insets = new Insets(0, 0, 5, 5);
		gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_emailTF.gridx = 6;
		gbc_emailTF.gridy = 1;
		busquedaPanel.add(emailTF, gbc_emailTF);

		// Segunda fila etiquetas
		JLabel telefonoLabel = new JLabel("Teléfono");
		GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
		gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoLabel.gridx = 1;
		gbc_telefonoLabel.gridy = 2;
		busquedaPanel.add(telefonoLabel, gbc_telefonoLabel);

		JLabel fechaContratacionDesdeLabel = new JLabel("Contratación desde");
		GridBagConstraints gbc_fechaContratacionDesdeLabel = new GridBagConstraints();
		gbc_fechaContratacionDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaContratacionDesdeLabel.gridx = 2;
		gbc_fechaContratacionDesdeLabel.gridy = 2;
		busquedaPanel.add(fechaContratacionDesdeLabel, gbc_fechaContratacionDesdeLabel);

		JLabel fechaContratacionHastaLabel = new JLabel("Contratación hasta");
		GridBagConstraints gbc_fechaContratacionHastaLabel = new GridBagConstraints();
		gbc_fechaContratacionHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaContratacionHastaLabel.gridx = 3;
		gbc_fechaContratacionHastaLabel.gridy = 2;
		busquedaPanel.add(fechaContratacionHastaLabel, gbc_fechaContratacionHastaLabel);

		JLabel fechaBajaDesdeLabel = new JLabel("Baja desde");
		GridBagConstraints gbc_fechaBajaDesdeLabel = new GridBagConstraints();
		gbc_fechaBajaDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaBajaDesdeLabel.gridx = 4;
		gbc_fechaBajaDesdeLabel.gridy = 2;
		busquedaPanel.add(fechaBajaDesdeLabel, gbc_fechaBajaDesdeLabel);

		JLabel fechaBajaHastaLabel = new JLabel("Baja hasta");
		GridBagConstraints gbc_fechaBajaHastaLabel = new GridBagConstraints();
		gbc_fechaBajaHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaBajaHastaLabel.gridx = 5;
		gbc_fechaBajaHastaLabel.gridy = 2;
		busquedaPanel.add(fechaBajaHastaLabel, gbc_fechaBajaHastaLabel);

		JLabel estadoLabel = new JLabel("Estado");
		GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
		gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoLabel.gridx = 6;
		gbc_estadoLabel.gridy = 2;
		busquedaPanel.add(estadoLabel, gbc_estadoLabel);

		// Segunda fila campos
		telefonoTF = new JTextField(10);
		GridBagConstraints gbc_telefonoTF = new GridBagConstraints();
		gbc_telefonoTF.insets = new Insets(0, 0, 5, 5);
		gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonoTF.gridx = 1;
		gbc_telefonoTF.gridy = 3;
		busquedaPanel.add(telefonoTF, gbc_telefonoTF);

		fechaContratacionDesdeDC = new JDateChooser();
		GridBagConstraints gbc_fechaContratacionDesdeDC = new GridBagConstraints();
		gbc_fechaContratacionDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaContratacionDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_fechaContratacionDesdeDC.gridx = 2;
		gbc_fechaContratacionDesdeDC.gridy = 3;
		busquedaPanel.add(fechaContratacionDesdeDC, gbc_fechaContratacionDesdeDC);

		fechaContratacionHastaDC = new JDateChooser();
		GridBagConstraints gbc_fechaContratacionHastaDC = new GridBagConstraints();
		gbc_fechaContratacionHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaContratacionHastaDC.fill = GridBagConstraints.BOTH;
		gbc_fechaContratacionHastaDC.gridx = 3;
		gbc_fechaContratacionHastaDC.gridy = 3;
		busquedaPanel.add(fechaContratacionHastaDC, gbc_fechaContratacionHastaDC);

		fechaBajaDesdeDC = new JDateChooser();
		GridBagConstraints gbc_fechaBajaDesdeDC = new GridBagConstraints();
		gbc_fechaBajaDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaBajaDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_fechaBajaDesdeDC.gridx = 4;
		gbc_fechaBajaDesdeDC.gridy = 3;
		busquedaPanel.add(fechaBajaDesdeDC, gbc_fechaBajaDesdeDC);

		fechaBajaHastaDC = new JDateChooser();
		GridBagConstraints gbc_fechaBajaHastaDC = new GridBagConstraints();
		gbc_fechaBajaHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaBajaHastaDC.fill = GridBagConstraints.BOTH;
		gbc_fechaBajaHastaDC.gridx = 5;
		gbc_fechaBajaHastaDC.gridy = 3;
		busquedaPanel.add(fechaBajaHastaDC, gbc_fechaBajaHastaDC);

		// ComboBox estado (activo/inactivo)
		estadoCB = new JComboBox<>(new String[] { "Seleccionar", "Activo", "Inactivo" });
		GridBagConstraints gbc_estadoCB = new GridBagConstraints();
		gbc_estadoCB.insets = new Insets(0, 0, 5, 5);
		gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoCB.gridx = 6;
		gbc_estadoCB.gridy = 3;
		busquedaPanel.add(estadoCB, gbc_estadoCB);

		// Género
		JLabel generoLabel = new JLabel("Género");
		GridBagConstraints gbc_generoLabel = new GridBagConstraints();
		gbc_generoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_generoLabel.gridx = 1;
		gbc_generoLabel.gridy = 4;
		busquedaPanel.add(generoLabel, gbc_generoLabel);

		generoCB = new JComboBox<>();
		GridBagConstraints gbc_generoCB = new GridBagConstraints();
		gbc_generoCB.insets = new Insets(0, 0, 5, 5);
		gbc_generoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_generoCB.gridx = 1;
		gbc_generoCB.gridy = 5;
		busquedaPanel.add(generoCB, gbc_generoCB);

		// Botones acción
		JButton limpiarButton = new JButton("Limpiar Campos");
		limpiarButton.setIcon(new ImageIcon(
				getClass().getResource("/nuvola/16x16/1909_restart_tool_restart_refresh_tool_refresh.png")));
		limpiarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
			}
		});

		buscarButton = new JButton("Buscar...");
		buscarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		GridBagConstraints gbc_buscarButton = new GridBagConstraints();
		gbc_buscarButton.insets = new Insets(0, 0, 0, 5);
		gbc_buscarButton.gridx = 11;
		gbc_buscarButton.gridy = 6;
		busquedaPanel.add(buscarButton, gbc_buscarButton);

		GridBagConstraints gbc_limpiarButton = new GridBagConstraints();
		gbc_limpiarButton.gridx = 12;
		gbc_limpiarButton.gridy = 6;
		busquedaPanel.add(limpiarButton, gbc_limpiarButton);

		// Tabla resultados
		resultadosTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(resultadosTable);
		add(scrollPane, BorderLayout.CENTER);

		// Panel SUR con paginación
		JPanel southPanel = new JPanel(new BorderLayout());
		add(southPanel, BorderLayout.SOUTH);

		JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		totalResultadosLabel = new JLabel("");
		leftPanel.add(totalResultadosLabel);

		JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		firstButton = new JButton("|<");
		firstButton.setToolTipText("Primera página");
		prevButton = new JButton("<");
		prevButton.setToolTipText("Página anterior");
		pageInfoLabel = new JLabel("Página 1 de 1");
		nextButton = new JButton(">");
		nextButton.setToolTipText("Página siguiente");
		lastButton = new JButton(">|");
		lastButton.setToolTipText("Última página");

		rightPanel.add(firstButton);
		rightPanel.add(prevButton);
		rightPanel.add(pageInfoLabel);
		rightPanel.add(nextButton);
		rightPanel.add(lastButton);

		southPanel.add(leftPanel, BorderLayout.WEST);
		southPanel.add(rightPanel, BorderLayout.EAST);
	}

	private void postInitialize() {
		EmpleadoSearchController searchAction = new EmpleadoSearchController(this);
		buscarButton.setAction(searchAction);
		nombreTF.addKeyListener(searchAction);
		estadoCB.addItemListener(searchAction);
		generoCB.setRenderer(new GeneroCBRenderer());
		generoCB.addItemListener(searchAction);

		cargarComboBoxes();
		resultadosTable.setDefaultRenderer(Object.class, new EmpleadoTableCellRenderer());

		// Listeners paginación
		firstButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPage(1);
			}
		});
		prevButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPage(currentPage - 1);
			}
		});
		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPage(currentPage + 1);
			}
		});
		lastButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goToPage(totalPages);
			}
		});

		firstButton.setEnabled(false);
		prevButton.setEnabled(false);
		nextButton.setEnabled(false);
		lastButton.setEnabled(false);
	}

	private void cargarComboBoxes() {
		// Cargar géneros
		List<Genero> generos = null;
		try {
			generos = generoService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		DefaultComboBoxModel<Genero> generoModel = new DefaultComboBoxModel<>();
		Genero gender = new Genero();
		gender.setId(null);
		gender.setNombre("Seleccionar");
		generoModel.addElement(gender);
		if (generos != null) {
			for (Genero g : generos) {
				generoModel.addElement(g);
			}
		}
		generoCB.setModel(generoModel);
	}

	private void limpiarCampos() {
		idEmpleadoFTF.setText("");
		nombreTF.setText("");
		apellido1TF.setText("");
		apellido2TF.setText("");
		dniTF.setText("");
		emailTF.setText("");
		telefonoTF.setText("");
		fechaContratacionDesdeDC.setDate(null);
		fechaContratacionHastaDC.setDate(null);
		fechaBajaDesdeDC.setDate(null);
		fechaBajaHastaDC.setDate(null);
		estadoCB.setSelectedIndex(0);
		generoCB.setSelectedIndex(0);

		fullModel = null;
		currentPage = 1;
		totalPages = 1;
		updateView();
	}

	private void initServices() {
		empleadoService = new EmpleadoServiceImpl();
		generoService = new GeneroServiceImpl();
	}

	public EmpleadoCriteria getCriteria() {
		EmpleadoCriteria criteria = new EmpleadoCriteria();

		criteria.setId(StringUtils.isBlank(idEmpleadoFTF.getText()) ? null : Long.parseLong(idEmpleadoFTF.getText()));
		criteria.setNombre(StringUtils.trimToNull(nombreTF.getText()));
		criteria.setApellido1(StringUtils.trimToNull(apellido1TF.getText()));
		criteria.setApellido2(StringUtils.trimToNull(apellido2TF.getText()));
		criteria.setDni(StringUtils.trimToNull(dniTF.getText()));
		criteria.setEmail(StringUtils.trimToNull(emailTF.getText()));
		criteria.setTelefono(StringUtils.trimToNull(telefonoTF.getText()));

		// Estado (activo)
		int estadoIdx = estadoCB.getSelectedIndex();
		if (estadoIdx == 1) {
			criteria.setActivo(true);
		} else if (estadoIdx == 2) {
			criteria.setActivo(false);
		} else {
			criteria.setActivo(null);
		}

		Genero genero = (Genero) generoCB.getSelectedItem();
		criteria.setGeneroId(genero != null && genero.getId() != null ? genero.getId() : null);

		return criteria;
	}

	public void setModel(Results<EmpleadoDTO> model) {
		this.fullModel = model;
		this.currentPage = 1;
		int totalRecords = model.getTotal();
		if (totalRecords == 0 && model.getPage() != null) {
			totalRecords = model.getPage().size();
		}
		this.totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		updateView();
	}

	private void updateView() {
		if (fullModel == null) {
			resultadosTable.setModel(new EmpleadoTableModel(new ArrayList<EmpleadoDTO>()));
			totalResultadosLabel.setText("0 resultados encontrados.");
			updatePaginationControls();
			return;
		}

		List<EmpleadoDTO> allEmpleados = fullModel.getPage();
		int totalRecords = fullModel.getTotal();
		if (totalRecords == 0 && allEmpleados != null) {
			totalRecords = allEmpleados.size();
		}

		int fromIndex = (currentPage - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, totalRecords);

		List<EmpleadoDTO> pageList;
		if (fromIndex < allEmpleados.size()) {
			pageList = allEmpleados.subList(fromIndex, Math.min(toIndex, allEmpleados.size()));
		} else {
			pageList = new ArrayList<EmpleadoDTO>();
		}

		EmpleadoTableModel tableModel = new EmpleadoTableModel(pageList);
		resultadosTable.setModel(tableModel);
		resultadosTable.setDefaultRenderer(Object.class, new EmpleadoTableCellRenderer());
		resultadosTable.setRowHeight(25);

		totalResultadosLabel.setText(totalRecords + " resultados encontrados.");
		updatePaginationControls();
	}

	private void updatePaginationControls() {
		if (totalPages <= 1) {
			firstButton.setEnabled(false);
			prevButton.setEnabled(false);
			nextButton.setEnabled(false);
			lastButton.setEnabled(false);
			pageInfoLabel.setText("Página 1 de 1");
		} else {
			firstButton.setEnabled(currentPage > 1);
			prevButton.setEnabled(currentPage > 1);
			nextButton.setEnabled(currentPage < totalPages);
			lastButton.setEnabled(currentPage < totalPages);
			pageInfoLabel.setText("Página " + currentPage + " de " + totalPages);
		}
	}

	private void goToPage(int page) {
		if (page < 1 || page > totalPages)
			return;
		currentPage = page;
		updateView();
	}
}