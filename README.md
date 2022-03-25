
### Desafio Dock Tech de Seleção 
Olá, queremos convidá-lo a participar de nosso desafio de seleção.  Pronto para participar? Seu trabalho será visto por nosso time e você receberá ao final um feedback sobre o que achamos do seu trabalho. Não é legal?

### Sobre a oportunidade 
A vaga é para software engineer, temos vagas com diversos níveis de senioridade e para cada um deles utilizaremos critérios específicos considerando esse aspecto, combinado? 
Se você for aprovado nesta etapa, será convidado para uma entrevista final com nosso time de especialistas.

### Desafio Técnico
  Nós trabalhamos com meios de pagamento e nada melhor que um bom sistema para gestão de contas:
  
  - Pré-requisitos:
    ```
    * Desenvolver os recursos em API Rest que realizam operações bancárias com a entidade conta a seguir:
    ```
    | Contas | Tipo |
    |-|-|
    | idConta | Numérico |
    | idPessoa | Numérico |
    | saldo | Monetário |
    | limiteSaqueDiario | Monetário |
    | flagAtivo | Condicional |
    | tipoConta | Numérido |
    | dataCriacao | Data |

    ```
    * Tabela de transações realizadas na conta
    ```
    | Transacoes | Tipo |
    |-|-|
    | idTransacao | Numérico |
    | idConta | Numérico |
    | valor | Monetário |
    | dataTransacao | Data |

    ```
    * P.S.: Não é necessário realizar operações com a tabela pessoa, mas é necessária a criação da tabela para mapeamento da relação com a conta e enviar script de criação de pelo menos uma pessoa.
    ```

    | Pessoas | Tipo |
    |-|-|
    | idPessoa | Numérico |
    | nome | Texto |
    | cpf | Texto |
    | dataNascimento | Data |    

  - O que esperamos como escopo mínimo:
    ```
    * Implementar path que realiza a criação de uma conta;
    * Implementar path que realiza operação de depósito em uma conta;
    * Implementar path que realiza operação de consulta de saldo em determinada conta;
    * Implementar path que realiza operação de saque em uma conta;
    * Implementar path que realiza o bloqueio de uma conta;
    * Implementar path que recupera o extrato de transações de uma conta;
    ```
  - O que será diferencial:
    ```
    * Implementar extrato por período;
    * Elaborar manual de execução;
    * Elaborar documentação javadoc;
    * Elaborar testes;
    * Prazo de entrega;
    ```
    
  - O que vamos avaliar:
    ```
    * Seu código; 
    * Script de banco;
    * Organização;
    * Boas práticas;
    * Diferenciais;    
    ```


### Instruções
      1. Faça o fork do desafio;
      2. Desenvolva. Você terá até 7 (sete) dias a partir da data do envio do desafio; 
      3. Envie um e-mail para arthur.azevedo@dock.tech notificando a finalização do desafio e o link do repositório para validação.



### Solução
A solução foi realizada com Spring Boot e Cassandra*.
Obs.: Single instante, mas poderia ter sido com NetworkTopology para rodo em cluster com mais de um container)

### Pré-requisitos
   1. Instalar o JDK (11+): 
      1. Windows: https://docs.oracle.com/en/java/javase/11/install/installation-jdk-microsoft-windows-platforms.html#GUID-A7E27B90-A28D-4237-9383-A58B416071CA
      2. Ubuntu: https://docs.oracle.com/en/java/javase/11/install/installation-jdk-linux-platforms.html
   2. Instalar o Docker:
      1. Windows: https://runnable.com/docker/install-docker-on-windows-10
      2. Ubuntu: https://docs.docker.com/engine/install/ubuntu/
   3. Instalar Postman*:
      1. Windows: https://learning.postman.com/docs/getting-started/installation-and-updates/#installing-postman-on-windows 
      2. Ubuntu: https://linuxize.com/post/how-to-install-postman-on-ubuntu-20-04/
   4. Instalar o Git:
      1. https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
   5. Configurar a chave SSH publica:
      1. Gerar a chave publica baseado no e-mail: https://docs.github.com/pt/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent#generating-a-new-ssh-key
      2. Configurar a chave publica no Github: https://docs.github.com/pt/developers/overview/managing-deploy-keys

OBS.: Utilizar qual utilitário se sentir mais confortável.


### Instruções:
    1. Para clonar o projeto, executar o comando:
        ```
        * git clone git@github.com:brenofr/desafio2.git
        ```
    2. Acessar o diretorio com o arquivo "Dockerfile":
        ```
        * cd desafio2/cassandra (Linux)
        ou
        * cd desafio2\cassandra (Windows)
        ```
    3. Para criar a imagem docker, executar o comando:
        ```
        * docker build -t my-cassandra-image .
        ```
    4. Para startar o container docker de cassandra, executar o comando:
        ```
        * docker run --rm -p 9042:9042 --name cassandra-container -d my-cassandra-image
        ```
        1. Para conferir se 
    5. Acessar o diretorio com o "pom.xml":
        ```
        * cd ..
        ```
    6. mvn install
    7. java -jar desafio2-0.0.1-SNAPSHOT.jar
    8. Usar a collection no diretório "postman" para fazer as operações


### Proximos passos
    1. Completar os testes de Mappers, Controllers e Services restantes
    2. Ajustar a classe RestResponseEntityExceptionHandler para exceções específicas (BusinessException e DatastoreException) 