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
    
    public String calculate() {
        
        Integer result;
        
        switch ( operator ) {
            case '+':
                result = leftNumber + rightNumber;
                break;
            case '-':
                result = leftNumber - rightNumber;
                break;
            case '*':
                result = leftNumber * rightNumber;
                break;
            case '/':
                result = leftNumber / rightNumber;
                break;
            default:
                result = leftNumber;
                break;
        }
        
        this.leftNumber = result;
        this.operator = ' ';
        
        isLeftNumberResult = true;
        return result.toString();
    }
    
    public void addNumber( int number ) {
        
        if ( operator == ' ' ) {
            
            if( isLeftNumberResult ){ 
                this.leftNumber = 0;
                isLeftNumberResult = false;
            }
            
            this.leftNumber = appendNumber( this.leftNumber, number );
            
        } else {
            
            if ( isLeftNumberResult ) {
                this.rightNumber = 0;
                isLeftNumberResult = false;
            }
            this.rightNumber = appendNumber( this.rightNumber, number );
            
        }
        
    }
    
    public void setOperator( char operator ) {
        
        if (this.operator == ' '){
            this.operator = operator;
        } else {
            calculate();
            this.operator = operator;
        }
        
    }
    
    public void clearAll(){
        
        this.leftNumber = 0;
        this.rightNumber = 0;
        this.operator = ' ';
    }
    
    private int appendNumber( int originalNumber, int newNumber ) {
        return originalNumber*10 + newNumber;
    }
    
    public String getCurrentResultText(){
        
        if( this.operator == ' ' ) {
            return this.leftNumber.toString();
        } else {
            return this.leftNumber + " " + operator + " " + this.rightNumber;
        }
    } 
}
