package ohmypatt.patt.creational.prototype.slime;
public class Slime implements Cloneable {
  private int size;
  private float damage;
  private String color;

  // slime color choices (or predefine it yourself)
  public static final String GREEN_SLIME = "Green";
  public static final String BLUE_SLIME = "Blue";

  // base damage for slime with size of 2
  private static final int BASE_DAMAGE = 10;
  private static final int BASE_SIZE = 2;
  private static final String DEFAULT_SLIME_COLOR = GREEN_SLIME;

  public Slime() {
    this(BASE_SIZE);
  }

  public Slime(int size) {
    this(size, DEFAULT_SLIME_COLOR);
  }

  public Slime(int size, String color) {
    setSize(size);
    this.color = color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getColor() {
    return color;
  }

  public void setSize(int size) {
    this.size = (size < 1) ? 1 : size;
    setDamage(size);
  }

  public int getSize() {
    return size;
  }

  public void setDamage(int size) {
    this.damage = (size * BASE_DAMAGE) / 2;
  }

  public float getDamage() {
    return damage;
  }

  /**
   * The clone() method are used for cloning object. If not supported, use copy constructor as replacement for object cloning.
   * @return cloned object
   */
  @Override
  public Slime clone() {
    try {
        return (Slime) super.clone();
    } catch (CloneNotSupportedException e) {
        return new Slime(size, color);
    }
  }

  public Slime grow() {
    Slime upgraded = clone();
    upgraded.setSize(upgraded.size * 2);
    return upgraded;
  }

  public Slime shrink() {
    Slime shrunk = clone();
    shrunk.setSize(size / 2);
    return shrunk;
  }

  public Slime[] split() throws Exception {
    if (size <= 1) {
      throw new Exception("This slime's size is too small to split!");
    }

    int numOfSplits = getRandomSlimeSplits();
    Slime[] splitSlimes = new Slime[numOfSplits];
    for (int i = 0; i < numOfSplits; i++) {
      splitSlimes[i] = this.shrink();
    }

    return splitSlimes;
  }

  private int getRandomSlimeSplits() {
    int maxSplits = 6;
    int minSplits = 2;
    return (int) Math.round((Math.random() * (maxSplits - minSplits)) + minSplits);
  }

  public String toString() {
      return String.format("Slime (color: %s | size: %d | damage: %.2f HP)", color, size, damage);
  }
}