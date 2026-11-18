// Produto concreto: maquina virtual da Aws
public class MaquinaVirtualAws implements iMaquinaVirtual {
    public static final double CUSTO_HORA = 0.10;

    // contador sequencial compartilhado por todas as maquinas da Aws
    private static int contador = 0;

    private String id;

    public MaquinaVirtualAws() {
        this.id = "i-" + proximoNumero();
    }

    // synchronized para nao repetir numero quando duas threads criam ao mesmo tempo
    private static synchronized int proximoNumero() {
        contador++;
        return contador;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getCustoHora() {
        return CUSTO_HORA;
    }
}
