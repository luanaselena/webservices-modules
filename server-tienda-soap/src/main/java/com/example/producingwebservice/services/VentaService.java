package com.example.producingwebservice.services;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.producingwebservice.model.BilleteraVirtualModel;
import com.example.producingwebservice.model.DomicilioModel;
import com.example.producingwebservice.model.TarjetaModel;
import com.example.producingwebservice.model.UsuarioModel;
import com.example.producingwebservice.model.VentaModel;
import com.example.producingwebservice.repositories.BilleteraVirtualRepository;
import com.example.producingwebservice.repositories.DomicilioRepository;
import com.example.producingwebservice.repositories.TarjetaRepository;
import com.example.producingwebservice.repositories.UsuarioRepository;
import com.example.producingwebservice.repositories.VentaRepository;
import com.example.producingwebservice.utils.Estado;

import io.spring.guides.gs_producing_web_service.AddVentaRequest;

@Service
public class VentaService {
	
	@Autowired
	private DomicilioRepository domicilioRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	@Autowired
	private VentaRepository ventaRepository;	
	
	@Autowired
	private BilleteraVirtualRepository billeteraRepository;	
	
	public String guardarVenta(AddVentaRequest request) {		
		DomicilioModel domicilio = domicilioRepository.findById(request.getIdDomicilio()).orElseThrow(()->new RuntimeException("Domicilio no encontrado!")); 
		UsuarioModel comprador = usuarioRepository.findById(request.getIdComprador()).orElseThrow(()->new RuntimeException("Comprador no encontrado!"));
		UsuarioModel vendedor = usuarioRepository.findById(request.getIdVendedor()).orElseThrow(()->new RuntimeException("Vendedor no encontrado!"));
		TarjetaModel tarjeta = tarjetaRepository.findById(request.getIdTarjeta()).orElseThrow(()->new RuntimeException("Tarjeta no encontrada"));
		
		
		VentaModel venta = VentaModel.builder()
				.precioTotal(request.getPrecioTotal().floatValue())
				.estado(Estado.INICIADO.name())
				.fecha(new Date())
				.domicilio(domicilio)
				.comprador(comprador)
				.vendedor(vendedor)
				.tarjeta(tarjeta)
				.build();		
		ventaRepository.save(venta);
		
		return Estado.OK.name();
	}
	
	public String finalizarVenta(long idVenta) {		
		VentaModel venta = ventaRepository.findById(idVenta).orElseThrow(()->new RuntimeException("Venta no encontrada!"));
		venta.setEstado(Estado.FINALIZADO.name());
		ventaRepository.save(venta);
		
		billeteraRepository.findByVendedor(venta.getVendedor())
			.ifPresentOrElse(
					(billeteraVirtual) -> {
						billeteraVirtual.setSaldo(billeteraVirtual.getSaldo() + venta.getPrecioTotal());
						billeteraRepository.save(billeteraVirtual);
					},
					() -> {
						BilleteraVirtualModel billeteraVirtual = BilleteraVirtualModel.builder()
								.saldo(venta.getPrecioTotal())
								.vendedor(venta.getVendedor())
								.build();
						billeteraRepository.save(billeteraVirtual);
					}
			);
		
		return Estado.OK.name();
	}

}