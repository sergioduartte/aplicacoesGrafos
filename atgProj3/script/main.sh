#!/bin/bash
# Script cria o arquivo de conexoes entre as paradas

./bin/filtra_rotas.sh
./bin/filtra_paradas.sh
./bin/cria_arestas.sh
./bin/cria_full_edges.sh
./bin/cria_full_nodes.sh
