package uk.ac.cam.jc841.sv1;

public class NewtonRaphsonSquareRoot {
  public static double sqrtNR(double x, double error) throws NegativeSquareRootException {
    if (x < 0.0) {
      throw new NegativeSquareRootException();
    } else {
      double guess = 1.0;
      while (!(Math.abs(guess * guess - x) < error)) {
        guess = (guess + x / guess) / 2;
      }
      return guess;
    }
  }

  public static void main(String[] args) {
    System.out.println(sqrtNR(3.0, 0.01));
    System.out.println(sqrtNR(-2.0, 0.1)); //throws an error
  }
}
