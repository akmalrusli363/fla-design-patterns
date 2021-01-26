# Singleton

[Main Page](..) → [Creational Patterns](.) → [Singleton](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/creational/singleton) | [refactoring.guru](https://refactoring.guru/design-patterns/singleton) | [sourcemaking.com](https://sourcemaking.com/design_patterns/singleton)

![Singleton](https://refactoring.guru/images/patterns/content/singleton/singleton.png#center "Singleton")

> The most basic & simplest type of creational design pattern. Just define a private constructor & call for a instance to define a object.

Singleton merupakan design pattern yang paling sederhana yang bertujuan untuk membatasi deklarasi menjadi hanya 1 instance/object dalam sebuah class. Dalam Singleton, hanya ada 1 object dari class tersebut yang dapat dibuat untuk class client, yaitu _instance_ itu sendiri. Setiap pemanggilan object berikutnya yang dilakukan oleh client, object akan dipanggilkan langsung dari instance yang sudah dibuatkan oleh class tersebut.

Sederhananya, client tidak bisa membuat object dari suatu class lebih dari 1 kali, sebaliknya object tersebut kemudian memanggil instance/object pertama sudah dibuatkan oleh class tersebut.

## Implementasi

### UML Model

![Singleton](../assets/img/creational/singleton.png#center "Singleton")

### Contoh code

Dalam kasus Singleton, kita merancang bentuk paling sederhana dari Singleton sebagai berikut:

```java
public class MySingleton {
  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  private static MySingleton instance = null;

  public static MySingleton getInstance() {
    if (instance == null) {
      instance = new MySingleton();
    }
    return instance;
  }

  // TODO declaration and methods here...
}
```

Dalam kasus multithreading, penggunaan Singleton dengan bentuk paling dasar sendiri tidaklah cukup apabila kita harus menghadapi banyak thread yang menggunakan Singleton tersebut sehingga dalam class tersebut dapat ditambahkan keyword `volatile` kepada object Singleton, `instance` untuk menampung variabel tersebut langsung ke *main memory* dan `synchronized` kepada method `getInstance()` untuk menjamin bahwa proses pengambilan instance tersebut *thread-safe*.

Hasil implementasi Singleton dalam kasus multithreading adalah sebagai berikut:

```java
public class MySingleton {
  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  private volatile static MySingleton instance = null;

  public synchronized static MySingleton getInstance() {
    if (instance == null) {
      instance = new MySingleton();
    }
    return instance;
  }

  // TODO declaration and methods here...
}
```

Hal tersebut juga ikut turut serta dalam implementasi kasus Singleton yaitu class Storage, dimana class tersebut menampung data-data produknya dalam `Vector<>` untuk menjamin ketersediaan produk dengan menggunakan kasus multithreading, seluruh deklarasi Singleton dideklarasikan dengan keyword `synchronized` pada method dan `volatile` pada static attribute.

```java
public class Storage {
  private Storage() {}

  /**
   * An instance for Storage, initially to be null before client class invoke to define this object for first time!
   * For next declarations, it will return this object defined from this class since first declaration.
   */
  private volatile static Storage instance = null;

  public synchronized static Storage getInstance() {
    if (instance == null)
       instance = new Storage();
    return instance;
  }

  private Vector<Product> storedProducts;

  // beyond methods and declaration codes inside...

}
```


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 144-152.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 181-202.
- Refactoring.guru (Singleton, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/singleton](https://refactoring.guru/design-patterns/singleton)
- SourceMaking (Singleton) - [https://sourcemaking.com/design_patterns/singleton](https://sourcemaking.com/design_patterns/singleton)
- Gang Of Four (GoF) Design Patterns: Singleton - [https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples](https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples)
