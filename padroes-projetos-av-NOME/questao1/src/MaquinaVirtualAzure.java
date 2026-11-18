// Produto concreto: maquina virtual da Azure
public class MaquinaVirtualAzure implements iMaquinaVirtual {
    public static final double CUSTO_HORA = 0.12;

    // contador sequencial compartilhado por todas as maquinas da Azure
    private static int contador = 0;

    private String id;

    public MaquinaVirtualAzure() {
        this.id = "vm-" + proximoNumero();
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
