public class Polynomial {
	double[] polynomialCo;

	public Polynomial(){
		polynomialCo = new double[]{0};
	}

	public Polynomial(double[] poly){
		this.polynomialCo = new double[poly.length];
		for (int i = 0; i < poly.length; i++){
			polynomialCo[i] = poly[i];
		}
	}

	// helper function to find length of array
	public int length(){
		//since Java has built-in function to find length for array
		return polynomialCo.length; 
	}

	public Polynomial add(Polynomial p){
		// add polynomialCo and p
		int maxLength = Math.max(this.length(), p.length());
		double[] result = new double[maxLength];
		for (int i = 0; i < maxLength; i++){
			if (i >= polynomialCo.length){
				result[i] = 0 + p.polynomialCo[i];
			}
			else if (i >= p.length()) {
				result[i] = polynomialCo[i] + 0;
			}
			else{
				result[i] = polynomialCo[i] + p.polynomialCo[i];
			}
		}
		return new Polynomial(result);
	}
	
	public double evaluate(double x){
		// should sub value 'x' into polynomial and return final value
		double value = 0;
		for (int i = 0; i < polynomialCo.length; i++){
			value += Math.pow(x, i) * polynomialCo[i];
		}
		return value;
	}
	
	public boolean hasRoot(double root){
		// return true if evaluate(root) = 0
		return evaluate(root) == 0;
	}
}