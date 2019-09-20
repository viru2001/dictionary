
import java.awt.Toolkit;
//import db.*;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author My
 */

class Node
{
    String word;
    String meaning;
    Node next;
}
/* class linked_list
{
    public Node head;
    public void insert(String w,String m)
    {
        Node node=new Node();
        node.word=w;
        node.meaning=m;
        node.next=null;
        if(head==null)
        {
            head=node;
        }
        else
        {
            Node n=head;
            while(n.next!=null)
            {
                n=n.next;
            }
            n.next=node;
        }
    }
    public void show()
    {
        Node node = head;
        while(node.next!=null)
        {
            System.out.println(node.word);
            System.out.println(node.meaning); 
            node = node.next;
        }
        System.out.println(node.word);
        System.out.println(node.meaning); 

        
    }
    public String value(String in)
    {
        Node t= head;
       
            while(t!=null)
            {
                if((t.word).compareTo(in)==0)
                {
                    String m1 = t.meaning;
                    System.out.println(m1);
                    return m1;
                }
                  
                 t=t.next;
            }
            return null;
        
    }
  /*  public String getWord()
    {
        Node t=head;
        while(t!=null)
        {
          String m1=t.word;
          
          return m1;
        }
       return null; 
    }
     public String getMeaning()
    {
        Node t=head;
        while(t!=null)
        {
          String m1=t.meaning;
          return m1;
        }
       return null; 
    }  
  } */
      
public class options extends javax.swing.JFrame {
    
      DefaultTableModel dm;
   
  //  String wo[]=new String[30];
  //  String mea[]=new String[150];
    int row;
    int i;
    int mean;
    int a;
  
    
    Node head;
    public void insert()throws SQLException
    {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICTIONARY");

                 
                 
        
        while(rs.next())
        {
            Node node=new Node();
        
         node.word=rs.getString(2);  
        
        node.meaning=rs.getString(3);
        node.next=null;
        if(head==null)
        {
            head=node;
        }
        else
        {
            Node n=head;
            while(n.next!=null)
            {
                n=n.next;
            }
            n.next=node;
        }
      }
    }
    
