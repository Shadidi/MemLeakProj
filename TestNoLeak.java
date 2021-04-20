import java.io.IOException;

/*
 * 	This program does the same tasks as the
 * 	TestLeak program but it doesn't leak memory.
 * 	It does so by changing some code to release 
 *  objects from the memory that will be collected 
 *  by the garbage collector.
 * */
public class TestNoLeak {
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Thread.sleep(10000); // Allow the Visual VM to start monitoring.
		
		System.out.println("This program will run a series of tasks"
				+ " to demonstrate how a program can achieve the same"
				+ " goals but without memory leak.");
		
		System.out.println("Task 1: Referencing to an object.");
		// No longer using static when generating the list.
		// GC will collect such instances.
		for (int i = 0; i < 20; i++) {
			StaticObjectReferance user = new StaticObjectReferance();
			user.notStatic_lotsOfOperations_noLeak();
		}
		
		System.gc();

		
		System.out.println("Task 2: Stack pushing and popping.");
		// Here we are not just popping the stuff placed in stack, but
		// also setting each location to null. This will allow no reference
		// to the object elements of the stack making it available for GC.
		try {
     		final int testSize = 1000;
     		Stack s = new Stack(testSize);
     		for (int j = 0; j < 5; j++) {
     			for (int i = 0; i < testSize; i++) {
         			s.push(new Stuff());
         		}
         		for (int i = 0; i < testSize; i++) {
         			s.popAndNull();
         		}
         		System.gc();
         		Thread.sleep(2000); 
     		}
    	}
     	catch(Throwable t) {
     		System.out.println(t);
     	}
		
		System.out.println("Task 3: Getting input from a buffer.");
		// Reading file input. Here we explicitly close the buffer 
		// input's stream when done with it. Although the language
		// (Java 8) technically closes it because of the 
		// throw/catch around it.
		
    	Buffer gettingInput = new Buffer();
    	gettingInput.bufferClosed();
		System.gc();
		
		
		System.out.println("Task 4: Strings.");
		// String object is created and then done with.
		// Proper disposal of it allows GC to pick it up.
		// Here we set it to null when done with.
		String bigString = new String(new byte[100000]);
		
		String substr = new String (bigString.substring(0, 2));
		bigString = null;
	
	}

}
