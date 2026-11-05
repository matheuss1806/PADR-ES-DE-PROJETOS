package questao2;

// Cada perfil de cobranca implementa esta interface com a sua propria regra
public interface EstrategiaCobranca {

    double calcular(int minutos);

    String getNome();
}
