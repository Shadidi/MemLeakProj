import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.*;
/*
 * 	This program does the same tasks as the
 * 	TeastNoLeak program except it leaks memory.
 * 	It does so by having its code not release 
 *  objects from the memory that would have 
 *  been collected by the garbage collector.
 * */


public class TestLeak {

    public static void main(String[] args) throws InterruptedException, IOException {
    	
    	Thread.sleep(10000); // Allow the Visual VM to start monitoring.
    	
    	System.out.println("This program will run a series of tasks"
				+ " to demonstrate how a program can achieve the same"
				+ " goals and leak memory.");
    	
    	System.out.println("Task 1: Referencing to an object.");
    	// Here the program is creating 20 objects each creates a big
    	// array. However, the array is defined static so it doesn't
    	// get deleted by the GC.
    	
    	for (int i = 0; i < 20; i++) {
	    	StaticObjectReferance user = new StaticObjectReferance();
			user.static_lotsOfOperations_memoryLeak();
    	}

    	System.gc();
    	
   
    	System.out.println("Task 2: Stack pushing and popping.");
    	// Now the program will stack and un-stack stuff. 
    	// The issue is that the stack's reference remains even 
    	// when all elements are popped because the stack's size
    	// was never nullified.
    	try {
    		 
     		final int testSize = 1000;
     		Stack s = new Stack(testSize);
     		for (int j = 0; j < 5; j++) {
     			for (int i = 0; i < testSize; i++) {s.push(new Stuff());}
         		for (int i = 0; i < testSize; i++) {s.pop();}
         		System.gc();
         		Thread.sleep(2000); 
     		}
    	}
     	catch(Throwable t) {
     		System.out.println(t);
     	}
    	
    	
    	System.out.println("Task 3: Getting input from a buffer.");
    	// One needs to close any open resources. Because I'm running
    	// Java 8, this issue doesn't happen here because the compiler
    	// asks a throw, catch to be placed which takes care of closing
    	// buffer connection. Yet in older versions, buffer needs to be
    	// explicitly closed.
    	Buffer gettingInput = new Buffer();
    	gettingInput.bufferNotClosed();
		
		System.gc();
		
		System.out.println("Task 4: Strings.");
		// Here the program is dealing with a big data of string.
		// When it substrings from it and no longer needs the 
		// bigString, nulling it doesn't remove dependency because
		// the substr references it to obtain the two chars. 
		// Again this issue shows up in older versions of Java.
		String bigString = new String(new byte[100000]);
		
		String substr = bigString.substring(0, 2);
		bigString = null;
		

    }
}
