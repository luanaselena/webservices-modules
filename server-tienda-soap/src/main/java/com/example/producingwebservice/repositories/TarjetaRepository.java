package com.example.producingwebservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.producingwebservice.model.TarjetaModel;
import com.example.producingwebservice.model.UsuarioModel;

@Repository
public interface TarjetaRepository extends CrudRepository<TarjetaModel, Long>{
	
	public abstract List<TarjetaModel> findByComprador(UsuarioModel comprador);
	
}
