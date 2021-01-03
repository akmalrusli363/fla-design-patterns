package ohmypatt.patt.structural.decorator.armory.model;
// Decorator / Wrapper
public abstract class ArmorDecorator implements Armor {
  protected Armor innerSkin; // alias wrapee
  protected String name;

  public ArmorDecorator(Armor innerSkin, String name) {
    this.innerSkin = innerSkin;
    this.name = name;
  }

  public String getName() {
    return name;
  }
}