import java.util.HashMap;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Polynomial {
	double[] polynomialCo;
	int[] polynomialDegree;
	HashMap<Integer, Double> polynomial = new HashMap<Integer, Double>();

	public Polynomial(){
		polynomialCo = new double[]{0};
		polynomialDegree = new int[]{0};
		polynomial.put(0,0.0);
	}

	public Polynomial(double[] polyCo, int[] polyD){
		this.polynomialCo = polyCo;
		this.polynomialDegree = polyD;
		for (int i = 0; i < polyCo.length; i++){
			this.polynomial.put(polyD[i], polyCo[i]);
		}
		
	}

	public Polynomial(File fileObj) throws FileNotFoundException {
		Scanner scanner = new Scanner(fileObj);
		String polyString = scanner.nextLine();
		scanner.close();

		polyString = polyString.replace("-", "+-");

		String[] terms = polyString.split("\\+");

		HashMap<Integer, Double> result = new HashMap<>();

		for (String term : terms) {
			if(!term.isEmpty()) {
				if (!term.contains("x")) {
					result.put(0, Double.parseDouble(term));
				} else {
					String[] parts = term.split("x");
					double coefficient;
					int degree;

					if(parts[0].isEmpty() || parts[0].equals("+")) {
						coefficient = 1.0;
					} else if (parts[0].equals("-")) {
						coefficient = -1.0;
					} else {
						coefficient = Double.parseDouble(parts[0]);
					}

					if (parts.length == 1){
						degree = 1;
					} else {
						degree = Integer.parseInt(parts[1]);
					}

					result.put(degree, result.getOrDefault(degree, 0.0) + coefficient);
				}
			}
		}
		
		polynomialCo = new double[result.size()];
		polynomialDegree = new int[result.size()];

		int index = 0;
		for (int deg : result.keySet()){
			polynomialDegree[index] = deg;
			polynomialCo[index] = result.get(deg);
			index++;
		}

		for (int i = 0; i < polynomialCo.length; i++){
			this.polynomial.put(polynomialDegree[i], polynomialCo[i]);
		}
	}

	public Boolean containsD (int[] polyD, int item){
		for (int i: polyD){
			if (item == i){	
				return true;
			}
		}
		return false;
	}

	public Boolean containsD (Polynomial polyD, int item){
		for (int i: polyD.polynomial.keySet()){
			if (item == i) {
				return true;
			}
		}
		return false;
	}

	public Polynomial add(Polynomial p){
		HashMap<Integer, Double> result = new HashMap<Integer, Double>();

		for (int i = 0; i < this.polynomialDegree.length; i++) {
			int  degree = this.polynomialDegree[i];
			double coefficient = this.polynomialCo[i];
			result.put(degree, coefficient);
		}


		for (int i = 0; i < p.polynomialDegree.length; i++) {
			int  degree = p.polynomialDegree[i];
			double coefficient = p.polynomialCo[i];
			if (containsD(p.polynomialDegree, degree) && containsD(this.polynomialDegree, degree)){
				result.put(degree, coefficient + this.polynomial.get(i));

			} else {
				result.put(degree, coefficient);
			}
			
		}
		
		double[] newPolyC = new double[result.size()];
		int[]  newPolyD = new int[result.size()];

		int index = 0;

		for (int deg : result.keySet()){
			newPolyD[index] = deg;
			newPolyC[index] = result.get(deg);
			index++;
		}

		return new Polynomial(newPolyC, newPolyD);
	}
	
	public double evaluate(double x){
		// should sub value 'x' into polynomial and return final value
		double value = 0;
		for (int i = 0; i < polynomialDegree.length; i++){
			value += Math.pow(x, polynomialDegree[i]) * polynomialCo[i];
		}
		return value;
	}
	
	public boolean hasRoot(double root){
		// return true if evaluate(root) = 0
		return evaluate(root) == 0;
	}

	public Polynomial multiply(Polynomial p){
		Polynomial newP = new Polynomial();

		for (int i = 0; i < this.polynomial.size(); i++){
			for (int j = 0; j < p.polynomial.size(); j++){
				double newCo = this.polynomialCo[i] * p.polynomialCo[j];
				int newD = this.polynomialDegree[i] + p.polynomialDegree[j];
				
				if (containsD(newP, newD)){
					newP.polynomial.put(newD, newP.polynomial.get(newD) + newCo);
				} else {	
					newP.polynomial.put(newD, newCo);
				}
			}	
		}
		
		newP.polynomial.remove(0);

		return newP;
	}

	public void saveToFile(String fileName) throws IOException {
		StringBuilder polyStr = new StringBuilder();
		for(int i = 0; i < polynomialCo.length; i++){
			double co = polynomialCo[i];
			int degree = polynomialDegree[i];

			if (i != 0 && co >= 0) {
				polyStr.append("+");
			}
			if (degree == 0) {
				polyStr.append(co);
			} else {
				polyStr.append(co).append("x");
				if (degree != 1) {
					polyStr.append(degree);
				}
			}
		}
		FileWriter writer = new FileWriter(fileName);
		writer.write(polyStr.toString());
		writer.close();
		
	}	
}