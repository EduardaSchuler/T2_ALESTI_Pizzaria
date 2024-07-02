package aplicacao;
import estrutura.*;
import gerenciamento.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private FilaPedidosDinamica filaPedidosDinamica;
    private Pizzaria pizzaria;
    private FilaAuxiliar filaAux;
    private ArvoreBinariaPesquisa abp;
    private PrintStream situacaoFilaSaida; // É o arquivo de saída.

    public App() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("pedidos_pizza_15.csv")); // Este é o arquivo de entrada.
            entrada = new Scanner(streamEntrada);
            situacaoFilaSaida = new PrintStream(new File("situacao_fila.csv"), StandardCharsets.UTF_8); // Este é o arquivo de saída.
            // Precisamos um para árvore também.
        } catch (Exception e) {
            System.out.println(e);
        }
        filaPedidosDinamica = new FilaPedidosDinamica();
        filaAux = new FilaAuxiliar();
        abp = new ArvoreBinariaPesquisa();
        pizzaria = new Pizzaria(abp);
    }

    // Este método atualiza conforme o tempo "t".
    public void executa() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int tempo = 0;
        leitura();
        try {
            situacaoFilaSaida.println("Instante de Tempo t,Fila de pedidos,Em produção,Prontos");
            while (true) {
                String teclado = reader.readLine();
                System.out.println("Pressione <ENTER> para avançar um ciclo."); // teste so com o enter, depois implemento o C
                if (teclado.isEmpty()) {
                    System.out.println(tempo);
                }
                colocaNaFila(tempo);
                processaCiclo(tempo);
                registraSituacao(tempo);
                //registrarSituacaoFila(tempo);
                tempo++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            situacaoFilaSaida.close();
        }
    }

    // A cada pressioamento de ENTER este método deve ser executado. Cada ENTER representa um ciclo.
    // Certo! Método para enfileirar e colocar tudo na lista auxiliar.
    private void leitura() {
        String linha;
        if (entrada.hasNextLine()) { // Isso ocorre para pular a linha do cabeçalho.
            entrada.nextLine();
        }
        while (entrada.hasNextLine()) {
            linha = entrada.nextLine(); // O .trim() ignora espaços.
            String[] valores = linha.split(",");
            if (valores.length == 4) { // Temos índex de 0 a 3.
                try {
                    int codigo = Integer.parseInt(valores[0]); // O código
                    String saborPizza = valores[1]; // O sabor
                    int instante = Integer.parseInt(valores[2]); // O instante "t"
                    int tempoPreparo = Integer.parseInt(valores[3]); // O tempo de preparo
                    Pedido p = new Pedido(codigo, saborPizza, instante, tempoPreparo); // O pedido de fato está sendo criado.
                    filaAux.enfileirar(p);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void colocaNaFila(int tempo) {
        //Passando para a fila principal.
        while (!filaAux.filaAuxEstaVazia() && filaAux.getInicio().getInstante() == tempo) {
            Pedido p = filaAux.desenfileirar(); // Tira da fila auxiliar.
            if (filaPedidosDinamica.getPedidosPendentes() == 0 && pizzaria.getPedidoAtual() == null) {
                pizzaria.setPedidoAtual(p);
            } else {
                filaPedidosDinamica.enfileirar(p); // Coloca na fila dinâmica.
            }
        }
    }
    private void processaCiclo(int tempo) {
        pizzaria.processarPedido();
        if (pizzaria.pizzaioloDisponivel()) {
            if (pizzaria.getPedidoAtual() == null) {
                Pedido proximoPedido = filaPedidosDinamica.desenfileirar();
                if (proximoPedido != null) {
                    pizzaria.adicionarPedido(proximoPedido);
                    // System.out.println("Pedido de " + proximoPedido.getSaborPizza() + " retirado da fila e em produção.");
                }
            }
        }
        // Imprime o estado atual da pizzaria
        System.out.println("Instante de Tempo " + tempo + ", Em produção: " + pizzaria.getPedidoAtual());
    }
    public void registraSituacao(int tempo) {
        String mensagem = tempo + ", ";
        mensagem += filaPedidosDinamica.imprimirFila();
        if (pizzaria.getPedidoAtual() == null) {
            mensagem += " ";
        } else {
            mensagem += "Pedido atual:" + pizzaria.getPedidoAtual().getCodigo();
            if (pizzaria.getPedidosProntos() == null) {
                mensagem += "";
            } else {
                if (abp != null) {
                    mensagem += " Pedidos prontos: " + abp.imprimirCodigosArvore();
                } else {
                    mensagem += "";
                }
            }
        }
        situacaoFilaSaida.println(mensagem);
    }
}


