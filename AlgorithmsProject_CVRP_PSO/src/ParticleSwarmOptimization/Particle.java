package ParticleSwarmOptimization;

import java.util.Arrays;
import java.util.Random;

public class Particle {

	double[] xSolution;
	double xFitnessValue;
	
	double[] pBest;
	double pBestValue;
	double[] pBestVelocity;
	
	double[] pVelocity;

	public Particle(int[] x){
		
		this.xSolution = Optimizer.copyFromIntArray(x);	
		this.pBest = this.xSolution;
		setRandomVelcities(xSolution.length);
		this.pBestVelocity = pVelocity;
	}

	
	public void setRandomVelcities(int n) {
		this.pVelocity = new double[n];
		// randomly generate the velocity
		for (int i = 0; i < n; i++) {
			this.pVelocity[i] = getRandomVelocity(n);
		}
	}

	private double getRandomVelocity(int upper) {
		int lower = 0;
		return (new Random().nextDouble() * (upper - lower)) + lower;
	}


	@Override
	public String toString() {
		return "Particle [xSolution=" + Arrays.toString(xSolution) + ", xFitnessValue=" + xFitnessValue + ", pBest="
				+ Arrays.toString(pBest) + ", pBestValue=" + pBestValue + ", pVelocity=" + Arrays.toString(pVelocity)
				+ "]";
	}
	
	
	
	
	
	
}
