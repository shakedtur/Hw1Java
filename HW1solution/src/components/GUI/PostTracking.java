package components.GUI;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * An object of this department manages the entire system, operates a clock, the branches and vehicles
 * creates the packages (simulates customers) and transfers them to the appropriate branches.
 * @version 2.0 09 April 2011
 * @author  Shaked Turgeman 313276859, Lior Daichman 316005347
 */
public class PostTracking extends JFrame implements ActionListener {

    //valuse
    private JPanel buttonRow;
    CreatePostDialog createPostDialog;
    private CreatePostPanel panel;
    private JButton[] btmButtons;
    private String[] btmNameStrings={"Create System","start","stop","Resume","All packages info","Branch info"};

    //ctor
    public PostTracking(){
        super("Post tracking system");

        setSize(1200,700);
        buttonRowCreator();
        add(buttonRow,BorderLayout.SOUTH);
        setLocationRelativeTo(null);//open screen in center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        createPostDialog=new CreatePostDialog(panel);
        panel=new CreatePostPanel(this);
    }


    //methods
    private void buttonRowCreator(){
        buttonRow=new JPanel();
        buttonRow.setLayout(new FlowLayout());
        buttonRow.setBackground(Color.RED);//choose color
        btmButtons=new JButton[btmNameStrings.length];
        for(int i=0;i< btmButtons.length;i++){
            btmButtons[i]=new JButton(btmNameStrings[i]);
            btmButtons[i].addActionListener(this);
            buttonRow.add(btmButtons[i]);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btmButtons[0]){
            //TODO
            createPostDialog.setVisible(true);


        }
    }
}
