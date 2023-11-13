import javax.swing.*;
import javax.swing.undo.UndoManager;

import java.awt.*;
import java.awt.event.*;

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
        JMenuItem fontstyle = new JMenuItem("Font Style");
        JMenuItem fontsize = new JMenuItem("Font Size");
        JMenuItem textcolor = new JMenuItem("Text Color");

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
            
        });

        newtab.addActionListener(e -> {
           
        });

        open.addActionListener(e -> {
            
        });

        save.addActionListener(e -> {
            
        });

        saveas.addActionListener(e -> {
            
        });

        print.addActionListener(e -> {
            
        });

        closetab.addActionListener(e -> {
            
        });

        exit.addActionListener(e -> {
            
        });

        zoomin.addActionListener(e -> {
           
        });

        zoomout.addActionListener(e -> {
            
        });

        resetzoom.addActionListener(e -> {
           
        });

        texthighlight.addActionListener(e -> {
            
        });

        fontsize.addActionListener(e -> {
            
        });

        fontstyle.addActionListener(e -> {
            
        });

        textcolor.addActionListener(e -> {
            
        });

        



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