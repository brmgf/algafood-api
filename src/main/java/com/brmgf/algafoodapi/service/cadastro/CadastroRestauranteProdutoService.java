package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Produto;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.repository.ProdutoRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteProdutoService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroRestauranteProdutoService {

    private final ProdutoRepository repository;
    private final ConsultaRestauranteProdutoService consultaService;

    @Transactional
    public Produto adicionar(Produto produto) {
        return repository.save(produto);
    }

    @Transactional
    public Produto atualizar(Long produtoId, Produto novoProduto, Restaurante restaurante) {
        Produto produto = consultaService.buscarPorIdAndRestauranteId(restaurante, produtoId);

        BeanUtils.copyProperties(novoProduto, produto, "id");
        produto.setRestaurante(restaurante);
        return repository.save(produto);
    }
}
