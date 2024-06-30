package gerenciamento;
import estrutura.*;

public class Pizzaria {
    private Pedido pedidoAtual;
    private FilaPedidosDinamica listaPedido;
    private int tempoTotal;
    private ArvoreBinariaPesquisa abp;

    public Pizzaria(){
        this.pedidoAtual = null;
        this.listaPedido = new FilaPedidosDinamica();
        this.tempoTotal = 0;
        this.abp = new ArvoreBinariaPesquisa();
    }

    public void adicionarPedido(Pedido pedido) {
        if (pedidoAtual == null) {
            pedidoAtual = pedido;
        } else {
            listaPedido.enfileirar(pedido);
        }
    }

    public void processarPedido() {
        if (pedidoAtual != null) {
            pedidoAtual.setTempoPreparo(pedidoAtual.getTempoPreparo() - 1);
            tempoTotal++;
            if (pedidoAtual.getTempoPreparo() <= 0) {
                abp.adicionar(pedidoAtual);
                pedidoAtual = null;
            }
        }
    }
    public boolean pizzaioloDisponivel() {
        return pedidoAtual == null;
    }
    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
    public ArvoreBinariaPesquisa getPedidosProntos() {
        return abp;
    }
}

