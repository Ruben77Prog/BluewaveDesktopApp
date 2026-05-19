package com.ruben.bluewave.ui.views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.commons.lang3.StringUtils;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.ruben.bluewave.dao.criteria.ContratoCriteria;
import com.ruben.bluewave.model.ContratoDTO;
import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.model.EstadoIncidencia;
import com.ruben.bluewave.model.Incidencia;
import com.ruben.bluewave.model.IncidenciaDTO;
import com.ruben.bluewave.model.Results;
import com.ruben.bluewave.model.TipoIncidencia;
import com.ruben.bluewave.service.ContratoService;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.EstadoIncidenciaService;
import com.ruben.bluewave.service.IncidenciaService;
import com.ruben.bluewave.service.TipoIncidenciaService;
import com.ruben.bluewave.service.impl.ContratoServiceImpl;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.service.impl.EstadoIncidenciaServiceImpl;
import com.ruben.bluewave.service.impl.IncidenciaServiceImpl;
import com.ruben.bluewave.service.impl.TipoIncidenciaServiceImpl;
import com.ruben.bluewave.ui.controller.AbstractController;
import com.ruben.bluewave.ui.renderer.ContratoCBRenderer;
import com.ruben.bluewave.ui.renderer.EmpleadoCBRenderer;
import com.ruben.bluewave.ui.renderer.EstadoIncidenciaRenderer;
import com.ruben.bluewave.ui.renderer.TipoIncidenciaRenderer;
import com.toedter.calendar.JDateChooser;

public class NuevaIncidenciaView extends AbstractView {

	private IncidenciaService incidenciaService;
	private TipoIncidenciaService tipoService;
	private EstadoIncidenciaService estadoService;
	private ContratoService contratoService;
	private EmpleadoService empleadoService;

	private JTextField numeroTF;
	private JTextField tituloTF;
	private JComboBox<TipoIncidencia> tipoCB;
	private JComboBox<EstadoIncidencia> estadoCB;
	private JComboBox<ContratoDTO> contratoCB;
	private JComboBox<EmpleadoDTO> empleadoCB;
	private JDateChooser fechaIncidenciaDC;
	private JDateChooser fechaResolucionDC;
	private JTextField horasEstimadasTF;
	private JTextField horasRealesTF;
	private JTextField costeTF;
	private JTextArea descripcionTA;

	private boolean editable = false;
	private JButton guardarButton;

	public NuevaIncidenciaView() {
		initServices();
		initialize();
		postInitialize();
	}

	private void initServices() {
		tipoService = new TipoIncidenciaServiceImpl();
		estadoService = new EstadoIncidenciaServiceImpl();
		contratoService = new ContratoServiceImpl();
		empleadoService = new EmpleadoServiceImpl();
		incidenciaService = new IncidenciaServiceImpl();
	}

	private void initialize() {
		setName("Nueva incidencia");
		setLayout(new BorderLayout(0, 0));

		JPanel formularioPanel = new JPanel();
		formularioPanel.setLayout(new GridLayout(0, 2, 10, 10));
		add(formularioPanel, BorderLayout.CENTER);

		formularioPanel.add(new JLabel("Número:"));
		numeroTF = new JTextField();
		formularioPanel.add(numeroTF);

		formularioPanel.add(new JLabel("Título:"));
		tituloTF = new JTextField();
		formularioPanel.add(tituloTF);

		formularioPanel.add(new JLabel("Tipo incidencia:"));
		tipoCB = new JComboBox<>();
		formularioPanel.add(tipoCB);

		formularioPanel.add(new JLabel("Estado incidencia:"));
		estadoCB = new JComboBox<>();
		formularioPanel.add(estadoCB);

		formularioPanel.add(new JLabel("Contrato:"));
		contratoCB = new JComboBox<>();
		formularioPanel.add(contratoCB);

		formularioPanel.add(new JLabel("Empleado asignado:"));
		empleadoCB = new JComboBox<>();
		formularioPanel.add(empleadoCB);

		formularioPanel.add(new JLabel("Fecha incidencia:"));
		fechaIncidenciaDC = new JDateChooser();
		formularioPanel.add(fechaIncidenciaDC);

		formularioPanel.add(new JLabel("Fecha resolución:"));
		fechaResolucionDC = new JDateChooser();
		formularioPanel.add(fechaResolucionDC);

		formularioPanel.add(new JLabel("Horas estimadas:"));
		horasEstimadasTF = new JTextField();
		formularioPanel.add(horasEstimadasTF);

		formularioPanel.add(new JLabel("Horas reales:"));
		horasRealesTF = new JTextField();
		formularioPanel.add(horasRealesTF);

		formularioPanel.add(new JLabel("Coste reparación (€):"));
		costeTF = new JTextField();
		formularioPanel.add(costeTF);

		formularioPanel.add(new JLabel("Descripción:"));
		descripcionTA = new JTextArea(4, 30);
		descripcionTA.setLineWrap(true);
		descripcionTA.setWrapStyleWord(true);
		JScrollPane scrollDesc = new JScrollPane(descripcionTA);
		formularioPanel.add(scrollDesc);

		JPanel botonesPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) botonesPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(botonesPanel, BorderLayout.SOUTH);

