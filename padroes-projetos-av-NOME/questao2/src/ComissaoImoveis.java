// Estrategia concreta: Imoveis, 3% do valor mais R$ 1.500,00 fixos
public class ComissaoImoveis implements iRegraComissao {

    @Override
    public double calcular(double valorVenda) {
        return valorVenda * 0.03 + 1500.00;
    }

    @Override
    public String getDescricao() {
        return "Imoveis (3% + R$ 1500,00)";
    }
}
