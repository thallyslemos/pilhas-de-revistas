import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Stack;

public class App {

    public static void menuPrincipal() {
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
        System.out.println("--------- Vendas ---------\n");
        System.out.println("(1) - Listar todas as vendas");
        System.out.println("(2) - Incluir nova venda");
        System.out.println("(3) - Limpar terminal");
        System.out.println("(0) - Voltar ao menu principal\n");
        System.out.print("Escolha uma opção: ");
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
    public static void removeRevista(Stack<Revista> pilha_revistas, Stack<Revista> historico_revistas) {
        Revista revista_retirada = pilha_revistas.pop();
        revista_retirada.setData_retirada(LocalDateTime.now());

        historico_revistas.add(revista_retirada);
        System.out.println("Revista removida da pilha: " + revista_retirada.toString());
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

    // recebe o historico de rebistas reitradas da pilha e exibe o tempo médio de
    // permanenciia
    // das revistas na pilha
    public static void tempo_medio_pilha(Stack<Revista> pilha_revista) {
        if (pilha_revista.size() > 0) {
            Duration soma_tempo = Duration.ofSeconds(0);
            for (Revista r : pilha_revista) {
                soma_tempo = soma_tempo.plus(r.tempo_na_pilha());
            }

            long diffInSeconds = soma_tempo.toSeconds() / pilha_revista.size();
            long hours = diffInSeconds / 3600;
            long minutes = (diffInSeconds % 3600) / 60;
            long seconds = diffInSeconds % 60;

            // Exibe a diferença de tempo no formato hh:mm:ss
            String diffTime = String.format("%03d:%02d:%02d", hours, minutes, seconds);
            System.out.println("\nTempo médio das revistas na pilha: " + diffTime);
        } else {
            System.out.println("Nenhuma revista foi retirada da pilha!");
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Lista para manipulação dos itens da compra
        // List<Mercadoria> lista_compra = new ArrayList<>();
        // Implementação de pilha de revistas com arrayDeque
        Stack<Revista> pilha_revistas = new Stack<>();

        Stack<Revista> historico_revistas = new Stack<>();

        int menu = 1;

        while (menu != 0) {
            // System.out.print("\033[H\033[2J");
            menuPrincipal();
            menu = scanner.nextInt();

            switch (menu) {

                case 1:
                    int segundo_menu = 1;

                    while (segundo_menu != 0) {
                        // System.out.print("\033[H\033[2J");
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
                                tempo_medio_pilha(historico_revistas);
                                ;
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
                                System.out.print("\033[H\033[2J");
                                break;
                            case 0:
                                break;
                        }
                    }
                    break;

                case 2:
                    int terceiro_menu = 1;

                    while (terceiro_menu != 0) {
                        // System.out.print("\033[H\033[2J");
                        menuVenda();
                        terceiro_menu = scanner.nextInt();

                        switch (terceiro_menu) {
                            case 1:
                                System.out.println("Listar vendas");
                                break;
                            case 2:
                                int quarto_menu = 1;
                                while (quarto_menu != 0) {
                                    // System.out.print("\033[H\033[2J");
                                    // adcionar o número da venda
                                    System.out.println("Nº da venda: ");
                                    System.out.println("-----------------------------------------\n");
                                    System.out.println("Itens: ");
                                    // Adcionar itens
                                    System.out.println("Valor Total: "); // Adcionar valor total
                                    System.out.println("\n-----------------------------------------");
                                    System.out.println("(1) - Incluir novo item");
                                    System.out.println("(2) - Excluir item");
                                    System.out.println("(0) - Finalizar venda\n");

                                    System.out.print("Escolha uma opção: ");
                                    quarto_menu = scanner.nextInt();

                                    switch (quarto_menu) {
                                        case 1:
                                            break;
                                        case 3:
                                            System.out.print("\033[H\033[2J");
                                            break;
                                        case 0:
                                            break;
                                    }
                                    ;
                                }

                                break;
                            case 3:
                                System.out.print("\033[H\033[2J");
                                break;
                            case 0:
                                // Voltar para o menu
                                break;
                        }
                    }
                    break;

                case 3:
                    System.out.print("\033[H\033[2J");
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