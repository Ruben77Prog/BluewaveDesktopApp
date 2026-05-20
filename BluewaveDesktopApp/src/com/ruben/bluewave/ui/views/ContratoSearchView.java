package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.EstadoContrato;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.EstadoContratoService;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.service.impl.EstadoContratoServiceImpl;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.controller.ContratoDeleteController;
import com.ruben.bluewave.ui.controller.ContratoSearchController;
import com.ruben.bluewave.ui.controller.ContratoSetEditableController;
import com.ruben.bluewave.ui.model.ContratoTableModel;
import com.ruben.bluewave.ui.renderer.ContratoTableCellRenderer;
import com.ruben.bluewave.ui.renderer.EstadoContratoCBRenderer;
import com.toedter.calendar.JDateChooser;

public class ContratoSearchView extends AbstractView {

	private ContratoService contratoService;
	private EstadoContratoService estadoContratoService;

	private JFormattedTextField idContratoFTF;
	private JTextField numeroContratoTF;
	private JComboBox<EstadoContrato> estadoCB;
	private JTextField clienteNombreTF;
	private JDateChooser fechaInicioDesdeDC;
	private JDateChooser fechaInicioHastaDC;

	private JTable resultadosTable;
	private Results<ContratoDTO> fullModel;
	private int currentPage = 1;
	private int pageSize = 10;
	private int totalPages = 1;

	private JButton buscarButton;
	private JLabel totalResultadosLabel;
	private JButton firstButton, prevButton, nextButton, lastButton;
	private JLabel pageInfoLabel;

	public ContratoSearchView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initServices() {
		contratoService = new ContratoServiceImpl();
		estadoContratoService = new EstadoContratoServiceImpl();
	}

