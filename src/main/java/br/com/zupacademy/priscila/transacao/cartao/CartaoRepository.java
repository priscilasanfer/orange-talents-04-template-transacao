package br.com.zupacademy.priscila.transacao.cartao;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {
    Optional<Cartao> findByNumero(String numero);

}
