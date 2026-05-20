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

import com.ruben.bluewave.dao.criteria.ClienteCriteria;
import com.ruben.bluewave.model.ClienteDTO;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.EstadoCliente;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.EstadoClienteService;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.service.impl.ClienteServiceImpl;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.service.impl.EstadoClienteServiceImpl;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.controller.ClienteDeleteController;
import com.ruben.bluewave.ui.controller.ClienteSearchController;
import com.ruben.bluewave.ui.controller.ClienteSetEditableController;
import com.ruben.bluewave.ui.model.ClienteTableModel;
import com.ruben.bluewave.ui.renderer.ClienteTableCellRenderer;
import com.ruben.bluewave.ui.renderer.EmpleadoCBRenderer;
import com.ruben.bluewave.ui.renderer.EstadoClienteCBRenderer;
import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
import com.toedter.calendar.JDateChooser;

public class ClienteSearchView extends AbstractView {

    private ClienteServiceImpl clienteService;
    private GeneroService generoService;
    private EstadoClienteService estadoClienteService;
    private EmpleadoService empleadoService;

    private JFormattedTextField idClienteFTF;
    private JTextField nombreTF;
    private JTextField apellido1TF;
    private JTextField apellido2TF;
    private JTextField dniTF;
    private JTextField emailTF;
    private JTextField telefonoTF;

    private JDateChooser fechaNacimientoDesdeDC;
    private JDateChooser fechaNacimientoHastaDC;
    private JDateChooser fechaCreacionDesdeDC;
    private JDateChooser fechaCreacionHastaDC;

    private JComboBox<EstadoCliente> estadoCB;
    private JComboBox<Genero> generoCB;
    private JComboBox<EmpleadoDTO> empleadoCB;

    private JTable resultadosTable;
    private Results<ClienteDTO> fullModel;
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;

    private JButton buscarButton;
    private JLabel totalResultadosLabel;
    private JButton firstButton, prevButton, nextButton, lastButton;
    private JLabel pageInfoLabel;

