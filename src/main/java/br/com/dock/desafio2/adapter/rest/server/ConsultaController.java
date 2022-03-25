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
@RequestMapping("/consulta")
@RequiredArgsConstructor
public class ConsultaController extends ConverteDataUtil {

    private final RealizaDepositoService realizaDepositoService;
    private final RealizaSaqueService realizaSaqueService;
    private final ConsultaSaldoService consultaSaldoService;
    private final ConsultaExtratoService consultaExtratoService;
    private final ObjectMapper mapper;

    @GetMapping("/saldo/{id_conta}")
    public ResponseEntity<String> obtemSaldo(final @PathVariable("id_conta") String idConta) {
        double saldo = consultaSaldoService.process(idConta);

        return ResponseEntity.ok(String.format("R$ %,.2f", saldo));
    }

    @SneakyThrows
    @GetMapping("/extrato")
    public ResponseEntity<String> obtemExtrato(final @RequestParam(name = "id_conta") String idConta,
                                               final @RequestParam(name = "data_inicio", required = false) String dataInicio,
                                               final @RequestParam(name = "data_fim", required = false) String dataFim) {
        List<Transacao> transacoes = consultaExtratoService.process(idConta, dataInicio, dataFim);
        if (transacoes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(EMPTY);
        }

        return ResponseEntity.ok(mapper.writeValueAsString(transacoes));
    }
}
