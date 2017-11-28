package uk.ac.cam.jc841.tick5;

import uk.ac.cam.acr31.life.World;
import uk.ac.cam.acr31.life.WorldViewer;
import java.util.List;
import java.io.IOException;
import java.io.Writer;
import java.io.OutputStreamWriter;

public class RefactorLife {
  public static void play(World world) throws Exception {
    Writer w = new OutputStreamWriter(System.out);
    WorldViewer v = new WorldViewer();
    int userResponse = 0;
    while (userResponse != 'q') {
      world.print(w);
      v.show(world);
      userResponse = System.in.read();
      world = world.nextGeneration(1);
    }
    v.close();
  }

  public static void main(String[] args) throws Exception {
    String worldType = args.length == 3 ? args[0] : "--array";

    if (args.length == 0 || args.length > 3) {
      System.out.println("Error: need between 1 and 3 arguments");
    } else {
      String location = args.length == 3 ? args[1] : args[0];
      List<Pattern> patternList;

      try {
        if (location.startsWith("http://")) {
          patternList = PatternLoader.loadFromURL(location);
        } else {
          patternList = PatternLoader.loadFromDisk(location);
        }
      } catch (IOException error) {
        System.out.println("Error: problem accessing the specified location");
        return;
      }

      if (args.length == 1) {
        int i = 0;
        for (Pattern p : patternList) {
          System.out.println(i + ") " + p.getFormatString());
          i++;
        }
      } else {
        int patternIndex;
        Pattern p;
        String indexString = args.length == 3 ? args[2] : args[1];

        try {
          patternIndex = Integer.parseInt(indexString);
        } catch (NumberFormatException error) {
          System.out.println("Error: invalid pattern index; must be a positive integer");
          return;
        }

        try {
          p = patternList.get(patternIndex);
        } catch (IndexOutOfBoundsException error) {
          System.out.println("Error: no pattern with specified index");
          return;
        }

        World world = null;

        if (worldType.equals("--array")) {
          world = new ArrayWorld(p.getWidth(), p.getHeight());
        } else if (worldType.equals("--long")) {
          world = new PackedWorld();
        } else if (worldType.equals("--aging")) {
          world = new AgingWorld(p.getWidth(), p.getHeight());
        } else {
          System.out.println("Error: world type option must be '--array', '--long' or '--aging'");
          return;
        }

        try {
          p.initialise(world);
        } catch (PatternFormatException error) {
          System.out.println("Error: " + error.getMessage());
          return;
        }

        play(world);
      }
    }
  }
}
