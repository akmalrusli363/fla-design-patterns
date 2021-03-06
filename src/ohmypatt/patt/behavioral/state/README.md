# State

[refactoring.guru](https://refactoring.guru/design-patterns/state) | [sourcemaking.com](https://sourcemaking.com/design_patterns/state)

![State](state.png "State")

**State** adalah design pattern yang terdiri dari beberapa state dimana setiap state mewakili aksi *(action)* dan tanggung jawab yang berbeda-beda yang dapat dijalankan oleh sebuah *State Machine*/Context. Sebuah state dapat berubah status bergantung pada apa yang dipanggil oleh Context menjadi state lain tanpa harus dipanggil secara eksplisit oleh Context yang menggunakan state tersebut.

Dengan State design pattern, ketika object tersebut mengalami perubahan state, maka behavior yang dijalankan pada object tersebut ikut berubah bergantung pada state yang digunakan pada object tersebut.

## Analogi Dasar: Finite State Automata

![State Automata](state-automata.png "State Automata")

Mendasari apa yang telah dipelajari dalam ilmu Computer Science, Finite State Automata adalah sebuah graph yang merepresentasikan perubahan state apabila terjadi input yang diterima oleh sebuah state. Adapun interaksi/perlakuan terhadap state dari sebuah machine mengakibatkan state tersebut berganti ke state lain yang telah ditentukan, sehingga state berikut yang dihasilkan adalah berupa state yang terpilih oleh input dari state sebelumnya.


## Essences of State

### 1. State Interface

```java
interface CreditState {
  void topup(int amount);
  void check(int amount);
  void pay();
  void withdraw(int amount);
}
```

**State interface** merupakan interface yang mewakili state yang berisikan *action-action* yang akan dijalankan oleh state, dimana setiap method berperan sebagai action yang akan dijalankan oleh state. Nantinya setiap state akan diimplementasikan dari state interface ini.

### 2. State class

**State class** merepresentasikan sebuah state yang mengimplementasikan *state interface* dimana dalam state class berisikan beberapa action-action yang dijalankan sesuai state yang dituju.

Misalnya terdapat 3 state yang diimplementasikan dari `CreditState`, yaitu `FullCredit`, `HasCredit`, dan `NoCredit`. Masing-masing state memiliki behavior masing-masing yang tertuang dalam contoh code di bawah:

```java
public class FullCredit implements CreditState {
  private CreditMachine machine;

  public FullCredit(CreditMachine machine) {
    this.machine = machine
  }
  
  @Override
  public void topup(int amount) {
    // unable to topup
  }
  @Override
  public void check() {
    // return current credit
  }
  @Override
  public void pay(int amount) {
    // pay for n credit
  }
  @Override
  public void withdraw(int amount) {
    // withdraw for n credit
  }
}

public class HasCredit implements CreditState {
  private CreditMachine machine;

  public HasCredit(CreditMachine machine) {
    this.machine = machine
  }

  @Override
  public void topup(int amount) {
    // add n credit
  }
  @Override
  public void check() {
    // return current credit
  }
  @Override
  public void pay(int amount) {
    // pay for n credit
  }
  @Override
  public void withdraw(int amount) {
    // withdraw for n credit
  }
}

public class NoCredit implements CreditState {
  private CreditMachine machine;

  public NoCredit(CreditMachine machine) {
    this.machine = machine
  }

  @Override
  public void topup(int amount) {
    // add n credit
  }
  @Override
  public void check() {
    // return current credit
  }
  @Override
  public void pay(int amount) {
    // unable to pay, not enough credit
  }
  @Override
  public void withdraw(int amount) {
    // unable to withdraw, not enough credit
  }
}
```

Maka ketika context/*state machine* menjalankan sebuah action, maka action tersebut kemudian didelegasikan pada state yang ada pada context/*state machine* tersebut.

### 3. State Machine (Context)

```java
public class CreditMachine {
  public static final CreditState noCredit, hasCredit, fullCredit;

  private CreditState creditState;
  private int balance;

  public CreditMachine(int balance) {
    this.balance = balance;
    this.noCredit = new NoCredit(this);
    this.hasCredit = new HasCredit(this);
    this.fullCredit = new FullCredit(this);
    this.creditState = checkState(this.balance);
  }

  private CreditState checkState(int balance) {
    // check balance & set state
  }
  
  public void topup(int amount) {
    // DELEGATE to state - add n credit
  }
  public void check() {
    // DELEGATE to state - return current credit
  }
  public void pay(int amount) {
    // DELEGATE to state - pay for n credit
  }
  public void withdraw(int amount) {
    // DELEGATE to state - withdraw for credit
  }
}
```

**_State Machine_** atau context merupakan pengendali state dimana state akan dikendalikan oleh sebuah class yang berisikan state-state yang tersimpan dalam *constant object variable* dan state penunjuk *(state pointer)* yang merepresentasikan state yang ditunjuk oleh context saat ini.

Semua action yang didefinisikan dalam *state machine* nantinya akan didelegasikan ke state sesuai kondisi state pada *state machine*.


## Contoh Kasus

![State Example: ATM](state-atm.png "State Example: ATM")

Pada contoh kasus berikut, sebuah mesin ATM memiliki 4 state, yaitu:

1. No card/tanpa kartu
2. Has card/kartu sudah dimasukkan
3. Has PIN inputted/sudah memasukkan PIN dengan baik
4. No money/ATM kehabisan uang.

Untuk mengimplementasikan state ATM tersebut ke dalam code, diperlukan interface yang berisikan informasi, attribute, dan method-method yang dijalankan ketika object tersebut sedang berada pada state tertentu (misalnya ATM sedang dalam keadaan *idle*/tanpa kartu).

