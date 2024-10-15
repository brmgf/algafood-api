package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.FotoProduto;
import com.brmgf.algafoodapi.domain.repository.FotoProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CatalogoFotoProdutoService {

    private final FotoProdutoRepository repository;

    @Transactional
    public FotoProduto salvar(FotoProduto fotoProduto) {
        Long restauranteId = fotoProduto.getRestauranteId();
        Long produtoId = fotoProduto.getProduto().getId();

        Optional<FotoProduto> fotoProdutoSalva = repository.findById(restauranteId, produtoId);
        if (fotoProdutoSalva.isPresent()) {
            repository.delete(fotoProdutoSalva.get());
        }
        return repository.save(fotoProduto);
    }
}
