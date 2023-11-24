// importing necessary libraries
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Timer;
import java .util.TimerTask;




public class notepad implements ActionListener{

    //declarng swing components
    JFrame frame;
    JMenuBar menubar;
    JTextArea textarea;
    JScrollPane scrollpane;
    JTabbedPane tp, tabbedpane;


    public void new_notepad(){
        create_notepad();

    }
   
    public void create_notepad(){

        // creating UI
        frame = new JFrame();
        menubar = new JMenuBar();
        textarea = new JTextArea();
        textarea.requestFocus();
        scrollpane = new JScrollPane(textarea);
        JTextField tfind = new JTextField(15);
        
        JTextField treplace = new JTextField(15);
    
        JPanel panel = new JPanel();
        JButton find  = new JButton("Find");
        JButton replace  = new JButton("Replace");
        JButton count_words = new JButton("Count Words");
        panel.add(new JLabel("Find: "));
        panel.add(tfind);
        panel.add(find);

        panel.add(new JLabel("Replace: "));
        panel.add(treplace);
        panel.add(replace);
        panel.add(count_words);
        frame.add(panel, BorderLayout.NORTH);
        UndoManager undoManager;

        undoManager = new UndoManager();
        Document doc = textarea.getDocument();
        doc.addUndoableEditListener(new UndoableEditListener() {
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());
            }
        });

        // adding logo to our project
        ImageIcon logo = new ImageIcon("logo.png");
        frame.setIconImage(logo.getImage());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 800, 600);
        frame.setTitle("KIOKU-PAD");
       
        frame.setJMenuBar(menubar); 

        //Creating new JMenubar and menu items and adding them to the menubar

        
        
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu view = new JMenu("View");
        JMenu format = new JMenu("Format");
        

        //creating menuitems

        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut  = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste"); 
        JMenuItem undo  = new JMenuItem("Undo");
        JMenuItem redo  = new JMenuItem("Redo");
        JMenuItem save  = new JMenuItem("Save");
        JMenuItem open  = new JMenuItem("Open File...");
        JMenuItem exit  = new JMenuItem("Exit");
        JMenuItem selectall  = new JMenuItem("Select All");
        JMenuItem newtab  = new JMenuItem("New Tab");
        JMenuItem saveas  = new JMenuItem("Save As");
        JMenuItem print = new JMenuItem("Print");
        JMenuItem zoomin = new JMenuItem("Zoom In");
        JMenuItem zoomout = new JMenuItem("Zoom Out");
        JMenuItem resetzoom = new JMenuItem("Reset Zoom");
        JMenu texthighlight = new JMenu("Highlight Text");
        JMenu fontstyle = new JMenu("Font Style");
        JMenu fontsize = new JMenu("Font Size");
        
        JMenuItem bulletpoint = new JMenuItem("Add Bulletpoint");
        JMenuItem remove_highlight =  new JMenuItem("Remove Highlight");
        JMenu security = new JMenu("Security");
        JMenuItem encrypt = new JMenuItem("Encrypt Test");
        JMenuItem decrypt = new JMenuItem("Decrypt Test");
        
        JMenu mode = new JMenu("Mode");

        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(view);
        menubar.add(security);
        
        

        edit.add(undo);
        edit.add(redo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        file.add(newtab);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(print);
        file.add(exit);

        view.add(zoomin);
        view.add(zoomout);
        view.add(resetzoom);
        view.add(mode);

        format.add(texthighlight);
        
        format.add(bulletpoint);
        format.add(fontsize);
        format.add(fontstyle);

        security.add(encrypt);
        security.add(decrypt);
       

        // we will explicitly add all functionalities using lambda functions

        //copy function
        copy.addActionListener(e -> {
            textarea.copy();
        });
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));

        // cut function
        cut.addActionListener(e -> {
            textarea.cut();
        });
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));


        // created undo functionality using undo manager
        undo.addActionListener(e -> {
            if (undoManager.canUndo()) {
                undoManager.undo();
            }
            
        });
        undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));

        // created a new redo functionality using undo manager
        redo.addActionListener(e -> {
                            if (undoManager.canRedo()) { 
                    undoManager.redo();
                }
            
        });
        redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));

        paste.addActionListener(e -> {
            textarea.paste();
        });
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));


        // creating find function
        find.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String findWord = tfind.getText();
                String text = textarea.getText();
                int index_of_word = text.indexOf(findWord);
                while(index_of_word != -1){
                    int end = index_of_word + findWord.length();
                    textarea.requestFocus();
                    textarea.select(index_of_word, end);
                    
                    index_of_word = text.indexOf(findWord, end);
                    
                    
                }
            }    
           
        });
        // adding replace function
        replace.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String findWord = tfind.getText();  
                 
                String replaceWord = treplace.getText();  
                
                String text = textarea.getText();
                
                
                if (text.contains(findWord)) {
                    text = text.replaceAll("(?i)" + findWord, replaceWord);
                    textarea.setText(text);
                    
                } else {
                    System.out.println("not found");
                }         
            }
        });
       
        // adding word count functionality
        count_words.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textarea.getText();

                // Count the words
                String[] words = text.split("\\s+");

                int wordCount = words.length;

                // Display word count
                JOptionPane.showMessageDialog(frame, "Word Count: " + wordCount);
            }
        });



        selectall.addActionListener(e -> {
            textarea.selectAll();
            
        });
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));     

        open.addActionListener(e -> {
             JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(filter);



            int action = filechooser.showOpenDialog(null);


            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                if(!filename.contains(".txt")){
                    filename+=".txt";
                } 
                try{
                    BufferedReader reader = new BufferedReader(new FileReader(filename));
                    textarea.read(reader, null);
                }
                catch(IOException ex){
                    ex.printStackTrace();

                }
            
            }});
            open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));

        saveas.addActionListener(e -> {
            JFileChooser filechooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Only Text Files(.txt)", "txt");
            filechooser.setAcceptAllFileFilterUsed(false);
            filechooser.addChoosableFileFilter(filter);



            int action = filechooser.showSaveDialog(null);


            if(action != JFileChooser.APPROVE_OPTION){
                return;
            }
            else{
                String filename = filechooser.getSelectedFile().getAbsolutePath().toString();
                if(!filename.contains(".txt")){
                    filename+=".txt";
                } 
                try{
                    BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
                    textarea.write(writer);
                }
                catch(IOException ex){
                    ex.printStackTrace();

                }
            }
            
        });
        saveas.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK ));

        save.addActionListener(e -> {
            String text = textarea.getText();
            String filename = "untitled" + System.currentTimeMillis() + ".txt";
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
                writer.write(text);

            }
            catch(IOException ex){
                ex.printStackTrace();
            }

            
        });
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));

        print.addActionListener(e -> {
            try {
                textarea.print();
            } catch (PrinterException e1) {
                
                e1.printStackTrace();
            }
            
        });
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));

        exit.addActionListener(e -> {
            System.exit(0);
            
        });

        zoomin.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = current_size.getSize() + 3;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
           
        });
        zoomin.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,ActionEvent.CTRL_MASK));

        zoomout.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = current_size.getSize() - 3;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
            
        });
        zoomout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS,ActionEvent.CTRL_MASK));

        resetzoom.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = 20;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
           
        });

        JMenuItem do_highlight = new JMenuItem("Highlight");

        do_highlight.addActionListener(e -> {
            Highlighter highlighter = textarea.getHighlighter();
            Highlighter.HighlightPainter texthiglhlight = new DefaultHighlighter.DefaultHighlightPainter(Color.PINK);
            try {
                int start = textarea.getSelectionStart(); // Start position to highlight
                int end = textarea.getSelectionEnd();   // End position to highlight
                highlighter.addHighlight(start, end, texthiglhlight);
                
        }   catch (BadLocationException E) {
                E.printStackTrace();
        }
            
                    
        });
            texthighlight.add(do_highlight);
        
        remove_highlight.addActionListener(e ->{
            Highlighter highlighter = textarea.getHighlighter();
            Highlighter.Highlight[] highlights = highlighter.getHighlights();
            if(highlights.length > 0){
                int last_index = highlights.length-1;
                highlighter.removeHighlight(highlights[last_index]);
            }
        });
        texthighlight.add(remove_highlight);

        // we will build encrypt and decrypt functionalties
        // AES accepts 16 24 0r 32 bytes 
        final String secret_key = "1234567890987654";
        final String algorithm = "AES";

        encrypt.addActionListener(e ->{
            try{
                String normal_text = textarea.getText();
                SecretKeySpec secretkey = new SecretKeySpec(secret_key.getBytes(StandardCharsets.UTF_8), algorithm);
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.ENCRYPT_MODE, secretkey);
                byte[] encryptedBytes = cipher.doFinal(normal_text.getBytes(StandardCharsets.UTF_8));
                String encrypted_text = Base64.getEncoder().encodeToString(encryptedBytes);
                textarea.setText(encrypted_text);
            }
            catch(Exception E){
                E.printStackTrace();
            }
  

        });
        encrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0,ActionEvent.CTRL_MASK));

        decrypt.addActionListener(e ->{
            try{
                String encrypted_text = textarea.getText();
                SecretKeySpec secretkey = new SecretKeySpec(secret_key.getBytes(StandardCharsets.UTF_8), algorithm);
                Cipher cipher = Cipher.getInstance(algorithm);
                cipher.init(Cipher.DECRYPT_MODE, secretkey);
                byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encrypted_text));
                String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
                textarea.setText(decryptedText);
            }
            catch(Exception E){
                E.printStackTrace();
            }
            
        });
        decrypt.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,ActionEvent.CTRL_MASK));


        bulletpoint.addActionListener(e -> {
            int caret_position = textarea.getCaretPosition();
            Document document = textarea.getDocument();
            String bullet = "\u2023";
            try{
                document.insertString(caret_position, bullet, null);
            }
            catch(BadLocationException ex){
                ex.printStackTrace();

            }

  
        });

     

        JMenuItem mode1 = new JMenuItem("Light Mode");
        mode1.addActionListener(e -> {
            textarea.setForeground(Color.BLACK);
            textarea.setBackground(Color.WHITE);
            textarea.setCaretColor(Color.BLACK);

        });

        JMenuItem mode2 = new JMenuItem("Dark Mode");
        mode2.addActionListener(e -> {
            textarea.setForeground(Color.WHITE);
            textarea.setBackground(Color.BLACK);
            textarea.setCaretColor(Color.WHITE);

        });
        mode.add(mode1);
        mode.add(mode2);

        // we will create new font styles and sizes;
        Font existingFont = textarea.getFont(); // will give us detail about existing font


        JMenuItem s12 = new JMenuItem("12");
        s12.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 12);
        });
        JMenuItem s14 = new JMenuItem("14");
        s14.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 14);

        });
        JMenuItem s16 = new JMenuItem("16");
        s16.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 16);
        });
        JMenuItem s18 = new JMenuItem("32");
        s18.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 18);
        });
        JMenuItem s20 = new JMenuItem("20");
        s20.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 20);
        });
        JMenuItem s24 = new JMenuItem("24");
        s24.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 24);
        });
        JMenuItem s28 = new JMenuItem("28");
        s28.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 28);
        });
        JMenuItem s32 = new JMenuItem("32");
        s32.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 32);
            
        });
        JMenuItem s36 = new JMenuItem("36");
        s36.addActionListener(e -> {
            updateFont(existingFont.getFamily(), existingFont.getStyle(), 36);
        });

        fontsize.add(s12);
        fontsize.add(s14);
        fontsize.add(s16);
        fontsize.add(s18);
        fontsize.add(s20);
        fontsize.add(s24);
        fontsize.add(s28);
        fontsize.add(s32);
        fontsize.add(s36);

        JMenuItem style1 = new JMenuItem("Serif");
        style1.addActionListener(e -> {
            updateFont(Font.SERIF, existingFont.getStyle(), 20);
   
        });

        JMenuItem style2 = new JMenuItem("Sans-Serif");
        style2.addActionListener(e -> {
            updateFont(Font.SANS_SERIF, existingFont.getStyle(), 20);
        });

        JMenuItem style3 = new JMenuItem("Monospaced");
        style3.addActionListener(e -> {
            updateFont(Font.MONOSPACED, existingFont.getStyle(), 20);
        });

        JMenuItem style4 = new JMenuItem("Dialog");
        style4.addActionListener(e -> {
            updateFont(Font.DIALOG, existingFont.getStyle(), 20);
        });
        JMenuItem style5 = new JMenuItem("Dialog-Input");
        style5.addActionListener(e -> {
            updateFont(Font.DIALOG_INPUT, existingFont.getStyle(), 20);
        });
        fontstyle.add(style1);
        fontstyle.add(style2);
        fontstyle.add(style3);
        fontstyle.add(style4);
        fontstyle.add(style5);

        newtab.addActionListener(e ->{
            new_notepad();
        });
 
     

        // creating a timer for autosave which automatically saves file after 5 mins
        Timer autosave = new Timer(true);
        autosave.schedule(new Auto_save(),0, 5*60*1000 );
        

        //setting default font size and font style for textarea

        int default_size =  20;
        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, default_size));
        textarea.setCaretPosition(0);

        //we dont want horizontal scroll bar,  we only need vertical scroll bar, also we want text wrap as word
        // reaches boundry of notepad
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
    


        // we will add it using scroll pane(so that we can add scroll bar feature)
        
        frame.add(scrollpane);

        
        frame.setVisible(true);


    }
    // autosave functionality
    public class Auto_save extends TimerTask{
        @Override
        public void run(){
            String text = textarea.getText();
            String filename = "untitled" + System.currentTimeMillis() + ".txt";
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
                writer.write(text);

            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        
            
        }
    }
    private void updateFont(String family, int style, int size) {
        Font newFont = new Font(family, style, size);
        textarea.setFont(newFont);
    }



    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new notepad().create_notepad());


   
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("success");
    }

}
