#!/bin/bash
# Script retorna as ligações das paradas por rotas

lista=$(ls ./paradas)
for rota in $lista; do
  # Ordenando paradas
  sort -un ./paradas/$rota > ./paradas/$rota.temp
  rm ./paradas/$rota
  mv ./paradas/$rota.temp ./paradas/$rota

  # Criando arestas
  tamanho=$(( $(cat ./paradas/$rota | wc -l) - 1 ))
  cat ./paradas/$rota | tail -n$tamanho > ./paradas/$rota.rev
  paste ./paradas/$rota ./paradas/$rota.rev > ./paradas/$rota.result
  sed -i '$ d' ./paradas/$rota.result

  # Cria ligacao da primeira parada com a ultima
  echo  -e  "$(cat paradas/$rota.result | tail -n1 | cut -d$'\t' -f2)\t$(cat paradas/$rota.result | head -n1 | cut -d$'\t' -f1)" >> paradas/$rota.result

  # Remove Linhas vazias
  sed -i '/^\s*$/d' paradas/$rota.result

  rm ./paradas/$rota.rev
done
