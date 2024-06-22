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


    public App(){
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("pedidos_pizza_1000.csv"));
            entrada = new Scanner(streamEntrada);
            entrada.useDelimiter("[;\r]");
            PrintStream streamSaida = new PrintStream(new File("saida.csv"), StandardCharsets.UTF_8);
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        filaPedidosDinamica = new FilaPedidosDinamica();
        pizzaria = new Pizzaria();
    }



    public void executa(){
        new JanelaPedido();
        recebePedido();
    }

    private void recebePedido() {
        int codigo;
        String saborPizza;
        int instante;
        int tempoPreparo;
        Pedido pedido;
        while(entrada.hasNextLine()){
            codigo = entrada.nextInt();
            saborPizza = entrada.next();
            instante = entrada.nextInt();
            tempoPreparo = entrada.nextInt();
            pedido = new Pedido(codigo, saborPizza, instante, tempoPreparo);
            pizzaria.adicionarPedido(pedido);
            System.out.println(pedido.toString());
            entrada.nextLine();
        }

    }
}
