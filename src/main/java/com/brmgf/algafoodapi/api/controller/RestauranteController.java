package com.brmgf.algafoodapi.api.controller;

import com.brmgf.algafoodapi.api.assembler.RestauranteDTOAssembler;
import com.brmgf.algafoodapi.api.disassembler.RestauranteInputDisassembler;
import com.brmgf.algafoodapi.api.domain.input.RestauranteInput;
import com.brmgf.algafoodapi.api.domain.model.RestauranteDTO;
import com.brmgf.algafoodapi.domain.model.Restaurante;
import com.brmgf.algafoodapi.service.cadastro.CadastroRestauranteService;
import com.brmgf.algafoodapi.service.consulta.ConsultaRestauranteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/restaurantes")
@RestController
public class RestauranteController {

    private final ConsultaRestauranteService consultaRestauranteService;
    private final CadastroRestauranteService cadastroRestauranteService;
    private final RestauranteDTOAssembler restauranteDTOAssembler;
    private final RestauranteInputDisassembler restauranteInputDisassembler;

    @GetMapping
    public List<Restaurante> listar() {
        return consultaRestauranteService.listar();
    }

    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscar(@PathVariable Long restauranteId) {
        return restauranteDTOAssembler.toModel(consultaRestauranteService.buscar(restauranteId));
    }

    @PostMapping
    public RestauranteDTO salvar(@RequestBody @Valid RestauranteInput restauranteInput) {
        return restauranteDTOAssembler
                .toModel(cadastroRestauranteService.salvar(restauranteInputDisassembler.toObjectModel(restauranteInput)));
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteInput restaurante) {
        return restauranteDTOAssembler
                .toModel(cadastroRestauranteService.atualizar(restauranteId, restauranteInputDisassembler.toObjectModel(restaurante)));
    }

    @DeleteMapping("/{restauranteId}")
    public void remover(@PathVariable Long restauranteId) {
        cadastroRestauranteService.remover(restauranteId);
    }

    @PatchMapping("/{restauranteId}")
    public RestauranteDTO atualizarParcial(@PathVariable Long restauranteId, @RequestBody Map<String, Object> campos) {
        return restauranteDTOAssembler
                .toModel(cadastroRestauranteService.atualizarDadosParcialmente(restauranteId, campos));
    }
}
