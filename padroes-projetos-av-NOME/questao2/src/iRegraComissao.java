// Strategy: regra de comissao cobrada do vendedor
public interface iRegraComissao {
    double calcular(double valorVenda);
    String getDescricao();
}
