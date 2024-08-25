package com.brmgf.algafoodapi.domain.repository;

import com.brmgf.algafoodapi.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    @Query("select r.formasPagamento from Restaurante r where r.id = :restauranteId")
    List<FormaPagamento> findAllByRestauranteId(Long restauranteId);
}
