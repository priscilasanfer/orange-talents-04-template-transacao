package br.com.zupacademy.priscila.transacao.transacao;

import br.com.zupacademy.priscila.transacao.cartao.Cartao;
import br.com.zupacademy.priscila.transacao.cartao.CartaoRepository;
import br.com.zupacademy.priscila.transacao.cartao.CartaoResponse;
import br.com.zupacademy.priscila.transacao.estabelicmento.Estabelecimento;
import br.com.zupacademy.priscila.transacao.estabelicmento.EstabelecimentoResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class EventoDeTransacao {

    private String id;
    private BigDecimal valor;
    private EstabelecimentoResponse estabelecimento;
    private CartaoResponse cartao;
    private LocalDateTime efetivadaEm;

    @Deprecated
    public EventoDeTransacao() {}

    public EventoDeTransacao(String id,
                             BigDecimal valor,
                             EstabelecimentoResponse estabelecimento,
                             CartaoResponse cartao,
                             LocalDateTime efetivadaEm) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
        this.efetivadaEm = efetivadaEm;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoResponse getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoResponse getCartao() {
        return cartao;
    }

    public LocalDateTime getEfetivadaEm() {
        return efetivadaEm;
    }

    public Transacao toModel(CartaoRepository repository) {
        Estabelecimento estabelecimento = this.estabelecimento.toModel();
        Optional<Cartao> possivelCartao = repository.findByNumero(this.cartao.getId());
        Cartao novoCartao;

        if (possivelCartao.isPresent()) {
            novoCartao = possivelCartao.get();
        } else {
            novoCartao = this.cartao.toModel();
        }
        return new Transacao(this.id, this.valor, estabelecimento, novoCartao, this.efetivadaEm);
    }
}
