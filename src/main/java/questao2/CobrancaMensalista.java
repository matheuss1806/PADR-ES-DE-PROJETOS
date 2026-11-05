package questao2;

public class CobrancaMensalista implements EstrategiaCobranca {

    public static final double VALOR_FIXO = 5.00;

    @Override
    public double calcular(int minutos) {
        // Valor fixo, nao importa quanto tempo o carro ficou
        return VALOR_FIXO;
    }

    @Override
    public String getNome() {
        return "Mensalista";
    }
}
