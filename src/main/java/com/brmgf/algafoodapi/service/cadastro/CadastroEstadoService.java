package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.exception.CampoObrigatorioException;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.model.Cidade;
import com.brmgf.algafoodapi.domain.model.Estado;
import com.brmgf.algafoodapi.domain.repository.EstadoRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaEstadoService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroEstadoService {

    private static final String NOME_ENTIDADE = "Estado";

    private final EstadoRepository estadoRepository;
    private final ConsultaEstadoService consultaEstadoService;

    @Transactional
    public Estado salvar(Estado estado) {
        consultaEstadoService.validarEstadoCadastradoComMesmoNome(estado, null);
        return estadoRepository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long estadoId, Estado novoEstado) {
        Estado estado = consultaEstadoService.buscar(estadoId);

        consultaEstadoService.validarEstadoCadastradoComMesmoNome(novoEstado, estadoId);
        BeanUtils.copyProperties(novoEstado, estado, "id");
        return estadoRepository.save(estado);
    }

    @Transactional
    public void remover(Long estadoId) {
        try {
            Estado estado = consultaEstadoService.buscar(estadoId);

            estadoRepository.delete(estado);
            estadoRepository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_ENTIDADE_EM_USO.getDescricao(), NOME_ENTIDADE, estadoId)
            );
        }
    }

    @Transactional(readOnly = true)
    public Estado buscarEstadoCidade(Cidade cidade) {
        if (isNull(cidade.getEstado()) || isNull(cidade.getEstado().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.ERRO_REALIZAR_OPERACAO_CAMPO_OBRIGATORIO.getDescricao(), NOME_ENTIDADE)
            );
        }

        Long estadoId = cidade.getEstado().getId();
        return consultaEstadoService.buscar(estadoId);
    }
}
