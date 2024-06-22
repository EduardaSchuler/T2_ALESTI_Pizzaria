package gerenciamento;
import estrutura.*;

public class Pizzaria {
    private Pedido pedidoAtual;
    private FilaPedidosDinamica listaPedido;
    private int tempoAtual;
    private ArvoreBinariaPesquisa abp;
    public Pizzaria(){
        this.pedidoAtual = null;
        this.listaPedido = new FilaPedidosDinamica();
        this.tempoAtual = 0;
        this.abp = new ArvoreBinariaPesquisa();
    }

    public void adicionarPedido(Pedido pedido){

        if (!listaPedido.pizzaioloDisponivel()) {
            listaPedido.enfileirar(pedido);
        }
        else{
            abp.adicionar(pedido);
        }
    }

    public void processarPedido(){
        Pedido pedidoAtual;
        if (listaPedido.pizzaioloDisponivel()){
            pedidoAtual = listaPedido.getPedido();
            listaPedido.desenfileirar();
            abp.adicionar(pedidoAtual);
        }
    }
}
