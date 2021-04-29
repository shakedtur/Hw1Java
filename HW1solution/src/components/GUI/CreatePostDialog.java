package components.GUI;

import components.MainOffice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import javax.swing.*;

/*
*
*
* AddAnimalDialog
* */

public class CreatePostDialog extends JDialog {
public MainOffice mainOfficeGame;
private CreatePostPanel createPostPanel;
private JPanel dialogPanl;
private JPanel buttonsPnl;

private JSlider brnchslider;
private int brunchnum;

private JSlider truckslider;
private int trucksnum;

private JSlider packslider;
private int packagenum;


private JButton okButton;
private JButton cancelButton;

//Ctor dialog
    public CreatePostDialog(CreatePostPanel createPostPanel){
        setTitle("Create Post system dialog");
        this.createPostPanel=createPostPanel;
        dialogPanl=new JPanel();
        dialogPanl.setVisible(true);

        buttonsPnl =new JPanel();
        //setSize(600,400);
        //setLocationRelativeTo(null);
        //constructAddAnimalPanel();

        //-------Slider--------
        //brunch slider
        dialogPanl.setLayout(new BoxLayout(dialogPanl,BoxLayout.Y_AXIS));
        brnchslider=new JSlider(JSlider.HORIZONTAL,1,10,5);
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
        truckslider=new JSlider(JSlider.HORIZONTAL,1,10,4);
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

