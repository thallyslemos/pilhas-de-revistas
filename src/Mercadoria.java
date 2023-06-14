public class Mercadoria {
    private String nome;
    private double preco;

    public Mercadoria(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    } 

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
