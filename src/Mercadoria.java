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
        String nomeFormatado = String.format("%-40s", nome);
        String precoFormatado = String.format("R$ %.2f", preco);
        String linhaFormatada = nomeFormatado + " | " + String.format("%-10s", precoFormatado);
        return linhaFormatada;
    }
}