	private void initialize() {
		setName("Búsqueda de contratos");
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

		JLabel idContratoLabel = new JLabel("ID Contrato");
		GridBagConstraints gbc_idContratoLabel = new GridBagConstraints();
		gbc_idContratoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idContratoLabel.gridx = 1;
		gbc_idContratoLabel.gridy = 0;
		busquedaPanel.add(idContratoLabel, gbc_idContratoLabel);

		JLabel numeroLabel = new JLabel("Nº Contrato");
		GridBagConstraints gbc_numeroLabel = new GridBagConstraints();
		gbc_numeroLabel.insets = new Insets(0, 0, 5, 5);
		gbc_numeroLabel.gridx = 2;
		gbc_numeroLabel.gridy = 0;
		busquedaPanel.add(numeroLabel, gbc_numeroLabel);

		JLabel estadoLabel = new JLabel("Estado");
		GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
		gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoLabel.gridx = 3;
		gbc_estadoLabel.gridy = 0;
		busquedaPanel.add(estadoLabel, gbc_estadoLabel);

		JLabel clienteLabel = new JLabel("Cliente");
		GridBagConstraints gbc_clienteLabel = new GridBagConstraints();
		gbc_clienteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clienteLabel.gridx = 4;
		gbc_clienteLabel.gridy = 0;
		busquedaPanel.add(clienteLabel, gbc_clienteLabel);

		JLabel fechaInicioDesdeLabel = new JLabel("Inicio desde");
		GridBagConstraints gbc_fechaInicioDesdeLabel = new GridBagConstraints();
		gbc_fechaInicioDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDesdeLabel.gridx = 5;
		gbc_fechaInicioDesdeLabel.gridy = 0;
		busquedaPanel.add(fechaInicioDesdeLabel, gbc_fechaInicioDesdeLabel);

		JLabel fechaInicioHastaLabel = new JLabel("Inicio hasta");
		GridBagConstraints gbc_fechaInicioHastaLabel = new GridBagConstraints();
		gbc_fechaInicioHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioHastaLabel.gridx = 6;
		gbc_fechaInicioHastaLabel.gridy = 0;
		busquedaPanel.add(fechaInicioHastaLabel, gbc_fechaInicioHastaLabel);

		idContratoFTF = new JFormattedTextField();
		GridBagConstraints gbc_idContratoFTF = new GridBagConstraints();
		gbc_idContratoFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idContratoFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idContratoFTF.gridx = 1;
		gbc_idContratoFTF.gridy = 1;
		busquedaPanel.add(idContratoFTF, gbc_idContratoFTF);

		numeroContratoTF = new JTextField(15);
		GridBagConstraints gbc_numeroTF = new GridBagConstraints();
		gbc_numeroTF.insets = new Insets(0, 0, 5, 5);
		gbc_numeroTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_numeroTF.gridx = 2;
		gbc_numeroTF.gridy = 1;
		busquedaPanel.add(numeroContratoTF, gbc_numeroTF);

		estadoCB = new JComboBox<>();
		GridBagConstraints gbc_estadoCB = new GridBagConstraints();
		gbc_estadoCB.insets = new Insets(0, 0, 5, 5);
		gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoCB.gridx = 3;
		gbc_estadoCB.gridy = 1;
		busquedaPanel.add(estadoCB, gbc_estadoCB);

		clienteNombreTF = new JTextField(15);
		GridBagConstraints gbc_clienteTF = new GridBagConstraints();
		gbc_clienteTF.insets = new Insets(0, 0, 5, 5);
		gbc_clienteTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteTF.gridx = 4;
		gbc_clienteTF.gridy = 1;
		busquedaPanel.add(clienteNombreTF, gbc_clienteTF);

		fechaInicioDesdeDC = new JDateChooser();
		GridBagConstraints gbc_fechaInicioDesdeDC = new GridBagConstraints();
		gbc_fechaInicioDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_fechaInicioDesdeDC.gridx = 5;
		gbc_fechaInicioDesdeDC.gridy = 1;
		busquedaPanel.add(fechaInicioDesdeDC, gbc_fechaInicioDesdeDC);

		fechaInicioHastaDC = new JDateChooser();
		GridBagConstraints gbc_fechaInicioHastaDC = new GridBagConstraints();
		gbc_fechaInicioHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaInicioHastaDC.fill = GridBagConstraints.BOTH;
		gbc_fechaInicioHastaDC.gridx = 6;
		gbc_fechaInicioHastaDC.gridy = 1;
		busquedaPanel.add(fechaInicioHastaDC, gbc_fechaInicioHastaDC);

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

		resultadosTable = new JTable();
		JScrollPane scrollPane = new JScrollPane(resultadosTable);
		add(scrollPane, BorderLayout.CENTER);

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
		ContratoSearchController searchAction = new ContratoSearchController(this);
		buscarButton.setAction(searchAction);
		numeroContratoTF.addKeyListener(searchAction);
		estadoCB.addItemListener(searchAction);
		clienteNombreTF.addKeyListener(searchAction);

		cargarCombos();
		resultadosTable.setDefaultRenderer(Object.class, new ContratoTableCellRenderer());

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

		resultadosTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int column = resultadosTable.columnAtPoint(e.getPoint());
				int row = resultadosTable.rowAtPoint(e.getPoint());

				if (row >= 0 && column == 7) {
					ContratoDTO contrato = ((ContratoTableModel) resultadosTable.getModel()).getContratoAt(row);

					if (contrato == null) {
						return;
					}

					Object[] options = { "Editar", "Eliminar", "Cancelar" };
					int option = JOptionPane.showOptionDialog(ContratoSearchView.this,
							"Contrato: " + contrato.getNumeroContrato(), "Acciones", JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[2]);

					if (option == 0) {
						ContratoAltaView editView = new ContratoAltaView();
						ContratoSetEditableController setEditableController = new ContratoSetEditableController(
								editView, contrato);
						setEditableController.doAction();
						MainWindow.getInstance().addView("Editar contrato", editView);
					} else if (option == 1) {
						ContratoDeleteController deleteController = new ContratoDeleteController(
								ContratoSearchView.this, contrato);
						deleteController.doAction();
					}
				}
			}
		});
	}

	private void cargarCombos() {
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
			estadoCB.setModel(model);
			estadoCB.setRenderer(new EstadoContratoCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void limpiarCampos() {
		idContratoFTF.setText("");
		numeroContratoTF.setText("");
		estadoCB.setSelectedIndex(0);
		clienteNombreTF.setText("");
		fechaInicioDesdeDC.setDate(null);
		fechaInicioHastaDC.setDate(null);
		fullModel = null;
		currentPage = 1;
		totalPages = 1;
		updateView();
	}

	public ContratoCriteria getCriteria() {
		ContratoCriteria criteria = new ContratoCriteria();

		criteria.setId(StringUtils.isBlank(idContratoFTF.getText()) ? null : Long.parseLong(idContratoFTF.getText()));
		criteria.setNumeroContrato(StringUtils.trimToNull(numeroContratoTF.getText()));
		criteria.setClienteNombre(StringUtils.trimToNull(clienteNombreTF.getText()));
		criteria.setFechaInicioDesde(fechaInicioDesdeDC.getDate());
		criteria.setFechaInicioHasta(fechaInicioHastaDC.getDate());

		EstadoContrato estado = (EstadoContrato) estadoCB.getSelectedItem();
		criteria.setEstadoContratoId(estado != null && estado.getId() != null ? estado.getId() : null);

		return criteria;
	}

	public void setModel(Results<ContratoDTO> model) {
		this.fullModel = model;
		this.currentPage = 1;
		int totalRecords = model.getTotal();
		if (totalRecords == 0 && model.getPage() != null) {
			totalRecords = model.getPage().size();
		}
		this.totalPages = (int) Math.ceil((double) totalRecords / pageSize);
		updateView();
	}

	public void buscar() {
		ContratoCriteria criteria = getCriteria();
		try {
			Results<ContratoDTO> resultados = contratoService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
			setModel(resultados);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al buscar contratos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateView() {
		if (fullModel == null) {
			resultadosTable.setModel(new ContratoTableModel(new ArrayList<ContratoDTO>()));
			totalResultadosLabel.setText("0 resultados encontrados.");
			updatePaginationControls();
			return;
		}

		List<ContratoDTO> allContratos = fullModel.getPage();
		int totalRecords = fullModel.getTotal();
		if (totalRecords == 0 && allContratos != null) {
			totalRecords = allContratos.size();
		}

		int fromIndex = (currentPage - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, totalRecords);

		List<ContratoDTO> pageList;
		if (fromIndex < allContratos.size()) {
			pageList = allContratos.subList(fromIndex, Math.min(toIndex, allContratos.size()));
		} else {
			pageList = new ArrayList<ContratoDTO>();
		}

		ContratoTableModel tableModel = new ContratoTableModel(pageList);
		resultadosTable.setModel(tableModel);
		resultadosTable.setDefaultRenderer(Object.class, new ContratoTableCellRenderer());
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
		if (page < 1 || page > totalPages) {
			return;
		}
		currentPage = page;
		updateView();
	}
}