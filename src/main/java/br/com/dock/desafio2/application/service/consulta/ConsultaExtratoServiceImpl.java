package br.com.dock.desafio2.application.service.consulta;

import br.com.dock.desafio2.adapter.datastore.TransacaoDatastore;
import br.com.dock.desafio2.domain.Transacao;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsultaExtratoServiceImpl implements ConsultaExtratoService {

    private final TransacaoDatastore transacaoDatastore;

    @Override
    public List<Transacao> process(String idConta, String dataInicio, String dataFim) {
        if (Strings.isNotBlank(dataInicio) && Strings.isNotBlank(dataFim)) {
            return transacaoDatastore.get(idConta,
                    dataInicio,
                    dataFim
            );
        } else {
            return transacaoDatastore.get(idConta);
        }
    }
}
