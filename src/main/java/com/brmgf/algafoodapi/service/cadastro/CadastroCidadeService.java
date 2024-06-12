package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.EstadoNaoEncontradoException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.repository.CidadeRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaCidadeService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;

@RequiredArgsConstructor
@Service
public class CadastroCidadeService {

    private static final String NOME_ENTIDADE = "Cidade";

    private final CidadeRepository cidadeRepository;
    private final ConsultaCidadeService consultaCidadeService;
    private final CadastroEstadoService cadastroEstadoService;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        try {
            cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(cidade));
            return cidadeRepository.save(cidade);
        } catch (EstadoNaoEncontradoException estadoNaoEncontradoException) {
            throw new NegocioException(estadoNaoEncontradoException.getMessage(), estadoNaoEncontradoException);
        }
    }

    @Transactional
    public Cidade atualizar(Long cidadeId, Cidade novaCidade) {
        Cidade cidade = consultaCidadeService.buscar(cidadeId);

        BeanUtils.copyProperties(novaCidade, cidade, "id");

        try {
            cidade.setEstado(cadastroEstadoService.buscarEstadoCidade(novaCidade));
            return cidadeRepository.save(cidade);
        } catch (EstadoNaoEncontradoException estadoNaoEncontradoException) {
            throw new NegocioException(estadoNaoEncontradoException.getMessage(), estadoNaoEncontradoException);
        }
    }

    @Transactional
    public void remover(Long cidadeId) {
        try {
            Cidade cidade = consultaCidadeService.buscar(cidadeId);

            cidadeRepository.delete(cidade);
            cidadeRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, cidadeId)
            );
        }
    }
}
