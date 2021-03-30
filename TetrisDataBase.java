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

public class TetrisDataBase{
    public static void main (String[] args){
        try {
            // ouverture d'un connexion à la base
            Connection connection = DriverManager.getConnection(
            "jdbc:mariadb://fimi-bd-srv1.insa-lyon.fr:3306/BD-MESSAGES",
            "monlogin","monmdp");
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
            // fermeture de la connexion à la base
            connection.close() ;
        }
        catch (java.sql.SQLException e) {
        System.err.println (e) ; // Affiche l’exception
        System.exit (-1) ; // Arrête toute l’application
    }
}
