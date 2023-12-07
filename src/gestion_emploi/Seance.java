package gestion_emploi;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Achref
 */
public class Seance {
    
    
     public PreparedStatement st;
    public   connexion cnx=new connexion();
   public void ajouter_seance(String choix_classe,String matiere,String choix_jour,String heure,String choix_enseignant)
   {
       try 
       {
          st =  cnx.connexion()
                .prepareStatement("Select * from seance where "
                    + "classe=?  and matiere=? and jour=? and heure=? and matricule=?");

                st.setString(1, choix_classe);
                st.setString(2, matiere);
                st.setString(3, choix_jour);
                st.setString(4, heure);
                st.setString(5, choix_enseignant);

                ResultSet result_verif = st.executeQuery();
               
                if (result_verif.next()) {
                    JOptionPane.showMessageDialog(null,"Séance déja existe");
                }
                else {
                    PreparedStatement st =  cnx.connexion()
                    .prepareStatement("INSERT INTO seance (`classe`, `matiere`, `jour`, `heure`, `matricule`) "
                        + "values(?,?,?,?,?)");

                    st.setString(1, choix_classe);
                    st.setString(2, matiere);
                    st.setString(3, choix_jour);
                    st.setString(4, heure);
                    st.setString(5, choix_enseignant);
                    st.executeUpdate();
                   

                    JOptionPane.showMessageDialog(null,"Données enregistré avec succès");

                }       }
       catch(Exception e)
       {
           
       }
   }
   public void update_table(JTable tabl_seance)
{
    try
    {
       
String sql="SELECT * FROM seance";
PreparedStatement pst=cnx.connexion().prepareStatement(sql); 
ResultSet rs=pst.executeQuery();
tabl_seance.setModel(DbUtils.resultSetToTableModel(rs));


    }
    catch(Exception e)
    {
    }
}
   public void delete_seance(String choix)
   {
        try
                {

                    PreparedStatement st =  cnx.connexion()
                    .prepareStatement("DELETE FROM  seance   where id_seance= '" + choix + "'");
                   

                    st.executeUpdate();
                     
                    JOptionPane.showMessageDialog(null,"Séance d'id "+choix+" est supprimé avec succès");
                }

                catch(Exception e)
                {

                }
   }
    public void chercher(JTable tabl_seance1, String choix_classe,String choix_matiere)
   {
        String sql="";
      try { 
           if(choix_matiere!="null")
           {
               sql="SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour, seance.heure, enseignant.nom, enseignant.contact "
                     + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                     + "where classe='"+choix_classe+"' and matiere='"+choix_matiere+"' ";
           }
           else 
           {
                sql="SELECT seance.id_seance as ID,seance.classe, seance.matiere, seance.jour, seance.heure, enseignant.nom, enseignant.contact "
                     + "FROM `seance` INNER JOIN enseignant on enseignant.matricule=seance.matricule "
                     + "where classe='"+choix_classe+"' ";
           }
                
 st=cnx.connexion().prepareStatement(sql); 
ResultSet rs=st.executeQuery();
tabl_seance1.setModel(DbUtils.resultSetToTableModel(rs));
      }
      catch(Exception e)
      {
          
      }
   }
  
   public void update_list_seance(JComboBox<String> list_seance)
{
    try
    {
        
String sql="SELECT * FROM seance";
PreparedStatement pst=cnx.connexion().prepareStatement(sql); 
ResultSet rs=pst.executeQuery();
list_seance.removeAllItems();
list_seance.addItem("Votre Choix:");
  while (rs.next()) {
                       
               list_seance.addItem(rs.getString(1));
  }

    }
    catch(Exception e)
    {
    }
}
   public void update_list_matiere(JComboBox<String> list_matiere)
{
    try
    {
        
String sql="SELECT DISTINCT(matiere) as matiere FROM seance";
PreparedStatement pst=cnx.connexion().prepareStatement(sql); 
ResultSet rs=pst.executeQuery();
list_matiere.removeAllItems();
list_matiere.addItem("Votre Choix:");
  while (rs.next()) {
                       
               list_matiere.addItem(rs.getString("matiere"));
  }

    }
    catch(Exception e)
    {
    }
} 
   
   public void loading(JComboBox<String> list_enseignant_ajout,JComboBox<String> list_seance,
           JComboBox<String> list_classe_ajout, JComboBox<String> list_classe2,JComboBox<String> list_matiere, JTable tabl_seance)
   {
         try
        {
            
String sql0="SELECT matricule FROM enseignant";
PreparedStatement pst0=cnx.connexion().prepareStatement(sql0); 
ResultSet rs0=pst0.executeQuery();
list_enseignant_ajout.removeAllItems();
list_enseignant_ajout.addItem("Votre Choix:");
  while (rs0.next()) {
                       
               list_enseignant_ajout.addItem(rs0.getString("matricule"));
  }
  String sql="SELECT id_seance FROM seance";
PreparedStatement pst=cnx.connexion().prepareStatement(sql); 
ResultSet rs=pst.executeQuery();
list_seance.removeAllItems();
list_seance.addItem("Votre Choix:");
  while (rs.next()) {
                       
               list_seance.addItem(rs.getString("id_seance"));
  }
  String sql1="SELECT * FROM classe";
PreparedStatement pst1=cnx.connexion().prepareStatement(sql1); 
ResultSet rs1=pst1.executeQuery();
/* initialise liste déroulante dans le formulaire d'ajout*/
list_classe_ajout.removeAllItems();
list_classe_ajout.addItem("Votre Choix:");
/* initialise liste déroulante dans l'interface action*/
list_classe2.removeAllItems();
list_classe2.addItem("Votre Choix:");
  while (rs1.next()) {
                       /* remplir liste déroulante dans le formulaire d'ajout*/
               list_classe_ajout.addItem(rs1.getString("libelle_classe"));
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
  update_table(tabl_seance);

        }
        catch(Exception e)
        {
            
        }
   }
  
}
