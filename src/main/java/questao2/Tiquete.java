package questao2;

// Tiquete e o "contexto" do Strategy: ele so delega o calculo para a estrategia atual
public class Tiquete {

    private String placa;
    private int minutos;
    private EstrategiaCobranca estrategia;

    public Tiquete(String placa, int minutos, EstrategiaCobranca estrategia) {
        this.placa = placa;
        this.minutos = minutos;
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaCobranca estrategia) {
        this.estrategia = estrategia;
    }

    public double calcularValor() {
        return estrategia.calcular(minutos);
    }

    public void imprimir() {
        System.out.println("Placa: " + placa + " | Tempo: " + minutos + " min | Perfil: "
                + estrategia.getNome() + " | Valor: R$ " + String.format("%.2f", calcularValor()));
    }

    public String getPlaca() {
        return placa;
    }

    public int getMinutos() {
        return minutos;
    }
}
