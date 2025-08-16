import java.util.HashMap;
import java.util.Map;

public class Registradores {
    private Map<String, Integer> regs;

    public Registradores() {
        this.regs = new HashMap<>();
        for(int i = 0; i < 32; i++) {
            this.regs.put("r" + i, 0);
        }
    }

    public Integer getRegs(String reg) {
        return regs.getOrDefault(reg, 0);
    }

    public void setRegs(String reg, int value) {
        regs.put(reg, value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registradores:\n");
        regs.forEach((key, value) -> sb.append(key).append(": ").append(value).append("\n"));
        return sb.toString();
    }  
}
