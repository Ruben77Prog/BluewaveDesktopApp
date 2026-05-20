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

import com.ruben.bluewave.dao.criteria.PlanCriteria;
import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.TipoPlanService;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.service.impl.TipoPlanServiceImpl;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.controller.PlanDeleteController;
import com.ruben.bluewave.ui.controller.PlanSearchController;
import com.ruben.bluewave.ui.controller.PlanSetEditableController;
import com.ruben.bluewave.ui.model.PlanTableModel;
import com.ruben.bluewave.ui.renderer.PlanTableCellRenderer;
import com.ruben.bluewave.ui.renderer.TipoPlanCBRenderer;

public class PlanSearchView extends AbstractView {

    private PlanService planService;
    private TipoPlanService tipoPlanService;

    private JFormattedTextField idPlanFTF;
    private JTextField nombreTF;
    private JComboBox<TipoPlan> tipoPlanCB;
    private JComboBox<String> activoCB;

    private JTable resultadosTable;
    private Results<PlanDTO> fullModel;
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;

    private JButton buscarButton;
    private JLabel totalResultadosLabel;
    private JButton firstButton, prevButton, nextButton, lastButton;
    private JLabel pageInfoLabel;

    public PlanSearchView() {
        initServices();
        initialize();
        postInitialize();
    }

    private void initServices() {
        planService = new PlanServiceImpl();
        tipoPlanService = new TipoPlanServiceImpl();
    }

