package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
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
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;

import com.ruben.bluewave.dao.criteria.IncidenciaCriteria;
import com.ruben.bluewave.dao.criteria.TipoIncidenciaCriteria;
import com.ruben.bluewave.model.EstadoIncidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.model.TipoIncidencia;
import com.ruben.bluewave.service.EstadoIncidenciaService;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.TipoIncidenciaService;
import com.ruben.bluewave.service.impl.EstadoIncidenciaServiceImpl;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.service.impl.TipoIncidenciaServiceImpl;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.controller.IncidenciaDeleteController;
import com.ruben.bluewave.ui.controller.IncidenciaSearchController;
import com.ruben.bluewave.ui.controller.IncidenciaSetEditableController;
import com.ruben.bluewave.ui.model.IncidenciaTableModel;
import com.ruben.bluewave.ui.renderer.EstadoIncidenciaCBRenderer;
import com.ruben.bluewave.ui.renderer.IncidenciaTableCellRenderer;
import com.ruben.bluewave.ui.renderer.TipoIncidenciaCBRenderer;
import com.toedter.calendar.JDateChooser;

public class IncidenciaSearchView extends AbstractView {

	private IncidenciaService incidenciaService;
	private TipoIncidenciaService tipoService;
	private EstadoIncidenciaService estadoService;

	private JFormattedTextField idIncidenciaFTF;
	private JTextField tituloTF;
	private JComboBox<TipoIncidencia> tipoCB;
	private JComboBox<EstadoIncidencia> estadoCB;
	private JTextField contratoNumeroTF;
	private JTextField clienteNombreTF;
	private JDateChooser fechaDesdeDC;
	private JDateChooser fechaHastaDC;

	private JTable resultadosTable;
	private Results<IncidenciaDTO> fullModel;
	private int currentPage = 1;
	private int pageSize = 10;
	private int totalPages = 1;

	private JButton buscarButton;
	private JLabel totalResultadosLabel;
	private JButton firstButton, prevButton, nextButton, lastButton;
	private JLabel pageInfoLabel;

	public IncidenciaSearchView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initServices() {
		incidenciaService = new IncidenciaServiceImpl();
		tipoService = new TipoIncidenciaServiceImpl();
		estadoService = new EstadoIncidenciaServiceImpl();
	}

