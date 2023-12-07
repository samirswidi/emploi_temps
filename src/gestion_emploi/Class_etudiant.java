/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_emploi;

/**
 *
 * @author Samir Souidi
 */
  

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class Class_etudiant {
     public PreparedStatement st;
    public   connexion cnx=new connexion();
    
  public void chercher_et(JTable tabl_seance_et, String choix_classe, String choix_matiere,String choix_jour) {
    try {
        DefaultTableModel model = new DefaultTableModel(); // Création d'un nouveau modèle de tableau

        // Ajout manuel des colonnes
        model.addColumn("jour");
        model.addColumn("matiere");
        model.addColumn("heure");
         model.addColumn("nom enseignant");
         model.addColumn("contact enseignant");
        // ... Ajoutez d'autres colonnes selon vos besoins

        String sql = "";
String[] jours = {"lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi"}; // Liste des jours
if(choix_jour=="Tous les Jours :")
{
// debut boucle for        
for (String jour : jours) {
        if (!"null".equals(choix_matiere)) {
            sql = "SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour as jour, seance.heure as heure,"
                    + " enseignant.nom as nom, enseignant.contact "
                    + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                    + "where classe='" + choix_classe + "' and matiere='" + choix_matiere + "' and seance.jour='" + jour + "' ";
        } else {
            sql = "SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour as jour, seance.heure, enseignant.nom, enseignant.contact "
                    + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                    + "where classe='" + choix_classe + "' and seance.jour='" + jour + "' ";
        }

        st = cnx.connexion().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        // Ajout manuel des lignes
       
        
        while (rs.next()) {
            
            Object[] rowData = {
                rs.getString("jour"),
                rs.getString("matiere"),
                rs.getString("heure"),
                rs.getString("nom"),
                rs.getString("contact"),
                // Ajoutez d'autres colonnes ici en utilisant rs.getString("nom_de_la_colonne")
                // Assurez-vous de correspondre aux noms de colonnes dans votre requête SQL
            };
            model.addRow(rowData); // Ajout de la ligne au modèle
        }
        
    }
} 
else {
    // debut boucle for        

        if (!"null".equals(choix_matiere)) {
            sql = "SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour as jour, seance.heure as heure,"
                    + " enseignant.nom as nom, enseignant.contact "
                    + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                    + "where classe='" + choix_classe + "' and matiere='" + choix_matiere + "' and seance.jour='" + choix_jour + "' ";
        } else {
            sql = "SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour as jour, seance.heure, enseignant.nom, enseignant.contact "
                    + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                    + "where classe='" + choix_classe + "' and seance.jour='" + choix_jour + "' ";
        }

        st = cnx.connexion().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        // Ajout manuel des lignes
       
        
        while (rs.next()) {
            
            Object[] rowData = {
                rs.getString("jour"),
                rs.getString("matiere"),
                rs.getString("heure"),
                rs.getString("nom"),
                rs.getString("contact"),
                // Ajoutez d'autres colonnes ici en utilisant rs.getString("nom_de_la_colonne")
                // Assurez-vous de correspondre aux noms de colonnes dans votre requête SQL
            };
            model.addRow(rowData); // Ajout de la ligne au modèle
        }
        
    
}

        // fin boucle for
        tabl_seance_et.setModel(model); // Affectation du modèle au JTable

    } catch (Exception e) {
      System.out.println(e);
    }
}

      public void loading( JComboBox<String> list_classe2,JComboBox<String> list_matiere)
   {
         try
        {
            

  String sql1="SELECT * FROM classe";
PreparedStatement pst1=cnx.connexion().prepareStatement(sql1); 
ResultSet rs1=pst1.executeQuery();
/* initialise liste déroulante dans le formulaire d'ajout*/

/* initialise liste déroulante dans l'interface action*/
list_classe2.removeAllItems();
list_classe2.addItem("Votre Choix:");
  while (rs1.next()) {
                      
               /* remplir liste déroulante dans l'interface action*/
                list_classe2.addItem(rs1.getString("libelle_classe"));
  }
  
  String sql2="SELECT DISTINCT(matiere) as matiere FROM seance";
PreparedStatement pst2=cnx.connexion().prepareStatement(sql2); 
ResultSet rs2=pst2.executeQuery();
list_matiere.removeAllItems();
list_matiere.addItem("Votre Choix:");
  while (rs2.next()) {
                       
               list_matiere.addItem(rs2.getString("matiere"));
  }
 

        }
        catch(Exception e)
        {
            
        }
   }
    
}
