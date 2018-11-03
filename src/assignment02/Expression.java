package assignment02;

/* Student Name: Hassan Haidar
 * Student ID:   260711061
 */

import java.util.Stack;
import java.util.ArrayList;

public class Expression  {
	private ArrayList<String> tokenList;

	//  Constructor    
	/**
	 * The constructor takes in an expression as a string
	 * and tokenizes it (breaks it up into meaningful units)
	 * These tokens are then stored in an array list 'tokenList'.
	 */

	Expression(String expressionString) throws IllegalArgumentException{
		tokenList = new ArrayList<String>();
		StringBuilder token = new StringBuilder();

		//ADD YOUR CODE BELOW HERE
		for (int i = 0; i < expressionString.length(); i++){
			if (!isWhiteSpace(expressionString.charAt(i))){ //only append if character is not a white space
				if (Character.isDigit(expressionString.charAt(i))){
					int count = i;
					while (Character.isDigit(expressionString.charAt(count))) {
						token.append(expressionString.charAt(count));
						count++;
					}
					i = count - 1;
				}

				else if (i < (expressionString.length() - 1)     //for parsing preincrements and predecrements
						&& ((expressionString.substring(i, i+2).equals("++")
								|| (expressionString.substring(i, i+2).equals("--"))))){
					token.append(expressionString.substring(i, i+2));
					i++;
				}			

				else if (isBinaryOperator(expressionString.charAt(i) + "") ||
						isBracket(expressionString.charAt(i) + "")) {
					token.append(expressionString.charAt(i));
				}
				tokenList.add(token.toString());
				token.delete(0, token.length());
			}
		}


		//ADD YOUR CODE ABOVE HERE
	}

	/**
	 * This method evaluates the expression and returns the value of the expression
	 * Evaluation is done using 2 stack ADTs, operatorStack to store operators
	 * and valueStack to store values and intermediate results.
	 * - You must fill in code to evaluate an expression using 2 stacks
	 */
	public Integer eval(){
		Stack<String> operatorStack = new Stack<String>();
		Stack<Integer> valueStack = new Stack<Integer>();

		//ADD YOUR CODE BELOW HERE

		int a = 0;
		int b = 0;
		for (int i = 0; i < tokenList.size(); i++){
			if (tokenList.get(i).equals("(") ||  tokenList.get(i).equals("["))
				continue;

			else if (isInteger(tokenList.get(i))){
				valueStack.push(Integer.valueOf(tokenList.get(i)));
			}
			else if (isUnaryOperator(tokenList.get(i)))
				operatorStack.push(tokenList.get(i));

			else if (isBinaryOperator(tokenList.get(i)))
				operatorStack.push(tokenList.get(i));	

			else if ((tokenList.get(i).equals(")")) && ( operatorStack.size() >= 1)){
				if (isBinaryOperator(operatorStack.peek())){//if operator is binary
					a = valueStack.pop();
					b = valueStack.pop();
					if (operatorStack.peek().equals("+")){
						valueStack.push(a + b);					
					}
					else if (operatorStack.peek().equals("-")){
						valueStack.push(b - a);
					}
					else if (operatorStack.peek().equals("*")){
						valueStack.push(a * b);
					}
					else if (operatorStack.peek().equals("/")){
						valueStack.push(b / a);
					}
					operatorStack.pop();
				}
				else {  //if operation is unary
					//System.out.println(a);
					a = valueStack.pop();
					if (operatorStack.peek().equals("++")){
						valueStack.push(a + 1);
						System.out.println("test");
					}
					else{
						valueStack.push(a - 1);
					}
					operatorStack.pop();
				}
			}
			else if ((tokenList.get(i).equals("]")) && ( operatorStack.size() >= 0)){
				a = valueStack.pop();
				if (a < 0)
					valueStack.push( -a );
				else
					valueStack.push ( a );
			}
		}

		return valueStack.pop();

		//ADD YOUR CODE ABOVE HERE

	}


	//Helper methods
	/**
	 * Helper method to test if a string is an integer
	 * Returns true for strings of integers like "456"
	 * and false for string of non-integers like "+"
	 * - DO NOT EDIT THIS METHOD
	 */
	private boolean isInteger(String element){
		try{
			Integer.valueOf(element);
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}

	/**
	 * Method to help print out the expression stored as a list in tokenList.
	 * - DO NOT EDIT THIS METHOD    
	 */

	@Override
	public String toString(){	
		String s = new String(); 
		for (String t : tokenList )
			s = s + "~"+  t;
		return s;		
	}

	/**
	 * Method that checks if the character is a whitespace or not
	 * @param c
	 * @return boolean
	 */
	private boolean isWhiteSpace(char c){
		if ((8 <= c && c <= 10) || (c == 32)){
			return true;
		}
		else return false;
	}

	/**
	 * Method to check if character is a binary operator
	 * @param s
	 * @return boolean
	 */
	private boolean isBinaryOperator(String s){
		if (s.equals("+") ||
				s.equals("-") ||
				s.equals("*") ||
				s.equals("/") )
			return true;
		else
			return false;
	}


	/**
	 * method to check if character is a bracket
	 * @param s
	 * @return
	 */
	private boolean  isBracket(String s){
		if (s.equals("[") ||
				s.equals("]") ||
				s.equals("(") ||
				s.equals(")")){
			return true;
		}
		else return false;
	}


	/**
	 * method to check if character is unary
	 * @param s
	 * @return boolean
	 */
	private boolean isUnaryOperator(String s){
		if (s.equals("++") ||
				s.equals("--"))
			return true;
		else return false;
	}

}