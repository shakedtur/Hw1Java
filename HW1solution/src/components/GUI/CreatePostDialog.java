package components.GUI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.*;
import java.awt.event.ActionListener;

/*
*
*
* AddAnimalDialog
* */

public class CreatePostDialog extends JDialog {

private CreatePostPanel createPostPanel;

private JPanel numberBrunchs;
private JPanel numTrucks2Brunch;
private  JPanel numOfPack;

private JButton okButton;
private JButton cancelButton;

//Ctor dialog
    public CreatePostDialog(CreatePostPanel createPostPanel){
        setTitle("Create Post system dialog");
        this.createPostPanel=createPostPanel;
        numberBrunchs=new JPanel();
        //setSize(600,400);
        //setLocationRelativeTo(null);
        //constructAddAnimalPanel();

        add(numberBrunchs);

        okButton=new JButton("ok");
        //okButton.addActionListener(new OkListener);
        add(okButton);
        pack();
    }




}
