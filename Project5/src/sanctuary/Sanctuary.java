package sanctuary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * This is the Sanctuary class, which has 2 types of housing: Isolation cages and Enclosures
 * Isolation cages contains a list of 20 cages
 * Enclosures contains different types of troops
 * Name: XIN DING
 * Date: 4/8/2024
 */
public class Sanctuary {
  private final List<IsolationCage> isolationCages;
  private final Map<PrimateType, Enclosure> enclosures;

  /**
   * This is the constructor of the Sanctuary class
   * isolationCages is initialized as an array of 20 empty cages that holds one primate in each cage
   * enclosures is initialized as an empty map with primate type as key and enclosure as value
   */
  public Sanctuary() {
    isolationCages = new ArrayList<>();
    for (int i = 0; i < 20; i++) {
      isolationCages.add(new IsolationCage());
    }
    enclosures = new TreeMap<>();
  }

  /**
   * This is the helper function that helps sort the isolationCages list
   * For filled cage, it is sorted lexicographical by primate's name, the empty cages are pushed to the back
   */
  public void sortList() {
    isolationCages.sort((c1, c2) -> {
      if (c1.getCage() == null && c2.getCage() == null) return 0;
      if (c1.getCage() == null) return 1; // Empty cages go to the end
      if (c2.getCage() == null) return -1;
      return c1.getCage().getName().compareTo(c2.getCage().getName());
    });
  }

  /**
   * Add a new primate to the sanctuary
   * @param primate, the primate to be added, goes to isolation cage
   * @throws IllegalStateException if all the isolation cages are occupied
   * Maintain the list order by sorting the list after move
   */
  public void addNewPrimate(Primate primate) throws IllegalStateException {
    boolean isAdded = false;
    for (IsolationCage cage : isolationCages) {
      if (cage.isEmpty()) {
        cage.addPrimate(primate);
        isAdded = true;
        break;
      }
    }
    if (!isAdded) {
      throw new IllegalStateException("Isolation cages are ALL full!");
    }
    sortList();
  }

  /**
   * Move the primate with the input name from isolation cage to its target enclosure
   * @param name, the name of the primate to be moved
   * @throws IllegalArgumentException if the primate is not found in the location cage
   * Maintain the list order by sorting the list after move
   */
  public void movePrimateToEnclosure(String name) throws IllegalArgumentException {
    boolean isMoved = false;
    Primate current = null;
    // remove from isolation cage
    for (IsolationCage cage : isolationCages) {
      if (!cage.isEmpty()) {
        current = cage.getCage();
        if (current.getName().equals(name) && current.checkHealth()) {
          cage.removePrimate(name);
          isMoved = true;
          break;
        }
      }
    }
    if (!isMoved) {
      throw new IllegalArgumentException(name + " is not found!");
    }
    // add to the enclosure
    PrimateType currentType = current.getType();
    if (!enclosures.containsKey(currentType)) {
      Enclosure newEnclosure = new Enclosure(currentType);
      newEnclosure.addPrimate(current);
      enclosures.put(currentType, newEnclosure);
    } else {
      enclosures.get(currentType).addPrimate(current);
    }
    sortList();
  }

  /**
   * Move all the sick primates in any enclosure to the isolation cage
   * Iterate though the enclosures map and include a sickList that helps with tracking
   */
  public void moveSickToIsolation() {
    for (Map.Entry<PrimateType, Enclosure> entry : enclosures.entrySet()) {
      Enclosure enclosure = entry.getValue();
      List<Primate> sickList = enclosure.removeAllSickPrimates();
      if (!sickList.isEmpty()) {
        for (Primate sickPrimate : sickList) {
          addNewPrimate(sickPrimate);
        }
      }
    }
  }

  /**
   *
   * @return the list of enclosures in the sanctuary
   */
  public List<Enclosure> getEnclosures() {
    List<Enclosure> eList = new ArrayList<>();
    for (Map.Entry<PrimateType, Enclosure> e : enclosures.entrySet()) {
      eList.add(e.getValue());
    }
    return eList;
  }

  /**
   *
   * @return the list of isolation cages in the sanctuary
   */
  public List<IsolationCage> getIsolationCages() {
    return this.isolationCages;
  }


  /**
   * @return the formatted string that prints out all the primates in the enclosures
   */
  public String printAllEnclosures() {
    StringBuilder acc = new StringBuilder();
    for (Enclosure e : getEnclosures()) {
      acc.append(e + ";\n");
    }
    return "{\n" +  acc + "}";
  }

  /**
   * @return the formatted string that prints out all the primates in isolation cages
   */
  public String printAllIsolations() {
    return "Isolations: " + getIsolationCages().toString();
  }

  /**
   * @return the formatted string of the primates in the entire sanctuary
   */
  @Override
  public String toString() {
    return printAllIsolations() + "; \nEnclosures: \n" + printAllEnclosures();
  }
}

