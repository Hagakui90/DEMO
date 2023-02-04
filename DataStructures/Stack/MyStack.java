package Stack;

public class MyStack {
	private int top;
	private int size;
	private int maxSize;
	private Node[] stack;
	
	public MyStack(int maxSize) {
		this.top = -1;
		this.size = 0;
		this.maxSize = maxSize;
		this.stack = new Node[this.maxSize];
	}
	
	public int getSize() {
		int currentSize = this.size;
		return currentSize;
	}
	
	public void push(Node node) {
		if (!this.isFull()) {		
			this.top++;
			this.size++;
			this.stack[this.top] = node;
		} else {
			System.out.println("Stack is full.");
			return;
		}

	}
	
	public Node pop() {
		if (!this.isEmpty()) {
			Node node = this.stack[this.top];
			this.size--;
			this.top--;
			return node;
		} else {
			System.out.println("Stack is empty.");
			return null;
		}
	}
	
	public Node peek() {
		if (!this.isEmpty()) {
			Node node = this.stack[this.top];
			return node;
		} else {
			System.out.println("Stack is empty.");
			return null;
		}
	}
	
	
	public boolean isEmpty() {
		return (this.top == -1) ;
		
	}
	
	public boolean isFull() {
		return (this.size == this.maxSize);
	}  
	
	public void print() {
		System.out.print("Stack is: " );
		for (int i = 0; i < this.size; i++) {
			stack[i].printData();
		}

	}
}
