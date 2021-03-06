package com.example.consumingwebservice.dto;

import java.util.List;

import com.example.consumingwebservice.wsdl.CuentaBancaria;
import com.example.consumingwebservice.wsdl.Domicilio;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UsuarioDomicilioCuentasBancariasDTO {
	@Getter @Setter private long id;
	@Getter @Setter private String nombre;
	@Getter @Setter private String apellido;
	@Getter @Setter private String dni;
	@Getter @Setter private String usuario;
	@Getter @Setter private String contrasenia;
	@Getter @Setter private String telefono;
	@Getter @Setter private List<Domicilio> domicilios;
	@Getter @Setter private List<CuentaBancaria> cuentasBancarias;
}
