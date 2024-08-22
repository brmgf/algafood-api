package com.brmgf.algafoodapi;

import com.brmgf.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.brmgf.algafoodapi.domain.model.Cozinha;
import com.brmgf.algafoodapi.service.cadastro.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CadastroCozinhaIT {

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@Test
	void deveSalvarQuandoCadastrarCozinhaComDadosCorretos() {
		Cozinha novaCozinha = new Cozinha();
		novaCozinha.setNome("Chinesa");

		novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

		Assertions.assertThat(novaCozinha).isNotNull();
		Assertions.assertThat(novaCozinha.getId()).isNotNull();
	}

	@Test
	void deveFalharQuandoCadastrarCozinhaSemNome() {
		Cozinha novaCozinha = new Cozinha();

		var exception = assertThrows(ConstraintViolationException.class,
				() -> cadastroCozinhaService.salvar(novaCozinha));

		Assertions.assertThat(exception).isNotNull();
	}

	@Test
	void deveFalharQuandoExcluirCozinhaEmUso() {
		var exception = assertThrows(EntidadeEmUsoException.class,
				() -> cadastroCozinhaService.remover(1L));

		Assertions.assertThat(exception).isNotNull();
	}

	@Test
	void deveFalharQuandoExcluirCozinhaInexistente() {
		var exception = assertThrows(CozinhaNaoEncontradaException.class,
				() -> cadastroCozinhaService.remover(999L));

		Assertions.assertThat(exception).isNotNull();
	}
}
