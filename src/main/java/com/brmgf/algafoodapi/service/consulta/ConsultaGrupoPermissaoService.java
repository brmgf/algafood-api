package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Permissao;
import com.brmgf.algafoodapi.domain.repository.PermissaoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConsultaGrupoPermissaoService {

    private static final String PERMISSAO = "Permissao";
    private static final String GRUPO = "Grupo";

    private final PermissaoRepository repository;

    @Transactional(readOnly = true)
    public Permissao buscar(Long permissaoId) {
        return repository.findById(permissaoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), PERMISSAO, permissaoId)
                ));
    }

    @Transactional(readOnly = true)
    public Permissao buscarPorGrupo(Grupo grupo, Long permissaoId) {
        Optional<Permissao> optionalPermissao = repository.findById(permissaoId);

        if (!optionalPermissao.isPresent()) {
            throw new EntidadeNaoEncontradaException(
                    String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), PERMISSAO, permissaoId)
            );
        }

        Permissao permissaoCadastrada = optionalPermissao.get();
        Optional<Permissao> permissaoGrupo = grupo.getPermissoes().stream()
                .filter(p -> permissaoCadastrada.getId().equals(p.getId()))
                .findFirst();

        if (!permissaoGrupo.isPresent()) {
            throw new NegocioException(
                    String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                            GRUPO, grupo.getId(), PERMISSAO.toLowerCase(), permissaoId)
            );
        }

        return permissaoGrupo.get();
    }
}
