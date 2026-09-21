package ValeMais;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    // Agora criamos um método que "entrega" uma conexão pronta
    public static Connection obterConexao() {
        String url = "jdbc:mysql://127.0.0.1:3306/app_estoque";
        String usuario = "root";
        String senha = "Amongus@123"; // <-- Coloque sua senha aqui
        try {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar no banco!");
            throw new RuntimeException(e);
        }
    }
}