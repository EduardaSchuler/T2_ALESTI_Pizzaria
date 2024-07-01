package estrutura;

import gerenciamento.Pedido;

public class ArvoreBinariaPesquisa {
    private Nodo raiz;
    private int tamanho;
    private String caminhoCentral; // Precisamos utilizar apenas caminhamento central.

    // Certo!
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

    // Certo!
    public int getTamanho() {
        return tamanho;
    }

    // Precisamos apenas o caminhamento central.
    // Certo!
    public void percorrerEmProfundidade() {
        caminhoCentral = "";
        percorrerEmProfundidade(raiz);
    }

    // Certo!
    // Este método é chamado dentro do percorrerEmProfundaidade().
    private void percorrerEmProfundidade(Nodo n) {
        if (n != null) {
            if(n.getEsquerda()!=null) {
                percorrerEmProfundidade(n.getEsquerda());
            }
            caminhoCentral = caminhoCentral + " " + n.getPedido().getCodigo();
            if(n.getDireita()!=null) {
                percorrerEmProfundidade(n.getDireita());
            }
        }
    }

    // Certo!
    public String getCaminhoCentral() {
        return caminhoCentral;
    }

    // Certo!
    public void imprimirArvore() {
        imprimirArvoreRecursivamente(this.raiz, 0);
    }

    // Certo!
    private void imprimirArvoreRecursivamente(Nodo raiz, int nivel) {
        if (raiz == null) return;
        nivel += 5;
        imprimirArvoreRecursivamente(raiz.getDireita(), nivel);
        System.out.print("\n");
        for (int i = 5; i < nivel; i++) System.out.print(" ");
        System.out.print(raiz.getPedido() + "\n");
        for (int i = 0; i < nivel; i++) System.out.print(" ");
        imprimirArvoreRecursivamente(raiz.getEsquerda(), nivel);
    }
}

