// Estrategia concreta: Eletronicos, 12% do valor com minimo de R$ 50,00
public class ComissaoEletronicos implements iRegraComissao {

    @Override
    public double calcular(double valorVenda) {
        return Math.max(valorVenda * 0.12, 50.00);
    }

    @Override
    public String getDescricao() {
        return "Eletronicos (12%, minimo R$ 50,00)";
    }
}
