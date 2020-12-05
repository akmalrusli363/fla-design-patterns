# Observer

[Main Page](..) → [Behavioral Design Patterns](.) → [Observer](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/structural/observer) | [refactoring.guru](https://refactoring.guru/design-patterns/observer) | [sourcemaking.com](https://sourcemaking.com/design_patterns/observer)

![Observer](../assets/img/behavioral/observer.png#center "Observer")

Merupakan design pattern yang menggunakan 2 Class, yaitu Observable (misalnya Penulis/Channel) sebagai sumber informasi dan Observer/Watcher (misalnya Pembaca/Viewer) sebagai penerima informasi.

Dalam Observer design pattern, Observable memiliki banyak Observer dimana setiap Observer berhak menerima informasi yang diterima melalui perintah yang dipanggil dari Observable.

Tujuan dari Observer design pattern menurut para penulis Gang of Fours adalah untuk menciptakan relasi *one-to-many* dimana ketika sebuah Subject/Observable mengalami perubahan, maka setiap penerima subject akan menerima update dari subject yang terdampak.

Pemakaian Observer sendiri juga sering diaplikasikan dalam *Event Listener* dalam beberapa bahasa pemrograman yang dapat menerima dan merespon input dari User.

## Essences of Observer

### 1. Observer (Reader/Subscriber/Viewer)

Berangkat dari kasus sehari-hari, seorang pembeli bisa saja mendatangi toko berkali-kali untuk hanya sekedar melihat produk yang konsumen inginkan atau malahan toko yang menawarkan produknya kepada konsumen berulang-ulang padahal konsumen tidak terlalu membutuhkan hal tersebut berulang-ulang.

**Observer** dalam design pattern disajikan sebagai Interface beserta implementasi sebagai penerima informasi sekaligus, dengan menyertakan beberapa method-method yang akan dipakai oleh class pemakainya sebagai Subscriber/penerima informasi.

Contoh code:

```java
public interface Observer {
    void notify();
}

public class Subscriber {
    private String name;

    public Subscriber(String name) {
    this.name = name;
  }

    public void notify(){
        System.out.println("You got new content from your subscribed channel!");
    }
}
```

### 2. Observable (Publisher/Channel)

Pada class **Observable**, akan ditampung array yang berisikan berbagai Observer-observer yang ikut turut serta di dalamnya dan dapat dikabarkan kepada para observer bila Observable memberikan informasi kepada para pemakainya.

Segala hal-hal yang berkaitan dengan pertambahan/pengurangan jumlah penonton/observer akan difasilitasi oleh **Observable** sebagai pihak yang mengatur keberadaan Observer dalam memperoleh informasi terbaru dan tercepat secara langsung.

Contoh code:

```java
public interface Observable {
  public void addSubscriber(User user);
  public void removeSubscriber(User user);
  public void notifyUser(String msg);
}

public class Channel implements Observable {
  private String name;
  private Vector<User> subscibers;
  
  public Channel(String name) {
    this.name = name;
    this.subscibers = new Vector<User>();
  }

  @Override
  public void addSubscriber(User user) {
    subscibers.add(user);
  }

  @Override
  public void removeSubscriber(User user) {
    subscibers.remove(user);
  }

  @Override
  public void notifyUser(String msg) {
    System.out.println(name + " posted a new video!");
    String constructedMessage = String.format("%s: %s", name, msg);
    for(User user : subscibers) {
      user.receiveNotification(constructedMessage);
    }
  }
}
```

## UML Model

![Observer Model](../assets/img/behavioral/observer-model.png#center "Observer Model")


## Contoh Kasus

![Channel and Subscriptions](../assets/img/behavioral/observer-channel.png#center "Observer Model")

Sebuah Channel dalam sebuah aplikasi streaming dihadapkan dengan banyaknya penonton yang ingin selalu up-to-date dengan kontennya. Ada dua cara yang dilakukan para developer pada saat itu untuk menghadapi kasus tersebut, melakukan update berkala secara rutin atau membiarkan penonton kesepian dan cari konten terbaru dari channel tersebut sampai jenuh?

Kedua cara tersebut tentunya tidak baik sama sekali karena tentunya tidak baik bagi para penonton yang mengikuti konten tersebut karena penonton bisa jadi akan selalu kena spam dari channel yang ia langgankan atau malah bosan/jenuh karena tidak ada konten yang bisa ia tonton.

Karena itulah, ada satu solusi bagi developer dalam menghadapi masalah tersebut yaitu dengan menghadirkan 2 actor dalam design pattern yaitu **Observer**/Subscriber/Viewer yang berperan serta dalam memperoleh informasi yang tercepat dan terupdate dari sumber informasinya yaitu **Observable**/Publisher/Channel.

Seperti dalam kasus analogi Koran dan Majalah, pembaca dapat mendapatkan update secara rutin baik setiap harinya maupun setiap minggunya dengan ikut serta dalam langganan koran/majalah dengan ongkos yang telah ditentukan. Nantinya media cukup mengirim informasi tersebut secara langsung kepada konsumen dan konsumen tinggal menggunakan fasilitas tersebut tanpa harus mendatangi agen penjual koran/majalah terdekat.

### Implementasi Code

Pertama-tama, buatlah 2 interface yaitu `Observer` dan `Observable`:

```java
package observer;

public interface Observer {
  public void receiveNotification(String msg);
}

public interface Observable {
  public void addSubscriber(User user);
  public void removeSubscriber(User user);
  public void notifyUser(String msg);
}
```

Kemudian buatlah class `User` dengan implementasi interface `Observer` di bawah. Pastikan class tersebut telah mengimplentasikan method-method dari class Observer untuk kemudian diakses oleh implementasi interface `Observable`.

```java
public class User implements Observer {
  private final String name;

  public User(String name) {
    this.name = name;
  }

  @Override
  public void receiveNotification(String msg) {
    System.out.println(msg);
  }

  public void subscribe(Channel channel) {
    channel.addSubscriber(this);
  }
  
  public void unsubscribe(Channel channel) {
    channel.removeSubscriber(this);
  }
  
  public String getName() {
    return name;
  }  
}
```

Setelah itu, buatlah class `Channel` dengan implementasi interface `Observable` dimana nantinya akan menampung object-object interface `Observer` untuk diakses secara langsung oleh class tersebut.

```java
public class Channel implements Observable {
  private String name;
  private Vector<User> subscibers;
  
  public Channel(String name) {
    this.name = name;
    this.subscibers = new Vector<User>();
  }

  @Override
  public void addSubscriber(User user) {
    subscibers.add(user);
  }

  @Override
  public void removeSubscriber(User user) {
    subscibers.remove(user);
  }

  @Override
  public void notifyUser(String msg) {
    System.out.println(name + " posted a new video!");
    String constructedMessage = String.format("%s: %s", name, msg);
    for(User user : subscibers) {
      user.receiveNotification(constructedMessage);
    }
  }
}
```

Nantinya segala bentuk notifikasi yang diterima oleh User akan dikirimkan oleh Observable (Channel) untuk mendapatkan informasi terbaru dan terkini dari Channel kepada user-user (observer) yang ada dalam list saja.

Ketika channel ingin menyalurkan konten baru, channel tinggal menyalurkan informasi tersebut dengan memanggil method `notifyUser()` kepada setiap subscriber.

```java
public class Main {
  public static void main(String[] args) {
    Channel ch = new Channel("Budi Setiawan Channel");
    User user1 = new User("Wika Wika");
    User user2 = new User("Jobot TV");
    User user3 = new User("Jinjit Karunia");
    
    ch.addSubscriber(user1);
    ch.addSubscriber(user2);
    ch.notifyUser("Ada video baru buat anda! Jangan lupa di subscribe ya!");
    
    user3.subscribe(ch);
    ch.notifyUser("Video baru!");
  }
}
```


## Implementasi: EventListener

Penerapan Observer design pattern juga dilakukan pada class-class yang menerapkan Observer design pattern dalam beberapa bahasa pemrograman, termasuk Java dimana class tersebut sering digunakan pada tampilan User Interface yaitu `EventListener`.

![EventListener in Java](../assets/img/Behavioral/observer-java-eventlistener.gif#center "EventListener in Java")

Event Listener sendiri sering dipakai pada elemen-elemen yang memiliki keterikatan dan interaksi dengan langsung dengan user seperti Button, Checkbox, Drop down, Menu item, dan komponen-komponen lainnya. Ada banyak Action/Event Listener yang dapat ditambahkan pada object-object dengan implementasi Event Listener seperti `KeyListener`, `MouseListener`, `ActionListener`, `WindowListener`, dan lain-lainnya.

Untuk mengetahui bagaimana implementasi design pattern tersebut, kita dapat umpamakan hal tersebut dalam tiga lingkup, yaitu window, object components, dan user input (baik mouse maupun keyboard).

Pada basis view, anggap kita buat class yang di-extend dari `JFrame` untuk menampilkan GUI yang berisikan beberapa komponen, terdapat window *(JFrame)* yang berisikan beberapa komponen di dalamnya yang dideklarasikan melalui inisialisasi object di dalam window tersebut.

```java
public class MyFrame extends JFrame {
  private JButton button;
  
  public void init() {
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public MyFrame() {
    init();
    button = new JButton("Click Me!");
    add(button);
  }

  // ...
}
```

Ketika user ingin menggunakan menghadirkan event-event yang diberikan pada komponen tersebut, kita harus menggunakan EventListener yang akan dibenamkan pada komponen tersebut.

### Contoh Kasus

Pada contoh kasus berikut, kita ingin menambahkan beberapa event pada sebuah tombol *(JButton)* misalnya KeyListener, ActionListener, dan MouseListener.

Sebelum dapat ditambahkan pada KeyListener, MouseListener, ActionListener, kita harus definisikan sendiri bentuk EventListener yang ingin kita implementasikan pada komponen yang dituju, misalnya Button. Ada 3 cara pendefinisian EventListener, yaitu:

1. Pembuatan class dengan implementasi interface berbasis EventListener, dimana nantinya dideklarasikan pada Event yang kita tambahkan.

   ```java
   public class MyMouseListener implements MouseListener {
     @Override
     public void mouseClicked(MouseEvent e) {
       JOptionPane.showMessageDialog(null, "You pressed using mouse!");
     }

     @Override
     public void mouseEntered(MouseEvent e) { }

     @Override
     public void mouseExited(MouseEvent e) { }

     @Override
     public void mousePressed(MouseEvent e) { }

     @Override
     public void mouseReleased(MouseEvent e) { }
   }
   ```

   ```java
   public void addButtonListener() {
     button.addMouseListener(new MyMouseListener());
   }
   ```

2. Pembuatan method dengan return value berupa object interface berbasis EventListener, dimana kita dapat mendefinisikan sendiri isi-isi deklarasi dalam return valuenya.

   ```java
   public KeyListener pressedUsingKeyboardListener() {
     return new KeyListener() {
       @Override
       public void keyPressed(KeyEvent e) { }

       @Override
       public void keyReleased(KeyEvent e) { }

       @Override
       public void keyTyped(KeyEvent e) {
         JOptionPane.showMessageDialog(null, "You pressed using keyboard!");
       }
     }
   }
   ```

3. Deklarasi langsung object interface berbasis EventListener di dalam parameter sebuah method.

    ```java
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "You performed an action for this button!");
      }
    });
    ```

    Atau dalam konteks sederhananya (Java 8+), kita juga bisa sederhanakan deklarasi tersebut menggunakan Lambda Expression dengan syarat interface tersebut hanya memiliki 1 method yang wajib diimplementasikan dalam deklarasi object tersebut.

    ```java
    button.addActionListener((e) -> {
      JOptionPane.showMessageDialog(null, "You performed an action for this button!");
    });
    ```

Pada skeneario EventListener dalam contoh kasus, `MouseListener` dideklarasikan sebagai class tersendiri dan pembuatan object dari class tersebut akan dilakukan ketika Client ingin menggunakan EventListener pada main classnya dengan detail implementasi code sebagai berikut:

```java
public class MyFrame extends JFrame {
  private JButton button;

  public void init() {
    setSize(500, 500);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public MyFrame() {
    init();
    button = new JButton("Click Me!");
    add(button);
  }
  
  public void addButtonListener(MouseListener listener) {
    button.addMouseListener(listener);
  }
  
  public void removeButtonListener(MouseListener listener) {
    button.removeMouseListener(listener);
  }
}
```

Ketika client ingin menambahkan EventListener kepada komponen, client cukup menggunakan fitur yang ada dalam component untuk menambahkan `EventListener` ke komponen yang ingin Client tambahkan.

```java
public class Main {
  public static void main(String[] args) {
    MyFrame frame = new MyFrame();
    frame.addButtonListener(new MyMouseListener());
    frame.setVisible(true);
  }
}
```

Ketika komponen tersebut mendapatkan perlakuan dari keyboard, mouse, maupun event-event lain, maka komponen akan memanggil interface `EventListener` untuk menjalankan aksi yang diperoleh dari event yang terjadi pada komponen tersebut.


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126.
- Refactoring.guru (Observer) - [https://refactoring.guru/design-patterns/observer](https://refactoring.guru/design-patterns/observer)
- SourceMaking (Observer) - [https://sourcemaking.com/design_patterns/observer](https://sourcemaking.com/design_patterns/observer)
- Gang Of Four (GoF) Design Patterns: Adapter - [https://www.journaldev.com/1557/observer-design-pattern-in-java](https://www.journaldev.com/1557/observer-design-pattern-in-java)
- Java Tutorial: Introduction to Event Listeners - [https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html](https://docs.oracle.com/javase/tutorial/uiswing/events/intro.html)
- Java 1.1 Event Model @ Universidad de Granada (ilustrasi gambar) - [https://bioinfo2.ugr.es/OReillyReferenceLibrary/java/awt/ch04_03.htm](https://bioinfo2.ugr.es/OReillyReferenceLibrary/java/awt/ch04_03.htm)