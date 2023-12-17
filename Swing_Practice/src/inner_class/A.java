package inner_class;

public class A {
	int a = 1;
	
	void display() {
		System.out.println("From A");
	}
	
	public class B {
		void display() {
			System.out.println("From B");
		}
	}

}