Sebuah state interface/abstract class harus mengandung method-method yang berisikan behavior sesuai state serta harus ada pergantian state pada minimal 1 method yang kita implementasikan.

Pada interface yang berisikan method-method yang akan diimplementasikan oleh class-class sebagai perwakilan state dimana pada contoh kasus berikut berisikan implementasi code sebagai berikut:

```java
public interface ATMState {
  public void insertCard();
  public void ejectCard();
  public void insertPin(int pin);
  public void withdrawCash(int amountOfCash);
}
```

Kemudian pada masing-masing state berisi implementasi-implementasi yang sifatnya berbeda pada kondisi tertentu dimana dalam setiap state terdapat 1 atau lebih method yang akan mengubah state ke state lain. Code-code dari keempat state terlampirkan sebagai berikut:

#### No Card

```java
public class NoCard implements ATMState {
  private ATMMachine atm;
  
  public NoCard(ATMMachine atm) {
    this.atm = atm;
  }

  @Override
  public void insertCard() {
    System.out.println("Card inserted!");
    atm.setCurrentState(atm.getHasCardState());
  }

  @Override
  public void ejectCard() {
    System.out.println("There is no card inside this machine!");
  }

  @Override
  public void insertPin(int pin) {
    System.out.println("Please insert your ATM card first!");
  }

  @Override
  public void withdrawCash(int amountOfCash) {
    System.out.println("There is no card inside this machine!");
  }
}
```

#### Has Card (ada kartu namun user belum memasukkan PIN)

```java
public class HasCard implements ATMState {
  private ATMMachine atm;

  public HasCard(ATMMachine atm) {
    this.atm = atm;
  }

  @Override
  public void insertCard() {
    System.out.println("Card is already inserted!");
  }

  @Override
  public void ejectCard() {
    System.out.println("Card ejected!");
    atm.setCurrentState(atm.getNoCardState());
  }

  @Override
  public void insertPin(int pin) {
    if (Integer.toString(pin).length() == 6) {
      System.out.println("PIN Valid!");
      atm.setCurrentState(atm.getHasPinState());
    } else {
      System.out.println("Invalid PIN!");            
    }
  }

  @Override
  public void withdrawCash(int amountOfCash) {
    System.out.println("Please input your PIN first!");
  }
}
```

