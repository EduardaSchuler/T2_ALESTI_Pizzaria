package estrutura;

import gerenciamento.Pedido;

// Nesta fila guardaremos todos os objetos que posteriormente serão realocados para a FilaPedidosDinamica
public class FilaAuxiliar {
    private Nodo inicio;
    private Nodo fim;
    private int tamanho;

    public FilaAuxiliar() {
        inicio = null;
        fim = null;
        tamanho = 0;
    }
    // Método para adicionar um pedido na fila auxiliar.
    public void enfileirar(Pedido pedido) {
        Nodo n = new Nodo(pedido);
        if (tamanho == 0) {
            inicio = n;
            fim = n;
        } else {
            fim.setProximo(n);
            fim = n;
        }
        tamanho++;
    }

    // Método para tirar um pedido da fila auxiliar.
    public Pedido desenfileirar() {
        if (tamanho > 0) {
            if (inicio.getProximo() != null) {
                Pedido aux = inicio.getPedido();
                inicio = inicio.getProximo();
                tamanho--;
                return aux;
            }
        }
        return null;
    }
    public boolean filaAuxEstaVazia() {
        return inicio == null;
    }
    public Pedido getInicio(){
        return inicio.getPedido();
    }
}
