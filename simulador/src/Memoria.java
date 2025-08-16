import java.util.HashMap;
import java.util.Map;

public class Memoria {
    private Map<Integer, Integer> memory;

    public Memoria() {
        this.memory = new HashMap<>();
    }

    public Integer getMem(int address) {
        return memory.getOrDefault(address, 0);
    }

    public void setMem(int address, int value) {
        memory.put(address, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Conteúdo da Memória (endereços não-nulos):\n");
        memory.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
        return sb.toString();  
    }    
}
