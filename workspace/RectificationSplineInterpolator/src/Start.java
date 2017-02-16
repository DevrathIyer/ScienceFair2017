import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.stat.descriptive.rank.Max;
import org.apache.commons.math3.stat.descriptive.rank.Min;

public class Start {
	
	public static void main(String[] args)
	{
		double[] x = {-2,-1,0,1,2};
		double[] y = {4,1,0,1,4};
	
		SplineInterpolator spline = new SplineInterpolator();
		PolynomialSplineFunction w = spline.interpolate(x, y);
		PolynomialFunction[] f = w.getPolynomials();
		
		ArrayList<double[]> pressurePoints = new ArrayList<double[]>();
		final double THRESHOLD = 1; 
		
		for(int i = 1; i < f.length; i++)
		{
			PolynomialFunction z = f[i];
			
			
			PolynomialFunction fprime = z.polynomialDerivative();
			PolynomialFunction fdoubleprime = fprime.polynomialDerivative();

			//find extrema using quadratic formula
			ArrayList<Double> roots = new ArrayList<Double>();
			double[] coeffs = fprime.getCoefficients();
			double a = coeffs[0];
			double b = coeffs[1];
			double c = coeffs[2];
	
			double discriminant = b*b - 4*a*c;
			
			if(discriminant < 0){}
			else if(Math.abs(a)<10E-4)
			{
				double x1 = -c/b;
				roots.add(x1);
			}
			else
			{
				double x1 = (-b+Math.sqrt(discriminant))/(2*a);
				double x2 = (-b-Math.sqrt(discriminant))/(2*a);
				roots.add(x1);
				roots.add(x2);
			}
			
			//calculate second derivative at each root, if it is above threshold (sharper minima) 
			//and within x bounds, then record that x as a pressure point
			double xLBound = new Min().evaluate(x);
			double xHBound = new Max().evaluate(x);
			
			for(Double d : roots)
			{
				
				if(d<=xHBound&&d>=xLBound&&fdoubleprime.value(d)>THRESHOLD)
				{
					pressurePoints.add(new double[]{d, z.value(d)});
				}
			}
		}
		
		//print out pressure points
		for(double[] orderedPair:pressurePoints)
		{
			System.out.println("("+orderedPair[0]+" , "+orderedPair[1]+")");
		}
	}
}