package br.com.zupacademy.priscila.transacao.events;

import br.com.zupacademy.priscila.transacao.cartao.CartaoRepository;
import br.com.zupacademy.priscila.transacao.transacao.EventoDeTransacao;
import br.com.zupacademy.priscila.transacao.transacao.Transacao;
import br.com.zupacademy.priscila.transacao.transacao.TransacaoRepository;
import br.com.zupacademy.priscila.transacao.utils.ExecutorTransacao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TransacaoListener {

    private final Logger logger = LoggerFactory.getLogger(TransacaoListener.class);

    @Autowired
    private ExecutorTransacao executor;

    @Autowired
    private CartaoRepository repository;

    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(EventoDeTransacao mensagem) {
        try {
            logger.info("Nova transacao = {}", mensagem);
            Transacao transacao = mensagem.toModel(repository);
            executor.atualizaEComita(transacao);
        } catch (Exception e) {
            logger.error("Não foi possivel salvar a transação");
            e.printStackTrace();
        }
    }
}
