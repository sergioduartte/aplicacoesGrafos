#!/bin/bash

cat ./grafo.csv | cut -d',' -f5-17 | tr ',' ' ' > ./rota.temp

# Remove Linhas vazias
sed -i '/^\s*$/d' ./rota.temp

while read p; do
  for rota in $p; do
      echo $rota >> ./rota.temp2
  done
done < rota.temp

sort -nu ./rota.temp2 > rotas
rm ./rota.temp2 ./rota.temp
