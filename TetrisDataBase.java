import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** CODE A INSERER DANS LE MAINGAME DANS UNE METHODE QUI SERA APPELLEE 
 * DEPUIS LES GUI LORSQUE L'ON AJOUTERA UN JOUEUR OU QUE L'ON VOUDRA 
 * AFFICHER UN BESTSCORE (ON METTRA CELA EN PARAMETRE ET ON UTILISERA 
 * DES IF OU CATCH POUR CHOISIR QUELLE ACTION FAIRE DANS CETTE METHODE)
 * */

public class TetrisDataBase{
    public static void main (String[] args){
        
        int nextID = 0;
        
        try {
            // ouverture d'un connexion à la base
            /** A MODIFIER **/
                Connection myCo = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TetrisDB","tetDB_id","tetDB_pw");

                
            // addig a nex player in the database
            if (newPlayer != null){
                
            
                // select the next id to add
                String query = "SELECT MAX (idPlayer) from Players WHERE playerName is not NULL;" ;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while ( rs.next() ) {
                    System.out.println( rs.getInt("idPlayer"));
                    nextID = 1 + rs.getInt("idPlayer");
                }
                // Insertion
                INSERT INTO Players VALUES (nextID/*, newPlayer*/) ; // nexPlayer est un String qui sera en parametre de la méthode das le mainGame
                    
                    
                // verifying the modification
                    
                String query = "SELECT * from Players WHERE idPlayer = "+nextID ;
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while ( rs.next() ) {
                    System.out.println(" new player added (id || name)"+ rs.getInt("idPlayer") + rs.getString("playerName"));
                    //we cancel the last nextID
                    nextID = O;
                }
            }
            
            /*
            // exemple de requete simple
                String query = "SELECT idMessage, dateMessage, texteMessage " +
                "FROM Message WHERE idAuteur = 1 " ;
            // execution de la requete
                Statement statement= connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
            // récupération et traitement du résultat
                System.out.println ("Vos Messages :") ;
                System.out.println("idMessage | dateMessage | texteMessage”);
            
            
            while ( rs.next() ) {
                System.out.println( rs.getInt("idMessage") + " | " +
                rs.getDate("dateMessage") + " | " + rs.getString("texteMessage") );
            }
            * */
            
            // fermeture de la connexion à la base
            connection.close() ;
        }
        catch (java.sql.SQLException e) {
            System.err.println ("error db") ; // Affiche l’exception
            //System.exit (-1) ; // Arrête toute l’application
        }
    }
}
