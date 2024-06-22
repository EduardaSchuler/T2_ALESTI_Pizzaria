package aplicacao;

import estrutura.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class App {
    private Scanner entrada;
    private FilaPedidosDinamica filaPedidosDinamica;


    public App(){
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("entrada.csv"));
            entrada = new Scanner(streamEntrada);
            entrada.useDelimiter("[;\n\r]");
            PrintStream streamSaida = new PrintStream(new File("saida.csv"), StandardCharsets.UTF_8);
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        filaPedidosDinamica = new FilaPedidosDinamica();
    }



    public void executa(){
        recebePedido();
    }

    private void recebePedido() {
        int codigo;
        String saborPizza;
        int instante;
        int tempoPreparo;
        codigo = entrada.nextInt();
        entrada.nextLine();
        while(entrada.hasNext()){
            saborPizza = entrada.next();
            instante = entrada.nextInt();
            tempoPreparo = entrada.nextInt();
            if (filaPedidosDinamica.pizzaioloDisponivel()) {

            }


            codigo = entrada.nextInt();
            entrada.nextLine();
        }

    }
}
