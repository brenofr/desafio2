package br.com.dock.desafio2.application.service.conta;

import br.com.dock.desafio2.adapter.datastore.ContaDatastore;
import br.com.dock.desafio2.adapter.datastore.entity.TransacaoEntity;
import br.com.dock.desafio2.application.exception.BusinessException;
import br.com.dock.desafio2.application.exception.DatastoreException;
import br.com.dock.desafio2.domain.Conta;
import br.com.dock.desafio2.domain.Transacao;
import com.datastax.oss.driver.api.core.servererrors.InvalidQueryException;
import com.datastax.oss.driver.api.core.servererrors.UnavailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.fasterxml.uuid.Generators.randomBasedGenerator;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = {CriaContaServiceImpl.class})
public class CriaContaServiceImplTest {

    @Autowired
    private CriaContaService service;

    @MockBean
    private ContaDatastore datastore;

    private Conta conta;

    @BeforeEach
    public void setup() {
        conta = Conta.builder()
                .idConta(randomBasedGenerator().generate().toString())
                .idPessoa(randomBasedGenerator().generate().toString())
                .tipoConta(1)
                .flagAtivo(true)
                .saldo(0)
                .dataCriacao(LocalDateTime.now())
                .limiteSaqueDiario(150)
                .build();
    }

    @Test
    public void cadastraConta_inputEhValido_insercaoComSucesso() {
        when(datastore.get(any(), anyBoolean()))
                .thenReturn(Optional.empty());

        service.process(conta);

        verify(datastore, times(1)).get(any(), anyBoolean());
        verify(datastore, times(1)).put(any());
    }

    @Test
    public void cadastraConta_inputEhInvalido_insercaoComFalha() {
        when(datastore.get(any(), anyBoolean()))
                .thenThrow(BusinessException.class);

        Exception exception = assertThrows(BusinessException.class, () -> {
            service.process(conta);
        });

        assertTrue(exception instanceof BusinessException);

        verify(datastore, times(1)).get(any(), anyBoolean());
        verify(datastore, times(0)).put(any());
    }
}
