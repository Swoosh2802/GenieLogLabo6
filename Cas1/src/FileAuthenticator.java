import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileAuthenticator extends Authenticator {
    private String filePath = ""; //Chemin du fichier qui a les infos d'authentification

    public FileAuthenticator(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getLogin(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) { //Lecture du fichier ligne par ligne
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(":"); //Delimiteur
                if (parts.length == 2 && parts[0].equals(username)) {  //Si la ligne contient bien 2 parties
                    return parts[0]; //Retourne le login trouvé
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        return null; //Si login non trouvé
    }

    @Override
    public String getPassword(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return parts[1]; //Retourne le mdp associé
                }
            }
        } catch (IOException e) {
            //Exception si pb de lecture du fichier
        }
        return null; //Si mdp non trouvé
    }

    @Override
    public boolean exists(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true; // L'utilisateur existe
                }
            }
        } catch (IOException e) {
            //Exception si pb de lecture du fichier
        }
        return false; //Si l'utilisateur n'existe pas
    }

}
