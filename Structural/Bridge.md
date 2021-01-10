# Bridge

[Main Page](..) → [Structural Design Patterns](.) → [bridge](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/structural/bridge) | [refactoring.guru](https://refactoring.guru/design-patterns/bridge) | [sourcemaking.com](https://sourcemaking.com/design_patterns/bridge)

![Bridge](../assets/img/structural/bridge.png#center "Bridge")

**Bridge** adalah design pattern yang memisahkan satu kesatuan konsep yang saling bereleasi satu sama lain sebagai 2 hierarki yang terpisah yaitu **abstraksi dan implementasi** dimana abstraksi _(abstraction)_ mempunyai hubungan *has-a* (memiliki attribute) dengan implementasi _(implementor/implementation)_ sehingga menghasilkan hubungan "bridge" antara abstraksi dengan implementasi.

Dalam **Bridge** design pattern, satu kesatuan konsep sebuah komponen dipisahkan hierarkinya menjadi 2 hierarki yaitu **hierarki abstraksi** dan **hierarki interface**.

Bridge bertujuan untuk mengurangi ikatan *coupling* seminimal mungkin sehingga dapat mencegah terjadinya dependensi yang berlebihan (apalagi menghasilkan hierarki yang tidak dibutuhkan)[^1] serta dapat meningkatkan ekspansibilitas pada masing-masing hierarki abstraksi dan implementasi.

![Bertempur dengan update/changes (Single whole hierarchy vs bridged hierarchy)](https://refactoring.guru/images/patterns/content/bridge/bridge-3-en.png#white-center "Bertempur dengan update/changes (Single whole hierarchy vs bridged hierarchy)")

Karena hierarki antara abstraksi *(abstraction)* dan implementasi *(implementator)* merupakan satu kesatuan konsep hierarki yang terpisah oleh "bridge", maka perubahan maupun pembuatan class tambahan yang melibatkan salah satu sisi hierarki baik abstraksi maupun implementasi dapat lebih mudah ditangani sehingga tidak mempersulit keberadaan komponen secara keseluruhan.

Selain itu, Bridge bertujuan agar masing-masing hierarki dapat diperluas sesuai kebutuhan user maupun kebutuhan tambahan dari system/platform apabila terdapat tambahan fitur yang diperlukan terhadap komponen yang akan digunakan oleh user maupun memenuhi kebutuhan platform agar komponen dapat berjalan dengan semestinya.

## Analogi

![Konsepsi sebuah objek geometri](https://refactoring.guru/images/patterns/diagrams/bridge/problem-en.png#white-center "Konsepsi sebuah objek geometri")

Objek geometri *(shape)* dan warna adalah 2 konsep yang dapat mempadukan dan memperlengkap sebuah object 2 dimensi maupun 3 dimensi satu sama lain. Barangkali sebuah komponen object dapat didefinisikan dengan mempadukan warna dan bentuk objek geometri sebagai satu kesatuan object.

Ketika bentuk/shape dan warna dipadukan sebagai 1 hierarki sekaligus, apabila terdapat perbedaan warna/bentuk sekecil apapun akan menghasilkan class baru yang tentunya sangat tidak baik dalam ekspansibilitas sebuah class. (Misalnya beda warna maka shape tersebut harus membuat class untuk shape-shape dengan warna baru; atau beda shape, maka harus buat class dengan warna-warnanya juga).

Perlu kalian ketahui, untuk menghasilkan code yang berkualitas, bersih, dan mudah dimengerti konsepnya, kedua konsep object harus dipisahkan sebagai satu kesatuan hierarki yang terpisah yang terhubung oleh "bridge" dimana sebuah *shape*/bentuk geometri berperan sebagai abstraksi dan *color*/warna sebagai implementasi.

![Memisahkan shape dan color sebagai hierarki yang terpisah](https://refactoring.guru/images/patterns/diagrams/bridge/solution-en.png#white-center "Memisahkan shape dan color sebagai hierarki yang terpisah")

**Bridge design pattern** digunakan untuk memisahkan hierarki yang merupakan satu kesatuan konsep sebuah object geometri, yaitu warna dan bentuk/shape sebagai hierarki terpisah.

Ketika *shape* dan *color* terpisahkan oleh "bridge", maka *shape* akan mengambil peran sebagai abstraksi/abstraction dimana dalam hierarki tersebut terdapat attribute yang berisikan implementasi (yaitu *color*).

Dengan demikian ketika user ingin menambahkan maupun melakukan kostumisasi terhadap model-model *shape* maupun warna, maka perubahan maupun penambahan class cukup dilakukan pada salah satu sisi hierarki saja (abstraksi atau implementasinya saja).


## Essences of Bridge

### 1. Abstraction (Konseptual, *user-side*)

**Abstraksi _(Abstraction/Interface)_** adalah pengkonsepan sebuah object yang berisikan implementasi-implementasi dasar dalam interface dan mencakup beberapa komponen (termasuk *implementator object*) yang akan digunakan oleh user. Segala kebutuhan yang dibutuhkan oleh user akan dilampirkan konsep-konsepnya dalam abstraksi dimana nantinya akan didelegasikan & diimplementasikan oleh platform/implementasi.

Contoh code:

```java
/**
 * Sebuah Abstraksi yang akan digunakan oleh User/Client
 */
public abstract class Shape {
  // berisikan implementasi/interface/platform
  private Color color;

  public Shape(Color color) {
    this.color = color;
  }

  public abstract void display();

  public Color getColor() { return color; }

  public void setColor(Color color) { this.color = color; }

  public String getDisplayColor() { return color.toString(); }
}
```

```java
public class Circle extends Shape {
  public Circle(Color color) {
    super(color);
  }

  public void display() {
    System.out.println(getDisplayColor() + " circle");
  }
}

public class Square extends Shape {
  public Square(Color color) {
    super(color);
  }

  public void display() {
    System.out.println(getDisplayColor() + " square");
  }
}
```

### 2. Implementation (Isi Implementasi, *system-side*)

Sebaliknya, **Implementasi _(Implementation/Platform)_** adalah sebuah implementasi konsep yang akan dipakai oleh object (termasuk cakupan warna, komponen, type, dan lain-lain).

Dalam implementation, segala isi dan kebutuhan yang diperlukan dari abstraksi diimplementasikan dengan tatanan, prosedur, maupun *code-code* yang diambil dari Library/Framework/Operating System API yang disesuaikan dengan kebutuhan user dalam menggunakan abstraksi.

```java
/**
 * Sebuah implementasi dari abstraksi dengan
 * cara/isi/teknik/prosedur yang beragam.
 */
public interface Color {
  String getColor();
  String toString();
}

public class Red implements Color {
  @Override
  public String getColor() {
    return "Red";
  }

  @Override
  public String toString() {
    return getColor();
  }
}

public class Green implements Color {
  @Override
  public String getColor() {
    return "Green";
  }

  @Override
  public String toString() {
    return getColor();
  }
}
```

### 3. Injecting Implementation into Abstraction

Sebelum object dapat dideklarasikan kepada user, perlu kalian ketahui bahwa object-object dalam hierarki abstraksi tidak akan berjalan efektif tanpa adanya object implementasi *(implementator)*. Hal itu disebabkan karena seluruh method dalam abstraksi harus didelegasikan kepada object implementasi.

Dalam sebuah class dalam hierarki abstraksi diperlukan adanya object implementasi dimana keberadaan implementasi harus ada melalui *dependency injection*[^2] dimana object dalam hierarki implementasi diinjeksikan pada object class abstraksi dalam constructor. Salah satu syaratnya adalah tidak boleh ada constructor kosong, alias hanya mendeklarasikan 1 constructor ber-parameter saja.

Contoh code (cukup gunakan constructor untuk melakukan *inject* implementasi ke class abstraksi):

```java
public abstract class Shape {
  // berisikan implementasi/interface/platform
  private Color color;

  public Shape(Color color) {
    this.color = color;
  }

  // declarative methods to delegate to implementation
}
```

### Hubungan antar Abstraction dan Implementation

Pengkonsepan *abstraction* (abstraksi) dapat berupa domain, front-end, maupun interface sebagai landasan dasar konsep yang akan dipakai oleh user (alias *user-side*) dimana nantinya seluruh isi implementasi didelegasikan kepada *implementation* (implementasi).

Sebaliknya, *implementation* (implementasi) merujuk pada infrastructure, back-end, maupun platform dimana secara dasar merupakan implementasi dari konsep yang akan dipakai oleh user (alias *system-side*).

Dalam bahasa mudahnya, sebuah GUI/user interface adalah sebuah *abstraction* yang akan digunakan oleh user sedangkan framework/API adalah *implemention* yang akan menjalankan segala kebutuhan yang diperlukan oleh *abstraction* dari user dengan isi/teknik/prosedur/cara yang beragam.

Atau bila dalam implementasi code, implementator perlu dicantumkan terlebih dahulu saat mendeklarasikan object agar sebuah komponen object dapat berfungsi dengan semestinya.


## UML Model

![Bridge Model](../assets/img/structural/bridge-model.png#center "Bridge Model")


## Contoh Code

Salah satu contoh pemakaian bridge dalam kehidupan sehari-hari adalah keberadaan remote dan perangkat elektronik (seperti TV/Robot/Smart Box/Radio/AC/dll) dimana sebuah remote dapat dianalogikan sebagai abstraksi *(user-side)* yang digunakan oleh user/pemakai sedangkan perangkat-perangkat elektronik yang dianalogikan sebagai implementasi *(system-side)*.

Ketika sebuah remote selalu didefinisikan sebagai cara untuk mengendalikan segala jenis perangkat elektronik, maka diperlukan implementasi-implementasi terhadap perangkat elektronik yang digunakan (misalnya TV, Radio, Smart Box, dan lain-lain).

Karena implementasi setiap jenis perangkat elektronik berbeda-beda caranya, apalagi cara yang digunakan oleh remote kurang lebih hampir sama cara kerjanya setiap model remotenya, maka konsep terhadap sebuah remote ada baiknya harus dipisahkan sebagai konsep abstrak yang terpisah dari implementasi.

Pada awal mulanya misal ketika hierarki remote didefinisikan sebagai berikut:

```java
public interface Remote {
  void turnOn();
  void turnOff();
  void increaseVolume();
  void decreaseVolume();
  void toggleMute();
  void next();
  void previous();
}

public class TVRemote implements Remote {
  private static final int minVolume = 0, maxVolume = 100;
  private static final int minChannel = 0, maxChannel = 20;
  private boolean turnedOn = false, muted = false;
  private int volume = 10, channel = minChannel;

  @Override
  public void turnOn() {
    turnedOn = true;
  }

  @Override
  public void turnOff() {
    turnedOn = false;
  }

  @Override
  public void increaseVolume() {
    if (volume < maxVolume) { volume++; }
  }

  @Override
  public void decreaseVolume() {
    if (volume > minVolume) { volume--; }
  }

  @Override
  public void toggleMute() {
    muted = !muted;
  }

  @Override
  public void next() {
    if (channel > maxChannel) {
      channel = minChannel;
    } channel++;
  }

  @Override
  public void previous() {
    if (channel < 0) {
      channel = maxChannel;
    } channel--;
  }
}

public class RadioRemote implements Remote {
  private static final int minVolume = 0, maxVolume = 100;
  private static final float minFreq = 87.5, maxFreq = 108.0;
  private boolean turnedOn = false, muted = false;
  private int volume = 10;
  private float frequency = minFreq;

  @Override
  public void turnOn() {
    turnedOn = true;
  }

  @Override
  public void turnOff() {
    turnedOn = false;
  }

  @Override
  public void increaseVolume() {
    if (volume < maxVolume) { volume++; }
  }

  @Override
  public void decreaseVolume() {
    if (volume > minVolume) { volume--; }
  }

  @Override
  public void toggleMute() {
    muted = !muted;
  }

  @Override
  public void next() {
    if (frequency < maxFreq) {
      frequency += .1f;
    }
  }

  @Override
  public void previous() {
    if (frequency > minFreq) {
      frequency -= .1f;
    }
  }
}
```

Karena terdapat perbedaan yang mendasar mengenai setup perangkat elektronik, kita dapat memisahkan keberadaan `Remote` dengan memisahkan class tersebut sebagai hierarki tersendiri yang berperan sebagai *abstraction* sehingga setiap implementasi perangkat akan dianggap sebagai hierarki yang terpisah yang berperan sebagai *implementator*.

Remote tersebut kemudian diubah kembali keberadaan fungsinya sebagai konsepsi yang terpisah yang difokuskan kepada user. Sebaliknya, perangkat-perangkat seperti TV dan Radio difungsikan sebagai implementasi yang lebih difokuskan pada sistem perangkat.

Maka setelah Remote dipisahkan sebagai sebuah hierarki abstraksi adalah sebagai berikut:

```java
public class Remote {
  protected Device device;

  public Remote(Device device) {
    this.device = device;
  }

  public void togglePower() {
    if (device.isTurnedOn()) {
      device.turnOn();
    } else {
      device.turnOff();
    }
  }

  public void increaseVolume() {
    device.setVolume(device.getVolume() + 1);
  }

  public void decreaseVolume() {
    device.setVolume(device.getVolume() - 1);
  }
  
  public void toggleMute() {
    device.toggleMute();
  }
  
  public void next() {
    device.next();
  }
  
  public void previous() {
    device.previous();
  }
}
```

Dan perangkat-perangkat elektronik didefinisikan dalam hierarki implementasi sebagai berikut:

```java
public interface Device {
  void turnOn();
  void turnOff();
  boolean isTurnedOn();
  int getVolume();
  void setVolume(int volume);
  void toggleMute();
  void next();
  void previous();
  String toString();
}

public class TV implements Device {
  private static final int minVolume = 0, maxVolume = 100;
  private static final int minChannel = 0, maxChannel = 20;
  private boolean turnedOn = false, muted = false;
  private int volume = 10, channel = minChannel;

  @Override
  public void turnOn() {
    turnedOn = true;
  }

  @Override
  public void turnOff() {
    turnedOn = false;
  }

  @Override
  public boolean isTurnedOn() {
    return turnedOn;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public void setVolume(int volume) {
    if (volume >= minVolume && volume <= maxVolume) {
      this.volume = volume;
    }
  }

  @Override
  public void toggleMute() {
    muted = !muted;
  }

  @Override
  public void next() {
    if (channel > maxChannel) {
      channel = minChannel;
    } channel++;
  }

  @Override
  public void previous() {
    if (channel < minChannel) {
      channel = maxChannel;
    } channel--;
  }

  @Override
  public String toString() {
    if (!turnedOn) {
      return "TV is off";
    }
    return String.format("TV: [Channel: %d | Volume: %d/%d]",
      channel, volume, maxVolume);
  }
}

public class Radio implements Device {
  private static final int minVolume = 0, maxVolume = 100;
  private static final float minFreq = 87.5f, maxFreq = 108.0f;
  private boolean turnedOn = false, muted = false;
  private int volume = 10;
  private float frequency = minFreq;

  @Override
  public void turnOn() {
    turnedOn = true;
  }

  @Override
  public void turnOff() {
    turnedOn = false;
  }

  @Override
  public boolean isTurnedOn() {
    return turnedOn;
  }

  @Override
  public int getVolume() {
    return volume;
  }

  @Override
  public void setVolume(int volume) {
    if (volume >= minVolume && volume <= maxVolume) {
      this.volume = volume;
    }
  }

  @Override
  public void toggleMute() {
    muted = !muted;
  }

  @Override
  public void next() {
    if (frequency < maxFreq) {
      frequency += .1f;
    }
  }

  @Override
  public void previous() {
    if (frequency > minFreq) {
      frequency -= .1f;
    }
  }

  @Override
  public String toString() {
    return String.format("Radio: [Frequency: %.1f FM | Volume: %d/%d]",
      frequency, volume, maxVolume);
  }
}
```

Karena hierarki antara remote dan device terpisah, maka setiap perubahan yang dilakukan pada remote maupun device dapat ditangani secara independen. Selain itu, konsep abstraksi maupun implementasi dapat ditambahkan sesuai kebutuhan user bila diperlukan dengan tetap meyakini bahwa konsep abstraksi hanya menambah isi-isi deklarasi (konsep) yang akan dipakai oleh user dan kemudian didelegasikan kepada hierarki implementasi.

Ketika konsepsi sebuah remote ingin diperluas dengan menambahkan opsi untuk mengatur volume sefleksibel mungkin maupun ingin menampilkan informasi channel dapat ditambahkan konsepsi pada class `Remote` dalam hierarki abstraksi dengan tetap mendelegasikan seluruh implementasi ke hierarki implementator.

Contoh code class tambahan:

```java
public class SuperRemote extends Remote {
  public SuperRemote(Device device) {
    super(device);
  }

  // an extra method for extra functionality
  public void setVolume(int volume) {
    device.setVolume(volume);
  }

  public void isTurnedOn() {
    System.out.println(device.isTurnedOn());
  }

  // keep it abstract, make sure that commands need to be delegated to implementator
  public void getInformation() {
    System.out.println("Device information:");
    System.out.println("----------------------");
    System.out.println(device.toString());
  }
}
```


## Catatan Tambahan: Dependency Injection

**Dependency Injection**[^2] dapat digunakan dalam bridge design pattern dimana ketika sebuah abstraksi memerlukan implementasi yang terpisah (diimplementasikan menggunakan library eksternal maupun komponen yang dibuat terpisah dari komponen yang ada) sebagai attributenya. Dengan menggunakan Dependency Injection, maka komponen berupa implementasi harus diinjeksikan ke dalam komponen abstraksi sebelum dapat dipergunakan oleh Client/user.

Terdapat 3 cara untuk melakukan injeksi sebuah dependency (komponen implementasi) kepada komponen abstraksi:

1. **Constructor Injection**: Module eksternal (komponen implementasi) diinjeksikan sebagai implementasi saat komponen abstraksi diinisiasikan dengan menggunakan constructor.

    ```java
    public class TVRemote {
      private Television television;

      public TVRemote(Television television) {
        this.television = television;
      }

      // abstractions need to delegate to implementation
    }
    ```

    ```java
    Television samsungTV = new SamsungTV();
    TVRemote samsungRemote = new TVRemote(samsungTV);
    ```

2. **Setter Injection**: Module eksternal (komponen implementasi) diinjeksikan menggunakan object setter dalam komponen abstraksi.

    ```java
    public class TVRemote {
      private Television television;

      public setTelevision(Television television) {
        this.television = television;
      }

      // abstractions need to delegate to implementation
    }
    ```

    ```java
    Television samsungTV = new SamsungTV();
    TVRemote myRemote = new TVRemote();
    myRemote.setTelevision(samsungTV);
    ```

3. **Interface Injection**: Interface menyediakan *setter* untuk menginjeksikan module eksternal (komponen implementasi) dimana interface tersebut diimplementasikan oleh class-class pemakai (komponen abstraksi) untuk digunakan oleh client/user.

    ```java
    public interface DeviceRemote {
      void setDevice(Device device);
      // abstract methods needed to implement by abstraction
      // to be delegated to implementation
    }

    public class TVRemote implements DeviceRemote {
      private Device device;

      @Override
      public setDevice(Device device) {
        this.device = device;
      }

      // abstractions need to delegate to implementation
    }
    ```

    ```java
    Device samsungTV = new SamsungTV();
    TVRemote myRemote = new TVRemote();
    myRemote.setTelevision(samsungTV);
    ```



## References

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126.
- Refactoring.guru (Bridge, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/bridge](https://refactoring.guru/design-patterns/bridge)
- SourceMaking (Bridge, termasuk referensi gambar) - [https://sourcemaking.com/design_patterns/bridge](https://sourcemaking.com/design_patterns/bridge)
- Gang Of Four (GoF) Design Patterns: Bridge - [https://www.journaldev.com/1491/bridge-design-pattern-java](https://www.journaldev.com/1491/bridge-design-pattern-java)
- Wikipedia English: Bridge Pattern - [https://en.wikipedia.org/wiki/Bridge_pattern](https://en.wikipedia.org/wiki/Bridge_pattern)
- Wikipedia English: Dependency Injection - [https://en.wikipedia.org/wiki/Dependency_injection](https://en.wikipedia.org/wiki/Dependency_injection)
- Agung Setiawan. Java: Memahami Dependency Injection - [https://agung-setiawan.com/java-memahami-dependency-injection/](https://agung-setiawan.com/java-memahami-dependency-injection/)


## Catatan Kaki

[^1]: Umumnya ketika kedua konsep yang terpadu dan menghasilkan hierarki yang tidak diperlukan, kejadian tersebut dapat merujuk pada smell [Unnecessary Abstraction](https://akmalrusli363.github.io/smell/Girish/Abstraction#unnecessary-abstraction) dimana pada kasus tersebut dapat diselesaikan dengan melakukan *collapse hierarchy* maupun dengan memisahkan satu kesatuan hierarki (yang disebabkan oleh masalah spele) yang menghasilkan turunan-turunan class sebagai beberapa hierarki yang terpisah.

[^2]: **Dependency injection** adalah teknik dimana sebuah komponen yang diperlukan oleh class diinjeksikan kepada *client class*/class pemakainya baik melalui constructor, setter, maupun interface injection. Dependency injection sering digunakan pada framework (Spring), web programming (ASP C#), maupun mobile programming (Android Java/Kotlin, Apple Swift) dimana modul-modul yang diinjeksikan dapat berupa dependensi dari library eksternal yang tidak mungkin difungsikan langsung sebagai attribute dalam sebuah komponen.