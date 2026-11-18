import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// Singleton: configuracao compartilhada do sistema.
// O arquivo config.properties e lido uma unica vez, no construtor privado.
public class Configuracao {
    //Atributo para persistir a instancia
    private static Configuracao instance;

    //Demais atributos
    private String regiaoPadrao;
    private double orcamentoMaximo;

    //Construtor privado
    private Configuracao() {
        Properties props = new Properties();
        try {
            FileInputStream arquivo = new FileInputStream("config.properties");
            props.load(arquivo);
            arquivo.close();
        } catch (IOException e) {
            System.out.println("Erro ao ler o config.properties: " + e.getMessage());
        }
        regiaoPadrao = props.getProperty("regiao.padrao");
        orcamentoMaximo = Double.parseDouble(props.getProperty("orcamento.maximo"));
        System.out.println("[Configuracao] config.properties lido (regiao=" + regiaoPadrao
                + ", orcamento maximo=R$ " + String.format("%.2f", orcamentoMaximo) + ")");
    }

    //Metodo para uso da instancia
    //synchronized para que duas threads nao criem duas instancias ao mesmo tempo
    public static synchronized Configuracao getInstance() {
        if (Configuracao.instance == null) {
            Configuracao.instance = new Configuracao();
        }
        return Configuracao.instance;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }
}
