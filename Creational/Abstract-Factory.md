# Abstract Factory

[Main Page](..) → [Creational Patterns](.) → [Abstract Factory](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/creational/abstractfactory) | [refactoring.guru](https://refactoring.guru/design-patterns/abstract-factory) | [sourcemaking.com](https://sourcemaking.com/design_patterns/abstract_factory)

![Abstract Factory](https://refactoring.guru/images/patterns/content/abstract-factory/abstract-factory-en.png#center "Abstract Factory")

> Pembuatan industri furniture melibatkan beberapa basis model yang berbeda (misal sofa, lemari, meja, ranjang) meski pada dasarnya adalah Furniture.
>
> Karena pembuatan beberapa model furniture tidak sama ragamnya karena mencakup material, rangka, dan slot yang berbeda dan tidak semua furniture memilikinya, maka penggunaan Abstract Factory sangat dibutuhkan dalam pembuatan ragam model furniture dengan jenis model & style yang berbeda-beda.

**Factory method dan Abstract factory** adalah 2 design pattern yang menerapkan teknik-teknik deklarasi class yang dilakukan dengan melakukan passing parameter yang diberikan oleh Client untuk mengembalikan Object yang dibuat oleh class perantaranya yaitu **Factory**.

**Abstract factory** pada dasarnya merupakan **factory method** dengan berbagai macam variasi yang dapat dibuat oleh sebuah **Factory**. Misalnya dalam suatu kasus, factory method hanya memproduksi 1 jenis object saja, maka abstract factory dapat memproduksi berbagai jenis object.

**Abstract factory** menggunakan **sekumpulan abstract method** yang merepresentasikan beberapa jenis class untuk memanggil/mendeklarasikan object class yang dilakukan oleh subclass dengan variasi object yang berbeda-beda.

![Ragam model furniture](https://refactoring.guru/images/patterns/diagrams/abstract-factory/problem-en.png#center "Ragam model furniture")

Berbeda dengan Factory method, Abstract class **mampu mengembalikan/memproduksi 2 atau lebih jenis class beserta turunannya** dimana pembuatan object mencakup beberapa model class dengan pengerjaan masing-masing model terfokuskan pada konteks yang berlaku.

## UML Model

![Abstract Factory](../assets/img/creational/abstract-factory.png#center "Abstract Factory")

## Contoh kasus: Furniture

Dalam kasus pembuatan furniture, furniture dibuat melalui `FurnitureFactory` dimana terdapat beberapa model yang dapat dibuat melalui beberapa factory method (misalnya sofa, lemari, meja, dll.) dimana setiap factory mempunyai cara pembuatan objectnya masing-masing dilakukan secara tersendiri oleh turunan Factory-nya (misal `VictorianFurnitureFactory` yang memproduksi berbagai macam furniture dengan style *Victorian*).

### Class Diagram

![Furniture Factory Class Diagram](../assets/img/creational/abstract-factory-uml.png#center "Furniture Factory Class Diagram")

### Implementasi Code

```java
public interface Furniture {
  void assemble();
  String describe();

  @Override
  String toString();

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
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(describe());
    sb.append("\nMaterial: ").append(getMaterial());
    sb.append("\nCapacity: ").append(getCapacity());
    return sb.toString();
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
  
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(describe());
    sb.append("\nMaterial: ").append(getMaterial());
    sb.append("\nCover: ").append(getCover());
    return sb.toString();
  }
}
```

```java
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

public class MedievalSofa extends Sofa {
  public void assemble() {
    // assembling Medieval-styled sofa...
  }

  public String describe() {
    return "Medieval Sofa";
  }
}

public class MedievalBed extends Bed {
  public void assemble() {
    // assembling Medieval-styled bed...
  }

  public String describe() {
    return "Medieval Bed";
  }
}
// more furniture-derived style classes
```

```java
public interface FurnitureFactory {
  Sofa createSofa();
  Bed createBed();
}

public class VictorianFurnitureFactory implements FurnitureFactory {
  public Sofa createSofa() {
    return new VictorianSofa();
  }

  public Bed createBed() {
    return new VictorianBed();
  }
}

public class MedievalFurnitureFactory implements FurnitureFactory {
  public Sofa createSofa() {
    return new MedievalSofa();
  }

  public Bed createBed() {
    return new MedievalBed();
  }
}
```

Anda juga bisa mendeklarasikan model-model factory sesuai kebutuhan object dengan menurunkan FurnitureFactory sebagai class turunannya, dimana anda juga bisa mendeklarasikan object sesuai kebutuhan factory dan client class sesuai dengan contoh kasus `ChandraFurniture` berikut:

```java
public class ChandraFurnitureFactory implements FurnitureFactory {
  public Sofa createSofa() {
    return new VictorianSofa();
  }

  public Bed createBed() {
    return new MedievalBed();
  }
}
```

Sederhananya, client class tidak perlu mengetahui style furniture yang ingin mereka karena pembuatan style furniture sudah diserahkan langsung ke factory sesuai prinsip *Liskov-Subtitution Principle* (LSP).

Dalam kasus dalam tangan client (misal class `AgenFurniture` sebagai agen), client dapat bebas memilih jenis factory sesuai style yang ia  inginkan (misal `ChandraFurnitureFactory` dengan perpaduan style `VictorianSofa` dan `MedievalBed`) yang bila didefinisikan dalam code terancang sebagai berikut:

```java
public class AgenFurniture {
  public void createFurnitureSets() {
    FurnitureFactory furnitureFactory = new ChandraFurnitureFactory();

    Furniture sofa = furnitureFactory.createSofa(); // Victorian
    Furniture ranjang = furnitureFactory.createFurniture(); // Medieval

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


## Catatan tambahan: Return type overriding

Dalam kasus inheritance, pengguna dapat melakukan override return type meski tidak dibahas secara langsung dalam pembahasan mengenai inheritance. Namun dalam melakukan return type overriding, pengguna harus memastikan bahwa object yang direturn pada method yang _dioverride_ return type-nya harus sama dan compatible dengan method dari superclass (misal Sofa dan Bed yang sama-sama dari Furniture).

Selain itu, method yang dioverride return type **tidak boleh menggunakan superclass maupun sibling class _(sesaudara dengan return type class)_** sebagai return typenya ketika method yang dihasilkan menggunakan return type berupa object subclass (misal return type pada SofaFactory adalah Sofa, maka tidak boleh menggunakan Furniture maupun Bed sebagai return typenya).

Salah satu contoh yang dapat diperbarui dengan return type overriding adalah `SofaFactory` dengan abstract factory `FurnitureFactory` di bawah:

```java
public interface FurnitureFactory {
  Furniture createFurniture(String type);
}

public class SofaFactory implements FurnitureFactory {
  public Sofa createFurniture(String type) {
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
```

Ketika class `SofaFactory` melakukan return dengan tipe data `Sofa`, maka class pemakai dengan deklarasi object class `Sofa` tidak perlu melakukan typecast dengan return type `Sofa` dan deklarasi object `Furniture` cukup menggunakan object tersebut sebagaimana fungsinya inheritance dan polymorphism berlaku.

```java
public class Client {
  private Sofa sofa;

  /**
   * Client class dapat return object method yang telah dioverride tanpa harus typecast ke model yang ditentukan
   */
  public void createSofa(String jenisSofa) {
    SofaFactory sofaFactory = new SofaFactory();
    sofa = sofaFactory.createFurniture(jenisSofa); // no typecasting errors
  }
}
```


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 99-109.
- Refactoring.guru (Abstract Factory, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/abstract-factory](https://refactoring.guru/design-patterns/abstract-factory)
- SourceMaking (Abstract Factory) - [https://sourcemaking.com/design_patterns/abstract_factory](https://sourcemaking.com/design_patterns/abstract_ _factory)
- Gang Of Four (GoF) Design Patterns: Abstract Factory - [https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java](https://www.journaldev.com/1418/abstract-factory-design-pattern-in-java)
