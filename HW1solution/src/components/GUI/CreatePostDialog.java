package components.GUI;
import components.GUI.PostTracking;
import components.MainOffice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

/*
*
*
* AddAnimalDialog
* */

public class CreatePostDialog extends JDialog {
public MainOffice mainOfficeGame;

public CreatePostPanel createPostPanel;
public JPanel dialogPanl;
public JPanel buttonsPnl;
public JSlider brnchslider;
public int brunchnum;
public JSlider truckslider;
public int trucksnum;
public JSlider packslider;
public int packagenum;
public JButton okButton;
public JButton cancelButton;

//Ctor dialog
    public CreatePostDialog(CreatePostPanel createPostPanel){
        setTitle("Create Post system dialog");
        this.createPostPanel=createPostPanel;
        
        dialogPanl=new JPanel();
        dialogPanl.setVisible(true);
        buttonsPnl =new JPanel();
        
       
//        createPostPanel.setSize(1200,700);
        //setLocationRelativeTo(null);
        //constructAddAnimalPanel();

        //-------Slider--------
        //brunch slider
        dialogPanl.setLayout(new BoxLayout(dialogPanl,BoxLayout.Y_AXIS));
        brnchslider=new JSlider(JSlider.HORIZONTAL,1,10,1);
        brnchslider.setPaintTicks(rootPaneCheckingEnabled);
        brnchslider.setMinorTickSpacing(1);
        brnchslider.setMajorTickSpacing(1);
        brnchslider.setPaintTicks(true);
        brnchslider.setPaintLabels(true);
        brnchslider.setVisible(true);
        brnchslider.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLoweredBevelBorder(),
                BorderFactory.createEmptyBorder(10,1,5,2)));
        dialogPanl.add(new JLabel("number of brunchs"),BorderLayout.CENTER);
        dialogPanl.add(brnchslider);//adding slider

        //truckslider
        truckslider=new JSlider(JSlider.HORIZONTAL,1,10,2);
        truckslider.setMajorTickSpacing(1);
        truckslider.setPaintTicks(true);
        truckslider.setPaintLabels(true);
        dialogPanl.add(new JLabel("number of trucks per branch"),BorderLayout.CENTER);
        dialogPanl.add(truckslider);

        //add(numberBrunchs,BorderLayout.CENTER);
        //pack slider
        packslider=new JSlider(JSlider.HORIZONTAL,2,20,10);
        packslider.setMajorTickSpacing(2);
        packslider.setPaintTicks(true);
        packslider.setPaintLabels(true);
        dialogPanl.add(new JLabel("number of packages"),BorderLayout.CENTER);
        dialogPanl.add(packslider);

        //add buttons
        okButton=new JButton("ok");
        okButton.addActionListener(new OkListener());
        buttonsPnl.add(okButton);
    //        dialogPanl.add(okButton);
        //cencel button
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(new OkListener());
        buttonsPnl.add(cancelButton);
        //    dialogPanl.add(cancelButton);
        dialogPanl.add(buttonsPnl,BorderLayout.SOUTH);
        add(dialogPanl);
        pack();
    }
    private class OkListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if (mainOfficeGame == null) {
                if (e.getSource() == okButton) {
                    brunchnum = brnchslider.getValue();
                    trucksnum=truckslider.getValue();
                    packagenum=packslider.getValue();
                    mainOfficeGame = new MainOffice(brunchnum, trucksnum, packagenum);//TODO update the number according the slider
                    //JOptionPane.showMessageDialog(createPostPanel, "Success", ":)", JOptionPane.NO_OPTION);
                    setVisible(false);
                } else//cancel prefromnce
                    setVisible(false);
            }
            else{
                setVisible(false);
                JOptionPane.showMessageDialog(createPostPanel, "the System already set", ":)", JOptionPane.NO_OPTION);
            }
        }
    }

    public int getBrunchnum() {
        return brunchnum;
    }

    public int getTrucksnum() {
        return trucksnum;
    }

    public int getPackagenum() {
        return packagenum;
    }

    public MainOffice getMainOfficeGame() {
        return mainOfficeGame;
    }
    

}//end of create postDialog

