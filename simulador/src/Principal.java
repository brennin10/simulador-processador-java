
public class Principal {
    public static void main(String[] args) {
        System.out.println("Iniciando Simulador...");

        // Defina apenas os nomes dos arquivos
        String instFile = "instructions.txt";
        String regFile = "registers.txt";
        String memFile = "memory.txt";

        Simulador simulador = new Simulador(instFile, regFile, memFile);

        simulador.runPipeline();
    }
}