# Singleton

[Main Page](..) → [Creational Patterns](.) → [Singleton](#)

![Singleton](https://refactoring.guru/images/patterns/content/singleton/singleton.png#center "Singleton")

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


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Refactoring.guru (Singleton, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/singleton](https://refactoring.guru/design-patterns/singleton)
- Gang Of Four (GoF) Design Patterns: Singleton - [https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples](https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples)
