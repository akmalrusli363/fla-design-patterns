# Creational Patterns

[Main Page](..) → [Creational Patterns](#)

<p align="center" style="text-align:center"><img src="/assets/img/pattern/creational.png" alt="Creational Patterns" height="200" class="thumbnail" /></p>

Merupakan design pattern yang berperan dalam pembuatan object maupun deklarasi object untuk mempermudah pemakaian kembali code dan meningkatkan fleksibilitas dalam hierarki class.

Ada 5 jenis creational patterns:

1.  Singleton
2.  Factory Method
3.  Abstract Method
4.  Builder
5.  Prototype

## Singleton

> The most basic & simplest type of creational design pattern. Just define a private constructor & call for a instance to define a object.

Singleton merupakan design pattern yang paling mudah & sederhana yang bertujuan untuk membatasi deklarasi menjadi hanya 1 instance/object dalam sebuah class. Dalam Singleton, hanya ada 1 object dari class tersebut yang dapat dibuat untuk class client, yaitu _instance_ itu sendiri. Setiap pemanggilan object berikutnya yang dilakukan oleh client, object akan dipanggilkan langsung dari instance yang sudah dibuatkan oleh class tersebut.

Sederhananya, client tidak bisa membuat object dari suatu class lebih dari 1 kali, sebaliknya object tersebut kemudian memanggil instance/object pertama sudah dibuatkan oleh class tersebut.

Contoh code:

```java
public class Storage {
  private Storage() {}

  /**
   * An instance for Storage, initially to be null before client class invoke to define this object for first time!
   * For next declarations, it will return this object defined from this class since first declaration.
   */
  private volatile static Storage instamce = null;

  public synchronized static Storage getInstance() {
    if (instance == null)
       instance = new Storage();
    return instance;
  }

  // beyond methods and declaration codes inside...

}
```

Atau jika disederhanakan:

```java
public class MySingleton {
  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  private volatile static MySingleton instamce = null;

  public synchronized static MySingleton getInstance() {
    if (instance == null) {
      instance = new MySingleton();
    }
    return instance;
  }

  // TODO declaration and methods here...
}
```

## Factory Method

Factory method menggunakan satu abstract method untuk memanggil/mendeklarasikan class yang dilakukan oleh subclass yang mendeklarasikan **sebuah** object (misal SmartphoneFactory menggunakan `createSmartphone()` untuk bikin Smartphone. Variasi-variasi yang dilakukan oleh factory terbatas pada 1 object class saja, yaitu Smartphone)

Contoh code:

```java
public interface SmartphoneFactory {
  public Smartphone createSmartphone(String type);
}

public class SimsongFactory implements SmartphoneFactory {
  public Smartphone createSmartphone(String type){
    Smartphone smartphone = null;
    if (type.equals("Galaxy Prime")) {
      smartphone = new SimsongGalaxyPrime();
    } else if (type.equals("Galaxy Note")) {
      smartphone = new SimsongGalaxyNote();
    } else if (type.equals("Fold Z")) {
      smartphone = new SimsongFoldZ();
    }
    return smartphone;
  }
}

public abstract class Smartphone {
  private float screenSize;
  private int capacity;
  private String deviceType;

  public Smartphone() {
    assemble();
  }

  public abstract void assemble();

  public abstract String describe();

  public float getScreenSize() {
    return screenSize;
  }

  public void setScreenSize(float screenSize) {
    this.screenSize = screenSize;
  }

  public int getCapacity() {
    return capacity;
  }

  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }

  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }

  @Override
  public String toString() {
    return String.format("%s (screen size: %.2f inch | capacity: %dGB | type: %s)",
        describe(), screenSize, capacity, deviceType);
  }
}

public class SimsongGalaxyPrime extends Smartphone {
  public void assemble() {
    setCapacity(64);
    setDeviceType("full-screen display");
    setScreenSize(5.99f);
    System.out.println("Assembling Simsong Galaxy Prime...");
  }

  public String describe() {
    return "Simsong Galaxy Prime";
  }
  // some codes...
}

public class SimsongGalaxyNote extends Smartphone {
  public void assemble() {
    setCapacity(128);
    setDeviceType("phablet");
    setScreenSize(6.6f);
    System.out.println("Assembling Simsong Galaxy Note...");
  }

  public String describe() {
    return "Simsong Galaxy Note";
  }
  // some codes...
}

public class SimsongFoldZ extends Smartphone {
  public void assemble() {
    setCapacity(512);
    setDeviceType("folded");
    setScreenSize(12.1f);
    System.out.println("Assembling Simsong Fold Z...");
  }

  public String describe() {
    return "Simsong Fold Z";
  }
  // some codes...
}
```

## Abstract Factory

Factory method menggunakan satu abstract method untuk memanggil/mendeklarasikan class yang dilakukan oleh subclass yang mendeklarasikan **berbagai macam** object (misal FurnitureFactory menggunakan `createFurniture()` untuk bikin Furniture beserta turunan modelnya (sofa, lemari, meja, dll.))

Contoh code:

```java
public interface FurnitureFactory {
  public Furniture createFurniture(String type);
}

public class SofaFactory implements FurnitureFactory {
  public Furniture createFurniture(String type) {
    Sofa sofa = null;
    if (type.equals("Victorian")) {
      sofa = new VictorianSofa();
    } else if (type.equals("Cyber")) {
      sofa = new CyberSofa();
    } else if (type.equals("Medieval")) {
      sofa = new MedievalSofa();
    }
    return sofa;
  }
}

public class BedFactory implements FurnitureFactory {
  public Furniture createFurniture(String type) {
    Bed bed = null;
    if (type.equals("Victorian")) {
      bed = new VictorianBed();
    } else if (type.equals("Cyber")) {
      bed = new CyberBed();
    } else if (type.equals("Medieval")) {
      bed = new MedievalBed();
    }
    return bed;
  }
}

public interface Furniture {
  public void assemble();
  public String describe();

  @Override
  public String toString();

  default String getFullDescription() {
    return toString();
  }
}

public abstract class Sofa implements Furniture {
  private String material;
  private int capacity;

  public Sofa() {
    assemble();
    System.out.println("Building sofa...");
  }

  public void setMaterial(String material) {
    this.material = material;
  }
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  public String getMaterial() {
    return material;
  }
  public int getCapacity() {
    return capacity;
  }
}

public abstract class Bed implements Furniture {
  private String cover, material;

  public Bed() {
    assemble();
    System.out.println("Building bed...");
  }

  public void setMaterial(String material) {
    this.material = material;
  }
  public void setCover(String cover) {
    this.cover = cover;
  }
  public String getMaterial() {
    return material;
  }
  public String getCover() {
    return cover;
  }
}

public class VictorianSofa extends Sofa {
  public void assemble() {
    // assembling Victorian-styled sofa...
  }

  public String describe() {
    return "Victorian Sofa";
  }
}

public class VictorianBed extends Bed {
  public void assemble() {
    // assembling Victorian-styled bed...
  }

  public String describe() {
    return "Victorian Bed";
  }
}

public class CyberSofa extends Sofa {
  public void assemble() {
    // assembling Cyber-styled sofa...
  }

  public String describe() {
    return "Cyber-styled Sofa";
  }
}

// more furniture-derived style classes
```

Dalam kasus dalam tangan client (misal class `ChandraFurniture`), client dapat bebas memilih jenis factory dan style yang ia inginkan (misal Sofa dan Bed factory dengan perpaduan style VictorianSofa dan MedievalBed) yang bila didefinisikan dalam code terancang sebagai berikut:

```java
public class ChandraFurniture {
  public void createFurnitureSets() {
    SofaFactory pabrikSofa = new SofaFactory();
    BedFactory pabrikRanjang = new BedFactory();

    Furniture sofa = pabrikSofa.createFurniture("Victorian");
    Furniture ranjang = pabrikRanjang.createFurniture("Medieval");

    System.out.println("------------------");

    System.out.println("Chandra Victorian Sofa:");
    System.out.println(sofa.getFullDescription());

    System.out.println("------------------");

    System.out.println("Chandra Medieval Sofa:");
    System.out.println(ranjang.getFullDescription());

    System.out.println("------------------");

    System.out.println("Selamat menikmati produk furniture kami :D");
  }
}
```

## Builder

Builder menggunakan beberapa method/object yang dideklarasikan oleh client maupun *director* untuk kemudian dibuatkan/dideklarasikan/dikembalikan sebagai sebuah object.

Berbeda dengan Constructor yang mengharuskan Client untuk memasukkan beberapa parameter untuk mendeklarasikan object (dimana dalam beberapa kasus tertentu, beberapa parameter tidak pernah digunakan alias _useless_), Builder class dapat memberikan fleksibilitas Client dalam menentukan/mendeklarasikan object yang mereka inginkan tanpa harus bergantung pada setiap attribute yang mereka inginkan.

Salah satu contoh Builder adalah penerapan pembuatan robot dimana dalam class Robot terdapat banyak variasi yang dapat dipilih oleh user (misal dengan barang yang mereka miliki seperti Sword, Gun, Brainchip, dan Shield) dimana Client/pengguna tidak harus menggunakan semua properti yang mereka miliki untuk menciptakan object.

```java
public class Robot {

  private String shield, sword, gun, brainchip;

  /**
   * Declare empty constructor which able to create basic Robot parts without
   * infer too long parameters to declare an object
   */
  public Robot() {
  }

  public String getShield() {
    return shield;
  }

  public void setShield(String shield) {
    this.shield = shield;
  }

  public String getSword() {
    return sword;
  }

  public void setSword(String sword) {
    this.sword = sword;
  }

  public String getGun() {
    return gun;
  }

  public void setGun(String gun) {
    this.gun = gun;
  }

  public String getBrainchip() {
    return brainchip;
  }

  public void setBrainchip(String brainchip) {
    this.brainchip = brainchip;
  }

  @Override
  public String toString() {
    return "Robot Properties"
        + "\n-----------"
        + "\nShield: " + getShield()
        + "\nGun: " + getGun()
        + "\nSword: " + getSword()
        + "\nBrain chip: " + getBrainchip();
  }

}

public class RobotBuilder {
  private Robot robot;

  public RobotBuilder() {
    this(new Robot());
  }

  public RobotBuilder(Robot robot) {
    this.robot = robot;
  }

  public RobotBuilder shield(String shield) {
    robot.setShield(shield);
    return this;
  }

  public RobotBuilder gun(String gun) {
    robot.setGun(gun);
    return this;
  }
  public RobotBuilder sword(String sword) {
    robot.setSword(sword);
    return this;
  }
  public RobotBuilder brainchip(String brainchip) {
    robot.setBrainchip(brainchip);
    return this;
  }

  public Robot getResult() {
    return robot;
  }

}
```

Untuk menutup/membatasi deklarasi pembuatan class maupun memberikan pilihan tertentu yang ditujukan kepada Client class, dapat dipergunakan **Director** class untuk mendelegasikan deklarasi class sesuai keinginan Client/user melalui beberapa Object method seperti yang dibahas di bawah:

```java
public class RobotDirector {

  // Declare Singleton
  private static RobotDirector director = null;

  private RobotDirector() {}

  public static synchronized RobotDirector getInstance() {
    if (director == null) {
      director = new RobotDirector();
    }
    return director;
  }

  public Robot CasualRobot() {
    RobotBuilder builder = new RobotBuilder();
    builder.brainchip("Kasula Brain");
    builder.sword("Taito Ward");
    builder.gun("P30");

    return builder.getResult();
  }

  public Robot IntelligentRobot() {
    RobotBuilder builder = new RobotBuilder();
    builder.brainchip("AI-Powered Brain");
    builder.shield("SAS Shield");
    builder.gun("M1A4 with SmartScope");
    builder.sword("Wielded Blade Arm");

    return builder.getResult();
  }

  public Robot RereRobot() {
    RobotBuilder robotBuilder = new RobotBuilder();
    Robot bot = robotBuilder.brainchip("i3").gun("Desert Eagle").getResult();
    return bot;
  }

}
```

Karena Director class merupakan class yang dipanggilkan secara universal oleh Client & tidak memerlukan instance, maka class tersebut sebaiknya dideklarasikan sebagai Singleton class untuk menghindari deklarasi ganda yang tidak diperlukan oleh class/instance lain atau sebagai static class yang cukup dipanggil langsung dari class tanpa mendeklarasikan object.

## Prototype

Segera...

## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Refactoring.guru - [https://refactoring.guru/design-patterns/creational-patterns](https://refactoring.guru/design-patterns/creational-patterns)
- Sourcemaking.com - [https://sourcemaking.com/design_patterns/creational_patterns](https://sourcemaking.com/design_patterns/creational_patterns)
- Gang Of Four (GoF) Design Patterns - [https://www.journaldev.com/31902/gangs-of-four-gof-design-patterns](https://www.journaldev.com/31902/gangs-of-four-gof-design-patterns)
