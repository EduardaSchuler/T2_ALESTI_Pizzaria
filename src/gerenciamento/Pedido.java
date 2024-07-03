package gerenciamento;

public class Pedido{
    private int codigo;
    private String saborPizza;
    private int instante;
    private int tempoPreparo;
    private int tempoTotalProcessamento;


    // Certo!
    public Pedido(int codigo, String saborPizza, int instante, int tempoPreparo){
        this.codigo = codigo;
        this.saborPizza = saborPizza;
        this.instante = instante;
        this.tempoPreparo = tempoPreparo;
        this.tempoTotalProcessamento = 0;
    }

    // Certo!
    public int getCodigo() {
        return codigo;
    }

    // Certo!
    public String getSaborPizza() {
        return saborPizza;
    }

    // Certo!
    public int getInstante(){
        return instante;
    }

    // Certo!
    public int getTempoPreparo() {
        return tempoPreparo;
    }

    // Certo!
    @Override
    public String toString() {
        return "Codigo:" + getCodigo() + ", Sabor: " + getSaborPizza() + ", Instante:" + getInstante() + ", Tempo de preparo:"
                + getTempoPreparo();
    }
    // Certo!
    public void setTempoPreparo(int tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }
//    public int getTempoTotalProcessamento() {
//        return tempoTotalProcessamento;
//    }
//    public void setTempoTotalProcessamento(int tempoTotalProcessamento) {
//        this.tempoTotalProcessamento = tempoTotalProcessamento;
//    }
}