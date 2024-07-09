/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
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
        
        // Initial configures of JFrame
        frameConfig();
        
        // Create the Numbers Area JLabel
        final JLabel numbersAreaLabel = createNumbersArea();
        
        // Create the Buttons Area JPanel
        final JPanel buttonsAreaPanel = createButtonsArea( numbersAreaLabel );
        
        // Add the created components to JFrame
        this.add( numbersAreaLabel );
        this.add( buttonsAreaPanel ); 
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
     * Create the JLabel of numbersArena Config
     * @return the JLabel created and configured
     */
    private JLabel createNumbersArea() {
        
        JLabel numbersArea = new JLabel();
        updateResultString( numbersArea );
        numbersArea.setFont( new Font( "Arial", Font.PLAIN, 50 ) );  
        numbersArea.setHorizontalAlignment( JLabel.RIGHT );
        numbersArea.setSize( this.getWidth(), 200 );
        numbersArea.setBackground( Color.WHITE );
        numbersArea.setOpaque( true );
        numbersArea.setBounds( 0, 50, this.getWidth()-16 , 100 );
        numbersArea.setBorder( BorderFactory.createLineBorder( Color.BLACK, 2 ) );
        
        return numbersArea;
        
    }
    
    /**
     * Set JPanel of Buttons area
     * @param numbersAreaLabel is the Label that the buttons of this area will change Text
     * @return the JPanel created and configured
     */
    private JPanel createButtonsArea( JLabel numbersAreaLabel ) {
        
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground( Color.WHITE );
        buttonsPanel.setBounds( 0, 150, this.getWidth()-16, 411 );
        buttonsPanel.setLayout( null );
        
        final List<JButton> buttonsList = createButtons( numbersAreaLabel );
        for( JButton button : buttonsList ){
            buttonsPanel.add( button );
        }
        
        return buttonsPanel;
    }
    
    /**
     * create all buttons of JPanel of Buttons area
     * @param numbersAreaLabel is the Label that the buttons of this area will change Text
     * @return A list with buttons
     */
    private List<JButton> createButtons( JLabel numbersAreaLabel ) {
        
        List<JButton> buttonsList = new ArrayList<>();
        
        buttonsList.add( createNumberButton( 1, numbersAreaLabel, new Rectangle( 0, 200, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 2, numbersAreaLabel, new Rectangle( 100, 200, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 3, numbersAreaLabel, new Rectangle( 200, 200, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 4, numbersAreaLabel, new Rectangle( 0, 100, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 5, numbersAreaLabel, new Rectangle( 100, 100, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 6, numbersAreaLabel, new Rectangle( 200, 100, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 7, numbersAreaLabel, new Rectangle( 0, 0, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 8, numbersAreaLabel, new Rectangle( 100, 0, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 9, numbersAreaLabel, new Rectangle( 200, 0, 100, 100 ) ) );
        buttonsList.add( createNumberButton( 0, numbersAreaLabel, new Rectangle( 100, 300, 100, 100 ) ) ); 
        
        buttonsList.add( createOperatorButton( '/', numbersAreaLabel, new Rectangle( 300, 0, 100, 100 ) ) );
        buttonsList.add( createOperatorButton( '*', numbersAreaLabel, new Rectangle( 300, 100, 100, 100 ) ) );
        buttonsList.add( createOperatorButton( '-', numbersAreaLabel, new Rectangle( 300, 200, 100, 100 ) ) );
        buttonsList.add( createOperatorButton( '+', numbersAreaLabel, new Rectangle( 300, 300, 100, 100 ) ) );
        buttonsList.add( createOperatorButton( '=', numbersAreaLabel, new Rectangle( 200, 300, 100, 100 ) ) );
        
        buttonsList.add( createClearButton( "CLEAR" ,numbersAreaLabel, new Rectangle( 0, 300, 100, 100 ) ) );
        
        return buttonsList;
        
    };
    
    /**
     * Create a new number button
     * @param x is the Number of the Button
     * @param numbersAreaLabel is the label that the button will change String
     * @param buttonArea is the area and localization of the button
     * @return the button created
     */
    private JButton createNumberButton( Integer x, JLabel numbersAreaLabel, Rectangle buttonArea ) {
        JButton button = new JButton( x.toString() );
        button.setBounds( buttonArea );
        button.addActionListener( e -> {
            calculator.addNumber( x );
            updateResultString( numbersAreaLabel );
        });
        return button;
    }
    
    /**
     * Create a new operator button
     * @param c is the Operator of the Button
     * @param numbersAreaLabel is the label that the button will change String
     * @param buttonArea is the area and localization of the button
     * @return the button created
     */
    private JButton createOperatorButton( Character c, JLabel numbersAreaLabel, Rectangle buttonArea ) {
        
        JButton button = new JButton( c.toString() );
        button.setBounds( buttonArea );
        
        if ( c == '=' ) {
            
            button.addActionListener( e -> {
                
                try {
                   calculator.calculate(); 
                   updateResultString( numbersAreaLabel );
                } catch ( ArithmeticException ex ) {
                    updateResultStringToError( numbersAreaLabel );
                }
                
            });
            
        } else {
            
            button.addActionListener( e -> {
                
                try {
                    calculator.setOperator( c );
                    updateResultString( numbersAreaLabel );
                } catch ( ArithmeticException ex ) {
                    updateResultStringToError( numbersAreaLabel );
                }
                
                
            });
            
        }
        
        return button;
    }
    
    /**
     * Create a new clear button
     * @param s is the String that will show on the button
     * @param numbersAreaLabel is the label that the button will change String
     * @param buttonArea is the area and localization of the button
     * @return the button created
     */
    private JButton createClearButton( String s, JLabel numbersAreaLabel, Rectangle buttonArea ) {
        JButton button = new JButton( s );
        button.setBounds( buttonArea );
        button.addActionListener( e -> {
            calculator.clearAll();
            updateResultString( numbersAreaLabel );
        });
        return button;
    }
    
     /**
     * Updates the result label text to the String of the Calculator object
     * @param numbersAreaLabel Label that will be text changed
     */
    private void updateResultString( JLabel numbersAreaLabel ){
        numbersAreaLabel.setText( calculator.getCurrentResultText() );
    }
    
    /**
     * Updates the result label text to an "ERROR" message
     * @param numbersAreaLabel Label that will be text changed
     */
    private void updateResultStringToError ( JLabel numbersAreaLabel ){
        numbersAreaLabel.setText( "ERROR" );
    }
}
