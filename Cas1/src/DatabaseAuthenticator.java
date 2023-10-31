import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseAuthenticator extends Authenticator {
    private Connection databaseConnection; //Connexion à la BDD

    public DatabaseAuthenticator(Connection databaseConnection) { //Constructeur
        this.databaseConnection = databaseConnection;
    }

    @Override
    public String getLogin(String username) {
        try {
            String sql = "SELECT login FROM users WHERE username = ?";  //Requete sql
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("login"); //Retourne le login trouvé
            }
        } catch (SQLException e) {
            //Gère l'exception si erreur SQL
        }
        return null; //Si login non trouvé
    }

    @Override
    public String getPassword(String username) {
        try {
            String sql = "SELECT password FROM users WHERE username = ?"; //Requete sql
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password"); //Retourne le mdp associé
            }
        } catch (SQLException e) {
            //Gere l'exception si erreur SQL
        }
        return null; //Si mdp non trouvé
    }

    @Override
    public boolean exists(String username) {
        try {
            String sql = "SELECT COUNT(*) FROM users WHERE username = ?"; //Requete sql
            PreparedStatement statement = databaseConnection.prepareStatement(sql);
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0; //Vérifie si l'utilisateur existe
            }
        } catch (SQLException e) {
            //Gère l'exception si erreur SQL
        }
        return false; //Si l'utilisateur n'existe pas
    }
}
