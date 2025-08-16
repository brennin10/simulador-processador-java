import java.util.List;
public class Instrucao {
    private String opcode;
    private List<String> operands;

    public Instrucao(String opcode, List<String> operands) {
        this.opcode = opcode;
        this.operands = operands;
    }

    public String getOpcode() {
        return opcode;
    }

    public List<String> getOperands() {
        return operands;
    }

    @Override
    public String toString() {
        return opcode + " " + String.join(" ", operands);
    }
    
}
