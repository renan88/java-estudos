import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Agenda {
    private List<Contato> contatos;
    private final String nomeArquivo = "contatos.txt";

    public Agenda() {
        contatos = new ArrayList<>();
        carregarContatos();
    }

    public void adicionarContato(Contato contato) {
        contatos.add(contato);
        salvarContatos();
    }

    public void listarContatos() {
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato cadastrado.");
        } else {
            for (int i = 0; i < contatos.size(); i++) {
                System.out.println((i + 1) + ". " + contatos.get(i));
            }
        }
    }

    public Contato buscarPorNome(String nome) {
        for (Contato c : contatos) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public boolean removerContato(String nome) {
        Contato c = buscarPorNome(nome);
        if (c != null) {
            contatos.remove(c);
            salvarContatos();
            return true;
        }
        return false;
    }

    public boolean editarContato(String nome, Contato novoContato) {
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).getNome().equalsIgnoreCase(nome)) {
                contatos.set(i, novoContato);
                salvarContatos();
                return true;
            }
        }
        return false;
    }

    private void salvarContatos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {
            for (Contato c : contatos) {
                writer.write(c.toArquivo());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos: " + e.getMessage());
        }
    }

    private void carregarContatos() {
        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Contato c = Contato.fromArquivo(linha);
                if (c != null) {
                    contatos.add(c);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar contatos: " + e.getMessage());
        }
    }
}