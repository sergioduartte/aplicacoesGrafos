#!/bin/bash
# Script separa paras a partir de uma lista de rotas

rm ./full-edges ./arestas1 ./arestas2 ./tipo ./rota-ligada 2> /dev/null
lista=$(ls ./paradas | grep -w result)
for rota in $lista; do
  cat ./paradas/$rota | cut -d$'\t' -f1 >> ./arestas1
  cat ./paradas/$rota | cut -d$'\t' -f2 >> ./arestas2
  n_linhas=$(cat ./paradas/$rota | wc -l)
  rota=$(echo $rota | cut -d'.' -f1)
  yes "$rota" | head -n $n_linhas >> ./rota-ligada
  yes Directed | head -n $n_linhas >> ./tipo
done

paste ./arestas1 ./arestas2 ./tipo ./rota-ligada >> full-edges
rm ./arestas1 ./arestas2 ./tipo ./rota-ligada
