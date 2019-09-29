import java.awt.Toolkit;
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
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.util.Random;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author My
 */
// Creating Node for Linked List
class Node 
{
    String word;
    String meaning;
    Node next;
}

      
public class options extends javax.swing.JFrame {
   
    DefaultTableModel dm;
    int row;
    int i;
    int mean;
    int a;
    Node head;
    public void appMenu()  //Tablet panel start
    {
        start.setVisible(true);
        start.setEnabled(true);
    }
    public void appHomeS()  // dictionary home start
    {
                     home_pan.setVisible(true);
                     home_pan.setEnabled(true);
    }
    public void appHomeC()   // dictionary home close
    {
                     home_pan.setVisible(false);
                     home_pan.setEnabled(false);
    }
    public void slideBarHomeS()  // slide bar on home screen start
    {
        slide_bar_panHome.setVisible(true);
        slide_bar_panHome.setEnabled(true);
    }
     public void slideBarHomeC()  // slide bar on home screen close
    {
        slide_bar_panHome.setVisible(false);
        slide_bar_panHome.setEnabled(false);
    }
     public void slideBarAboutS()   // slide bar on about screen start
    {
        slide_bar_panAbout.setVisible(true);
        slide_bar_panAbout.setEnabled(true);
    }
     public void slideBarAboutC()   // slide bar on about screen close
    {
        slide_bar_panAbout.setVisible(false);
        slide_bar_panAbout.setEnabled(false);
    }
    public void aboutPanS()  // About us screen start
    {
        about_pannel.setVisible(true);
        about_pannel.setEnabled(true);
    }
     public void aboutPanC()   // About us screen start
    {
        about_pannel.setVisible(false);
        about_pannel.setEnabled(false);
    }
      public void wodS()  // Word of the day screen start
    {
        wod.setVisible(true);
        wod.setEnabled(true);
    }
     public void wodC()  // Word of the day screen close
    {
        wod.setVisible(false);
        wod.setEnabled(false);
    }
     public void loadingS()  // loading gif start
     {
        loading.setVisible(true);
        loading.setEnabled(true);
         
     }
     public void loadingC()   // loading gif close
     {
        loading.setVisible(false);
        loading.setEnabled(false);

     }
    public void randomWord()  // generates random word using Random()
    {
        int rowCount=0;
        Random rand = new Random();
        Node t=head;
        while(t!=null)
        {
         rowCount++;   
         t=t.next;   
        }
        int r = rand.nextInt(rowCount);
        int k=0;
        Node t1 = head;
        while(t1!=null)
        {
            k++;
            if(k==r)
            {
                wod_word.setText(t1.word);
                wod_meaning.setText(t1.meaning);
                break;
            }
            t1=t1.next;
        }
    }
    public void insert()throws SQLException //inserting values in linked list from database
    {
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery("select * from DICTIONARY");
        while(rs.next()) // traversing rows in DB 
        {
            Node node=new Node();
            node.word=rs.getString(2);  // storing data in node of linked list
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
    public void insertSp(String word ,String meaning) throws SQLException // insert data at specific location 
    {                                                                    //in linked list and jTable
        DefaultTableModel model = (DefaultTableModel) traversal.getModel(); // creating jTable
        model.setRowCount(0); 
        model.setColumnCount(0);
        Node t;
        Node n=new Node();
        n.word=word;   // taking values linked list
        n.meaning=meaning;
        n.next=null;
       int count=1;
       int crow=0; // total no. of=f rows in Linked list
       t=head;
       while(t!=null)
       {
           crow++;
           t=t.next;
        }
       t=head;
       while(t!=null)
       {
            int k=((t.word).compareTo(n.word));//comparing new word with word in Linked list
            if(k>0)
            {
                break;
            }
            count++;  // location of node to insert new word  
            t=t.next;
        }
         int b=crow;
         if(count==1)// if node is first
           {
               t=head;
               head=n;
               n.next=t;
               b++;
           }
         else if(count<b)
         {
             t=head;
            for(int h=1;h<count-1;h++)
            {
                  t=t.next;
            }
            n.next=t.next;
            t.next=n;
            b++;
         }
         else //if node is last
         {
             t=head;
             for(int h=1;h<count-1;h++)
            {
                 t=t.next;
            }
             t.next=n;
             b++;
          } 
       CreateColumns(); 
       Populate();
    }   
    public void wmean(String in) // to display meaning of searched word
    {
        Node t= head;
       
            while(t!=null)
            {
                if((t.word).compareTo(in)==0)
                {
                    String m1 = t.meaning;
                    meaning1.setText(m1);
                    return ;
                }
                  
                 t=t.next;
            }
            if(t==null)
            {
                meaning1.setText("Word Not Found");
            }
        
    } 
    public options() throws SQLException {
     // full screen output without task bar and top bar
         this.setUndecorated(true);
        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        double width = toolkit.getScreenSize().getWidth();
        
        double height = toolkit.getScreenSize().getHeight();
        
        this.setSize((int)width, (int)height);
        initComponents();
        
        appMenu();  // displaying tablet screen
      // closing all pannels except app menu pannel
        loadingC();   
        appHomeC();
        slideBarHomeC();
        slideBarAboutC();
        aboutPanC();
        wodC();
        
        insert(); // inserting data from DB in linked list
        // inserting data from linked list to jTable
        CreateColumns();
        Populate();
    }
    public void CreateColumns()
    {  
       // GET TABLE MODEL
        dm=(DefaultTableModel)traversal.getModel();
        //ADD COLUMNS
      
        dm.addColumn("WORD");
        dm.addColumn("MEANING");
        TableColumn column ;
      // setting size of columns
        column = traversal.getColumnModel().getColumn(0);
  
        column.setPreferredWidth(100);
        column = traversal.getColumnModel().getColumn(1); //second column is bigger
        column.setPreferredWidth(512);
 }
    //ADD ROWS DATA
public void Populate()
 {  
    dm=(DefaultTableModel)traversal.getModel(); 
    Node node=head;
    while(node!=null)
        {
             String w=node.word;
             String m=node.meaning;
             Object[] rowData = new Object[2];
             rowData[0]=w;
             rowData[1]=m;
             dm.addRow(rowData);
             node = node.next;
        }  
 }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main_pan = new javax.swing.JPanel();
        start = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        app_icon = new javax.swing.JLabel();
        app_menu = new javax.swing.JLabel();
        frame_start = new javax.swing.JLabel();
        about_pannel = new javax.swing.JPanel();
        menu_about = new javax.swing.JLabel();
        abt_short = new javax.swing.JLabel();
        abt = new javax.swing.JLabel();
        team_logo = new javax.swing.JLabel();
        topbar1 = new javax.swing.JLabel();
        jay_txt = new javax.swing.JLabel();
        abhi_txt = new javax.swing.JLabel();
        viru_txt = new javax.swing.JLabel();
        n_logo = new javax.swing.JLabel();
        wod = new javax.swing.JPanel();
        meanSpeak = new javax.swing.JButton();
        wordSpeak = new javax.swing.JButton();
        close = new javax.swing.JButton();
        wod_meaning = new javax.swing.JTextField();
        wod_word = new javax.swing.JTextField();
        disp_random = new javax.swing.JLabel();
        wod_bg = new javax.swing.JLabel();
        slide_bar_panAbout = new javax.swing.JPanel();
        home_about = new javax.swing.JButton();
        wod_about = new javax.swing.JButton();
        exit_about = new javax.swing.JButton();
        menu_txt_about = new javax.swing.JLabel();
        sbar_about = new javax.swing.JLabel();
        remove_menuAbout = new javax.swing.JLabel();
        slide_bar_panHome = new javax.swing.JPanel();
        random = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        about = new javax.swing.JButton();
        menu_txt = new javax.swing.JLabel();
        sbar = new javax.swing.JLabel();
        remove_menu = new javax.swing.JLabel();
        home_pan = new javax.swing.JPanel();
        topbar = new javax.swing.JLabel();
        home_tab = new javax.swing.JTabbedPane();
        search = new javax.swing.JPanel();
        speak_meaning = new javax.swing.JButton();
        speak_word = new javax.swing.JButton();
        meaning1 = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        word = new javax.swing.JTextField();
        search_bg = new javax.swing.JLabel();
        insert = new javax.swing.JPanel();
        insert_txt = new javax.swing.JLabel();
        op = new javax.swing.JTextField();
        insertIn = new javax.swing.JButton();
        wordIn = new javax.swing.JTextField();
        meanIn = new javax.swing.JTextField();
        bg_insert = new javax.swing.JLabel();
        see_all_words = new javax.swing.JPanel();
        txt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        traversal = new javax.swing.JTable();
        saw_bg = new javax.swing.JLabel();
        delete = new javax.swing.JPanel();
        result = new javax.swing.JTextField();
        dword = new javax.swing.JTextField();
        dbutton = new javax.swing.JButton();
        tab_bg = new javax.swing.JLabel();
        menu_homeTab = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        loading = new javax.swing.JPanel();
        loading_txt = new javax.swing.JLabel();
        loading_gif = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        main_pan.setLayout(null);

        start.setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Dictionary");
        start.add(jLabel1);
        jLabel1.setBounds(412, 567, 60, 20);

        app_icon.setIcon(new javax.swing.ImageIcon("C:\\Users\\My\\Downloads\\Webp.net-resizeimage.png")); // NOI18N
        app_icon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                app_iconMouseClicked(evt);
            }
        });
        start.add(app_icon);
        app_icon.setBounds(405, 525, 60, 45);

        app_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_screen 1.jpg"))); // NOI18N
        start.add(app_menu);
        app_menu.setBounds(50, 72, 630, 640);

        frame_start.setIcon(new javax.swing.ImageIcon(getClass().getResource("/frame.jpg"))); // NOI18N
        start.add(frame_start);
        frame_start.setBounds(0, 0, 720, 780);

        main_pan.add(start);
        start.setBounds(370, -1, 720, 790);

        about_pannel.setLayout(null);

        menu_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu.jpg"))); // NOI18N
        menu_about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_aboutMouseClicked(evt);
            }
        });
        about_pannel.add(menu_about);
        menu_about.setBounds(10, 30, 50, 50);

        abt_short.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abt-1--1-.png"))); // NOI18N
        about_pannel.add(abt_short);
        abt_short.setBounds(0, 10, 60, 90);

        abt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/abt(1).png"))); // NOI18N
        about_pannel.add(abt);
        abt.setBounds(60, 10, 560, 90);

        team_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/team_logo(1).jpg"))); // NOI18N
        about_pannel.add(team_logo);
        team_logo.setBounds(190, 170, 250, 210);

        topbar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_screen 1(1).jpg"))); // NOI18N
        about_pannel.add(topbar1);
        topbar1.setBounds(0, 0, 620, 10);

        jay_txt.setFont(new java.awt.Font("Algerian", 3, 24)); // NOI18N
        jay_txt.setText("AJAY  CHODANKAR");
        about_pannel.add(jay_txt);
        jay_txt.setBounds(210, 380, 250, 40);

        abhi_txt.setFont(new java.awt.Font("Algerian", 3, 24)); // NOI18N
        abhi_txt.setText("ABHISHEK BHINGLE");
        about_pannel.add(abhi_txt);
        abhi_txt.setBounds(210, 420, 270, 30);

        viru_txt.setFont(new java.awt.Font("Algerian", 3, 24)); // NOI18N
        viru_txt.setText("VIRESH  FEGADE");
        about_pannel.add(viru_txt);
        viru_txt.setBounds(210, 460, 300, 30);

        n_logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/name_frame-1-.jpg"))); // NOI18N
        about_pannel.add(n_logo);
        n_logo.setBounds(0, 100, 620, 535);

        main_pan.add(about_pannel);
        about_pannel.setBounds(420, 70, 620, 635);

        wod.setLayout(null);

        meanSpeak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/speak_icon-3-.png"))); // NOI18N
        meanSpeak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                meanSpeakActionPerformed(evt);
            }
        });
        wod.add(meanSpeak);
        meanSpeak.setBounds(470, 310, 50, 50);

        wordSpeak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/speak_icon-3-.png"))); // NOI18N
        wordSpeak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wordSpeakActionPerformed(evt);
            }
        });
        wod.add(wordSpeak);
        wordSpeak.setBounds(320, 240, 50, 50);

        close.setText("CLOSE");
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });
        wod.add(close);
        close.setBounds(250, 410, 90, 30);

        wod_meaning.setEditable(false);
        wod_meaning.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        wod_meaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wod_meaningActionPerformed(evt);
            }
        });
        wod.add(wod_meaning);
        wod_meaning.setBounds(90, 310, 360, 50);

        wod_word.setEditable(false);
        wod_word.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        wod_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wod_wordActionPerformed(evt);
            }
        });
        wod.add(wod_word);
        wod_word.setBounds(90, 240, 210, 50);

        disp_random.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dispWod.jpg"))); // NOI18N
        wod.add(disp_random);
        disp_random.setBounds(80, 200, 450, 280);

        wod_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blur_image(1).jpg"))); // NOI18N
        wod.add(wod_bg);
        wod_bg.setBounds(0, 0, 620, 635);

        main_pan.add(wod);
        wod.setBounds(420, 70, 620, 635);

        slide_bar_panAbout.setLayout(null);

        home_about.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        home_about.setText("HOME");
        home_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                home_aboutActionPerformed(evt);
            }
        });
        slide_bar_panAbout.add(home_about);
        home_about.setBounds(0, 160, 320, 40);

        wod_about.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        wod_about.setText("WORD OF THE DAY");
        wod_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wod_aboutActionPerformed(evt);
            }
        });
        slide_bar_panAbout.add(wod_about);
        wod_about.setBounds(0, 240, 320, 40);

        exit_about.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        exit_about.setText("EXIT");
        exit_about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exit_aboutActionPerformed(evt);
            }
        });
        slide_bar_panAbout.add(exit_about);
        exit_about.setBounds(0, 320, 320, 40);

        menu_txt_about.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        menu_txt_about.setForeground(new java.awt.Color(255, 255, 255));
        menu_txt_about.setText("MENU");
        slide_bar_panAbout.add(menu_txt_about);
        menu_txt_about.setBounds(120, 70, 130, 60);

        sbar_about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbar.jpg"))); // NOI18N
        slide_bar_panAbout.add(sbar_about);
        sbar_about.setBounds(0, 0, 320, 635);

        remove_menuAbout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blur_image.jpg"))); // NOI18N
        remove_menuAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_menuAboutMouseClicked(evt);
            }
        });
        slide_bar_panAbout.add(remove_menuAbout);
        remove_menuAbout.setBounds(320, 0, 300, 635);

        main_pan.add(slide_bar_panAbout);
        slide_bar_panAbout.setBounds(420, 70, 620, 635);

        slide_bar_panHome.setLayout(null);

        random.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        random.setText("WORD OF THE DAY");
        random.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomActionPerformed(evt);
            }
        });
        slide_bar_panHome.add(random);
        random.setBounds(0, 240, 320, 40);

        exit.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });
        slide_bar_panHome.add(exit);
        exit.setBounds(0, 320, 320, 40);

        about.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        about.setText("ABOUT US");
        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });
        slide_bar_panHome.add(about);
        about.setBounds(0, 160, 320, 40);

        menu_txt.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        menu_txt.setForeground(new java.awt.Color(255, 255, 255));
        menu_txt.setText("MENU");
        slide_bar_panHome.add(menu_txt);
        menu_txt.setBounds(120, 70, 90, 40);

        sbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sbar.jpg"))); // NOI18N
        slide_bar_panHome.add(sbar);
        sbar.setBounds(0, 0, 320, 640);

        remove_menu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/blur_image.jpg"))); // NOI18N
        remove_menu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                remove_menuMouseClicked(evt);
            }
        });
        slide_bar_panHome.add(remove_menu);
        remove_menu.setBounds(320, 0, 300, 635);

        main_pan.add(slide_bar_panHome);
        slide_bar_panHome.setBounds(420, 70, 620, 635);

        home_pan.setLayout(null);

        topbar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_screen 1(1).jpg"))); // NOI18N
        home_pan.add(topbar);
        topbar.setBounds(0, 0, 620, 10);

        search.setLayout(null);

        speak_meaning.setIcon(new javax.swing.ImageIcon(getClass().getResource("/speak_icon-3-(1).png"))); // NOI18N
        speak_meaning.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speak_meaningActionPerformed(evt);
            }
        });
        search.add(speak_meaning);
        speak_meaning.setBounds(530, 210, 70, 70);

        speak_word.setIcon(new javax.swing.ImageIcon(getClass().getResource("/speak_icon-3-.png"))); // NOI18N
        speak_word.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                speak_wordActionPerformed(evt);
            }
        });
        search.add(speak_word);
        speak_word.setBounds(390, 30, 50, 50);

        meaning1.setEditable(false);
        meaning1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        search.add(meaning1);
        meaning1.setBounds(10, 170, 510, 140);

        Search.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/search_icon-1-(1).jpg"))); // NOI18N
        Search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchActionPerformed(evt);
            }
        });
        search.add(Search);
        Search.setBounds(450, 30, 160, 50);

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
        word.setBounds(20, 30, 350, 50);

        search_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tab(1).jpg"))); // NOI18N
        search.add(search_bg);
        search_bg.setBounds(0, 0, 620, 500);

        home_tab.addTab("SEARCH", search);

        insert.setLayout(null);

        insert_txt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        insert_txt.setForeground(new java.awt.Color(255, 255, 255));
        insert_txt.setText("INSERT");
        insert.add(insert_txt);
        insert_txt.setBounds(270, 250, 90, 30);

        op.setEditable(false);
        op.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        op.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        op.setBorder(null);
        op.setName(""); // NOI18N
        op.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                opActionPerformed(evt);
            }
        });
        insert.add(op);
        op.setBounds(100, 330, 400, 40);

        insertIn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/white insert.png"))); // NOI18N
        insertIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertInActionPerformed(evt);
            }
        });
        insert.add(insertIn);
        insertIn.setBounds(200, 240, 160, 50);

        wordIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        wordIn.setForeground(new java.awt.Color(204, 204, 204));
        wordIn.setText("Enter Word Here");
        wordIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wordInFocusGained(evt);
            }
        });
        wordIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                wordInKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                wordInKeyReleased(evt);
            }
        });
        insert.add(wordIn);
        wordIn.setBounds(100, 70, 420, 50);

        meanIn.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        meanIn.setForeground(new java.awt.Color(204, 204, 204));
        meanIn.setText("Enter Meaning Here");
        meanIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                meanInFocusGained(evt);
            }
        });
        meanIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                meanInKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                meanInKeyReleased(evt);
            }
        });
        insert.add(meanIn);
        meanIn.setBounds(100, 140, 420, 80);

        bg_insert.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tab(1).jpg"))); // NOI18N
        bg_insert.setText("jabel2");
        insert.add(bg_insert);
        bg_insert.setBounds(0, 0, 620, 490);

        home_tab.addTab("INSERT", insert);

        see_all_words.setLayout(null);

        txt.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt.setForeground(new java.awt.Color(204, 204, 204));
        txt.setText("Enter Word Here");
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

        saw_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tab(1).jpg"))); // NOI18N
        see_all_words.add(saw_bg);
        saw_bg.setBounds(0, 0, 620, 500);

        home_tab.addTab("SEE ALL WORDS", see_all_words);

        delete.setLayout(null);

        result.setEditable(false);
        result.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        result.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        delete.add(result);
        result.setBounds(40, 250, 500, 60);

        dword.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dword.setForeground(new java.awt.Color(204, 204, 204));
        dword.setText("Enter Word Here");
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
        dword.setBounds(30, 85, 340, 60);

        dbutton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/delete fixed.jpg"))); // NOI18N
        dbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dbuttonActionPerformed(evt);
            }
        });
        delete.add(dbutton);
        dbutton.setBounds(400, 90, 160, 50);

        tab_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tab(1).jpg"))); // NOI18N
        delete.add(tab_bg);
        tab_bg.setBounds(0, 0, 620, 500);

        home_tab.addTab("DELETE", delete);
        delete.getAccessibleContext().setAccessibleName("");

        home_pan.add(home_tab);
        home_tab.setBounds(0, 110, 620, 540);
        home_tab.getAccessibleContext().setAccessibleName("SEARCH");

        menu_homeTab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menu.jpg"))); // NOI18N
        menu_homeTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_homeTabMouseClicked(evt);
            }
        });
        home_pan.add(menu_homeTab);
        menu_homeTab.setBounds(20, 30, 50, 50);

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dict_logo1 (1).jpg"))); // NOI18N
        home_pan.add(logo);
        logo.setBounds(0, 10, 620, 100);

        main_pan.add(home_pan);
        home_pan.setBounds(420, 70, 620, 635);

        loading.setLayout(null);

        loading_txt.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        loading_txt.setForeground(new java.awt.Color(255, 255, 255));
        loading_txt.setText("LOADING");
        loading.add(loading_txt);
        loading_txt.setBounds(240, 510, 210, 60);

        loading_gif.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loading_gif(1).gif"))); // NOI18N
        loading.add(loading_gif);
        loading_gif.setBounds(0, 0, 620, 635);

        main_pan.add(loading);
        loading.setBounds(420, 80, 620, 625);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bg.jpg"))); // NOI18N
        main_pan.add(bg);
        bg.setBounds(0, 0, 1400, 790);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_pan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1393, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main_pan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 786, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordActionPerformed
      
    }//GEN-LAST:event_wordActionPerformed

    private void SearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchActionPerformed
       
                wmean(word.getText()); //displaying meaning of word search
   
    }//GEN-LAST:event_SearchActionPerformed

    private void wordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordKeyReleased
      
         if(word.getText().isEmpty()==true)
        {
            word.setText("Enter Word Here");
            word.setCaretPosition(0);
            word.setForeground(new java.awt.Color(204, 204, 204));
        }

    }//GEN-LAST:event_wordKeyReleased

    private void wordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordKeyPressed
       
         if(word.getForeground()!=Color.BLACK)
        {
            if(word.getText().equals("Enter Word Here"))
            {
                word.setText("");
            }
            word.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_wordKeyPressed
  
   //filtering data in jTable
    public void filter_v(String query)
    {
      
        if(txt.getText().length()!=15) //to avoid comparing "enter word here"
        {  
        int cw=query.length();
        DefaultTableModel model = (DefaultTableModel) traversal.getModel();
        model.setRowCount(0); 
        
        Node t= head;
       
            while(t!=null)
            {
              
                 String wo1; 
                 String wo2=t.word; 
                 int len=wo2.length();
                 System.out.println(t.word);
                 if(len>=cw) //to avoid Array index out of bound error
                 {
                 wo1=wo2.substring(0,cw); 
                 System.out.println(cw);                  
                 System.out.println(wo1);   
                 System.out.println(query);   
           
                    if(query.equals(wo1))
                    {
                    
                      Object[] rowData = new Object[2];
                      rowData[0]=t.word;
                      rowData[1]=t.meaning;
                      dm.addRow(rowData);
                    
                    }
                  }
                t=t.next;
            }
          }     
    }
          
  

    
    private void wordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordFocusGained
       
         word.setCaretPosition(0);
    }//GEN-LAST:event_wordFocusGained

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased
       
         if(txt.getText().isEmpty()==true)
        {
          
            txt.setText("Enter Word Here");
            txt.setCaretPosition(0);
            txt.setForeground(new java.awt.Color(204, 204, 204));
         
        }
        
       String query =txt.getText().toLowerCase();
     
        filter_v(query);
     
        int y=txt.getText().length();
     
        if(y==15)
        {
          DefaultTableModel model = (DefaultTableModel) traversal.getModel();
            model.setRowCount(0);
             model.setColumnCount(0); 
            
             CreateColumns();
           
             Populate(); 
        }  
    }//GEN-LAST:event_txtKeyReleased

    private void txtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyPressed
      
         if(txt.getForeground()!=Color.BLACK)
        {
            if(txt.getText().equals("Enter Word Here"))
            {
                txt.setText("");
            }
            txt.setForeground(Color.BLACK);
        }

    }//GEN-LAST:event_txtKeyPressed

    private void txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusGained
        
         txt.setCaretPosition(0);
    }//GEN-LAST:event_txtFocusGained

    private void insertInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertInActionPerformed
          try {
              
              Node t=head;
              int check = 0;
              while(t!=null)
              {
                  if(wordIn.getText().compareToIgnoreCase(t.word)==0)
                  {
                      op.setText("Word Already Exist");
                      check++;
                      break;
                  }
                  t=t.next;
              }
              if(check==0)
              {
                  insertSp(wordIn.getText(),meanIn.getText());
                  op.setText("Word Inserted Succesfully");
              }
                            
          } catch (SQLException ex) {
              Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
          }
        
    }//GEN-LAST:event_insertInActionPerformed

    private void dwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dwordActionPerformed
   
    }//GEN-LAST:event_dwordActionPerformed

    private void dwordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dwordKeyReleased
       
         if(dword.getText().isEmpty()==true)
        {
            dword.setText("Enter Word Here");
            dword.setCaretPosition(0);
            dword.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_dwordKeyReleased

    private void dwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dwordKeyPressed
       
         if(dword.getForeground()!=Color.BLACK)
        {
            if(dword.getText().equals("Enter Word Here"))
            {
                dword.setText("");
            }
            dword.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_dwordKeyPressed
    public void delete()    //deleting word from linked list
    {
        Node p=head;
        Node q=head;
        String delword=dword.getText();
     
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
        
        delete();
    }//GEN-LAST:event_dbuttonActionPerformed
  
    private void menu_homeTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_homeTabMouseClicked
        
        appHomeC();
        slideBarHomeS();
    
    }//GEN-LAST:event_menu_homeTabMouseClicked

    private void remove_menuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_menuMouseClicked
     
     slideBarHomeC();
     appHomeS();
    }//GEN-LAST:event_remove_menuMouseClicked
