/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

/**
 *
 * @author Victor Ramos
 */
public class Calculator {
    
    private Integer leftNumber = 0;
    private Integer rightNumber = 0;
    private char operator = ' ';
    private boolean isLeftNumberResult = false;
    
    /**
     * Execute the calculation with the parameters of the Calculator, treating 
     * the ArithmeticException of division by 0 error
     */
    public void calculate() {
        
        int result;
        
        switch ( this.operator ) {
            case '+':
                result = this.leftNumber + this.rightNumber;
                break;
            case '-':
                result = this.leftNumber - this.rightNumber;
                break;
            case '*':
                result = this.leftNumber * this.rightNumber;
                break;
            case '/':
                
                try {
                    result = this.leftNumber / this.rightNumber;
                } catch ( ArithmeticException e ) {
                    clearAll();
                    result = 0;
                    throw e;
                }
                break;
                
            default:
                result = this.leftNumber;
                break;
        }
        
        this.leftNumber = result;
        this.operator = ' ';
        
        this.isLeftNumberResult = true;
    }
    
    /**
     * Add a new number to the calculator, deciding where it will be adding
     * and appending it to a already existent number if necessary
     * @param number the number that will be added
     */
    public void addNumber( int number ) {
        
        if ( this.operator == ' ' ) {
            
            if( this.isLeftNumberResult ){ 
                this.leftNumber = 0;
                this.isLeftNumberResult = false;
            }
            
            this.leftNumber = appendNumber( this.leftNumber, number );
            
        } else {
            
            if ( this.isLeftNumberResult ) {
                this.rightNumber = 0;
                this.isLeftNumberResult = false;
            }
            this.rightNumber = appendNumber( this.rightNumber, number );
            
        }
        
    }
    
    /**
     * Set a new operator to the Calculator
     * @param operator is the new operator that will be added
     */
    public void setOperator( char operator ) {
        
        if ( this.operator == ' ' ){
            
            this.operator = operator;
            
        } else if ( !isLeftNumberResult ){
            calculate();
            this.operator = operator;
        } else {
            
            if( operator == this.operator ){
                calculate();
            } else {
                this.operator = operator;
            }
            
        }
    }
    
    /**
     * Clear all the variables of the Calculator
     */
    public void clearAll(){
        
        this.leftNumber = 0;
        this.rightNumber = 0;
        this.operator = ' ';
    }
    
    /**
     * Function that append new numbers to the int value
     * @param originalNumber is the original number that will have a new number appended to it
     * @param newNumber is the new number that will be appended to original number
     * @return the original number with the new number appended to it
     */
    private int appendNumber( int originalNumber, int newNumber ) {
        return originalNumber*10 + newNumber;
    }
    
    /**
     * Function that returns the string that must be displayed on Result Label
     * @return the current result string
     */
    public String getCurrentResultText() {
        return ( this.operator == ' ' ) ? ( this.leftNumber.toString() ) : ( this.leftNumber + " " + this.operator + " " + this.rightNumber );
    } 
}
