package com.ruben.bluewave.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import com.ruben.bluewave.ui.controller.OpenClienteSearchController;
import com.ruben.bluewave.ui.controller.OpenEmpleadoSearchController;
import com.ruben.bluewave.ui.controller.OpenIncidenciaSearchController;
import com.ruben.bluewave.ui.controller.OpenNuevaIncidenciaController;
import com.ruben.bluewave.ui.views.ClienteSearchView;
import com.ruben.bluewave.ui.views.ClienteAltaView;
import com.ruben.bluewave.ui.views.IncidenciaSearchView;
import com.ruben.bluewave.ui.views.NuevaIncidenciaView;
import com.ruben.bluewave.ui.views.View;

/**
 * Ventana de la aplicacion implementada como singleton
 */
public class MainWindow {

	private JFrame frame;
	private JPanel centerPanel;
	private JTabbedPane contentTabbedPane;
	private JButton buscarClienteButton;
	private JMenuItem buscarClienteMenuItem;
	private static MainWindow instance;

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

		JMenuBar menuBar = new JMenuBar();
		menuPanel.add(menuBar);

		JMenu clienteMenu = new JMenu("Cliente");
		menuBar.add(clienteMenu);

		buscarClienteMenuItem = new JMenuItem("Buscar...");
		buscarClienteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteSearchView csv = new ClienteSearchView();
				addView(csv.getName(), csv);
			}
		});
		clienteMenu.add(buscarClienteMenuItem);

		JMenuItem nuevoClienteMenuItem = new JMenuItem("Nuevo...");
		nuevoClienteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteAltaView ncv = new ClienteAltaView();
				addView(ncv.getName(), ncv);
			}
		});
		clienteMenu.add(nuevoClienteMenuItem);

		
		JMenu usuarioMenu = new JMenu("Usuario");
		menuBar.add(usuarioMenu);

		JMenuItem nuevoUsuarioMenuItem = new JMenuItem("Nuevo...");
		nuevoUsuarioMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		usuarioMenu.add(nuevoUsuarioMenuItem);

		JMenuItem buscarUsuarioMenuItem = new JMenuItem("Buscar...");
		usuarioMenu.add(buscarUsuarioMenuItem);

		JMenuItem buscarEmpleadoMenuItem = new JMenuItem("Buscar empleado...");
		buscarEmpleadoMenuItem.addActionListener(new OpenEmpleadoSearchController());
		usuarioMenu.add(buscarEmpleadoMenuItem);

		
		JMenu contratoMenu = new JMenu("Contrato");
		menuBar.add(contratoMenu);
		JMenuItem nuevoContratoMenuItem = new JMenuItem("Nuevo...");
		contratoMenu.add(nuevoContratoMenuItem);

		
		JMenu incidenciaMenu = new JMenu("Incidencia");
		menuBar.add(incidenciaMenu);

		JMenuItem buscarIncidenciaMenuItem = new JMenuItem("Buscar incidencia...");
		buscarIncidenciaMenuItem.addActionListener(new OpenIncidenciaSearchController());
		incidenciaMenu.add(buscarIncidenciaMenuItem);

		JMenuItem nuevaIncidenciaMenuItem = new JMenuItem("Nueva...");
		nuevaIncidenciaMenuItem.addActionListener(new OpenNuevaIncidenciaController());
		incidenciaMenu.add(nuevaIncidenciaMenuItem);

		JMenu subMenuBuscar = new JMenu("Buscar...");
		incidenciaMenu.add(subMenuBuscar);
		JMenuItem tipoIncidenciaMenuItem = new JMenuItem("Tipo");
		subMenuBuscar.add(tipoIncidenciaMenuItem);
		JMenuItem prioridadMenuItem = new JMenuItem("Prioridad");
		subMenuBuscar.add(prioridadMenuItem);

		JMenu estadisticaNewMenu = new JMenu("Estadistica");
		menuBar.add(estadisticaNewMenu);

		JPanel toolbarPanel = new JPanel();
		northPanel.add(toolbarPanel, BorderLayout.SOUTH);
		toolbarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

		JToolBar clienteToolBar = new JToolBar();
		toolbarPanel.add(clienteToolBar);

		buscarClienteButton = new JButton("");
		buscarClienteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteSearchView csv = new ClienteSearchView();
				contentTabbedPane.addTab(csv.getName(), csv);
				addView(csv.getName(), csv);
				contentTabbedPane.revalidate();
			}
		});
		buscarClienteButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		clienteToolBar.add(buscarClienteButton);

		JButton anadirClienteButton = new JButton("");
		anadirClienteButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        ClienteAltaView ncv = new ClienteAltaView();  
			        addView(ncv.getName(), ncv);
			}
		});
		anadirClienteButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		clienteToolBar.add(anadirClienteButton);

		JToolBar usuarioToolBar = new JToolBar();
		toolbarPanel.add(usuarioToolBar);

		JButton nuevoUsuarioButton = new JButton("");
		nuevoUsuarioButton.setIcon(new ImageIcon(MainWindow.class.getResource(
				"/nuvola/16x16/1447_man_male_male_man_user_employee_manager_employee_operator_manager_personal_operator_administrator_administrator_personal_user.png")));
		nuevoUsuarioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Abrir NuevaEmpleadoView (pendiente)
			}
		});
		usuarioToolBar.add(nuevoUsuarioButton);

		JButton buscarEmpleadoButton = new JButton("");
		buscarEmpleadoButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		buscarEmpleadoButton.addActionListener(new OpenEmpleadoSearchController());
		usuarioToolBar.add(buscarEmpleadoButton);

		JToolBar contratoToolBar = new JToolBar();
		toolbarPanel.add(contratoToolBar);
		JButton nuevoContratoButton = new JButton("");
		nuevoContratoButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1394_kthememgr_kthememgr.png")));
		contratoToolBar.add(nuevoContratoButton);
		JButton buscarContratoButton = new JButton("");
		buscarContratoButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1718_compfile_compfile.png")));
		contratoToolBar.add(buscarContratoButton);

		JToolBar incidenciaToolBar = new JToolBar();
		toolbarPanel.add(incidenciaToolBar);

		JButton nuevaIncidenciaButton = new JButton("");
		nuevaIncidenciaButton
				.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1875_viewmag+_viewmag+.png")));
		nuevaIncidenciaButton.addActionListener(new OpenNuevaIncidenciaController()); 
		incidenciaToolBar.add(nuevaIncidenciaButton);

		JButton buscarIncidenciaButton = new JButton("");
		buscarIncidenciaButton.setIcon(new ImageIcon(MainWindow.class.getResource("/nuvola/16x16/1339_kmag_kmag.png")));
		buscarIncidenciaButton.addActionListener(new OpenIncidenciaSearchController());
		incidenciaToolBar.add(buscarIncidenciaButton);

		centerPanel = new JPanel();
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BorderLayout(0, 0));
		contentTabbedPane = new JTabbedPane(JTabbedPane.TOP);
		centerPanel.add(contentTabbedPane, BorderLayout.CENTER);

		JPanel southPanel = new JPanel();
		mainPanel.add(southPanel, BorderLayout.SOUTH);
		JPanel eastPanel = new JPanel();
		mainPanel.add(eastPanel, BorderLayout.EAST);
		JPanel westPanel = new JPanel();
		mainPanel.add(westPanel, BorderLayout.WEST);
	}

	public void addView(String titulo, JPanel contenido) {
		contentTabbedPane.addTab(titulo, contenido);
		int index = contentTabbedPane.indexOfComponent(contenido);
		contentTabbedPane.setTabComponentAt(index, new ClosableTabComponent(contentTabbedPane));
	}

	public void removeView(View view) {
		contentTabbedPane.remove((Component) view);
	}

	private void postInitialize() {
		OpenClienteSearchController c = new OpenClienteSearchController();
		buscarClienteButton.addActionListener(c);
		buscarClienteMenuItem.setAction(c);
	}
}