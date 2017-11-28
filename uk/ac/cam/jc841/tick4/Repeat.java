package uk.ac.cam.jc841.tick4;

public class Repeat {
  public static void main(String[] args) {
    System.out.println(parseAndRep(args));
  }

  public static String parseAndRep(String[] args) {
    if (args.length < 2) {
      return "Error: insufficient arguments";
    } else {
      String inText = args[0];
      int repetitions;

      try {
        repetitions = Integer.parseInt(args[1]);
      } catch (NumberFormatException error) {
        return "Error: second argument is not a positive integer";
      }

      if (repetitions <= 0) {
        return "Error: second argument is not a positive integer";
      }

      String outText = inText;
      for (int i = 0; i < repetitions - 1; i++) {
        outText += " " + inText;
      }
      return outText;
    }
  }
}
