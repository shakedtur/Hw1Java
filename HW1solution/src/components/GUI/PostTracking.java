package components.GUI;
import components.MainOffice;

import components.GUI.CreatePostDialog;
import components.Package;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;


/**
 * An object of this department manages the entire system, operates a clock, the branches and vehicles
 * creates the packages (simulates customers) and transfers them to the appropriate branches.
 * @version 2.0 09 April 2021
 * @author  Shaked Turgeman 313276859, Lior Daichman 316005347
 */
public class PostTracking extends JFrame implements ActionListener {
    //valuse
	private int NumberOfBrunchesToDraw = 1;
	private int NumberOfPackToDraw;
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


    //ctor
    public PostTracking(){
        super("Post tracking system");
        setSize(1200,700);
        bourdpnl=new JPanel();
        buttonRowCreator();
        //add(new FillWithGraphics());
        add(bourdpnl,BorderLayout.NORTH);
        add(buttonRow,BorderLayout.SOUTH);
        setLocationRelativeTo(null);//open screen in center
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        createPostDialog=new CreatePostDialog(panel);
        createPostDialog.setSize(400,600);
        panel=new CreatePostPanel(this);
    }
    

	public static final Color DarkGreen = new Color(0,102,0);

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
                NumberOfBrunchesToDraw = brunches;

                add(new FillWithGraphics());


                repaint();
                revalidate();
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



    public class FillWithGraphics extends JPanel {
    	

        @Override
        public void paintComponent(Graphics g) {
            	int YcoordinateBrunches = 30;
            	int YcoordinateLine = 40;
            	int YcoordinateLineDistance = 215;
            	for (int i = 0; i<NumberOfBrunchesToDraw; i++) {
    					g.setColor(Color.cyan);
    					g.fillRect(80, YcoordinateBrunches, 60, 20);
    					g.setColor(Color.green);
    					g.drawLine(120,YcoordinateLine,1100,YcoordinateLineDistance);
    					YcoordinateBrunches = YcoordinateBrunches + 50;
                		YcoordinateLine = YcoordinateLine + 50;
                		YcoordinateLineDistance = YcoordinateLineDistance + 20;
                    	g.setColor(DarkGreen);
                    	g.fillRect(1100, 200, 40, 200);
                	}

                int xLocation=150;
            	for(Package p:starGame.getPackages()){
                    //drawing sender Packages(UP)
                    g.setColor(Color.RED);
                    g.fillOval(xLocation,50,30,30);
                    //drawing destnation Packages(DOWN)
                    g.setColor(Color.blue);
                    g.fillOval(xLocation,500,30,30);
                    xLocation+=getWidth()/starGame.getPackNumExsist();
                }

        }
    }





}