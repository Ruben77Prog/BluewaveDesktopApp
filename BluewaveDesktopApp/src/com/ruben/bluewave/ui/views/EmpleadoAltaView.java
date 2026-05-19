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
import com.ruben.bluewave.model.Direccion;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.Genero;
import com.ruben.bluewave.model.Pais;
import com.ruben.bluewave.model.Provincia;
import com.ruben.bluewave.model.Rol;
import com.ruben.bluewave.service.CiudadService;
import com.ruben.bluewave.service.DireccionService;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.GeneroService;
import com.ruben.bluewave.service.PaisService;
import com.ruben.bluewave.service.ProvinciaService;
import com.ruben.bluewave.service.RolService;
import com.ruben.bluewave.service.impl.CiudadServiceImpl;
import com.ruben.bluewave.service.impl.DireccionServiceImpl;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.service.impl.GeneroServiceImpl;
import com.ruben.bluewave.service.impl.PaisServiceImpl;
import com.ruben.bluewave.service.impl.ProvinciaServiceImpl;
import com.ruben.bluewave.service.impl.RolServiceImpl;
import com.ruben.bluewave.ui.controller.AbstractController;
import com.ruben.bluewave.ui.renderer.GeneroCBRenderer;
import com.ruben.bluewave.ui.renderer.RolCBRenderer;
import com.toedter.calendar.JDateChooser;

public class EmpleadoAltaView extends AbstractView {

    private EmpleadoService empleadoService;
    private GeneroService generoService;
    private RolService rolService;
    private PaisService paisService;
    private ProvinciaService provinciaService;
    private CiudadService ciudadService;
    private DireccionService direccionService;

    private JTextField nombreTF;
    private JTextField apellido1TF;
    private JTextField apellido2TF;
    private JTextField dniTF;
    private JTextField telefonoTF;
    private JTextField emailTF;
    private JTextField passwordTF;
    private JDateChooser fechaContratacionDC;
    private JComboBox<Genero> generoCB;
    private JComboBox<Rol> rolCB;

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

    public EmpleadoAltaView() {
        initServices();
        initialize();
        postInitialize();
    }

    private void initServices() {
        empleadoService = new EmpleadoServiceImpl();
        generoService = new GeneroServiceImpl();
        rolService = new RolServiceImpl();
        paisService = new PaisServiceImpl();
        provinciaService = new ProvinciaServiceImpl();
        ciudadService = new CiudadServiceImpl();
        direccionService = new DireccionServiceImpl();
    }

