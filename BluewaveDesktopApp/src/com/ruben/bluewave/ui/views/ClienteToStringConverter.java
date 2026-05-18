package com.ruben.bluewave.ui.views;

import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import com.ruben.bluewave.model.EmpleadoDTO;

public class ClienteToStringConverter extends ObjectToStringConverter{
	
	public ClienteToStringConverter() {
		
	}
	
	public String getPreferredStringForItem(Object item) {

		EmpleadoDTO e = (EmpleadoDTO) item;
		return e.getNombre() + e.getApellido1();
		
	}

}
