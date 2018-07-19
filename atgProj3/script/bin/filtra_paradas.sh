#!/bin/bash
# Script separa paras a partir de uma lista de rotas

rm -rf ./paradas 2> /dev/null
mkdir ./paradas
while read p; do
  id=$(echo $p | cut -d',' -f1)
  parada=$(echo $p | cut -d',' -f6-17)
  for rota in $(cat ./rotas); do
    if [ $(echo $parada | grep -w $rota) ]; then
      echo $id >> ./paradas/$rota
    fi
  done
done < grafo.csv
