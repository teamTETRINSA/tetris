//package fr.insalyon.p2i2.td1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
* */
import java.sql.*;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/** 
 * CODE A INSERER DANS LE MAINGAME DANS UNE METHODE QUI SERA APPELLEE 
 * DEPUIS LES GUI LORSQUE L'ON AJOUTERA UN JOUEUR OU QUE L'ON VOUDRA 
 * AFFICHER UN BESTSCORE (ON METTRA CELA EN PARAMETRE ET ON UTILISERA 
 * DES IF OU CATCH POUR CHOISIR QUELLE ACTION FAIRE DANS CETTE METHODE)
 * */

/**
 * DRIVER
 * */
public class TetrisDataBase{
    
    private final String serveurBD = "localhost";
    private final String portBD = "3306";
    private final String nomBD = "TetrisDB";
    private final String loginBD = "tetDB_id";
    private final String motdepasseBD = "tetDB_pw";

    //private Connection myCo = null; // does not work (static problem)
    //private PreparedStatement insertMesureStatement = null;
    //private PreparedStatement selectMesuresStatement = null;
    
    /**
     * main
     * */
     
    public static void main (String[] args){
        
        int nextID = 0;
        String newPlayer = "John"; // to be given as a parameter
        
        try {
            // ouverture d'un connexion à la base
            /** A MODIFIER **/
                Connection myCo = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/TetrisDB");//,"tetDB_id","tetDB_pw");

                
            // addig a nex player in the database
            if (newPlayer != null){
                
            
                // select the next id to add
                String query_1 = "SELECT MAX (idPlayer) from Players WHERE playerName is not NULL;" ;
                Statement statement_1 = myCo.createStatement();
                ResultSet rs_1 = statement_1.executeQuery(query_1);
                while ( rs_1.next() ) {
                    System.out.println( rs_1.getInt("idPlayer"));
                    nextID = 1 + rs_1.getInt("idPlayer");
                }
                // Insertion
                String insertData = "INSERT INTO Players"+" VALUES ("+ nextID +","+ newPlayer +")" ; // nexPlayer est un String qui sera en parametre de la méthode das le mainGame
                statement_1.executeUpdate(insertData); 
                System.out.println("____update executed in database");
                    
                // verifying the modification
                    
                String query_2 = "SELECT * from Players WHERE idPlayer = "+nextID ;
                Statement statement_2 = myCo.createStatement();
                ResultSet rs_2 = statement_2.executeQuery(query_2);
                while ( rs_2.next() ) {
                    System.out.println(" new player added (id || name)"+ rs_2.getInt("idPlayer") + rs_2.getString("playerName"));
                    //we cancel the last nextID
                    nextID = 0;
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
            myCo.close() ;
        }
        catch (java.sql.SQLException e) {
            System.err.println ("error db") ; // Affiche l’exception
            //System.exit (-1) ; // Arrête toute l’application
        }
    }
}
