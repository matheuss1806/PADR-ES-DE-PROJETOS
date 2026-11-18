import java.util.ArrayList;
import java.util.List;

// Ambiente montado: guarda os recursos criados (todos do mesmo provedor)
// So conhece as interfaces dos recursos, nunca as classes concretas.
public class Ambiente {
    public static final int HORAS_MES = 730;

    private String nome;
    private String provedor;
    private String regiao;
    private List<iMaquinaVirtual> maquinas;
    private iArmazenamento armazenamento;
    private iFila fila;

    public Ambiente(String nome, String provedor, String regiao) {
        this.nome = nome;
        this.provedor = provedor;
        this.regiao = regiao;
        this.maquinas = new ArrayList<iMaquinaVirtual>();
    }

    public void adicionarMaquina(iMaquinaVirtual maquina) {
        maquinas.add(maquina);
    }

    public void setArmazenamento(iArmazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }

    public void setFila(iFila fila) {
        this.fila = fila;
    }

    // RF04: maquinas x custo/hora x 730 + GB x custo/GB + custo fixo da fila
    public double getCustoMensal() {
        double total = 0;
        for (iMaquinaVirtual maquina : maquinas) {
            total += maquina.getCustoHora() * HORAS_MES;
        }
        total += armazenamento.getCustoMensal();
        total += fila.getCustoMensal();
        return total;
    }

    // RF06: monta o resumo inteiro numa String so, para nao misturar
    // com a saida de outras threads na hora de imprimir
    public String getResumo() {
        StringBuilder sb = new StringBuilder();
        sb.append("----- Ambiente provisionado -----\n");
        sb.append("Ambiente: ").append(nome).append("\n");
        sb.append("Provedor: ").append(provedor).append("\n");
        sb.append("Regiao: ").append(regiao).append("\n");
        for (iMaquinaVirtual maquina : maquinas) {
            sb.append("  Maquina virtual: ").append(maquina.getId()).append("\n");
        }
        sb.append("  Armazenamento: ").append(armazenamento.getId())
          .append(" (").append(armazenamento.getTamanhoGb()).append(" GB)\n");
        sb.append("  Fila: ").append(fila.getId()).append("\n");
        sb.append("Custo mensal estimado: R$ ").append(String.format("%.2f", getCustoMensal())).append("\n");
        sb.append("---------------------------------");
        return sb.toString();
    }

    public String getNome() {
        return nome;
    }

    public String getProvedor() {
        return provedor;
    }

    public String getRegiao() {
        return regiao;
    }
}
