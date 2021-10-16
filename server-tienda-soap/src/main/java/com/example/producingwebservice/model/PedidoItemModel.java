package com.example.producingwebservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidoitem")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class PedidoItemModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private Long id;
	@Getter @Setter private Integer cantidad;
	@ManyToOne
	@JoinColumn(name = "FK_PRODUCTO", nullable = false, updatable = false)
	@Getter @Setter private ProductoModel producto;
	
	@ManyToOne
	@JoinColumn(name = "FK_PEDIDO", nullable = false, updatable = false)
	@Getter @Setter private PedidoModel pedido;

}
