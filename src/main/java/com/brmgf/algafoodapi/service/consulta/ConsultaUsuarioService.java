package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.domain.repository.UsuarioRepository;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaUsuarioService {

    private static final String USUARIO = "Usuário";
    private static final String RESTAURANTE = "restaurante";

    private final UsuarioRepository repository;

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscar(Long usuarioId) {
        return repository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), USUARIO, usuarioId)
                ));
    }

    @Transactional(readOnly = true)
    public Usuario buscarUsuarioResponsavelRestaurante(Restaurante restaurante, Long usuarioId) {
        Usuario usuarioCadastrado = repository.findById(usuarioId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MensagemErro.ENTIDADE_NAO_ENCONTRADA.getDescricao(), USUARIO, usuarioId)
                ));

        Usuario usuarioResponsavel = restaurante.getUsuariosResponsaveis().stream()
                .filter(u -> usuarioCadastrado.getId().equals(u.getId()))
                .findFirst()
                .orElseThrow(() -> new NegocioException(
                String.format(MensagemErro.ENTIDADE_NAO_POSSUI_VINCULO.getDescricao(),
                        USUARIO, RESTAURANTE)
        ));

        return usuarioResponsavel;
    }
}
