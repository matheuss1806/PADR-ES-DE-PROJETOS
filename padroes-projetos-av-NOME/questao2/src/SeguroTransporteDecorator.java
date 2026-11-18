// Decorator concreto: seguro de transporte, 1,5% do valor de venda
public class SeguroTransporteDecorator extends absServicoDecorator {

    public SeguroTransporteDecorator(iCompra compra) {
        super(compra);
    }

    private double custoServico() {
        return compra.getValorVenda() * 0.015;
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Seguro de transporte (1,5%): R$ " + String.format("%.2f", custoServico());
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + custoServico();
    }
}
