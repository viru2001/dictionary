/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author My
 */
public class data {
     int i=1;
     int mea=1;
     int m=0;
    public String w[]=new String[30];
    public String me[]=new String[30];
     public String[] db_word() throws SQLException
            {
               Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/dict", "root", "root");
         //    System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICT");
              
              while(rs.next())
              {
                  w[i]=rs.getString(1);
                  i++;
              }
              return w;
            }
     public String[] db_meaning() throws SQLException
            {
               Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/dict", "root", "root");
         //    System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICT");
              
              while(rs.next())
              {
                  me[mea]=rs.getString(2);
                  mea++;
              }
              return me;
            }

     
     public  int row() throws SQLException {
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/dict", "root", "root");
            //  System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICT");
              
              while(rs.next())
              {
                  m++;
              }
              return m;
     }
      

    
}
