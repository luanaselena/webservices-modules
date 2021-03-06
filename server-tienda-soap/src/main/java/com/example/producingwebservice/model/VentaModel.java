package com.example.producingwebservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "venta")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class VentaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private float precioTotal;
	private String estado;
	private Date fecha;	
	private String idSeguimiento;
	
	@ManyToOne
	@JoinColumn(name = "id_domicilio")
	private DomicilioModel domicilio;
	
	@ManyToOne
	@JoinColumn(name = "id_comprador")
	private UsuarioModel comprador;
	
	@ManyToOne
	@JoinColumn(name = "id_vendedor")
	private UsuarioModel vendedor;
	
	@ManyToOne
	@JoinColumn(name = "id_tarjeta")
	private TarjetaModel tarjeta;
	
	@Transient
	private List<ProductoModel> items = new ArrayList<>();

}
