package br.com.zupacademy.priscila.transacao.transacao;

import br.com.zupacademy.priscila.transacao.utils.exception.ErroPadronizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoRepository repository;

    @GetMapping("{numero}")
    public ResponseEntity<?> detalhar (@PathVariable String numero){
        List<Transacao> transacao = repository.findByCartaoNumero(numero);
        if (transacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroPadronizado(List.of("Nenhuma transação encontrada para o cartão informado")));
        }
        return ResponseEntity.ok((transacao.stream().map(DetalheTransacaoResponse::new).collect(Collectors.toList())));
    }
}
