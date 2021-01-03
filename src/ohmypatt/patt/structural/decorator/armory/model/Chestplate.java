package ohmypatt.patt.structural.decorator.armory.model;
public class Chestplate implements Armor {
  private String name;
  public Chestplate(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public void attach() {
    System.out.println("Basic chestplate");
  }
}