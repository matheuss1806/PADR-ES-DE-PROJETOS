import java.util.ArrayList;
import java.util.List;

// Lote do leilao.
// - Subject do Observer: guarda a lista de iObservadorLote e avisa a cada lance aceito.
// - Context do Strategy: guarda a iRegraComissao vigente, que pode ser trocada em tempo de execucao.
// So conhece interfaces (iObservadorLote, iLicitante, iRegraComissao), nenhuma classe concreta.
public class Lote {
    private static final double AUMENTO_MINIMO = 0.05; // 5%

    private String nome;
    private String categoria;
    private double precoInicial;
    private double precoReserva;
    private double lanceAtual;
    private boolean aberto;
    private boolean vendido;
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
        this.vendido = false;
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
    private void notificarObservadores(Lance lance) {
        for (iObservadorLote observador : observadores) {
            if (observador != lance.getLicitante()) {
                observador.atualizar(this, lance);
            }
        }
    }

    // ---------- Lances (RF02) ----------
    public boolean receberLance(iLicitante licitante, double valor) {
        System.out.println(licitante.getNome() + " oferece R$ " + String.format("%.2f", valor)
                + " no lote '" + nome + "'");
        if (!aberto) {
            System.out.println("   Lance RECUSADO: o lote '" + nome + "' ja foi encerrado.");
            return false;
        }
        double minimo = lanceAtual * (1 + AUMENTO_MINIMO);
        // arredonda para centavos para evitar problema de casas decimais do double
        minimo = Math.round(minimo * 100) / 100.0;
        if (valor < minimo) {
            System.out.println("   Lance RECUSADO: o valor deve ser pelo menos 5% maior que o lance atual de R$ "
                    + String.format("%.2f", lanceAtual) + " (minimo R$ " + String.format("%.2f", minimo) + ").");
            return false;
        }
        Lance lance = new Lance(licitante, valor);
        lances.add(lance);
        lanceAtual = valor;
        System.out.println("   Lance ACEITO.");
        notificarObservadores(lance);
        return true;
    }

    // ---------- Strategy (RF07) ----------
    public void setRegraComissao(iRegraComissao regraComissao) {
        if (!aberto) {
            System.out.println("Nao e possivel trocar a regra de comissao: o lote '" + nome + "' ja foi encerrado.");
            return;
        }
        System.out.println("Regra de comissao do lote '" + nome + "' alterada de " + this.regraComissao.getDescricao()
                + " para " + regraComissao.getDescricao());
        this.regraComissao = regraComissao;
    }

    // ---------- Encerramento (RF05) ----------
    public void encerrar() {
        if (!aberto) {
            System.out.println("O lote '" + nome + "' ja estava encerrado.");
            return;
        }
        aberto = false;
        if (!lances.isEmpty() && lanceAtual >= precoReserva) {
            vendido = true;
            // a comissao usa a regra vigente no momento do encerramento
            comissao = regraComissao.calcular(lanceAtual);
            System.out.println("Lote '" + nome + "' ENCERRADO: VENDIDO para " + getMaiorLance().getNomeParticipante()
                    + " por R$ " + String.format("%.2f", lanceAtual));
        } else if (lances.isEmpty()) {
            vendido = false;
            System.out.println("Lote '" + nome + "' ENCERRADO: NAO VENDIDO (nenhum lance recebido)");
        } else {
            vendido = false;
            System.out.println("Lote '" + nome + "' ENCERRADO: NAO VENDIDO (maior lance R$ "
                    + String.format("%.2f", lanceAtual) + " abaixo da reserva de R$ "
                    + String.format("%.2f", precoReserva) + ")");
        }
    }

    // ---------- Acerto do vendedor (RF10) ----------
    public void imprimirAcertoVendedor() {
        System.out.println("===== ACERTO DO VENDEDOR =====");
        System.out.println("Lote: " + nome + " (" + categoria + ")");
        if (!vendido) {
            System.out.println("Lote nao vendido: sem comissao e sem valor a repassar.");
            return;
        }
        System.out.println("Valor de venda:      R$ " + String.format("%.2f", lanceAtual));
        System.out.println("Comissao cobrada:    R$ " + String.format("%.2f", comissao)
                + " [regra " + regraComissao.getDescricao() + "]");
        System.out.println("Valor liquido:       R$ " + String.format("%.2f", getValorLiquidoVendedor()));
    }

    public double getValorLiquidoVendedor() {
        return lanceAtual - comissao;
    }

    // ---------- getters ----------
    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecoInicial() {
        return precoInicial;
    }

    public double getPrecoReserva() {
        return precoReserva;
    }

    public double getLanceAtual() {
        return lanceAtual;
    }

    public Lance getMaiorLance() {
        if (lances.isEmpty()) {
            return null;
        }
        return lances.get(lances.size() - 1);
    }

    public int getTotalLances() {
        return lances.size();
    }

    public boolean isAberto() {
        return aberto;
    }

    public boolean isVendido() {
        return vendido;
    }

    public double getValorVenda() {
        return lanceAtual;
    }

    public double getComissao() {
        return comissao;
    }

    public iRegraComissao getRegraComissao() {
        return regraComissao;
    }
}
