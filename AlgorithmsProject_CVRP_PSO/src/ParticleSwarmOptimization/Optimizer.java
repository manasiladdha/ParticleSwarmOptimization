package ParticleSwarmOptimization;

import java.util.Random;

public class Optimizer {
	
	  // Implementing Fisher–Yates shuffle
	  public static void shuffleArray(int[] ar)
	  {	    
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	    
	  }
	  
	  // copy the int array to double array
	  public static double[] copyFromIntArray(int[] source) {
		    double[] dest = new double[source.length];
		    for(int i=0; i<source.length; i++) {
		        dest[i] = source[i];
		    }
		    return dest;
		}
}
