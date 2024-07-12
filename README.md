#Kartódromo Autônomo

Este projeto é a implementação em JavaFX de um Problema de IPC (Inter-Process-Communication) simulando um trânsito de veículos autônomos e foi proposto na disciplina de Programação Concorrente no 3º Semestre do curso de Bacharelado em Ciência da Computação da Universidade Estadual do Sudoeste da Bahia.
O Problema proposto trata das Threads de veículos que percorrem o caminho em seus respectivos percursos, que são sorteados quando o trabalho é proposto na matéria.
O desafio propõe oito diagramas de percursos diferentes para cada veículo. Enquanto em uma via só é possível trafegar um veículo, seus trajetos se sobrepõem em alguns setores e possíveis colisões ou deadlocks devem ser evitadas durante a execução do programa.
Para solucionar esse problema, evitando as colisões ou uma possível deadlock, são utilizados semáforos implementados por todo o diagrama e cada um deles opera de forma a não permitir  que dois ou mais processos acessem mutuamente a região crítica (que é a via para apenas um veículo).

![gif](https://github.com/HerculesDraycon/kartodromo-autonomo-semaforos/blob/main/img/gif.gif)

Projeto realizado por Hércules S. Oliveira, Graduando em Bacharelado em Ciência da Computação pela Universidade Estadual do Sudoeste da Bahia.
Docente da matéria e Orientador do projeto: Professor Marlos André Marques Simões de Oliveira.
Junho do 2024
