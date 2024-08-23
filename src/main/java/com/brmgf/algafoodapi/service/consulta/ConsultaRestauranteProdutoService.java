package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Produto;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.ProdutoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConsultaRestauranteProdutoService {

    private static final String RESTAURANTE = "Restaurante";
    private static final String PRODUTO = "Produto";

    private final ProdutoRepository repository;

    @Transactional(readOnly = true)
    public Produto buscarPorRestaurante(Restaurante restaurante, Long produtoId) {
        Optional<Produto> optionalProdutoCadastrado = repository.findById(produtoId);

        if (!optionalProdutoCadastrado.isPresent()) {
            throw new NegocioException(
                String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), PRODUTO, produtoId)
            );
        }

        Produto produtoCadastrado = optionalProdutoCadastrado.get();
        Optional<Produto> produtoRestaurante = restaurante.getProdutos().stream()
                .filter(p -> produtoCadastrado.getId().equals(p.getId()))
                .findFirst();

        if (!produtoRestaurante.isPresent()) {
            throw new NegocioException(
                    String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                            RESTAURANTE, restaurante.getId(), PRODUTO.toLowerCase(), produtoId));
        }

        return produtoRestaurante.get();
    }
}
