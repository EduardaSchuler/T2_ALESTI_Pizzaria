public class Pedido{
    private int codigo;
    private String saborPizza;
    private int tempoPreparo;

    public Pedido(int codigo, String saborPizza, int tempoPreparo){
        this.codigo = codigo;
        this.saborPizza = saborPizza;
        this.tempoPreparo = tempoPreparo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSaborPizza() {
        return saborPizza;
    }

    public int getTempoPreparo() {
        return tempoPreparo;
    }

    @Override
    public String toString() {
        return "Pedido [getCodigo()=" + getCodigo() + ", getSaborPizza()=" + getSaborPizza() + ", getTempoPreparo()="
                + getTempoPreparo() + "]";
    }
}