package com.example.consumingwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.consumingwebservice.VentasClient;
import com.example.consumingwebservice.dto.UsuarioDomicilioCuentasBancariasDTO;
import com.example.consumingwebservice.dto.UsuarioLoginDTO;
import com.example.consumingwebservice.mapper.UsuarioMapper;
import com.example.consumingwebservice.wsdl.AddCuentaBancariaResponse;
import com.example.consumingwebservice.wsdl.AddDomicilioResponse;
import com.example.consumingwebservice.wsdl.AddUsuarioResponse;
import com.example.consumingwebservice.wsdl.CuentaBancaria;
import com.example.consumingwebservice.wsdl.Domicilio;
import com.example.consumingwebservice.wsdl.GetCuentasBancariasResponse;
import com.example.consumingwebservice.wsdl.GetDomiciliosResponse;
import com.example.consumingwebservice.wsdl.GetUsuarioResponse;
import com.example.consumingwebservice.wsdl.LoginValResponse;
import com.example.consumingwebservice.wsdl.UpdateUsuarioResponse;
import com.example.consumingwebservice.wsdl.Usuario;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	@Autowired
	VentasClient ventasClient;
	UsuarioMapper usuariomap = new UsuarioMapper();

	@GetMapping(path = "/{user}")
	public UsuarioDomicilioCuentasBancariasDTO getUsuario(@PathVariable("user") String usuario) {
		GetUsuarioResponse user = ventasClient.getUser(usuario);
		UsuarioDomicilioCuentasBancariasDTO dto = new UsuarioDomicilioCuentasBancariasDTO();
		if (user.getUsuario()!=null) {
			GetDomiciliosResponse addresses = ventasClient.getAddresses(usuario);
			GetCuentasBancariasResponse bankAccounts = ventasClient.getBankAccounts(usuario);	
			dto = usuariomap.toUsuarioDTO(user.getUsuario(), addresses.getDomicilio(), bankAccounts.getCuentaBancaria());
		}
		return dto;
	}
	
	@PostMapping(path = "/login")
	public String validarUser(@RequestBody UsuarioLoginDTO usuario) {
		LoginValResponse response = ventasClient.validator(usuario.getUser(), usuario.getPass());
		return response.getEstado();
	}
	
	@PostMapping(path = "/update")
	public String modificarUser(@RequestBody Usuario usuario) {
		UpdateUsuarioResponse response = ventasClient.updateUser(usuario);
		return response.getEstado();
	}
	
	@PostMapping(path = "/register")
	public String registrarUser(@RequestBody Usuario usuario) {
		AddUsuarioResponse response = ventasClient.signInUser(usuario);
		return response.getEstado();
	}
	
	@PostMapping(path = "/domicilio")
	public String agregarDomicilio(@RequestBody Domicilio domicilio) {
		AddDomicilioResponse response = ventasClient.addDomicilio(domicilio);
		return response.getEstado();
	}
	
	@PostMapping(path = "/cuentaBancaria")
	public String agregarCuentaBancaria(@RequestBody CuentaBancaria cuentaBancaria) {
		AddCuentaBancariaResponse response = ventasClient.addCuentaBancaria(cuentaBancaria);
		return response.getEstado();
	}
}
