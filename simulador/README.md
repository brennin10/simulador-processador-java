Simulador de Pipeline RISC em Java ⚙️

## Nomes da Equipe:
-  ANDREÂNGELO SANTANA ANDRADE
-  ANDREINA SANTANA SANTOS
-  BRENNO COSTA SANTOS
-  RONALD SOUZA RAMALHO
-  TARCISIO SILVA SILVEIRA



## 1. Visão Geral do Projeto

Este projeto consiste em um simulador de pipeline para um processador com arquitetura RISC, implementado em Java. O objetivo principal é demonstrar os conceitos de arquitetura de computadores,
como os estágios de um pipeline (IF, ID, EX, MEM, WB) e a execução de instruções a partir de um conjunto de dados externos.

O simulador é capaz de carregar instruções, o estado inicial dos registradores e o conteúdo da memória a partir de arquivos de texto, processando-os em ciclos de execução.

## 2. Estrutura do Projeto

A organização do código foi pensada para ser modular e claro.

-   `src/`: Contém todos os arquivos de código-fonte (`.java`).
-   `bin/`: Pasta de destino para os arquivos compilados (`.class`).
-   `resources/`: Pasta para os arquivos de dados (`.txt`) que são lidos pelo simulador.
-   `instructions.txt`: Arquivo que contém a lista de instruções a serem executadas.
-   `registers.txt`: Define o valor inicial dos registradores.
-   `memory.txt`: Define o conteúdo inicial da memória.

## 3. Componentes Principais

| Classe            | Função                                                                                             |
| :---------------- | :------------------------------------------------------------------------------------------------- |
| `Principal.java`  | **Ponto de entrada do programa.** Responsável por inicializar o simulador e a simulação principal.   |
| `Simulador.java`  | **O Ponto Central.** Gerencia o fluxo entre os estágios do pipeline, controlando os ciclos e os registradores. |
| `Parser.java`     | Responsável por ler os arquivos de dados (`.txt`) e traduzir o conteúdo para os objetos do programa. |
| `Instrucao.java`  | Modelo de dados que representa uma única instrução (opcode e operandos).                            |
| `Registradores.java` | Modelo de dados que representa o conjunto de registradores do processador.                          |
| `Memoria.java`     | Modelo de dados que representa a memória principal do processador.                                    |

## 4. Estágios do Pipeline Implementados

O simulador modela um pipeline de 5 estágios:

-   **IF (Instruction Fetch):** Busca a próxima instrução na memória de instruções.
-   **ID (Instruction Decode):** Decodifica a instrução para identificar a operação e os operandos.
-   **EX (Execute):** Executa a operação lógica ou aritmética.
-   **MEM (Memory Access):** Acessa a memória para operações de leitura (`LW`) ou escrita (`SW`).
-   **WB (Write Back):** Escreve o resultado final da operação de volta para um registrador.

## 5. Operadores Suportados

O simulador é capaz de executar as seguintes instruções:

-   `ADD`: Adição de registradores (`add rd, rs1, rs2`)
-   `SUB`: Subtração de registradores (`sub rd, rs1, rs2`)
-   `LW`: Carrega dados da memória para um registrador (`lw rt, offset(rs)`)
-   `SW`: Salva dados de um registrador para a memória (`sw rt, offset(rs)`)
-   **Desafio:** `SWAP`: Troca o valor de dois registradores (`swap r1, r2`)
-   **Desafio:** `AVG`: Calcula a média de dois registradores (`avg rd, rs1, rs2`)
-   **Desafio:** `REV`: Reverte a ordem dos bytes de um registrador (`rev rd, rs`)

## 6. Como Executar o Simulador

Para rodar a simulação, certifique-se de que o seu ambiente Java está configurado e que os arquivos de dados (`.txt`) estão na pasta `src/resources`.

Abra o terminal na pasta raiz do projeto e execute o seguinte comando para compilar e iniciar o programa:

```bash
# Compilar o projeto
javac -d bin -cp bin src/**/*.java

# Executar a classe principal
java -cp bin Principal
```
## 7. Considerações de Design
Este projeto foi desenvolvido com foco em:

-  Modularidade: Cada componente possui uma responsabilidade única e bem definida.

-  Eficiência: O uso de Map para registradores e memória permite acesso rápido aos dados por ID ou endereço.

-  Robustez: O carregamento de arquivos é feito usando o class loader, que garante que os recursos sejam encontrados corretamente, independentemente do ambiente de execução.

-  Extensibilidade: A estrutura modular facilita a adição de novos operadores ou estágios ao pipeline no futuro.
