package com.example.producingwebservice.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.producingwebservice.model.BilleteraVirtualModel;
import com.example.producingwebservice.model.UsuarioModel;

@Repository
public interface BilleteraVirtualRepository extends CrudRepository<BilleteraVirtualModel, Long> {
	public abstract Optional<BilleteraVirtualModel> findByVendedor(UsuarioModel usuario );
}
