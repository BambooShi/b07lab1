import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
public static void main(String [] args) {
Polynomial p = new Polynomial();
System.out.println(p.evaluate(3));
double [] c1 = {6,5};
int [] pc1 = {0,3};
Polynomial p1 = new Polynomial(c1, pc1);
double [] c2 = {-2,-9};
int [] pc2 = {1, 4};
Polynomial p2 = new Polynomial(c2, pc2);
Polynomial s = p1.add(p2);
System.out.println("s(0.1) = " + s.evaluate(0.1));
if(s.hasRoot(1))
System.out.println("1 is a root of s");
else
System.out.println("1 is not a root of s");

Polynomial m = p1.multiply(p2);

System.out.println("m is: " + m.polynomial.keySet() + " and " + m.polynomial.values());

File file = new File("C:\\Users\\shiSn\\b07lab1\\polyTest.txt");

try {
    Polynomial p3 = new Polynomial(file);  // Initialize polynomial from file
    System.out.println("p3 is: " + p3.polynomial.keySet() + " and " + p3.polynomial.values());
} catch (FileNotFoundException e) {
    System.out.println("File not found: " + e.getMessage());  // Handle file not found error
}

try {
    s.saveToFile("C:\\Users\\shiSn\\b07lab1\\polyCopy.txt");
} catch (IOException e) {
    System.out.println("File not found: " + e.getMessage());  // Handle file not found error
}

}
}