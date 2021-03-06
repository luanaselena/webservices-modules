
package com.example.consumingwebservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.example.consumingwebservice.dto.CuentaBancariaDTO;
import com.example.consumingwebservice.dto.VentaDTO;
import com.example.consumingwebservice.wsdl.ActualizarEstadoVentaRequest;
import com.example.consumingwebservice.wsdl.AddCategoriaProductoRequest;
import com.example.consumingwebservice.wsdl.AddCategoriaProductoResponse;
import com.example.consumingwebservice.wsdl.AddCuentaBancariaRequest;
import com.example.consumingwebservice.wsdl.AddCuentaBancariaResponse;
import com.example.consumingwebservice.wsdl.AddDomicilioRequest;
import com.example.consumingwebservice.wsdl.AddDomicilioResponse;
import com.example.consumingwebservice.wsdl.AddProductoRequest;
import com.example.consumingwebservice.wsdl.AddProductoResponse;
import com.example.consumingwebservice.wsdl.AddUsuarioRequest;
import com.example.consumingwebservice.wsdl.AddUsuarioResponse;
import com.example.consumingwebservice.wsdl.AddVentaResponse;
import com.example.consumingwebservice.wsdl.BilleteraVirtualToCuentaBancariaRequest;
import com.example.consumingwebservice.wsdl.BilleteraVirtualToCuentaBancariaResponse;
import com.example.consumingwebservice.wsdl.CancelVentaRequest;
import com.example.consumingwebservice.wsdl.CancelVentaResponse;
import com.example.consumingwebservice.wsdl.DeleteCuentaBancariaRequest;
import com.example.consumingwebservice.wsdl.DeleteCuentaBancariaResponse;
import com.example.consumingwebservice.wsdl.Domicilio;
import com.example.consumingwebservice.wsdl.FinalizeVentaRequest;
import com.example.consumingwebservice.wsdl.FinalizeVentaResponse;
import com.example.consumingwebservice.wsdl.GetBilleteraVirtualRequest;
import com.example.consumingwebservice.wsdl.GetBilleteraVirtualResponse;
import com.example.consumingwebservice.wsdl.GetCategoriasDenunciaRequest;
import com.example.consumingwebservice.wsdl.GetCategoriasDenunciaResponse;
import com.example.consumingwebservice.wsdl.GetCategoriasProductoRequest;
import com.example.consumingwebservice.wsdl.GetCategoriasProductoResponse;
import com.example.consumingwebservice.wsdl.GetCuentasBancariasRequest;
import com.example.consumingwebservice.wsdl.GetCuentasBancariasResponse;
import com.example.consumingwebservice.wsdl.GetDomiciliosRequest;
import com.example.consumingwebservice.wsdl.GetDomiciliosResponse;
import com.example.consumingwebservice.wsdl.GetProductoPorIdRequest;
import com.example.consumingwebservice.wsdl.GetProductoPorIdResponse;
import com.example.consumingwebservice.wsdl.GetProductoRequest;
import com.example.consumingwebservice.wsdl.GetProductoResponse;
import com.example.consumingwebservice.wsdl.GetProductosPorIdVendedorRequest;
import com.example.consumingwebservice.wsdl.GetProductosPorIdVendedorResponse;
import com.example.consumingwebservice.wsdl.GetProductosPorNameRequest;
import com.example.consumingwebservice.wsdl.GetProductosPorNameResponse;
import com.example.consumingwebservice.wsdl.GetProductosRequest;
import com.example.consumingwebservice.wsdl.GetProductosResponse;
import com.example.consumingwebservice.wsdl.GetUsuarioRequest;
import com.example.consumingwebservice.wsdl.GetUsuarioResponse;
import com.example.consumingwebservice.wsdl.GetVentasPorIdVendedorRequest;
import com.example.consumingwebservice.wsdl.GetVentasPorIdVendedorResponse;
import com.example.consumingwebservice.wsdl.LoginValRequest;
import com.example.consumingwebservice.wsdl.LoginValResponse;
import com.example.consumingwebservice.wsdl.Producto;
import com.example.consumingwebservice.wsdl.UpdateProductoRequest;
import com.example.consumingwebservice.wsdl.UpdateProductoResponse;
import com.example.consumingwebservice.wsdl.UpdateUsuarioRequest;
import com.example.consumingwebservice.wsdl.UpdateUsuarioResponse;
import com.example.consumingwebservice.wsdl.Usuario;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VentasClient extends WebServiceGatewaySupport {
	
	@Value("${ws.server.dir}")
	private String wsServerDir;
	
	@Value("${soap.action.callback}")
	private String soapActionCallback;

	public GetUsuarioResponse getUser(String name) {

		GetUsuarioRequest request = new GetUsuarioRequest();
		request.setName(name);
		log.info("Searching user : " + name);
		GetUsuarioResponse response = (GetUsuarioResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public GetDomiciliosResponse getAddresses(String usuario) {

		GetDomiciliosRequest request = new GetDomiciliosRequest();
		request.setUsuario(usuario);
		log.info("Searching address : user: " + usuario);
		GetDomiciliosResponse response = (GetDomiciliosResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetCuentasBancariasResponse getBankAccounts(String usuario) {
		
		GetCuentasBancariasRequest request = new GetCuentasBancariasRequest();
		request.setUsuario(usuario);
		log.info("Searching bank accounts: user: "+usuario);
		GetCuentasBancariasResponse response = (GetCuentasBancariasResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public LoginValResponse validator(String user, String pass) {

		LoginValRequest request = new LoginValRequest();
		request.setContrasenia(pass);
		request.setUsuario(user);
		LoginValResponse response = (LoginValResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public UpdateUsuarioResponse updateUser(Usuario user) {
		UpdateUsuarioRequest request = new UpdateUsuarioRequest();
		request.setUsuario(user);
		UpdateUsuarioResponse response = (UpdateUsuarioResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public AddUsuarioResponse signInUser(Usuario user) {
		AddUsuarioRequest request = new AddUsuarioRequest();
		request.setUsuario(user);
		AddUsuarioResponse response = (AddUsuarioResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public AddDomicilioResponse addDomicilio(Domicilio domicilio) {
		AddDomicilioRequest request = new AddDomicilioRequest();
		request.setDomicilio(domicilio);
		AddDomicilioResponse response = (AddDomicilioResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}

	public AddCuentaBancariaResponse addCuentaBancaria(CuentaBancariaDTO cuentaBancariaDTO) {
		AddCuentaBancariaRequest request = new AddCuentaBancariaRequest();
		request.setCbu(cuentaBancariaDTO.getCbu());
		request.setIdUsuario(cuentaBancariaDTO.getIdUsuario());		
		
		AddCuentaBancariaResponse response = (AddCuentaBancariaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		
		return response;
	}
	
	public AddProductoResponse addProducto(Producto producto) {
		AddProductoRequest request = new AddProductoRequest();
		request.setProducto(producto);
		AddProductoResponse response = (AddProductoResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetProductoResponse getProductoPorNombre(String name) {
		GetProductoRequest request = new GetProductoRequest();
		request.setName(name);
		GetProductoResponse response = (GetProductoResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetProductosResponse getProductos() {
		GetProductosRequest request = new GetProductosRequest();
		GetProductosResponse response = (GetProductosResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetProductoPorIdResponse getProductoPorId(Long id) {
		GetProductoPorIdRequest request = new GetProductoPorIdRequest();
		request.setId(id);
		GetProductoPorIdResponse response = (GetProductoPorIdResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetProductosPorIdVendedorResponse getProductosPorIdVendedor(Long idVendedor) {
		GetProductosPorIdVendedorRequest request = new GetProductosPorIdVendedorRequest();
		request.setId(idVendedor);
		GetProductosPorIdVendedorResponse response = (GetProductosPorIdVendedorResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetProductosPorNameResponse getProductosPorName(String name) {
		GetProductosPorNameRequest request = new GetProductosPorNameRequest();
		request.setName(name);
		GetProductosPorNameResponse response = (GetProductosPorNameResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public UpdateProductoResponse updateProducto(Producto producto) {
		UpdateProductoRequest request = new UpdateProductoRequest();
		request.setProducto(producto);
		UpdateProductoResponse response = (UpdateProductoResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public AddCategoriaProductoResponse addCategoriaProducto(String categoriaProducto) {
		AddCategoriaProductoRequest request = new AddCategoriaProductoRequest();
		request.setCategoriaProducto(categoriaProducto);
		AddCategoriaProductoResponse response = (AddCategoriaProductoResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetCategoriasProductoResponse getCategoriasProducto() {
		GetCategoriasProductoRequest request = new GetCategoriasProductoRequest();
		GetCategoriasProductoResponse response = (GetCategoriasProductoResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public AddVentaResponse addVenta(VentaDTO venta) {
		log.info("Enviando alta de venta a servicio soap: {}", wsServerDir);
		AddVentaResponse response = (AddVentaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir,
				venta.toSOAPRequest(),
				new SoapActionCallback(soapActionCallback));
		
		return response;
	}
	
	public GetVentasPorIdVendedorResponse getVentasPorIdVendedor(Long idVendedor) {
		GetVentasPorIdVendedorRequest request = new GetVentasPorIdVendedorRequest();
		request.setId(idVendedor);
		GetVentasPorIdVendedorResponse response = (GetVentasPorIdVendedorResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetBilleteraVirtualResponse getBilleteraVirtual(Long userId) {
		GetBilleteraVirtualRequest request = new GetBilleteraVirtualRequest();
		request.setId(userId);
		GetBilleteraVirtualResponse response = (GetBilleteraVirtualResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public BilleteraVirtualToCuentaBancariaResponse transferir(BilleteraVirtualToCuentaBancariaRequest request) {
		BilleteraVirtualToCuentaBancariaResponse response = (BilleteraVirtualToCuentaBancariaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}	
	
	public void actualizarEstadoVenta(Long idVenta, String estado) {
		ActualizarEstadoVentaRequest request = new ActualizarEstadoVentaRequest();
		request.setIdVenta(idVenta);
		request.setEstado(estado);
		
		getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
	}
	
	public String finalizarVenta(Long idVenta) {
		FinalizeVentaRequest request = new FinalizeVentaRequest();
		request.setIdVenta(idVenta);
		
		FinalizeVentaResponse response = (FinalizeVentaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		
		return response.getEstado();
	}
		
	public String cancelarVenta(Long idVenta, Long idComprador) {
		CancelVentaRequest request = new CancelVentaRequest();
		request.setIdVenta(idVenta);
		request.setIdComprador(idComprador);
		
		CancelVentaResponse response = (CancelVentaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response.getEstado();
	}
	
	public DeleteCuentaBancariaResponse deleteCuentaBancaria(Long id) {
		DeleteCuentaBancariaRequest request = new DeleteCuentaBancariaRequest();
		request.setIdCuentaBancaria(id);
		DeleteCuentaBancariaResponse response = (DeleteCuentaBancariaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		return response;
	}
	
	public GetCategoriasDenunciaResponse getCategoriasDenuncia() {
		GetCategoriasDenunciaRequest request = new GetCategoriasDenunciaRequest();
		
		GetCategoriasDenunciaResponse response = (GetCategoriasDenunciaResponse) getWebServiceTemplate().marshalSendAndReceive(
				wsServerDir, request,
				new SoapActionCallback(soapActionCallback));
		
		return response;
	}
}
