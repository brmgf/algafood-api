package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.UsuarioDTOAssembler;
import com.brmgf.algafoodapi.api.domain.dto.UsuarioDTO;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.domain.model.Usuario;
import com.brmgf.algafoodapi.service.cadastro.AssociacaoUsuarioResponsavelRestauranteService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import com.brmgf.algafoodapi.service.consulta.ConsultaUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
@RestController
public class UsuarioResponsavelRestauranteController {

    private final AssociacaoUsuarioResponsavelRestauranteService service;
    private final ConsultaRestauranteService consultaRestauranteService;
    private final ConsultaUsuarioService consultaUsuarioService;
    private final UsuarioDTOAssembler assembler;

    @GetMapping
    public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        return assembler.toCollectionDTO(restaurante.getUsuariosResponsaveis());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{usuarioId}")
    public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        Usuario usuario = consultaUsuarioService.buscar(usuarioId);
        service.associar(restaurante, usuario);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{usuarioId}")
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
        Restaurante restaurante = consultaRestauranteService.buscar(restauranteId);
        Usuario usuario = consultaUsuarioService.buscarUsuarioResponsavelRestaurante(restaurante, usuarioId);
        service.desassociar(restaurante, usuario);
    }
}
