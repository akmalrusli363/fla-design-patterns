# Chain of Responsibility

[Main Page](..) → [Behavioral Design Patterns](.) → [Chain of Responsibility](#)

[Source Code](https://github.com/akmalrusli363/fla-design-patterns/tree/main/src/ohmypatt/patt/behavioral/chains) | [refactoring.guru](https://refactoring.guru/design-patterns/chain-of-responsibility) | [sourcemaking.com](https://sourcemaking.com/design_patterns/chain_of_responsibility)

![Chain of Responsibility](../assets/img/behavioral/chain-of-responsibility.png#center "Chain of Responsibility")

**Chain of Responsibility** merupakan design pattern dimana sebuah interface dapat mengakses object berikutnya dari interface yang sama. Meski secara struktural mirip dengan Decorator, namun design pattern ini lebih difokuskan pada proses handling sebuah request.

Design pattern ini sangat berguna ketika sistem ingin memproses request yang dibutuhkan oleh Client dimana request tersebut akan diseleksi oleh sebuah handler sesuai kebutuhan dan mengalihkan request tersebut ke handler lain jika tidak sesuai kebutuhannya.

![Bulks of validation required within class](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/problem1-en.png#white#center "Bulks of validation required within class")

![Splitting bulks of validation to chain of handlers](https://refactoring.guru/images/patterns/diagrams/chain-of-responsibility/solution1-en.png#white#center "Splitting bulks of validation to chain of handlers")

Fungsi dari **Chain of Responsibility** sendiri adalah untuk meminimalisir dependensi dan memaksimalkan modularitas terutama pada proses seleksi request dimana pembagian alur terhadap proses yang dilakukan oleh sistem dipisahkan secara *modular* menjadi satuan **handler class** dimana setiap *handler class* akan menyeleksi request sehingga dapat diproses sesuai kebutuhan dari request tersebut.

Design pattern ini sering disandingkan dengan Decorator dimana secara struktural memiliki struktur model yang sama. Perbedaan secara mendasar dengan Decorator sendiri adalah:

- **Decorator** dapat menjalankan request sekaligus sedangkan **Chain of Responsibility** hanya meneruskan perintah ke object lain apabila memenuhi syarat validasi atau berhenti jika sebaliknya.
- **Decorator** harus berhenti di object class turunan interface tanpa decoratornya (misal `Player` terdapat `Body` yang memiliki fungsionalitas dasar tanpa decorator sedangkan `Armor` sebagai decorator harus menemukan object dasar agar berhenti). Sedangkan **Chain of Responsibility** bisa berhenti di object handler mana saja, bahkan dalam kondisi *unhandled* sekalipun.


## Analogi

![ATM Denominations](../assets/img/behavioral/chain-of-responsibility-atm-denominations.png#center "ATM Denominations")

![ATM money solving steps](https://sourcemaking.com/files/sm/images/patterns/Chain_of_responsibility_example.png#white#center "ATM money solving steps")

Salah satu contoh termudah dalam kasus *Chain of Responsibility* sendiri adalah bagaimana cara sebuah request dapat dihandle sesuai kebutuhannya dimana dalam kasus mesin ATM terdapat 3 pecahan uang yang dapat dipecahkan, yaitu 20 ribu, 50 ribu, dan 100 ribu.

Pada kasus pembagian uang sesuai pecahan dalam mesin ATM, nasabah akan mengimput nominal yang akan ditarik dari mesin ATM dengan minimum pecahan 20 ribu dan harus habis dibagi salah satu dari ketiga pecahan uang tersebut.

Secara sistematis, pemecahan uang dalam mesin ATM diurutkan dari pecahan uang 100 ribu, 50 ribu, dan berakhir di 20 ribu. Ketika uang yang tersisa untuk ditarik kurang dari 100 ribu, maka pembagian pecahan uang akan beralih ke pecahan 50 ribu dan berganti menjadi 20 ribu ketika kurang dari 50 ribu.

Karena pada kasus ini tidak menyinggung masalah mengenai pembagian pecahan uang melalui [Coin change problem](https://en.wikipedia.org/wiki/Coin_problem), maka sisa uang yang akan ditarik di bawah 20 ribu akan dikembalikan ke saldo nasabah atau sederhananya, ditolak permintaannya.

## UML Model

![Chain of Responsibility UML Model](https://sourcemaking.com/files/v2/content/patterns/Chain_of_responsibility__.png#white#center "Chain of Responsibility UML Model")


## Contoh Kasus

Sesuai yang dikisahkan pada bagian Analogi, ketika nasabah ingin menarik uang, maka hal yang pertama kali mesin ATM lakukan adalah melakukan validasi pada uang yang akan ditarik dimana nominal uang yang akan ditarik harus berkelipatan 10 ribu. Setelah nominal yang divalidasi sesuai dengan kelipatannya, maka uang akan ditarik mulai dari 100 ribu, 50 ribu, 20 ribu, dan 10 ribu.

Agar uang dapat ditarik sesuai nominalnya, maka mesin ATM dapat menerapkan **chain of responsibility** dimana penarikan uang dapat dilakukan dengan melalui beberapa proses validasi yang berantai sebelum akhirnya seluruh uang berhasil ditarik dari ATM.


```java
public class Currency {
    private int amount;

    public Currency(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }
}

public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);
    void dispense(Currency currency);
}
```

```java
public class Dispense100k implements DispenseChain {
    private DispenseChain chain;

    public Dispense100k(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if (amount >= 100000) {
            int numOfBanknotes = amount/100000;
            int remainder = amount % 100000;
            System.out.printf("Dispensed IDR 100k @ %d notes! ", numOfBanknotes);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}

public class Dispense50k implements DispenseChain {
    private DispenseChain chain;

    public Dispense50k(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if (amount >= 50000) {
            int numOfBanknotes = amount/50000;
            int remainder = amount % 50000;
            System.out.printf("Dispensed IDR 50k @ %d notes! ", numOfBanknotes);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}

public class Dispense20k implements DispenseChain {
    private DispenseChain chain;

    public Dispense20k(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if (amount >= 20000) {
            int numOfBanknotes = amount/20000;
            int remainder = amount % 20000;
            System.out.printf("Dispensed IDR 20k @ %d notes! ", numOfBanknotes);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}

public class Dispense10k implements DispenseChain {
    private DispenseChain chain;

    public Dispense10k(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        int amount = currency.getAmount();
        if (amount >= 10000) {
            int numOfBanknotes = amount/10000;
            int remainder = amount % 10000;
            System.out.printf("Dispensed IDR 10k @ %d notes! ", numOfBanknotes);
            if (remainder != 0) {
                this.chain.dispense(new Currency(remainder));
            }
        } else {
            this.chain.dispense(currency);
        }
    }
}
```

Selain dapat menarik 100rb, 50rb, 20rb, dan 10rb, mesin ATM juga mempunyai class kosong implementasi `DispenseChain` yaitu `DispenseNothing` dimana class ini merupakan class dengan object kosong dimana ketika chain mencapai class ini, maka keberantaian object berakhir di sini. Class ini juga akan menampung uang yang akan ditarik namun sebatas *end-point* chain karena tidak ada chain setelah `DispenseNothing`.

Tanpa adanya **null chain** (alias *end chain* alias object chain kosong), maka ketika sebuah chain object memanggil chain berikutnya akan terlempar `NullPointerException` karena tidak ada chain yang terpanggil pada object tersebut.

```java
public class DispenseNothing implements DispenseChain {
    // May incorrect & causes Refused Bequest smell, but main goal to stop chains
    @Override
    public void setNextChain(DispenseChain nextChain) {
        // nothing
    }

    @Override
    public void dispense(Currency currency) {
        System.out.printf("Nothing to dispense for IDR %d!", currency.getAmount());
    }
}
```

Setelah masing-masing class dibuat sesuai nominal penarikan uang, class `MoneyValidator` juga ditambahkan untuk memastikan bahwa uang tersebut memenuhi syarat kelipatan nominal sebelum ditarik.

```java
// Additional class to validate money to dispense
public class MoneyValidator implements DispenseChain {
    private DispenseChain chain;

    public MoneyValidator(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.chain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        if (currency.getAmount() % 10000 != 0) {
            System.out.println("Amount must be 10k multipler!");
        } else {
            this.chain.dispense(currency);
        }
    }
}
```

Ketika anda ingin menarik saldo dari ATM, maka urutan penarikan saldo dari ATM secara mendasar adalah sebagai berikut:

```java
private DispenseChain getAtmDispenseChains() {
    DispenseChain dispense10k = new Dispense10k(new DispenseNothing());
    DispenseChain dispense20k = new Dispense20k(dispense10k);
    DispenseChain dispense50k = new Dispense50k(dispense20k);
    return new Dispense100k(dispense50k);
}

// Alternatifnya (oneliner)
private DispenseChain getAtmDispenseChains() {
    return new Dispense100k(new Dispense50k(new Dispense20k(new Dispense10k(new DispenseNothing))));
}
```

Dan pada Client class (contoh: `ATMMachine`), client tinggal mendeklarasikan chain of objects (atau gampangnya cantumkan method `getAtmDispenseChains()` dan tinggal panggil method tersebut sebagai objectnya) sebagai berikut:

```java
public class ATMMachine {
    private DispenseChain getAtmDispenseChains() {
        DispenseChain dispense10k = new Dispense10k(new DispenseNothing());
        DispenseChain dispense20k = new Dispense20k(dispense10k);
        DispenseChain dispense50k = new Dispense50k(dispense20k);
        return new Dispense100k(dispense50k);
    }

    public void dispense(int nominal) {
        Currency currency = new Currency(nominal);
        DispenseChain dispenser = new MoneyValidator(getAtmDispenseChains());
        try {
            dispenser.dispense(currency);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
```

Andaikan nasabah ingin menarik uang, maka nasabah tinggal menginput nilai dan nilai tersebut akan masuk ke dalam `ATMMachine` untuk ditarik uangnya. Contoh hasil eksekusi code yang dijalankan pada class `Main` adalah sebagai berikut:

```
Enter amount to dispense: 200000
Dispensed IDR 100k @ 2 notes!

Enter amount to dispense: 160000
Dispensed IDR 100k @ 1 notes!
Dispensed IDR 50k @ 1 notes!
Dispensed IDR 10k @ 1 notes!

Enter amount to dispense: 2000
Amount must be 10k multipler!

Enter amount to dispense: 220000
Dispensed IDR 100k @ 2 notes!
Dispensed IDR 20k @ 1 notes!

Enter amount to dispense: 25000
Amount must be 10k multipler!

Enter amount to dispense: 180000
Dispensed IDR 100k @ 1 notes!
Dispensed IDR 50k @ 1 notes!
Dispensed IDR 20k @ 1 notes!
Dispensed IDR 10k @ 1 notes!
```

### B. Middleware

**Chain of Responsibility** juga sering diaplikasikan pada request handler dimana pada setiap handler akan melakukan validasi pada request yang diterima dimana ketika request tersebut diterima, maka handler akan memproses requestnya ke handler berikutnya dan ketika ditolak maka request akan berhenti dan tidak akan diteruskan.

Pada contoh kasus berikut, pada sebuah service API, terdapat sebuah interface `Handler` dan abstract class `BaseHandler` dimana handler berperan sebagai *chain of handler* yang dapat merangkap handler berikutnya & memproses request dalam bentuk apapun (termasuk mendelegasikan request ke handler berikutnya).

```java
public interface Handler {
    void handle();
    void setNextHandler(Handler nextHandler);
}

public abstract class BaseHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void handle() {
        if (nextHandler != null) nextHandler.handle();
    }

    @Override
    public void setHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
```

Untuk dapat mengakses elemen-elemen API, **API key** diperlukan dalam contoh kasus ini dimana API key sendiri hanya dapat diperoleh dengan login & user harus melakukan generate API key agar dapat memperoleh API yang dimaksud.

Karena untuk memperoleh API key mewajibkan user untuk login, maka terdapat beberapa middleware yang dipakai dalam memperoleh akses ke elemen-elemen API didalamnya. Middleware sendiri merupakan sebuah handler class yang menggunakan **Chain of Responsibility design pattern** dimana class tersebut dapat merangkap handler lain dan akan meneruskan request ke handler berikutnya jika request tersebut lolos validasi.

Pada implementasi code berikut, class `Session`, `User`, dan `APIKey` dibuatkan sebagai model object dimana ketiga object tersebut masing-masing memiliki handlernya sebagai berikut:

```java
public class Session {
    private User user;

    public Session(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public boolean isGuest() {
        return user == null;
    }
    
    @Override
    public String toString() {
        return isGuest() ? "Guest" : getUser().getUsername();
    }
}

public class User {
    private final String username, password;
    private APIKey apiKey;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean hasApiKey() {
        return apiKey != null;
    }

    public APIKey getApiKey() {
        return apiKey;
    }

    public void setApiKey(APIKey apiKey) {
        this.apiKey = apiKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class APIKey {
    private final String key;

    public APIKey(String key) {
        this.key = key;
    }

    public static boolean validateAPIKey(String key) {
        return (key != null && key.matches("[A-Za-z0-9]+") && key.length() < 6);
    }

    public static boolean validateAPIKey(APIKey apiKey) {
        return validateAPIKey(apiKey.key);
    }

    public String getKey() {
        return key;
    }
}
```

```java
public class VerifySessionHandler extends BaseHandler {
    private Session session;

    public VerifySessionHandler(Session session, Handler nextHandler) {
        this.session = session;
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle() {
        if (session == null || session.isGuest()) {
            System.err.println("Not Logged in!");
            return;
        } super.handle();
    }
}

public class VerifyUserHandler extends BaseHandler {
    private User user;

    public VerifyUserHandler(User user, Handler nextHandler) {
        this.user = user;
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle() {
        if (user == null) {
            System.err.println("Not Logged in!");
            return;
        } super.handle();
    }
}

public class VerifyApiKeyHandler extends BaseHandler {
    private APIKey apiKey;

    public VerifyApiKeyHandler(APIKey apiKey, Handler nextHandler) {
        this.apiKey = apiKey;
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle() {
        if (apiKey == null) {
            System.err.println("Please generate API Key first!");
        } else if (APIKey.validateAPIKey(apiKey)) {
            System.err.println("API Key must be alphanumeric, non-whitespaces, >= 6 chars!");
        } else {
            super.handle();
        }
    }
}
```

Ketika request tersebut dihandle, maka proses validasi akan dilakukan di dalam method `handle()` dimana apabila request tersebut sesuai dalam proses validasinya, maka request tersebut akan diteruskan dan sebaliknya, jika tidak sesuai, maka request tersebut berhenti di object handler tersebut dan tidak akan dilanjutkan lagi.

Misalkan request yang akan diberikan dari user kepada elemen API adalah untuk menguji ketersediaan API dengan menggunakan API key dimana apabila user telah login & memiliki API key, maka ada pesan sapaan dari API yang akan memberitahukan user bahwa API tersebut dapat diakses.

Pada salah satu contoh class `HelloMessage`, menggunakan basis `Handler` untuk menerima & menghandle langsung request yang diterima tanpa mengalami modifikasi sedikitpun (kecuali tidak ada next handlernya di method `handle()`).

```java
public class HelloMessage extends BaseHandler {
    private Session session;
    
    public HelloMessage(Session session) {
        this.session = session;
    }

    @Override
    public void handle() {
        System.out.println("Hello, " + session + "! Here is your API corner!");
    }
}
```

Untuk memastikan agar hanya user dengan API key yang berhak mengakses class `HelloMessage`, maka handler-handler yang telah dibuat dirangkaikan sebagai satu kesatuan middleware untuk mengakses komponen API dengan urutan `VerifySessionHandler -> VerifyUserHandler -> VerifyApiKeyHandler` dimana jika dideklarasikan dalam method sebagai berikut:

```java
private Handler getMiddlewareChains(Session s, Handler targetHandler) {
    Handler apiKeyHandler = new VerifyApiKeyHandler(s.getUser().getApiKey(), targetHandler);
    Handler userHandler = new VerifyUserHandler(s.getUser(), apiKeyHandler);
    return new VerifySessionHandler(s, userHandler);
}
```

Sebagai contoh, Session dideklarasikan dengan menggunakan user dengan API Key akan dieksekusi sebagai berikut:

```java
APIKey key = new BasicAPIKey("asademar1a");
User user = new User("Budi Setiawan", "budisetia01_", key);
Session s = new Session(user);

Handler hello = new HelloMessage(s);
Handler middleware = getMiddlewareChains(s, hello);
middleware.handle();
```

Maka outputnya adalah sebagai berikut:

```
Hello, Budi Setiawan! Here is your API corner!
```


## Catatan Tambahan: Middleware

**Middleware** dalam web adalah sebuah konsep yang digunakan dalam web server untuk menghandle HTTP Request dengan melakukan validasi dan filtrasi request yang akan diproses. Ketika request tersebut sesuai dengan hal yang diperlukan, maka request tersebut akan dilanjutkan dan sebaliknya, jika tidak sesuai, maka request tersebut dapat dialihkan maupun dihentikan prosesnya dengan HTML response code.

Middleware sendiri menggunakan **chain of responsibility** design pattern dimana ketika request tersebut sesuai dengan yang apa yang dibutuhkan oleh handler, maka request dapat diteruskan ke request lain/diproses langsung pada class itu juga.

Salah satu penerapan middleware dalam web sendiri adalah authentikasi dimana ketika request yang dilakukan hanya diperuntukkan pada user yang sudah login, berhak mendapatkan akses (authorized/owned), atau berada pada role tertentu.

Contoh middleware sendiri ditulis menggunakan framework PHP Laravel dengan membuatkan middleware sebagai berikut:

```bash
php artisan make:Middleware UserAuthHandler
```

```php
namespace App\Http\Middleware;

use Closure;

class UserAuthHandler
{
     /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        if (Auth::user() === null || Auth::guest()) {
            // redirect to login page to perform login first
            return route('auth.login');
        }
        return $next($request);
    }
}
```

Maka ketika terdapat request yang melewati middleware (misal `UserAuthHandler`) maka ada proses validasi request sesuai kriteria yang masuk (baik melalui pengecekan session, request, header, dan lain-lain) sehingga ketika request tersebut sesuai kriteria maka akan diteruskan, sebaliknya akan ditolak dan dialihkan ke laman lain.

Contoh code lainnya:

```php
namespace App\Http\Middleware;

use Closure;

class ApiKeyHandler
{
     /**
     * Handle an incoming request.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  \Closure  $next
     * @return mixed
     */
    public function handle($request, Closure $next)
    {
        if ($request->api_key != null) {
            return $next($request);
        }
        return abort(403);
    }
}
```


## References

- Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides. Design Patterns: Elements of Reusable Object-Oriented Software. Addison-Wesley Professional, 1994. Page 251-262.
- Eric Freeman, Elisabeth Robson, Bert Bates, Kathy Sierra. Head First Design Patterns. O'Reilly Media, 2004. ISBN: 9780596007126. Page 616-617.
- Refactoring.guru (Chain of Responsibility, termasuk referensi gambar) - [https://refactoring.guru/design-patterns/chain-of-responsibility](https://refactoring.guru/design-patterns/chain-of-responsibility)
- SourceMaking (Chain of Responsibility, termasuk referensi gambar) - [https://sourcemaking.com/design_patterns/chain_of_responsibility](https://sourcemaking.com/design_patterns/chain_of_responsibility)
- Gang Of Four (GoF) Design Patterns: Chain of Responsibility - [https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java](https://www.journaldev.com/1617/chain-of-responsibility-design-pattern-in-java)