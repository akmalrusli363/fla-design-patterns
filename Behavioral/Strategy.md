# Strategy

[Main Page](..) → [Behavioral Design Patterns](.) → [Strategy](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/strategy) | [refactoring.guru](https://refactoring.guru/design-patterns/strategy) | [sourcemaking.com](https://sourcemaking.com/design_patterns/strategy)

![Strategy](../assets/img/behavioral/strategy.png#center "Strategy")

**Strategy** merupakan design pattern dimana sebuah class menggunakan sebuah Interface yang berisikan method-method yang dapat ditentukan sendiri isinya oleh class turunannya sehingga class pemakai dapat menggunakan implementasi dari Interface yang ia gunakan.

**Strategy** sangat memungkinkan Client untuk mengubah pilihan implementasi interface asalkan berada dalam satu interface yang sama. Selain itu, dengan Strategy design pattern, developer dapat menerapkan prinsip enkapsulasi dimana user tidak perlu mengetahui proses maupun code-code di dalam algoritma yang mereka kerjakan.

Design pattern ini merupakan salah satu design pattern yang diimplementasikan langsung oleh bahasa pemrograman dimana secara mendasar programmer sudah menentukan method-method yang akan diimplementasikan secara langsung oleh class pemakainya.

Meski hampir sebagian besar design pattern menggunakan `interface`, namun tidak semua design pattern tersebut didasari oleh Strategy Pattern karena adanya perbedaan model yang digunakan oleh Client kepada implementasi class-class interface.


## Analogi

Java adalah salah satu bahasa pemrograman yang menggunakan Interface untuk menyederhanakan isi class tersebut menjadi hanya berisikan method-method abstract yang harus dipergunakan oleh class pemakainya.

Pemakaian Interface tentunya sangat mempermudah Client untuk menentukan strategi/metode yang diterapkan dalam sebuah class/object, misalnya dalam memilih algoritma yang digunakan untuk menentukan rute navigasi (Kruskal, Prim, Dijkstra) atau memilih metode sort data dengan berbagai pilihan algoritma sort data (misal Bubble sort, insertion sort, selection sort, quick sort, dan merge sort).

Selain itu, dengan menggunakan strategy pattern, user tidak perlu mengetahui proses dibalik algoritma yang dipakai serta user tidak melulu bergantung pada rancangan strategi yang user inginkan dengan cukup menggunakan pilihan strategi yang ada sebagai pilihan baginya.


## Contoh Kasus

Dalam sebuah kasus Strategy Design Pattern dikisahkan bahwa seorang mahasiswa yang tinggal jauh dari kampus (misalnya tinggal di Bintaro, kampusnya UI di Depok). Dalam kasus ini, mahasiswa harus menentukan strategi dasar untuk berjalan ke kampus tanpa terlambat (apalagi jadwal kelasnya jam 7 pagi) dimana diberikan 3 pilihan strategi dasar yaitu naik angkutan umum, bawa kendaraan pribadi, atau naik ojek online.

Pilihan pertama bagi dia adalah pilihan termurah namun paling lama untuk tiba di kampus. Lain lagi dengan membawa kendaraan pribadi dimana ia harus memiliki kendaraan sendiri namun biaya transportasi hanya bergantung pada bensin dan dapat menentukan rutenya sendiri. Sebaliknya, ojek online, menjadi pilihan tercepat baginya namun biaya yang harus dikeluarkan dalam satu kali perjalanan bisa menjadi sangat mahal dibanding pilihan lainnya.

Melalui Strategy Pattern, ia tinggal menentukan kapan ia memilih transportasi umum sesuai kebutuhannya. Misalnya berangkat ke kampus secara reguler dengan kendaraan pribadi, jika sedang berada di jam lenggang naik transportasi umum, dan naik ojek online bila sedang terburu-buru mengejar kelas di pagi hari.


### Implementasi code

Pada contoh implementasi code pada kasus berikut, sebagai sarana demonstrasi, hanya menampilkan output yang berbeda-beda karena tidak memungkinkan bagi penulis untuk menuliskan algoritma di dalamnya :cry:

```java
public interface ITravelMethods {
  void performTrip(Route route);
}

public class Ojol implements ITravelMethods {
  private static final double minutePerKilometer = 1.2;

  private int estimateCost(double distance) {
    double extendedDistance = (distance < 4) ? 0 : (distance - 4);
    return (int) Math.round(10000 + (extendedDistance * 3000));
  }

  private int estimateTime(double distance) {
    return distance * minutePerKilometer;
  }

  @Override
  public void performTrip(Route route) {
    System.out.printf("Performing an ride %s using bike sharing...\n",
                        route.toString());
    System.out.printf("Time estimated: %.1f minutes\n",
                        estimateTime(route.getDistance()));
    System.out.printf("Cost: Rp%10d\n", estimateCost(route.getDistance()));
  }
}

public class SelfDriving implements ITravelMethods {
  private static final double minutePerKilometer = 1.8;

  public int estimateCost() {
    // flat fee
    return 12000;
  }

  private double estimateTime(double distance) {
    return distance * minutePerKilometer;
  }

  @Override
  public void performTrip(Route route) {
    System.out.printf("Performing an ride %s using owned vehicle...\n",
                        route.toString());
    System.out.printf("Time estimated: %.1f minutes\n",
                        estimateTime(route.getDistance()));
    System.out.printf("Cost: Rp%10d\n", estimateCost());
  }
}

public class PublicTransportation implements ITravelMethods {
  private int estimateCost(double distance) {
    return (int) (3000 + (Math.round(distance / 10) * 1000));
  }

  private double estimateTime(double distance) {
    // always minimum 3 minute because need to stop at each station
    return distance * 3;
  }

  @Override
  public void performTrip(Route route) {
    System.out.printf("Performing an ride %s using public transportation...\n",
                        route.toString());
    System.out.printf("Time estimated: %.1f minutes\n",
                        estimateTime(route.getDistance()));
    System.out.printf("Cost: Rp%10d\n", estimateCost(route.getDistance()));
  }
}
```

Ketika mahasiswa tersebut ingin memilih strategi dengan cara yang berbeda, ia cukup menggantikan strategi perjalanan dengan menambahkan implementasi class dari `ITravelMethods` ke dalam class `MyTrip` sebagai berikut:

```java
public class Route {
  private final double distanceInKilometer;
  private final String source, destination;

  public Route(String source, String destination, double distanceInKilometer) {
    this.source = source;
    this.destination = destination;
    this.distanceInKilometer = distanceInKilometer;
  }

  public double getDistance() {
    return distanceInKilometer;
  }

  @Override
  public String toString() {
    return String.format("from %s to %s", source, destination);
  }
}

public class MyTrip {
  private ITravelMethod travelMethod;
  private Route route;

  public MyTrip(Route route, ITravelMethod travelMethod) {
    this.route = route;
    this.travelMethod = travelMethod;
  }

  public void setTravelMethod(ITravelMethod travelMethod) {
    this.travelMethod = travelMethod;
  }

  public void setRoute(Route route) {
    this.route = route;
  }

  public void performTrip() {
    travelMethod.performTrip(route);
  }
}
```

Saat mahasiswa ingin membuat 2 strategi secara bersamaan, ia cukup mendeklarasikan `MyTrip` dengan salah satu travel method dan kemudian mengubah strategi tersebut di saat mahasiswa menginginkan strategi yang berbeda dengan pilihan yang ia inginkan seperti pada contoh code di bawah:

```java
Route routeToCampus = new Route("Kos bintaro", "Kampus UI depok", 15);
MyTrip myTripToCampus = new MyTrip(routeToCampus, new Ojol());
myTripToCampus.performTrip();
myTripToCampus.setTravelMethod(new PublicTransportation());
myTripToCampus.performTrip();
```

Selain itu, ia juga bisa menentukan sendiri metode yang ia gunakan sendiri ketika ia tidak menginginkan pilihan-pilihan yang tersedia dengan mendeklarasikan interface sebagai object dengan membuatkan method khusus untuk menggunakan pilihan buatannya melalui anonymous inner class:

```java
/**
 * Declare a strategy using anonymous inner objects.
 */
public ITravelMethods jalanKaki(Route route) {
  return new ITravelMethods() {
    @Override
    public void performTrip(Route route) {
      double estimatedTime = route.getDistance() * 5;
      System.out.printf("Walking %s...\n", route.toString());
      System.out.printf("Time estimated: %.1d minutes\n", estimatedTime;
      System.out.printf("No cost needed to walk!\n");
    }
  };
}
```

Maupun menggunakan lambda expression (Java 8+, hanya boleh berisikan 1 method dalam interface/abstract class saja):

```java
/**
 * Declare a strategy using lambda expression.
 */
public ITravelMethods nebeng(Route route) {
  return (route) {
    double estimatedTime = route.getDistance() * 1;
    System.out.printf("Nebeng bareng teman %s...\n", route.toString());
    System.out.printf("Time estimated: %.1d minutes\n", estimatedTime;
    System.out.printf("Uangnya dibayarin teman :)\n");
  }
}
```

## Catatan: Anonymous Inner vs Lambda Expression

Di Java tersedia deklarasi object terhadap abstract class dan interface dimana pendeklarasian object tersebut harus mengimplementasikan beberapa method-method abstract yang ada dalam class tersebut. Sebagai contoh terdapat sebuah interface yang hanya berisikan 1 method untuk memastikan agar dapat membedakan **Anonymous Inner Class** dengan **Lambda Expression**.

```java
/* Pada dasarya interface adalah abstract class yang hanya
 * mengandung abstract method (tidak ada constructor dan attribute)
 * yang akan digunakan oleh class-class pemakainya. */
public interface DataCollector() {
    public abstract String getJsonDataFromURL(String url);
}

/* Karena interface method harus mengandung 'public abstract' */
// maka method ini dapat disingkat menjadi:
public interface DataCollector() {
    String getJsonDataFromURL(String url);
}
```

Ketika sebuah object dari abstract class/interface ingin dideklarasikan oleh user, akan ada error yang muncul karena object dari class tersebut belum mengimplementasikan method-method abstract dari class yang dideklarasikan di bawah:

```java
DataCollector weatherCollector = new DataCollector(); // error!
```

Karena method-method tersebut harus diimplementasikan tersendiri oleh user, maka ada 2 pilihan yang dapat diberikan oleh user (hanya 1 jika method abstractnya lebih dari 1)[^1] untuk mengimplementasikan class tersebut, yaitu **Anonymous Inner Class** dan **Lambda Expression**.

**Anonymous Inner Class** merupakan class anonim dimana class tersebut dibuat ketika user ingin melakukan override/mengimplementasikan method-method abstract di dalamnya. Cara deklarasi anonymous inner adallah sebagai berikut:

```java
DataCollector weatherCollector = new DataCollector() {
    @Override
    public String getJsonDataFromURL(String url) {
        return UtilMethods.getJSON(url, null);
    }

    // further more abstract methods/overridable method here...
};
```

Sebaliknya, **Lambda Expression** merupakan method anonim dimana expression yang dideklarasikan dalam lambda akan dieksekusi oleh `invokedynamic`[^2] dalam compiler Java dimana nantinya dalam lambda akan dipanggil sebagai method terpisah yang dapat mengakses data luaran *(outer class objects)*.

Mendeklarasikan object dari interface/abstract class dapat dilakukan dengan Lambda Expression layaknya mendeklarasikan object pada umumnya dengan hanya mencantumkan parameter dan isi dari method dalam lambda dengan format `T object = (arguments) -> { method_body }` atau bila diimplementasikan dalam contoh code:

```java
DataCollector weatherCollector = (url) -> {
    return UtilMethods.getJSON(url, null);
};
```

Bedanya dengan *anonymous inner class* adalah pada jumlah method yang digunakan serta rujukan keyword `this` yang berbeda dimana *anonymous inner class* memperbolehkan 1 atau lebih method untuk diimplementasikan (dan dioverride) oleh user sedangkan *lambda expression* hanya memperbolehkan 1 method yand dibuat dalam interface saja yang diimplementasikan oleh user sendiri.

Selain itu, penggunaan keyword `this` dalam *anonymous inner class* merujuk pada object dalaman *(inner class objects)* yang fungsinya sama dengan *inner class* pada umumnya sedangkan `this` dalam *lambda expression* merujuk pada object luaran *(outer class objects)* yang dimaksud.

Mengenai pembahasan lebih lanjut mengenai perbedaan antara **Anonymous Inner Class** dan **Lambda Expression**, silakan dicek pada beberapa referensi tambahan di bawah.


## References

### Core - Strategy

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 349-359.
- Refactoring.guru (Strategy) - [https://refactoring.guru/design-patterns/strategy](https://refactoring.guru/design-patterns/strategy)
- SourceMaking (Strategy) - [https://sourcemaking.com/design_patterns/strategy](https://sourcemaking.com/design_patterns/strategy)
- Gang Of Four (GoF) Design Patterns: Strategy - [https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial](https://www.journaldev.com/1754/strategy-design-pattern-in-java-example-tutorial)

### Anonymous Inner vs Lambda Expression

- Java Anonymous Classes - [https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html](https://docs.oracle.com/javase/tutorial/java/javaOO/anonymousclasses.html)
- Java Lambda Expression - [https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
- GeeksForGeeks: Difference between Anonymous Inner Class and Lambda Expression - [https://www.geeksforgeeks.org/difference-between-anonymous-inner-class-and-lambda-expression/](https://www.geeksforgeeks.org/difference-between-anonymous-inner-class-and-lambda-expression/)
- Baeldung: Lambda Expressions and Functional Interfaces: Tips and Best Practices - [https://www.baeldung.com/java-8-lambda-expressions-tips](https://www.baeldung.com/java-8-lambda-expressions-tips)
- Petani Kode: Tutorial Java OOP: Mengenal Lambda Expression untuk Membuat Fungsi Anonymous di Java - [https://www.petanikode.com/java-lambda/](https://www.petanikode.com/java-lambda/)
- DZone: How Lambdas And Anonymous Inner Classes Work - [https://dzone.com/articles/how-lambdas-and-anonymous-inner-classesaic-work](https://dzone.com/articles/how-lambdas-and-anonymous-inner-classesaic-work)


## Footnotes

Mengenai definisi maupun penjelasan catatan kaki terlampir di bawah:

[^1]: Yaitu anonymous inner class, dimana cara ini memperbolehkan 1 atau lebih method dalam interface/abstract class untuk diimplementasikan, juga override method yang sudah didefinisikan sebelumnya asalkan method tersebut bukan bersifat `final`.

[^2]: `invokedynamic` merupakan hal yang baru dalam Java, dikarenakan sebelum Java 8 hanya ada 4 jenis *invocation*:

    - `invokevirtual` untuk memanggil method dari class biasa,
    - `invokestatic` untuk memanggil static methods,
    - `invokeinterface` untuk memanggil method dari interface,
    - `invokespecial` untuk memanggil constructor maupun private methods.

    `invokedynamic` diciptakan agar lambda dapat dipanggil dengan 2 cara, yaitu *Bootstrap Method* (slow) dan `CallSite & MethodHandle` (fast path, conditional).
