package janela;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JanelaPedido extends JFrame implements ActionListener {
    private JButton processarPedido, processarAutomatico, pararProcesso;

    public JanelaPedido() {
        super();
        this.setTitle("Simulador de Pedidos de Pizza");
        this.setSize(314, 120);
        processarPedido = new JButton("Processar Pedido");
        processarAutomatico = new JButton("Processar Automatico");
        JPanel container = new JPanel();
        BoxLayout boxLayout = new BoxLayout(container, BoxLayout.X_AXIS);
        container.setLayout(boxLayout);
        container.add(processarPedido);
        container.add(processarAutomatico);
        this.add(container);
//        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == processarPedido) {

        }else if (e.getSource() == processarAutomatico) {

        }
    }
}
