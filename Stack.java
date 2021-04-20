import java.util.EmptyStackException;
import java.util.*;

public class Stack {
	private Object[] elements;
	private int size = 0;
	public Stack(int initialCapacity) {
		elements = new Object[initialCapacity];
	}
	
	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public Object pop(){
		if (size == 0) throw new EmptyStackException(); 
		return elements[--size];
	}
	
	public Object popAndNull() {
		if (size == 0) throw new EmptyStackException(); 
		Object result = elements[--size];
		elements[size] = null;
		return result;
	}
	
	private void ensureCapacity() {
		if (elements.length == size) {
			Object[] oldElements = elements;
			elements = new Object[2 * elements.length + 1];
			System.arraycopy(oldElements, 0, elements, 0, size);
		} 
	}
}
class Stuff {
    private int[] vals = new int[10000]; 
}


