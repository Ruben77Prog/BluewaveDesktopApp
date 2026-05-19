package com.ruben.bluewave.ui.controller;

import com.ruben.bluewave.model.EmpleadoDTO;
import com.ruben.bluewave.service.EmpleadoService;
import com.ruben.bluewave.service.impl.EmpleadoServiceImpl;
import com.ruben.bluewave.ui.MainWindow;
import com.ruben.bluewave.ui.views.LoginView;

public class LoginController extends AbstractController {

    private LoginView view;
    private EmpleadoService empleadoService;

    public LoginController(LoginView view) {
        super(view);
        this.view = view;
        this.empleadoService = new EmpleadoServiceImpl();
    }

    @Override
    public void doAction() {
        String email = view.getEmail();
        String password = view.getPassword();
        String tipoUsuario = view.getTipoUsuario();

        if (email.isEmpty() || password.isEmpty()) {
            view.mostrarError("Por favor, introduzca email y contraseña");
            return;
        }

        try {
            EmpleadoDTO empleado = empleadoService.login(email, password);
            
            if (empleado != null) {
                
                boolean esAdmin = "Administrador".equals(tipoUsuario);
                
                if (esAdmin && empleado.getRolId() != null && empleado.getRolId() == 1L) {
                    MainWindow.getInstance().loginSuccess(empleado, true);
                } else if (!esAdmin && empleado.getRolId() != null && empleado.getRolId() == 2L) {
                    MainWindow.getInstance().loginSuccess(empleado, false);
                } else {
                    view.mostrarError("Email o contraseña incorrectos");
                }
            } else {
                view.mostrarError("Email o contraseña incorrectos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.mostrarError("Error al conectar con la base de datos");
        }
    }
}