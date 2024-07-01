package estrutura;

import gerenciamento.Pedido;

public class Nodo {
    private Pedido pedido; // É o conteúdo do nodo.
    private Nodo proximo; // É a referência para o próximo, quando o nodo é utilizado em uma fila.
    private Nodo esquerda; // É o nodo à esquerda, quando utilizado em árvore.
    private Nodo direita; // É o nodo à direita, quando utilizado em árvore.
    private Nodo pai; // É o pai do nodo, quando utilizado em árvore.

    // Certo!
    public Nodo(Pedido pedido) {
        this.pedido = pedido;
        this.proximo = null;
        this.esquerda = null;
        this.direita = null;
        this.pai = null;
    }

    // Certo!
    public Pedido getPedido() {
        return pedido;
    }

    // Certo!
    public Nodo getProximo() {
        return proximo;
    }

    // Certo!
    public void setProximo(Nodo proximo) {
        this.proximo = proximo;
    }

    // Certo!
    public Nodo getEsquerda() {
        return esquerda;
    }

    // Certo!
    public void setEsquerda(Nodo esquerda) {
        this.esquerda = esquerda;
    }

    // Certo!
    public Nodo getDireita() {
        return direita;
    }

    // Certo!
    public void setDireita(Nodo direita) {
        this.direita = direita;
    }

    // Certo!
    public Nodo getPai() {
        return pai;
    }

    // Certo!
    public void setPai(Nodo pai) {
        this.pai = pai;
    }
}
