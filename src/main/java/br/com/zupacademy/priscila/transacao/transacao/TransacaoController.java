package br.com.zupacademy.priscila.transacao.transacao;

import br.com.zupacademy.priscila.transacao.utils.exception.ErroPadronizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<?> detalhar (@PathVariable String numero,
                                       @PageableDefault(sort = "efetivadaEm", direction = Sort.Direction.DESC, page = 0, size = 10)Pageable paginacao){
        Page<Transacao> transacoes = repository.findByCartaoNumero(numero, paginacao);
        if (transacoes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErroPadronizado(List.of("Nenhuma transação encontrada para o cartão informado")));
        }
        return ResponseEntity.ok((transacoes.stream().map(DetalheTransacaoResponse::new).collect(Collectors.toList())));
    }
}
