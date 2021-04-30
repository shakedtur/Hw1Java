package components.GUI;
import components.MainOffice;
import components.Package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

/**
 * An object of this department manages the entire system, operates a clock, the branches and vehicles
 * creates the packages (simulates customers) and transfers them to the appropriate branches.
 * @version 2.0 09 April 2021
 * @author  Shaked Turgeman 313276859, Lior Daichman 316005347
 */
public class PostTracking extends JFrame implements ActionListener {

    //valuse
    private JPanel buttonRow;
    private JPanel bourdpnl;
    CreatePostDialog createPostDialog;
    MainOffice starGame;
    private boolean flagStart=false;
    private CreatePostPanel panel;
    private JButton[] btmButtons;
    private String[] btmNameStrings={"Create System","start","stop","Resume","All packages info","Branch info"};
    private int brunches,trucks,packages;
    //all package table info
    private JTable infoPackTable;
    private JScrollPane scrollPane;
    private int mousecount=0;
    private static final String[] columnNames={"Package Id","Sender","Destination","Priority","Status","Type"};
    //MainOffice game;

    //ctor
    public PostTracking(){
        super("Post tracking system");

        setSize(1200,700);
        bourdpnl=new JPanel();
        buttonRowCreator();
        add(new FillWithGraphics());
        add(bourdpnl,BorderLayout.NORTH);
        add(buttonRow,BorderLayout.SOUTH);
        setLocationRelativeTo(null);//open screen in center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        createPostDialog=new CreatePostDialog(panel);
        createPostDialog.setSize(400,600);
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

    public void setFlagStart(boolean flagStart) {
        this.flagStart = flagStart;
    }
    public class FillWithGraphics extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            g.fillOval(30, 30, 20, 50);

            g.setColor(Color.magenta);
            g.fillRect(60, 60, 35, 35);

            g.setColor(Color.yellow);
            g.fillOval(150, 50, 30, 30);
            g.setColor(Color.red);
            g.drawOval(150, 50, 30, 30);

            g.setColor(Color.red);
            g.drawOval(150, 100, 30, 30);
            g.setColor(Color.yellow);
            g.fillOval(150, 100, 30, 30);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==btmButtons[0]){//create system button
            //TODO
            createPostDialog.setVisible(true);
            setFlagStart(true);
        }

            if (e.getSource() == btmButtons[1]) {//start button
                starGame = createPostDialog.getMainOfficeGame();
                brunches = createPostDialog.getBrunchnum();
                trucks = createPostDialog.getTrucksnum();
                packages = createPostDialog.getPackagenum();

                //drowBoard()
                if (flagStart) {
                    flagStart = false;
                    starGame.play(60);
                } else {
                    JOptionPane.showMessageDialog(this, "the System already started", ":)", JOptionPane.NO_OPTION);
                }
            }
        if(starGame!=null) {//protect the buttons just if the game started
            if (e.getSource() == btmButtons[4]) {//package table
                mousecount++;
                if (mousecount % 2 != 0) {
                    tableCtor();
                    scrollPane = new JScrollPane(infoPackTable);
                    add(scrollPane, BorderLayout.CENTER);
                    repaint();
                    revalidate();
                } else {
                    remove(scrollPane);
                    repaint();
                    revalidate();
                }


            }
        }
    }

    public void tableCtor(){
        infoPackTable=new JTable(rowCtor(),columnNames);

    }
    private Object [][] rowCtor(){
        //TODO neeed to fix paramter in data
        Object[][] data=new Object[starGame.getPackages().size()][columnNames.length];
        ArrayList<Package> packList=starGame.getPackages();
        int i=0;
        for(Package pack: packList){
            int j=0;
            data[i][j++]=pack.getPackageID();
            data[i][j++]=pack.getSenderAddress().toString();
            data[i][j++]=pack.getDestinationAddress().toString();
            data[i][j++]=pack.getPriority().toString();
            data[i][j++]=pack.getStatus().toString();
            data[i++][j++]=pack.toString();

        }



        //        for(int i=0;i<starGame.getPackNumExsist();i++){
//            for(int j=0;j<columnNames.length;j++){
//                data[i][j]="4";
//            }
//        }
        return data;
    }
}
