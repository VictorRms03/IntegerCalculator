/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Victor Ramos
 */
public class CalculatorFrame extends JFrame {
    
    private final Calculator calculator = new Calculator();
    
    public CalculatorFrame() {
        
        frameConfig();
        final JLabel numbersAreaLabel = numbersAreaConfig();
        buttonsAreaConfig( numbersAreaLabel );
        
    }
    
    /**
     * Set Initial Frame Config
     */
    private void frameConfig() {
        
        this.setSize( 415, 590 );
        this.setTitle( "Calculator - by Victor Ramos" );
        this.setIconImage( new ImageIcon( "icon.png" ).getImage() );
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.getContentPane().setBackground( Color.WHITE );
        this.setLayout( null );
        this.setResizable( false );
        
        this.setVisible( true );
        
    }
    
    /**
     * Set JLabel of numbersArena Config
     * @return the JLabel created and configured
     */
    private JLabel numbersAreaConfig() {
        
        JLabel numbersArea = new JLabel();
        updateResultString( numbersArea );
        numbersArea.setFont( new Font( "Arial", Font.PLAIN, 50 ) );  
        numbersArea.setHorizontalAlignment( JLabel.RIGHT );
        numbersArea.setSize( this.getWidth(), 200 );
        numbersArea.setBackground( Color.WHITE );
        numbersArea.setOpaque( true );
        numbersArea.setBounds( 0, 50, this.getWidth()-16 , 100 );
        numbersArea.setBorder( BorderFactory.createLineBorder( Color.BLACK, 2 ) );
        
        this.add( numbersArea );
        
        return numbersArea;
        
    }
    
    /**
     * Set JPanel of Buttons area
     */
    private void buttonsAreaConfig( JLabel numbersAreaLabel ) {
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground( Color.WHITE );
        buttonsPanel.setBounds( 0, 150, this.getWidth()-16, 411 );
        buttonsPanel.setLayout( null );
        this.add( buttonsPanel );
        
        buttonsConfig( buttonsPanel, numbersAreaLabel );
    }
    
    /**
     * Set all buttons of JPanel of Buttons area
     * @param buttonsPanel are the JPanel that buttons are gonna be part
     */
    private void buttonsConfig( JPanel buttonsPanel, JLabel numbersAreaLabel ) {
        
        JButton oneButton = new JButton("1");
        oneButton.setBounds(0, 200, 100, 100);
        oneButton.addActionListener( e -> {
            calculator.addNumber(1);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(oneButton);
        
        JButton twoButton = new JButton("2");
        twoButton.setBounds(100, 200, 100, 100);
        twoButton.addActionListener( e -> {
            calculator.addNumber(2);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(twoButton);
        
        JButton threeButton = new JButton("3");
        threeButton.setBounds(200, 200, 100, 100);
        threeButton.addActionListener( e -> {
            calculator.addNumber(3);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(threeButton);
        
        JButton fourButton = new JButton("4");
        fourButton.setBounds(0, 100, 100, 100);
        fourButton.addActionListener( e -> {
            calculator.addNumber(4);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(fourButton);
        
        JButton fiveButton = new JButton("5");
        fiveButton.setBounds(100, 100, 100, 100);
        fiveButton.addActionListener( e -> {
            calculator.addNumber(5);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(fiveButton);
        
        JButton sixButton = new JButton("6");
        sixButton.setBounds(200, 100, 100, 100);
        sixButton.addActionListener( e -> {
            calculator.addNumber(6);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(sixButton);
        
        JButton sevenButton = new JButton("7");
        sevenButton.setBounds(0, 0, 100, 100);
        sevenButton.addActionListener( e -> {
            calculator.addNumber(7);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(sevenButton);
        
        JButton eightButton = new JButton("8");
        eightButton.setBounds(100, 0, 100, 100);
        eightButton.addActionListener( e -> {
            calculator.addNumber(8);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(eightButton);
        
        JButton nineButton = new JButton("9");
        nineButton.setBounds(200, 0, 100, 100);
        nineButton.addActionListener( e -> {
            calculator.addNumber(9);
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(nineButton);
        
        JButton divideButton = new JButton("/");
        divideButton.setBounds(300, 0, 100, 100);
        divideButton.addActionListener( e -> {
            calculator.setOperator('/');
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(divideButton);
        
        JButton timesButton = new JButton("*");
        timesButton.setBounds(300, 100, 100, 100);
        timesButton.addActionListener( e -> {
            calculator.setOperator('*');
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(timesButton);
        
        JButton minusButton = new JButton("-");
        minusButton.setBounds(300, 200, 100, 100);
        minusButton.addActionListener( e -> {
            calculator.setOperator('-');
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(minusButton);
        
        JButton plusButton = new JButton("+");
        plusButton.setBounds(300, 300, 100, 100);
        plusButton.addActionListener( e -> {
            calculator.setOperator('+');
            updateResultString( numbersAreaLabel );
                });
        buttonsPanel.add(plusButton);

        JButton equalsButton = new JButton("=");
        equalsButton.setBounds(200, 300, 100, 100);
        equalsButton.addActionListener( e -> {
            calculator.calculate();
            updateResultString( numbersAreaLabel );
        });
        buttonsPanel.add(equalsButton);
        
        JButton clearButton = new JButton("CLEAR");
        clearButton.setBounds(100, 300, 100, 100);
        clearButton.addActionListener( e -> {
            calculator.clearAll();
            updateResultString( numbersAreaLabel );
        });
        buttonsPanel.add(clearButton);
    };
    
    /**
     * Updates the result string to the String of the Calculator object
     */
    private void updateResultString( JLabel numbersAreaLabel){
        numbersAreaLabel.setText( calculator.getCurrentResultText() );
    }
}
