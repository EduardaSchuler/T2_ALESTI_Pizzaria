package estrutura;

import gerenciamento.Pedido;

public class ArvoreBinariaPesquisa {
    private Nodo raiz;
    private int tamanho;
    private String caminhoPreOrdem;
    private String caminhoPosOrdem;
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
        caminhoPreOrdem = "";
        caminhoPosOrdem = "";
        caminhoCentral = "";
        percorrerEmProfundidade(raiz);
    }

    private void percorrerEmProfundidade(Nodo n) {
        if (n != null) {
            caminhoPreOrdem += " " + n.getPedido();
            percorrerEmProfundidade(n.getEsquerda());
            caminhoCentral += " " + n.getPedido();
            percorrerEmProfundidade(n.getDireita());
            caminhoPosOrdem += " " + n.getPedido();
        }
    }

    public String getCaminhoPreOrdem() {
        return caminhoPreOrdem;
    }

    public String getCaminhoPosOrdem() {
        return caminhoPosOrdem;
    }

    public String getCaminhoCentral() {
        return caminhoCentral;
    }

    public void imprimirArvore() {
        imprimirArvoreRecursivamente(this.raiz, 0);
    }

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
    public void emOrdem(StringBuilder sb) {
        emOrdemRecursivo(raiz, sb);
    }
    private void emOrdemRecursivo(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            emOrdemRecursivo(nodo.getEsquerda(), sb);
            sb.append(nodo.getPedido().getCodigo()).append(",");
            emOrdemRecursivo(nodo.getDireita(), sb);
        }
    }
}

