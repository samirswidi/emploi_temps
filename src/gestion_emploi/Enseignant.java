package gestion_emploi;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
public class Enseignant {
    String matricule;
    String nom;
    String contact;
    PreparedStatement st;
       connexion cnx=new connexion();
    
    public void ajouter_enseignant(String matricule,String nom , String contact)
    {
         try
         { st =  cnx.connexion()
            .prepareStatement("INSERT INTO enseignant values('" + matricule+ "',"
                   + "'" + nom + "','" +contact + "')");

           
             st.executeUpdate();
             
         }
         catch (Exception e)
                 {
                     
                 }
    }
    public void get(JTable tabl_enseignant,JComboBox<String> list_matricule)
    {
      try
        {
            


  st =  cnx.connexion()
                        .prepareStatement("Select * from enseignant ");

                  
                    ResultSet rs = st.executeQuery();
                    DefaultTableModel model=(DefaultTableModel)tabl_enseignant.getModel();
                    while (rs.next()) {
                       
               list_matricule.addItem(rs.getString(1));
               
model.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
                    }
        }
        catch(Exception e)
        {
            
        }  
    }
    
     public String get_by_matricule(String matricule)
     {  
         String retour ="false";
     try
         {
             String req="Select * from enseignant where matricule='"+matricule+"' ";
              
              st =  cnx.connexion().prepareStatement(req);
             ResultSet  res = st.executeQuery();
             
          if(res.next())  
          { 
            
              retour="true";
          }
          
          
         }
         catch (Exception e)
                 {
                     
                 } 
     return retour;
    }
     
     
 void update_table(JTable tabl_enseignant)
{
    try
    {
       
String sql="SELECT * FROM enseignant";
 st=cnx.connexion().prepareStatement(sql); 
ResultSet rs=st.executeQuery();
tabl_enseignant.setModel(DbUtils.resultSetToTableModel(rs));

    }
    catch(Exception e)
    {
    }
}
 
 public void update_list(JComboBox<String> list_matricule)
{
     try {
        // Vérifier si list_classe est null et l'initialiser si c'est le cas
        if (list_matricule == null) {
            list_matricule = new JComboBox<>();
        } else {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Créer un nouveau modèle
            list_matricule.setModel(model); // Affecter le nouveau modèle au JComboBox
        }

        String sql = "SELECT * FROM enseignant";
        st = cnx.connexion().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) list_matricule.getModel();

        model.addElement("Votre Choix:"); // Ajouter un élément initial

        while (rs.next()) {
            model.addElement(rs.getString(1));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}
 public void modifier_enseignant(String nom_action,String contact_action, String choix)
 {
      try
        {

          
           
             st =  cnx.connexion()
            .prepareStatement("UPDATE  enseignant "
                    + "set nom='" + nom_action+ "',contact= '" +
                        contact_action + "' where matricule= '" + choix + "'");

           
             st.executeUpdate();
             
               JOptionPane.showMessageDialog(null,"Données modifié avec succès");
 
        }
 
        catch(Exception e)
        {
           
        }
 }
 
 public void supprimer_enseignant(String choix)
 {
     try
        {

          
           
             st =  cnx.connexion()
            .prepareStatement("DELETE FROM  enseignant   where matricule= '" + choix + "'");

             st.executeUpdate();
             
               JOptionPane.showMessageDialog(null,"Enseignant d'id "+choix+" est supprimé avec succès");
 
        }
 
        catch(Exception e)
        {
           
        } 
 }
 public void change_list_enseignant(JTextField nom_action,JTextField contact_action, String choix)
 {
     try 
     {
          st =  cnx.connexion()
                        .prepareStatement("Select * from enseignant where matricule='"+choix+"' ");
                   
                   
                    ResultSet result_verif = st.executeQuery();
                   
 if (result_verif.next()) {
     
  String nom=result_verif.getString("nom");
  nom_action.setText(nom);
   
    String contact=result_verif.getString("contact");
    contact_action.setText(contact);
    
 }  
 }
 
 catch (Exception e)
 {
     
 }
}
}