public void updateDB()  //updating database after inserting or deleting
{
     try {
             
              Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dict?zeroDateTimeBehavior=convertToNull", "root", "viresh");
              Statement st=con.createStatement();
        
             java.sql.PreparedStatement ps= con.prepareStatement(" DELETE FROM dictionary ");
             ps.executeUpdate();   
             
             Node t=head;
             int h=1;
             while(t!=null)
             {  
             java.sql.PreparedStatement s1=con.prepareStatement("insert into DICTIONARY(id,word,meaning) values(?,?,?)");
           
             s1.setInt(1,h);
             h++;  
          
             s1.setString(2,t.word);
             s1.setString(3,t.meaning);
             s1.executeUpdate();
             t=t.next;
            } 
       
              
        } catch (SQLException ex) {
              Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
          }
    
}
    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
         
         updateDB();
         appHomeC();
         slideBarHomeC();

    }//GEN-LAST:event_exitActionPerformed
    public static final String VOICENAME="kevin16";
    private void speak_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speak_wordActionPerformed
    System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      Voice v;
      VoiceManager vm=VoiceManager.getInstance();
    
      v = vm.getVoice(VOICENAME);
      v.allocate(); 
      v.speak(word.getText());
      
    }//GEN-LAST:event_speak_wordActionPerformed

    private void speak_meaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_speak_meaningActionPerformed
       
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      Voice v;
      VoiceManager vm=VoiceManager.getInstance();
    
      v = vm.getVoice(VOICENAME);
      v.allocate(); 
      v.speak(meaning1.getText());

    }//GEN-LAST:event_speak_meaningActionPerformed

    private void wordInFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wordInFocusGained
      
       wordIn.setCaretPosition(0); 
    }//GEN-LAST:event_wordInFocusGained

    private void wordInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordInKeyPressed
      
        if(wordIn.getForeground()!=Color.BLACK)
        {
            if(wordIn.getText().equals("Enter Word Here"))
            {
                wordIn.setText("");
            }
            wordIn.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_wordInKeyPressed

    private void wordInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_wordInKeyReleased
       
          if(wordIn.getText().isEmpty()==true)
        {
            wordIn.setText("Enter Word Here");
            wordIn.setCaretPosition(0);
            wordIn.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_wordInKeyReleased

    private void meanInFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_meanInFocusGained
      
         meanIn.setCaretPosition(0); 
    }//GEN-LAST:event_meanInFocusGained

    private void meanInKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_meanInKeyPressed
       
         if(meanIn.getForeground()!=Color.BLACK)
        {
            if(meanIn.getText().equals("Enter Meaning Here"))
            {
                meanIn.setText("");
            }
            meanIn.setForeground(Color.BLACK);
        }
    }//GEN-LAST:event_meanInKeyPressed

    private void meanInKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_meanInKeyReleased
       
          if(meanIn.getText().isEmpty()==true)
        {
            meanIn.setText("Enter Word Here");
            meanIn.setCaretPosition(0);
            meanIn.setForeground(new java.awt.Color(204, 204, 204));
        }
    }//GEN-LAST:event_meanInKeyReleased

    private void dwordFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dwordFocusGained
      
         dword.setCaretPosition(0); 
    }//GEN-LAST:event_dwordFocusGained

    private void opActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_opActionPerformed
       
    }//GEN-LAST:event_opActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
       
        slideBarHomeC();
        aboutPanS();
  
         
    }//GEN-LAST:event_aboutActionPerformed

    private void randomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomActionPerformed
      
        slideBarHomeC();
        
        wodS();
        randomWord();
    }//GEN-LAST:event_randomActionPerformed

    private void menu_aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menu_aboutMouseClicked
        
        aboutPanC();
        slideBarAboutS();
    }//GEN-LAST:event_menu_aboutMouseClicked

    private void home_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_home_aboutActionPerformed
      
        slideBarAboutC();
        appHomeS();

    }//GEN-LAST:event_home_aboutActionPerformed

    private void exit_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exit_aboutActionPerformed
     
        updateDB();
        slideBarAboutC();
        appHomeC();
        
    }//GEN-LAST:event_exit_aboutActionPerformed

    private void app_iconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_app_iconMouseClicked
      
        loadingS();
 
        new java.util.Timer().schedule( 
        new java.util.TimerTask() {
        @Override
        public void run() {
                loadingC();
                appHomeS();
            }
        }, 
        3000 
);
   
        
        
    }//GEN-LAST:event_app_iconMouseClicked

    private void remove_menuAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_remove_menuAboutMouseClicked
      
        slideBarAboutC();
        aboutPanS();
    }//GEN-LAST:event_remove_menuAboutMouseClicked

    private void wod_wordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wod_wordActionPerformed
     
    }//GEN-LAST:event_wod_wordActionPerformed

    private void wod_meaningActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wod_meaningActionPerformed
       
    }//GEN-LAST:event_wod_meaningActionPerformed

    private void wordSpeakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wordSpeakActionPerformed
       
      System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      Voice v;
      VoiceManager vm=VoiceManager.getInstance();
    
      v = vm.getVoice(VOICENAME);
      v.allocate(); 
      v.speak(wod_word.getText());
    }//GEN-LAST:event_wordSpeakActionPerformed

    private void wod_aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wod_aboutActionPerformed
       
        slideBarAboutC();
        wodS();
        randomWord();
    }//GEN-LAST:event_wod_aboutActionPerformed

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed
       
        wodC();
        appHomeS();
    }//GEN-LAST:event_closeActionPerformed

    private void meanSpeakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_meanSpeakActionPerformed
      
      System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
      Voice v;
      VoiceManager vm=VoiceManager.getInstance();
     
      v = vm.getVoice(VOICENAME);
      v.allocate(); 
      v.speak(wod_meaning.getText());   
    }//GEN-LAST:event_meanSpeakActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
    public static void main(String args[]) throws SQLException {
     
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
                    
     
                } catch (SQLException ex) {
                    Logger.getLogger(options.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Search;
    private javax.swing.JLabel abhi_txt;
    private javax.swing.JButton about;
    private javax.swing.JPanel about_pannel;
    private javax.swing.JLabel abt;
    private javax.swing.JLabel abt_short;
    private javax.swing.JLabel app_icon;
    private javax.swing.JLabel app_menu;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel bg_insert;
    private javax.swing.JButton close;
    private javax.swing.JButton dbutton;
    private javax.swing.JPanel delete;
    private javax.swing.JLabel disp_random;
    private javax.swing.JTextField dword;
    private javax.swing.JButton exit;
    private javax.swing.JButton exit_about;
    private javax.swing.JLabel frame_start;
    private javax.swing.JButton home_about;
    private javax.swing.JPanel home_pan;
    private javax.swing.JTabbedPane home_tab;
    private javax.swing.JPanel insert;
    private javax.swing.JButton insertIn;
    private javax.swing.JLabel insert_txt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jay_txt;
    private javax.swing.JPanel loading;
    private javax.swing.JLabel loading_gif;
    private javax.swing.JLabel loading_txt;
    private javax.swing.JLabel logo;
    private javax.swing.JPanel main_pan;
    private javax.swing.JTextField meanIn;
    private javax.swing.JButton meanSpeak;
    private javax.swing.JTextField meaning1;
    private javax.swing.JLabel menu_about;
    private javax.swing.JLabel menu_homeTab;
    private javax.swing.JLabel menu_txt;
    private javax.swing.JLabel menu_txt_about;
    private javax.swing.JLabel n_logo;
    private javax.swing.JTextField op;
    private javax.swing.JButton random;
    private javax.swing.JLabel remove_menu;
    private javax.swing.JLabel remove_menuAbout;
    private javax.swing.JTextField result;
    private javax.swing.JLabel saw_bg;
    private javax.swing.JLabel sbar;
    private javax.swing.JLabel sbar_about;
    private javax.swing.JPanel search;
    private javax.swing.JLabel search_bg;
    private javax.swing.JPanel see_all_words;
    private javax.swing.JPanel slide_bar_panAbout;
    private javax.swing.JPanel slide_bar_panHome;
    private javax.swing.JButton speak_meaning;
    private javax.swing.JButton speak_word;
    private javax.swing.JPanel start;
    private javax.swing.JLabel tab_bg;
    private javax.swing.JLabel team_logo;
    private javax.swing.JLabel topbar;
    private javax.swing.JLabel topbar1;
    private javax.swing.JTable traversal;
    private javax.swing.JTextField txt;
    private javax.swing.JLabel viru_txt;
    private javax.swing.JPanel wod;
    private javax.swing.JButton wod_about;
    private javax.swing.JLabel wod_bg;
    private javax.swing.JTextField wod_meaning;
    private javax.swing.JTextField wod_word;
    private javax.swing.JTextField word;
    private javax.swing.JTextField wordIn;
    private javax.swing.JButton wordSpeak;
    // End of variables declaration//GEN-END:variables
}
