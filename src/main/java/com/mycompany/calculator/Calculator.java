/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.calculator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Ramos
 */
public class Calculator {
    
    private StringBuilder operation = new StringBuilder();
    private boolean hasOperationResult = false;
    
    /**
     * Execute the calculation with 3 terms ( 2 numbers and 1 operator)
     * till it gets the result, then call clearAll() and add the result to the
     * string operation.
     */
    public void calculate() {
        
        List<String> terms = new ArrayList<>();
        
        
        for( String s : operation.toString().split(" ")){
            terms.add(s);
        }
        
        // trowing error if the last term is a operator
        if ( isOperator( terms.getLast() ) ) {
            throw new AssertionError();
        }
        
        // treating operators as first term
        if ( isOperator( terms.getFirst() ) ) {
            
            if ( terms.getFirst().equals("+") ){
                terms.remove(0);  
            } else if ( terms.getFirst().equals("-") ) {
                terms.addFirst("0");
            } else {
                throw new AssertionError();
            }
            
        }
        
        // doing the * and / operations first:
        for( int i=0; i<terms.size(); ) {
            if( terms.get(i).equals("x") || terms.get(i).equals("/") ){
                terms = calculate( terms, i-1 );
            } else {
                i++;
            }
        }
        
        // doing the rest of the operations:
        while ( terms.size() > 1 ){
            terms = calculate( terms, 0 );
        }
        
        this.operation.append(" = ").append( terms.get(0) );
        this.hasOperationResult = true;
        
    }
    
    /**
     * Execute the calculation with the first 3 terms and return the result
     * of this operation as just 1 term on the given array treating the
     * ArithmeticException of division by 0 error.
     * @param terms is the array with all terms of the operation.
     * @param start is the position of the first term of the operation that will
     * be compressed.
     * @return the given array but with the 3 first terms compressed as just 1.
     */
    private List<String> calculate( List<String> terms, int start ) {
        
        switch ( terms.get(start+1) ) {
            case "+":
                terms.set( start, Integer.toString( Integer.parseInt( terms.get(start) ) + Integer.parseInt( terms.get(start+2) ) ) );
                break;
            case "-":
                terms.set( start, Integer.toString( Integer.parseInt( terms.get(start) ) - Integer.parseInt( terms.get(start+2) ) ) );
                break;
            case "x":
                terms.set( start, Integer.toString( Integer.parseInt( terms.get(start) ) * Integer.parseInt( terms.get(start+2) ) ) );
                break;
            case "/":
                
                try {
                    terms.set( start, Integer.toString( Integer.parseInt( terms.get(start) ) / Integer.parseInt( terms.get(start+2) ) ) );
                } catch ( ArithmeticException e ) {
                    throw e;
                }
                
                break;
                
            default:
                throw new AssertionError();
        }
                
        terms.remove(start+1);
        terms.remove(start+1);
        
        return terms;
        
    }
    
    /**
     * Add a new term to the calculator, treating all the scenarios that can
     * ocurr.
     * @param term the term ( number or operator ) that will be added.
     */
    public void addTerm( char term ) {
        
        if ( this.hasOperationResult ) {
            if ( isOperator( term ) ){
                String result = this.operation.toString().split(" ")[ this.operation.toString().split(" ").length-1 ];
                clearAll();
                this.operation.append( result ); 
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
     * Remove last term of the operation
     */
    public void removeLastTerm() {
        
        if ( hasOperationResult ){
            
            while( this.operation.charAt( this.operation.length()-1 ) != '=' ){
                this.operation.deleteCharAt( this.operation.length()-1 );
            }
            this.operation.delete(this.operation.length()-2, this.operation.length());
            
            this.hasOperationResult = false;
            
        } else {
            if ( !this.operation.isEmpty() ){
                this.operation.deleteCharAt( this.operation.length()-1 );
            }
        
            if ( !this.operation.isEmpty() ){
                if ( this.operation.charAt( this.operation.length()-1 ) == ' '){
                    this.operation.deleteCharAt( this.operation.length()-1 ); 
                 }
            }
        }
    }
    
    /**
     * Clear all the variables of the Calculator.
     */
    public void clearAll(){
        this.operation.delete( 0, this.operation.length() );
        this.hasOperationResult = false;
    }
    
    /**
     * Function that returns the string that must be displayed on Result Label.
     * @return the current result string.
     */
    public String getCurrentResultText() {
        return this.operation.toString();
    }
    
    /**
     * Decide if the char term is an operator ( '+', '-', '*', '/' ).
     * @param term the term that will be compared on char format.
     * @return true if the term is an operator and false if it isn't.
     */
    private boolean isOperator ( char term ){
        return term == '+' || term == '-' || term == 'x' || term == '/';
    }
    
    /**
     * Decide if the String term is an operator ( '+', '-', '*', '/' ).
     * @param term the term that will be compared on String format.
     * @return true if the term is an operator and false if it isn't.
     */
    private boolean isOperator ( String term ){
        return term.equals( "+" ) || term.equals( "-" ) || term.equals( "x" ) || term.equals( "/" );
    }
}