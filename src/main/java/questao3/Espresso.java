package questao3;

public class Espresso implements Bebida {

    private static final double PRECO = 6.00;

    @Override
    public String getDescricao() {
        return "Espresso";
    }

    @Override
    public double getPreco() {
        return PRECO;
    }
}
