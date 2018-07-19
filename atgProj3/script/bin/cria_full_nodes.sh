#!/bin/bash
# Script separa paras a partir de uma lista de rotas

rm ./no.temp 2> /dev/null
while read p; do
   echo $p | cut -d' ' -f1 >> ./no.temp
   echo $p | cut -d' ' -f2 >> ./no.temp
done < full-edges

sort -nu ./no.temp > full-nodes
rm no.temp
