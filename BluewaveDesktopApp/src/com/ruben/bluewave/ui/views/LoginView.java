package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.ruben.bluewave.ui.controller.LoginController;

public class LoginView extends AbstractView {

    private JTextField emailTF;
    private JPasswordField passwordPF;
    private JComboBox<String> tipoUsuarioCB;
    private JButton loginButton;
    private LoginController loginController;

    public LoginView() {
        initialize();
        postInitialize();
    }

    private void initialize() {
        setName("Login");
        setLayout(new BorderLayout(0, 0));
        setBorder(new EmptyBorder(50, 50, 50, 50));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        add(centerPanel, BorderLayout.CENTER);

        JPanel loginPanel = new JPanel();
        loginPanel.setBorder(new EmptyBorder(30, 40, 30, 40));
        GridBagLayout gbl = new GridBagLayout();
        gbl.columnWidths = new int[] { 0, 0, 0 };
        gbl.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gbl.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        loginPanel.setLayout(gbl);

        JLabel titleLabel = new JLabel("Bluewave");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        GridBagConstraints gbcTitle = new GridBagConstraints();
        gbcTitle.insets = new Insets(0, 0, 30, 0);
        gbcTitle.gridx = 1;
        gbcTitle.gridy = 0;
        loginPanel.add(titleLabel, gbcTitle);

        JLabel subtitleLabel = new JLabel("Sistema de Gestión");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        GridBagConstraints gbcSubtitle = new GridBagConstraints();
        gbcSubtitle.insets = new Insets(0, 0, 40, 0);
        gbcSubtitle.gridx = 1;
        gbcSubtitle.gridy = 1;
        loginPanel.add(subtitleLabel, gbcSubtitle);

        JLabel emailLabel = new JLabel("Email:");
        GridBagConstraints gbcEmailLabel = new GridBagConstraints();
        gbcEmailLabel.insets = new Insets(0, 0, 5, 10);
        gbcEmailLabel.gridx = 0;
        gbcEmailLabel.gridy = 2;
        loginPanel.add(emailLabel, gbcEmailLabel);

        emailTF = new JTextField(20);
        GridBagConstraints gbcEmail = new GridBagConstraints();
        gbcEmail.insets = new Insets(0, 0, 5, 0);
        gbcEmail.fill = GridBagConstraints.HORIZONTAL;
        gbcEmail.gridx = 1;
        gbcEmail.gridy = 2;
        loginPanel.add(emailTF, gbcEmail);

        JLabel passwordLabel = new JLabel("Contraseña:");
        GridBagConstraints gbcPasswordLabel = new GridBagConstraints();
        gbcPasswordLabel.insets = new Insets(0, 0, 5, 10);
        gbcPasswordLabel.gridx = 0;
        gbcPasswordLabel.gridy = 3;
        loginPanel.add(passwordLabel, gbcPasswordLabel);

        passwordPF = new JPasswordField(20);
        GridBagConstraints gbcPassword = new GridBagConstraints();
        gbcPassword.insets = new Insets(0, 0, 5, 0);
        gbcPassword.fill = GridBagConstraints.HORIZONTAL;
        gbcPassword.gridx = 1;
        gbcPassword.gridy = 3;
        loginPanel.add(passwordPF, gbcPassword);

        JLabel tipoUsuarioLabel = new JLabel("Tipo Usuario:");
        GridBagConstraints gbcTipoUsuarioLabel = new GridBagConstraints();
        gbcTipoUsuarioLabel.insets = new Insets(0, 0, 5, 10);
        gbcTipoUsuarioLabel.gridx = 0;
        gbcTipoUsuarioLabel.gridy = 4;
        loginPanel.add(tipoUsuarioLabel, gbcTipoUsuarioLabel);

        tipoUsuarioCB = new JComboBox<>();
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        model.addElement("Administrador");
        model.addElement("Empleado");
        tipoUsuarioCB.setModel(model);
        GridBagConstraints gbcTipoUsuarioCB = new GridBagConstraints();
        gbcTipoUsuarioCB.insets = new Insets(0, 0, 5, 0);
        gbcTipoUsuarioCB.fill = GridBagConstraints.HORIZONTAL;
        gbcTipoUsuarioCB.gridx = 1;
        gbcTipoUsuarioCB.gridy = 4;
        loginPanel.add(tipoUsuarioCB, gbcTipoUsuarioCB);

        loginButton = new JButton("Iniciar Sesión");
        loginButton.setIcon(new ImageIcon(getClass().getResource("/nuvola/16x16/1339_kmag_kmag.png")));
        GridBagConstraints gbcButton = new GridBagConstraints();
        gbcButton.insets = new Insets(20, 0, 0, 0);
        gbcButton.gridx = 1;
        gbcButton.gridy = 5;
        loginPanel.add(loginButton, gbcButton);

        GridBagConstraints gbcPanel = new GridBagConstraints();
        gbcPanel.gridx = 0;
        gbcPanel.gridy = 0;
        centerPanel.add(loginPanel, gbcPanel);
    }

    private void postInitialize() {
        loginController = new LoginController(this);
        loginButton.addActionListener(loginController);
        passwordPF.addActionListener(loginController);
        emailTF.addActionListener(loginController);
        
    }

    public String getEmail() {
        return emailTF.getText().trim();
    }

    public String getPassword() {
        return new String(passwordPF.getPassword());
    }

    public String getTipoUsuario() {
        return (String) tipoUsuarioCB.getSelectedItem();
    }

    public void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error de autenticación", JOptionPane.ERROR_MESSAGE);
        limpiarCampos();
    }

    public void limpiarCampos() {
        emailTF.setText("");
        passwordPF.setText("");
        emailTF.requestFocus();
    }
}