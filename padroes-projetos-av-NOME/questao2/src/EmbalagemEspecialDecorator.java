// Decorator concreto: embalagem especial, R$ 180,00 fixos
public class EmbalagemEspecialDecorator extends absServicoDecorator {

    public EmbalagemEspecialDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Embalagem especial: R$ 180.00";
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + 180.00;
    }
}
