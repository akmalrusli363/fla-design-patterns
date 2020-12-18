# Template Method

[Main Page](..) → [Behavioral Design Patterns](.) → [Template Method](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/templatemethod) | [refactoring.guru](https://refactoring.guru/design-patterns/template-method) | [sourcemaking.com](https://sourcemaking.com/design_patterns/template_method)

![Template Method](../assets/img/behavioral/template-method.png#center "Template Method")

Template Method merupakan design pattern dimana dalam sebuah abstract class yang memiliki satu atau lebih abstract method yang harus didefinisikan oleh class turunannya. Selain memiliki abstract method, sebuah abstract class juga mempunyai satu atau lebih concrete method yang akan memanggil abstract method tersebut.

Template Method memungkinkan sebuah class untuk mendefinisikan method yang memanggil method-method abstract yang akan digunakan oleh subclassnya. Misalnya sebuah abstract class yang berisikan beberapa method abstract/*override-able* yaitu `boilWater()`, `prepareIngredients()`, `cook()`, dan `serve()` yang dipanggil oleh sebuah method concrete bernama `createSoup()`.

![Hollywood Principle](../assets/img/behavioral/template-method-hollywood.png#center "Hollywood Principle")

Dalam template method, isi-isi deklarasi dari method abstract/override-able dapat ditentukan langsung dari parent class maupun ditentukan sendiri oleh subclassnya. Sebaliknya, class pemanggil *(parent class)* cukup memanggil method yang berisikan method-method *(abstract/override-able)* melalui concrete method (template). Ketika Client ingin menyiapkan produk yang diinginkan, Client cukup memanggil parent class saja tanpa harus memanggil isi method maupun memanggil method dari subclassnya (mengikuti Hollywood Principle).


## Template

```java
public abstract class BaseClass {
  /**
   * A concrete method that not override-able
   */
  public final void trigger() {
    doSomething();
    doOtherSteps();
    storeData();
  }

  protected abstract void doSomething();

  protected abstract void doOtherSteps();

  protected void storeData() {
    System.ou.println("Store triggered things to disk...");
  }
}

public class SubClassA extends BaseClass{
  @Override
  protected void doSomething() {
    System.out.println("Makan");
  }

  @Override
  protected void doOtherSteps() {
    System.out.println("Tidur");
  }
}

public class SubClassB extends BaseClass{
  @Override
  protected void doSomething() {
    System.out.println("Ngegame");
  }

  @Override
  protected void doOtherSteps() {
    System.out.println("Mabar");
  }

  /**
   * Overriden from concrete method: storeData()
   */
  @Override
  protected void storeData() {
    System.out.println("Lupakan!");
  }
}
```


## Contoh Kasus

Sebuah warung makan di Jakarta menyajikan 3 jenis penyajian mie yaitu mie goreng, mie ayam, dan mie kuah. Ketiga jenis penyajian mie ini mempunyai cara penyajian yang berbeda-beda misalnya Mie goreng dimana mie harus direbus, ditiriskan airnya, dan diberi bumbu sebelum disajikan; Mie ayam dimana mie direbus, ayam dimasak terpisah, ditiriskan airnya, dan diberikan sayuran dan daun bawang; dan Mie kuah dimana mie direbus dan disajikan bersama kuah dan bumbu pelengkapnya.

Anggap kita mendefinisikan sebuah base class sebagai berikut:

```java
public abstract class Noodle {
  protected String name = "";

  public Noodle() {
  }

  public Noodle(String name) {
    this.name = name;
  }

  public final void makeNoodle() {
    boilWater();
    prepareIngredients();
    cook();
    serve();
  }

  protected void boilWater() {
      System.out.println("Boiling water...");
  }

  protected abstract void prepareIngredients();

  protected void cook() {
    System.out.println("Cooking noodle...");
  }

  protected abstract void serve();

  public abstract String getName();
}
```

Ketika user ingin menyajikan Mie, tidak semua jenis penyajian mie memiliki cara penyajian yang sama. Ada yang kuahnya ditiriskan, ada yang ditiriskan sebagaian kuahnya, dan ada juga yang disajikan beserta kuahnya. Selain masalah penyajian, sebagian orang juga terkadang mempunyai cara masak mie yang berbeda (misal masak mie 3 menit **tidak sama** dengan masak mie dan pangsit sekaligus selama 5 menit).

Untuk subclassnya, kita dapat mendefinisikan 3 class yang diturunkan dari parent class `Noodle` sebagai berikut:

```java
/* Mie Goreng */
public class FriedNoodle extends Noodle {
  public FriedNoodle() {
    super();
  }

  public FriedNoodle(String name) {
    super(name);
  }

  @Override
  protected void prepareIngredients() {
    System.out.println("Put seasoning and ketchup to plate/bowl..");
  }

  @Override
  protected void serve() {
    System.out.println("Drain the water from noodle..");
    System.out.println("Serving dried noodle to plate/bowl");
  }

  @Override
  public String getName() {
    return "Mie Goreng " + name;
  }
}
```

```java
/* Mie Kuah */
public class NoodleSoup extends Noodle {
  public NoodleSoup() {
    super();
  }

  public NoodleSoup(String name) {
    super(name);
  }

  @Override
  protected void prepareIngredients() {
    System.out.println("Put seasoning to pan..");
  }

  @Override
  protected void serve() {
    System.out.println("Serving noodle together with soup to bowl");
  }

  @Override
  public String getName() {
    return "Mie Kuah " + name;
  }
}
```

```java
/* Mie Ayam */
public class ChickenNoodle extends Noodle {
  public ChickenNoodle() {
    super();
  }

  public ChickenNoodle(String name) {
    super(name);
  }

  @Override
  protected void prepareIngredients() {
    System.out.println("Put seasoning to plate/bowl..");
  }

  /* This way is different from NoodleSoup and FriedNoodle
   * because of chicken & dumpling use in cook() */
  @Override
  protected void cook() {
    System.out.println("Cooking minced chicken and dumpling...");
    System.out.println("Cooking noodle...");
  }

  @Override
  protected void serve() {
    System.out.println("Drain the water from noodle..");
    System.out.println("Mix served chicken noodle to plate/bowl..");
    System.out.println("Prepare dumpling and vegetables to served noodle");
  }

  @Override
  public String getName() {
    return "Mie Ayam " + name;
  }
}
```

Ketika Client ingin menyajikan pesanan dari konsumen dengan beragam pilihan penyajian mie, Client cukup mendeklarasikan parent class dengan object berupa subclassnya. Contoh deklarasinya sebagai berikut:

```java
Noodle myNoodle = new FriedNoodle();
myNoodle.serve();
myNoodle = new NoodleSoup();
myNoodle.serve();
```

Apabila diaplikasikan ke dalam sebuah aplikasi Order, Client dapat membuatkan sistem order berbasis Template Method Design Pattern sebagai berikut:

```java
public class Order {
  private ArrayList<Noodle> orders = new ArrayList<Noodle>();

  public void newOrder(Noodle noodle) {
    orders.add(noodle);
  }

  /**
   * Queue First-in-First-out
   */
  public void serve() {
    Noodle noodle = orders.get(0);
    System.out.println(">> Serving " + noodle.getName());
    noodle.makeNoodle();
    System.out.println();
    orders.remove(noodle);
  }
}
```

Ketika Client ingin memamerkan aplikasi ordernya, asumsikan dalam sebuah simulasi order didefinisikan dalam Main class, setiap mie yang dipesan nantinya disajikan dengan caranya masing-masing.

```java
public class Main {
  public Main() {
    Order order = new Order();

    System.out.println(">> Placing 4 orders...");
    order.newOrder(new NoodleSoup());
    order.newOrder(new ChickenNoodle());
    order.newOrder(new NoodleSoup("Soto Mie"));
    order.newOrder(new FriedNoodle());

    System.out.println(">> Serving orders one by one...");
  }

  public static void main(String[] args) {
    new Main();
  }
}
```


## Catatan

Karena Java membagikan keabstrakan class menjadi 2 jenis yaitu `abstract class` dan `interface`, maka dalam kasus Template Method disarankan untuk menggunakan `abstract class` agar dapat mendefinisikan isi-isi deklarasinya dalam 1 atau lebih concrete method dan menyediakan 1 atau lebih abstract method untuk diisi/ditentukan oleh subclass.

Meski di Java 8 sudah ada `default` method untuk mengisi implementasi dasar dalam `interface` dan dapat difungsikan sebagai template method, namun anda sebaiknya menggunakan `abstract class` jika template tersebut juga berisikan attribute dan mempunyai constructor di dalamnya.

Contoh `default` method di Interface:

```java
public interface InstantDrinkServing {
  /**
   * Berisikan default implementasi dari sebuah method yang peranannya
   * sama dengan abstract method
   */
  default void serve() {
    pourSachet();
    prepareWater();
    stir();
  }

  void pourSachet();
  void prepareWater();
  void stir();
}
```

Ketika subclass ingin mengimplementasikan interface `InstantDrinkServing`, kita tidak perlu mengubah isi method `serve()` karena sudah didefinisikan pada interface-nya. Sebagai contoh, dibuatkan 2 class yang bernama `Energen` dan `KopiSachet` yang tercantum sebagai berikut:

```java
public interface Energen implements InstantDrinkServing {
  void pourSachet() {
    System.out.println("Tuang sachet");
  }
  void prepareWater() {
    System.out.println("Siapkan air hangat");
  }
  void stir() {
    System.out.println("Aduk dan sajikan");
  }
}
```

```java
public interface KopiSachet implements InstantDrinkServing {
  void pourSachet() {
    System.out.println("Tuang sachet dan gula");
  }
  void prepareWater() {
    System.out.println("Siapkan air panas");
  }
  void stir() {
    System.out.println("Aduk dan sajikan");
  }
}
```

Sebaliknya, penggunaan keyword `final` pada sebuah method menandakan bahwa method tersebut tidak boleh di-override oleh subclass sama sekali.


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126.
- Refactoring.guru (Template Method) - [https://refactoring.guru/design-patterns/adapter](https://refactoring.guru/design-patterns/template-method)
- SourceMaking (Template Method) - [https://sourcemaking.com/design_patterns/adapter](https://sourcemaking.com/design_patterns/template_method)
- Gang Of Four (GoF) Design Patterns: Template Method - [https://www.journaldev.com/1487/adapter-design-pattern-java](https://www.journaldev.com/1487/adapter-design-pattern-java)
- Java SE 8 Documentation: Default Methods - [https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html](https://docs.oracle.com/javase/tutorial/java/IandI/defaultmethods.html)
