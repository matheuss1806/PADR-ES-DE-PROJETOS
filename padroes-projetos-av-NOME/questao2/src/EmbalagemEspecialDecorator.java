// Decorator concreto: embalagem especial, R$ 180,00 fixos
public class EmbalagemEspecialDecorator extends absServicoDecorator {
    private static final double CUSTO = 180.00;

    public EmbalagemEspecialDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Embalagem especial: R$ " + String.format("%.2f", CUSTO);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + CUSTO;
    }
}
