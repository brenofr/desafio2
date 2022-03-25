package br.com.dock.desafio2.adapter.rest.server;

import br.com.dock.desafio2.adapter.rest.model.ContaDto;
import br.com.dock.desafio2.application.service.conta.BloqueiaContaService;
import br.com.dock.desafio2.application.service.conta.CriaContaService;
import br.com.dock.desafio2.domain.Conta;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Component
@RestController
@RequestMapping("/conta")
@RequiredArgsConstructor
public class ContaController {

    private final CriaContaService criaContaService;
    private final BloqueiaContaService bloqueiaContaService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void criaConta(final @RequestBody ContaDto contaDto) {
        Conta conta = Conta.builder()
                .idConta(contaDto.getIdConta())
                .idPessoa(contaDto.getIdPessoa())
                .limiteSaqueDiario(contaDto.getLimiteSaqueDiario())
                .tipoConta(contaDto.getTipoConta())
                .saldo(0)
                .flagAtivo(true)
                .dataCriacao(LocalDateTime.now())
                .build();

        criaContaService.process(conta);
    }

    @PatchMapping("/block/{id_conta}")
    public ResponseEntity blockConta(final @PathVariable("id_conta") String idConta) {
        bloqueiaContaService.process(idConta);

        return new ResponseEntity(HttpStatus.OK);
    }
}
