public class FilaPedidosDinamica {
    class Nodo {
        Pedido pedido;
        Nodo proximo;
        public Nodo(Pedido pedido){
            this.pedido = pedido;
        }
    }
    private Nodo inicio;
    private Nodo fim;
    private int pedidosPendentes;
    public FilaPedidosDinamica(){
        pedidosPendentes = 0;
        inicio = null;
        fim = null;
    }

    public void enfileirar(Pedido pedido){
        Nodo n = new Nodo(pedido);
        if(pedidosPendentes == 0){
            inicio = n;
            fim = n;
        } else {
            fim.proximo = n;
            fim = n;
        }
        pedidosPendentes++;
    }

    public void desenfileirar(){
        Pedido pedido = null;

        if(pedidosPendentes > 0){
            pedido = inicio.pedido;
            if(inicio.proximo != null){
                inicio = inicio.proximo;
                pedidosPendentes--;
            }
        }
    }
}
