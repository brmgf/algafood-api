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

    private final EstadoRepository repository;
    private final ConsultaEstadoService consultaService;

    @Transactional
    public Estado salvar(Estado estado) {
        consultaService.validarEstadoCadastradoComMesmoNome(estado, null);
        return repository.save(estado);
    }

    @Transactional
    public Estado atualizar(Long estadoId, Estado novoEstado) {
        Estado estado = consultaService.buscar(estadoId);

        consultaService.validarEstadoCadastradoComMesmoNome(novoEstado, estadoId);
        BeanUtils.copyProperties(novoEstado, estado, "id");
        return repository.save(estado);
    }

    @Transactional
    public void remover(Long estadoId) {
        try {
            Estado estado = consultaService.buscar(estadoId);

            repository.delete(estado);
            repository.flush();
        } catch (DataIntegrityViolationException dataIntegrityViolationException) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Estado", estadoId)
            );
        }
    }

    @Transactional(readOnly = true)
    public Estado buscarEstadoCidade(Cidade cidade) {
        if (isNull(cidade.getEstado()) || isNull(cidade.getEstado().getId())) {
            throw new CampoObrigatorioException(
                    String.format(MensagemErro.CAMPO_OBRIGATORIO.getDescricao(), "Estado")
            );
        }

        Long estadoId = cidade.getEstado().getId();
        return consultaService.buscar(estadoId);
    }
}
