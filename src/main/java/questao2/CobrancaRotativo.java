package questao2;

public class CobrancaRotativo implements EstrategiaCobranca {

    public static final double VALOR_POR_HORA = 8.00;

    @Override
    public double calcular(int minutos) {
        // Hora iniciada: qualquer fracao de hora conta como uma hora inteira
        int horas = (int) Math.ceil(minutos / 60.0);
        return horas * VALOR_POR_HORA;
    }

    @Override
    public String getNome() {
        return "Rotativo";
    }
}
