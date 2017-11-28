package uk.ac.cam.jc841.urop;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class LogoPlayer {
  private enum Heading {
    UP, DOWN, LEFT, RIGHT
  }

  private int xPosition;
  private int yPosition;
  private Heading direction;
  private LogoViewer viewer;

  public LogoPlayer(LogoViewer v) {
    xPosition = LogoViewer.SIZE / 2;
    yPosition = LogoViewer.SIZE / 2;
    direction = Heading.UP;
    viewer = v;
    drawPosition();
  }

  public void leftTurn() {
    if (direction == Heading.UP) {
      direction = Heading.LEFT;
    } else if (direction == Heading.LEFT) {
      direction = Heading.DOWN;
    } else if (direction == Heading.DOWN) {
      direction = Heading.RIGHT;
    } else {
      direction = Heading.UP;
    }
  }

  public void rightTurn() {
    if (direction == Heading.UP) {
      direction = Heading.RIGHT;
    } else if (direction == Heading.RIGHT) {
      direction = Heading.DOWN;
    } else if (direction == Heading.DOWN) {
      direction = Heading.LEFT;
    } else {
      direction = Heading.UP;
    }
  }

  private int xVelocity() {
    if (direction == Heading.UP || direction == Heading.DOWN) {
      return 0;
    } else if (direction == Heading.LEFT) {
      return -1;
    } else {
      return 1;
    }
  }

  private int yVelocity() {
    if (direction == Heading.LEFT || direction == Heading.RIGHT) {
      return 0;
    } else if (direction == Heading.UP) {
      return -1;
    } else {
      return 1;
    }
  }

  private void wrapPosition() {
    xPosition %= LogoViewer.SIZE;
    yPosition %= LogoViewer.SIZE;

    xPosition = xPosition < 0 ? xPosition + LogoViewer.SIZE : xPosition;
    yPosition = yPosition < 0 ? yPosition + LogoViewer.SIZE : yPosition;
  }

  private void drawPosition() {
    viewer.drawPoint(xPosition, yPosition);
  }

  public void forwards() {
    for (int i = 0; i<10; i++) {
      xPosition += xVelocity();
      yPosition += yVelocity();
      wrapPosition();
      drawPosition();
    }
  }

  public void backwards() {
    for (int i = 0; i<10; i++) {
      xPosition -= xVelocity();
      yPosition -= yVelocity();
      drawPosition();
    }
  }

  private static void runCommand(String command, LogoPlayer lp) throws BadCommandException {
    if (command.equals("forwards")) {
      lp.forwards();
    } else if (command.equals("backwards")) {
      lp.backwards();
    } else if (command.equals("left")) {
      lp.leftTurn();
    } else if (command.equals("right")) {
      lp.rightTurn();
    } else {
      throw new BadCommandException();
    }
  }


  private static void interactive(Scanner sc, LogoPlayer lp) {
    boolean exitRequested = false;

    System.out.println("forwards, backwards, left, right for movement, exit to quit");
    while (!exitRequested) {
      System.out.print("logo> ");
      String command = sc.nextLine().trim();
      if (command.equals("exit")) {
        exitRequested = true;
      } else {
        try {
          runCommand(command, lp);
        } catch (BadCommandException bce) {
          System.out.println("unknown command");
        }
      }
    }
  }

  private static void batch(Scanner sc, LogoPlayer lp) {
    int lineNumber = 1;
    while (sc.hasNextLine()) {
      String command = sc.nextLine().trim();
      try {
        runCommand(command, lp);
      } catch (BadCommandException bce) {
        System.out.println("unknown command on line " + lineNumber);
      }
      lineNumber++;
    }
  }

  public static void main(String[] args) {
    LogoPlayer lp = new LogoPlayer(new LogoViewer());

    Scanner sc;

    if (args.length == 0) {
      sc = new Scanner(System.in);
      interactive(sc, lp);
    } else if (args.length == 1) {
      try {
        sc = new Scanner(new File(args[0]));
        batch(sc, lp);
      } catch (FileNotFoundException fnf) {
        System.out.println("script not found");
        return;
      }
    } else {
      System.out.println("invalid number of arguments: 0 for interactive or 1 for script file");
    }
  }
}
