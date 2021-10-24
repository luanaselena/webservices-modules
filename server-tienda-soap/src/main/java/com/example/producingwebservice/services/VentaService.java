package com.example.producingwebservice.services;


import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.producingwebservice.model.BilleteraVirtualModel;
import com.example.producingwebservice.model.PedidoModel;
import com.example.producingwebservice.model.UsuarioModel;
import com.example.producingwebservice.model.VentaModel;
import com.example.producingwebservice.repositories.BilleteraVirtualRepository;
import com.example.producingwebservice.repositories.PedidoRepository;
import com.example.producingwebservice.repositories.UsuarioRepository;
import com.example.producingwebservice.repositories.VentaRepository;

@Service
public class VentaService {
	
	@Autowired
	PedidoRepository pedidoRepository;	
	@Autowired
	UsuarioRepository usuarioRepository;	
	@Autowired
	VentaRepository ventaRepository;
	@Autowired
	BilleteraVirtualRepository billeteraRepository;
	
	public String guardarVenta(long idPedido, long idVendedor) {
		String estado = "OK";
		PedidoModel pM = pedidoRepository.findById(idPedido).get();
		UsuarioModel uM = usuarioRepository.findById(idVendedor).get();
		VentaModel vM = new VentaModel();
		vM.setEstado("Iniciado");
		vM.setFecha(new Date());
		vM.setPedido(pM);
		vM.setVendedor(uM);
		ventaRepository.save(vM);
		return estado;
	}
	
	public String finalizarVenta(long idVenta) {
		String estado = "OK";
		float total = 0;
		VentaModel vM = ventaRepository.findById(idVenta).get();
		vM.setEstado("Finalizado");
		ventaRepository.save(vM);
		total = vM.getPedido().getTotal();
		BilleteraVirtualModel bvM = new BilleteraVirtualModel();
		bvM.setSaldo(total);
		bvM.setVendedor(vM.getVendedor());
		//antes de guardar verificar que el usuario no tenga registro anterior ya creado
		//si lo tiene, se suma el total al saldo
		billeteraRepository.save(bvM);
		return estado;
	}

}
