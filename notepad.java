import javax.swing.*;

public class notepad{

    public static void main(String[] args){
        //creating new JFrame.

        JFrame frame = new JFrame();
        JMenuBar menubar = new JMenuBar();
        JTextArea textarea = new JTextArea();
        JScrollPane scrollpane = new JScrollPane(textarea);
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

        
        // we will add it using scroll pane(so that we can add scroll bar feature)
        
        frame.add(scrollpane);
        frame.setVisible(true);
        
        




    }




}