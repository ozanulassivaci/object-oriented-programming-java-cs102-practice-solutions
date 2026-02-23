package src.question_set01.question_4;

/*
================================================================================
🔹 A) QUESTION
Draw the memory layout drawing for the following code:
public class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Person person = new Person("Ozan", 21);
    }
}
================================================================================

🔹 B) QUESTION ANALYSIS
- Bu soru, RAM'in Stack ve Heap bölgelerindeki hareketliliği saniye saniye 
  görebilme (Memory Layout) yeteneğini ölçmektedir.
- Ölçülen Kavramlar:
  1. Primitive Type (int) bellekte nasıl tutulur?
  2. Reference Type (String) bellekte nasıl tutulur? (String Pool mantığı)
  3. Constructor (Kurucu Metot) çağrıldığında Stack'te nasıl bir "Frame" açılır?
  4. 'this' anahtar kelimesi bellekte tam olarak nereyi işaret eder?
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Java'da bir metot çağrıldığında (main veya constructor fark etmez), 
  Stack (Yığın) belleğinde o metot için bir çalışma alanı (Stack Frame) açılır.
- "new" anahtar kelimesi kullanıldığında Heap (Öbek) belleğinde objeye yer ayrılır.
- "String" özel bir sınıftır. Çoğu zaman Heap'in içindeki "String Pool" 
  (String Havuzu) adı verilen özel bir alanda tutulur.
- Primitive tipler (int, double, boolean) doğrudan yaratıldıkları alanın 
  içinde (Stack'te ise Stack'te, Heap'teki bir objenin içindeyse objenin içinde) tutulur.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE MEMORY LAYOUT)

KOD: Person person = new Person("Ozan", 21);

Aşağıda bu tek satırlık kodun, arka planda (JVM'de) yarattığı 3 adımlı 
bellek haritası saniye saniye çizilmiştir:

--- STEP 1: main() Metodunun Başlaması ---
Program çalıştığında ilk olarak Stack'te main() metodu için bir blok (frame) açılır.
Henüz "new Person" işlemi tamamlanmamıştır.

STACK:
---------------------------------
[ main() Method Frame ]
args = [Adres: 0x001] -> (Heap'teki String[] dizisi)
person = (Henüz atanmadı, null veya tanımsız)
---------------------------------
HEAP:
---------------------------------
String Pool (String Havuzu):
"Ozan" [Adres: 0x10A] (String literal kullanıldığı için havuza atıldı)
---------------------------------


--- STEP 2: Constructor (Kurucu Metot) Çağrısı ---
"new" kelimesi Heap'te bir yer açar. Ardından "Person(String name, int age)" 
constructor'ı çalışır ve Stack'te main()'in üzerine YENİ bir frame açılır.
'this' kelimesi, Heap'te yeni açılan yeri gösteren gizli bir referanstır.

STACK:
---------------------------------
[ Person() Constructor Frame ]
this = [Adres: 0x500] ----------> (Heap'teki yeni objeyi işaret eder)
name = [Adres: 0x10A] ----------> (String Pool'daki "Ozan"ı işaret eder)
age  = 21
---------------------------------
[ main() Method Frame ]
args = [Adres: 0x001]
---------------------------------

HEAP:
---------------------------------
String Pool:
[Adres: 0x10A] -> "Ozan"

[Person Object @ 0x500] (Bu an için içi henüz varsayılan değerlerde)
this.name = null
this.age = 0
---------------------------------


--- STEP 3: Atamaların Yapılması ve Objenin Teslimi (FINAL STATE) ---
Constructor içindeki "this.name = name;" ve "this.age = age;" kodları çalışır.
Obje doldurulur. Constructor işini bitirir ve Stack'ten SİLİNİR (Pop edilir).
Heap'teki objenin adresi (0x500), main metodundaki 'person' değişkenine verilir.

STACK:
---------------------------------
(Constructor frame'i silindi!)
---------------------------------
[ main() Method Frame ]
args   = [Adres: 0x001]
person = [Adres: 0x500] ---------(İşaret Eder)--------+
---------------------------------                     |
                                                      |
HEAP:                                                 |
---------------------------------                     |
String Pool:                                          |
[Adres: 0x10A] -> "Ozan"                              |
                                                      |
[Person Object @ 0x500] <-----------------------------+
name = [Adres: 0x10A] -----> "Ozan" 
age  = 21 (Primitive olduğu için adres tutmaz, sayıyı direkt içinde tutar)
---------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Bu kodun tek bir mantıksal eksiği, Encapsulation (Kapsülleme) yapılmamış olmasıdır.
- Sınav standartlarına uyum için 'name' ve 'age' alanları private yapılmalı ve 
  ilgili getter metotları eklenmelidir.
================================================================================
*/

public class Person {

    // 1. ATTRIBUTES (State)
    // Bunları default yerine private yapmayı bilmek CS açısından daha iyi.
    private String name;
    private int age;

    // 2. CONSTRUCTOR
    // Parametrelerdeki "Ozan" ve 21, Stack'teki constructor frame'ine düşer.
    public Person(String name, int age) {
        // 'this' referansı objenin Heap'teki adresini gösterir.
        this.name = name; 
        this.age = age;   
    }

    // 3. GETTERS (Encapsulation gereği)
    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}

/*
================================================================================
🔹 F) EXAM TIPS (Sınav ve Mülakat İpuçları)
- KRİTİK FARK: Objenin içindeki "age" (21) bir primitive tiptir. Obje 0x500 
  adresindeyse, bu 21 sayısı fiziksel olarak o 0x500 adresinin içindedir.
- KRİTİK FARK: Objenin içindeki "name" bir Reference (String) tiptir. "Ozan" 
  kelimesi objenin içinde YAZMAZ. Objenin içinde, "Ozan" kelimesinin String Pool'daki 
  adresi (0x10A) yazar. Bu, Java mülakatlarında en çok sorulan memory detaylarından biridir.
================================================================================
*/