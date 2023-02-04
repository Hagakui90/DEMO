package Stack;

public class TestStackWithArray {

	public static void main(String[] args) {
		MyStack myStack = new MyStack(6);
		
		myStack.push(new Node(7));
		myStack.push(new Node(12));
		myStack.push(new Node(15));
		System.out.println("Current size of stack is " + myStack.getSize());
		
		System.out.print("Get top: ");
		myStack.pop().printData();
		
		Node peekTop = myStack.peek();
		System.out.print("\nPeek top:");
		peekTop.printData();
		
		System.out.print("\nAfter pop, ");
		myStack.print();

	}

}
