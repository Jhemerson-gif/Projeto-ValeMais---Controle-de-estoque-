package ValeMais;

import javax.swing.*;


public class MinhaTela extends JFrame {

    public MinhaTela() {
        setTitle("Minha Janela");
        setSize(480, 840);
        setIconImage(new ImageIcon("C:\\Users\\jheme\\Downloads200.gif").getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);          // Abre/exibe a janela
    }

    public static void main(String[] args) {
        // Executa a criação da tela
        new MinhaTela();
    }
}