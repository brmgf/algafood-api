package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Grupo;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.domain.repository.GrupoRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaGrupoService {

    private static final String GRUPO = "Grupo";
    private static final String USUARIO = "usuário";

    private final GrupoRepository repository;

    @Transactional(readOnly = true)
    public List<Grupo> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Grupo buscar(Long grupoId) {
        return repository.findById(grupoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), GRUPO, grupoId)
                ));
    }

    @Transactional(readOnly = true)
    public Grupo buscarGrupoUsuario(Usuario usuario, Long grupoId) {
        Grupo grupoCadastrado = repository.findById(grupoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), GRUPO, grupoId)
                ));

        Grupo grupoUsuario = usuario.getGrupos().stream()
                .filter(g -> grupoCadastrado.getId().equals(g.getId()))
                .findFirst()
                .orElseThrow(() -> new NegocioException(
                        String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                                GRUPO, USUARIO)
                ));

        return grupoUsuario;
    }
}
