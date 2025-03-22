# Kartódromo Autônomo

![about](https://img.icons8.com/?size=40&id=3439&format=png&color=FFFFFF)
#### Este projeto é a implementação em JavaFX de um Problema de IPC (Inter-Process-Communication) simulando um trânsito de veículos autônomos e foi proposto na disciplina de Programação Concorrente no 3º Semestre do curso de Bacharelado em Ciência da Computação da Universidade Estadual do Sudoeste da Bahia. <br>O Problema proposto trata das Threads de veículos que percorrem o caminho em seus respectivos percursos, que são sorteados quando o trabalho é proposto na matéria. <br>O desafio propõe oito diagramas de percursos diferentes para cada veículo. Enquanto em uma via só é possível trafegar um veículo, seus trajetos se sobrepõem em alguns setores e possíveis colisões ou deadlocks devem ser evitadas durante a execução do programa. <br>Para solucionar esse problema, evitando as colisões ou uma possível deadlock, são utilizados semáforos implementados por todo o diagrama e cada um deles opera de forma a não permitir  que dois ou mais processos acessem mutuamente a região crítica (que é a via para apenas um veículo).

<mark> Reprodução parcial do programa em funcionamento: <mark/><br>
![gif](https://github.com/HerculesDraycon/kartodromo-autonomo-semaforos/blob/main/img/gif.gif)

![corporative](https://img.icons8.com/?size=40&id=VZEOwb3lft8h&format=png&color=FFFFFF)
#### Projeto realizado por Hércules S. Oliveira, Graduando em Bacharelado em Ciência da Computação pela Universidade Estadual do Sudoeste da Bahia. <br>Docente da matéria e Orientador do projeto: Professor Marlos André Marques Simões de Oliveira.
Junho de 2024

_________________________________________________________________________________________

![download](https://img.icons8.com/?size=50&id=86327&format=png&color=FFFFFF)
- Sua máquina deve conter uma versão do Java compatível para garantia de que o código será compilado e executado. Versões do JDK 8 são compatíveis.
- Baixe o repositório
- Descompacte a pasta gerada
- No diretório raiz da pasta descompactada, compile e execute o arquivo Principal.java
