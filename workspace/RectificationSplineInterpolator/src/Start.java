import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.analysis.solvers.SecantSolver;
public class Start {
	public static void main(String[] args)
	{
		double[] x = {-2,-1,0,1,2};
		double[] y = {4,1,0,1,4};
		SplineInterpolator spline = new SplineInterpolator();
		SecantSolver solver = new SecantSolver();
		PolynomialSplineFunction w = spline.interpolate(x, y);
		PolynomialFunction[] f = w.getPolynomials();
		for(int i = 1; i < f.length; i++)
		{
			PolynomialFunction z = f[i];
			PolynomialFunction fprime = z.polynomialDerivative();
			PolynomialFunction fdoubleprime = fprime.polynomialDerivative();
			System.out.println(solver.solve(100000, fdoubleprime,-1000000,100000));
		}
	}
}