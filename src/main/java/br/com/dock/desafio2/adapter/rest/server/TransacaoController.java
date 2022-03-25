package br.com.dock.desafio2.adapter.rest.server;

import br.com.dock.desafio2.adapter.datastore.utils.ConverteDataUtil;
import br.com.dock.desafio2.adapter.rest.model.TransacaoDto;
import br.com.dock.desafio2.application.service.consulta.ConsultaExtratoService;
import br.com.dock.desafio2.application.service.consulta.ConsultaSaldoService;
import br.com.dock.desafio2.application.service.transacao.RealizaDepositoService;
import br.com.dock.desafio2.application.service.transacao.RealizaSaqueService;
import br.com.dock.desafio2.domain.Transacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.EMPTY;

@Component
@RestController
@RequestMapping("/transacao")
@RequiredArgsConstructor
public class TransacaoController extends ConverteDataUtil {

    private final RealizaDepositoService realizaDepositoService;
    private final RealizaSaqueService realizaSaqueService;
    private final ConsultaSaldoService consultaSaldoService;
    private final ConsultaExtratoService consultaExtratoService;
    private final ObjectMapper mapper;

    @PostMapping("/deposita")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deposita(final @RequestBody TransacaoDto transacaoDto) {
        Transacao transacao = criaTransacao(transacaoDto);

        realizaDepositoService.process(transacao);
    }

    @PostMapping("/saque")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sacaValor(final @RequestBody TransacaoDto transacaoDto) {
        Transacao transacao = criaTransacao(transacaoDto);

        realizaSaqueService.process(transacao);
    }

    private Transacao criaTransacao(TransacaoDto transacaoDto) {
        return Transacao.builder()
                .idConta(transacaoDto.getIdConta())
                .valor(transacaoDto.getValor())
                .dataTransacao(LocalDate.now())
                .build();
    }
}
