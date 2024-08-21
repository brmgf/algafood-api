package com.brmgf.algafoodapi.service.consulta;

import com.brmgf.algafoodapi.domain.exception.entidadenaoencontrada.UsuarioNaoEncontradoException;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ConsultaUsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional(readOnly = true)
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Usuario buscar(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
