package launcher;

//import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

public class SeleniumReporter extends TimerTask {
	
	public static long startTime;
	public static Thread mainThread;
	
	static {
		SeleniumReporter.startTime = System.currentTimeMillis();
		SeleniumReporter.mainThread = SeleniumReporter.getMainThread();
	}
		
	
	public static Thread getMainThread() {
		
		Map<Thread,StackTraceElement[]> threadMap =  new  HashMap<Thread,StackTraceElement[]>();
		threadMap = Thread.getAllStackTraces();
		
		Thread mainThread = new Thread();
		for (Map.Entry<Thread, StackTraceElement[]> entry : threadMap.entrySet()) {
			
			Thread currThread = entry.getKey();
			if (currThread.getName().equalsIgnoreCase("main")) {
				mainThread = currThread;
				break;
			}
			
		}
		
		return mainThread;
		
	}

	public void run() {
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		
		if (elapsedTime >= 8) {
		
			String currentMethodName;
			String className;
			
			/*List<StackTraceElement> stackTraceAsList = new ArrayList<StackTraceElement>();
			
			for (StackTraceElement element : SeleniumReporter.mainThread.getStackTrace()) {
				stackTraceAsList.add(element);
			}
			
			Collections.reverse(stackTraceAsList);
			
			if (!stackTraceAsList.isEmpty()) {

				currentMethodName = stackTraceAsList.get(2).getMethodName();
				className = stackTraceAsList.get(2).getClassName().replace("co.tengo.selenium.", "");
				System.out.println(">>> Executing test method: " + className + '.' + currentMethodName + " - Elapsed time: " + String.valueOf(elapsedTime));

			} else {

				System.out.println(">>> Execution of Selenium tests finished - Elapsed time: " + String.valueOf(elapsedTime) + " seconds");
				
			}*/
		
		}
			
    }
	
}
