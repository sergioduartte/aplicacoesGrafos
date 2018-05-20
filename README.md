# Aplicação de Grafos

## Repositório criado para a disciplina de Aplicações de Teoria dos Grafos - 2018.1

O objetivo desse projeto é desenvolver uma biblioteca para manipulação e representação de grafos em Java. 

O projeto está dividido em três classes base:
* Controller.java
* Edge.java
* Graph.java

E duas classes de teste:
* ControllerTest.java
* GraphTest.java

## Etapas e seus respectivos métodos:

### Leitura do arquivo:
* Criação de uma classe para manipular e tratar o path passado pelo usuário;

* Considerar arestas com peso: <em>readWeightedGraph(path)</em>
* Considerar arestas sem peso: <em>readGraph(path)</em> 

### Montar o grafo:
* Geração de um grafo baseado no processamento do arquivo (path);
* Considerar grafo sem e com peso nas arestas.

### Representar informações do grafo:
* Geração do número de vértices: <em> int getVertexNumber(graph) </em>
* Geração do número de arestas: <em> int getEdgeNumber(graph) </em>
* Grau médio: <em> float getMeanEdge(graph) </em>
* Verificação da conexão ou não do grafo: <em> boolean connected(graph) </em>

### Representar um grafo
* Representação gráfica usando matriz de adjacências: <em> String graphRepresentation (graph, type) </em>

### Pesquisar no grafo
* Busca em largura - BFS: <em> String BFS(graph, v) </em>
* Busca em profundidade - DFS: <em> String DFS(graph, v) </em>

### Informar quanto às conexões 
* Conexões mais fortes - SCC: <em> String SCC(graph) </em>
* Menor caminho entre dois vértices - Shortest Path: <em> String shortestPath(v1, v2) </em>


## Authors

### Jefferson Carlos (https://github.com/jeffersontuc)
### Marta Laís (https://github.com/martalais)
### Rafael Sampaio (https://github.com/rafaelkillua)
### Sérgio Duarte (https://github.com/sergioduartte)
### Victor Hugo (https://github.com/victorhundo)
