package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.api.domain.input.SenhaInput;
import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.exception.NegocioException;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.domain.repository.UsuarioRepository;
import com.brmgf.algafoodapi.service.consulta.ConsultaUsuarioService;
import com.brmgf.algafoodapi.util.MensagemErro;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class CadastroUsuarioService {

    private final UsuarioRepository repository;
    private final ConsultaUsuarioService consultaService;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validarEmailCadastrado(usuario, null);
        usuario.setDataHoraCadastro(LocalDateTime.now());
        return repository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Long usuarioId, Usuario novoUsuario) {
        Usuario usuario = consultaService.buscar(usuarioId);

        validarEmailCadastrado(novoUsuario, usuario);

        BeanUtils.copyProperties(novoUsuario, usuario, "id", "senha", "dataHoraCadastro");
        return repository.save(usuario);
    }

    private void validarEmailCadastrado(Usuario novoUsuario, Usuario usuarioBuscado) {
        Optional<Usuario> usuarioExistente = repository.findByEmail(novoUsuario.getEmail());
        if (usuarioExistente.isPresent() && (isNull(usuarioBuscado) || !usuarioExistente.get().equals(usuarioBuscado))) {
            throw new NegocioException(MensagemErro.USUARIO_CADASTRADO_EMAIL.getDescricao());
        }
    }

    @Transactional
    public void alterarSenha(Long usuarioId, SenhaInput senhaInput) {
        Usuario usuario = consultaService.buscar(usuarioId);

        validarSenhaAtual(senhaInput.getSenhaAtual(), usuario.getSenha());

        usuario.setSenha(senhaInput.getNovaSenha());
        repository.save(usuario);
    }

    @Transactional
    public void remover(Long usuarioId) {
        try {
            Usuario usuario = consultaService.buscar(usuarioId);

            repository.delete(usuario);
            repository.flush();
        } catch (DataIntegrityViolationException ex) {
            throw new EntidadeEmUsoException(
                    String.format(MensagemErro.ENTIDADE_EM_USO.getDescricao(), "Usuário", usuarioId)
            );
        }
    }

    private void validarSenhaAtual(String senhaInformada, String senhaAtual) {
        if (!senhaInformada.equals(senhaAtual)) {
            throw new NegocioException(MensagemErro.SENHA_ATUAL_DIFERENTE_SENHA_CADASTRADA.getDescricao());
        }
    }
}
