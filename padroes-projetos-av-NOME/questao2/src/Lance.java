// Lance aceito: guarda o participante e o valor (RF02)
public class Lance {
    private String participante;
    private double valor;

    public Lance(String participante, double valor) {
        this.participante = participante;
        this.valor = valor;
    }

    public String getParticipante() {
        return participante;
    }

    public double getValor() {
        return valor;
    }
}
