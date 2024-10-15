package com.brmgf.algafoodapi.domain.repository;

import com.brmgf.algafoodapi.domain.model.FotoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FotoProdutoRepository extends JpaRepository<FotoProduto, Long> {

    @Query("""
            SELECT fp
            FROM FotoProduto fp
            JOIN Produto p on fp.produto.id = p.id
            WHERE p.restaurante.id = :restauranteId
            AND p.id = :produtoId
            """)
    Optional<FotoProduto> findById(Long restauranteId, Long produtoId);
}