	private void initialize() {
		setName("Búsqueda de incidencias");
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

		JLabel idLabel = new JLabel("ID Incidencia");
		GridBagConstraints gbc_idLabel = new GridBagConstraints();
		gbc_idLabel.insets = new Insets(0, 0, 5, 5);
		gbc_idLabel.gridx = 1;
		gbc_idLabel.gridy = 0;
		busquedaPanel.add(idLabel, gbc_idLabel);

		JLabel tituloLabel = new JLabel("Título");
		GridBagConstraints gbc_tituloLabel = new GridBagConstraints();
		gbc_tituloLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tituloLabel.gridx = 2;
		gbc_tituloLabel.gridy = 0;
		busquedaPanel.add(tituloLabel, gbc_tituloLabel);

		JLabel tipoLabel = new JLabel("Tipo");
		GridBagConstraints gbc_tipoLabel = new GridBagConstraints();
		gbc_tipoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_tipoLabel.gridx = 3;
		gbc_tipoLabel.gridy = 0;
		busquedaPanel.add(tipoLabel, gbc_tipoLabel);

		JLabel estadoLabel = new JLabel("Estado");
		GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
		gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_estadoLabel.gridx = 4;
		gbc_estadoLabel.gridy = 0;
		busquedaPanel.add(estadoLabel, gbc_estadoLabel);

		JLabel contratoLabel = new JLabel("Nº Contrato");
		GridBagConstraints gbc_contratoLabel = new GridBagConstraints();
		gbc_contratoLabel.insets = new Insets(0, 0, 5, 5);
		gbc_contratoLabel.gridx = 5;
		gbc_contratoLabel.gridy = 0;
		busquedaPanel.add(contratoLabel, gbc_contratoLabel);

		JLabel clienteLabel = new JLabel("Cliente");
		GridBagConstraints gbc_clienteLabel = new GridBagConstraints();
		gbc_clienteLabel.insets = new Insets(0, 0, 5, 5);
		gbc_clienteLabel.gridx = 6;
		gbc_clienteLabel.gridy = 0;
		busquedaPanel.add(clienteLabel, gbc_clienteLabel);

		idIncidenciaFTF = new JFormattedTextField();
		GridBagConstraints gbc_idFTF = new GridBagConstraints();
		gbc_idFTF.insets = new Insets(0, 0, 5, 5);
		gbc_idFTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_idFTF.gridx = 1;
		gbc_idFTF.gridy = 1;
		busquedaPanel.add(idIncidenciaFTF, gbc_idFTF);

		tituloTF = new JTextField(15);
		GridBagConstraints gbc_tituloTF = new GridBagConstraints();
		gbc_tituloTF.insets = new Insets(0, 0, 5, 5);
		gbc_tituloTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_tituloTF.gridx = 2;
		gbc_tituloTF.gridy = 1;
		busquedaPanel.add(tituloTF, gbc_tituloTF);

		tipoCB = new JComboBox<>();
		GridBagConstraints gbc_tipoCB = new GridBagConstraints();
		gbc_tipoCB.insets = new Insets(0, 0, 5, 5);
		gbc_tipoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_tipoCB.gridx = 3;
		gbc_tipoCB.gridy = 1;
		busquedaPanel.add(tipoCB, gbc_tipoCB);

		estadoCB = new JComboBox<>();
		GridBagConstraints gbc_estadoCB = new GridBagConstraints();
		gbc_estadoCB.insets = new Insets(0, 0, 5, 5);
		gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
		gbc_estadoCB.gridx = 4;
		gbc_estadoCB.gridy = 1;
		busquedaPanel.add(estadoCB, gbc_estadoCB);

		contratoNumeroTF = new JTextField(10);
		GridBagConstraints gbc_contratoTF = new GridBagConstraints();
		gbc_contratoTF.insets = new Insets(0, 0, 5, 5);
		gbc_contratoTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_contratoTF.gridx = 5;
		gbc_contratoTF.gridy = 1;
		busquedaPanel.add(contratoNumeroTF, gbc_contratoTF);

		clienteNombreTF = new JTextField(10);
		GridBagConstraints gbc_clienteTF = new GridBagConstraints();
		gbc_clienteTF.insets = new Insets(0, 0, 5, 5);
		gbc_clienteTF.fill = GridBagConstraints.HORIZONTAL;
		gbc_clienteTF.gridx = 6;
		gbc_clienteTF.gridy = 1;
		busquedaPanel.add(clienteNombreTF, gbc_clienteTF);

		JLabel fechaDesdeLabel = new JLabel("Fecha desde");
		GridBagConstraints gbc_fechaDesdeLabel = new GridBagConstraints();
		gbc_fechaDesdeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeLabel.gridx = 1;
		gbc_fechaDesdeLabel.gridy = 2;
		busquedaPanel.add(fechaDesdeLabel, gbc_fechaDesdeLabel);

		JLabel fechaHastaLabel = new JLabel("Fecha hasta");
		GridBagConstraints gbc_fechaHastaLabel = new GridBagConstraints();
		gbc_fechaHastaLabel.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaLabel.gridx = 2;
		gbc_fechaHastaLabel.gridy = 2;
		busquedaPanel.add(fechaHastaLabel, gbc_fechaHastaLabel);

		fechaDesdeDC = new JDateChooser();
		GridBagConstraints gbc_fechaDesdeDC = new GridBagConstraints();
		gbc_fechaDesdeDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaDesdeDC.fill = GridBagConstraints.BOTH;
		gbc_fechaDesdeDC.gridx = 1;
		gbc_fechaDesdeDC.gridy = 3;
		busquedaPanel.add(fechaDesdeDC, gbc_fechaDesdeDC);

		fechaHastaDC = new JDateChooser();
		GridBagConstraints gbc_fechaHastaDC = new GridBagConstraints();
		gbc_fechaHastaDC.insets = new Insets(0, 0, 5, 5);
		gbc_fechaHastaDC.fill = GridBagConstraints.BOTH;
		gbc_fechaHastaDC.gridx = 2;
		gbc_fechaHastaDC.gridy = 3;
		busquedaPanel.add(fechaHastaDC, gbc_fechaHastaDC);

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
		prevButton = new JButton("<");
		pageInfoLabel = new JLabel("Página 1 de 1");
		nextButton = new JButton(">");
		lastButton = new JButton(">|");
		rightPanel.add(firstButton);
		rightPanel.add(prevButton);
		rightPanel.add(pageInfoLabel);
		rightPanel.add(nextButton);
		rightPanel.add(lastButton);
		southPanel.add(leftPanel, BorderLayout.WEST);
		southPanel.add(rightPanel, BorderLayout.EAST);
	}