   /* public void show()
    {
        Node node = head;
        while(node.next!=null)
        {
            System.out.println(node.word);
            System.out.println(node.meaning); 
            node = node.next;
        }
        System.out.println(node.word);
        System.out.println(node.meaning); 

        
    } 
    public String value(String in)
    {
        Node t= head;
       
            while(t!=null)
            {
                if((t.word).compareTo(in)==0)
                {
                    String m1 = t.meaning;
                  //  System.out.println(m1);
                    return m1;
                }
                  
                 t=t.next;
            }
            return null;
        
    } */

/*
     int j=1;
     int d=1;
     int m=0;
    public String w[]=new String[m];
    public String me[]=new String[m];
    public String[] db_word() throws SQLException
            {
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
            //  System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICTIONARY");
              
              while(rs.next())
              {
                  w[j]=rs.getString(2);  
                 j++;
              }
              return w;
            }
     public String[] db_meaning() throws SQLException
            {
               Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
         //    System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICTIONARY");
              
              while(rs.next())
              {
                  me[d]=rs.getString(3);
                  d++;
              }
              return me;
            }
     
     public  int row() throws SQLException {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
            //  System.out.println("Connection Created !!!");
               Statement st=con.createStatement();
               ResultSet rs=st.executeQuery("select * from DICTIONARY");
              
              while(rs.next())
              {
                  m++;
              }
              return m;
     }
      

*/
    /**
     * Creates new form options
     */
    public options() throws SQLException {
      //  linked_list l1=new linked_list(); 

         this.setUndecorated(true);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        double width = toolkit.getScreenSize().getWidth();
        
        double height = toolkit.getScreenSize().getHeight();
        
        this.setSize((int)width, (int)height);
        initComponents();
        insert();
        CreateColumns();
        Populate();
                
    }
    public void CreateColumns()
    {  
      //  DefaultTableModel dm;
          // GET TABLE MODEL
        dm=(DefaultTableModel)traversal.getModel();
        //ADD COLUMNS
      
        dm.addColumn("WORD");
        
        dm.addColumn("MEANING");
        TableColumn column = null;
//for (int f = 0; f < 5; f++) {
    column = traversal.getColumnModel().getColumn(0);
   // if (i == 2) {
        column.setPreferredWidth(100);
        column = traversal.getColumnModel().getColumn(1);//third column is bigger
   column.setPreferredWidth(512);
   // } else {
   //     column.setPreferredWidth(50);
  //  }

 }
    //ADD ROWS DATA
    public void Populate()
    {  
      

       dm=(DefaultTableModel)traversal.getModel(); 

       Node node=head;
      //  node=node.next;
    /*    String w=node.word;
        String m=node.meaning;
       // System.out.println(w + m ); 
         Object[] rowData = new Object[2];
         rowData[0]=w;
         rowData[1]=m;
         
             dm.addRow(rowData); */

       

        
  //     linked_list l2=new linked_list(); 
       
   /*     w=l2.getWord();
        m=l2.getMeaning();
      for(int c=1;c<=row;c++)
      {
        String[] rowData = {w,m};
        dm.addRow(rowData);
      }  */
   while(node!=null)
        {
             String w=node.word;
        String m=node.meaning;
       // System.out.println(w + m ); 
         Object[] rowData = new Object[2];
         rowData[0]=w;
         rowData[1]=m;
         
             dm.addRow(rowData);

            System.out.println(node.word);
            System.out.println(node.meaning); 
            node = node.next;
        }  
     /*   String[] rowData = {node.word,node.meaning};
        dm.addRow(rowData);
        System.out.println(node.word);
        System.out.println(node.meaning);   */
        
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        search = new javax.swing.JPanel();
        meaning1 = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        word = new javax.swing.JTextField();
        bg_search = new javax.swing.JLabel();
        insert = new javax.swing.JPanel();
        delete = new javax.swing.JPanel();
        see_all_words = new javax.swing.JPanel();
        see = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        traversal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        w1 = new javax.swing.JLabel();
        frame = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        search.setLayout(null);

        meaning1.setEditable(false);
        meaning1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search.add(meaning1);
        meaning1.setBounds(20, 220, 560, 150);

        Search.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Search.setText("SEARCH");
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        search.add(Search);
        Search.setBounds(420, 23, 130, 50);

        word.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        word.setForeground(new java.awt.Color(204, 204, 204));
        word.setText("Enter Word Here");
        word.setToolTipText("");
        word.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wordFocusGained(evt);
            }
        });
        word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordActionPerformed(evt);
            }
        });
        word.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                wordKeyReleased(evt);
            }
        });
        search.add(word);
        word.setBounds(10, 20, 350, 50);

        bg_search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        search.add(bg_search);
        bg_search.setBounds(-5, 0, 620, 500);

        jTabbedPane2.addTab("SEARCH", search);

        insert.setLayout(null);
        jTabbedPane2.addTab("INSERT", insert);

        delete.setLayout(null);
        jTabbedPane2.addTab("DELETE", delete);
        delete.getAccessibleContext().setAccessibleName("");

        see_all_words.setLayout(null);

        see.setText("SEE");
        see.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seeActionPerformed(evt);
            }
        });
        see_all_words.add(see);
        see.setBounds(220, 10, 51, 23);

        traversal.setBackground(new java.awt.Color(204, 204, 204));
        traversal.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        traversal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        traversal.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        traversal.setGridColor(new java.awt.Color(0, 0, 0));
        traversal.setShowGrid(true);
        traversal.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(traversal);

        see_all_words.add(jScrollPane1);
        jScrollPane1.setBounds(0, 50, 620, 440);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        see_all_words.add(jLabel1);
        jLabel1.setBounds(0, 0, 620, 500);

        jTabbedPane2.addTab("SEE ALL WORDS", see_all_words);

        jPanel1.add(jTabbedPane2);
        jTabbedPane2.setBounds(420, 180, 620, 520);
        jTabbedPane2.getAccessibleContext().setAccessibleName("SEARCH");

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/dict_logo1 (1).jpg"))); // NOI18N
        jPanel1.add(logo);
        logo.setBounds(420, 75, 620, 100);

        w1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        jPanel1.add(w1);
        w1.setBounds(420, 75, 620, 430);

        frame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/frame.jpg"))); // NOI18N
        jPanel1.add(frame);
        frame.setBounds(370, 0, 720, 780);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/bg.jpg"))); // NOI18N
        jPanel1.add(bg);
        bg.setBounds(0, 0, 1370, 780);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1375, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_wordActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
        // TODO add your handling code here:
      /*     data d1=new data();
        try {
            wo=d1.db_word();
            wo[0]=null;
            mea=d1.db_meaning();
            mea[0]=null;
           row=d1.row(); */
       /*     for(i=1;i<=row;i++)
            {
            System.out.println(wo[i]+" : "+ mea[i]);
            
            } */
     //  linked_list l1=new linked_list();
    /*   for(int k=1;k<=row;k++)
       {
           insert(wo[k],mea[k]);
           
       } 
       
       show();
       
       String in=word.getText();
      // System.out.println(in);
     /*  for(int a=1;a<=row;a++)
       {
           
           if((w1.compareTo(in))==0) //string w1 = word from linked list
           {
               meaning.setText(m1); //string m1 = meaning from linked list
           }
      }
       
       
      // linked_list l=new linked_list();
       String m2 =l1.value(in);
       System.out.println(m2);
       if(m2!=null)
       {
           meaning1.setText(m2);
       }
       } catch (SQLException ex) {
            Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }//GEN-LAST:event_SearchActionPerformed

    private void wordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordKeyReleased
        // TODO add your handling code here:
         if(word.getText().isEmpty()==true)
        {
            word.setText("Enter Word Here");
            word.setCaretPosition(0);
            word.setForeground(new java.awt.Color(204, 204, 204));
        }

    }//GEN-LAST:event_wordKeyReleased

    private void wordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordKeyPressed
        // TODO add your handling code here:
         if(word.getForeground()!=Color.BLACK)
        {
            if(word.getText().equals("Enter Word Here"))
            {
                word.setText("");
            }
            word.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_wordKeyPressed

    private void wordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordFocusGained
        // TODO add your handling code here:
         word.setCaretPosition(0);
    }//GEN-LAST:event_wordFocusGained

    private void seeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seeActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_seeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException {
     /*  options o1=new options();
        o1.insert();
        o1.CreateColumns();
        o1.Populate(); */
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(options.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                
                try {
                    new options().setVisible(true);
      /*              options o1=new options();
        o1.insert();
       o1.CreateColumns();
       o1.Populate(); */

                } catch (SQLException ex) {
                    Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_search;
    private javax.swing.JPanel delete;
    private javax.swing.JLabel frame;
    private javax.swing.JPanel insert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField meaning1;
    private javax.swing.JPanel search;
    private javax.swing.JButton see;
    private javax.swing.JPanel see_all_words;
    private javax.swing.JTable traversal;
    private javax.swing.JLabel w1;
    private javax.swing.JTextField word;
    // End of variables declaration//GEN-END:variables
}
