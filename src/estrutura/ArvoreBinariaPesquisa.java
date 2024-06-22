package estrutura;

import gerenciamento.Pedido;

public class ArvoreBinariaPesquisa {
    class Nodo{
        Pedido pedido;
        Nodo esquerda;
        Nodo direita;
        Nodo pai;
        public Nodo(Pedido pedido){
            this.pedido = pedido;
        }
    }
    private Nodo raiz;
    private int tamanho;
    private String caminhoPreOrdem;
    private String caminhoPosOrdem;
    private String caminhoCentral;

    public void adicionar(Pedido pedido){
        Nodo n = new Nodo(pedido);
        if(raiz == null){
            raiz = n;
        }else{
            Nodo aux = raiz;
            Nodo paidoAux = null;
            while(aux != null){
                paidoAux = aux;
                if(pedido.getCodigo() <= aux.pedido.getCodigo()){
                    aux = aux.esquerda;
                    if (aux == null){
                        paidoAux.direita = n;
                    }
                }
            }
        }
        tamanho++;
    }
    public int getTamanho(){
        return tamanho;
    }
    public void percorrerEmProfundidade(){
        caminhoPreOrdem = "";
        caminhoPosOrdem = "";
        caminhoCentral = "";
        percorrerEmProfundidade(raiz);
    }
    public void percorrerEmProfundidade(Nodo n){
        caminhoPreOrdem = caminhoPreOrdem + " " + n.pedido;
        if (n.esquerda == null){
            percorrerEmProfundidade(n.esquerda);
        }
        caminhoCentral = caminhoCentral + " " + n.pedido;
        if (n.direita == null){
            percorrerEmProfundidade(n.direita);
        }
        caminhoPosOrdem = caminhoPosOrdem + " " + n.pedido;
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
    public void imprimirArvore(){
        imprimirArvoreRecursivamente(this.raiz, 0);
    }

    private void imprimirArvoreRecursivamente(Nodo raiz, int nivel) {
        if (raiz == null) return;
        nivel+=5;
        imprimirArvoreRecursivamente(raiz.direita, nivel);
        System.out.print("\n");
        for (int i = 5; i < nivel; i++) System.out.print(" ");
        System.out.print(raiz.pedido + "\n");
        for (int i = 0; i < nivel; i++) System.out.print(" ");
        imprimirArvoreRecursivamente(raiz.esquerda, nivel);
    }
}
