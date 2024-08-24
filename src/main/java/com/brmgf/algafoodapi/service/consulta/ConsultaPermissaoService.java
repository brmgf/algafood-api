package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Permissao;
import com.brmgf.algafoodapi.domain.repository.PermissaoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ConsultaPermissaoService {

    private static final String PERMISSAO = "Permissão";
    private static final String GRUPO = "grupo";

    private final PermissaoRepository repository;

    @Transactional(readOnly = true)
    public Permissao buscar(Long permissaoId) {
        return repository.findById(permissaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(),
                                PERMISSAO, permissaoId)
                ));
    }

    @Transactional(readOnly = true)
    public Permissao buscarPermissaoGrupo(Grupo grupo, Long permissaoId) {
        Permissao permissaoCadastrada = repository.findById(permissaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(),
                                PERMISSAO, permissaoId)
                ));

        Permissao permissaoGrupo = grupo.getPermissoes().stream()
                .filter(p -> permissaoCadastrada.getId().equals(p.getId()))
                .findFirst()
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                                PERMISSAO, GRUPO)
                ));

        return permissaoGrupo;
    }
}