#### Has PIN inserted (ada kartu dan user menginput PIN dengan benar)

```java
public class HasPin implements ATMState {
  private ATMMachine atm;

  public HasPin(ATMMachine atm) {
    this.atm = atm;
  }

  @Override
  public void insertCard() {
    System.out.println("Card is already inserted!");
  }

  @Override
  public void ejectCard() {
    System.out.println("Card ejected!");
    atm.setCurrentState(atm.getNoCardState());
  }

  @Override
  public void insertPin(int pin) {
    System.out.println("You've already inputted PIN!");
  }

  @Override
  public void withdrawCash(int amountOfCash) {
    if (amountOfCash < 10000) {
      System.out.println("Please input your withdraw amount at least Rp10000!");
    } else if (amountOfCash > atm.getMoney()) {
      System.out.println("Insufficient ATM amount to withdraw your cash!");
    } else {
      int remaining = atm.getMoney() - amountOfCash;
      
      atm.setMoney(remaining);
      
      System.out.println("Withdrawn Rp" + amountOfCash + " from card!");
      System.out.println("Remaining: Rp" + remaining);
      
      ejectCard();;
      
      if (remaining <= 100000) {
        atm.setCurrentState(atm.getNoCashState());
        System.out.println("ATM is in maintenance!");
      }
    }
  }
}
```

#### No Cash (Uang dalam ATM kosong)

```java
public class NoCash implements ATMState {
  private ATMMachine atm;

  public NoCash(ATMMachine atm) {
    this.atm = atm;
  }

  @Override
  public void insertCard() {
    System.out.println("Insufficient ATM Money, unable to perform transaction!");
  }

  @Override
  public void ejectCard() {
    System.out.println("Card ejected!");
    atm.setCurrentState(atm.getNoCardState());
  }

  @Override
  public void insertPin(int pin) {
    System.out.println("You've already inputted PIN!");
  }

  @Override
  public void withdrawCash(int amountOfCash) {
    System.out.println("Insufficient ATM amount to withdraw your cash!");
  }
}
```

Nantinya keempat state tersebut akan disertakan ke dalam context *(state machine)* yang akan menentukan role dari ATM baik perubahan state, reaksi ketika user berinteraksi dengan ATM, dan perubahan behavior yang terjadi bila object tersebut berganti state.

Dalam sebuah context (misalnya class `ATMMachine`), nantinya keempat state harus disertakan dalam class tersebut sebagai *constant object variable* dimana keempat state dideklarasikan ketika class tersebut dijalankan. Selain keempat state, juga terdapat *state pointer* dimana nantinya ATM akan menggunakan salah satu state sebagai state awal ketika object tersebut dideklarasikan dan dapat berubah ketika mengalami perubahan state oleh state yang menjalankan method yang melakukan pergantian state.

```java
public class ATMMachine {
  private int money;
  private final ATMState noCardState, hasCardState, hasPinState, noCashState;
  
  private ATMState currentState;

  public ATMMachine(int money) {
    this.money = money;
    noCardState = new NoCard(this);
    hasCardState = new HasCard(this);
    hasPinState = new HasPin(this);
    noCashState = new NoCash(this);
    currentState = noCardState;
  }
  
  public void insertCard() {
    currentState.insertCard();
  }

  public void ejectCard() {
    currentState.ejectCard();
  }
  
  public void insertPin(int pin) {
    currentState.insertPin(pin);
  }

  public void withdrawCash(int amountOfCash) {
    currentState.withdrawCash(amountOfCash);
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
    
    if (money > 100000) {
      setCurrentState(noCardState);
    }
  }

  public ATMState getCurrentState() {
    return currentState;
  }

  public void setCurrentState(ATMState currentState) {
    this.currentState = currentState;
  }

  public ATMState getNoCardState() {
    return noCardState;
  }

  public ATMState getHasCardState() {
    return hasCardState;
  }

  public ATMState getHasPinState() {
    return hasPinState;
  }

  public ATMState getNoCashState() {
    return noCashState;
  }
}
```

