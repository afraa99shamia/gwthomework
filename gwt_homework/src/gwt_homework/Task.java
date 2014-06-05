package gwt_homework;
import java.util.*;
import java.math.*;
public class Task {
	
	
	
	 public double a;
  
	 public double b;
  
	 public double c;
	 
	public  List <Double> list =new ArrayList<Double>();
	 public String res ="";  

	public static Task create(Task task) {
	task.list=calcresult(task.a,task.b,task.c); 
	if (task.list.size() == 1)
	{
	task.res=" X =" + task.list.get(0) ;}
	else if(task.list.size() == 2)
	{task.res=" X1 =" + task.list.get(0) + "     ,       X2 = "+task.list.get(1)  ;}
	else 
	{task.res="don't have any solution";}
	return task;
	  }
	public static List <Double> calcresult(double a ,double b ,double c) {

	List <Double> t =new ArrayList<Double>();


	double d = 0;
	double bb =b*b;
	d= bb -(4*a*c);
	if(d==0)
	{double x = -(b/(2*a));
	 t.add(x);
	}
	else if(d > 0)
	{ double x1=-((b+Math.sqrt(d))/(2*a));
	  double x2=-((b-Math.sqrt(d))/(2*a));
	     t.add(x1);
	     t.add(x2);
	}
	return t ;
	  }

	  
	    
	}


