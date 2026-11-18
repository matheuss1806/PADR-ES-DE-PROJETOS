// Componente concreto: a compra do lote sem nenhum servico adicional
public class CompraLote implements iCompra {
    private String nomeLote;
    private double valorVenda;

    public CompraLote(String nomeLote, double valorVenda) {
        this.nomeLote = nomeLote;
        this.valorVenda = valorVenda;
    }

    @Override
    public String getDescricao() {
        return "Lote: " + nomeLote + "\nValor de venda: R$ " + String.format("%.2f", valorVenda);
    }

    @Override
    public double getCusto() {
        return valorVenda;
    }

    @Override
    public double getValorVenda() {
        return valorVenda;
    }
}
