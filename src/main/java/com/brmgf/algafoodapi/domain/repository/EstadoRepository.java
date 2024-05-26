package com.brmgf.algafoodapi.domain.repository;

import com.brmgf.algafoodapi.domain.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNomeIgnoreCase(@Param("nome") String nome);
}
