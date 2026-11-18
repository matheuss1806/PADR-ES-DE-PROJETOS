// Lance aceito: guarda quem deu o lance e o valor (RF02)
public class Lance {
    private iLicitante licitante;
    private double valor;

    public Lance(iLicitante licitante, double valor) {
        this.licitante = licitante;
        this.valor = valor;
    }

    public iLicitante getLicitante() {
        return licitante;
    }

    public String getNomeParticipante() {
        return licitante.getNome();
    }

    public double getValor() {
        return valor;
    }
}