	private void postInitialize() {
		IncidenciaSearchController searchAction = new IncidenciaSearchController(this);
		buscarButton.setAction(searchAction);
		tituloTF.addKeyListener(searchAction);
		tipoCB.setRenderer(new TipoIncidenciaCBRenderer());
		tipoCB.addItemListener(searchAction);
		estadoCB.setRenderer(new EstadoIncidenciaCBRenderer());
		estadoCB.addItemListener(searchAction);

		cargarCombos();
		resultadosTable.setDefaultRenderer(Object.class, new IncidenciaTableCellRenderer());

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
					IncidenciaDTO incidencia = ((IncidenciaTableModel) resultadosTable.getModel()).getIncidenciaAt(row);

					if (incidencia == null) {
						return;
					}

					Object[] options = { "Editar", "Eliminar", "Cancelar" };
					int option = JOptionPane.showOptionDialog(IncidenciaSearchView.this,
							"Incidencia: " + incidencia.getNumeroIncidencia() + " - " + incidencia.getTitulo(),
							"Acciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options,
							options[2]);

					if (option == 0) {
						NuevaIncidenciaView editView = new NuevaIncidenciaView();
						IncidenciaSetEditableController setEditableController = new IncidenciaSetEditableController(
								editView, incidencia);
						setEditableController.doAction();
						MainWindow.getInstance().addView("Editar incidencia", editView);
					} else if (option == 1) {
						IncidenciaDeleteController deleteController = new IncidenciaDeleteController(
								IncidenciaSearchView.this, incidencia);
						deleteController.doAction();
					}
				}
			}
		});
	}

	private void cargarCombos() {
		try {
			TipoIncidenciaCriteria criteria = new TipoIncidenciaCriteria();
			criteria.setActivo(true);
			List<TipoIncidencia> tipos = tipoService.findByCriteria(criteria);

			DefaultComboBoxModel<TipoIncidencia> tipoModel = new DefaultComboBoxModel<>();
			TipoIncidencia t = new TipoIncidencia();
			t.setId(null);
			t.setNombre("Seleccionar");
			tipoModel.addElement(t);
			if (tipos != null) {
				for (TipoIncidencia ti : tipos) {
					tipoModel.addElement(ti);
				}
			}
			tipoCB.setModel(tipoModel);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				List<TipoIncidencia> tipos = tipoService.findAll();
				DefaultComboBoxModel<TipoIncidencia> tipoModel = new DefaultComboBoxModel<>();
				TipoIncidencia t = new TipoIncidencia();
				t.setId(null);
				t.setNombre("Seleccionar");
				tipoModel.addElement(t);
				for (TipoIncidencia ti : tipos) {
					tipoModel.addElement(ti);
				}
				tipoCB.setModel(tipoModel);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		try {
			List<EstadoIncidencia> estados = estadoService.findAll();
			DefaultComboBoxModel<EstadoIncidencia> estadoModel = new DefaultComboBoxModel<>();
			EstadoIncidencia es = new EstadoIncidencia();
			es.setId(null);
			es.setNombre("Seleccionar");
			estadoModel.addElement(es);
			if (estados != null) {
				for (EstadoIncidencia ei : estados) {
					estadoModel.addElement(ei);
				}
			}
			estadoCB.setModel(estadoModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void limpiarCampos() {
		idIncidenciaFTF.setText("");
		tituloTF.setText("");
		tipoCB.setSelectedIndex(0);
		estadoCB.setSelectedIndex(0);
		contratoNumeroTF.setText("");
		clienteNombreTF.setText("");
		fechaDesdeDC.setDate(null);
		fechaHastaDC.setDate(null);
		fullModel = null;
		currentPage = 1;
		totalPages = 1;
		updateView();
	}

	public IncidenciaCriteria getCriteria() {
		IncidenciaCriteria criteria = new IncidenciaCriteria();
		criteria.setId(
				StringUtils.isBlank(idIncidenciaFTF.getText()) ? null : Long.parseLong(idIncidenciaFTF.getText()));
		criteria.setTitulo(StringUtils.trimToNull(tituloTF.getText()));
		TipoIncidencia tipo = (TipoIncidencia) tipoCB.getSelectedItem();
		criteria.setTipoIncidenciaId(tipo != null && tipo.getId() != null ? tipo.getId() : null);
		EstadoIncidencia estado = (EstadoIncidencia) estadoCB.getSelectedItem();
		criteria.setEstadoIncidenciaId(estado != null && estado.getId() != null ? estado.getId() : null);
		criteria.setContratoNumero(StringUtils.trimToNull(contratoNumeroTF.getText()));
		criteria.setClienteNombre(StringUtils.trimToNull(clienteNombreTF.getText()));
		criteria.setFechaDesde(fechaDesdeDC.getDate());
		criteria.setFechaHasta(fechaHastaDC.getDate());
		return criteria;
	}

	public void setModel(Results<IncidenciaDTO> model) {
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
		IncidenciaCriteria criteria = getCriteria();
		try {
			Results<IncidenciaDTO> resultados = incidenciaService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
			setModel(resultados);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error al buscar incidencias: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateView() {
		if (fullModel == null) {
			resultadosTable.setModel(new IncidenciaTableModel(new ArrayList<>()));
			totalResultadosLabel.setText("0 resultados encontrados.");
			updatePaginationControls();
			return;
		}

		List<IncidenciaDTO> all = fullModel.getPage();
		int totalRecords = fullModel.getTotal();
		if (totalRecords == 0 && all != null) {
			totalRecords = all.size();
		}

		int fromIndex = (currentPage - 1) * pageSize;
		int toIndex = Math.min(fromIndex + pageSize, totalRecords);
		List<IncidenciaDTO> pageList;
		if (fromIndex < all.size()) {
			pageList = all.subList(fromIndex, Math.min(toIndex, all.size()));
		} else {
			pageList = new ArrayList<>();
		}

		IncidenciaTableModel tableModel = new IncidenciaTableModel(pageList);
		resultadosTable.setModel(tableModel);
		resultadosTable.setDefaultRenderer(Object.class, new IncidenciaTableCellRenderer());
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