package com.ruben.bluewave.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;

import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.ui.controller.OpenClienteSearchController;
import com.ruben.bluewave.ui.controller.OpenEmpleadoSearchController;
import com.ruben.bluewave.ui.controller.OpenIncidenciaSearchController;
import com.ruben.bluewave.ui.controller.OpenNuevaIncidenciaController;
import com.ruben.bluewave.ui.views.ClienteAltaView;
import com.ruben.bluewave.ui.views.ClienteSearchView;
import com.ruben.bluewave.ui.views.EmpleadoAltaView;
import com.ruben.bluewave.ui.views.EmpleadoSearchView;
import com.ruben.bluewave.ui.views.IncidenciaSearchView;
import com.ruben.bluewave.ui.views.LoginView;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;
import com.ruben.bluewave.ui.views.View;

public class MainWindow {

	private JFrame frame;
	private JPanel centerPanel;
	private JTabbedPane contentTabbedPane;
	private static MainWindow instance;

	private EmpleadoDTO usuarioActual;
	private boolean isAdmin;
	private JMenuBar menuBar;
	private JToolBar mainToolBar;

	private JButton buscarClienteButton;
	private JButton anadirClienteButton;
	private JButton buscarEmpleadoButton;
	private JButton nuevoEmpleadoButton;
	private JButton buscarIncidenciaButton;
	private JButton nuevaIncidenciaButton;

	private JMenuItem buscarClienteMenuItem;
	private JMenuItem nuevoClienteMenuItem;
	private JMenuItem buscarEmpleadoMenuItem;
	private JMenuItem nuevoEmpleadoMenuItem;
	private JMenuItem buscarIncidenciaMenuItem;
	private JMenuItem nuevaIncidenciaMenuItem;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.formdev.flatlaf.themes.FlatMacLightLaf");
					MainWindow window = MainWindow.getInstance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private MainWindow() {
		initialize();
		postInitialize();
		showLogin();
	}

	public static MainWindow getInstance() {
		if (instance == null) {
			instance = new MainWindow();
		}
		return instance;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(550, 200, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));

