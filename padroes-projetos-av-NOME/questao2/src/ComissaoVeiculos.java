// Estrategia concreta: Veiculos, 8% do valor limitado a R$ 5.000,00
public class ComissaoVeiculos implements iRegraComissao {

    @Override
    public double calcular(double valorVenda) {
        return Math.min(valorVenda * 0.08, 5000.00);
    }

    @Override
    public String getDescricao() {
        return "Veiculos (8%, maximo R$ 5000,00)";
    }
}
