import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
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
        System.out.println("(1) - Listar a pilha de revistas");
        System.out.println("(2) - Incluir nova revista");
        System.out.println("(3) - Limpar terminal");
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

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        // Lista para manipulação dos itens da compra
        List<Mercadoria> lista_compra = new ArrayList<>();
        // Implementação de pilha de revistas com arrayDeque
        Stack<Revista> pilha_revistas = new Stack<>();
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
                                System.out.println("Impressao de revistas");
                                for (Revista r : pilha_revistas) {
                                    System.out.println(r.toString());
                                }
                                break;
                            case 2:
                                // System.out.print("\033[H\033[2J");
                                cadastraRevista(scanner, pilha_revistas);
                                break;
                            case 3:
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