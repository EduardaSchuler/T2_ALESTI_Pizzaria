package aplicacao;

import estrutura.*;
import gerenciamento.Pedido;
import gerenciamento.Pizzaria;
import janela.JanelaPedido;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private FilaPedidosDinamica filaPedidosDinamica;
    private Pizzaria pizzaria;
    private PrintStream situacaoFilaSaida;
    private PrintStream caminhamentoCentralSaida;

    public App(){
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("pedidos_pizza_15.csv"));
            entrada = new Scanner(streamEntrada);
            entrada.useDelimiter(",");
            situacaoFilaSaida = new PrintStream(new File("situacao_fila.csv"), StandardCharsets.UTF_8);
            caminhamentoCentralSaida = new PrintStream(new File("caminhamento_central.csv"), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println(e);
        }
        filaPedidosDinamica = new FilaPedidosDinamica();
        pizzaria = new Pizzaria();
    }

    public void executa(){
        recebePedido();
    }
    private void recebePedido() {
        int codigo;
        String saborPizza;
        int instante;
        int tempoPreparo;
        Pedido pedido;
        if (entrada.hasNextLine()) {
            entrada.nextLine();
        }
        try {
            while (entrada.hasNextLine()) {
                String linha = entrada.nextLine().trim();
                if (linha.isEmpty()) {
                    continue;
                }
                String[] valores = linha.split(",");
                if (valores.length == 4) {
                    codigo = Integer.parseInt(valores[0].trim());
                    saborPizza = valores[1].trim();
                    instante = Integer.parseInt(valores[2].trim());
                    tempoPreparo = Integer.parseInt(valores[3].trim());
                    pedido = new Pedido(codigo, saborPizza, instante, tempoPreparo);
                    pizzaria.adicionarPedido(pedido);
                } else {
                    System.out.println("Formato inválido na linha: " + linha);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Erro de formato na entrada.");
            e.printStackTrace();
        } finally {
        situacaoFilaSaida.println("implementar");
        caminhamentoCentralSaida.println("implementar");

    }
}
}

