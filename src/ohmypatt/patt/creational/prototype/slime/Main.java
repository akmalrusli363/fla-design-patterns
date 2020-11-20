package ohmypatt.patt.creational.prototype.slime;
public class Main {
  public static void main(String[] a) {
    Slime slime = new Slime(5);
    System.out.println("Parent before split: " + slime.toString() + "\n");

    try {
      Slime[] splits = slime.split();
      slime.setSize(3);
      slime.setColor(Slime.BLUE_SLIME);
      System.out.println("Parent after split: " + slime.toString() + "\n");

      System.out.println("Splits:");
      Slime sk = null;
      for (Slime s : splits) {
        System.out.println(s.toString());
        System.out.println((sk != null) ? "Equal to previous clones? " + sk.equals(s) : "First clone instance");
        sk = s;
      }
    } catch (Exception e) {
        System.out.println(e.toString());
    }
  }
}