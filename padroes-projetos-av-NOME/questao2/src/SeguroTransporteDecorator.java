// Decorator concreto: seguro de transporte, 1,5% do valor de venda
public class SeguroTransporteDecorator extends absServicoDecorator {

    public SeguroTransporteDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Seguro de transporte (1,5%): R$ "
                + String.format("%.2f", compra.getValorVenda() * 0.015);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + compra.getValorVenda() * 0.015;
    }
}
