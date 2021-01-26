# Iterator

[Main Page](..) → [Behavioral Design Patterns](.) → [Iterator](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/iterator) | [refactoring.guru](https://refactoring.guru/design-patterns/iterator) | [sourcemaking.com](https://sourcemaking.com/design_patterns/iterator)

![Iterator](../assets/img/behavioral/iterator.png#center "Iterator")

**Iterator** merupakan design pattern yang berisikan sebuah List dan sebuah pointer yang berisikan index dari List/Array. Ketika Client/user ingin mengambil data, Iterator akan mengambil isi list pada pointer dan kemudian bergerak ke index berikutnya untuk kemudian akan dipanggil oleh user bila diperlukan.

Dengan Iterator, developer cukup menentukan strategi apa yang digunakan untuk mengambil data dari sebuah List/Array tanpa harus menentukan index yang akan diambil (misalnya dengan teknik *Depth-First* atau *Breadth-First* Iteration serta dapat diterapkan pada konsep *Linked-List*).

Iterator mempunyai 3 dasar yang tercantum pada sub-bab di bawah:

## Essences of Iterator

### 1. Index Pointer

Iterator memerlukan pointer untuk mengambil data dalam sebuah list dengan bantuan index. Dengan bantuan index, pointer akan mengambil data dalam list dengan index yang tersimpan sehingga user tidak perlu menghafal/menentukan index berdasarkan size dari sebuah list.

```java
private List<T> list;
private int pointer_index;

public Iterator(List<T> list) {
  this.list = list;
  pointer_index = 0;
}

public boolean hasNext() {
  return pointer_index < list.size();
}

public T next() {
  return hasNext() ? list.get(pointer_index++) : null;
}
```

Untuk mengetahui seberapa jauh iterator mengambil data, terdapat method `hasNext()` yang akan mengetahui apakah iterator sudah mengambil seluruh data dengan mencocokkan index pointer dengan size list/array. Selain itu, ketika data tersebut sudah diambil dengan method `next()`, index pada pointer akan bertambah hingga index tersebut mencapai size list/array.

### 2. List Member Getter *(alias List Iterator)*

Ya **Iterator**, kenapa harus Iterator? Meski kalian tahu bahwa Java mempunyai sebuah interface bernama `Iterator<T>` dalam class `java.util.Iterator<T>`, namun bukan berarti Iterator tidak perlu dipelajari lho! Karena pada dasarnya Iterator adalah sebuah konsep terhadap List dimana pengguna dapat mengambil object dengan cepat tanpa harus melakukan pencarian berulang kali.

```java
public interface Iterator<T> {
  boolean hasNext();
  T next();
  void resetIndex();
}

public class MyIterator implements Iterator<T> {
  private List<T> list;
  private int index;

  public MyIterator(List<T> list) {
    this.list = list;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < list.size();
  }

  @Override
  public T next() {
    return hasNext() ? list.get(index++) : null;
  }

  @Override
  public void resetIndex() {
    index = 0;
  }
}
```

Sebuah Iterator harus mengandung pointer yang akan menyimpan index terakhir dimana data tersebut diakses. Misalnya sebuah List berisikan serangkaian angka yang terhubung satu sama lain, maka ketika User ingin mengambil data dari list tersebut, user tinggal memanggil Iterator  untuk mengambil data dengan cepat dan mudah.

Terdapat 3 method utama yang digunakan dalam Iterator yaitu:

1. `boolean hasNext()`: Mengetahui seberapa banyak data tersisa untuk diambil. Method ini akan mengecek apakah pointer index pada Iterator lebih kecil dibanding size dari list/array itu sendiri.
2. `T next()`: Mengambil data pada index yang tersimpan dalam pointer Iterator dengan mengecek apakah index dari pointer iterator sendiri masih lebih kecil dibanding size dari list/array dengan bantuan method `hasNext()`.
3. `void resetIndex()` *(opsional)*: Mengulang iterasi dari awal dengan melakukan reset index pada pointer Iterator ke 0.

Selain itu, dengan Iterator, user juga tidak perlu mengetahui proses/algoritma yang diterapkan dalam mengambil data-data dalam list sama sekali (termasuk mengetahui index data yang diambil).

### 3. List Collection (dengan implementasi IterableList)

Meski setiap bahasa pemrograman dibekali dengan List/Array bawaan, perlu kalian ketahui bahwa dalam skeneario tertentu akan ada kejadian dimana user ingin mengambil dan merekap semua data-data dalam sebuah list tanpa harus melakukan code manual untuk mengambil data (misal Karyawan ingin melakukan rekap sebagian/seluruh order sekaligus).

```java
public interface Iterable<T> {
  Iterator<T> createIterator();
}

public interface IterableList<T> extends Iterable<T> {
  boolean add(T element);
  boolean remove(T element);
  int size();
}

/**
 * An Iterable List consists of ArrayList.
 * The <T> must be inserted while you want to use it to prevent errors due
 * of unfilled/not converted Generic Type as an Class Type.
 */
public class MyIterableList implements IterableList<T> {
  private ArrayList<T> myList;

  public MyIterableList() {
    myList = new ArrayList<>();
  }

  @Override // from Iterable<T>
  public Iterator<T> createIterator() {
    return new MyIterator(myList);
  }

  @Override
  public boolean add(T element) {
    return myList.add(element);
  }

  @Override
  public boolean remove(T element) {
    return myList.remove(element);
  }
  
  @Override
  public int size() {
    return myList.size();
  }
}
```

Dalam sebuah List Collection terdapat 1 method yang berisikan deklarasi Iterator yang diimplementasikan dari sebuah interface bernama `Iterable<T>` yaitu `createIterator()` dimana method tersebut akan memanggil Iterator ketika user memerlukan iterasi data dari list yang dibutuhkan olehnya (misal dengan `foreach`).


## Contoh kasus: Iterating whole members in List

Dalam sebuah contoh kasus, terdapat 2 cara untuk mengambil data *(member)* dalam sebuah list yaitu iterasi dengan index dan iterasi dengan bantuan iterasi. Keduanya sama-sama berperan untuk mengambil data dari sebuah list, namun dengan cara yang berbeda.

Cara pertama, dengan bantuan index, menggunakan konsep `for` dengan tambahan variabel index yang disesuaikan dengan size array/list. Contoh modelnya sebagai berikut:

```java
Product[] wishlist = user.getWishlist();
for (int i = 0; i < arr.length; i++) {
  Product o = wishlist[i];
  // do something
}
```

Dan cara kedua adalah dengan bantuan Iterator dimana data akan di-iterasikan (ambil list/array data) dengan bantuan pointer index yang tertanam dalam Iterator.

```java
// Wishlist contains Product[]
Wishlist wishlist = user.getWishlist();
// ...
Iterator<Product> wishIterator = wishlist.createIterator();
while (wishIterator.hasNext()) {
  String wishItem = wishIterator.next();
  // execute within iterator (for-each)
  System.out.println(wishItem);
}
```

Ketika user ingin mengambil data dengan bantuan Iterator, user hanya mengetahui seberapa banyak data yang tersisa/masih perlu diambil hingga semua data teriterasikan dengan method `boolean` yaitu `hasNext()` dimana ketika index pointer dalam Iterator sudah melebihi/menyamai size pada array/list tidak akan mengambil data pada list tersebut lagi (alias mengembalikan nilai `null` pada method yang diberikan).

Namun akan berbeda kasusnya apabila dalam kasus berikut mengharuskan user untuk mengambil seluruh isi dalam wishlist (termasuk yang dikelompokkan dalam kategori-kategorinya).

Dalam contoh kasus berikut, akan ada 2 contoh skeneario mengenai bagaimana cara kerja Iterator sebagai berikut:

### a. Iterator untuk List/Array tunggal

Pada kasus List/Array tunggal, Iterator lebih mudah diaplikasikan karena cukup mengambil data-data langsung dengan bantuan pointer index sehingga user tidak perlu melihat isi dan implementasi dari array/list yang terenkapsulasi dalam sebuah list.

Dalam sebuah contoh kasus e-commerce, terdapat sebuah fitur yang memperbolehkan user untuk menyimpan produk-produk yang ia gemari tanpa harus membeli produknya. Kira-kira isi implementasi produknya terlampir sebagai berikut:

```java
public class Product {
  private String name, description;
  private int price;

  public Product(String name, String description, int price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

  public void setName(String name) {
    this.name = name;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public void setPrice(int price) {
    this.price = price;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public int getPrice() {
    return price;
  }
}
```

Ketika user ingin menyimpan produk-produk tersebut ke dalam wishlist, tentunya disediakan class `WishList` yang akan menampung semua barang-barang yang difavoritkan user dimana barang-barang tersebut bisa saja ia inginkan di lain waktu.

Untuk mengimplementasikan wishlist, developer ingin proses pengambilan semua produknya dilakukan apa adanya tanpa membiarkan user tahu proses pengambilan data apalagi dengan ukuran wishlist yang tidak terkira-kirakan oleh user apabila ia menyimpan sangat banyak data di dalamnya. User pada intinya hanya ingin ia dapat menambahkan, menghapus, maupun mengetahui jumlah barang yang ia favoritkan.

Maka developer memutuskan untuk menambahkan Iterator dengan menggunakan interface `Iterator<T>` buatannya serta custom list yang ia buat sendiri agar customer hanya perlu menambahkan, mengurangi, mengetahui jumlah barang yang ia favoritkan, serta mengambil semua barang yang ia favoritkan dengan bantuan Iterator.

Pada implementasi code berikut, ia mendefinisikan Iterator sebagai berikut:

```java
public interface Iterator<T> {
  boolean hasNext();
  T next();
  void resetIndex();
}

public class ProductIterator implements Iterator<Product> {
  private List<Product> list;
  private int index;

  public ProductIterator(List<Product> list) {
    this.list = list;
    index = 0;
  }

  @Override
  public boolean hasNext() {
    return index < list.size();
  }

  @Override
  public Product next() {
    return hasNext() ? list.get(index++) : null;
  }

  @Override
  public void resetIndex() {
    index = 0;
  }
}
```

Kemudian pada wishlist terdefinisikan sebagai berikut:

```java
public interface Iterable<T> {
  Iterator<T> createIterator();
}

public interface IterableList<T> extends Iterable<T> {
  boolean add(T element);
  boolean remove(T element);
  int size();
}

public class WishList implements IterableList<Product> {
  private Vector<Product> products;

  public WishList() {
    products = new Vector<Product>();
  }

  @Override
  public Iterator<Product> createIterator() {
    return new ProductIterator(products);
  }

  @Override
  public boolean add(Product element) {
    return products.add(element);
  }

  @Override
  public boolean remove(Product element) {
    return products.remove(element);
  }

  @Override
  public int size() {
    return products.size();
  }
}
```

Maka kita cukup mengimplementasikan Iterator untuk mengambil semua data-data dari wishlist ketika User ingin mengambil semua produknya dari wishlist dengan code sebagai berikut:

```java
Wishlist myWishlist = customer.getWishlist();
Iterator<Product> iterator = myWishlist.createIterator();
while (iterator.hasNext()) {
  Product p = iterator.next();
  // do something
}
```

Atau sebagai penggantinya (dalam Java 8, namun dengan sedikit modifikasi), ketika class tersebut ingin dibuatkan agar dapat di-iterasikan dengan `foreach`, developer cukup mengubah implementasi code dengan Iterator bawaan Java dan menambahkan `Iterable<T>` pada interface `IterableList<T>` sehingga sebuah custom list yang dibuatkan olehnya bisa diimplementasikan lebih singkat sebagai berikut:

```java
Wishlist myWishlist = customer.getWishlist();
for (Product p : myWishlist) {
  // do something
}
```


### b. Iterator untuk *Nested List/Array*

Lain kasusnya apabila developer ingin memutar otak agar user dapat menyimpan produk-produknya ke dalam kategori. Semua produk-produk yang user simpan dapat ditempatkan ke kategori yang user inginkan sehingga user dapat mengakses barang-barang dari kategori tertentu pada wishlist.

Ketika user memerlukan data-data dalam kategori dalam wishlist, penggunaan Iterator tidaklah cukup untuk digunakan dalam *nested list/array* seperti pada contoh code di bawah:

```java
public class CategorizedWishList implements IterableList<Product> {
  private Vector<Vector<Product>> productSets;

  public CategorizedWishList() {
    this.productSets = new Vector<Vector<Product>>();
  }

  public CategorizedWishList(Vector<Product> products) {
    this();
    this.productSets.add(products);
  }

  @Override
  public Iterator<Product> createIterator() {
    return new WishlistIterator(productSets);
  }

  /**
   * Get all products by iterating through categories and return as list
   * of products wishlisted by user without needs of category.
   */
  public Vector<Product> getAll() {
    Vector<Product> vec = new Vector<>();
    // Using default Iterator applied to Vector<> (inherited from List<>)
    for(Vector<Product> category : productSets) {
      vec.addAll(category);
    }
    return vec;
  }

  /**
   * In the example documentation, they added the default and uncategorized
   * item Vector to store inserted products into first index of nested Vector.
   */
  public Vector<Product> getUncategorizedProducts() {
    if (productSets.isEmpty()) {
      productSets.add(new Vector<Product>());
    } return productSets.get(0);
  }

  /**
   * Add product to first index of nested Vector (uncategorized)
   */
  @Override
  public boolean add(Product element) {
    return getUncategorizedProducts().add(element);
  }

  /**
   * Remove product from nested Vector by iterating through all lists.
   */
  @Override
  public boolean remove(Product element) {
    for (Vector<Product> category : productSets) {
      if (category.remove(element)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Display number of products NOT number of category in nested vector
   */
  @Override
  public int size() {
    int totalItems = 0;
    for (Vector<Product> category : productSets) {
      totalItems += category.size();
    }
    return totalItems;
  }
}
```

Menggunakan Iterator tunggal tidaklah cukup bagi class `Wishlist` karena Iterator hanya bisa mengambil isi-isi dalam sebuah list, apalagi dalam class `Wishlist` sendiri menggunakan *Nested List* yang dimana dalam class tersebut ia hanya mau mengambil produk-produknya tanpa melihat kategorinya (dalam implementasinya tercantum `implements IterableList<Product>` bukan `implements IterableList<Vector<Product>>`).

Agak gregetan bagi seorang developer apabila menggunakan Iterator bawaan Java (`java,util.Iterator`) karena penggunaan Iterator bawaan Java hanya mampu mengiterasikan satu level dalam list tersebut sedangkan developer harus melakukan code tambahan lagi untuk iterasi tambahan di dalamnya.

Sebagai solusinya, kita harus membuatkan class custom yang diimplementasi dari interface `Iterator<T>` yang sudah dibuatkan sebelumnya dengan menambahkan implementasi custom untuk mengiterasikan seluruh member dalam *nested list* tanpa melakukan code tambahan pada Client class sebagai berikut:

```java
public class WishlistIterator implements Iterator<Product> {
  private List<List<Product>> nestedList;
  private int allIndex, outerIndex, innerIndex;
  private List<Product> innerList;

  public WishlistIterator(List<List<Product>> nestedList) {
    this.nestedList = nestedList;
    allIndex = 0;
    innerIndex = 0;
    outerIndex = 0;
  }

  @Override
  public boolean hasNext() {
    return allIndex < getWholeListSize();
  }

  private boolean hasNext(List<Product> innerList) {
    return innerIndex < innerList.size();
  }

  private boolean hasNextList() {
    return outerIndex < nestedList.size();
  }

  @Override
  public Product next() {
    if (hasNext(innerList)) {
      return innerList.get(innerIndex);
    } else if (hasNextList()) {
      innerList = nestedList.get(outerIndex++);
      return next();
    }
    return null;
  }

  @Override
  public void resetIndex() {
    allIndex = innerIndex = outerIndex = 0;
  }

  private int getWholeListSize() {
    int totalSize = 0;
    for (List<Product> innerList : nestedList) {
      totalSize += innerList.size();
    }
    return totalSize;
  }
}
```


## Catatan

Perlu kalian ketahui bahwa dalam beberapa bahasa pemrograman telah dibekali dengan `foreach` dimana dalam sebuah block iterasi berisikan deklarasi object yang diambil dengan teknik iterasi (iterator) dimana user tidak perlu mengetahui jumlah dan index yang diperoleh dengan mengambil data langsung dengan bantuan pointer index.

Dalam contoh kasus di Java, ketika user ingin menampilkan data dalam code dengan cara konvensional (Pre-Java 8), user harus membuatkan variabel yang berisikan index yang diiterasikan dalam `for` block di bawah:

```java
ArrayList<String> wishlist = new ArrayList<>();
for (int i = 0; i < wishlist.size(); i++) {
  System.out.println(wishlist.get(i));
}
```

Di Java 8, semua list-list yang mengimplementasikan interface `java.util.Iterator<T>` dapat melakukan operasi `foreach` dengan cepat dan mudah tanpa mengetahui isi implementasi di dalamnya dengan cukup memanggil object dalam list yang *Iterable* sehingga user cukup mengambil object yang ada dari Iterator tanpa harus memanggil list object (apalagi dengan index) yang ada didalamnya.

```java
ArrayList<String> wishlist = new ArrayList<>();
// ...
for (String wishItem : wishlist) {
  // execute within foreach
  System.out.println(wishItem);
}
```

Sedikit bocoran dimana dalam `foreach` terdapat persamaan syntax dengan bantuan implementasi Iterator yang akan mengiterasikan object-object di dalamnya sebagai berikut:

```java
ArrayList<String> wishlist = new ArrayList<>();
// ...
Iterator<String> wishIterator = wishlist.createIterator();
while (wishIterator.hasNext()) {
  String wishItem = wishIterator.next();
  // execute within iterator (for-each)
  System.out.println(wishItem);
}
```

## Common Misconceptions

Terkadang sebagian orang salah mengartikan dan menerapkan Iterator sebagai cara otomatis developer untuk mengiterasikan data (apalagi mengambil data tanpa sepengetahuan user) dimana developer menerapkan teknik iterasi langsung ke dalam List seperti pada contoh di bawah:

```java
public interface IteratedList<T> {
  Iterator<T> getIterator();
  T get();
  List<T> getAll();
}

public class CustomIteratedList extends IteratedList<String> {
  private ArrayList<String> myList;
  private Iterator<String> iterator;

  @Override
  public Iterator<String> getIterator() {
    if (iterator == null) {
      iterator = new UserIterator(users);
    }
    return iterator;
  }

  @Override
  public String get() {
    return getIterator().get();
  }

  @Override
  public List<String> getAll() {
    return myList;
  }
}
```

Mungkin sebagian orang (termasuk saya, teman saya, aslab, dan dosen) menilai bahwa teknik Iterasi yang dilakukan pada contoh kasus soal ini benar, padahal... **SALAH!!!**

Lho kok salah? Jawabannya karena Iterator harus terpisah operasinya dari List (apapun wujudnya) karena ketika digabung akan memperoleh data yang sangat tidak dapat diprediksi oleh siapapun (termasuk developer sendiri!) :hushed:.

Alasan lain dari kesalahan yang termaksud dalam code tersebut adalah apabila data tersebut mengalami **modifikasi _(senggolan)_ sekecil apapun terhadap list** akan mempengaruhi Iterator yang sedang berjalan karena akan memperoleh data yang tidak akurat sama sekali bahkan menghasilkan data yang tidak akurat!

Dalam kasus Iterator bawaan Java, perubahan, modifikasi (termasuk senggolan) sekecil apapun terhadap list akan menimbulkan `ConcurrentModificationException` karena adanya senggolan data ketika Iterator sedang berjalan *(hint: peristiwa tersebut dapat disebut sebagai **Concurrency**)[^1]*.

## References

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 289-304.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 327-352.
- Refactoring.guru (Iterator) - [https://refactoring.guru/design-patterns/iterator](https://refactoring.guru/design-patterns/iterator)
- SourceMaking (Iterator) - [https://sourcemaking.com/design_patterns/iterator](https://sourcemaking.com/design_patterns/iterator)
- Gang Of Four (GoF) Design Patterns: Iterator - [https://www.journaldev.com/1716/iterator-design-pattern-java](https://www.journaldev.com/1716/iterator-design-pattern-java)
- Java JDK 8 Docs: Iterator - [https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html](https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html)
- Carnegie Mellon University. Confluence: "MSC06-J. Do not modify the underlying collection when an iteration is in progress" - [https://wiki.sei.cmu.edu/confluence/display/java/MSC06-J.+Do+not+modify+the+underlying+collection+when+an+iteration+is+in+progress](https://wiki.sei.cmu.edu/confluence/display/java/MSC06-J.+Do+not+modify+the+underlying+collection+when+an+iteration+is+in+progress)
- Jakob Jenkov (2020-05-22): Java Iterator - [http://tutorials.jenkov.com/java-collections/iterator.html](http://tutorials.jenkov.com/java-collections/iterator.html)

## Footnotes

Mengenai definisi maupun penjelasan catatan kaki terlampir di bawah:

[^1]: **Concurrency** adalah skeneario dimana 2 atau lebih proses dijalankan bersamaan terhadap 1 data sekaligus.
    Dalam kasus database dan threading, concurrency harus ditangani sebaik mungkin untuk mencegah adanya ketidakakuratan data yang diperoleh dari > 2 proses yang berjalan bersamaan.