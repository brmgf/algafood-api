package com.brmgf.algafoodapi.domain.repository;

import com.brmgf.algafoodapi.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findAllByRestauranteId(Long restauranteId);
}
