# Command

[Main Page](..) → [Behavioral Design Patterns](.) → [Command](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/command) | [refactoring.guru](https://refactoring.guru/design-patterns/command) | [sourcemaking.com](https://sourcemaking.com/design_patterns/command)

![Deploy command from event source to perform function call](https://refactoring.guru/images/patterns/content/command/command-en.png "Deploy command from event source to perform function call")

**Command** merupakan design pattern yang memisahkan sebuah *command* (perintah)/*function call* (panggilan fungsi) sebagai sebuah object class dimana perintah tersebut dapat dieksekusi dari mana saja (dari Button, shortcut, link, maupun elemen-elemen lain yang mengakses perintah tersebut).

Tanpa memisahkan command sebagai sebuah object class, komponen-komponen yang menggunakan perintah yang sama harus menggunakan code-code yang keberadaan dan fungsionalitasnya suatu command mirip di beberapa komponen sekaligus.

Selain bertujuan untuk memisahkan command-command (alias *function call*) yang tersebar di beberapa komponen object, **command** bertujuan agar dapat menerima beberapa parameter bila dibandingkan dengan menggunakan code-code yang sama namun beda dalam segi jumlah parameter saja.


## UML Diagram

![Command UML Diagram](../assets/img/behavioral/command.png#center "Command UML Diagram")

![Command UML Diagram](https://sourcemaking.com/files/v2/content/patterns/Command.png#white#center "Command UML Diagram")


## Essences of Command

### 1. Command Interface

**Command interface** berfungsi sebagai interface terhadap setiap function/command yang akan dijalankan oleh aplikasi dimana user dapat mendefinisikan perintah sesuai kebutuhan aplikasinya.

```java
public interface Command {
    void execute();
}
```

Interface ini mempunyai 1 method yaitu `execute()` dimana method ini akan digunakan oleh class implementator (class-class pemakai).

Selain dapat diturunkan ke class-class pemakai, interface ini dapat didefinisikan secara *annonymous inner* dalam client class maupun dibuatkan secara sederhana dengan *lambda expression* karena hanya memerlukan 1 method yang dipakai oleh class (**meski tidak direkomendasikan** dari segi ekstensibilitas dan melanggar OCP).


### 2. Command Class (alias Concrete Command)

**Command class** menggunakan *command interface* sebagai base classnya dimana user dapat mendefinisikan sebuah command beserta parameternya di dalam class ini. Nantinya perintah yang akan dieksekusi oleh receiver (alias class penerima).

Command class dapat berupa class yang berisikan perintah sederhana sebagai berikut:

```java
public class SimpleCommand implements Command {
    @Override
    public void execute() {
        // do something
    }
}
```

Atau juga dapat ditambahkan parameternya sebagai berikut:

```java
public class MyCommand implements Command {
    private String message;
    private Color color;

    public MyCommand(String message) {
        this(message, Color.BLACK);
    }

    public MyCommand(String message, Color color) {
        this.message = message;
        this.color = color;
    }

    @Override
    public void execute() {
        JLabel textLabel = new JLabel(message);
        text.setForeground(color);
        JOptionPane.showMessageDialog(null, textLabel);
    }
}
```


### 3. Invoker (alias Pengeksekusi/Eksekutor)

> Ini bukan hero DoTA apalagi DoTA 2! Dia adalah object/class/method yang mengeksekusi sebuah command.

**Invoker** (alias eksekutor) adalah object/class/method yang memanggil sebuah command. Invoker dapat dipanggil dari Client class langsung maupun class pemanggil command.

Gampangnya, Invoker akan memanggil command (bisa dari button, shortcut, event, maupun user action) dengan memanggil command yang didefinisikan pada objectnya.

Invoker dapat dipanggil langsung dari Client class (menggunakan method):

```java
Command copy = new CopyCommand(textArea);
copy.execute();
```

Atau dibuatkan secara tersendiri sebagai class:

```java
public class CommandExecutor {
    private Command command;

    public CommandExecutor(Command command) {
        this.command = command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeCommand() {
        command.execute();
    }
}
```

### 4. Receiver (alias Penerima)

**Receiver** akan menerima segala bentuk command/perintah dari Command untuk dijalankan secara independen. Object apapun berhak dapat dilibatkan sebagai receiver dimana command akan mendelegasikan sebagian/seluruh perintah kepada *receiver*.

Salah satu contoh bentuk delegasi `Command` ke `Receiver` adalah sebagai berikut:

```java
public class MyCommand implements Command {
    private TargetClass target;
    public MyCommand(TargetClass target) {
        this.target = target;
    }

    @Override
    public void execute() {
        target.targetMethod();
    }
}
```

```java
// An example receiver class (can be any classes/objects)
public class TargetClass {
    public void targetMethod() {
        // do something
    }
}
```


## Contoh Kasus

### A. Multiple commands in Text Editor

![Menu, Command, and Shortcut](https://refactoring.guru/images/patterns/diagrams/command/solution3-en.png#white#center "Menu, Command, and Shortcut")

Ketika sebuah text editor dihadapkan dengan banyaknya perintah dimana kita harus mencantumkan perintah satu-per-satu ke dalam 1 text editor sekaligus. Ketika fitur ditambahkan ke dalam text editor, maka kita harus menambahkan code yang sangat panjang apalagi jika code-code tersebut berisikan perintah-perintah yang sangat panjang apalagi memerlukan dependensi library luar.

Untuk menghadapi fitur-fitur yang membludak setiap saat, pembagian menu-menu sebagai **command class** dapat dilakukan dengan mengekstrak method-method yang terdapat dalam menu (termasuk toolbar dan shortcut) sebagai class tersendiri.

Misalnya terdapat 8 fitur yang ada dalam text editor yaitu:

- New file
- Open file
- Save file
- Cut
- Copy
- Paste
- Find
- Replace

Berangkat pada 3 contoh fitur dalam text editor, yaitu *Cut, Copy, dan Paste* dimana ketiga fitur ini dapat didefinisikan sebagai berikut:

```java
private void cut() {
    copy();

    // remove text from selection
}

private void copy() {
    String text = textEditor.getSelectedText();
    StringSelection data = new StringSelection(selection);
    Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
    cb.setContents(data, data);
}

private void paste(){
    Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
    DataFlavor flavor = DataFlavor.stringFlavor;
    String pastedText = "";
    if (clipboard.isDataFlavorAvailable(flavor)) {
        try {
            pastedText = (String) clipboard.getData(flavor);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    // attach pasted file to cursor
}
```

Dan dalam menunya didefinisikan sebagai berikut:

```java
private void defineMenus() {
    // new, open, save...

    cutMenuItem.addActionListener(e -> {
        cut();
    });

    copyMenuItem.addActionListener(e -> {
        copy(textEditor.getSelectedText());
    });

    pasteMenuItem.addActionListener(e -> {
        paste();
    });

    // other menus...
}
```

Ketika fitur-fitur tambahan dalam text editor tersebut membludak, maka panjang dari text editor sendiri akan semakin panjang dan tentunya akan mempersulit developer untuk mengembangkan satu aplikasi text editor yang berisikan banyak code yang kompleks.

Sebagai gantinya, ekstrak ketiga fitur dalam menu sebagai **command class** yang diturunkan dari interface `Command` yang berisikan method `execute()` yang akan mengeksekusi perintah-perintah dari text editor.

```java
public interface Command {
    void execute();
}
```

Lalu buatkan masing-masing class dengan implementasi interface `Command`. Anda juga bisa mengekstrak method-method yang akan dijalankan dalam *business logic* sebagai `Receiver` untuk meminimalisir dependensi secara langsung dari command class.

```java
public class CutCommand implements Command {
    private MyClipboard clip = new MyClipboard();
    private JTextArea textArea;
    private String text;

    public CutCommand(JTextArea textArea) {
        this.textArea = textArea;
        this.text = textArea.getSelectedText();
    }

    @Override
    public void execute() {
        clip.storeToClipboard();

        // remove text from selection
    }
}

public class CopyCommand implements Command {
    private MyClipboard clip = new MyClipboard();
    private JTextArea textArea;
    private String text;

    public CopyCommand(JTextArea textArea) {
        this.textArea = textArea;
        this.text = textArea.getSelectedText();
    }

    @Override
    public void execute() {
        clip.storeToClipboard();
    }
}

public class PasteCommand implements Command {
    private MyClipboard clip = new MyClipboard();
    private JTextArea textArea;
    private String text;

    public PasteCommand(JTextArea textArea) {
        this.textArea = textArea;
        this.text = textArea.getSelectedText();
    }

    @Override
    public void execute() {
        String text = clip.getFromClipboard();
        
        // attach pasted text to cursor
    }
}
```

```java
// An example of receiver class, executed from Command
public class MyClipboard {
    private Clipboard clipboard;

    public MyClipboard() {
        clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    }

    public String getFromClipboard(){
        DataFlavor flavor = DataFlavor.stringFlavor;
        if (clipboard.isDataFlavorAvailable(flavor)) {
            try {
                return (String) clipboard.getData(flavor);
            } catch (Exception e){
                e.printStackTrace();
            }
        } return "";
    }

    public void storeToClipboard(String text) {
        StringSelection data = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(data, data);
    }
}
```

Command class juga berfungsi sebagai sarana penampungan fitur ketika user ingin menambahkan custom shortcut ke text editor dimana user cukup menggunakan command yang ada dalam text editor (yang terdefinisikan) sebagai *trigger* dalam shortcut yang dibuat oleh user.

Contoh:

```java
// only demonstrate cut, copy, and paste
private Command cut, copy, paste;

private void defineCommands() {
    cut = new CutCommand(textArea);
    copy = new CopyCommand(textArea);
    paste = new PasteCommand(textArea);
}

private void defineMenus() {
    cutMenuItem.addActionListener(e -> {
        cut.execute();
    });

    copyMenuItem.addActionListener(e -> {
        copy.execute();
    });

    pasteMenuItem.addActionListener(e -> {
        paste.execute();
    });
}

private void bindShortcut(Shortcut shortcut, Command command) {
    // bind the shortcut to command
}
```

```java
// assume that user customized "ctrl+alt+c" as Copy command.
bindShortcut("ctrl+alt+c", copy);
```

### B. Macro (Set of Commands)

![Macro, mixed Command and Composite implementation](../assets/img/behavioral/command-macro.png#center "Macro, mixed Command and Composite implementation")

Command juga dapat digunakan untuk membuat macro yang dapat mengeksekusi command lain. Caranya adalah dengan memanggil sebuah macro (berupa class `MacroCommand`) dimana dalam macro berisikan beberapa command di dalamnya untuk dieksekusi oleh command tersebut.

Macro sendiri merupakan perpaduan antara **command design pattern** dengan **composite design pattern** dimana di dalam macro akan ada sekumpulan urutan command yang tersimpan dalam command list (Vector/ArrayList/Array) dan dijalankan sekaligus melalui `execute()`.

Misalnya dalam permainan bergenre *Fighting Combat* (pertarungan) berbasis *Action Card*, player harus menyertakan pilihan untuk menyerang player lawan dimana terdapat 5 pilihan combat, yaitu *defend, punch, kick, punch,* dan *combo attack*.

Setiap player harus memilih salah satu dari 5 pilihan combat sebagai 1 set combat dari player. Khusus combo attack, pemain dapat memilih kombinasi serangan yang diinginkan sebagai serangkaian 1 set (maksimal 3 serangan dalam 1 set).

```java
public interface Command {
    void execute();
}

public class DefendCommand implements Command {
    private Player player;

    public DefendCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.defend();
    }
}

public class HitCommand implements Command {
    private Player player, enemy;

    public HitCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        player.hit(enemy);
    }
}

public class PunchCommand implements Command {
    private Player player, enemy;

    public PunchCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {
        player.punch(enemy);
    }
}

public class KickCommand implements Command {
    private Player player, enemy;

    public KickCommand(Player player, Player enemy) {
        this.player = player;
        this.enemy = enemy;
    }
    
    @Override
    public void execute() {
        player.kick(enemy);
    }
}

public class ComboAttackCommand implements Command {
    private ArrayList<Command> attackSets;

    public ComboAttackCommand(ArrayList<Command> attackSets) {
        this.attackSets = attackSets;
    }

    @Override
    public void execute() {
        for (Command attack : attackSets) {
            attack.execute();
        }
    }
}
```

Contoh implementasi dari sebuah serangan spesial adalah sebagai berikut:

```java
Player player = new Player();
Player bot = new Player();

ArrayList<Command> attackSets = new ArrayList<>();
attackSets.add(new KickCommand(player, bot));
attackSets.add(new KickCommand(player, bot));
attackSets.add(new PunchCommand(player, bot));

Command deadlyKickPunch = new ComboAttackCommand(attackSets);
```

### C. Order

Sebuah **command** juga bisa difungsikan sebagai object yang dapat dipanggil dan ditampung dalam sebuah list (termasuk *queue* dan *stack*).

Pada contoh kasus berikut, user dapat menempatkan order ke Chef melalui perantara yaitu `Waiter` dimana beberapa order yang dipesan oleh user ditampung dalam `OrderList`.

Sebuah order dapat ditambahkan, dikurang, maupun diproses ordernya melalui command interface `OrderCommand` dimana command tersebut akan memproses `OrderList`. Sebuah *command* dapat dibuatkan sebagai *batch* dimana sekumpulan command terhadap order tersebut diproses ke dalam `OrderList` dimana command tersebut dapat dieksekusi sekaligus oleh `Waiter` jika ingin diproses sekaligus dalam 1 paket order.

```java
public class Order {
    private Menu menu;
    private int quantity;

    public Order(Menu menu, int quantity) {
        this.menu = menu;
        this.quantity = quantity;
    }

    public void execute() {
        System.out.printf("Executing %s (x%d)...\n", menu.getName(), quantity);
    }

    public Menu getMenu() {
        return menu;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s (Rp%d x %d)",
                    menu.getName(), menu.getPrice(), quantity);
    }
}
```

```java
public class OrderList {
    private Vector<Order> orderList;

    public OrderList() {
        orderList = new Vector<>();
    }

    public void addOrder(Order order) {
        orderList.add(order);
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }

    public void executeOrder() {
        for (Order order : orderList) {
            order.execute();
        } orderList.clear();
    }

    @Override
    public String toString() {
    	if (orderList.isEmpty()) {
    		return " >> no available orders!\n";
    	}
        String str = "";
        for (Order order : orderList) {
            str += " - " + order.toString() + "\n";
        } return str;
    }
}
```

```java
public interface OrderCommand {
    void execute(OrderList orderList);
}

public class AddOrder implements OrderCommand {
    private Order order;

    public AddOrder(Order order) {
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void execute(OrderList orderList) {
        System.out.println("Add order '" + order + "'...");
        orderList.addOrder(order);
    }
}

public class RemoveOrder implements OrderCommand {
    private Order order;

    public RemoveOrder(Order order) {
        this.order = order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public void execute(OrderList orderList) {
        System.out.println("Remove order '" + order + "'...");
        orderList.removeOrder(order);
    }
}

public class ExecuteOrder implements OrderCommand {
    @Override
    public void execute(OrderList orderList) {
        System.out.println("Placing order to Chef...");
        orderList.executeOrder();
    }
}
```

```java
public class Waiter {
    private OrderList orderList;
    
    public Waiter() {
        orderList = new OrderList();
    }
    
    public void addOrder(Order order) {
        execute(new AddOrder(order));
    }
    
    public void removeOrder(Order order) {
        execute(new RemoveOrder(order));
    }

    public void executeOrder() {
        execute(new ExecuteOrder());
    }
    
    public void executeBatch(ArrayList<OrderCommand> orderCommands) {
        for (OrderCommand command : orderCommands) {
            execute(command);
        }
    }

    public void displayOrderedMenu() {
        System.out.println(orderList.toString());
    }

    private void execute(OrderCommand command) {
        command.execute(orderList);
    }
}
```

Nantinya ketika user ingin memasukkan order ke Waiter, maka waiter akan memasukkan order dari user melalui class implementasi `OrderCommand` yaitu `AddOrder` dengan memasukkan `Order` sebagai parameter deklarasi dan dapat dieksekusi langsung dengan memanggil `ExecuteCommand`.

```java
Menu nasiPadangTelor = new Menu("Nasi Padang Telor", 14000);
Menu nasiPadangRendang = new Menu("Nasi Padang Rendang", 17000);
Menu nasiGoreng = new Menu("Nasi Goreng", 13000);

Waiter waiter = new Waiter();

waiter.addOrder(new Order(nasiPadangTelor, 2));
waiter.addOrder(new Order(nasiPadangRendang, 1));
waiter.executeOrder();

waiter.addOrder(new Order(nasiGoreng, 1));
waiter.executeOrder();
```

Ketika order tersebut diproses oleh Chef, maka order-order tersebut dieksekusi dan dikeluarkan dari `OrderList` karena order sudah diproses oleh Chef.

Selain itu, ketika order tersebut ingin diproses sebagai satu kesatuan paket order, maka `Waiter` tinggal memasukkan satu kesatuan order dalam command ke dalam `OrderList` sekaligus melalui `executeBatch()`.

```java
Menu nasiGoreng = new Menu("Nasi Goreng", 13000);
Menu tempeGoreng = new Menu("Tempe Goreng", 6000);
Menu tehTawar = new Menu("Teh Tawar", 3000);

ArrayList<OrderCommand> commands = new ArrayList<>();
commands.add(new AddOrder(new Order(nasiGoreng, 2)));
commands.add(new AddOrder(new Order(tempeGoreng, 1)));
commands.add(new AddOrder(new Order(tehTawar, 2)));
commands.add(new ExecuteOrder());

Waiter waiter = new Waiter();
waiter.executeBatch(commands);
```

Maka output yang diperoleh dengan memproses order sekaligus adalah sebagai berikut:

```
Add order 'Nasi Goreng (Rp13000 x 2)'...
Add order 'Tempe Goreng (Rp6000 x 1)'...
Add order 'Teh Tawar (Rp3000 x 2)'...
Placing order to Chef...
Executing Nasi Goreng (x2)...
Executing Tempe Goreng (x1)...
Executing Teh Tawar (x2)...
```

Menariknya lagi, proses dan command dari Order sendiri dapat dikombinasikan dengan **memento design pattern** dimana dalam class `OrderList`, state dari Vector penampung order sendiri dapat disimpan seiring dengan penambahan dan pemrosesan sebuah order. Pada kasus order history sendiri akan dibahas di contoh kasus [Memento](Memento#contoh-kasus).


## Catatan Tambahan

Perlu kalian ketahui bahwa **Command** mempunyai hubungan yang erat dengan **Memento design pattern** dimana command dapat dibuatkan alur yang dapat di-*rollback* maupun *undo* dengan menggunakan state yang sudah dijalankan sebelumnya oleh command.

Selain dapat di-undo/rollback dengan bantuan *memento*, command dapat digunakan untuk:

- Membuat macro (beberapa Command yang digabung ke dalam satu Command)
- Memindahkan pemilihan alur yang menggunakan if-else/switch-case menjadi Map (sempat disebut di topik Observer)
- Mempermudah passing, terutama di bahasa pemrograman yang tidak mendukung penyimpanan fungsi ke dalam variabel.
- Mengurangi redundasi code (code-code yang sama) dengan menampatkannya ke dalam satu Command.

### Runnable, Thread implementation for Command

Jika anda ingin menjalankan command secara langsung dalam sebuah thread, Java menyediakan sebuah interface bernama `Runnable` dimana dalam interface tersebut terdapat method `run()` yang berisikan perintah-perintah yang akan dijalankan dalam thread.

Nantinya setelah object `Runnable` tersebut didefinisikan (baik melalui class implementasi, *annonymous inner*, maupun *lambda expression*), object tersebut dapat dijalankan dengan method `run()` yang dimana object tersebut akan dieksekusi di thread baru.

Contoh code:

```java
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        // do something..
    }
}
// ...
Runnable t = new MyRunnable();
```

```java
Runnable run = new Runnable() {
    @Override
    public void run() {
        // do something..
    }
}
```

```java
Runnable run = () -> {
    // do something..
}
```


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 263-273.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 191-233.
- Refactoring.guru (Command, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/command](https://refactoring.guru/design-patterns/command)
- SourceMaking (Command, termasuk referensi gambar) - [https://sourcemaking.com/design_patterns/command](https://sourcemaking.com/design_patterns/command)
- Gang Of Four (GoF) Design Patterns: Command - [https://www.journaldev.com/1624/command-design-pattern](https://www.journaldev.com/1624/command-design-pattern)
- Java JDK 7 Docs: Runnable - [https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html)