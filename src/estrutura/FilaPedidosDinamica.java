package estrutura;

import gerenciamento.Pedido;

public class FilaPedidosDinamica {
    private Nodo inicio;
    private Nodo fim;
    private int pedidosPendentes; // É o tamanho!

    public FilaPedidosDinamica() {
        pedidosPendentes = 0;
        inicio = null;
        fim = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public Pedido getPedido() {
        return inicio.getPedido();
    }

    // Certo!
    public void enfileirar(Pedido pedido) {
        Nodo n = new Nodo(pedido);
        if (pedidosPendentes == 0) {
            inicio = n;
            fim = n;
        } else {
            fim.setProximo(n);
            fim = n;
        }
        pedidosPendentes++;
    }
    // O pedido que foi desenfileirado não precisa ser retornado? Talvez para enviar o pedido para produção.
    // Certo!
    public Pedido desenfileirar() {
        Pedido antigoInicio = null;
        if (pedidosPendentes > 0) {
                antigoInicio = inicio.getPedido();
                inicio = inicio.getProximo();
                pedidosPendentes--;
                return antigoInicio;
            }
        return antigoInicio;
    }
    // Certo! É para pegar o tamanho.
    public int getPedidosPendentes() {
        return pedidosPendentes;
    }


    // Imprime a fila em determinado instante. Ou seja a cada ENTER terei um instante diferente, logo a fila muda.
    // Certo!
    public String imprimirFila() {
        StringBuilder sb = new StringBuilder();
        Nodo aux = inicio;
        while(aux!=null) {
            sb.append(aux.getPedido().getCodigo()).append(",");
            aux = aux.getProximo();
        }
        return sb.toString();
    }

    // Certo!
    public boolean estaVazia() {
        return inicio == null;
    }
    
}

