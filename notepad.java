import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class notepad implements ActionListener{


    public static void main(String[] args){
        //creating new JFrame.

        JFrame frame = new JFrame();
        JMenuBar menubar = new JMenuBar();
        JTextArea textarea = new JTextArea();
        JScrollPane scrollpane = new JScrollPane(textarea);
        UndoManager u = new UndoManager();

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
        JMenu help = new JMenu("Help");

        //creating menuitems

        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem cut  = new JMenuItem("Cut");
        JMenuItem paste = new JMenuItem("Paste"); 
        JMenuItem undo  = new JMenuItem("Undo");
        JMenuItem redo  = new JMenuItem("Redo");
        JMenuItem save  = new JMenuItem("Save");
        JMenuItem open  = new JMenuItem("Open File...");
        JMenuItem exit  = new JMenuItem("Exit");
        JMenuItem find  = new JMenuItem("Find");
        JMenuItem replace  = new JMenuItem("Replace");
        JMenuItem selectall  = new JMenuItem("Select All");
        JMenuItem newtab  = new JMenuItem("New Tab");
        JMenuItem saveas  = new JMenuItem("Save As");
        JMenuItem print = new JMenuItem("Print");
        JMenuItem closetab = new JMenuItem("Close Tab");
        JMenuItem zoomin = new JMenuItem("Zoom In");
        JMenuItem zoomout = new JMenuItem("Zoom Out");
        JMenuItem resetzoom = new JMenuItem("Reset Zoom");
        JMenuItem texthighlight = new JMenuItem("Highlight Text");
        JMenu fontstyle = new JMenu("Font Style");
        JMenu fontsize = new JMenu("Font Size");
        JMenu textcolor = new JMenu("Text Color");
        JMenu mode = new JMenu("Mode");

        menubar.add(file);
        menubar.add(edit);
        menubar.add(format);
        menubar.add(view);
        menubar.add(help);
        

        edit.add(undo);
        edit.add(redo);
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(find);
        edit.add(replace);
        edit.add(selectall);

        file.add(newtab);
        file.add(open);
        file.add(save);
        file.add(saveas);
        file.add(print);
        file.add(closetab);
        file.add(exit);

        view.add(zoomin);
        view.add(zoomout);
        view.add(resetzoom);
        view.add(mode);

        format.add(texthighlight);
        format.add(fontsize);
        format.add(fontstyle);
        format.add(textcolor);

        // we will explicitly add all functionalities using lambda functions

        //copy function
        copy.addActionListener(e -> {
            textarea.copy();
        });

        // cut function
        cut.addActionListener(e -> {
            textarea.cut();
        });

        // created undo functionality using undo manager
        undo.addActionListener(e -> {
            u.undo();
        });

        // created a new redo functionality using undo manager
        redo.addActionListener(e -> {
            u.redo();
        });

        paste.addActionListener(e -> {
            textarea.paste();
        });

        find.addActionListener(e -> {
            
        });

        replace.addActionListener(e -> {
            
        });

        selectall.addActionListener(e -> {
            textarea.selectAll();
            
        });

        newtab.addActionListener(e -> {
           
        });

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

        save.addActionListener(e -> {

            
        });

        print.addActionListener(e -> {
            try {
                textarea.print();
            } catch (PrinterException e1) {
                
                e1.printStackTrace();
            }
            
        });

        closetab.addActionListener(e -> {
            
        });

        exit.addActionListener(e -> {
            System.exit(0);
            
        });

        zoomin.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = current_size.getSize() + 2;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
           
        });

        zoomout.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = current_size.getSize() - 2;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
            
        });

        resetzoom.addActionListener(e -> {
            Font current_size = textarea.getFont();
            int new_size = 20;
            Font new_font = current_size.deriveFont((float) new_size);
            textarea.setFont(new_font);
           
        });

        texthighlight.addActionListener(e -> {
            Highlighter highlighter = textarea.getHighlighter();
            Highlighter.HighlightPainter texthiglhlight = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
            try {
                int start = textarea.getSelectionStart(); // Start position to highlight
                int end = textarea.getSelectionEnd();   // End position to highlight
                highlighter.addHighlight(start, end, texthiglhlight);
        }   catch (BadLocationException E) {
                E.printStackTrace();
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
        


        //setting default font size and font style for textarea

        textarea.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));

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
     // Now lets start adding functionalities

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("success");
    }


}