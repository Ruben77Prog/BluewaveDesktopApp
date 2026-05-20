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
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.ruben.bluewave.model.PlanDTO;
import com.ruben.bluewave.model.TipoPlan;
import com.ruben.bluewave.service.PlanService;
import com.ruben.bluewave.service.TipoPlanService;
import com.ruben.bluewave.service.impl.PlanServiceImpl;
import com.ruben.bluewave.service.impl.TipoPlanServiceImpl;
import com.ruben.bluewave.ui.controller.AbstractController;

public class PlanAltaView extends AbstractView {

    private PlanService planService;
    private TipoPlanService tipoPlanService;

    private JTextField nombreTF;
    private JTextField descripcionTF;
    private JTextField precioTF;
    private JTextField duracionMesesTF;
    private JTextField descuentoTF;
    private JComboBox<TipoPlan> tipoPlanCB;
    private JCheckBox activoCB;

    private boolean editable = false;
    private JButton guardarButton;

    public PlanAltaView() {
        initServices();
        initialize();
        postInitialize();
    }

    private void initServices() {
        planService = new PlanServiceImpl();
        tipoPlanService = new TipoPlanServiceImpl();
    }

    private void initialize() {
        setName("Alta de plan");
        setLayout(new BorderLayout(0, 0));

        JPanel formPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        formPanel.setLayout(gbl);

        JLabel nombreLabel = new JLabel("Nombre:");
        GridBagConstraints gbcNombreLabel = new GridBagConstraints();
        gbcNombreLabel.insets = new Insets(0, 0, 5, 5);
        gbcNombreLabel.gridx = 0;
        gbcNombreLabel.gridy = 0;
        formPanel.add(nombreLabel, gbcNombreLabel);

        nombreTF = new JTextField();
        nombreTF.setColumns(20);
        GridBagConstraints gbcNombreTF = new GridBagConstraints();
        gbcNombreTF.insets = new Insets(0, 0, 5, 5);
        gbcNombreTF.fill = GridBagConstraints.HORIZONTAL;
        gbcNombreTF.gridx = 1;
        gbcNombreTF.gridy = 0;
        formPanel.add(nombreTF, gbcNombreTF);

        JLabel descripcionLabel = new JLabel("Descripción:");
        GridBagConstraints gbcDescripcionLabel = new GridBagConstraints();
        gbcDescripcionLabel.insets = new Insets(0, 0, 5, 5);
        gbcDescripcionLabel.gridx = 0;
        gbcDescripcionLabel.gridy = 1;
        formPanel.add(descripcionLabel, gbcDescripcionLabel);

        descripcionTF = new JTextField();
        descripcionTF.setColumns(20);
        GridBagConstraints gbcDescripcionTF = new GridBagConstraints();
        gbcDescripcionTF.insets = new Insets(0, 0, 5, 5);
        gbcDescripcionTF.fill = GridBagConstraints.HORIZONTAL;
        gbcDescripcionTF.gridx = 1;
        gbcDescripcionTF.gridy = 1;
        formPanel.add(descripcionTF, gbcDescripcionTF);

        JLabel precioLabel = new JLabel("Precio (€):");
        GridBagConstraints gbcPrecioLabel = new GridBagConstraints();
        gbcPrecioLabel.insets = new Insets(0, 0, 5, 5);
        gbcPrecioLabel.gridx = 0;
        gbcPrecioLabel.gridy = 2;
        formPanel.add(precioLabel, gbcPrecioLabel);

        precioTF = new JTextField();
        precioTF.setColumns(10);
        GridBagConstraints gbcPrecioTF = new GridBagConstraints();
        gbcPrecioTF.insets = new Insets(0, 0, 5, 5);
        gbcPrecioTF.fill = GridBagConstraints.HORIZONTAL;
        gbcPrecioTF.gridx = 1;
        gbcPrecioTF.gridy = 2;
        formPanel.add(precioTF, gbcPrecioTF);

        JLabel duracionLabel = new JLabel("Duración (meses):");
        GridBagConstraints gbcDuracionLabel = new GridBagConstraints();
        gbcDuracionLabel.insets = new Insets(0, 0, 5, 5);
        gbcDuracionLabel.gridx = 0;
        gbcDuracionLabel.gridy = 3;
        formPanel.add(duracionLabel, gbcDuracionLabel);

        duracionMesesTF = new JTextField();
        duracionMesesTF.setColumns(10);
        GridBagConstraints gbcDuracionTF = new GridBagConstraints();
        gbcDuracionTF.insets = new Insets(0, 0, 5, 5);
        gbcDuracionTF.fill = GridBagConstraints.HORIZONTAL;
        gbcDuracionTF.gridx = 1;
        gbcDuracionTF.gridy = 3;
        formPanel.add(duracionMesesTF, gbcDuracionTF);

        JLabel descuentoLabel = new JLabel("Descuento (%):");
        GridBagConstraints gbcDescuentoLabel = new GridBagConstraints();
        gbcDescuentoLabel.insets = new Insets(0, 0, 5, 5);
        gbcDescuentoLabel.gridx = 0;
        gbcDescuentoLabel.gridy = 4;
        formPanel.add(descuentoLabel, gbcDescuentoLabel);

        descuentoTF = new JTextField();
        descuentoTF.setColumns(10);
        GridBagConstraints gbcDescuentoTF = new GridBagConstraints();
        gbcDescuentoTF.insets = new Insets(0, 0, 5, 5);
        gbcDescuentoTF.fill = GridBagConstraints.HORIZONTAL;
        gbcDescuentoTF.gridx = 1;
        gbcDescuentoTF.gridy = 4;
        formPanel.add(descuentoTF, gbcDescuentoTF);

        JLabel tipoPlanLabel = new JLabel("Tipo de Plan:");
        GridBagConstraints gbcTipoPlanLabel = new GridBagConstraints();
        gbcTipoPlanLabel.insets = new Insets(0, 0, 5, 5);
        gbcTipoPlanLabel.gridx = 0;
        gbcTipoPlanLabel.gridy = 5;
        formPanel.add(tipoPlanLabel, gbcTipoPlanLabel);

        tipoPlanCB = new JComboBox<>();
        GridBagConstraints gbcTipoPlanCB = new GridBagConstraints();
        gbcTipoPlanCB.insets = new Insets(0, 0, 5, 5);
        gbcTipoPlanCB.fill = GridBagConstraints.HORIZONTAL;
        gbcTipoPlanCB.gridx = 1;
        gbcTipoPlanCB.gridy = 5;
        formPanel.add(tipoPlanCB, gbcTipoPlanCB);

        JLabel activoLabel = new JLabel("Activo:");
        GridBagConstraints gbcActivoLabel = new GridBagConstraints();
        gbcActivoLabel.insets = new Insets(0, 0, 5, 5);
        gbcActivoLabel.gridx = 0;
        gbcActivoLabel.gridy = 6;
        formPanel.add(activoLabel, gbcActivoLabel);

        activoCB = new JCheckBox();
        activoCB.setSelected(true);
        GridBagConstraints gbcActivoCB = new GridBagConstraints();
        gbcActivoCB.insets = new Insets(0, 0, 5, 5);
        gbcActivoCB.fill = GridBagConstraints.HORIZONTAL;
        gbcActivoCB.gridx = 1;
        gbcActivoCB.gridy = 6;
        formPanel.add(activoCB, gbcActivoCB);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        guardarButton = new JButton("Guardar");
        guardarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1511_mount_zip_mount_zip.png")));
        guardarButton.addActionListener(e -> crearPlan());
        buttonPanel.add(guardarButton);

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png")));
        limpiarButton.addActionListener(e -> limpiarCampos());
        buttonPanel.add(limpiarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void postInitialize() {
        cargarTiposPlan();
    }

    private void cargarTiposPlan() {
        try {
            List<TipoPlan> tipos = tipoPlanService.findAll();
            DefaultComboBoxModel<TipoPlan> model = new DefaultComboBoxModel<>();
            TipoPlan placeholder = new TipoPlan();
            placeholder.setId(null);
            placeholder.setNombre("Seleccionar");
            model.addElement(placeholder);
            for (TipoPlan t : tipos) {
                model.addElement(t);
            }
            tipoPlanCB.setModel(model);
            tipoPlanCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
                public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof TipoPlan) {
                        setText(((TipoPlan) value).getNombre());
                    }
                    return this;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearPlan() {
        try {
            if (nombreTF.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            TipoPlan tipoPlan = (TipoPlan) tipoPlanCB.getSelectedItem();
            if (tipoPlan == null || tipoPlan.getId() == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de plan.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            PlanDTO plan = getModel();
            if (plan == null) return;

            Long idPlan = planService.create(plan);
            if (idPlan != null) {
                JOptionPane.showMessageDialog(this, "Plan creado correctamente con ID: " + idPlan);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el plan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public PlanDTO getModel() {
        if (nombreTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre es obligatorio.", "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        PlanDTO plan = new PlanDTO();
        plan.setNombre(nombreTF.getText().trim());
        plan.setDescripcion(descripcionTF.getText().trim());
        
        try {
            plan.setPrecio(Double.parseDouble(precioTF.getText().trim()));
        } catch (NumberFormatException e) {
            plan.setPrecio(0.0);
        }
        
        try {
            plan.setDuracionMeses(Integer.parseInt(duracionMesesTF.getText().trim()));
        } catch (NumberFormatException e) {
            plan.setDuracionMeses(12);
        }
        
        try {
            plan.setDescuento(Double.parseDouble(descuentoTF.getText().trim()));
        } catch (NumberFormatException e) {
            plan.setDescuento(0.0);
        }

        TipoPlan tipoPlan = (TipoPlan) tipoPlanCB.getSelectedItem();
        if (tipoPlan != null && tipoPlan.getId() != null) {
            plan.setTipoPlanId(tipoPlan.getId());
        }

        plan.setActivo(activoCB.isSelected());

        return plan;
    }

    public void limpiarCampos() {
        nombreTF.setText("");
        descripcionTF.setText("");
        precioTF.setText("");
        duracionMesesTF.setText("");
        descuentoTF.setText("");
        tipoPlanCB.setSelectedIndex(0);
        activoCB.setSelected(true);
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
        nombreTF.setEditable(editable);
        descripcionTF.setEditable(editable);
        precioTF.setEditable(editable);
        duracionMesesTF.setEditable(editable);
        descuentoTF.setEditable(editable);
        tipoPlanCB.setEnabled(editable);
        activoCB.setEnabled(editable);

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

    public void cargarPlan(PlanDTO plan) {
        nombreTF.setText(plan.getNombre() != null ? plan.getNombre() : "");
        descripcionTF.setText(plan.getDescripcion() != null ? plan.getDescripcion() : "");
        precioTF.setText(plan.getPrecio() != null ? String.valueOf(plan.getPrecio()) : "");
        duracionMesesTF.setText(plan.getDuracionMeses() != null ? String.valueOf(plan.getDuracionMeses()) : "");
        descuentoTF.setText(plan.getDescuento() != null ? String.valueOf(plan.getDescuento()) : "");
        activoCB.setSelected(plan.getActivo() != null ? plan.getActivo() : true);

        if (plan.getTipoPlanId() != null) {
            for (int i = 0; i < tipoPlanCB.getItemCount(); i++) {
                TipoPlan tp = tipoPlanCB.getItemAt(i);
                if (tp.getId() != null && tp.getId().equals(plan.getTipoPlanId())) {
                    tipoPlanCB.setSelectedIndex(i);
                    i = tipoPlanCB.getItemCount();
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