package estrutura;

import gerenciamento.Pedido;

public class FilaPedidosDinamica {
    private Nodo inicio;
    private Nodo fim;
    private int pedidosPendentes;

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

    public void desenfileirar() {
        if (pedidosPendentes > 0) {
            if (inicio.getProximo() != null) {
                inicio = inicio.getProximo();
                pedidosPendentes--;
            }
        }
    }

    public int getPedidosPendentes() {
        return pedidosPendentes;
    }

    public void imprimirFila(int instante) {
        Nodo n = this.inicio;
        while (n != null) {
            System.out.println("Instante de Tempo t " + instante + " Fila de pedidos " + n.getPedido().getCodigo());
            n = n.getProximo();
        }
    }
    public boolean estaVazia() {
        return inicio == null;
    }
}

