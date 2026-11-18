// Observer concreto: participante do leilao
public class Participante implements iObservadorLote {
    private String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    // o participante manda o lance e se identifica (this) para nao ser avisado do proprio lance
    public void darLance(Lote lote, double valor) {
        lote.receberLance(nome, valor, this);
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [" + nome + "] Novo lance no lote '" + lote.getNome() + "': R$ "
                + String.format("%.2f", lance.getValor()));
    }
}
