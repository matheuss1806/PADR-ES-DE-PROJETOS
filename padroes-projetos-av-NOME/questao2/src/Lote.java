import java.util.ArrayList;
import java.util.List;

// Lote do leilao.
// - Subject do Observer: guarda a lista de iObservadorLote e avisa a cada lance aceito.
// - Context do Strategy: guarda a iRegraComissao vigente, que pode ser trocada em tempo de execucao.
// So conhece as interfaces iObservadorLote e iRegraComissao, nenhuma classe concreta.
public class Lote {
    private String nome;
    private String categoria;
    private double precoInicial;
    private double precoReserva;
    private double lanceAtual;
    private boolean aberto;
    private double comissao;

    private List<Lance> lances;
    private List<iObservadorLote> observadores;
    private iRegraComissao regraComissao;

    public Lote(String nome, String categoria, double precoInicial, double precoReserva, iRegraComissao regraComissao) {
        this.nome = nome;
        this.categoria = categoria;
        this.precoInicial = precoInicial;
        this.precoReserva = precoReserva;
        this.lanceAtual = precoInicial; // RF01: ate o primeiro lance vale o preco inicial
        this.regraComissao = regraComissao;
        this.aberto = true;
        this.lances = new ArrayList<Lance>();
        this.observadores = new ArrayList<iObservadorLote>();
    }

    // ---------- Observer ----------
    public void adicionarObservador(iObservadorLote observador) {
        observadores.add(observador);
    }

    public void removerObservador(iObservadorLote observador) {
        observadores.remove(observador);
    }

    // avisa todos que acompanham o lote, menos quem deu o lance
    private void notificarObservadores(Lance lance, iObservadorLote autor) {
        for (iObservadorLote observador : observadores) {
            if (observador != autor) {
                observador.atualizar(this, lance);
            }
        }
    }

    // ---------- Lances (RF02) ----------
    public void receberLance(String participante, double valor, iObservadorLote autor) {
        System.out.println(participante + " oferece R$ " + String.format("%.2f", valor) + " no lote '" + nome + "'");
        if (!aberto) {
            System.out.println("   Lance RECUSADO: o lote '" + nome + "' ja foi encerrado.");
            return;
        }
        double minimo = lanceAtual * 1.05; // pelo menos 5% maior
        if (valor < minimo) {
            System.out.println("   Lance RECUSADO: o valor deve ser pelo menos 5% maior que o lance atual de R$ "
                    + String.format("%.2f", lanceAtual) + " (minimo R$ " + String.format("%.2f", minimo) + ").");
            return;
        }
        Lance lance = new Lance(participante, valor);
        lances.add(lance);
        lanceAtual = valor;
        System.out.println("   Lance ACEITO.");
        notificarObservadores(lance, autor);
    }

    // ---------- Strategy (RF07) ----------
    public void setRegraComissao(iRegraComissao regraComissao) {
        System.out.println("Regra de comissao do lote '" + nome + "' alterada de " + this.regraComissao.getDescricao()
                + " para " + regraComissao.getDescricao());
        this.regraComissao = regraComissao;
    }

    // ---------- Encerramento (RF05) ----------
    public void encerrar() {
        aberto = false;
        if (!lances.isEmpty() && lanceAtual >= precoReserva) {
            // a comissao usa a regra vigente no momento do encerramento
            comissao = regraComissao.calcular(lanceAtual);
            System.out.println("Lote '" + nome + "' ENCERRADO: VENDIDO para " + getComprador()
                    + " por R$ " + String.format("%.2f", lanceAtual));
        } else {
            System.out.println("Lote '" + nome + "' ENCERRADO: NAO VENDIDO (preco de reserva de R$ "
                    + String.format("%.2f", precoReserva) + " nao foi atingido)");
        }
    }

    // ---------- Acerto do vendedor (RF10) ----------
    public void imprimirAcertoVendedor() {
        System.out.println("===== ACERTO DO VENDEDOR =====");
        System.out.println("Lote: " + nome + " (" + categoria + ")");
        System.out.println("Valor de venda:      R$ " + String.format("%.2f", lanceAtual));
        System.out.println("Comissao cobrada:    R$ " + String.format("%.2f", comissao)
                + " [regra " + regraComissao.getDescricao() + "]");
        System.out.println("Valor liquido:       R$ " + String.format("%.2f", lanceAtual - comissao));
    }

    // ---------- getters ----------
    public String getNome() {
        return nome;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public int getTotalLances() {
        return lances.size();
    }

    // o comprador e quem deu o ultimo lance aceito (o maior)
    public String getComprador() {
        return lances.get(lances.size() - 1).getParticipante();
    }
}
