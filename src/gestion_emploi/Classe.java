package gestion_emploi;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
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
public class Classe {
   public String libelle;
   public String description;
     PreparedStatement st;
       connexion cnx=new connexion();
   public void ajouter_classe(String libelle,String description)
   {
          try{ 
              st =  cnx.connexion()
            .prepareStatement("INSERT INTO classe (libelle_classe,description) values('" + libelle+ "','" + description + "')");

           
           st.executeUpdate();
              
      
               JOptionPane.showMessageDialog(null,"Données enregistré avec succès");
          }
          catch (Exception e)
          {
              
          }
   }
   public void get(JTable tabl_classe,JComboBox<String> list_classe)
   {
        try
        {
             st =  cnx.connexion().prepareStatement("Select * from classe ");
                ResultSet rs = st.executeQuery();
                DefaultTableModel model=(DefaultTableModel)tabl_classe.getModel();
             while (rs.next()) 
             {
               list_classe.addItem(rs.getString(2));
                    model.addRow(new String[]{rs.getString(1),rs.getString(2),rs.getString(3)});
              }
        }
        catch(Exception e)
        {
            
        }
   }
    public String get_by_libelle(String libelle)
     {  
         String retour ="false";
     try
         {
             String req="Select * from classe where libelle='"+libelle+"' ";
              
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
  public void supprimer_classe(String choix)  
  {
       try
        {

          
           
             st =  cnx.connexion()
            .prepareStatement("DELETE FROM  classe   where libelle_classe= '" + choix + "'");

             st.executeUpdate();
             
               JOptionPane.showMessageDialog(null,"Classe d'id "+choix+" est supprimé avec succès");
 
        }
 
        catch(Exception e)
        {
           
        }  
  }
  public void modifier_classe (String libelle_action, String description_action,String choix)
  {
       try
        {

          
            
             st =  cnx.connexion()
            .prepareStatement("UPDATE  classe "
                    + "set libelle_classe='" + libelle_action + "',description= '" +
                        description_action + "' where libelle_classe= '" + choix + "'");

           
             st.executeUpdate();
             
               JOptionPane.showMessageDialog(null,"Données modifié avec succès");
 
        }
 
        catch(Exception e)
        {
           
        }
  }
 public   void update_table(JTable tabl_classe)
{
    try
    {
       
        String sql="SELECT * FROM classe";
        st=cnx.connexion().prepareStatement(sql); 
        ResultSet rs=st.executeQuery();
        tabl_classe.setModel(DbUtils.resultSetToTableModel(rs));

    }
    catch(Exception e)
    {
    }
}
public void update_list(JComboBox<String> list_classe) {
    try {
        // Vérifier si list_classe est null et l'initialiser si c'est le cas
        if (list_classe == null) {
            list_classe = new JComboBox<>();
        } else {
            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(); // Créer un nouveau modèle
            list_classe.setModel(model); // Affecter le nouveau modèle au JComboBox
        }

        String sql = "SELECT * FROM classe";
        st = cnx.connexion().prepareStatement(sql);
        ResultSet rs = st.executeQuery();

        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) list_classe.getModel();

        model.addElement("Votre Choix:"); // Ajouter un élément initial

        while (rs.next()) {
            model.addElement(rs.getString("libelle_classe"));
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

 public void change_list_classe(JTextField libelle_action,JTextArea description_action, String choix)
 {
    try
    {
         st =  cnx.connexion()
                        .prepareStatement("Select * from classe where libelle_classe=? ");
                    st.setString(1, choix);
                   
                    ResultSet result_verif = st.executeQuery();
                   
 if (result_verif.next()) {
     
  String nom=result_verif.getString("libelle_classe");
  libelle_action.setText(nom);
   
    String contact=result_verif.getString("description");
    description_action.setText(contact);
    
 }  
 }
    catch(Exception e)
    {
        
    }
 }
    
}
