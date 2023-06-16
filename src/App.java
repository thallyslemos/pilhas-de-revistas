import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void menuPrincipal() {
        limpaTerminal();
        System.out.println("-------- Menu --------\n");
        System.out.println("(1) - Pilha de revistas");
        System.out.println("(2) - Vendas");
        System.out.println("(3) - Limpar terminal");
        System.out.println("(0) - Sair\n");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuRevistas() {
        System.out.println("------- Pilha de Revistas -------\n");
        System.out.println("(1) - Empilhar nova revista");
        System.out.println("(2) - Consultar próxima revista da pilha");
        System.out.println("(3) - Consultar Ultima revista retirada");
        System.out.println("(4) - Tempo médio de permanencia na pilha");
        System.out.println("(5) - Exibir revistas por ano de publicação");
        System.out.println("(6) - Limpar terminal");
        System.out.println("(0) - Voltar ao menu principal\n");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuVenda() {
        limpaTerminal();
        System.out.println("--------- Vendas ---------\n");
        System.out.println("(1) - Listar todas as vendas");
        System.out.println("(2) - Incluir nova venda");
        System.out.println("(3) - Limpar terminal");
        System.out.println("(0) - Voltar ao menu principal\n");
        System.out.print("Escolha uma opção: ");
    }

    public static void menuRealizaVenda() {
        System.out.println("\n-----------------------------------------");
        System.out.println("(1) - Incluir novo item");
        System.out.println("(2) - Excluir item");
        System.out.println("(3) - Finalizar venda\n");
        System.out.print("Escolha uma opção: ");
    }

    public static Integer buscarChavePorValor(Map<Integer, Mercadoria> mapa, Mercadoria valor) {
        return mapa.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(valor))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static int notaCompra(Map<Mercadoria, Integer> lista_compra, Map<Integer, Mercadoria> estoque) {
        double valor_total_compra = 0;
        limpaTerminal();
        System.out.printf("%-7s| %-35s| %-14s| %-11s| %-13s%n", "Código", "Nome", "Preço Unitário", "Quantidade",
                "Valor Total");
        System.out.println(
                "----------------------------------------------------------------------------------------------");
        for (Map.Entry<Mercadoria, Integer> entry : lista_compra.entrySet()) {
            Mercadoria mercadoria = entry.getKey();
            int quantidade = entry.getValue();
            double preco_unitario = mercadoria.getPreco();
            double valor_total_item = quantidade * preco_unitario;
            valor_total_compra += valor_total_item;
            Integer id = buscarChavePorValor(estoque, mercadoria);

            System.out.printf("%-7d| %-35s| R$ %-11.2f| %-11d| R$ %-11.2f%n",
                    id, mercadoria.getNome(), preco_unitario, quantidade, valor_total_item);
        }
        // Adcionar itens
        System.out.println("\nValor Total: " + String.format("R$ %.2f", valor_total_compra)); // Adcionar valor total

        int quantidade_revistas = (int) Math.floor(valor_total_compra / 100);
        System.out.println("\nRevistas de brinde: " + String.format(" %-3s", quantidade_revistas)); // Adcionar valor
        return quantidade_revistas;
    }

    public static void revistas_por_ano(Stack<Revista> pilha_revistas, int ano) {
        // Stack<Revista> revista_mesmo_ano = new Stack<>();
        Stack<Revista> pilha_temporaria = new Stack<>();
        pilha_temporaria.addAll(pilha_revistas);
        System.out.println("\nRevistas do ano  " + ano + ": ");

        while (!pilha_temporaria.isEmpty()) {
            Revista revista = pilha_temporaria.pop();
            if (revista.getAno_publicacao() == ano) {
                System.out.println(revista.toString());
            }
        }
        // while (!pilha_revistas.isEmpty()) {
        // Revista revista = pilha_revistas.pop();
        // if (revista.getAno_publicacao() == ano) {
        // revista_mesmo_ano.push(revista);
        // } else {
        // pilhaTemporaria.push(revista);
        // }
        // }

        // while (!pilhaTemporaria.isEmpty()) {
        // pilha_revistas.push(pilhaTemporaria.pop());
        // }

        // return revistasComMesmaIdade;
    }

    public static void cadastraRevista(Scanner scanner, Stack<Revista> pilha_revistas) {
        System.out.println("Digite o título: ");
        String titulo = scanner.next();
        System.out.println("Digite número de edição: ");
        int edicao = scanner.nextInt();
        System.out.println("Digite ano de publicação: ");
        int ano = scanner.nextInt();
        System.out.println("Digite o mês de publicação: ");
        int mes = scanner.nextInt();
        System.out.println("Digite o número de volume (caso não exista, digite 0): ");
        int volume = scanner.nextInt();

        // Inserir código que cadastra a revista
        if (volume == 0) {
            pilha_revistas.add(new Revista(titulo, edicao, mes, ano));
        } else {
            pilha_revistas.add(new Revista(titulo, edicao, mes, ano, volume));
        }
    }

    /*
     * Metodo de remoção de revistas da pilha
     * guarda a data e horário da remoção da revista
     * exibe o tempo
     * armazena os dados das revistas retiradas
     */
    public static void removeRevista(Stack<Revista> pilha_revistas, Stack<Revista> historico_revistas, int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            if (pilha_revistas.size() > 0) {
                Revista revista_retirada = pilha_revistas.pop();
                revista_retirada.setData_retirada(LocalDateTime.now());

                historico_revistas.add(revista_retirada);
                System.out.println(revista_retirada.toString());
            } else {
                System.out.println("Não há mais revistas de brinde ddisponíveis...");
                break;
            }
        }
    }

    // meto recebe uma revista que foi retirada da pilha e retorna o tempo total que
    // ela permaneceu na pilha
    public static void tempo_na_pilha(Revista revista) {
        Duration duration = revista.tempo_na_pilha();
        long diffInSeconds = duration.getSeconds();
        long hours = diffInSeconds / 3600;
        long minutes = (diffInSeconds % 3600) / 60;
        long seconds = diffInSeconds % 60;

        // Exibe a diferença de tempo no formato hh:mm:ss
        String diffTime = String.format("%03d:%02d:%02d", hours, minutes, seconds);
        System.out.println("Data de entrada: " + revista.getData_cadastro().toLocalDate() + " | Data de saída: "
                + revista.getData_retirada().toLocalDate());
        System.out.println("Diferença de tempo: " + diffTime);
    }

    public static void limpaTerminal() {
        try {
            // Verifica qual é o sistema operacional
            String os = System.getProperty("os.name").toLowerCase();

            // Limpa o terminal de acordo com o sistema operacional
            if (os.contains("win")) {
                // Comando para limpar o terminal no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para limpar o terminal em sistemas Unix-like (Linux, macOS)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            // Tratar qualquer exceção que possa ocorrer
            e.printStackTrace();
        }
    }

    public static void cria_estoque(Map<Integer, Mercadoria> estoque) {
        estoque.put(1, new Mercadoria("Açúcar Refinado 1Kg", 2.15));
        estoque.put(2, new Mercadoria("Detergente Líquido Incolor 500ml", 1.39));
        estoque.put(3, new Mercadoria("Nuggets Tradicional 300g", 5.98));
        estoque.put(4, new Mercadoria("Água Sanitária 2L", 3.98));
        estoque.put(5, new Mercadoria("Extrato de Tomate 340g", 3.74));
        estoque.put(6, new Mercadoria("Óleo de Soja 900ml", 3.10));
        estoque.put(7, new Mercadoria("Amaciante 2L", 11.58));
        estoque.put(8, new Mercadoria("Farinha de Trigo 1Kg", 2.28));
        estoque.put(9, new Mercadoria("Pão de Forma Tradicional 500g", 5.85));
        estoque.put(10, new Mercadoria("Arroz Branco 5Kg", 13.98));
        estoque.put(11, new Mercadoria("Feijão 1Kg", 3.28));
        estoque.put(12, new Mercadoria("Papel Higiênico 30m 4un", 6.45));
        estoque.put(13, new Mercadoria("Azeite Tipo Extravirgem 500ml", 17.90));
        estoque.put(14, new Mercadoria("Gim 750ml", 89.90));
        estoque.put(15, new Mercadoria("Refrigerante 1,5L", 5.49));
        estoque.put(16, new Mercadoria("Barra de Chocolate 135g", 5.99));
        estoque.put(17, new Mercadoria("Hambúrguer de Frango e Boi 672g", 12.75));
        estoque.put(18, new Mercadoria("Requeijão Cremoso Tradicional 200g", 2.99));
        estoque.put(19, new Mercadoria("Bolacha Recheada de Chocolate 136g", 1.88));
        estoque.put(20, new Mercadoria("Iogurte de Morango 40g 8un", 3.99));
        estoque.put(21, new Mercadoria("Sabão em Pó 1Kg", 7.99));
        estoque.put(22, new Mercadoria("Bombons Sortidos 300g", 7.98));
        estoque.put(23, new Mercadoria("Lã de Aço 60g 8un", 1.86));
        estoque.put(24, new Mercadoria("Sabonete 90g", 1.22));
        estoque.put(25, new Mercadoria("Café em Pó Tradicional 500g", 11.88));
        estoque.put(26, new Mercadoria("Leite Condensado 395g", 3.98));
        estoque.put(27, new Mercadoria("Sal Refinado 1Kg", 2.19));
        estoque.put(28, new Mercadoria("Cerveja 350ml", 2.39));
        estoque.put(29, new Mercadoria("Leite UHT Integral 1L", 2.18));
        estoque.put(30, new Mercadoria("Sorvete Napolitano 1,5L", 17.50));
        estoque.put(31, new Mercadoria("Cerveja 500ml", 12.70));
        estoque.put(32, new Mercadoria("Macarrão com Ovos Parafuso 500g", 2.65));
        estoque.put(33, new Mercadoria("Suco Pronto para Consumo Néctar 1L", 4.92));
        estoque.put(34, new Mercadoria("Creme de Avelã 350g", 17.90));
        estoque.put(35, new Mercadoria("Macarrão Espaguete 500g", 6.49));
        estoque.put(36, new Mercadoria("Uísque 12 Anos 750ml", 117.90));
        estoque.put(37, new Mercadoria("Creme de Leite 200g", 2.49));
        estoque.put(38, new Mercadoria("Maionese 500g", 4.98));
        estoque.put(39, new Mercadoria("Vodca 998ml", 29.90));
    }

    public static void inicializaPilhaRevistas(Stack<Revista> pilha_revistas) {
        pilha_revistas.push(new Revista("IEEE Computer Magazine", 1, 1, 2022, 1));
        pilha_revistas.push(new Revista("ACM Transactions on Computer Systems", 2, 3, 2022, 1));
        pilha_revistas.push(new Revista("Journal of Computer Science and Technology", 3, 5, 2022, 2));
        pilha_revistas.push(new Revista("IEEE Transactions on Computers", 4, 7, 2022, 2));
        pilha_revistas.push(new Revista("Communications of the ACM", 5, 9, 2022, 3));
        // pilha_revistas.push(new Revista("IEEE Software Magazine", 6, 11, 2022, 3));
        // pilha_revistas.push(new Revista("Journal of Parallel and Distributed
        // Computing", 7, 2, 2023, 4));
        // pilha_revistas.push(new Revista("ACM Computing Surveys", 8, 4, 2023, 4));
        // pilha_revistas.push(new Revista("Journal of Computer Vision and Image
        // Understanding", 9, 6, 2023, 5));
        // pilha_revistas.push(new Revista("Information and Computation", 10, 8, 2023,
        // 5));
    }

    public static void inserir_produto(Integer codigo_produto, Integer quantidade, Map<Integer, Mercadoria> estoque,
            Map<Mercadoria, Integer> lista_compra) {
        lista_compra.put(estoque.get(codigo_produto), quantidade);

    }

    public static void listarProdutos(Map<Integer, Mercadoria> estoque) {
        System.out.println("Lista de produtos: ");

        for (Map.Entry<Integer, Mercadoria> entry : estoque.entrySet()) {
            Integer codigo = entry.getKey();
            Mercadoria mercadoria = entry.getValue();

            System.out.printf("Código: %d\n", codigo);
            System.out.println(mercadoria.toString());
            System.out.println("--------------------------");
        }
    }

    // recebe o historico de rebistas reitradas da pilha e exibe o tempo médio de
    // permanenciia
    // das revistas na pilha
    public static void tempo_medio_pilha(Stack<Revista> pilha_revista, Stack<Revista> revistas_retiradas) {
        Duration soma_tempo = Duration.ofSeconds(0);

        if (pilha_revista.size() > 0 || revistas_retiradas.size() > 0) {
            for (Revista r : pilha_revista) {
                soma_tempo = soma_tempo.plus(r.tempo_na_pilha());
            }
            for (Revista r : revistas_retiradas) {
                soma_tempo = soma_tempo.plus(r.tempo_na_pilha());
            }

            int numero_total_revistas = pilha_revista.size() + revistas_retiradas.size();
            System.out.println(numero_total_revistas);
            long diffInSeconds = soma_tempo.toSeconds() / numero_total_revistas;
            long hours = diffInSeconds / 3600;
            long minutes = (diffInSeconds % 3600) / 60;
            long seconds = diffInSeconds % 60;

            // Exibe a diferença de tempo no formato hh:mm:ss
            String diffTime = String.format("%03d:%02d:%02d", hours, minutes, seconds);
            System.out.println("\nTempo médio das revistas na pilha: " + diffTime);
        }else {
            System.out.println("Sem dados para exibir...");
        }

    }

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        // Lista para manipulação dos itens da compra
        Map<Mercadoria, Integer> lista_compra = new HashMap<>();
        // Implementação de pilha de revistas com arrayDeque
        Stack<Revista> pilha_revistas = new Stack<>();
        Stack<Revista> historico_revistas = new Stack<>();
        Map<Integer, Mercadoria> estoque = new HashMap<>();

        cria_estoque(estoque);
        inicializaPilhaRevistas(pilha_revistas);

        int menu = 1;

        while (menu != 0) {
            menuPrincipal();
            menu = scanner.nextInt();

            switch (menu) {
                case 1:
                    int segundo_menu = 1;
                    while (segundo_menu != 0) {
                        menuRevistas();

                        segundo_menu = scanner.nextInt();

                        switch (segundo_menu) {
                            case 1:
                                // cadastro de revista
                                cadastraRevista(scanner, pilha_revistas);
                                break;
                            case 2:
                                // exibe ultima revista da pilha
                                System.out.println("\nÚltima revista empilhada: ");
                                if (pilha_revistas.size() > 0) {
                                    System.out.println(pilha_revistas.peek().toString());
                                } else {
                                    System.out.println("Não há revistas na pilha...");
                                }
                                System.out.println("-----------------------");
                                break;
                            case 3:
                                // exibe ultima revista retirada da pilha
                                System.out.println("\nÚltima revista desempilhada: ");
                                if (historico_revistas.size() > 0) {
                                    System.out.println(historico_revistas.peek().toString());
                                } else {
                                    System.out.println("Não há revistas no histórico...");
                                }
                                System.out.println("-----------------------");
                                break;
                            case 4:
                                tempo_medio_pilha(historico_revistas, pilha_revistas);
                                break;
                            case 5:
                                System.out.println("Digite um dos anos disponiveis: ");
                                for (Revista r : pilha_revistas) {
                                    System.out.println(r.getAno_publicacao());
                                }
                                ;
                                int ano = scanner.nextInt();
                                revistas_por_ano(pilha_revistas, ano);
                                ;
                                break;
                            case 6:
                                // limpa terminal
                                limpaTerminal();
                                break;
                            case 0:
                                break;
                        }
                    }
                    break;

                case 2:
                    int terceiro_menu = 1;

                    while (terceiro_menu != 0) {
                        // limpaTerminal();
                        menuVenda();
                        terceiro_menu = scanner.nextInt();

                        switch (terceiro_menu) {
                            case 1:
                                break;
                            case 2:
                                int quarto_menu = 1;

                                while (quarto_menu != 0) {
                                    limpaTerminal();
                                    System.out.println("Venda");
                                    if (lista_compra.size() > 0) {
                                        notaCompra(lista_compra, estoque); // adcionar o número da venda
                                    } else {
                                        System.out.println("Inicie a venda: ");
                                    }
                                    menuRealizaVenda();
                                    quarto_menu = scanner.nextInt();

                                    switch (quarto_menu) {
                                        case 1:
                                            listarProdutos(estoque);

                                            System.out.println("Digite o código do produto a ser adicionado: ");
                                            int id_produto = scanner.nextInt();
                                            System.out.println("Digite a quantidade: ");
                                            int qnt_produto = scanner.nextInt();
                                            inserir_produto(id_produto, qnt_produto, estoque, lista_compra);
                                            scanner.nextLine();

                                            break;
                                        case 2:
                                            System.out.println("Exclusão de produtos da compra: \n");
                                            limpaTerminal();
                                            notaCompra(lista_compra, estoque);
                                            System.out.println("Digite o código do produto na lista a ser excluido: ");
                                            int n_produto = scanner.nextInt();
                                            Mercadoria produto = estoque.get(n_produto);
                                            System.out.println(lista_compra.remove(produto));
                                            // for(Mercadoria m : lista_compra.keySet()){
                                            // if(m.getNome() == produto.getNome()) {

                                            // }
                                            // }
                                            scanner.nextLine();
                                            break;
                                        case 3:
                                            limpaTerminal();
                                            if (lista_compra.size() > 0) {
                                                int revistas = notaCompra(lista_compra, estoque);
                                                removeRevista(pilha_revistas, historico_revistas, revistas);
                                            } else {
                                                System.out.println("Não há mercadorias para serem processadas...");
                                            }
                                            System.out.println("Tecle (0) para sair: ");
                                            quarto_menu = scanner.nextInt();
                                            break;
                                        default:
                                            break;
                                    }
                                    ;
                                }

                                break;
                            case 3:
                                limpaTerminal();
                                break;
                            case 0:
                                // Voltar para o menu
                                break;
                        }
                    }
                    break;

                case 3:
                    limpaTerminal();
                    break;
                case 0:
                    System.out.println("Programa encerrado...");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, escolha uma opção válida.");
                    break;
            }
        }
    }
}