
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
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
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
    public void insertSp(String word ,String meaning) throws SQLException
    {
        DefaultTableModel model = (DefaultTableModel) traversal.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        
        Node t=head;
        Node n=new Node();
        n.word=word;
        n.meaning=meaning;
        n.next=null;
       int count=0;
     //  System.out.println(n.word);
     int crow=0;
      t=head;
     while(t!=null)
     {
         crow++;
         t=t.next;
            
     }
     System.out.println(crow);
     t=head;
       while(t!=null)
       {
           
            int k=((t.word).compareTo(n.word));
          //  System.out.println("k : "+k);
          //  System.out.print(" ");
            if(k>0)
            {
                break;
            }
                       /* if(k<0)
            {
                count=1;
                break;
            }  */
            count++;
            t=t.next;
        }
       System.out.println("count : " +count);
     // t=head;
     
       int b=crow;
         if(count==0)
           {
               t=head;
               head=n;
               n.next=t;
               b++;
           }
         else if(count<b)
         {
             t=head;
            for(int h=1;h<count;h++)
            {
                  t=t.next;
            }
            n.next=t.next;
            t.next=n;
            b++;
         }
        else
         {
             t=head;
             for(int h=1;h<count-1;h++)
            {
                  t=t.next;
            }
             t.next=n;
             b++;
             
         } 
         
         // insert();
        //  insertSp(String word ,String meaning);
        CreateColumns();
        
        Populate();
      //   show();
          
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

        
    }   */
    public void wmean(String in)
    {
        Node t= head;
       
            while(t!=null)
            {
                if((t.word).compareTo(in)==0)
                {
                    String m1 = t.meaning;
                    meaning1.setText(m1);
                  //  System.out.println(m1);
                    return ;
                }
                  
                 t=t.next;
            }
            if(t==null)
            {
                meaning1.setText("Word Not Found");
            }
        
    } 

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
        
      
        System.out.println("25");
       dm=(DefaultTableModel)traversal.getModel(); 
     
        dm.setRowCount(0); 

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
       //   System.out.println(node.word);
        //    System.out.println(node.meaning); 
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
        insertIn = new javax.swing.JButton();
        wordIn = new javax.swing.JTextField();
        meanIn = new javax.swing.JTextField();
        bg_insert = new javax.swing.JLabel();
        see_all_words = new javax.swing.JPanel();
        txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        traversal = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        delete = new javax.swing.JPanel();
        result = new javax.swing.JTextField();
        dword = new javax.swing.JTextField();
        dbutton = new javax.swing.JButton();
        tab_bg = new javax.swing.JLabel();
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

        insertIn.setText("INSERT");
        insertIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertInActionPerformed(evt);
            }
        });
        insert.add(insertIn);
        insertIn.setBounds(250, 250, 69, 23);

        wordIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insert.add(wordIn);
        wordIn.setBounds(90, 60, 420, 50);

        meanIn.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insert.add(meanIn);
        meanIn.setBounds(90, 140, 420, 90);

        bg_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        bg_insert.setText("jabel2");
        insert.add(bg_insert);
        bg_insert.setBounds(0, 0, 620, 490);

        jTabbedPane2.addTab("INSERT", insert);

        see_all_words.setLayout(null);

        txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt.setForeground(new java.awt.Color(204, 204, 204));
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFocusGained(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });
        see_all_words.add(txt);
        txt.setBounds(70, 10, 320, 40);

        traversal.setAutoCreateRowSorter(true);
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
        jScrollPane1.setBounds(0, 60, 620, 430);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        see_all_words.add(jLabel1);
        jLabel1.setBounds(0, 0, 620, 500);

        jTabbedPane2.addTab("SEE ALL WORDS", see_all_words);

        delete.setLayout(null);

        result.setEditable(false);
        result.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        delete.add(result);
        result.setBounds(40, 180, 470, 60);

        dword.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dword.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dwordFocusGained(evt);
            }
        });
        dword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dwordActionPerformed(evt);
            }
        });
        dword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dwordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                dwordKeyReleased(evt);
            }
        });
        delete.add(dword);
        dword.setBounds(40, 60, 300, 60);

        dbutton.setText("DELETE");
        dbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbuttonActionPerformed(evt);
            }
        });
        delete.add(dbutton);
        dbutton.setBounds(380, 70, 110, 30);

        tab_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict/w1.jpg"))); // NOI18N
        delete.add(tab_bg);
        tab_bg.setBounds(0, 0, 620, 500);

        jTabbedPane2.addTab("DELETE", delete);
        delete.getAccessibleContext().setAccessibleName("");

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
                wmean(word.getText());
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
    public void filter(String query)
    {
        System.out.println("aaaa");
      if(txt.getText().length()!=15)
       {
           
        TableRowSorter<DefaultTableModel> tr = new  TableRowSorter<>(dm);
        traversal.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query)); 
       } 
    }
    public void filter_v(String query)
    {
      /*  System.out.println("bbbbb");
        if(txt.getText().length()!=15)
        {    
       /* DefaultTableModel model = (DefaultTableModel) traversal.getModel();
        model.setRowCount(0); */
        
      /*  Node t= head;
        
            while(t!=null)
            {
               // int q=t.word.length();
                           
             
               if((t.word).charAt(0)==txt.getText().charAt(0)) */
            /*    int h=txt.getText().length();
                System.out.println(h);
                String s=(t.word).substring(0,h);
              if(s.compareTo(txt.getText())==0) 
                {
                    
                      Object[] rowData = new Object[2];
                      rowData[0]=t.word;
                      rowData[1]=t.meaning;
                      dm.addRow(rowData);
                    
                }
     
                                 
                 t=t.next;
            } */
        }    
     
                   
    
                   
          
     /*   int y=txt.getText().length();
        if(y==0)
        {
         //   DefaultTableModel model = (DefaultTableModel) traversal.getModel();
            model.setRowCount(0);
             model.setColumnCount(0); 
             CreateColumns();
             Populate();
             
        } */
    
     
    

    
    private void wordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordFocusGained
        // TODO add your handling code here:
         word.setCaretPosition(0);
    }//GEN-LAST:event_wordFocusGained

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
        // TODO add your handling code here:
        /* if(txt.getText().isEmpty()==true)
        {
            
           
            txt.setText("Enter Word Here");
            txt.setCaretPosition(0);
            txt.setForeground(new java.awt.Color(204, 204, 204));
         /*    try {
                 
                 insert();
                Node node=head;
                 System.out.println(head.word); 
             } catch (SQLException ex) {
                 Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
             }
            CreateColumns();
            Populate(); 
        } */
       if(txt.getText().isEmpty()==false)
       {
       String query =txt.getText().toLowerCase();
        filter(query);
        filter_v(query);
       }
       /*  try {
                 
                 insert();
                Node node=head;
                 System.out.println(head.word); 
             } catch (SQLException ex) {
                 Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
             } */
          //  CreateColumns();
          //  Populate();
        int y=txt.getText().length();
       System.out.println(y);
        if(y==0)
        {
          DefaultTableModel model = (DefaultTableModel) traversal.getModel();
            model.setRowCount(0);
            model.setColumnCount(0); 
            System.out.println("hello");
             CreateColumns();
             System.out.println("word");
             Populate(); 
        }  
    }//GEN-LAST:event_txtKeyReleased

    private void txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyPressed
        // TODO add your handling code here:
       /*  if(txt.getForeground()!=Color.BLACK)
        {
            if(txt.getText().equals("Enter Word Here"))
            {
                txt.setText("");
            }
            txt.setForeground(Color.BLACK);
        } */
  
    }//GEN-LAST:event_txtKeyPressed

    private void txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusGained
        // TODO add your handling code here:
       //  txt.setCaretPosition(0);
    }//GEN-LAST:event_txtFocusGained

    private void insertInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertInActionPerformed
          try {
              // TODO add your handling code here:
              //  String p=wordIn.getText();
              // System.out.println(p);
              // show();
              insertSp(wordIn.getText(),meanIn.getText());
          } catch (SQLException ex) {
              Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }//GEN-LAST:event_insertInActionPerformed

    private void dwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dwordActionPerformed

    private void dwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dwordKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_dwordKeyReleased

    private void dwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dwordKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_dwordKeyPressed
    public void delete()
    {
       Node p=head;
        Node q=head;
        String delword=dword.getText();
      /* int index=1;
        while(p!=null)
        {
            if((p.word).equals(delword)==true)
            {
              break;
            }
            index++;
        }
        //p=head;
       System.out.println(index);
      /*  if(index==1)
        {
            head=head.next;
        }
        else
        {
            Node n=head;
            Node n1=null;
           for(int h=1;h<index-1;h++)
            {
                  n=n.next;
            }
          n1=n.next;
          n.next=n1.next;
          System.out.println("n1 :" +n1.word);
        } */
        while(p!=null)
        {
            if((p.word).compareTo(delword)==0)
            {
                if(p==head)
                {
                    head=head.next;
                    break;
                }
               q.next=p.next;
              // System.out.println(p.word);
               break;
            }
            q=p;
            p=p.next; 
                
        }
        if(p==null)
        {
            result.setText("Word Not Found");
        }
        else
        {
            result.setText("Word Deleted Succesfully");
        }
        DefaultTableModel model = (DefaultTableModel) traversal.getModel();
            model.setRowCount(0);
             model.setColumnCount(0); 
        CreateColumns(); 
        Populate(); 
    }
    private void dbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dbuttonActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_dbuttonActionPerformed

    private void dwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dwordFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_dwordFocusGained

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
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
    private javax.swing.JLabel bg_insert;
    private javax.swing.JLabel bg_search;
    private javax.swing.JButton dbutton;
    private javax.swing.JPanel delete;
    private javax.swing.JTextField dword;
    private javax.swing.JLabel frame;
    private javax.swing.JPanel insert;
    private javax.swing.JButton insertIn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField meanIn;
    private javax.swing.JTextField meaning1;
    private javax.swing.JTextField result;
    private javax.swing.JPanel search;
    private javax.swing.JPanel see_all_words;
    private javax.swing.JLabel tab_bg;
    private javax.swing.JTable traversal;
    private javax.swing.JTextField txt;
    private javax.swing.JLabel w1;
    private javax.swing.JTextField word;
    private javax.swing.JTextField wordIn;
    // End of variables declaration//GEN-END:variables
}
