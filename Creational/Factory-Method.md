# Factory Method

[Main Page](..) → [Creational Patterns](.) → [Factory Method](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/creational/abstractfactory) | [refactoring.guru](https://refactoring.guru/design-patterns/factory-method) | [sourcemaking.com](https://sourcemaking.com/design_patterns/factory_method)

![Factory Method](/assets/img/creational/factory-method.png#center "Factory Method")

> Pembuatan industri perangkat elektronik semakin hari semakin maju dengan kehadiran berbagai jenis variasi perangkat elektronik termasuk kehadiran Smartphone dengan berbagai jenis model mulai dari smartphone konvensional, tablet, dan yang terbaru, smartphone lipat.
>
> Kehadiran banyak variasi Smartphone dengan spesifikasi yang kurang lebih sama basis modelnya menunjukkan bahwa Factory Method cukup berperan dalam merakit 1 basis model smartphone meski dengan ragam model yang banyak & beragam.

Factory method dan Abstract factory adalah 2 design pattern yang menerapkan teknik-teknik deklarasi class yang dilakukan dengan melakukan passing parameter yang diberikan oleh Client untuk mengembalikan Object yang dibuat oleh class perantaranya yaitu **Factory**.

**Factory method** menggunakan satu abstract method untuk memanggil/mendeklarasikan class yang dilakukan oleh subclass yang mendeklarasikan **sebuah** object.

Berbeda dengan Abstract factory, Factory method **hanya mengembalikan/memproduksi 1 jenis class beserta turunannya** dimana pembuatan object terbatas pada 1 model class saja (misal SmartphoneFactory menggunakan createSmartphone() untuk bikin Smartphone. Variasi-variasi yang dilakukan oleh factory terbatas pada 1 object class saja, yaitu Smartphone).

## Contoh kasus: Smartphone

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


## Catatan tambahan

Penggunaan **Factory Method** dapat dipergunakan pada class-class lain tanpa harus mengimplementasikan factory class tersendiri asalkan client class tersebut telah mengimplementasikan interface yang berisikan **Factory Method** yang return type berlaku.


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126.
- Refactoring.guru (Factory Method, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/factory-method](https://refactoring.guru/design-patterns/factory-method)
- SourceMaking (Factory Method) - [https://sourcemaking.com/design_patterns/factory_method](https://sourcemaking.com/design_patterns/factory_method)
- Gang Of Four (GoF) Design Patterns: Factory - [https://www.journaldev.com/1392/factory-design-pattern-in-java](https://www.journaldev.com/1392/factory-design-pattern-in-java)