    private void initialize() {
        setName("Alta de empleado");
        setLayout(new BorderLayout(0, 0));

        JPanel formPanel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
        gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl.columnWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        formPanel.setLayout(gbl);

        JLabel nombreLabel = new JLabel("Nombre:");
        GridBagConstraints gbcNombreLabel = new GridBagConstraints();
        gbcNombreLabel.insets = new Insets(0, 0, 5, 5);
        gbcNombreLabel.gridx = 0;
        gbcNombreLabel.gridy = 0;
        formPanel.add(nombreLabel, gbcNombreLabel);

        nombreTF = new JTextField();
        nombreTF.setColumns(12);
        GridBagConstraints gbcNombreTF = new GridBagConstraints();
        gbcNombreTF.insets = new Insets(0, 0, 5, 5);
        gbcNombreTF.fill = GridBagConstraints.HORIZONTAL;
        gbcNombreTF.gridx = 1;
        gbcNombreTF.gridy = 0;
        formPanel.add(nombreTF, gbcNombreTF);

        JLabel apellido1Label = new JLabel("Primer Apellido:");
        GridBagConstraints gbcApellido1Label = new GridBagConstraints();
        gbcApellido1Label.insets = new Insets(0, 0, 5, 5);
        gbcApellido1Label.gridx = 2;
        gbcApellido1Label.gridy = 0;
        formPanel.add(apellido1Label, gbcApellido1Label);

        apellido1TF = new JTextField();
        apellido1TF.setColumns(12);
        GridBagConstraints gbcApellido1TF = new GridBagConstraints();
        gbcApellido1TF.insets = new Insets(0, 0, 5, 5);
        gbcApellido1TF.fill = GridBagConstraints.HORIZONTAL;
        gbcApellido1TF.gridx = 3;
        gbcApellido1TF.gridy = 0;
        formPanel.add(apellido1TF, gbcApellido1TF);

        JLabel apellido2Label = new JLabel("Segundo Apellido:");
        GridBagConstraints gbcApellido2Label = new GridBagConstraints();
        gbcApellido2Label.insets = new Insets(0, 0, 5, 5);
        gbcApellido2Label.gridx = 0;
        gbcApellido2Label.gridy = 1;
        formPanel.add(apellido2Label, gbcApellido2Label);

        apellido2TF = new JTextField();
        apellido2TF.setColumns(12);
        GridBagConstraints gbcApellido2TF = new GridBagConstraints();
        gbcApellido2TF.insets = new Insets(0, 0, 5, 5);
        gbcApellido2TF.fill = GridBagConstraints.HORIZONTAL;
        gbcApellido2TF.gridx = 1;
        gbcApellido2TF.gridy = 1;
        formPanel.add(apellido2TF, gbcApellido2TF);

        JLabel dniLabel = new JLabel("DNI:");
        GridBagConstraints gbcDniLabel = new GridBagConstraints();
        gbcDniLabel.insets = new Insets(0, 0, 5, 5);
        gbcDniLabel.gridx = 2;
        gbcDniLabel.gridy = 1;
        formPanel.add(dniLabel, gbcDniLabel);

        dniTF = new JTextField();
        dniTF.setColumns(12);
        GridBagConstraints gbcDniTF = new GridBagConstraints();
        gbcDniTF.insets = new Insets(0, 0, 5, 5);
        gbcDniTF.fill = GridBagConstraints.HORIZONTAL;
        gbcDniTF.gridx = 3;
        gbcDniTF.gridy = 1;
        formPanel.add(dniTF, gbcDniTF);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        GridBagConstraints gbcTelefonoLabel = new GridBagConstraints();
        gbcTelefonoLabel.insets = new Insets(0, 0, 5, 5);
        gbcTelefonoLabel.gridx = 0;
        gbcTelefonoLabel.gridy = 2;
        formPanel.add(telefonoLabel, gbcTelefonoLabel);

        telefonoTF = new JTextField();
        telefonoTF.setColumns(12);
        GridBagConstraints gbcTelefonoTF = new GridBagConstraints();
        gbcTelefonoTF.insets = new Insets(0, 0, 5, 5);
        gbcTelefonoTF.fill = GridBagConstraints.HORIZONTAL;
        gbcTelefonoTF.gridx = 1;
        gbcTelefonoTF.gridy = 2;
        formPanel.add(telefonoTF, gbcTelefonoTF);

        JLabel emailLabel = new JLabel("Email:");
        GridBagConstraints gbcEmailLabel = new GridBagConstraints();
        gbcEmailLabel.insets = new Insets(0, 0, 5, 5);
        gbcEmailLabel.gridx = 2;
        gbcEmailLabel.gridy = 2;
        formPanel.add(emailLabel, gbcEmailLabel);

        emailTF = new JTextField();
        emailTF.setColumns(12);
        GridBagConstraints gbcEmailTF = new GridBagConstraints();
        gbcEmailTF.insets = new Insets(0, 0, 5, 5);
        gbcEmailTF.fill = GridBagConstraints.HORIZONTAL;
        gbcEmailTF.gridx = 3;
        gbcEmailTF.gridy = 2;
        formPanel.add(emailTF, gbcEmailTF);

        JLabel passwordLabel = new JLabel("Contraseña:");
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.insets = new Insets(0, 0, 5, 5);
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 3;
        formPanel.add(passwordLabel, gbcPasswordLabel);

        passwordTF = new JTextField();
        passwordTF.setColumns(12);
        GridBagConstraints gbcPasswordTF = new GridBagConstraints();
        gbcPasswordTF.insets = new Insets(0, 0, 5, 5);
        gbcPasswordTF.fill = GridBagConstraints.HORIZONTAL;
        gbcPasswordTF.gridx = 1;
        gbcPasswordTF.gridy = 3;
        formPanel.add(passwordTF, gbcPasswordTF);

        JLabel rolLabel = new JLabel("Rol:");
        GridBagConstraints gbcRolLabel = new GridBagConstraints();
        gbcRolLabel.insets = new Insets(0, 0, 5, 5);
        gbcRolLabel.gridx = 2;
        gbcRolLabel.gridy = 3;
        formPanel.add(rolLabel, gbcRolLabel);

        rolCB = new JComboBox<>();
        GridBagConstraints gbcRolCB = new GridBagConstraints();
        gbcRolCB.insets = new Insets(0, 0, 5, 5);
        gbcRolCB.fill = GridBagConstraints.HORIZONTAL;
        gbcRolCB.gridx = 3;
        gbcRolCB.gridy = 3;
        formPanel.add(rolCB, gbcRolCB);

        JLabel generoLabel = new JLabel("Género:");
        GridBagConstraints gbcGeneroLabel = new GridBagConstraints();
        gbcGeneroLabel.insets = new Insets(0, 0, 5, 5);
        gbcGeneroLabel.gridx = 0;
        gbcGeneroLabel.gridy = 4;
        formPanel.add(generoLabel, gbcGeneroLabel);

        generoCB = new JComboBox<>();
        GridBagConstraints gbcGeneroCB = new GridBagConstraints();
        gbcGeneroCB.insets = new Insets(0, 0, 5, 5);
        gbcGeneroCB.fill = GridBagConstraints.HORIZONTAL;
        gbcGeneroCB.gridx = 1;
        gbcGeneroCB.gridy = 4;
        formPanel.add(generoCB, gbcGeneroCB);

        JLabel fechaContratacionLabel = new JLabel("Fecha Contratación:");
        GridBagConstraints gbcFechaContratacionLabel = new GridBagConstraints();
        gbcFechaContratacionLabel.insets = new Insets(0, 0, 5, 5);
        gbcFechaContratacionLabel.gridx = 2;
        gbcFechaContratacionLabel.gridy = 4;
        formPanel.add(fechaContratacionLabel, gbcFechaContratacionLabel);

        fechaContratacionDC = new JDateChooser();
        GridBagConstraints gbcFechaContratacionDC = new GridBagConstraints();
        gbcFechaContratacionDC.insets = new Insets(0, 0, 5, 5);
        gbcFechaContratacionDC.fill = GridBagConstraints.HORIZONTAL;
        gbcFechaContratacionDC.gridx = 3;
        gbcFechaContratacionDC.gridy = 4;
        formPanel.add(fechaContratacionDC, gbcFechaContratacionDC);

        JLabel paisLabel = new JLabel("País:");
        GridBagConstraints gbcPaisLabel = new GridBagConstraints();
        gbcPaisLabel.insets = new Insets(0, 0, 5, 5);
        gbcPaisLabel.gridx = 0;
        gbcPaisLabel.gridy = 5;
        formPanel.add(paisLabel, gbcPaisLabel);

        paisCB = new JComboBox<>();
        GridBagConstraints gbcPaisCB = new GridBagConstraints();
        gbcPaisCB.insets = new Insets(0, 0, 5, 5);
        gbcPaisCB.fill = GridBagConstraints.HORIZONTAL;
        gbcPaisCB.gridx = 1;
        gbcPaisCB.gridy = 5;
        formPanel.add(paisCB, gbcPaisCB);
        paisCB.addActionListener(e -> cargarProvincias());

        JLabel provinciaLabel = new JLabel("Provincia:");
        GridBagConstraints gbcProvinciaLabel = new GridBagConstraints();
        gbcProvinciaLabel.insets = new Insets(0, 0, 5, 5);
        gbcProvinciaLabel.gridx = 2;
        gbcProvinciaLabel.gridy = 5;
        formPanel.add(provinciaLabel, gbcProvinciaLabel);

        provinciaCB = new JComboBox<>();
        GridBagConstraints gbcProvinciaCB = new GridBagConstraints();
        gbcProvinciaCB.insets = new Insets(0, 0, 5, 5);
        gbcProvinciaCB.fill = GridBagConstraints.HORIZONTAL;
        gbcProvinciaCB.gridx = 3;
        gbcProvinciaCB.gridy = 5;
        formPanel.add(provinciaCB, gbcProvinciaCB);
        provinciaCB.addActionListener(e -> cargarCiudades());

        JLabel ciudadLabel = new JLabel("Ciudad:");
        GridBagConstraints gbcCiudadLabel = new GridBagConstraints();
        gbcCiudadLabel.insets = new Insets(0, 0, 5, 5);
        gbcCiudadLabel.gridx = 0;
        gbcCiudadLabel.gridy = 6;
        formPanel.add(ciudadLabel, gbcCiudadLabel);

        ciudadCB = new JComboBox<>();
        GridBagConstraints gbcCiudadCB = new GridBagConstraints();
        gbcCiudadCB.insets = new Insets(0, 0, 5, 5);
        gbcCiudadCB.fill = GridBagConstraints.HORIZONTAL;
        gbcCiudadCB.gridx = 1;
        gbcCiudadCB.gridy = 6;
        formPanel.add(ciudadCB, gbcCiudadCB);

        JLabel calleLabel = new JLabel("Calle:");
        GridBagConstraints gbcCalleLabel = new GridBagConstraints();
        gbcCalleLabel.insets = new Insets(0, 0, 5, 5);
        gbcCalleLabel.gridx = 0;
        gbcCalleLabel.gridy = 7;
        formPanel.add(calleLabel, gbcCalleLabel);

        calleTF = new JTextField();
        calleTF.setColumns(12);
        GridBagConstraints gbcCalleTF = new GridBagConstraints();
        gbcCalleTF.insets = new Insets(0, 0, 5, 5);
        gbcCalleTF.fill = GridBagConstraints.HORIZONTAL;
        gbcCalleTF.gridx = 1;
        gbcCalleTF.gridy = 7;
        formPanel.add(calleTF, gbcCalleTF);

        JLabel numeroLabel = new JLabel("Número:");
        GridBagConstraints gbcNumeroLabel = new GridBagConstraints();
        gbcNumeroLabel.insets = new Insets(0, 0, 5, 5);
        gbcNumeroLabel.gridx = 2;
        gbcNumeroLabel.gridy = 7;
        formPanel.add(numeroLabel, gbcNumeroLabel);

        numeroTF = new JTextField();
        numeroTF.setColumns(12);
        GridBagConstraints gbcNumeroTF = new GridBagConstraints();
        gbcNumeroTF.insets = new Insets(0, 0, 5, 5);
        gbcNumeroTF.fill = GridBagConstraints.HORIZONTAL;
        gbcNumeroTF.gridx = 3;
        gbcNumeroTF.gridy = 7;
        formPanel.add(numeroTF, gbcNumeroTF);

        JLabel pisoLabel = new JLabel("Piso / Bloque:");
        GridBagConstraints gbcPisoLabel = new GridBagConstraints();
        gbcPisoLabel.insets = new Insets(0, 0, 5, 5);
        gbcPisoLabel.gridx = 0;
        gbcPisoLabel.gridy = 8;
        formPanel.add(pisoLabel, gbcPisoLabel);

        pisoTF = new JTextField();
        pisoTF.setColumns(12);
        GridBagConstraints gbcPisoTF = new GridBagConstraints();
        gbcPisoTF.insets = new Insets(0, 0, 5, 5);
        gbcPisoTF.fill = GridBagConstraints.HORIZONTAL;
        gbcPisoTF.gridx = 1;
        gbcPisoTF.gridy = 8;
        formPanel.add(pisoTF, gbcPisoTF);

        JLabel puertaLabel = new JLabel("Puerta / Apartamento:");
        GridBagConstraints gbcPuertaLabel = new GridBagConstraints();
        gbcPuertaLabel.insets = new Insets(0, 0, 5, 5);
        gbcPuertaLabel.gridx = 2;
        gbcPuertaLabel.gridy = 8;
        formPanel.add(puertaLabel, gbcPuertaLabel);

        puertaTF = new JTextField();
        puertaTF.setColumns(12);
        GridBagConstraints gbcPuertaTF = new GridBagConstraints();
        gbcPuertaTF.insets = new Insets(0, 0, 5, 5);
        gbcPuertaTF.fill = GridBagConstraints.HORIZONTAL;
        gbcPuertaTF.gridx = 3;
        gbcPuertaTF.gridy = 8;
        formPanel.add(puertaTF, gbcPuertaTF);

        JLabel codigoPostalLabel = new JLabel("Código Postal:");
        GridBagConstraints gbcCodigoPostalLabel = new GridBagConstraints();
        gbcCodigoPostalLabel.insets = new Insets(0, 0, 5, 5);
        gbcCodigoPostalLabel.gridx = 0;
        gbcCodigoPostalLabel.gridy = 9;
        formPanel.add(codigoPostalLabel, gbcCodigoPostalLabel);

        codigoPostalTF = new JTextField();
        codigoPostalTF.setColumns(12);
        GridBagConstraints gbcCodigoPostalTF = new GridBagConstraints();
        gbcCodigoPostalTF.insets = new Insets(0, 0, 5, 5);
        gbcCodigoPostalTF.fill = GridBagConstraints.HORIZONTAL;
        gbcCodigoPostalTF.gridx = 1;
        gbcCodigoPostalTF.gridy = 9;
        formPanel.add(codigoPostalTF, gbcCodigoPostalTF);

        JScrollPane scrollPane = new JScrollPane(formPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        guardarButton = new JButton("Guardar");
        guardarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1511_mount_zip_mount_zip.png")));
        guardarButton.addActionListener(e -> crearEmpleado());
        buttonPanel.add(guardarButton);

        JButton limpiarButton = new JButton("Limpiar");
        limpiarButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1250_delete_delete.png")));
        limpiarButton.addActionListener(e -> limpiarCampos());
        buttonPanel.add(limpiarButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void postInitialize() {
        cargarGeneros();
        cargarRoles();
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
            for (Genero g : generos) {
                model.addElement(g);
            }
            generoCB.setModel(model);
            generoCB.setRenderer(new GeneroCBRenderer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cargarRoles() {
        try {
            List<Rol> roles = rolService.findAll();
            DefaultComboBoxModel<Rol> model = new DefaultComboBoxModel<>();
            Rol placeholder = new Rol();
            placeholder.setId(null);
            placeholder.setNombre("Seleccionar");
            model.addElement(placeholder);
            for (Rol r : roles) {
                model.addElement(r);
            }
            rolCB.setModel(model);
            rolCB.setRenderer(new RolCBRenderer());
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
            for (Pais p : paises) {
                model.addElement(p);
            }
            paisCB.setModel(model);
            paisCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
                public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Pais) {
                        setText(((Pais) value).getNombre());
                    }
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
            for (Provincia p : provincias) {
                model.addElement(p);
            }
            provinciaCB.setModel(model);
            provinciaCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
                public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Provincia) {
                        setText(((Provincia) value).getNombre());
                    }
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
            for (Ciudad c : ciudades) {
                model.addElement(c);
            }
            ciudadCB.setModel(model);
            ciudadCB.setRenderer(new javax.swing.DefaultListCellRenderer() {
                public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value,
                        int index, boolean isSelected, boolean cellHasFocus) {
                    super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    if (value instanceof Ciudad) {
                        setText(((Ciudad) value).getNombre());
                    }
                    return this;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error cargando ciudades", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void crearEmpleado() {
        try {
            if (nombreTF.getText().trim().isEmpty() || apellido1TF.getText().trim().isEmpty() ||
                dniTF.getText().trim().isEmpty() || emailTF.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre, primer apellido, DNI y email son obligatorios.",
                        "Datos incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Pais pais = (Pais) paisCB.getSelectedItem();
            Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
            Ciudad ciudad = (Ciudad) ciudadCB.getSelectedItem();
            
            if (pais == null || pais.getId() == null || provincia == null || provincia.getId() == null || 
                ciudad == null || ciudad.getId() == null) {
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
                JOptionPane.showMessageDialog(this, "Error al guardar la dirección", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            EmpleadoDTO empleado = getModel();
            if (empleado == null) return;
            
            empleado.setDireccionId(direccionGuardada.getId());

            Long idEmpleado = empleadoService.create(empleado);
            if (idEmpleado != null) {
                JOptionPane.showMessageDialog(this, "Empleado creado correctamente con ID: " + idEmpleado);
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear el empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public EmpleadoDTO getModel() {
        if (nombreTF.getText().trim().isEmpty() || apellido1TF.getText().trim().isEmpty() ||
            dniTF.getText().trim().isEmpty() || emailTF.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre, primer apellido, DNI y email son obligatorios.",
                    "Datos incompletos", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        EmpleadoDTO empleado = new EmpleadoDTO();
        empleado.setNombre(nombreTF.getText().trim());
        empleado.setApellido1(apellido1TF.getText().trim());
        empleado.setApellido2(apellido2TF.getText().trim());
        empleado.setDni(dniTF.getText().trim());
        empleado.setTelefono(telefonoTF.getText().trim());
        empleado.setEmail(emailTF.getText().trim());
        empleado.setFechaCreacion(fechaContratacionDC.getDate());
        empleado.setPassword(passwordTF.getText().trim());
        empleado.setActivo(true);

        Genero genero = (Genero) generoCB.getSelectedItem();
        if (genero != null && genero.getId() != null) {
            empleado.setGeneroId(genero.getId());
        } else {
            empleado.setGeneroId(1L);
        }

        Rol rol = (Rol) rolCB.getSelectedItem();
        if (rol != null && rol.getId() != null) {
            empleado.setRolId(rol.getId());
        } else {
            empleado.setRolId(1L);
        }

        return empleado;
    }

    public Direccion getDireccionFromForm() {
        Pais pais = (Pais) paisCB.getSelectedItem();
        Provincia provincia = (Provincia) provinciaCB.getSelectedItem();
        Ciudad ciudad = (Ciudad) ciudadCB.getSelectedItem();
        
        if (pais == null || pais.getId() == null || provincia == null || provincia.getId() == null || 
            ciudad == null || ciudad.getId() == null) {
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
        passwordTF.setText("");
        fechaContratacionDC.setDate(null);
        generoCB.setSelectedIndex(0);
        rolCB.setSelectedIndex(0);
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
        passwordTF.setEditable(editable);
        fechaContratacionDC.setEnabled(editable);
        generoCB.setEnabled(editable);
        rolCB.setEnabled(editable);
        calleTF.setEditable(editable);
        numeroTF.setEditable(editable);
        pisoTF.setEditable(editable);
        puertaTF.setEditable(editable);
        codigoPostalTF.setEditable(editable);
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

    public void cargarEmpleado(EmpleadoDTO empleado) {
        nombreTF.setText(empleado.getNombre() != null ? empleado.getNombre() : "");
        apellido1TF.setText(empleado.getApellido1() != null ? empleado.getApellido1() : "");
        apellido2TF.setText(empleado.getApellido2() != null ? empleado.getApellido2() : "");
        dniTF.setText(empleado.getDni() != null ? empleado.getDni() : "");
        telefonoTF.setText(empleado.getTelefono() != null ? empleado.getTelefono() : "");
        emailTF.setText(empleado.getEmail() != null ? empleado.getEmail() : "");
        fechaContratacionDC.setDate(empleado.getFechaCreacion());

        if (empleado.getGeneroId() != null) {
            for (int i = 0; i < generoCB.getItemCount(); i++) {
                Genero g = generoCB.getItemAt(i);
                if (g.getId() != null && g.getId().equals(empleado.getGeneroId())) {
                    generoCB.setSelectedIndex(i);
                    i = generoCB.getItemCount();
                }
            }
        }

        if (empleado.getRolId() != null) {
            for (int i = 0; i < rolCB.getItemCount(); i++) {
                Rol r = rolCB.getItemAt(i);
                if (r.getId() != null && r.getId().equals(empleado.getRolId())) {
                    rolCB.setSelectedIndex(i);
                    i = rolCB.getItemCount();
                }
            }
        }

        if (empleado.getDireccionId() != null) {
            try {
                Direccion direccion = direccionService.findById(empleado.getDireccionId());
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
}