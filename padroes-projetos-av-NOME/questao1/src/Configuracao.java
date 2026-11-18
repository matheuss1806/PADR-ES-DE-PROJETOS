import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Singleton: configuracao compartilhada do sistema.
// O arquivo config.properties e lido uma unica vez, no construtor privado.
// Tecnica usada: double-checked locking com volatile (seguro com varias threads).
public class Configuracao {
    private static volatile Configuracao instance;

    private String regiaoPadrao;
    private double orcamentoMaximo;

    // construtor privado: ninguem fora da classe consegue dar new
    private Configuracao() {
        Properties props = new Properties();
        InputStream entrada = null;
        try {
            entrada = abrirArquivo();
            props.load(entrada);
        } catch (IOException e) {
            throw new RuntimeException("Nao foi possivel ler o config.properties: " + e.getMessage());
        } finally {
            try {
                if (entrada != null) {
                    entrada.close();
                }
            } catch (IOException e) {
                // ignora erro ao fechar
            }
        }
        this.regiaoPadrao = props.getProperty("regiao.padrao");
        this.orcamentoMaximo = Double.parseDouble(props.getProperty("orcamento.maximo"));
        System.out.println("[Configuracao] config.properties lido (regiao=" + regiaoPadrao
                + ", orcamento maximo=R$ " + String.format("%.2f", orcamentoMaximo) + ")");
    }

    // procura o arquivo na pasta atual ou dentro de src/ (caso execute a partir da pasta questao1)
    private InputStream abrirArquivo() throws IOException {
        try {
            return new FileInputStream("config.properties");
        } catch (IOException e) {
            return new FileInputStream("src/config.properties");
        }
    }

    public static Configuracao getInstance() {
        if (instance == null) {
            synchronized (Configuracao.class) {
                if (instance == null) {
                    instance = new Configuracao();
                }
            }
        }
        return instance;
    }

    public String getRegiaoPadrao() {
        return regiaoPadrao;
    }

    public double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }
}