    private void initialize() {
        setName("Búsqueda de planes");
        setLayout(new BorderLayout(0, 0));

        JPanel busquedaPanel = new JPanel();
        add(busquedaPanel, BorderLayout.NORTH);
        GridBagLayout gbl_busquedaPanel = new GridBagLayout();
        gbl_busquedaPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_busquedaPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_busquedaPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_busquedaPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        busquedaPanel.setLayout(gbl_busquedaPanel);

        JLabel idPlanLabel = new JLabel("ID Plan");
        GridBagConstraints gbc_idPlanLabel = new GridBagConstraints();
        gbc_idPlanLabel.insets = new Insets(0, 0, 5, 5);
        gbc_idPlanLabel.gridx = 1;
        gbc_idPlanLabel.gridy = 0;
        busquedaPanel.add(idPlanLabel, gbc_idPlanLabel);

        JLabel nombreLabel = new JLabel("Nombre");
        GridBagConstraints gbc_nombreLabel = new GridBagConstraints();
        gbc_nombreLabel.insets = new Insets(0, 0, 5, 5);
        gbc_nombreLabel.gridx = 2;
        gbc_nombreLabel.gridy = 0;
        busquedaPanel.add(nombreLabel, gbc_nombreLabel);

        JLabel tipoPlanLabel = new JLabel("Tipo Plan");
        GridBagConstraints gbc_tipoPlanLabel = new GridBagConstraints();
        gbc_tipoPlanLabel.insets = new Insets(0, 0, 5, 5);
        gbc_tipoPlanLabel.gridx = 3;
        gbc_tipoPlanLabel.gridy = 0;
        busquedaPanel.add(tipoPlanLabel, gbc_tipoPlanLabel);

        JLabel activoLabel = new JLabel("Activo");
        GridBagConstraints gbc_activoLabel = new GridBagConstraints();
        gbc_activoLabel.insets = new Insets(0, 0, 5, 5);
        gbc_activoLabel.gridx = 4;
        gbc_activoLabel.gridy = 0;
        busquedaPanel.add(activoLabel, gbc_activoLabel);

        idPlanFTF = new JFormattedTextField();
        GridBagConstraints gbc_idPlanFTF = new GridBagConstraints();
        gbc_idPlanFTF.insets = new Insets(0, 0, 5, 5);
        gbc_idPlanFTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_idPlanFTF.gridx = 1;
        gbc_idPlanFTF.gridy = 1;
        busquedaPanel.add(idPlanFTF, gbc_idPlanFTF);

        nombreTF = new JTextField(15);
        GridBagConstraints gbc_nombreTF = new GridBagConstraints();
        gbc_nombreTF.insets = new Insets(0, 0, 5, 5);
        gbc_nombreTF.fill = GridBagConstraints.HORIZONTAL;
        gbc_nombreTF.gridx = 2;
        gbc_nombreTF.gridy = 1;
        busquedaPanel.add(nombreTF, gbc_nombreTF);

        tipoPlanCB = new JComboBox<>();
        GridBagConstraints gbc_tipoPlanCB = new GridBagConstraints();
        gbc_tipoPlanCB.insets = new Insets(0, 0, 5, 5);
        gbc_tipoPlanCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_tipoPlanCB.gridx = 3;
        gbc_tipoPlanCB.gridy = 1;
        busquedaPanel.add(tipoPlanCB, gbc_tipoPlanCB);

        activoCB = new JComboBox<>(new String[] { "Seleccionar", "Activo", "Inactivo" });
        GridBagConstraints gbc_activoCB = new GridBagConstraints();
        gbc_activoCB.insets = new Insets(0, 0, 5, 5);
        gbc_activoCB.fill = GridBagConstraints.HORIZONTAL;
        gbc_activoCB.gridx = 4;
        gbc_activoCB.gridy = 1;
        busquedaPanel.add(activoCB, gbc_activoCB);

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

    private void postInitialize() {
        PlanSearchController searchAction = new PlanSearchController(this);
        buscarButton.setAction(searchAction);
        nombreTF.addKeyListener(searchAction);
        tipoPlanCB.addItemListener(searchAction);
        activoCB.addItemListener(searchAction);

        cargarCombos();
        resultadosTable.setDefaultRenderer(Object.class, new PlanTableCellRenderer());

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

                if (row >= 0 && column == 6) {
                    PlanDTO plan = ((PlanTableModel) resultadosTable.getModel()).getPlanAt(row);

                    if (plan == null) {
                        return;
                    }

                    Object[] options = { "Editar", "Eliminar", "Cancelar" };
                    int option = JOptionPane.showOptionDialog(PlanSearchView.this,
                            "Plan: " + plan.getNombre(),
                            "Acciones",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[2]);

                    if (option == 0) {
                        PlanAltaView editView = new PlanAltaView();
                        PlanSetEditableController setEditableController = new PlanSetEditableController(editView, plan);
                        setEditableController.doAction();
                        MainWindow.getInstance().addView("Editar plan", editView);
                    } else if (option == 1) {
                        PlanDeleteController deleteController = new PlanDeleteController(PlanSearchView.this, plan);
                        deleteController.doAction();
                    }
                }
            }
        });
    }

    private void cargarCombos() {
        try {
            List<TipoPlan> tipos = tipoPlanService.findAll();
            DefaultComboBoxModel<TipoPlan> model = new DefaultComboBoxModel<>();
            TipoPlan placeholder = new TipoPlan();
            placeholder.setId(null);
            placeholder.setNombre("Seleccionar");
            model.addElement(placeholder);
            for (TipoPlan tp : tipos) {
                model.addElement(tp);
            }
            tipoPlanCB.setModel(model);
            tipoPlanCB.setRenderer(new TipoPlanCBRenderer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        idPlanFTF.setText("");
        nombreTF.setText("");
        tipoPlanCB.setSelectedIndex(0);
        activoCB.setSelectedIndex(0);
        fullModel = null;
        currentPage = 1;
        totalPages = 1;
        updateView();
    }

    public PlanCriteria getCriteria() {
        PlanCriteria criteria = new PlanCriteria();

        criteria.setId(StringUtils.isBlank(idPlanFTF.getText()) ? null : Long.parseLong(idPlanFTF.getText()));
        criteria.setNombre(StringUtils.trimToNull(nombreTF.getText()));

        TipoPlan tipoPlan = (TipoPlan) tipoPlanCB.getSelectedItem();
        criteria.setTipoPlanId(tipoPlan != null && tipoPlan.getId() != null ? tipoPlan.getId() : null);

        int activoIdx = activoCB.getSelectedIndex();
        if (activoIdx == 1) {
            criteria.setActivo(true);
        } else if (activoIdx == 2) {
            criteria.setActivo(false);
        } else {
            criteria.setActivo(null);
        }

        return criteria;
    }

    public void setModel(Results<PlanDTO> model) {
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
        PlanCriteria criteria = getCriteria();
        try {
            Results<PlanDTO> resultados = planService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
            setModel(resultados);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al buscar planes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateView() {
        if (fullModel == null) {
            resultadosTable.setModel(new PlanTableModel(new ArrayList<PlanDTO>()));
            totalResultadosLabel.setText("0 resultados encontrados.");
            updatePaginationControls();
            return;
        }

        List<PlanDTO> allPlanes = fullModel.getPage();
        int totalRecords = fullModel.getTotal();
        if (totalRecords == 0 && allPlanes != null) {
            totalRecords = allPlanes.size();
        }

        int fromIndex = (currentPage - 1) * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalRecords);

        List<PlanDTO> pageList;
        if (fromIndex < allPlanes.size()) {
            pageList = allPlanes.subList(fromIndex, Math.min(toIndex, allPlanes.size()));
        } else {
            pageList = new ArrayList<PlanDTO>();
        }

        PlanTableModel tableModel = new PlanTableModel(pageList);
        resultadosTable.setModel(tableModel);
        resultadosTable.setDefaultRenderer(Object.class, new PlanTableCellRenderer());
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