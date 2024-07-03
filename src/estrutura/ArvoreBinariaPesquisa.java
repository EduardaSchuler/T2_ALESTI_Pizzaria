package estrutura;

import gerenciamento.Pedido;

public class ArvoreBinariaPesquisa {
    private Nodo raiz;
    private int tamanho;
    private String caminhoCentral;

    public void adicionar(Pedido pedido) {
        Nodo n = new Nodo(pedido);
        if (raiz == null) {
            raiz = n;
        } else {
            Nodo aux = raiz;
            Nodo paidoAux = null;
            while (aux != null) {
                paidoAux = aux;
                if (pedido.getCodigo() <= aux.getPedido().getCodigo()) {
                    aux = aux.getEsquerda();
                    if (aux == null) {
                        paidoAux.setEsquerda(n);
                    }
                } else {
                    aux = aux.getDireita();
                    if (aux == null) {
                        paidoAux.setDireita(n);
                    }
                }
            }
        }
        tamanho++;
    }
    public int getTamanho() {
        return tamanho;
    }
    public void percorrerEmProfundidade() {
        caminhoCentral = "";
        percorrerEmProfundidade(raiz);
    }
    private void percorrerEmProfundidade(Nodo n) {
        if (n != null) {
            percorrerEmProfundidade(n.getEsquerda());
            caminhoCentral += n.getPedido().getCodigo() + " ";
            percorrerEmProfundidade(n.getDireita());
        }
    }
    public String getCaminhoCentral() {
        return caminhoCentral;
    }
    public String imprimirCodigosArvore() {
        percorrerEmProfundidade();
        return getCaminhoCentral();
    }
//    public Pedido getPedidoMaisDemorado() {
//        return getPedidoMaisDemorado(raiz);
//    }
//
//    private Pedido getPedidoMaisDemorado(Nodo nodo) {
//        if (nodo == null) {
//            return null;
//        }
//        Pedido maisDemorado = nodo.getPedido();
//        Pedido esquerdo = getPedidoMaisDemorado(nodo.getEsquerda());
//        Pedido direito = getPedidoMaisDemorado(nodo.getDireita());
//
//        if (esquerdo != null && esquerdo.getTempoTotalProcessamento() > maisDemorado.getTempoTotalProcessamento()) {
//            maisDemorado = esquerdo;
//        }
//        if (direito != null && direito.getTempoTotalProcessamento() > maisDemorado.getTempoTotalProcessamento()) {
//            maisDemorado = direito;
//        }
//        return maisDemorado;
//    }

}

