package inner_class;

public class Main {

	public static void main(String[] args) {
//		A objA = new A() { // anonymous inner class
//			void display( ) {
//				System.out.println("Hello anno...");
//			}
//		};
		//A objA1 = new A();
		//A.B objB = objA.new B(); //inner class invoke
		//objA.display();
		
		hello obj = new hello() { //object for interface using anno class
			public void fn1() {
				System.out.println("Hello fn1");
			}
			public void fn2() {
				System.out.println("Hello fn2");
			}
		};
		
		obj.fn1();

	}

}
