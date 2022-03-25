#FROM cassandra:3.0.20
#
#ENV CASSANDRA_KEYSPACE desafio_2_keyspace
#
#ENV TABLE1 tb_transacao
#ENV TABLE2 tb_conta
#ENV TABLE3 tb_pessoa
#
#COPY entrypoint.sh /entrypoint.sh
#ENTRYPOINT ["/entrypoint.sh"]
#CMD ["cassandra", "-f"]


FROM cassandra:3.11

ENV CASSANDRA_KEYSPACE desafio_2_keyspace

ENV TABLE1 tb_transacao
ENV TABLE2 tb_conta
ENV TABLE3 tb_pessoa

ENV LANG C.UTF-8

COPY *.cql /docker-entrypoint-initdb.d/

COPY entrypoint.sh /

ENTRYPOINT ["/entrypoint.sh"]
CMD ["cassandra", "-f"]