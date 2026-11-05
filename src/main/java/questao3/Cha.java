package questao3;

public class Cha implements Bebida {

    private static final double PRECO = 4.50;

    @Override
    public String getDescricao() {
        return "Cha";
    }

    @Override
    public double getPreco() {
        return PRECO;
    }
}
