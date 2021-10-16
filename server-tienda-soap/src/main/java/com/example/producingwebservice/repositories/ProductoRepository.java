package com.example.producingwebservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.producingwebservice.model.CategoriaProductoModel;
import com.example.producingwebservice.model.ProductoModel;
import com.example.producingwebservice.model.UsuarioModel;

@Repository
public interface ProductoRepository extends CrudRepository<ProductoModel, Long>{
	public abstract Optional<ProductoModel> findByNombre(String nombre);
	public abstract Optional<ProductoModel> findByCategoria(CategoriaProductoModel categoria);
	public abstract Optional<ProductoModel> findByVendedor(UsuarioModel vendedor);
}
