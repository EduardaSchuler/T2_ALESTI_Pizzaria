package aplicacao;

import estrutura.*;
import gerenciamento.Pedido;
import gerenciamento.Pizzaria;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private FilaPedidosDinamica filaPedidosDinamica;
    private Pizzaria pizzaria;
    private PrintStream situacaoFilaSaida;

    public App() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("pedidos_pizza_15.csv"));
            entrada = new Scanner(streamEntrada);
            situacaoFilaSaida = new PrintStream(new File("situacao_fila.csv"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }
        filaPedidosDinamica = new FilaPedidosDinamica();
        pizzaria = new Pizzaria();
    }
    public void executa() {
        Scanner teclado = new Scanner(System.in);
        int tempo = 0;
        try {
            if (entrada.hasNextLine()) {
                entrada.nextLine();
            }
            situacaoFilaSaida.println("Instante de Tempo t,Fila de pedidos,Em produção,Prontos");
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
    private void processaCiclo(int tempo) {
        String linha;
        while (entrada.hasNextLine()) {
            linha = entrada.nextLine().trim();
            if (linha.isEmpty()) {
                continue;
            }
            String[] valores = linha.split(",");
            if (valores.length == 4) {
                try {
                    int codigo = Integer.parseInt(valores[0].trim());
                    String saborPizza = valores[1].trim();
                    int instante = Integer.parseInt(valores[2].trim());
                    int tempoPreparo = Integer.parseInt(valores[3].trim());
                    if (instante <= tempo) {
                        Pedido pedido = new Pedido(codigo, saborPizza, instante, tempoPreparo);
                        //funciona
                        System.out.println("Pedido recebido: " + pedido);
                        if (pizzaria.pizzaioloDisponivel()) {
                            pizzaria.adicionarPedido(pedido);
                            System.out.println("Pedido de " + saborPizza + " em produção.");
                        } else {
                            filaPedidosDinamica.enfileirar(pedido);
                            System.out.println("Pedido de " + saborPizza + " adicionado à fila de espera.");
                        }
                    } else {
                        entrada = new Scanner(new BufferedReader(new FileReader("pedidos_pizza_15.csv")));
                        entrada.nextLine();
                        for (int i = 0; i <= tempo; i++) {
                            if (entrada.hasNextLine()) {
                                entrada.nextLine();
                            }
                        }
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Erro de formato na entrada.");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
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
        //nao esta adicionando a fila, o getInicio retorna null no teste
        System.out.println(filaPedidosDinamica.getInicio());
        while (nodoAtual != null) {
            Pedido pedido = nodoAtual.getPedido();
            fila.append(pedido.getCodigo()).append(",");
            nodoAtual = nodoAtual.getProximo();
        }
        if (fila.length() > 0) fila.setLength(fila.length() - 1);
        Pedido emProducao = pizzaria.getPedidoAtual();
        StringBuilder prontos = new StringBuilder();
        pizzaria.getPedidosProntos().emOrdem(prontos);
        // falta implementar algo para ver o que esta sendo produzido
        System.out.println(instante + fila.toString());
        if (prontos.length() > 0) prontos.setLength(prontos.length() - 1);
        situacaoFilaSaida.printf("%d,%s,%s,%s%n", instante, fila.toString(), prontos.toString());
    }
}



