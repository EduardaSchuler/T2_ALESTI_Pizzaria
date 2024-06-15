public class Pedido{
    private int codigo;
    private String saborPizza;
    private int instante;
    private int tempoPreparo;

    public Pedido(int codigo, String saborPizza, int instante, int tempoPreparo){
        this.codigo = codigo;
        this.saborPizza = saborPizza;
        this.instante = instante;
        this.tempoPreparo = tempoPreparo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSaborPizza() {
        return saborPizza;
    }

    public int getInstante(){
        return instante;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    @Override
    public String toString() {
        return "Pedido [getCodigo()=" + getCodigo() + ", getSaborPizza()=" + getSaborPizza() + ", getInstante)=" + getInstante() + ", getTempoPreparo()="
                + getTempoPreparo() + "]";
    }
}