		guardarButton = new JButton("");
		guardarButton.setIcon(
				new ImageIcon(ClienteAltaView.class.getResource("/nuvola/16x16/1511_mount_zip_mount_zip.png")));
		guardarButton.addActionListener(e -> guardar());
		botonesPanel.add(guardarButton);

		JButton limpiarButton = new JButton("");
		limpiarButton.setIcon(new ImageIcon(ClienteAltaView.class.getResource("/nuvola/16x16/1250_delete_delete.png")));
		limpiarButton.addActionListener(e -> limpiarCampos());
		botonesPanel.add(limpiarButton);
	}

	private void postInitialize() {
		cargarTipos();
		cargarEstados();
		cargarContratos();
		cargarEmpleados();
	}

	private void guardar() {
		try {
			Incidencia incidencia = getModel();
			if (incidencia == null)
				return;
			Long id = incidenciaService.create(incidencia);
			if (id != null) {
				JOptionPane.showMessageDialog(this, "Incidencia creada con ID: " + id);
				limpiarCampos();
			} else {
				JOptionPane.showMessageDialog(this, "Error al crear la incidencia", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}
	}

	public Incidencia getModel() {
		TipoIncidencia tipo = (TipoIncidencia) tipoCB.getSelectedItem();
		EstadoIncidencia estado = (EstadoIncidencia) estadoCB.getSelectedItem();
		ContratoDTO contrato = (ContratoDTO) contratoCB.getSelectedItem();
		EmpleadoDTO empleado = (EmpleadoDTO) empleadoCB.getSelectedItem();

		if (StringUtils.isBlank(numeroTF.getText()) || StringUtils.isBlank(tituloTF.getText()) || tipo == null
				|| tipo.getId() == null || estado == null || estado.getId() == null || contrato == null
				|| contrato.getId() == null || empleado == null || empleado.getId() == null) {
			JOptionPane.showMessageDialog(this, "Número, título, tipo, estado, contrato y empleado son obligatorios.",
					"Datos incompletos", JOptionPane.WARNING_MESSAGE);
			return null;
		}

		Incidencia incidencia = new Incidencia();
		incidencia.setNumeroIncidencia(StringUtils.trim(numeroTF.getText()));
		incidencia.setTitulo(StringUtils.trim(tituloTF.getText()));
		incidencia.setDescripcion(StringUtils.trimToNull(descripcionTA.getText()));
		incidencia.setFechaIncidencia(fechaIncidenciaDC.getDate());
		incidencia.setFechaResolucion(fechaResolucionDC.getDate());
		incidencia.setHorasEstimadas(parseDouble(horasEstimadasTF.getText()));
		incidencia.setHorasReales(parseDouble(horasRealesTF.getText()));
		incidencia.setCosteReparacion(parseDouble(costeTF.getText()));
		incidencia.setTipoIncidenciaId(tipo.getId());
		incidencia.setEstadoIncidenciaId(estado.getId());
		incidencia.setContratoId(contrato.getId());
		incidencia.setCreadorEmpleadoId(empleado.getId());
		return incidencia;
	}

	private Double parseDouble(String text) {
		if (StringUtils.isBlank(text))
			return null;
		try {
			return Double.valueOf(text.replace(',', '.'));
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public void limpiarCampos() {
		numeroTF.setText("");
		tituloTF.setText("");
		descripcionTA.setText("");
		fechaIncidenciaDC.setDate(null);
		fechaResolucionDC.setDate(null);
		horasEstimadasTF.setText("");
		horasRealesTF.setText("");
		costeTF.setText("");
		tipoCB.setSelectedIndex(0);
		estadoCB.setSelectedIndex(0);
		contratoCB.setSelectedIndex(0);
		empleadoCB.setSelectedIndex(0);
	}

	private void cargarTipos() {
		try {
			List<TipoIncidencia> tipos = tipoService.findAll();
			DefaultComboBoxModel<TipoIncidencia> model = new DefaultComboBoxModel<>();
			TipoIncidencia placeholder = new TipoIncidencia();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (TipoIncidencia t : tipos)
				model.addElement(t);
			tipoCB.setModel(model);
			tipoCB.setRenderer(new TipoIncidenciaRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarEstados() {
		try {
			List<EstadoIncidencia> estados = estadoService.findAll();
			DefaultComboBoxModel<EstadoIncidencia> model = new DefaultComboBoxModel<>();
			EstadoIncidencia placeholder = new EstadoIncidencia();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (EstadoIncidencia e : estados)
				model.addElement(e);
			estadoCB.setModel(model);
			estadoCB.setRenderer(new EstadoIncidenciaRenderer());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void cargarContratos() {
		try {
			ContratoCriteria criteria = new ContratoCriteria();
			Results<ContratoDTO> results = contratoService.findByCriteria(criteria, 0, Integer.MAX_VALUE);
			List<ContratoDTO> contratos = results.getPage();

			DefaultComboBoxModel<ContratoDTO> model = new DefaultComboBoxModel<>();
			ContratoDTO placeholder = new ContratoDTO();
			placeholder.setId(null);
			placeholder.setNumeroContrato("Seleccionar");
			model.addElement(placeholder);
			for (ContratoDTO c : contratos) {
				model.addElement(c);
			}

			contratoCB.setModel(model);
			contratoCB.setRenderer(new ContratoCBRenderer());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error cargando contratos: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarEmpleados() {
		try {
			List<EmpleadoDTO> empleados = empleadoService.findAll();
			DefaultComboBoxModel<EmpleadoDTO> model = new DefaultComboBoxModel<>();
			EmpleadoDTO placeholder = new EmpleadoDTO();
			placeholder.setId(null);
			placeholder.setNombre("Seleccionar");
			model.addElement(placeholder);
			for (EmpleadoDTO e : empleados)
				model.addElement(e);
			empleadoCB.setModel(model);
			empleadoCB.setRenderer(new EmpleadoCBRenderer());
			AutoCompleteDecorator.decorate(empleadoCB);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public void setEditable(boolean editable) {
		this.editable = editable;
		numeroTF.setEditable(editable);
		tituloTF.setEditable(editable);
		descripcionTA.setEditable(editable);
		tipoCB.setEnabled(editable);
		estadoCB.setEnabled(editable);
		contratoCB.setEnabled(editable);
		empleadoCB.setEnabled(editable);
		fechaIncidenciaDC.setEnabled(editable);
		fechaResolucionDC.setEnabled(editable);
		horasEstimadasTF.setEditable(editable);
		horasRealesTF.setEditable(editable);
		costeTF.setEditable(editable);

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

	public void cargarIncidencia(IncidenciaDTO incidencia) {
	    numeroTF.setText(incidencia.getNumeroIncidencia() != null ? incidencia.getNumeroIncidencia() : "");
	    tituloTF.setText(incidencia.getTitulo() != null ? incidencia.getTitulo() : "");
	    descripcionTA.setText(incidencia.getDescripcion() != null ? incidencia.getDescripcion() : "");
	    fechaIncidenciaDC.setDate(incidencia.getFechaIncidencia());
	    fechaResolucionDC.setDate(incidencia.getFechaResolucion());
	    horasEstimadasTF.setText(incidencia.getHorasEstimadas() != null ? String.valueOf(incidencia.getHorasEstimadas()) : "");
	    horasRealesTF.setText(incidencia.getHorasReales() != null ? String.valueOf(incidencia.getHorasReales()) : "");
	    costeTF.setText(incidencia.getCosteReparacion() != null ? String.valueOf(incidencia.getCosteReparacion()) : "");
	    
	    if (incidencia.getTipoIncidenciaId() != null) {
	        for (int i = 0; i < tipoCB.getItemCount(); i++) {
	            TipoIncidencia t = tipoCB.getItemAt(i);
	            if (t.getId() != null && t.getId().equals(incidencia.getTipoIncidenciaId())) {
	                tipoCB.setSelectedIndex(i);
	                i = tipoCB.getItemCount();
	            }
	        }
	    }
	    
	    if (incidencia.getEstadoIncidenciaId() != null) {
	        for (int i = 0; i < estadoCB.getItemCount(); i++) {
	            EstadoIncidencia e = estadoCB.getItemAt(i);
	            if (e.getId() != null && e.getId().equals(incidencia.getEstadoIncidenciaId())) {
	                estadoCB.setSelectedIndex(i);
	                i = estadoCB.getItemCount();
	            }
	        }
	    }
	    
	    if (incidencia.getContratoId() != null) {
	        for (int i = 0; i < contratoCB.getItemCount(); i++) {
	            ContratoDTO c = contratoCB.getItemAt(i);
	            if (c.getId() != null && c.getId().equals(incidencia.getContratoId())) {
	                contratoCB.setSelectedIndex(i);
	                i = contratoCB.getItemCount();
	            }
	        }
	    }
	    
	    if (incidencia.getEmpleadoCreadorId() != null) {
	        for (int i = 0; i < empleadoCB.getItemCount(); i++) {
	            EmpleadoDTO e = empleadoCB.getItemAt(i);
	            if (e.getId() != null && e.getId().equals(incidencia.getEmpleadoCreadorId())) {
	                empleadoCB.setSelectedIndex(i);
	                i = empleadoCB.getItemCount();
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