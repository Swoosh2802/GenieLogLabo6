//Classe mère

public abstract class Authenticator {  //Constructeur
    public final boolean authenticate(String username, String password) { //Squelette
        if (exists(username)) { //Verification
            String storedPassword = getPassword(username);
            return storedPassword.equals(password); //Compare mdp stocké avec mdp en paramètre
        }
        return false;
    }

    public abstract String getLogin(String username);
    public abstract String getPassword(String username);
    public abstract boolean exists(String username); //Methode pour vérifier qu'un user existe bien
}
