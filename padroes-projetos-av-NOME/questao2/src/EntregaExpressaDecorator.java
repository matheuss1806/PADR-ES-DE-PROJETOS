// Decorator concreto: entrega expressa, R$ 450,00 fixos
public class EntregaExpressaDecorator extends absServicoDecorator {
    private static final double CUSTO = 450.00;

    public EntregaExpressaDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Entrega expressa: R$ " + String.format("%.2f", CUSTO);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + CUSTO;
    }
}
