package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Produto;
import com.brmgf.algafoodapi.domain.repository.ProdutoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaProdutoService {

    private static final String RESTAURANTE = "Restaurante";
    private static final String PRODUTO = "Produto";

    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    public Produto buscarProdutoRestaurante(Long restauranteId, Long produtoId) {
        Produto produtoCadastrado = repository.findById(produtoId)
                .orElseThrow(() -> new NegocioException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), PRODUTO, produtoId)
                ));

        List<Produto> produtos = repository.findAllByRestauranteId(restauranteId);
        return produtos.stream()
                .filter(p -> produtoCadastrado.getId().equals(p.getId()))
                .findFirst()
                .orElseThrow( () -> new NegocioException(
                    String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                            PRODUTO, RESTAURANTE.toLowerCase())
                ));
    }
}
