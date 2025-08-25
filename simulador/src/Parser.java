import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {

    public List<Instrucao> parseInstructions(String filePath) {
        List<Instrucao> instructions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length > 0) {
                    String opcode = parts[0];
                    List<String> operands = new ArrayList<>(Arrays.asList(parts).subList(1, parts.length));
                    instructions.add(new Instrucao(opcode, operands));
                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de instrucoes: " + e.getMessage());
        }
        return instructions;
    }

    public Registradores parseRegisters(String filePath) {
        Registradores regs = new Registradores();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    String regName = parts[0];
                    int value = Integer.parseInt(parts[1]);
                    regs.setReg(regName, value);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar o arquivo de registradores: " + e.getMessage());
        }
        return regs;
    }

    public Memoria parseMemory(String filePath) {
        Memoria mem = new Memoria();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/resources/" + filePath)))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    int address = Integer.parseInt(parts[0]);
                    int value = Integer.parseInt(parts[1]);
                    mem.setMem(address, value);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Erro ao carregar o arquivo de memoria: " + e.getMessage());
        }
        return mem;
    }
}