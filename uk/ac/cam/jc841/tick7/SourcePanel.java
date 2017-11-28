package uk.ac.cam.jc841.tick7;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public abstract class SourcePanel extends JPanel {

  private JRadioButton current;

  public SourcePanel() {
    super();
    setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
    final JRadioButton none = new JRadioButton(Strings.BUTTON_SOURCE_NONE, true);
    final JRadioButton file = new JRadioButton(Strings.BUTTON_SOURCE_FILE, true);
    final JRadioButton library = new JRadioButton(Strings.BUTTON_SOURCE_LIBRARY, true);
    final JRadioButton fourStar = new JRadioButton(Strings.BUTTON_SOURCE_FOURSTAR, true);
    //add RadioButtons to this JPanel
    add(none);
    add(file);
    add(library);
    add(fourStar);
    //create a ButtonGroup containing all four buttons
    //Only one Button in a ButtonGroup can be selected at once
    ButtonGroup group = new ButtonGroup();
    group.add(none);
    group.add(file);
    group.add(library);
    group.add(fourStar);

    none.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (setSourceNone()) {
          current = none;
        } else {
          current.setSelected(true);
        }
      }
    });

    file.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (setSourceFile()) {
          current = file;
        } else {
          current.setSelected(true);
        }
      }
    });

    library.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (setSourceLibrary()) {
          current = library;
        } else {
          current.setSelected(true);
        }
      }
    });

    fourStar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (setSourceFourStar()) {
          current = fourStar;
        } else {
          current.setSelected(true);
        }
      }
    });

    current = none;
  }

  protected abstract boolean setSourceNone();
  protected abstract boolean setSourceFile();
  protected abstract boolean setSourceLibrary();
  protected abstract boolean setSourceFourStar();
}
