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
    
    private StringBuilder operation = new StringBuilder();
    private boolean hasOperationResult = false;
    
    /**
     * Execute the calculation with the first 3 terms ( 2 numbers and 1 operator)
     * till it gets the result, then add it to the string operation.
     */
    public void calculate() {
        
        String[] terms = operation.toString().split( " " );
        
        while ( terms.length > 1 ){
            terms = calculate( terms );
        }
        
        operation.append(" = ").append( terms[0] );
        hasOperationResult = true;
        
    }
    
    /**
     * Execute the calculation with the first 3 terms and return the result
     * of these as just 1 term on the given array treating the 
     * ArithmeticException of division by 0 error.
     * @param terms is the array with all terms of the operation.
     * @return the given array but with the 3 first terms compressed as just 1.
     */
    private String[] calculate( String[] terms ) {
        
        String[] newTerms = new String[ terms.length-2];
        
        switch ( terms[1] ) {
            case "+":
                newTerms[0] = Integer.toString( Integer.parseInt( terms[0] ) + Integer.parseInt( terms[2] ) );
                break;
            case "-":
                newTerms[0] = Integer.toString( Integer.parseInt( terms[0] ) - Integer.parseInt( terms[2] ) );
                break;
            case "*":
                newTerms[0] = Integer.toString( Integer.parseInt( terms[0] ) * Integer.parseInt( terms[2] ) );
                break;
            case "/":
                
                try {
                    newTerms[0] = Integer.toString( Integer.parseInt( terms[0] ) / Integer.parseInt( terms[2] ) );
                } catch ( ArithmeticException e ) {
                    throw e;
                }
                
                break;
                
            default:
                throw new AssertionError();
        }
        
        for( int i=1; i<newTerms.length; i++ ) {
            newTerms[i] = terms[i+2];
        }
        
        return newTerms;
        
    }
    
    /**
     * Add a new term to the calculator, treating all the scenarios that can
     * ocurr.
     * @param term the term ( number or operator ) that will be added.
     */
    public void addTerm( char term ) {
        
        if ( hasOperationResult ) {
            if ( isOperator( term ) ){
                String result = operation.toString().split(" ")[ operation.toString().split(" ").length-1 ];
                clearAll();
                operation.append( result ); 
            } else {
                clearAll();
            }
            
        }
        
        if( this.operation.length()==0 ){
            
            this.operation.append( term );
            
        } else {
            
            if( isOperator( this.operation.charAt( this.operation.length()-1 ) ) ) {
            
                if ( isOperator( term ) ) {
                    this.operation.deleteCharAt( this.operation.length()-1 );
                    this.operation.append( term );
                } else {
                    this.operation.append( ' ' );
                    this.operation.append( term );
                }

            } else {

                if ( isOperator( term ) ) {
                    this.operation.append( ' ' );
                    this.operation.append( term );
                } else {
                    this.operation.append( term );
                }

            }
            
        }
        
    }
    
    /**
     * Clear all the variables of the Calculator.
     */
    public void clearAll(){
        this.operation.delete( 0, this.operation.length() );
        hasOperationResult = false;
    }
    
    /**
     * Function that returns the string that must be displayed on Result Label.
     * @return the current result string.
     */
    public String getCurrentResultText() {
        return this.operation.toString();
    }
    
    /**
     * Decide if the term is an operator ( '+', '-', '*', '/' ).
     * @param term the term that will be compared.
     * @return true if the term is an operator and false if it isn't.
     */
    private boolean isOperator ( char term ){
        return term == '+' || term == '-' || term == '*' || term == '/';
    }
}