		JPanel northPanel = new JPanel();
		mainPanel.add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JPanel menuPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) menuPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		northPanel.add(menuPanel, BorderLayout.NORTH);

		menuBar = new JMenuBar();
		menuPanel.add(menuBar);

		JMenu clienteMenu = new JMenu("Cliente");
		menuBar.add(clienteMenu);

		buscarClienteMenuItem = new JMenuItem("Buscar...");
		clienteMenu.add(buscarClienteMenuItem);

		nuevoClienteMenuItem = new JMenuItem("Nuevo...");
		clienteMenu.add(nuevoClienteMenuItem);

		JMenu usuarioMenu = new JMenu("Usuario");
		menuBar.add(usuarioMenu);

		buscarEmpleadoMenuItem = new JMenuItem("Buscar...");
		usuarioMenu.add(buscarEmpleadoMenuItem);

		nuevoEmpleadoMenuItem = new JMenuItem("Nuevo...");
		usuarioMenu.add(nuevoEmpleadoMenuItem);

		JMenu contratoMenu = new JMenu("Contrato");
		menuBar.add(contratoMenu);

		JMenu incidenciaMenu = new JMenu("Incidencia");
		menuBar.add(incidenciaMenu);

		buscarIncidenciaMenuItem = new JMenuItem("Buscar...");
		incidenciaMenu.add(buscarIncidenciaMenuItem);

		nuevaIncidenciaMenuItem = new JMenuItem("Nueva...");
		incidenciaMenu.add(nuevaIncidenciaMenuItem);

		JMenu sesionMenu = new JMenu("Sesión");
		menuBar.add(sesionMenu);

		JMenuItem cerrarSesionMenuItem = new JMenuItem("Cerrar sesión");
		cerrarSesionMenuItem.addActionListener(e -> cerrarSesion());
		sesionMenu.add(cerrarSesionMenuItem);

		JMenuItem salirMenuItem = new JMenuItem("Salir");
		salirMenuItem.addActionListener(e -> System.exit(0));
		sesionMenu.add(salirMenuItem);

		JPanel toolbarPanel = new JPanel();
		northPanel.add(toolbarPanel, BorderLayout.SOUTH);
		toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		mainToolBar = new JToolBar();
		toolbarPanel.add(mainToolBar);

		buscarClienteButton = new JButton("");
		buscarClienteButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		buscarClienteButton.setToolTipText("Buscar...");
		mainToolBar.add(buscarClienteButton);

		anadirClienteButton = new JButton("");
		anadirClienteButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		anadirClienteButton.setToolTipText("Nuevo...");
		mainToolBar.add(anadirClienteButton);

		mainToolBar.addSeparator();

		buscarEmpleadoButton = new JButton("");
		buscarEmpleadoButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		buscarEmpleadoButton.setToolTipText("Buscar...");
		mainToolBar.add(buscarEmpleadoButton);

		nuevoEmpleadoButton = new JButton("");
		nuevoEmpleadoButton.setIcon(new ImageIcon(MainWindow.class.getResource(
				"/nuvola/16x16/1447_man_male_male_man_user_employee_manager_employee_operator_manager_personal_operator_administrator_administrator_personal_user.png")));
		nuevoEmpleadoButton.setToolTipText("Nuevo...");
		mainToolBar.add(nuevoEmpleadoButton);

		mainToolBar.addSeparator();

		buscarIncidenciaButton = new JButton("");
		buscarIncidenciaButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		buscarIncidenciaButton.setToolTipText("Buscar...");
		mainToolBar.add(buscarIncidenciaButton);

		nuevaIncidenciaButton = new JButton("");
		nuevaIncidenciaButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		nuevaIncidenciaButton.setToolTipText("Nueva...");
		mainToolBar.add(nuevaIncidenciaButton);

		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));

		contentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(contentTabbedPane, BorderLayout.CENTER);
	}

	private void postInitialize() {
		OpenClienteSearchController clienteSearchController = new OpenClienteSearchController();

		buscarClienteButton.setAction(clienteSearchController);
		buscarClienteMenuItem.setAction(clienteSearchController);

		nuevoClienteMenuItem.addActionListener(e -> {
			ClienteAltaView view = new ClienteAltaView();
			addView(view.getName(), view);
		});

		anadirClienteButton.addActionListener(e -> {
			ClienteAltaView view = new ClienteAltaView();
			addView(view.getName(), view);
		});

		OpenEmpleadoSearchController empleadoSearchController = new OpenEmpleadoSearchController();
		buscarEmpleadoButton.setAction(empleadoSearchController);
		buscarEmpleadoMenuItem.setAction(empleadoSearchController);

		nuevoEmpleadoMenuItem.addActionListener(e -> {
			EmpleadoAltaView view = new EmpleadoAltaView();
			addView(view.getName(), view);
		});

		nuevoEmpleadoButton.addActionListener(e -> {
			EmpleadoAltaView view = new EmpleadoAltaView();
			addView(view.getName(), view);
		});

		OpenIncidenciaSearchController incidenciaSearchController = new OpenIncidenciaSearchController();
		buscarIncidenciaButton.setAction(incidenciaSearchController);
		buscarIncidenciaMenuItem.setAction(incidenciaSearchController);

		OpenNuevaIncidenciaController nuevaIncidenciaController = new OpenNuevaIncidenciaController();
		nuevaIncidenciaButton.setAction(nuevaIncidenciaController);
		nuevaIncidenciaMenuItem.setAction(nuevaIncidenciaController);
	}

	private void aplicarPermisos() {
		if (isAdmin) {

			buscarEmpleadoButton.setVisible(true);
			nuevoEmpleadoButton.setVisible(true);
			buscarEmpleadoMenuItem.setVisible(true);
			nuevoEmpleadoMenuItem.setVisible(true);
			anadirClienteButton.setVisible(true);
			nuevoClienteMenuItem.setVisible(true);
		} else {

			buscarEmpleadoButton.setVisible(false);
			nuevoEmpleadoButton.setVisible(false);
			buscarEmpleadoMenuItem.setVisible(false);
			nuevoEmpleadoMenuItem.setVisible(false);
			anadirClienteButton.setVisible(false);
			nuevoClienteMenuItem.setVisible(false);
		}
	}

	public void showLogin() {
		LoginView loginView = new LoginView();
		contentTabbedPane.removeAll();
		contentTabbedPane.addTab("Login", loginView);
		menuBar.setVisible(false);
		mainToolBar.setVisible(false);
	}

	public void loginSuccess(EmpleadoDTO usuario, boolean isAdmin) {
		this.usuarioActual = usuario;
		this.isAdmin = isAdmin;

		String rol = isAdmin ? "Administrador" : "Empleado";
		frame.setTitle("Bluewave - Usuario: " + usuario.getNombre() + " " + usuario.getApellido1() + " (" + rol + ")");

		contentTabbedPane.removeAll();
		menuBar.setVisible(true);
		mainToolBar.setVisible(true);

		aplicarPermisos();

		JPanel welcomePanel = new JPanel();
		addView("Bienvenido", welcomePanel);
	}

	public void cerrarSesion() {
		usuarioActual = null;
		isAdmin = false;
		frame.setTitle("Bluewave");
		showLogin();
	}

	public void addView(String titulo, JPanel contenido) {
		contentTabbedPane.addTab(titulo, contenido);
		int index = contentTabbedPane.indexOfComponent(contenido);
		contentTabbedPane.setTabComponentAt(index, new ClosableTabComponent(contentTabbedPane));
	}

	public void removeView(View view) {
		contentTabbedPane.remove((Component) view);
	}

	public boolean isAdmin() {
		return isAdmin;
	}
}