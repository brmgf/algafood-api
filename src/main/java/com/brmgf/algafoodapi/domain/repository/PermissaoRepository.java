package com.brmgf.algafoodapi.domain.repository;

import com.brmgf.algafoodapi.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Long> {
}
