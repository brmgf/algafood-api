package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;
    private final ConsultaCidadeService consultaCidadeService;
    private final CadastroEstadoService cadastroEstadoService;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        try {
            cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(cidade));
            return cidadeRepository.save(cidade);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            throw new NegocioException(entidadeNaoEncontradaException.getMessage());
        }
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade novaCidade) {
        Cidade cidade = consultaCidadeService.buscar(cidadeId);

        BeanUtils.copyProperties(novaCidade, cidade, "id");

        try {
            cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(novaCidade));
            return cidadeRepository.save(cidade);
        } catch (EntidadeNaoEncontradaException entidadeNaoEncontradaException) {
            throw new NegocioException(entidadeNaoEncontradaException.getMessage());
        }
    }

    @Transactional
    public void remover(Long cidadeId) {
        Cidade cidade = consultaCidadeService.buscar(cidadeId);

        cidadeRepository.delete(cidade);
    }
}
