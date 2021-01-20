# Memento

[Main Page](..) → [Behavioral Design Patterns](.) → [Memento](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/memento) | [refactoring.guru](https://refactoring.guru/design-patterns/memento) | [sourcemaking.com](https://sourcemaking.com/design_patterns/memento)

![State, kunci dari Memento](../assets/img/behavioral/memento.png#center "State, kunci dari Memento")

![State-by-state draw (including undos)](../assets/img/behavioral/memento.gif#center "State-by-state draw (including undos)")

> Mungkin barangkali design pattern ini adalah yang paling favorit & tersulit bagi kalian semua :satisfied:.. Ya mungkin karena kalian pasti sangat memikirkan cara undo-redonya.
>
> Berterimakasihlah kepada **Memento**, karena tanpa dia, kerjaan anda (baik kerjaan tugas, skripsi, gambar, design, hingga modeling) bakalan hancur berantakan oleh kesalahan kalian sendiri :scream:
>
> (...Kamu udah mati-matian 2 hari gambar karakter anime favoritmu, udah bagus banget eh digarisin sama adikmu sendiri wkwk :joy:)

**Memento** adalah design pattern yang memungkinkan anda untuk melakukan *undo* alias kembali ke state sebelumnya. Sudah banyak aplikasi (text editor, image editor, hingga game) yang sudah support undo dan redo. Fitur undo dan redo memungkinkan anda untuk mengembalikan state saat ini ke state sebelumnya ketika anda melakukan kesalahan/melakukan hal yang tidak diinginkan.

**Memento** menggunakan **_snapshot_** sebagai penampungan berbagai state/command yang sudah dijalankan sebelumnya dimana state hasil command yang sudah dijalankan sebelumnya akan dicatat dalam *history log* agar bisa di-*undo* dan *redo* sesuai kebutuhan user.

Dengan **memento design pattern**, fitur *undo* dan *redo* dapat diimplementasikan dalam sebuah class sehingga command-command yang tidak diinginkan oleh user dapat dikembalikan ke state sebelumnya.


## UML Diagram

![Memento UML Diagram](../assets/img/behavioral/memento-model.png#center "Memento UML Diagram")

![Memento Sequence Diagram](../assets/img/behavioral/memento-sequences.png#center "Memento Sequence Diagram")


## Essences of Memento (Snapshots)

### 1. Originator

`Originator` bisa berupa class apapun yang mempunyai state dan berisikan attribute-attribute penting yang berperan penting dalam object tersebut. Atribut-atribut yang berperan penting dalam class tersebut juga nantinya akan ditampung dalam subclass *(nested class)* melalui constructor yang bernama `Memento`.

`Originator` harus mengandung method `save()` dan `restore(Memento state)` dimana kedua method tersebut sangat berperan dalam mengatur keberadaan state pada suatu object dengan menyimpan state yang sudah dijalankan/dikenakan pada object tersebut sebagai object `Memento` dan dikembalikan statenya apabila state pada object tersebut di-*undo* maupun *redo*.

```java
public class TextContent {
    private String content;

    public TextContent() {
        this.content = "";
    }

    public TextContent(String content) {
        this.content = content;
    }

    public void append(String newText) {
        content.concat(newText);
    }

    public void setText(String newText) {
        content = newText;
    }

    @Override
    public String toString() {
        return content;
    }

    public Memento save() {
        return new Memento(content);
    }

    public void restore(Memento state) {
        content = state.getState();
    }
}
```

Atau bahasa sederhana enkapsulasinya sebagai berikut:

```java
public interface Originator<T> {
    Memento<T> save();
    void restore(Memento<T> state);
}

public class TextContent implements Originator<String> {
    // The state of the content
    private String content;

    public TextContent() {
        this.content = "";
    }

    public TextContent(String content) {
        this.content = content;
    }

    public void append(String newText) {
        content.concat(newText);
    }

    public void setText(String newText) {
        content = newText;
    }

    @Override
    public String toString() {
        return content;
    }

    @Override
    public Memento<String> save() {
        return new Memento<String>(content);
    }

    @Override
    public void restore(Memento<String> state) {
        content = state.getState();
    }
}
```


### 2. Memento

`Memento` adalah object yang akan menyimpan state-state penting dari `Originator` ke dalam salinannya. Memento sendiri dapat dideklarasikan dalam inner class (sebagai *nested class*) maupun secara eksternal (melalui constructor copy).

Ada 2 cara deklarasi Memento:

1. Melalui inner class (sebagai *nested class*):

   ```java
   public class TextContent {
      private String content;

      public TextContent() {
         this.content = "";
      }

      public TextContent(String content) {
         this.content = content;
      }

      public void append(String newText) {
         content.concat(newText);
      }

      public void setText(String newText) {
         content = newText;
      }

      @Override
      public String toString() {
         return content;
      }

      public Memento save() {
         return new Memento(content);
      }

      public void restore(Memento m) {
         content = m.getState();
      }

      public static class Memento {
         private String state;

         public Memento(String state) {
               this.state = state;
         }

         public String getState() {
               return state;
         }
      }
   }
   ```

2. Dideklarasikan secara eksternal (melalui constructor copy):

   ```java
   public class Memento {
      private String todoState;

      public Memento(String todoState) {
         this.todoState = todoState;
      }

      public String getState() {
         return todoState;
      }
   }
   ```

   Atau dalam bentuk Generic class:

   ```java
   public class Memento<T> {
      private T todoState;

      public Memento(T todoState) {
         this.todoState = todoState;
      }

      public T getState() {
         return todoState;
      }
   }
   ```

### 3. Caretaker

**Caretaker** merupakan class yang mengetahui kondisi state pada suatu object dan menyimpan state-state yang sudah dieksekusi pada object tersebut ke dalam sekumpulan memento (alias *history*).

Class apapun (termasuk client class) dapat berperan sebagai **_caretaker_** dimana class tersebut memiliki attribute berupa object `Originator` dan sekumpulan state dari object tersebut.

Ketika object tersebut sudah dieksekusi oleh sekumpulan perintah, maka state tersebut akan disimpan ke dalam bentuk memento dan ditampung ke dalam *history*.

Agar command/perintah dapat di-*redo*, maka dalam class `Caretaker` disediakan pointer index dimana pointer index pada array bergerak ke index sebelumnya ketika di-*undo* dan bergerak ke index berikutnya ketika di-*redo*.

```java
public class Caretaker {
    private int index = 0;
    private ArrayList<Memento> history = new ArrayList<>();

    /**
     * Add memento to history. Remove undoned history if exist.
     * @param memento Memento to add
     */
    public void addMemento(Memento memento) {
        this.removeMementoOnUpdate();
        history.add(memento);
        index = history.size()-1;
    }
    
    /**
     * Remove memento from post-current index to size of list.
     */
    private void removeMementoOnUpdate() {
        int size = history.size();
        for(int i = index+1; i < size; i++) {
            history.remove(i);
        }
        history.trimToSize();
    }
    
    /**
     * Move history index backward if index < 0
     * 
     * @return memento object on current index (after decremental)
     */
    public Memento undo() {
        index = (index > 0) ? index - 1 : 0;
        return history.elementAt(index);
    }

    /**
     * Move history index forward if index < (list size - 1)
     * 
     * @return memento object on current index (after incremental)
     */
    public Memento redo() {
        int maxIndex = history.size() - 1;
        index = (index < maxIndex) index + 1 : maxIndex;
        return history.elementAt(index);
    }
}
```


## Contoh Kasus

### A. Todo List

Pada contoh yang paling sederhana, misalnya di dalam todo list terdapat sekumpulan task yang merepresentasikan isi dan status todo list itu sendiri.

Ketika seseorang menambahkan task ke dalam todo list, maka state dalam todo list akan tersimpan dalam history. Hal ini juga berlaku juga ketika ia mengubah isi/status dalam task maupun menghapus task dari todo list dimana hal tersebut juga mempengaruhi state yang ada dalam todo list.

Agar todo list dapat di-*undo* maupun di-*redo*, sebuah todo list harus mempunyai state dimana state tersebut mewakili keberadaan todo list saat itu. Nantinya ketika state tersebut mengalami perubahan (baik terpengaruh secara eksternal maupun melalui perintah/command), maka state berikutnya akan disimpan ke dalam *list of mementos* alias **_history_**. Hal ini juga berlaku ketika object tersebut dideklarasikan dimana state tersebut akan disimpan sebagai *initial state* dari object tersebut.

```java
public class ToDo {
    private String content;
    private boolean done;

    public ToDo(String content) {
        this.content = content;
        this.done = false;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDone() {
        return done;
    }

    public String getContent() {
        return content;
    }
}

public class ToDoList {
    private ArrayList<ToDo> todoList;

    public ToDoList() {
        this.todoList = new ArrayList<>();
    }

    public ToDoList(ArrayList<ToDo> todoList) {
        this.todoList = todoList;
    }

    public void add(ToDo todo) {
        todoList.add(todo);
    }

    public ToDo get(int index) {
        return todoList.get(index);
    }

    public void update(int index, ToDo todo) {
        todoList.set(index, todo);
    }

    public void remove(int index) {
        todoList.remove(index);
    }

    public String getAll() {
        String output = "";
        if (todoList == null || todoList.isEmpty()) {
            return " No todo entries...\n";
        }
        for (ToDo todo : todoList) {
            char done = (todo.isDone()) ? 'v' : ' ';
            output += String.format("(%c) %s\n", done, todo.getContent());
        } return output;
    }

    public Memento save() {
        return new Memento(todoList);
    }

    public void restore(Memento m) {
        todoList = m.getState();
    }
}
```

```java
public class Memento {
    private ArrayList<ToDo> todoState;

    public Memento(ArrayList<ToDo> todoState) {
        this.todoState = todoState;
    }

    public ArrayList<ToDo> getState() {
        return todoState;
    }
}
```

```java
public class Caretaker {
    private ArrayList<Memento> history = new ArrayList<>();
    private int index = 0;

    public void addMemento(Memento memento) {
        this.removeMementoOnUpdate();
        history.add(memento);
        index = history.size()-1;
    }

    private void removeMementoOnUpdate() {
        int size = history.size();
        for (int i = index + 1; i < size; i++) {
            history.remove(i);
        }
        history.trimToSize();
    }

    public Memento undo() {
        index = (index > 0) ? index - 1 : 0;
        return history.get(index);
    }

    public Memento redo() {
        int maxIndex = history.size() - 1;
        index = (index < maxIndex) ? index + 1 : maxIndex;
        return history.get(index);
    }
}
```

Ketika user ingin melakukan *undo* pada todo list yang sudah ditambahkan/diubah/dihapus tasknya, user cukup memanggil `Caretaker` untuk mengembalikan todo list ke state sebelumnya dengan `undo()` dan bila ingin kembali ke state berikutnya dengan `redo()`.

Agar dapat mempermudah user dalam berinteraksi dengan todo list tanpa harus coding manual mengenai akses perintah ke todo/task (baik penambahan, pengurangan, update, *undo*, dan *redo*), seluruh object `Caretaker` dan object *originator* `ToDoList` disertakan dalam class `TodoPad` dimana class ini berfungsi sebagai "*the real caretaker*" dimana perintah-perintah yang dijalankan kepada object *originator* akan ditambahkan proses penyimpanan *snapshot/state* ke dalam history.

```java
public class TodoPad {
    private ToDoList todoList;
    private Caretaker caretaker;

    public TodoPad() {
        this(new ToDoList());
    }

    public TodoPad(ToDoList todoList) {
        this.todoList = todoList;
        caretaker = new Caretaker();
        saveState();
    }

    public void add(ToDo todo) {
        todoList.add(todo);
        saveState();
    }

    public ToDo get(int index) {
        return todoList.get(index);
    }

    public String getAll() {
        return todoList.getAll();
    }

    public void setTodoContent(int index, String todoContent) {
        ToDo todo = get(index);
        todo.setContent(todoContent);
        todoList.update(index, todo);
        saveState();
    }

    public void setDone(int index, boolean done) {
        ToDo todo = get(index);
        todo.setDone(done);
        todoList.update(index, todo);
        saveState();
    }

    public void remove(int index) {
        todoList.remove(index);
        saveState();
    }

    public void saveState() {
        caretaker.addMemento(todoList.save());
    }

    public void undo() {
        todoList.restore(caretaker.undo());
    }

    public void redo() {
        todoList.restore(caretaker.redo());
    }
}
```

Ketika user ingin melakukan *undo* pada todo list yang sudah ditambahkan/diubah/dihapus tasknya, user cukup memanggil `Caretaker` untuk mengembalikan todo list ke state sebelumnya dengan `undo()` dan bila ingin kembali ke state berikutnya dengan `redo()`.

```java
TodoPad myTasks = new TodoPad();
myTasks.add(new ToDo("Nulis catatan"));
myTasks.undo();
myTasks.add(new ToDo("Ngoding PHP"));
myTasks.add(new ToDo("Pesan boba"));
myTasks.setDone(1, true);
myTasks.undo();
myTasks.redo();
```

Maka ketika code tersebut dijalankan, maka ketika di-*undo*, maka state sebelumnya/berikutnya tidak terpengaruh dengan keberadaan state todo list sebelumnya:

```
Add task...
( ) Nulis catatan

Undo...
( ) Nulis catatan

Add task...
( ) Nulis catatan
( ) Ngoding PHP

Set finished task...
( ) Nulis catatan
(v) Ngoding PHP
( ) Pesan boba

Undo...
( ) Nulis catatan
(v) Ngoding PHP
( ) Pesan boba

Redo...
( ) Nulis catatan
(v) Ngoding PHP
( ) Pesan boba
```

Agar setiap attribute dalam class tersebut dapat tersimpan sempurna (secara *deep copy*) di `Memento`, kedua model baik `ToDo` dan `ToDoList` sudah disertakan **prototype design pattern** di dalamnya.

```diff
+ public class ToDo implements Cloneable {
    private String content;
    private boolean done;

    public ToDo(String content) {
        this.content = content;
        this.done = false;
    }

    // setter, getter, and actions...

+   @Override
+   public ToDo clone() {
+   	return new ToDo(content, done);
+   }

}
```

```diff
public class ToDoList {
    private ArrayList<ToDo> todoList;

    public ToDoList() {
        this.todoList = new ArrayList<>();
    }

    // constructor and actions...

    public Memento save() {
-       return new Memento(todoList);
+       return new Memento(copyTodoItems());
    }

    public void restore(Memento m) {
        todoList = m.getState();
    }
+
+   @Override
+   public ToDoList clone() {
+   	return new ToDoList(copyTodoItems());
+   }
+
+   private ArrayList<ToDo> copyTodoItems() {
+   	ArrayList<ToDo> copy = new ArrayList<>();
+   	for (ToDo todo : todoList) {
+   		copy.add(todo.clone());
+   	} return copy;
+   }

}
```

Sehingga ketika state tersebut di-*undo* maupun di-*redo*, maka state tersebut dapat berpindah ke state sebelumnya/berikutnya.

```
Add task...
( ) Nulis catatan

Undo...
 No todo entries...

Add task...
( ) Ngoding PHP

Set finished task...
( ) Ngoding PHP
(v) Pesan boba

Undo...
( ) Ngoding PHP
( ) Pesan boba

Redo...
( ) Ngoding PHP
(v) Pesan boba

```

### B. Order

Diambil dari kasus Order dalam [Command](Command) dimana user dapat menempatkan order ke Chef melalui perantara yaitu `Waiter`. Pada contoh kasus Order dengan penerapan memento design pattern, terdapat `OrderList` yang mewakili beberapa order sekaligus yang dipesan oleh user dan dapat dikurangi maupun diproses seluruhnya ke Chef.

Selain itu, terdapat beberapa command yang dapat dilakukan oleh `Waiter` untuk mengatur order yang dipesan oleh user baik penambahan, pengurangan, maupun pemrosesan order ke Chef melalui interface `OrderCommand` dimana command-command tersebut dieksekusi melalui `command.execute()`.
Ketika user ingin membatalkan order, maka waiter tinggal melakukan *undo* terhadap order yang sudah dipesan.

Mula-mula kasus Order terdefinisikan sebagai berikut:

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

    @Override
    public void execute(OrderList orderList) {
        orderList.addOrder(order);
    }
}

public class RemoveOrder implements OrderCommand {
    private Order order;

    public RemoveOrder(Order order) {
        this.order = order;
    }

    @Override
    public void execute(OrderList orderList) {
        orderList.removeOrder(order);
    }
}

public class ExecuteOrder implements OrderCommand {
    @Override
    public void execute(OrderList orderList) {
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
        System.out.println("Add order...");
        execute(new AddOrder(order));
    }
    
    public void removeOrder(Order order) {
        System.out.println("Remove order...");
        execute(new RemoveOrder(order));
    }

    public void executeOrder() {
        System.out.println("Placing order to Chef...");
        execute(new ExecuteOrder());
    }

    public void displayOrderedMenu() {
        System.out.println(orderList.toString());
    }

    private void execute(OrderCommand command) {
        command.execute(orderList);
    }
}
```

Agar `Waiter` dapat membatalkan penambahan, pengurangan, maupun membatalkan penyerahan order ke Chef, maka state dalam `OrderList` harus disertakan beserta state agar order tersebut dapat diralat oleh waiter ketika user berubah pikiran mengenai order yang ia pesan.

Penambahan state dilakukan dengan melakukan class nesting (membuat class baru `Memento` di dalam `OrderList`) dimana dalam class tersebut berisikan state didalamnya yang dapat di-*undo* dan disimpan state oleh `OrderList`.

```diff
public class OrderList {
    private Vector<Order> orderList;

    public OrderList() {
        orderList = new Vector<>();
    }

    public void addOrder(Order order) { ... }

    public void removeOrder(Order order) { ... }

    public void executeOrder() { ... }

    public String toString() { ... }
+
+   private Vector<Order> cloneOrder() {
+       return (Vector<Order>) orderList.clone();
+   }
+
+   public Memento save() {
+       return new Memento(orderList);
+   }
+
+   public void restore(Memento m) {
+       orderList = m.orderState;
+   }
+
+   public static class Memento {
+       private Vector<Order> orderState;
+
+       public Memento(Vector<Order> orderState) {
+           this.orderState = orderState;
+       }
+
+       public Vector<Order> getOrderState() {
+           return orderState;
+       }
+   }

}
```

Kemudian agar state tersebut dapat disimpan dalam history dan dapat di-*undo* dari list, maka class `Caretaker` dibuatkan agar state dari `OrderList` dapat ditampung dalam kumpulan history state (dalam bentuk `Stack<>`).

```java
public class OrderCaretaker {
    private OrderList orderList;
    private Stack<OrderList.Memento> history;

    public OrderCaretaker(OrderList orderList) {
        this.orderList = orderList;
        history = new Stack<>();
    }
    
    public OrderCaretaker() {
        this(new OrderList());
    }
    
    public OrderList getOrderList() {
        return orderList;
    }
    
    public void executeCommand(OrderCommand command) {
        saveState();
        command.execute(orderList);
    }

    public void saveState() {
        history.push(orderList.save());
    }

    public void undo() {
        orderList.restore(history.pop());
    }
}
```

Kemudian modifikasi class `Waiter` dengan menggantikan object `OrderList` yang sudah dipindahkan ke `OrderCaretaker` dan diubah pemanggilan commandnya menjadi `orderCaretaker.executeCommand()`:

```java
public class Waiter {
    private OrderCaretaker handler;

    public Waiter() {
        handler = new OrderCaretaker();
    }

    public void addOrder(Order order) {
        System.out.println("Add order...");
        handler.executeCommand(new AddOrder(order));
    }

    public void removeOrder(Order order) {
        System.out.println("Remove order...");
        handler.executeCommand(new RemoveOrder(order));
    }

    public void executeOrder() {
        System.out.println("Placing order to Chef...");
        handler.executeCommand(new ExecuteOrder());
    }
    
    public void executeBatch(ArrayList<OrderCommand> orderCommands) {
        for (OrderCommand command : orderCommands) {
            handler.executeCommand(command);
        }
    }

    public void undo() {
        System.out.println("Undoing an order action...");
        handler.undo();
    }

    public void displayOrderedMenu() {
        System.out.println(handler.getOrderList().toString());
    }
}
```

Ketika dalam sebuah order user ingin mengubah pikiran dengan membatalkan salah satu ordernya, maka `Waiter` cukup memanggil `undo()` untuk membatalkan command/perintah yang dijalankan olehnya sebelumnya.

```java
Menu nasiPadang = new Menu("Nasi Padang", 17000);
Menu indomie = new Menu("Indomie goreng", 7000);
Menu boba = new Menu("Boba", 27000);

Waiter waiter = new Waiter();
waiter.addOrder(new Order(nasiPadang, 2));
waiter.addOrder(new Order(indomie, 4));
waiter.undo();
waiter.addOrder(new Order(boba, 1));
waiter.executeOrder();
```

Maka output yang diperoleh adalah sebagai berikut:

```
Add order...
 - Nasi Padang (Rp17000 x 2)

Add order...
 - Nasi Padang (Rp17000 x 2)
 - Indomie goreng (Rp7000 x 4)

Undoing an order action...
 - Nasi Padang (Rp17000 x 2)

Add order...
 - Nasi Padang (Rp17000 x 2)
 - Boba (Rp27000 x 1)

Placing order to Chef...
Executing Nasi Padang (x2)...
Executing Boba (x1)...
 >> no available orders!
```

## Catatan Tambahan

Karena **memento** memerlukan resource tambahan untuk menyimpan state, maka pemakaian design pattern ini perlu dipertimbangkan sebaik mungkin apalagi jika diterapkan pada sistem yang mempunyai daya alokasi yang rendah dimana pemakaian memento akan memakan banyak resource jika tidak dibatasi maksimum state yand disimpan dalam aplikasi.

Selain itu, pemakaian **memento** juga mewajibkan object yang dibuatkan snapshot/state harus dalam keadaan *deep-copied* agar keberadaan setiap state yang tersimpan dalam history tidak terpengaruhi oleh perubahan baru sama sekali.

### Honorable Mention: Notepad

![Notepad](../assets/img/behavioral/memento-notepad.png#center "Notepad")

**Notepad** merupakan salah satu aplikasi legendaris yang sudah ada dalam Microsoft Windows sejak Windows 1.0 dimana keberadaan fitur yang ada dalam Windows sendiri tidak mengalami perubahan yang sangat signifikan.

Salah satu masalah yang sering dibahas pada aplikasi Notepad adalah keberadaan **Undo yang hanya bisa kembali ke satu state sebelumnya** dan ketika user ingin melakukan *undo* kembali, maka dokumen yang ter-*undo* kembali ke state terbarunya. Hal ini memang sedikit membuat para text editor sedikit kewalahan apalagi ketika editor harus mengkoreksikan kesalahan dalam file yang dikerjakan di Notepad.

Sebagai gantinya, sebagian user lebih memilih untuk menggunakan text editor lain seperti **Sublime**, **Visual Studio Code**, dan **Notepad++** sebagai text editor yang keberadaan fiturnya lebih lengkap dan lebih interaktif dibanding Notepad yang fiturnya sangat minim dan apa adanya.


## Referensi

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 316-325.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 624-625.
- Refactoring.guru (Memento, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/memento](https://refactoring.guru/design-patterns/memento)
- SourceMaking (Memento, termasuk referensi gambar) - [https://sourcemaking.com/design_patterns/memento](https://sourcemaking.com/design_patterns/memento)
- Gang Of Four (GoF) Design Patterns: Memento - [https://www.journaldev.com/1734/memento-design-pattern-java](https://www.journaldev.com/1734/memento-design-pattern-java)
- Java JDK 7 Docs: Runnable - [https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html](https://docs.oracle.com/javase/7/docs/api/java/lang/Runnable.html)