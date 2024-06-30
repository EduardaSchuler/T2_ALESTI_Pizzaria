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
        listaPedido.enfileirar(pedido);
    }

    public void processarPedido(){
        if (pedidoAtual == null && !listaPedido.estaVazia()) {
            listaPedido.desenfileirar();
            tempoAtual = 0;
        }
        if (pedidoAtual != null) {
            pedidoAtual.setTempoPreparo(pedidoAtual.getTempoPreparo() - 1);
            tempoAtual++;
            if (pedidoAtual.getTempoPreparo() <= 0) {
                abp.adicionar(pedidoAtual);
                pedidoAtual = null;
            }
        }
    }
    public boolean pizzaioloDisponivel(){
        return pedidoAtual == null;
    }
}
