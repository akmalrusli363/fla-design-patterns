# Builder

[Main Page](..) → [Creational Patterns](.) → [Builder](#)

![Builder](https://refactoring.guru/images/patterns/content/builder/builder-en.png#center "Builder")

> Pernah mendengar istilah _"Mending rakit PC"_ yang viral belakangan ini? Istilah ini membuktikan bahwa PC adalah perangkat yang dirakit dari beberapa komponen terpisah. Berbeda dengan Laptop, Smartphone, maupun Tablet yang dirakit langsung oleh perusahaan perakit, PC memberikan banyak pilihan dan fleksibilitas yang diberikan kepada pengguna untuk memilih komponen sesuai kebutuhan dan budget yang dimiliki mereka masing-masing.

Builder merupakan design pattern yang dapat mempermudah pembuatan berbagai macam variasi class meski dengan model yang berbeda.

![Class House (Rumah) dengan beberapa variasi yang dibuat](https://refactoring.guru/images/patterns/diagrams/builder/problem1.png#center "Class House (Rumah) dengan beberapa variasi yang dibuat")

![Classic class constructor, too long parameters, unused at some cases](https://refactoring.guru/images/patterns/diagrams/builder/problem2.png#center "Classic class constructor, too long parameters, unused at some cases")

Berbeda dengan Constructor yang mengharuskan Client untuk memasukkan beberapa parameter untuk mendeklarasikan object (dimana dalam beberapa kasus tertentu, beberapa parameter tidak pernah digunakan alias _useless_), Builder class dapat memberikan fleksibilitas Client dalam menentukan/mendeklarasikan object yang mereka inginkan tanpa harus bergantung pada setiap attribute yang mereka inginkan.

![Builder class structures](https://refactoring.guru/images/patterns/diagrams/builder/solution1.png#center "Builder class structures")

Builder menggunakan beberapa method/object yang dideklarasikan oleh client maupun *director* untuk kemudian dideklarasikan/dibuatkan/dikembalikan sebagai sebuah object. Penggunaan Builder juga harus mencakup deklarasi attribute tersendiri (sebagai setter) dalam builder class itu sendiri sebelum dapat dikembalikan sebagai sebuah object.


## Essences of Builder

### 1. Sets of class objects as an object

Agar Builder dapat mendeklarasikan sebuah class object, sebuah class yang dijadikan model harus mempunyai beberapa attribute (termasuk _Setter_ dan _Getter_) untuk mempermudah deklarasi object dan meningkatkan fleksibilitas yang diberikan kepada pengguna/Client.

Salah satu contoh class yang dapat dipergunakan dalam builder adalah class Computer, dimana class tersebut mencakup 5 attribute yang dapat ditentukan oleh user yaitu Processor, Storage, RAM, GPU, dan Cooling.

### 2. Attribute Setter

Dibanding menggunakan Constructor, Builder memungkinkan Client untuk menentukan attribute-attribute yang mereka inginkan untuk dideklarasikan sebagai object dengan bantuan Setter dan lebih fleksibel karena tidak harus mencantumkan semua attribute yang perlu dibuat dibanding Constructor.

### 3. Returner

Setelah object tersebut dibuat dengan bantuan Setter, Client dapat mengembalikan object yang mereka buat dengan object method.

```java
private ComputerSet computerSet;

// sets of setter for complex object creation

public ComputerSet getResult() {
 return computerSet;
}
```

### 4. Object Reset _(optional, to redeclare complex object as new object)_

Ketika Client ingin menggunakan kembali Builder object, class dapat direset dengan bantuan method `reset()` untuk mendeklarasikan object dalam builder sebagai object kosong agar dapat digunakan sesuai kebutuhan Client untuk object berikutnya.


## Director

![Director, juru mandor Builder yang dapat dipergunakan oleh Client](https://refactoring.guru/images/patterns/content/builder/builder-comic-2-en.png "Director, juru mandor Builder yang dapat dipergunakan oleh Client")

> Ketika Client tidak mempunyai banyak waktu untuk berpikir untuk membangun rumah, merakit komputer, maupun merancang hunian impian, **Director** alias juru mandor adalah solusi bagi anda yang malas mengurus berbagai urusan yang menyulitkan hidup anda.

Dalam beberapa kasus tertentu, pembuatan object tidak melalu dilakukan oleh Client. Sebaliknya, Client dapat menggunakan beberapa pilihan object yang sudah ada untuk dipergunakan langsung oleh Client. Penggunaan **Director** dapat dilakukan untuk mendelegasikan deklarasi class sesuai keinginan Client/user melalui beberapa Object method yang ditujukan kepada Builder untuk memenuhi kebutuhan Client.

Contoh:

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

	// add more robot models to produce to ensure direct usability by Client

}
```

## Contoh kasus A: Robot

Salah satu contoh Builder adalah penerapan pembuatan robot dimana dalam class Robot terdapat banyak variasi yang dapat dipilih oleh Client (misal dengan barang yang mereka miliki seperti Sword, Gun, Brainchip, dan Shield) dimana Client/pengguna tidak harus menggunakan semua properti yang mereka miliki untuk menciptakan object.

**Model class (Robot.java):**

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
```

**Builder class:**

```java
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


## Contoh kasus B: Computer Set

Dalam kasus pembuatan set/object dari sekumpulan model object *(an object associated from different object models)* misalnya Set Furniture, Computer Set, Combat Kit, maupun barang-barang satuan seperti mobil dan komputer, Builder sangat diperlukan untuk menyatukan beberapa komponen dasar menjadi 1 object terpadu.

Dalam contoh kasus Computer Set, diketahui bahwa sebuah Set Komputer terdiri dari Monitor, Keyboard, Mouse, Speaker, dan Komputer yang terdiri dari beberapa komponen internal yang dapat dibuat dengan bantuan Computer Builder di dalamnya.

Contoh code:

```java
public class ComputerSet {
  private Computer computer;
  private String keyboard, mouse, speaker, monitor;

  public Computer getComputer() {
    return computer;
  }

  public void setComputer(Computer computer) {
    this.computer = computer;
  }

  public String getKeyboard() {
    return keyboard;
  }

  public void setKeyboard(String keyboard) {
    this.keyboard = keyboard;
  }

  public String getMouse() {
    return mouse;
  }

  public void setMouse(String mouse) {
    this.mouse = mouse;
  }

  public String getSpeaker() {
    return speaker;
  }

  public void setSpeaker(String speaker) {
    this.speaker = speaker;
  }

  public String getMonitor() {
    return monitor;
  }

  public void setMonitor(String monitor) {
    this.monitor = monitor;
  }

}

public class ComputerSetBuilder {
  private ComputerSet computerSet;

  public ComputerSetBuilder() {
    this(new ComputerSet());
  }

  public ComputerSetBuilder(ComputerSet computerSet) {
    this.computerSet = computerSet;
  }

  public void computer(Computer computer) {
    computerSet.setComputer(computer);
  }

  public void keyboard(String keyboard) {
    computerSet.setKeyboard(keyboard);
  }

  public void mouse(String mouse) {
    computerSet.setMouse(mouse);
  }

  public void speaker(String speaker) {
    computerSet.setSpeaker(speaker);
  }

  public void monitor(String monitor) {
    computerSet.setMonitor(monitor);
  }


  public ComputerSet getResult() {
    return computerSet;
  }

  public void resetBuilder() {
    computerSet = new ComputerSet();
  }

}
```


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Refactoring.guru (Builder, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/builder](https://refactoring.guru/design-patterns/builder)
- Gang Of Four (GoF) Design Patterns: Builder - [https://www.journaldev.com/1425/builder-design-pattern-in-java](https://www.journaldev.com/1425/builder-design-pattern-in-java)
