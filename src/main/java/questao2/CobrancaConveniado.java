package questao2;

public class CobrancaConveniado implements EstrategiaCobranca {

    public static final int MINUTOS_GRATIS = 30;
    public static final double VALOR_POR_HORA = 8.00;
    public static final double PERCENTUAL_DESCONTO = 0.25; // 25% de desconto

    @Override
    public double calcular(int minutos) {
        if (minutos <= MINUTOS_GRATIS) {
            return 0.0;
        }

        // Cobra as horas iniciadas depois dos 30 minutos gratis
        int minutosCobrados = minutos - MINUTOS_GRATIS;
        int horas = (int) Math.ceil(minutosCobrados / 60.0);
        double valorSemDesconto = horas * VALOR_POR_HORA;
        return valorSemDesconto * (1 - PERCENTUAL_DESCONTO);
    }

    @Override
    public String getNome() {
        return "Conveniado";
    }
}
