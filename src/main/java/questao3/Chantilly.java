package questao3;

public class Chantilly extends Complemento {

    private static final double PRECO = 3.00;

    public Chantilly(Bebida bebida) {
        super(bebida);
    }

    @Override
    public String getDescricao() {
        return bebida.getDescricao() + ", Chantilly";
    }

    @Override
    public double getPreco() {
        return bebida.getPreco() + PRECO;
    }
}
