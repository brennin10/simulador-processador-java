import java.util.List;

public class Simulador {

    private Parser parser;
    private Registradores registradores;
    private Memoria memoria;
    private List<Instrucao> instructions;
    private int programCounter; // Contador de programa

    public Simulador(String instFile, String regFile, String memFile) {
        this.parser = new Parser();
        this.instructions = parser.parseInstructions(instFile);
        this.registradores = parser.parseRegisters(regFile);
        this.memoria = parser.parseMemory(memFile);
        this.programCounter = 0; // Começa na primeira instrução
    }

    public void runPipeline() {
        System.out.println("Iniciando a simulacao do pipeline...");
        while (programCounter < instructions.size()) {
            System.out.println("\n--- Ciclo " + (programCounter + 1) + " ---");
            
            // Estágio 1: IF - Busca de Instrução
            Instrucao fetchedInstruction = instructions.get(programCounter);
            System.out.println("IF: Buscando instrucao '" + fetchedInstruction + "'");

            // Estágio 2: ID - Decodificação de Instrução
            System.out.println("ID: Decodificando instrucao...");
            // Lógica de decodificação iria aqui
            
            // Estágio 3: EX - Execução
            System.out.println("EX: Executando operacao...");
            execute(fetchedInstruction);

            // Estágio 4: MEM - Acesso à Memória
            System.out.println("MEM: Acessando memoria (se necessario)...");
            
            // Estágio 5: WB - Escrita de Volta
            System.out.println("WB: Escrevendo resultado (se necessario)...");
            
            programCounter++; // Avança para a próxima instrução
        }
        System.out.println("\nSimulacao concluida.");
        System.out.println(this.registradores); // Mostra o estado final dos registradores
        System.out.println(this.memoria); // Mostra o estado final da memoria
    }

    private void execute(Instrucao instruction) {
        // Lógica de execução de cada opcode (ADD, SUB, LW, SW, etc.)
        String opcode = instruction.getOpcode();
        List<String> operands = instruction.getOperands();

        switch (opcode.toLowerCase()) {
            case "add": {
                // ADD rd, rs1, rs2 (ex: add r0 r1 r2)
                int val1 = registradores.getReg(operands.get(1));
                int val2 = registradores.getReg(operands.get(2));
                int result = val1 + val2;
                registradores.setReg(operands.get(0), result);
                System.out.println("  -> " + operands.get(0) + " = " + val1 + " + " + val2 + " = " + result);
                break;
            }
            case "sub": {
                // SUB rd, rs1, rs2 (ex: sub r0 r1 r2)
                int val1 = registradores.getReg(operands.get(1));
                int val2 = registradores.getReg(operands.get(2));
                int result = val1 - val2;
                registradores.setReg(operands.get(0), result);
                System.out.println("  -> " + operands.get(0) + " = " + val1 + " - " + val2 + " = " + result);
                break;
            }
            case "lw": {
                // LW rt, offset(rs) (ex: lw r0 4(r1))
                String regDestino = operands.get(0);
                String[] offsetParts = operands.get(1).split("[()]"); // Separa offset e registrador base
                int offset = Integer.parseInt(offsetParts[0]);
                String regBase = offsetParts[1];
                int endereco = registradores.getReg(regBase) + offset;
                int valorLido = memoria.getMem(endereco);
                registradores.setReg(regDestino, valorLido);
                System.out.println("  -> " + regDestino + " = MEMORIA[" + endereco + "] = " + valorLido);
                break;
            }
            case "sw": {
                // SW rt, offset(rs) (ex: sw r0 4(r1))
                String regOrigem = operands.get(0);
                String[] offsetParts = operands.get(1).split("[()]");
                int offset = Integer.parseInt(offsetParts[0]);
                String regBase = offsetParts[1];
                int endereco = registradores.getReg(regBase) + offset;
                int valorSalvo = registradores.getReg(regOrigem);
                memoria.setMem(endereco, valorSalvo);
                System.out.println("  -> MEMORIA[" + endereco + "] = " + valorSalvo);
                break;
            }
            // Operadores de Desafio
            case "swap": {
                // SWAP r1, r2 (ex: swap r0 r1)
                String reg1 = operands.get(0);
                String reg2 = operands.get(1);
                int temp = registradores.getReg(reg1);
                registradores.setReg(reg1, registradores.getReg(reg2));
                registradores.setReg(reg2, temp);
                System.out.println("  -> Conteudo de " + reg1 + " e " + reg2 + " trocado.");
                break;
            }
            case "avg": {
                // AVG rd, rs1, rs2 (ex: avg r0 r1 r2)
                int val1 = registradores.getReg(operands.get(1));
                int val2 = registradores.getReg(operands.get(2));
                int result = (val1 + val2) / 2;
                registradores.setReg(operands.get(0), result);
                System.out.println("  -> " + operands.get(0) + " = media de " + val1 + " e " + val2 + " = " + result);
                break;
            }
            case "rev": {
                // REV rd, rs (ex: rev r0 r1)
                String regDestino = operands.get(0);
                String regOrigem = operands.get(1);
                int valorOriginal = registradores.getReg(regOrigem);

                // Lógica de reversão de bits
                int reversedValue = 0;
                // Reverte a ordem dos 4 bytes (8 bits cada)
                for (int i = 0; i < 4; i++) {
                    int byteValue = (valorOriginal >> (i * 8)) & 0xFF; // Extrai o byte
                    reversedValue = (reversedValue << 8) | byteValue; // Adiciona no início
                }
                registradores.setReg(regDestino, reversedValue);
                System.out.println("  -> " + regDestino + " = " + regOrigem + " com bytes revertidos.");
                break;
            }
            default:
                System.out.println("Operacao '" + opcode + "' desconhecida.");
        }
    }
}