import java.util.ArrayList;
import java.util.Random;

public class StaticObjectReferance {
	private Random random = new Random();
	public static final ArrayList<Double> list = new ArrayList<Double>(1000000);
	
	public void static_lotsOfOperations_memoryLeak() throws InterruptedException {
		for (int i = 0; i < 1000000; i++) {
	        list.add(random.nextDouble());
	    }
	}
	
	public final ArrayList<Double> list2 = new ArrayList<Double>(1000000);
	
	public void notStatic_lotsOfOperations_noLeak() {
		for (int i = 0; i < 1000000; i++) {
	        list2.add(random.nextDouble());
	    }
	}
}
