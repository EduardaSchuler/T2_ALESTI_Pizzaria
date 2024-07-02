package gerenciamento;
import estrutura.*;

public class Pizzaria {
    private Pedido pedidoAtual; // É o pedido que está sendo feito.
    private FilaPedidosDinamica listaPedido; // Possui uma fila dinâmica com os pedidos que estão em espera.
    private int tempoTotal; // É o tempo total de processamento. É o "t".
    private ArvoreBinariaPesquisa abp; // Possui uma árvore binária de pesquisa onde os pedidos realizados são alocados.
    private ListaFinalizados lista;

    // Certo!
    public Pizzaria(){
        this.pedidoAtual = null;
        this.listaPedido = new FilaPedidosDinamica();
        this.tempoTotal = 0;
        this.abp = new ArvoreBinariaPesquisa();
        this.lista = new ListaFinalizados();
    }

    // Certo!
    public void adicionarPedido(Pedido p) {
        if (pedidoAtual == null) { // Se não houver pedido atual, o pedido "p" será o atual.
            pedidoAtual = p;
        } else {
            listaPedido.enfileirar(p); // Se já houver algum pedido atual, o pedido "p" será alocado na fila.
        }
    }
    public void setPedidoAtual(Pedido pedidoAtual) {
        this.pedidoAtual = pedidoAtual;
    }
    // Certo!
    public void processarPedido() {
        if (pedidoAtual != null) {
            pedidoAtual.setTempoPreparo(pedidoAtual.getTempoPreparo() - 1);
            if (pedidoAtual.getTempoPreparo() <= 0) {
                abp.adicionar(pedidoAtual);// Uma referência ao pedido atual é armazenada.
                lista.adicionar(pedidoAtual);// Adiciona o pedido pronto a lista de pedidos finalizados
                pedidoAtual = null; // Fica nulo para o próximo pedido da fila.
            }
        }
    }
    // Certo!
    public boolean pizzaioloDisponivel() {
        return pedidoAtual == null; // O pizzaiolo só está disponível se o pedido atual for nulo.
    }
    // Certo!
    public Pedido getPedidoAtual() {
        return pedidoAtual;
    }
    // Certo!
    public ArvoreBinariaPesquisa getPedidosProntos() {
        return abp;
    }
}

