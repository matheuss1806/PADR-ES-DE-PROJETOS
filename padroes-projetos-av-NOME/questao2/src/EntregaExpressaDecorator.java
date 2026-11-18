// Decorator concreto: entrega expressa, R$ 450,00 fixos
public class EntregaExpressaDecorator extends absServicoDecorator {

    public EntregaExpressaDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Entrega expressa: R$ " + String.format("%.2f", 450.00);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + 450.00;
    }
}