Karena hubungan antara context dan state interface mempunyai hubungan yang erat dan bergantung satu sama lain, maka contextlah yang memiliki state interface karena Context berperan sebagai *state machine* yang mempunyai behavior yang terikat dengan state-state dalam class tersebut dan dalam state sudah terwakili object yang berisikan state machine dimana ketika state tersebut ingin mengubah state, maka state akan mengubah state pada *state machine* ke state lain.

Ketika client ingin berinteraksi dengan ATM, maka client dapat memilih salah satu dari 4 action yang tersedia dalam ATM dimana setiap action akan menghasilkan behavior yang berbeda sesuai dengan state yang ada pada ATM.

Keempat method tersebut masing-masing mendelegasikan isi methodnya ke state pada ATM sebagai berikut:

```java
public class ATMMachine {
  // attributes...
  private ATMState currentState;

  // delegator
  public void insertCard() {
    currentState.insertCard();
  }

  public void ejectCard() {
    currentState.ejectCard();
  }
  
  public void insertPin(int pin) {
    currentState.insertPin(pin);
  }

  public void withdrawCash(int amountOfCash) {
    currentState.withdrawCash(amountOfCash);
  }

  // method and behaviors
}
```

Dan pada masing-masing state akan menjalankan methodnya sesuai dengan keberadaan state pada ATM. Misal ATM berada pada state dimana ATM tidak ada kartu, maka ketika salah satu method dijalankan dari `ATMMachine` akan didelegasikan kepada `NoCard` dan state tersebut akan menentukan apakah state dalam ATM tersebut tetap sama atau berubah ke state lain.

Sebagai contoh demonstrasi state, misalnya sebuah mesin ATM memiliki tampungan uang sebanyak 300000 dimana user ingin menarik uang dengan nominal 150000 dari ATM maka sisa uang tampungan dalam ATM adalah 150000. Kemudian terdapat user lain ingin menarik uang dengan nominal 300000 dari ATM, maka ATM menolak permintaan user tersebut dengan alasan karena saldo yang ditarik melebihi tampungan uang dalam ATM.

Sebaliknya, jika seseorang ingin menarik uang namun tidak memasukkan kartu/PIN, maka permintaan tersebut ditolak hingga orang tersebut memasukkan kartu dan PIN dengan benar.

Contoh kasus yang terimplementasikan dalam code terlampir sebagai berikut:

```java
ATMMachine atm = new ATMMachine(200000);

System.out.println("Customer A:");
atm.insertCard();
atm.insertPin(123456);
atm.withdrawCash(150000);
atm.ejectCard();

System.out.println("\nCustomer B:");
atm.insertCard();
atm.insertPin(190029);
atm.withdrawCash(300000);
atm.ejectCard();

System.out.println("\nCustomer C:");
atm.withdrawCash(100000);
```

Maka output pada sample code tersebut adalah sebagai berikut:

```
Customer A:
Card inserted!
PIN Valid!
Withdrawn Rp150000 from card!
Remaining: Rp150000
Card ejected!
There is no card inside this machine!

Customer B:
Card inserted!
PIN Valid!
Insufficient ATM amount to withdraw your cash!
Card ejected!

Customer C:
There is no card inside this machine!
```

Perlu kalian ketahui bahwa sebuah state machine baru akan berganti state melalui proses dan validasi-validasi tertentu yang mengakibatkan state tersebut berganti ke state lain.


## References

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994.
- Refactoring.guru (State) - [https://refactoring.guru/design-patterns/state](https://refactoring.guru/design-patterns/state)
- SourceMaking (State) - [https://sourcemaking.com/design_patterns/state](https://sourcemaking.com/design_patterns/state)
- Gang Of Four (GoF) Design Patterns: State - [https://www.journaldev.com/1751/state-design-pattern-java](https://www.journaldev.com/1751/state-design-pattern-java)