    public ClienteSearchView() {
        initServices();
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Busqueda cliente");
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

        JLabel idClienteLabel = new JLabel("ID Cliente");
        GridBagConstraints gbc_idClienteLabel = new GridBagConstraints();
        gbc_idClienteLabel.insets = new Insets(0, 0, 5, 5);
        gbc_idClienteLabel.gridx = 1;
        gbc_idClienteLabel.gridy = 0;
        busquedaPanel.add(idClienteLabel, gbc_idClienteLabel);

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

        idClienteFTF = new JFormattedTextField();
        GridBagConstraints gbc_idClienteFTF = new GridBagConstraints();
        gbc_idClienteFTF.insets = new Insets(0, 0, 5, 5);
        gbc_idClienteFTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_idClienteFTF.gridx = 1;
        gbc_idClienteFTF.gridy = 1;
        busquedaPanel.add(idClienteFTF, gbc_idClienteFTF);

        nombreTF = new JTextField();
        nombreTF.setColumns(10);
        GridBagConstraints gbc_nombreTF = new GridBagConstraints();
        gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
        gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_nombreTF.gridx = 2;
        gbc_nombreTF.gridy = 1;
        busquedaPanel.add(nombreTF, gbc_nombreTF);

        apellido1TF = new JTextField();
        apellido1TF.setColumns(10);
        GridBagConstraints gbc_apellido1TF = new GridBagConstraints();
        gbc_apellido1TF.insets = new Insets(0, 0, 5, 5);
        gbc_apellido1TF.fill = GridBagConstraints.HORIZONTAL;
        gbc_apellido1TF.gridx = 3;
        gbc_apellido1TF.gridy = 1;
        busquedaPanel.add(apellido1TF, gbc_apellido1TF);

        apellido2TF = new JTextField();
        apellido2TF.setColumns(10);
        GridBagConstraints gbc_apellido2TF = new GridBagConstraints();
        gbc_apellido2TF.insets = new Insets(0, 0, 5, 5);
        gbc_apellido2TF.fill = GridBagConstraints.HORIZONTAL;
        gbc_apellido2TF.gridx = 4;
        gbc_apellido2TF.gridy = 1;
        busquedaPanel.add(apellido2TF, gbc_apellido2TF);

        dniTF = new JTextField();
        dniTF.setColumns(10);
        GridBagConstraints gbc_dniTF = new GridBagConstraints();
        gbc_dniTF.insets = new Insets(0, 0, 5, 5);
        gbc_dniTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_dniTF.gridx = 5;
        gbc_dniTF.gridy = 1;
        busquedaPanel.add(dniTF, gbc_dniTF);

        emailTF = new JTextField();
        emailTF.setColumns(15);
        GridBagConstraints gbc_emailTF = new GridBagConstraints();
        gbc_emailTF.insets = new Insets(0, 0, 5, 5);
        gbc_emailTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_emailTF.gridx = 6;
        gbc_emailTF.gridy = 1;
        busquedaPanel.add(emailTF, gbc_emailTF);

        JLabel telefonoLabel = new JLabel("Teléfono");
        GridBagConstraints gbc_telefonoLabel = new GridBagConstraints();
        gbc_telefonoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_telefonoLabel.gridx = 1;
        gbc_telefonoLabel.gridy = 2;
        busquedaPanel.add(telefonoLabel, gbc_telefonoLabel);

        JLabel fechaNacimientoDesdeLabel = new JLabel("Nacimiento desde");
        GridBagConstraints gbc_fechaNacimientoDesdeLabel = new GridBagConstraints();
        gbc_fechaNacimientoDesdeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_fechaNacimientoDesdeLabel.gridx = 2;
        gbc_fechaNacimientoDesdeLabel.gridy = 2;
        busquedaPanel.add(fechaNacimientoDesdeLabel, gbc_fechaNacimientoDesdeLabel);

        JLabel fechaNacimientoHastaLabel = new JLabel("Nacimiento hasta");
        GridBagConstraints gbc_fechaNacimientoHastaLabel = new GridBagConstraints();
        gbc_fechaNacimientoHastaLabel.insets = new Insets(0, 0, 5, 5);
        gbc_fechaNacimientoHastaLabel.gridx = 3;
        gbc_fechaNacimientoHastaLabel.gridy = 2;
        busquedaPanel.add(fechaNacimientoHastaLabel, gbc_fechaNacimientoHastaLabel);

        JLabel fechaCreacionDesdeLabel = new JLabel("Creación desde");
        GridBagConstraints gbc_fechaCreacionDesdeLabel = new GridBagConstraints();
        gbc_fechaCreacionDesdeLabel.insets = new Insets(0, 0, 5, 5);
        gbc_fechaCreacionDesdeLabel.gridx = 4;
        gbc_fechaCreacionDesdeLabel.gridy = 2;
        busquedaPanel.add(fechaCreacionDesdeLabel, gbc_fechaCreacionDesdeLabel);

        JLabel fechaCreacionHastaLabel = new JLabel("Creación hasta");
        GridBagConstraints gbc_fechaCreacionHastaLabel = new GridBagConstraints();
        gbc_fechaCreacionHastaLabel.insets = new Insets(0, 0, 5, 5);
        gbc_fechaCreacionHastaLabel.gridx = 5;
        gbc_fechaCreacionHastaLabel.gridy = 2;
        busquedaPanel.add(fechaCreacionHastaLabel, gbc_fechaCreacionHastaLabel);

        JLabel estadoLabel = new JLabel("Estado");
        GridBagConstraints gbc_estadoLabel = new GridBagConstraints();
        gbc_estadoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_estadoLabel.gridx = 6;
        gbc_estadoLabel.gridy = 2;
        busquedaPanel.add(estadoLabel, gbc_estadoLabel);

        telefonoTF = new JTextField();
        telefonoTF.setColumns(10);
        GridBagConstraints gbc_telefonoTF = new GridBagConstraints();
        gbc_telefonoTF.insets = new Insets(0, 0, 5, 5);
        gbc_telefonoTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_telefonoTF.gridx = 1;
        gbc_telefonoTF.gridy = 3;
        busquedaPanel.add(telefonoTF, gbc_telefonoTF);

        fechaNacimientoDesdeDC = new JDateChooser();
        GridBagConstraints gbc_fechaNacimientoDesdeDC = new GridBagConstraints();
        gbc_fechaNacimientoDesdeDC.insets = new Insets(0, 0, 5, 5);
        gbc_fechaNacimientoDesdeDC.fill = GridBagConstraints.BOTH;
        gbc_fechaNacimientoDesdeDC.gridx = 2;
        gbc_fechaNacimientoDesdeDC.gridy = 3;
        busquedaPanel.add(fechaNacimientoDesdeDC, gbc_fechaNacimientoDesdeDC);

        fechaNacimientoHastaDC = new JDateChooser();
        GridBagConstraints gbc_fechaNacimientoHastaDC = new GridBagConstraints();
        gbc_fechaNacimientoHastaDC.insets = new Insets(0, 0, 5, 5);
        gbc_fechaNacimientoHastaDC.fill = GridBagConstraints.BOTH;
        gbc_fechaNacimientoHastaDC.gridx = 3;
        gbc_fechaNacimientoHastaDC.gridy = 3;
        busquedaPanel.add(fechaNacimientoHastaDC, gbc_fechaNacimientoHastaDC);

        fechaCreacionDesdeDC = new JDateChooser();
        GridBagConstraints gbc_fechaCreacionDesdeDC = new GridBagConstraints();
        gbc_fechaCreacionDesdeDC.insets = new Insets(0, 0, 5, 5);
        gbc_fechaCreacionDesdeDC.fill = GridBagConstraints.BOTH;
        gbc_fechaCreacionDesdeDC.gridx = 4;
        gbc_fechaCreacionDesdeDC.gridy = 3;
        busquedaPanel.add(fechaCreacionDesdeDC, gbc_fechaCreacionDesdeDC);

        fechaCreacionHastaDC = new JDateChooser();
        GridBagConstraints gbc_fechaCreacionHastaDC = new GridBagConstraints();
        gbc_fechaCreacionHastaDC.insets = new Insets(0, 0, 5, 5);
        gbc_fechaCreacionHastaDC.fill = GridBagConstraints.BOTH;
        gbc_fechaCreacionHastaDC.gridx = 5;
        gbc_fechaCreacionHastaDC.gridy = 3;
        busquedaPanel.add(fechaCreacionHastaDC, gbc_fechaCreacionHastaDC);

        estadoCB = new JComboBox<>();
        GridBagConstraints gbc_estadoCB = new GridBagConstraints();
        gbc_estadoCB.insets = new Insets(0, 0, 5, 5);
        gbc_estadoCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_estadoCB.gridx = 6;
        gbc_estadoCB.gridy = 3;
        busquedaPanel.add(estadoCB, gbc_estadoCB);

        JLabel generoLabel = new JLabel("Género");
        GridBagConstraints gbc_generoLabel = new GridBagConstraints();
        gbc_generoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_generoLabel.gridx = 1;
        gbc_generoLabel.gridy = 4;
        busquedaPanel.add(generoLabel, gbc_generoLabel);

        JLabel empleadoLabel = new JLabel("Empleado");
        GridBagConstraints gbc_empleadoLabel = new GridBagConstraints();
        gbc_empleadoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_empleadoLabel.gridx = 2;
        gbc_empleadoLabel.gridy = 4;
        busquedaPanel.add(empleadoLabel, gbc_empleadoLabel);

        generoCB = new JComboBox<>();
        GridBagConstraints gbc_generoCB = new GridBagConstraints();
        gbc_generoCB.insets = new Insets(0, 0, 5, 5);
        gbc_generoCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_generoCB.gridx = 1;
        gbc_generoCB.gridy = 5;
        busquedaPanel.add(generoCB, gbc_generoCB);

        empleadoCB = new JComboBox<>();
        GridBagConstraints gbc_empleadoCB = new GridBagConstraints();
        gbc_empleadoCB.insets = new Insets(0, 0, 5, 5);
        gbc_empleadoCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_empleadoCB.gridx = 2;
        gbc_empleadoCB.gridy = 5;
        busquedaPanel.add(empleadoCB, gbc_empleadoCB);

        JButton limpiarButton = new JButton("Limpiar Campos");
        limpiarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1909_restart_tool_restart_refresh_tool_refresh.png")));
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

    private void initServices() {
        clienteService = new ClienteServiceImpl();
        generoService = new GeneroServiceImpl();
        estadoClienteService = new EstadoClienteServiceImpl();
        empleadoService = new EmpleadoServiceImpl();
    }

    private void postInitialize() {
        ClienteSearchController searchAction = new ClienteSearchController(this);
        buscarButton.setAction(searchAction);
        
        nombreTF.addKeyListener(searchAction);
        apellido1TF.addKeyListener(searchAction);
        apellido2TF.addKeyListener(searchAction);
        dniTF.addKeyListener(searchAction);
        emailTF.addKeyListener(searchAction);
        telefonoTF.addKeyListener(searchAction);
        idClienteFTF.addKeyListener(searchAction);
        
        estadoCB.setRenderer(new EstadoClienteCBRenderer());
        estadoCB.addItemListener(searchAction);
        generoCB.setRenderer(new GeneroCBRenderer());
        generoCB.addItemListener(searchAction);

        empleadoCB.setRenderer(new EmpleadoCBRenderer());
        empleadoCB.addItemListener(searchAction);

        cargarComboBox();
        resultadosTable.setDefaultRenderer(Object.class, new ClienteTableCellRenderer());

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

                if (row >= 0 && column == 9) {
                    ClienteDTO cliente = ((ClienteTableModel) resultadosTable.getModel()).getClienteAt(row);

                    if (cliente == null) {
                        return;
                    }

                    Object[] options = { "Editar", "Eliminar", "Cancelar" };
                    int option = JOptionPane.showOptionDialog(ClienteSearchView.this,
                            "Cliente: " + cliente.getNombre() + " " + cliente.getApellido1(),
                            "Acciones",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);

                    if (option == 0) {
                        ClienteAltaView editView = new ClienteAltaView();
                        ClienteSetEditableController setEditableController = new ClienteSetEditableController(editView, cliente);
                        setEditableController.doAction();
                        MainWindow.getInstance().addView("Editar cliente", editView);
                    } else if (option == 1) {
                        ClienteDeleteController deleteController = new ClienteDeleteController(ClienteSearchView.this, cliente);
                        deleteController.doAction();
                    }
                }
            }
        });
    }

    private void cargarComboBox() {
        List<EstadoCliente> estados = null;
        try {
            estados = estadoClienteService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultComboBoxModel<EstadoCliente> estadoModel = new DefaultComboBoxModel<>();
        EstadoCliente es = new EstadoCliente();
        es.setId(null);
        es.setNombre("Seleccionar");
        estadoModel.addElement(es);
        if (estados != null) {
            for (EstadoCliente ec : estados) {
                estadoModel.addElement(ec);
            }
        }
        estadoCB.setModel(estadoModel);

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

        List<EmpleadoDTO> empleados = null;
        try {
            empleados = empleadoService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DefaultComboBoxModel<EmpleadoDTO> empleadoModel = new DefaultComboBoxModel<>();
        EmpleadoDTO em = new EmpleadoDTO();
        em.setId(null);
        em.setNombre("Seleccionar");
        em.setApellido1("");
        empleadoModel.addElement(em);
        if (empleados != null) {
            for (EmpleadoDTO e : empleados) {
                empleadoModel.addElement(e);
            }
        }
        empleadoCB.setModel(empleadoModel);
    }

    private void limpiarCampos() {
        idClienteFTF.setText("");
        nombreTF.setText("");
        apellido1TF.setText("");
        apellido2TF.setText("");
        dniTF.setText("");
        emailTF.setText("");
        telefonoTF.setText("");
        fechaNacimientoDesdeDC.setDate(null);
        fechaNacimientoHastaDC.setDate(null);
        fechaCreacionDesdeDC.setDate(null);
        fechaCreacionHastaDC.setDate(null);
        estadoCB.setSelectedIndex(0);
        generoCB.setSelectedIndex(0);

        fullModel = null;
        currentPage = 1;
        totalPages = 1;
        updateView();
    }

    public ClienteCriteria getCriteria() {
        ClienteCriteria criteria = new ClienteCriteria();
        criteria.setId(StringUtils.isBlank(idClienteFTF.getText()) ? null : Long.parseLong(idClienteFTF.getText()));
        criteria.setNombre(StringUtils.trimToNull(nombreTF.getText()));
        criteria.setApellido1(StringUtils.trimToNull(apellido1TF.getText()));
        criteria.setApellido2(StringUtils.trimToNull(apellido2TF.getText()));
        criteria.setDni(StringUtils.trimToNull(dniTF.getText()));
        criteria.setEmail(StringUtils.trimToNull(emailTF.getText()));
        criteria.setTelefono(StringUtils.trimToNull(telefonoTF.getText()));
        criteria.setFechaNacimientoDesde(fechaNacimientoDesdeDC.getDate());
        criteria.setFechaNacimientoHasta(fechaNacimientoHastaDC.getDate());
        criteria.setFechaCreacionDesde(fechaCreacionDesdeDC.getDate());
        criteria.setFechaCreacionHasta(fechaCreacionHastaDC.getDate());
        EstadoCliente estado = (EstadoCliente) estadoCB.getSelectedItem();
        criteria.setEstadoClienteId(estado != null && estado.getId() != null ? estado.getId() : null);
        Genero genero = (Genero) generoCB.getSelectedItem();
        criteria.setGeneroId(genero != null && genero.getId() != null ? genero.getId() : null);
        EmpleadoDTO empleado = (EmpleadoDTO) empleadoCB.getSelectedItem();
        criteria.setEmpleadoAsignadoId(empleado != null && empleado.getId() != null ? empleado.getId() : null);
        return criteria;
    }

    public void setModel(Results<ClienteDTO> model) {
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
        ClienteCriteria criteria = getCriteria();
        try {
            Results<ClienteDTO> resultados = clienteService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
            setModel(resultados);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateView() {
        if (fullModel == null) {
            resultadosTable.setModel(new ClienteTableModel(new ArrayList<>()));
            totalResultadosLabel.setText("0 resultados encontrados.");
            updatePaginationControls();
            return;
        }

        List<ClienteDTO> allClientes = fullModel.getPage();
        int totalRecords = fullModel.getTotal();
        if (totalRecords == 0 && allClientes != null) {
            totalRecords = allClientes.size();
        }

        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalRecords);

        List<ClienteDTO> pageList;
        if (fromIndex < allClientes.size()) {
            pageList = allClientes.subList(fromIndex, Math.min(toIndex, allClientes.size()));
        } else {
            pageList = new ArrayList<>();
        }

        ClienteTableModel tableModel = new ClienteTableModel(pageList);
        resultadosTable.setModel(tableModel);
        resultadosTable.setDefaultRenderer(Object.class, new ClienteTableCellRenderer());
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