// Observer concreto: participante do leilao
public class Participante implements iObservadorLote, iLicitante {
    private String nome;

    public Participante(String nome) {
        this.nome = nome;
    }

    // facilita a leitura na Main: ana.darLance(lote, 1000)
    public boolean darLance(Lote lote, double valor) {
        return lote.receberLance(this, valor);
    }

    @Override
    public void atualizar(Lote lote, Lance lance) {
        System.out.println("   [" + nome + "] Novo lance no lote '" + lote.getNome() + "': R$ "
                + String.format("%.2f", lance.getValor()));
    }

    @Override
    public String getNome() {
        return nome;
    }
}
