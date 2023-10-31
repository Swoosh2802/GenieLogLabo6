public class Main {
    public static void main(String[] args) {
        //Création d'une instance de FileAuthenticator avec le filePath
        FileAuthenticator fileAuthenticator = new FileAuthenticator("Cas1\\src\\MdpUsers.txt");

        //On teste l'authentification
        String username = "anissa";
        String password = "mdp1";

        if (fileAuthenticator.exists(username)) {  //S'il y a bien l'utilisateur dans le fichier
            String storedPassword = fileAuthenticator.getPassword(username);
            if (password.equals(storedPassword)) { //Verification que les mdp correspondent
                System.out.println("L'authentification a reussie pour " + username);
            } else {
                System.out.println("Le mot de passe est incorrect pour " + username);
            }
        } else {
            System.out.println("L'utilisateur " + username + " n'existe pas");
        }
    }
}
