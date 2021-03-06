# Singleton

[Main Page](..) → [Creational Patterns](.) → [Singleton](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/creational/singleton) | [refactoring.guru](https://refactoring.guru/design-patterns/singleton) | [sourcemaking.com](https://sourcemaking.com/design_patterns/singleton)

![Singleton](https://refactoring.guru/images/patterns/content/singleton/singleton.png#center "Singleton")

> The most basic & simplest type of creational design pattern. Just define a private constructor & call for a instance to define a object.

Singleton merupakan design pattern yang paling sederhana yang bertujuan untuk membatasi deklarasi menjadi hanya 1 instance/object dalam sebuah class. Dalam Singleton, hanya ada 1 object dari class tersebut yang dapat dibuat untuk class client, yaitu _instance_ itu sendiri. Setiap pemanggilan object berikutnya yang dilakukan oleh client, object akan dipanggilkan langsung dari instance yang sudah dibuatkan oleh class tersebut.

Singleton menggunakan *lazy-loading* untuk menginisiasikan sebuah instance/object dalam sebuah class yang hanya dipanggil 1x sejak class tersebut dipanggil oleh class client untuk mencegah borosnya resource yang terjadi karena pemanggilan object yang berulang-ulang.

Sederhananya, client tidak bisa membuat object dari suatu class lebih dari 1 kali & tidak mempunyai constructor (karena access modifier yang `private`), sebaliknya object tersebut kemudian memanggil instance/object pertama sudah dibuatkan oleh class tersebut.

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

  public static synchronized MySingleton getInstance() {
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

  public static synchronized Storage getInstance() {
    if (instance == null)
       instance = new Storage();
    return instance;
  }

  private Vector<Product> storedProducts;

  // beyond methods and declaration codes inside...

}
```


## Jenis-jenis Singleton

### 1. Eagerly-initialized Singleton

```java
public class MySingleton {
  private static MySingleton instance = new MySingleton();

  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  public static MySingleton getInstance() {
    return instance;
  }

  // TODO declaration and methods here...
}
```

Jenis Singleton ini merupakan jenis singleton termudah, hanya dengan mendeklarasikan object langsung pada `static` attribute tanpa harus melalui *null checking* dimana method `getInstance()` hanya memanggil object instance pada class Singleton.

Jenis ini kurang efektif apabila class yang dibuatkan Singleton hanya dipanggil apabila dibutuhkan oleh class client dan mengakibatkan pemborosan resources dibanding dengan *lazy initialization*.

Namun dari segi pembuatan object, object tersebut sudah dibuatkan langsung oleh JVM sesaat class tersebut dipanggil & sebelum thread mengakses object/instance tersebut sehingga dijamin *thread-safe* dibanding melalui *lazy initialization*.


### 2. Lazy-initialized Singleton

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

Jenis Singleton ini mencakup deklarasi object dengan *lazy initialization* dimana object akan dibuatkan apabila belum ada. Sebaliknya, class akan memanggil instance yang sudah dibuatkan sebelumnya sebagai objectnya.

Jenis ini tidak akan berjalan efektif apabila class memerlukan aktivitas yang melibatkan beberapa thread atau ingin menjamin bahwa Singleton tersebut dapat berjalan efektif dalam kasus *multithreading*.


### 3. Thread-Safe Singleton

```java
public class MySingleton {
  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  private volatile static MySingleton instance = null;

  public static synchronized MySingleton getInstance() {
    if (instance == null) {
      instance = new MySingleton();
    }
    return instance;
  }

  // TODO declaration and methods here...
}
```

Jenis Singleton ini menggunakan keyword `volatile` kepada object Singleton, `instance` untuk menampung variabel tersebut langsung ke *main memory* dan `synchronized` kepada method `getInstance()` untuk menjamin bahwa proses pengambilan instance tersebut *thread-safe*.

Jenis ini menjamin bahwa pembuatan object Singleton hanya sekali untuk semua thread yang menggunakan object tersebut & efektif dalam kasus *multithreading*.


### 4. Double-check Locking Singleton

```java
public class MySingleton {
  private MySingleton() {
    // TODO contents of constructor (if needed)
  }

  private volatile static MySingleton instance = null;

  public static MySingleton getInstance() {
    if (instance == null) {
      synchronized(MySingleton.class) {
        if (instance == null) {
          instance = new MySingleton();
        }
      }
    }
    return instance;
  }

  // TODO declaration and methods here...
}
```

Merupakan implementasi lanjutan dari *thread-safe* Singleton, namun peletakan `synchronized` ditempatkan dalam method body `getInstance()` dalam *null check* untuk meminimalisir panggilan sinkronisasi thread berulang ketika memanggil instance object, sehingga sinkronisasi hanya dipakai apabila belum ada object instance pada class tersebut.

Selain itu, metode ini mengecek keberadaan object/instance Singleton sebanyak 2 kali, yaitu ketika method `getInstance()` dipanggil & dalam method body `synchronized()` pada static method `getInstance()`.

Singleton ini menggunakan `synchronized()` dalam block pada static method `getInstance()` dimana apabila tidak ada object instance pada class tersebut, maka hanya 1 thread dalam class tersebut yang dapat mengakses isi method body `synchronized()` untuk dicek keberadaan object tersebut & dideklarasikan object/instance Singleton apabila benar-benar kosong.


## Catatan Tambahan

- Keyword `volatile` digunakan dalam object Java untuk ditampung ke memory utama dalam komputer (bukan CPU Cache) dengan menjamin bahwa setiap thread dapat mengakses object tersebut secara langsung (baik read dan write) & data yang diperoleh sesuai dengan kebutuhan thread dalam kasus *multithreading* dan *concurrency*.
- Penggunaan keyword `synchronized` dapat dilakukan dalam method definition (secara keseluruhan) maupun dalam method body *(block of code)* dimana hanya 1 thread yang diperbolehkan untuk mengakses method/*block of code* tersebut.


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 144-152.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 169-190.
- Refactoring.guru (Singleton, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/singleton](https://refactoring.guru/design-patterns/singleton)
- SourceMaking (Singleton) - [https://sourcemaking.com/design_patterns/singleton](https://sourcemaking.com/design_patterns/singleton)
- Gang Of Four (GoF) Design Patterns: Singleton - [https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples](https://www.journaldev.com/1377/java-singleton-design-pattern-best-practices-examples)
- Jakob Jenkov (2020-09-11): Java Volatile Keyword - [http://tutorials.jenkov.com/java-concurrency/volatile.html](http://tutorials.jenkov.com/java-concurrency/volatile.html)
- Jakob Jenkov (2020-08-12): Java Synchronized Blocks - [http://tutorials.jenkov.com/java-concurrency/synchronized.html](http://tutorials.jenkov.com/java-concurrency/synchronized.html)