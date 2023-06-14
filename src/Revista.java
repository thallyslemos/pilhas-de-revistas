public class Revista {
    private String titulo;
    private int numero_edicao;
    private int mes_publicacao;
    private int ano_publicacao;
    private int numero_volume;

    // Construtor para revistas com volume
    public Revista(String titulo,
    int numero_edicao,
    int mes_publicacao,
    int ano_publicacao,
    int numero_volume) {
        // validacao de mes e ano de publicacao
        this.titulo = titulo;
        this.numero_edicao = numero_edicao;
        this.mes_publicacao = mes_publicacao;
        this.ano_publicacao = ano_publicacao;
        this.numero_volume = numero_volume;
    }
    
    // Construtor para revistas sem volume
    public Revista(String titulo,
    int numero_edicao,
    int mes_publicacao,
    int ano_publicacao) {
        // validacao de mes e ano de publicacao
        this.titulo = titulo;
        this.numero_edicao = numero_edicao;
        this.mes_publicacao = mes_publicacao;
        this.ano_publicacao = ano_publicacao;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public int getMes_publicacao() {
        return mes_publicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumero_edicao() {
        return numero_edicao;
    }

    public int getNumero_volume() {
        return numero_volume;
    }

    @Override
    public String toString() {
        // alterar impleentação
        return super.toString();
    }


}
