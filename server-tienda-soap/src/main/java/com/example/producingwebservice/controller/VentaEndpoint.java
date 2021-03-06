package com.example.producingwebservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.producingwebservice.model.VentaModel;
import com.example.producingwebservice.services.VentaService;

import io.spring.guides.gs_producing_web_service.ActualizarEstadoVentaRequest;
import io.spring.guides.gs_producing_web_service.AddReclamoRequest;
import io.spring.guides.gs_producing_web_service.AddReclamoResponse;
import io.spring.guides.gs_producing_web_service.AddVentaRequest;
import io.spring.guides.gs_producing_web_service.AddVentaResponse;
import io.spring.guides.gs_producing_web_service.CancelVentaRequest;
import io.spring.guides.gs_producing_web_service.CancelVentaResponse;
import io.spring.guides.gs_producing_web_service.FinalizeVentaRequest;
import io.spring.guides.gs_producing_web_service.FinalizeVentaResponse;
import io.spring.guides.gs_producing_web_service.GetVentasPorIdCompradorRequest;
import io.spring.guides.gs_producing_web_service.GetVentasPorIdCompradorResponse;
import io.spring.guides.gs_producing_web_service.GetVentasPorIdVendedorRequest;
import io.spring.guides.gs_producing_web_service.GetVentasPorIdVendedorResponse;
import mapper.VentaMapper;

@CrossOrigin(origins="http://127.0.0.1:5500" ,methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT})
@Endpoint
public class VentaEndpoint {
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	@Autowired
	private VentaService ventaService;
	
	VentaMapper ventaMapper = new VentaMapper();
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addVentaRequest")
	@ResponsePayload
	public AddVentaResponse addVenta(@RequestPayload AddVentaRequest request) {
		AddVentaResponse response = new AddVentaResponse();
		response.setEstado(ventaService.guardarVenta(request));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "finalizeVentaRequest")
	@ResponsePayload
	public FinalizeVentaResponse finalizeVenta(@RequestPayload FinalizeVentaRequest request) {
		FinalizeVentaResponse response = new FinalizeVentaResponse();
		response.setEstado(ventaService.finalizarVenta(request.getIdVenta()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVentasPorIdVendedorRequest")
	@ResponsePayload
	public GetVentasPorIdVendedorResponse getVentas(@RequestPayload GetVentasPorIdVendedorRequest request) {
		GetVentasPorIdVendedorResponse response = new GetVentasPorIdVendedorResponse();
		Iterable<VentaModel> lstVentas = ventaService.traerVentasPorVendedor(request.getId());
		for (VentaModel v : lstVentas) {
			response.getVenta().add(ventaMapper.toVentaXML(v,true));
		}
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getVentasPorIdCompradorRequest")
	@ResponsePayload
	public GetVentasPorIdCompradorResponse getVentasPorComprador(@RequestPayload GetVentasPorIdCompradorRequest request) {
		GetVentasPorIdCompradorResponse response = new GetVentasPorIdCompradorResponse();
		
		Iterable<VentaModel> lstVentas = ventaService.traerVentasPorComprador(request.getId());
		for (VentaModel v : lstVentas) {
			response.getVenta().add(ventaMapper.toVentaXML(v,true));
		}
		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "actualizarEstadoVentaRequest")
	@ResponsePayload
	public void actualizarEstadoVenta(@RequestPayload ActualizarEstadoVentaRequest request) {
		ventaService.actualizarEstadoVenta(request.getIdVenta(), request.getEstado());
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "cancelVentaRequest")
	@ResponsePayload
	public CancelVentaResponse cancelVenta(@RequestPayload CancelVentaRequest request) {
		CancelVentaResponse response = new CancelVentaResponse();
		response.setEstado(ventaService.cancelarVenta(request.getIdComprador(), request.getIdVenta()));
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addReclamoRequest")
	@ResponsePayload
	public AddReclamoResponse addReclamo(@RequestPayload AddReclamoRequest request) {
		AddReclamoResponse response = new AddReclamoResponse();
		
		response.setEstado(ventaService.addReclamo(request.getIdVenta(), request.getComentario()));
		
		return response;
	}
	

}
