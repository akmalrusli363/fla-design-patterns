# Composite

[Main Page](..) → [Structural Design Patterns](.) → [Composite](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/structural/composite) | [refactoring.guru](https://refactoring.guru/design-patterns/composite) | [sourcemaking.com](https://sourcemaking.com/design_patterns/composite)

![Composite](../assets/img/structural/composite.png#center "Composite")

**Composite** merupakan design pattern dimana komponen-komponen dari sebuah hierarki memiliki hubungan *has-a* ke object lain (mempunyai attribute ke object lain) yang dapat bersifat rekursif. Sebuah hubungan rekursif yang dimiliki sebuah object bisa saja terhubung pada class-class dalam hierarki yang sama yang bersifat kolektif hingga ujung node (alias leaf) yang tidak beranak.

**Composite** sangat memungkinkan sebuah object untuk mempunyai banyak object yang bercabang-cabang di dalamnya sebagai sebuah *tree*.

Secara konseptual, tree mengandung *node leaf* dan *branch* yang berisikan node-node dan branch-branch di dalamnya. Nantinya sebuah composite dapat diakses *(transverse)* melalui *DFS (depth first search)* dan *BFS (breadth first search)*.

Bagi sebagian orang, Composite bisa saja disamakan dengan *Decorator*, namun bedanya ada pada jumlah anak dalam hubungan rekursif yang banyak, namun tidak secara eksplisit mengandung implementasi dari sebuah object yang rekursif di dalamnya.

## Analogi

![Composite = Tree](../assets/img/structural/composite-tree.gif#center "Composite = Tree")

**Composite** merupakan design pattern yang konsepnya terinspirasi dari *Tree* dimana sebuah tree mengandung *leaf* dan *branch* yang diwakili oleh node-node dalam sebuah *leaf* maupun *branch*. Sebuah *leaf* merupakan node-node yang tidak mempunyai cabang/anak sedangkan *branch* merupakan node yang mempunyai cabang/anak di dalamnya.

Contoh konsep yang mudah dianalogikan pada Composite adalah sistem *folder* dan *file*, dimana sebuah folder *(parent)* dapat berisikan file (*leaf*) dan folder (*parent*) yang bercabang-cabang dan berisikan folder dalam folder hingga folder tersebut hanya berisikan file atau kosong sama sekali.


## UML Model

![Composite Model](../assets/img/structural/composite-model.png#center "Composite Model")

## Essences of Composite

### 1. Base Class (Component)

Sebuah composite/tree harus mengandung komponen node yang berisikan deklarasi dasar dari sebuah hierarki yang akan digunakan oleh node-node baik *leaf* (tanpa cabang) dan *branch* (mengandung cabang yang berisikan node-node didalamnya). Dalam interface/base class sendiri terdapat beberapa method yang akan digunakan langsung oleh *leaf* maupun *composite* sesuai kebutuhan hierarki.

```java
public interface Component {
  void execute();
}
```

### 2. Leaf (Item)

**Leaf** dalam Composite design pattern merujuk pada class yang tidak mengandung object dari hierarki yang sama dan dapat disamakan sebagai *leaf node* alias ujung dari hirarki objek. Sebuah *leaf* hanya merupakan keturunan dari *base class* yang berisikan implementasi-implementasi buatan class mereka sendiri.

```java
public class Item implements Component {
  private String name;

  public Item(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public void execute() {
    System.out.println("Item: " + name);
  }
}
```

### 3. Composite (Aggregate/Container)

**Composite** (alias *Container/Aggregate*) merupakan class dimana sebuah object yang berisikan object-object dari hierarki yang sama di dalamnya. Dalam Composite, terdapat *container/aggregate* yang menampung beberapa object sekaligus ke dalam *array/list* dimana object-object yang ditampung merupakan anak dari object berupa *Leaf* maupun *Composite* itu sendiri.

Nantinya ketika sebuah method dari *base class/interface* tersebut dipanggilkan pada Composite, maka Composite akan mendelegasikan eksekusi method tersebut kepada anak-anaknya.

```java
// Component used in the class is NOT from java.awt.Component
public class Package implements Component {
  private Vector<Component> components;
  private String name;

  public Package(String name) {
    components = new Vector<>();
    this.name = name;
  }

  public void add(Component item) {
    components.add(item);
  }
  public void remove(Component item) {
    components.remove(item);
  }
  public List<Component> getChildren() {
    return components;
  }
  public int size() {
    components.size();
  }

  @Override
  public void execute() {
    for (Component component : components) {
      component.execute();
    }
  }
}
```

## Contoh Kasus

![Composite example: File System](../assets/img/structural/composite-file-system.gif#center "Composite example: File System")

Salah satu contoh konsep yang paling umum digunakan dalam Operating System adalah File System dimana dalam sebuah storage terdapat banyak file dan folder yang dikumpulkan dalam satu *root directory*. Setiap folder berisikan beberapa file dan folder di dalamnya, juga bisa berupa folder kosong dimana folder tersebut tidak berisikan apapun didalamnya.

Untuk deklarasi dasar, disajikan base class berupa `Component` sebagai berikut:

```java
public abstract class Component {
  protected String name;

  public Component(String name) {
    this.name = name;
  }

  public abstract void open();
  public abstract void rename(String name);

  public final void print() {
    printUsingIndentation(0);
  }

  protected abstract void printUsingIndentation(int indentation);
  protected void printIndent(int n) {
    for(int i = 0; i < n; i++) {
      System.out.print(" ");
    }
  }
}
```

Kemudian pada file dibuatkan sebagai *leaf node* dimana sebuah file hanya berisikan attribute dan behavior dari base class `Component` tanpa adanya hubungan *has-a* dengan object-object sehierarki.

```java
public class File extends Component {
  public File(String name) {
    super(name);
  }

  @Override
  public void open() {
    System.out.println("opening file...");
  }

  @Override
  public void rename(String name) {
    this.name = name;
    System.out.println("renaming file...");
  }

  @Override
  protected void printUsingIndentation(int indentation) {
    printIndent(indentation);
    System.out.println(name);
  }
}
```

Dan folder yang dibuatkan sebagai *branch node* yang berisikan file dan folder yang berbasis dari hierarki yang sama, serta memiliki attribute dan behavior dari base class `Component` dengan tambahan function/method berupa `add(Component)` dan `remove(Component)` untuk menunjang penambahan & pengurangan komponen-komponen yang akan dimasukkan ke dalam *Composite/Container*.

```java
public class Folder extends Component {
  private Vector<Component> components;

  public Folder(String name) {
    super(name);
    components = new Vector<Component>();
  }

  public void add(Component c) {
    components.add(c);
  }
  
  public void remove(Component c) {
    components.remove(c);
  }

  @Override
  public void open() {
    System.out.println("opening folder...");
  }
  
  @Override
  public void rename(String name) {
    this.name = name;
    System.out.println("renaming folder...");
  }
  
  @Override
  protected void printUsingIndentation(int indentation) {
    printIndent(indentation);
    System.out.println(name);

    for(Component c : components) {
      c.printUsingIndentation(indentation + 2);
    }
  }
}
```

Misalkan kita ingin membuat struktur folder sebagai berikut:

- GoF
  - creational
    - prototype.txt
    - singleton.txt
  - structural
    - adapter.txt
    - composite.txt
    - decorator.txt
  - behavioral
    - observer.txt
    - strategy.txt
  - cover.jpg

Maka di client class, kita bisa mendeklarasikan isi-isi dari folder dan file dari struktur folder sebagai berikut:

```java
Folder root = new Folder("GoF");
Folder folder1 = new Folder("creational");
Folder folder2 = new Folder("structural");
Folder folder3 = new Folder("behavioral");

root.add(folder1);
root.add(folder2);
root.add(folder3);

folder1.add(new File("prototype.txt"));
folder1.add(new File("singleton.txt"));

folder2.add(new File("adapter.txt"));
folder2.add(new File("composite.txt"));
folder2.add(new File("decorator.txt"));

folder3.add(new File("observer.txt"));
folder3.add(new File("strategy.txt"));

root.add(new File("cover.jpg"));

root.print();
```

Nantinya ketika user ingin melihat isi struktur dari folder yang ada, cukup panggil `print()` dari folder yang sudah kita buat (misalnya `root`) untuk ditampilkan output file tree sebagai berikut:

```
GoF
  creational
    prototype.txt
    singleton.txt
  structural
    adapter.txt
    composite.txt
    decorator.txt
  behavioral
    observer.txt
    strategy.txt
  cover.jpg
```


## References

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 183-195.
- Refactoring.guru (Composite) - [https://refactoring.guru/design-patterns/composite](https://refactoring.guru/design-patterns/composite)
- SourceMaking (Composite, termasuk referensi gambar) - [https://sourcemaking.com/design_patterns/composite](https://sourcemaking.com/design_patterns/composite)
- Gang Of Four (GoF) Design Patterns: Composite - [https://www.journaldev.com/1535/composite-design-pattern-in-java](https://www.journaldev.com/1535/composite-design-pattern-in-java)
- IBM z/OS UNIX file systems (hierarchial file system structure ilustration) - [https://www.ibm.com/support/knowledgecenter/zosbasics/com.ibm.zos.zconcepts/zconcepts_177.htm](https://www.ibm.com/support/knowledgecenter/zosbasics/com.ibm.zos.zconcepts/zconcepts_177.htm)