// Decorator concreto: certificado de autenticidade, R$ 300,00 fixos
public class CertificadoAutenticidadeDecorator extends absServicoDecorator {

    public CertificadoAutenticidadeDecorator(iCompra compra) {
        super(compra);
    }

    @Override
    public String getDescricao() {
        return compra.getDescricao() + "\n + Certificado de autenticidade: R$ " + String.format("%.2f", 300.00);
    }

    @Override
    public double getCusto() {
        return compra.getCusto() + 300.00;
    }
}
