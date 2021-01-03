package ohmypatt.patt.structural.decorator.armory.model;
public class Wings extends ArmorDecorator {
  public Wings(Armor innerSkin, String name) {
    super(innerSkin, name);
  }

  @Override
  public void attach() {
    this.innerSkin.attach();
    System.out.printf("Attaching wing (%s) to your armor...\n", name);
  }
}

