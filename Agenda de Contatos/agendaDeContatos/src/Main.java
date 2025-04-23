import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n--- AGENDA DE CONTATOS ---");
            System.out.println("1. Adicionar contato");
            System.out.println("2. Listar contatos");
            System.out.println("3. Buscar contato");
            System.out.println("4. Editar contato");
            System.out.println("5. Remover contato");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();
                    agenda.adicionarContato(new Contato(nome, tel, email));
                    System.out.println("Contato adicionado!");
                    break;

                case 2:
                    agenda.listarContatos();
                    break;

                case 3:
                    System.out.print("Nome do contato: ");
                    String busca = sc.nextLine();
                    Contato encontrado = agenda.buscarPorNome(busca);
                    if (encontrado != null) {
                        System.out.println(encontrado);
                    } else {
                        System.out.println("Contato não encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Nome do contato a editar: ");
                    String nomeEditar = sc.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = sc.nextLine();
                    System.out.print("Novo telefone: ");
                    String novoTel = sc.nextLine();
                    System.out.print("Novo email: ");
                    String novoEmail = sc.nextLine();
                    boolean editado = agenda.editarContato(nomeEditar, new Contato(novoNome, novoTel, novoEmail));
                    System.out.println(editado ? "Contato editado!" : "Contato não encontrado.");
                    break;

                case 5:
                    System.out.print("Nome do contato a remover: ");
                    String nomeRemover = sc.nextLine();
                    boolean removido = agenda.removerContato(nomeRemover);
                    System.out.println(removido ? "Contato removido!" : "Contato não encontrado.");
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}