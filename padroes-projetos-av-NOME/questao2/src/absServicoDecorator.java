// Decorator abstrato: todo servico adicional "embrulha" uma compra
public abstract class absServicoDecorator implements iCompra {
    protected iCompra compra;

    // Construtor da classe abstrata
    public absServicoDecorator(iCompra compra) {
        this.compra = compra;
    }

    // o valor de venda nao muda com os servicos, so repassa
    @Override
    public double getValorVenda() {
        return compra.getValorVenda();
    }
}
