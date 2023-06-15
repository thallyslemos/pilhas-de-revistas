import java.time.Duration;
import java.time.LocalDateTime;

public class Revista {
    private String titulo;
    private int numero_edicao;
    private int mes_publicacao;
    private int ano_publicacao;
    private int numero_volume;
    private LocalDateTime data_cadastro;
    private LocalDateTime data_retirada;

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
        this.data_cadastro = LocalDateTime.now();
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
        this.data_cadastro = LocalDateTime.now();

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

    public void setData_retirada(LocalDateTime data_retirada) {
        this.data_retirada = data_retirada;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public LocalDateTime getData_retirada() {
        return data_retirada;
    }

    public Duration tempo_na_pilha() {
        Duration duration;
        if(data_retirada != null){
            duration = Duration.between(data_cadastro, data_retirada);
        }else {
            duration = Duration.between(data_cadastro, LocalDateTime.now());
        }

        return duration;
    }

    @Override
    public String toString() {
        // alterar impleentação
        String dados_revista = "\n * Título: " + titulo + "\n * Edição: " + numero_edicao + "\n * Publicação: "
                + mes_publicacao + "/" + ano_publicacao + "\n * Data cadastro: " + this.data_cadastro.toLocalDate();
        if (numero_volume > 0) {
            dados_revista += "\n * Volume: " + numero_volume;
        }
        dados_revista += "\n------------------------";
        return dados_revista;
    }

}
