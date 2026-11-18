// Decorator concreto: certificado de autenticidade, R$ 300,00 fixos
public class CertificadoAutenticidadeDecorator extends absServicoDecorator {
    private static final double CUSTO = 300.00;

    public CertificadoAutenticidadeDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Certificado de autenticidade: R$ " + String.format("%.2f", CUSTO);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + CUSTO;
    }
}
