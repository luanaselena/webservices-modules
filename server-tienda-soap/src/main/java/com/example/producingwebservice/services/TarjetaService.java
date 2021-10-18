package com.example.producingwebservice.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.producingwebservice.model.TarjetaModel;
import com.example.producingwebservice.model.UsuarioModel;
import com.example.producingwebservice.repositories.TarjetaRepository;
import com.example.producingwebservice.repositories.UsuarioRepository;

import io.spring.guides.gs_producing_web_service.Tarjeta;
import mapper.TarjetaMapper;

@Service
@Component
public class TarjetaService { //No distingue si es comprador o vendedor , falta definir el tipo ya que la tarjeta solo va al comprador
	@Autowired
	TarjetaRepository tarjetaRepository;
	@Autowired
	UsuarioRepository usuarioRepository;
	TarjetaMapper tarjetaMap = new TarjetaMapper();
	
	public String guardarTarjeta(Tarjeta tarjeta) {
		String estado="";
		String numeroTarjeta=tarjeta.getNumero();
		Long idUser = tarjeta.getComprador().getId();
		
		if ( idUser==null || numeroTarjeta==null ){
			estado="ERROR";
		}else {
			if( usuarioRepository.findById(idUser).isPresent()&& !(tarjetaRepository.findByNumero(tarjeta.getNumero()).isPresent())   ) {
				UsuarioModel userModel = usuarioRepository.findById(idUser).get();
				TarjetaModel tarjetaModel = new TarjetaModel();
				tarjetaModel = tarjetaMap.toTarjetaModel(tarjeta, false);
				tarjetaModel.setComprador(userModel);
				tarjetaRepository.save(tarjetaModel);
				estado="OK";
				
			}else estado="ERROR";
		}
		
		return estado;
	}
	
	public ArrayList<TarjetaModel> buscarTarjeta(String userName){
		ArrayList<TarjetaModel> tarjetaModels = new ArrayList<TarjetaModel>();
		UsuarioModel usuarioModel = new UsuarioModel();
		if (usuarioRepository.findByUsuario(userName).isPresent()) { 
			usuarioModel = usuarioRepository.findByUsuario(userName).get();
			tarjetaModels = (ArrayList<TarjetaModel>) tarjetaRepository.findByComprador(usuarioModel);
		} 
		return tarjetaModels;
	}

}