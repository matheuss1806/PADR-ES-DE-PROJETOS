package questao3;

public class Leite extends Complemento {

    private static final double PRECO = 2.00;

    public Leite(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Leite";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + PRECO;
    }
}
