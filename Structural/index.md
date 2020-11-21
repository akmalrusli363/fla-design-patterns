# Structural Design Patterns

[Main Page](..) → [Structural Design Patterns](#)

<p align="center" style="text-align:center"><img src="/assets/img/pattern/structural.png" alt="Structural Design Patterns" height="200" class="thumbnail" /></p>

<p align="center" style="text-align:center"><img src="/assets/img/structural/structural-model.png" alt="Structural Design Pattern Model" class="center" /></p>

Merupakan design pattern yang berperan dalam strukturisasi object untuk memudahkan skalabilitas _(scalability)_ dengan menjamin fleksibilitas dan efisiensi dari class itu sendiri.

Ada 7 jenis structual design patterns:

1. [Adapter](#adapter)
2. [Facade](#facade)
3. Decorator
4. Proxy
5. Bridge
6. Composite
7. Flyweight

## [Adapter](Adapter)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/structural/adapter) | [refactoring.guru](https://refactoring.guru/design-patterns/adapter) | [sourcemaking.com](https://sourcemaking.com/design_patterns/adapter)

Adapter merupakan design pattern yang menghubungkan antara sebuah class yang dibuat oleh Client dengan class/package/library lain (3rd party) dengan bantuan middle class. Dengan memanfaatkan class penengah, data-data maupun attribute yang digunakan oleh client dapat compatible dengan attribute pada class luaran (3rd party).

Salah satu contoh penerapan adapter adalah adapter antara JSON dan XML dimana kedua jenis object data ini tidak kompatibel secara langsung namun dapat dikomversikan dengan bantuan middle class. Selain itu, ada banyak contoh-contoh lain dimana Client dapat menggunakan design pattern ini untuk mengadaptasi class library terhadap class Client.

### Contoh: Port Adapter

[Source code](smartphone)

![Plug Adapter](/assets/img/structural/adapter-plug.png#center "Adapter")

Dalam kasus Port adapter, terdapat 2 perangkat smartphone yang RiJukie miliki yaitu iPhone Xy dan Samsung S19. Smartphone iPhone Xy sudah ada sejak 2019 dan Smartphone Samsung S19 baru dibeli beberapa minggu yang lalu. Pada suatu hari, kabel data/charger Samsung S19 dipinjam tetangga sehingga ia hanya mempunyai kabel Lightning yang hanya dapat digunakan di perangkat iPhone saja sehingga harus membeli kabel data untuk HP Samsung yaitu USB-C.

Karena untuk membeli kabel USB-C boleh dikatakan cukup mahal (yaitu sekitar 200-300 ribu), maka cukup sulit bagi kita untuk menabung apalagi mempreteli kabel Lightning hanya demi dapat dipergunakan oleh HP berbasis USB-C seperti Samsung. Sebagai gantinya, kenapa tidak coba beli adapter Lightning to USB-C?

Asumsikan pada kasus mula-mula, didefinisikan 4 class yaitu `Lightning`, `UsbTypeC`, `Samsung`, dan `SamsungCharger`. Keempat class tersebut didefinisikan sebagai berikut:

```java
package ports;
public interface Lightning {
  public void chargeWithLightning();
}

package ports.usbc;
public interface UsbTypeC {
  void chargeWithUsbTypeC();
}

package model.samsung;
public class SamsungCharger {
  private static SamsungCharger instance;

  private SamsungCharger() {
  }

  /**
   * Uses Singleton with thread-safe capability with lazy-loading
   */
  public synchronized static SamsungCharger getInstance() {
    if (instance == null) {
      instance = new SamsungCharger();
    }

    return instance;
  }

  /**
   * Charge using USB-C
   */
  public void charge(UsbTypeC port) {
    port.chargeWithUsbTypeC();
  }
}

public class Samsung {
  private String storage, ram, batteryDuration;
  private final SamsungCharger charger;

  public Samsung() {
    charger = SamsungCharger.getInstance();
  }

  public Samsung(String storage, String ram, String batteryDuration) {
    this();
    this.storage = storage;
    this.ram = ram;
    this.batteryDuration = batteryDuration;
  }

  public String getStorage() {
    return storage;
  }

  public void setStorage(String storage) {
    this.storage = storage;
  }

  public String getRam() {
    return ram;
  }

  public String getBatteryDuration() {
    return batteryDuration;
  }

  public void setBatteryDuration(String batteryDuration) {
    this.batteryDuration = batteryDuration;
  }

  public SamsungCharger getCharger() {
    return charger;
  }

  public void charge(UsbTypeC port) {
    charger.charge(port);
  }

  @Override
  public String toString() {
    return "Samsung [storage=" + storage + ", ram=" + ram + ", batteryDuration=" + batteryDuration + "]";
  }
}
```

Dapat dibayangkan sulitnya dan mustahilnya menggunakan port Lightning dengan HP Samsung S19 dengan port USB-C karena ketidakcocokan port model. Namun dengan menggunakan Adapter design pattern, kita dapat menggunakan `LightningUsbCAdapter` dengan **mengimplementasikan interface `UsbTypeC` dan menggunakan object berbasis interface `Lightning`**.

Dengan asumsi terdapat class adapter port colokan bernama `IPhonePort` dengan implementasi interface `Lightning` dan `SamsungPort` dengan implementasi interface `UsbTypeC`, kita dapat mendeklarasikan class khusus dengan implementasi Adapter design pattern bernama `LightningUsbCAdapter` untuk menghubungkan kedua interface yang berbeda jauh model implementasinya.

Pertama-tama, deklarasikan model colokan yang mengimplementasikan kedua interface colokan tersebut:

```java
package ports;
public class IPhonePort implements Lightning {
  @Override
  public void chargeWithLightning() {
    System.out.println("The phone is charged with Lightning port");
  }
}

package ports.usbc;
public class SamsungPort implements UsbTypeC {
  @Override
  public void chargeWithUsbTypeC() {
    System.out.println("The phone is charged with USB Type C");
  }
}
```

Ketika Client menginginkan adanya koneksi antara Lightning dan USB-C, maka buatlah class `LightningUsbCAdapter` dengan implementasi `UsbTypeC` namun dengan penggunaan object dari `Lightning` seperti pada di bawah:

```java
package ports.usbc;
public class LightningUsbCAdapter implements UsbTypeC {
  private IPhonePort iPort;

  public LightningUsbCAdapter(IPhonePort iPort) {
    this.iPort = iPort;
  }

  @Override
  public void chargeWithUsbTypeC() {
    iPort.chargeWithLightning();
  }
}
```

Kemudian pada sisi pemakainya, misal Client class, cukup lakukan injeksi adapter yang ingin digunakan oleh Client untuk melakukan charging pada perangkat Samsung S19 berbasis USB-C (Misal dengan port bawaan S19 maupun Lightning port dari iPhone Xy dengan bantuan adapter `LightningUsbCAdapter`).

```java
// Asumsikan bahwa Client menggunakan class ini untuk charge Samsung S19
public class Main {
  public Main() {
    Samsung samsungS19 = new Samsung("128GB", "6GB", "4000mAH");
    System.out.println(samsungS19.toString());
    chargeWithUsbTypeC(samsungS19);
    chargeWithLightning(samsungS19);
  }

  private void chargeWithUsbTypeC(Samsung smartphone) {
    SamsungPort samsungPort = new SamsungPort();
    smartphone.charge(samsungPort);
  }

  private void chargeWithLightning(Samsung smartphone) {
    IPhonePort iPhonePort = new IPhonePort();
    LightningUsbCAdapter lightningAdapter = new LightningUsbCAdapter(iPhonePort);
    smartphone.charge(lightningAdapter);
  }

  public static void main(String[] args) {
    new Main();
  }
}
```

Kita juga bisa melakukan hal yang sama pada Adapter USB-C to Lightning port yang dapat dipergunakan pada iPhone apabila kabel Lightningnya dipinjam tetangga, apalagi jika kita pengen beli iPhone XZ yang gak ada kepala colokannya dan kabelnya cuma ada USB-C ke USB-C dari Samsung S19 saja.

```java
package ports.usbc;
public class UsbCLightningAdapter implements Lightning {
  private SamsungPort cPort;

  public UsbCLightningAdapter(SamsungPort cPort) {
    this.cPort = cPort;
  }

  @Override
  public void chargeWithLightning() {
    cPort.chargeWithUsbTypeC();
  }
}
```

Lalu gunakan pada perangkat iPhone kesayangan RiJukie deh :wink:

```java
// Asumsikan bahwa Client menggunakan class ini untuk charge IPhone XZ
public class Main {
  public Main() {
    // sengaja error karena saya tidak masukkan code IPhone ke sini :sad:
    IPhone iPhoneXZ = new IPhone("iPhone XZ Pro Max");
    System.out.println(iPhoneXZ.toString());
    chargeWithUsbTypeC(iPhoneXZ);
    chargeWithLightning(iPhoneXZ);
  }

  private void chargeWithUsbTypeC(IPhone iPhone) {
    SamsungPort samsungPort = new SamsungPort();
    UsbCLightningAdapter usbCAdapter = new UsbCLightningAdapter(iPhonePort);
    iPhone.charge(usbCAdapter);
  }

  private void chargeWithLightning(IPhone iPhone) {
    IPhonePort iPhonePort = new IPhonePort();
    iPhone.charge(iPhonePort);
  }

  public static void main(String[] args) {
    new Main();
  }
}
```

Wah mantap bukan?! Adapter USB-C Lightning pun sudah jadi dan siap dipakai! RiJukie senang melihat kreasi adapter ini :smiley:

Untuk isi code lebih jelasnya dapat kalian lihat pada [source code](smartphone).


## [Facade](Facade)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/structural/facade) | [refactoring.guru](https://refactoring.guru/design-patterns/facade) | [sourcemaking.com](https://sourcemaking.com/design_patterns/facade)

Facade adalah design pattern yang mempermudah Client untuk mengakses wilayah-wilayah dalam suatu ruang lingkup API. Facade memungkinkan anda untuk mengendalikan banyak object-object sekaligus dalam 1 genggaman tangan anda.

Dengan Facade, Client tidak perlu mengetahui seluruh isi class dalam library karena Facade-lah yang akan mengendalikan seluruh proses-proses di dalamnya.

Penggunaan Facade sendiri bertujuan untuk mengurangi kompleksitas class-class library terhadap pengguna namun penggunaan Facade sendiri tidak efektif apabila kasus yang dibutuhkan oleh Client benar-benar membutuhkan banyak hal yang Client butuhkan.

### Contoh kasus: Kamar

[Source code](room)

Ketika user dihadapkan dengan banyaknya hal yang perlu mereka lakukan untuk menyiapkan momen-momen penting seperti menyaksikan sepakbola, terkadang kita harus menyiapkan berbagai hal yang barangkali kita tidak inginkan seperti remote TV, channel TV yang ingin kita tonton, jumlah lampu yang dinyalakan, suhu AC, serta menyiapkan barang-barang yang kita inginkan saat itu.

Pemakaian facade ternyata berfungsi untuk mempermudah user dalam melakukan setup ruangan ketika user menginginkan hal-hal yang dibutuhkan oleh user terhadap ruangan (misal nyalakan tv, set channel olahraga, nyalakan AC, dan tutup jendela).

```java
package room;
public class TV {
  private String tvMode = "Television";

  public void powerOn() {
    System.out.println("Turn LED TV on");
    System.out.println("Current TV Mode: " + tvMode);
  }

  public void powerOff() {
    System.out.println("Turn LED TV off");
  }

  public void tvMode() {
    System.out.println("Set TV mode to Television");
    tvMode = "Television";
  }

  public void consoleMode() {
    System.out.println("Set TV mode to Game Console");
    tvMode = "Game Console";
  }

  public void setChannel(String channel) {
    System.out.println("Set TV channel to " + channel);
  }
}

public class AC {
  public void turnOn() {
    System.out.println("Turn AC ON");
  }
  public void turnOff() {
    System.out.println("Turn AC OFF");
  }
  public void cooler() {
    System.out.println("Set AC Cooler");
  }
  public void warmer() {
    System.out.println("Set AC Warmer");
  }
}

public class Lamp {
  public void turnOn() {
    System.out.println("Turn lamps ON");
  }

  public void turnOff() {
    System.out.println("Turn lamps OFF");
  }
}

public class Window {
  public void open() {
    System.out.println("Opening the window");
  }

  public void close() {
    System.out.println("Closing the window");
  }
}

public class Sofa {
  public void clean() {
    System.out.println("Cleaning the sofa");
  }
}
```

Dengan mengimplementasikan Facade, maka kita cukup menggunakan beberapa fitur yang dibutuhkan oleh user untuk keperluan-keperluan tertentu seperti nyalain TV, buka jendela, nyalain AC dalam 1 perintah sekaligus. Code design pattern Facade diimplementasikan sebagai berikut:

```java
package facade;
import room.*;

public class RoomFacader {
  private TV tv;
  private AC ac;
  private Sofa sofa;
  private Lamp lamp;
  private Window window;

  public RoomFacader(TV tv, AC ac, Sofa sofa, Lamp lamp, Window window) {
    this.tv = tv;
    this.ac = ac;
    this.sofa = sofa;
    this.lamp = lamp;
    this.window = window;
  }

  public void sportMode() {
    tv.powerOn();
    tv.tvMode();
    tv.setChannel("Bola TV");
    lamp.turnOn();
    ac.turnOn();
    ac.warmer();
    sofa.clean();
    window.close();
  }

  public void gamingMode() {
    tv.powerOn();
    tv.consoleMode();
    lamp.turnOn();
    ac.turnOn();
    ac.cooler();
    sofa.clean();
    window.close();
  }

  public void leaveRoom() {
    tv.powerOff();
    lamp.turnOff();
    ac.turnOff();
    sofa.clean();
    window.open();
  }
}
```

Ketika user menginginkan momen-momen tertentu tanpa harus kerepotan terhadap barang-barang yang diinteraksikan oleh user, user cukup menjalan perintah dari Facade sebagai berikut:

```java
package main;
import room.*;
import facade.RoomFacader;

public class Main {
  public Main() {
    AC ac = new AC();
    TV tv = new SmartTV();
    Sofa sofa = new Sofa();
    Lamp fan = new Lamp();
    Window window = new Window();

    RoomFacader facade = new RoomFacader(tv, ac, sofa, fan, window);
    facade.sportMode();
    System.out.println();
    facade.gamingMode();
    System.out.println();
    facade.leaveRoom();
  }

  public static void main(String[] args) {
    new Main();
  }
}
```

Dengan demikian, permasalahan terhadap masalah-masalah yang tidak diinginkan oleh user dapat dicegah dan user dapat menghemat waktu untuk mengurus berbagai urusan karena ditangani langsung oleh Facade.


## Decorator

Coming soon...

## Proxy

Coming soon...

## Bridge

Coming soon...

## Composite

Coming soon...

## Flyweight

Coming soon...

## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126.
- Refactoring.guru - [https://refactoring.guru/design-patterns/structural-patterns](https://refactoring.guru/design-patterns/creational-patterns)
- Sourcemaking.com - [https://sourcemaking.com/design_patterns/structural_patterns](https://sourcemaking.com/design_patterns/creational_patterns)
- Gang Of Four (GoF) Design Patterns - [https://www.journaldev.com/31902/gangs-of-four-gof-design-patterns](https://www.journaldev.com/31902/gangs-of-four-gof-design-patterns)