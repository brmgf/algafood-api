package com.brmgf.algafoodapi.service.cadastro;

import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.model.Usuario;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssociacaoUsuarioResponsavelRestauranteService {

    @Transactional
    public void associar(Restaurante restaurante, Usuario usuario) {
        restaurante.getUsuariosResponsaveis().add(usuario);
    }

    @Transactional
    public void desassociar(Restaurante restaurante, Usuario usuario) {
        restaurante.getUsuariosResponsaveis().remove(usuario);
    }
}
