package assignment02;

public class CustomTester {

	public static void main(String[] args) throws IllegalArgumentException{
		Expression test = new Expression("[(-1)]");
		Expression test2 = new Expression("([(2 - 5)])");
		Expression test3 = new Expression("(((32 + 4) * (4 - 1)) * (12 / 4))");
		System.out.print(test3.eval());	
		//Expression test = new Expression;
		//Expression test = new Expression
		

	}

}
