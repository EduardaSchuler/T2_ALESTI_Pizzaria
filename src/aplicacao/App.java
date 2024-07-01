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
        pizzaria = new Pizzaria();
    }

    // Este método atualiza conforme o tempo "t".
    public void executa() {
        Scanner teclado = new Scanner(System.in);
        int tempo = 0;
        try {
            if (entrada.hasNextLine()) { // Isso ocorre para pular a linha do cabeçalho.
                entrada.nextLine();
            }
            situacaoFilaSaida.println("Instante de Tempo t,Fila de pedidos,Em produção,Prontos");
            leitura();
            while (entrada.hasNextLine() || !filaPedidosDinamica.estaVazia() || !pizzaria.pizzaioloDisponivel()) {
                System.out.println("Pressione <ENTER> para avançar um ciclo."); // teste so com o enter, depois implemento o C
                teclado.nextLine();
                processaCiclo(tempo);
                registrarSituacaoFila(tempo);
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


    private void processaCiclo(int tempo) {
        //Passando para a fila principal.
        while(!filaAux.filaAuxEstaVazia() && filaAux.getInicio().getInstante() == tempo){
            Pedido p = filaAux.desenfileirar(); // Tira da fila auxiliar.
            filaPedidosDinamica.enfileirar(p); // Coloca na fila dinâmica.
        }

        pizzaria.processarPedido();
        if (pizzaria.pizzaioloDisponivel() && !filaPedidosDinamica.estaVazia()) {
            Pedido proximoPedido = filaPedidosDinamica.desenfileirar();
            if (proximoPedido != null) {
                pizzaria.adicionarPedido(proximoPedido);
                //teste
                System.out.println("Pedido de " + proximoPedido.getSaborPizza() + " retirado da fila e em produção.");
            }
        }
    }


    private void registrarSituacaoFila(int instante) {
        StringBuilder fila = new StringBuilder();
        Nodo nodoAtual = filaPedidosDinamica.getInicio();
        System.out.println(filaPedidosDinamica.getInicio()); //Não esta adicionando a fila, o getInicio retorna null no teste
        while (nodoAtual != null) {
            Pedido pedido = nodoAtual.getPedido();
            fila.append(pedido.getCodigo()).append(",");
            nodoAtual = nodoAtual.getProximo();
        }
        if (fila.length() > 0) fila.setLength(fila.length() - 1);
        Pedido emProducao = pizzaria.getPedidoAtual();
        StringBuilder prontos = new StringBuilder();
        // pizzaria.getPedidosProntos().emOrdem(prontos);
        // falta implementar algo para ver o que esta sendo produzido
        System.out.println(instante + fila.toString());
        if (prontos.length() > 0) prontos.setLength(prontos.length() - 1);
        // situacaoFilaSaida.printf("%d,%s,%s,%s%n", instante, fila.toString(), prontos.toString());
    }
}



