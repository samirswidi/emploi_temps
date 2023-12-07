package gestion_emploi;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Achref
 */
import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class connexion {
  
  public Connection  connexion() throws SQLException
    {
          Connection connection=null;
        
        try 
        {
         connection =DriverManager.getConnection("jdbc:mysql://localhost:3306/Emploi_temps?useSSL=false","root", "");
        
        
      //  meme base données en ligne
 /*connection =DriverManager.getConnection("jdbc:mysql://sql11.freemysqlhosting.net/sql11666730?useSSL=false",
                        "sql11666730", "Dij8Kei4j9");*/

        }
       catch (SQLException e) {
    
     JOptionPane.showMessageDialog(null, "Erreur lors de la connexion à la base de données : " + e.getMessage());
}
       return connection; 
    }
}
