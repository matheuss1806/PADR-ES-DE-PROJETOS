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

    // RF06: monta o resumo inteiro numa String so e imprime de uma vez,
    // para nao misturar com a saida das outras threads
    public void imprimirResumo() {
        String resumo = "----- Ambiente provisionado -----\n";
        resumo += "Ambiente: " + nome + "\n";
        resumo += "Provedor: " + provedor + "\n";
        resumo += "Regiao: " + regiao + "\n";
        for (iMaquinaVirtual maquina : maquinas) {
            resumo += "  Maquina virtual: " + maquina.getId() + "\n";
        }
        resumo += "  Armazenamento: " + armazenamento.getId() + " (" + armazenamento.getTamanhoGb() + " GB)\n";
        resumo += "  Fila: " + fila.getId() + "\n";
        resumo += "Custo mensal estimado: R$ " + String.format("%.2f", getCustoMensal()) + "\n";
        resumo += "---------------------------------";
        System.out.println(resumo);
    }
}
