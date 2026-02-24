package question_set01.question_9;

/*
================================================================================
🔹 A) QUESTION
(Method Implementation For Attribute Modification)
Create a Person class with name and age attributes. Add a have_birthday() method that
increments the age by one when called.
================================================================================

🔹 B) QUESTION ANALYSIS
- Bu soru bizden bir objenin kendi iç durumunu (state) güncelleyen bir eylem 
  (behavior) yazmamızı istiyor.
- Ölçülen OOP Kavramları:
  1. Domain-Specific Mutators (İş mantığına özel değiştirici metotlar).
  2. Encapsulation: Değişkenlerin private tutulup kontrollü metotlarla değiştirilmesi.
  3. Memory Update (Heap'teki primitive verinin kendi üzerine yazılarak artırılması).
  4. Naming Conventions (have_birthday -> haveBirthday).
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Setter vs. Domain Mutator: Yaşı değiştirmek için 'setAge(int age)' kullanabilirdik. 
  Ancak gerçek hayatta kimse "Yaşımı 25 olarak set et" demez, "Doğum günü kutladım" der. 
  OOP, gerçek hayatı dijitale modellediği için, 'haveBirthday()' gibi işin doğasına 
  uygun isimlendirilmiş metotlar kullanmak (Domain-Driven Design) en iyi pratiktir.
- Increment İşlemi: Java'da bir sayıyı 1 artırmak için 'age = age + 1' veya 
  kısaca 'age++' kullanılır.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE: IN-PLACE MEMORY UPDATE)

KOD:
Person p1 = new Person("Ali", 20);
p1.haveBirthday();

--- STEP 1: Programın Başlaması ve Objenin Yaratılması ---
Stack'te 'p1' referansı oluşur. Heap'te Person objesi için alan açılır. 
'age' bir primitive (int) olduğu için, 20 değeri doğrudan objenin 
içindeki bellek adresine yazılır.

STACK:                                 HEAP:
------------------------               --------------------------------------------
[ main() Method Frame ]                [ Person Object @ 0x777 ]
p1 = [Adres: 0x777] -----------------> private String name = [Adres: 0x11] -> "Ali"
------------------------               private int age = 20 (Direkt burada yaşar)
                                       --------------------------------------------

--- STEP 2: haveBirthday() Metodunun Çağrılması ---
Metot çağrıldığında Stack'te yeni bir frame açılır. Bu frame'in içinde sadece 
'this' referansı vardır (çünkü metot dışarıdan parametre almıyor).

STACK:
---------------------------------
[ haveBirthday() Method Frame ]
this = [Adres: 0x777] --------+
---------------------------------|
[ main() Method Frame ]          |
p1 = [Adres: 0x777] -------------|--(İkisi de aynı objeye bakıyor)
---------------------------------V

HEAP (İşlem Anı):
--------------------------------------------
[ Person Object @ 0x777 ]
this.age++; kodu çalışır. 
İşlemci (CPU), 0x777 adresindeki age değerini (20) okur, 1 ekler (21) 
ve TEKRAR aynı adrese (0x777 içindeki age kutusuna) yazar.
--------------------------------------------

--- STEP 3: İşlem Sonu (FINAL STATE) ---
haveBirthday() frame'i Stack'ten silinir (Pop). Heap'teki yaş güncellenmiş kalır.

STACK:                                 HEAP:
------------------------               --------------------------------------------
(Frame Silindi)                        [ Person Object @ 0x777 ]
[ main() Method Frame ]                private String name = [Adres: 0x11] -> "Ali"
p1 = [Adres: 0x777] -----------------> private int age = 21 (Yeni Değer!)
------------------------               --------------------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Naming Convention: have_birthday -> haveBirthday
- 'name' ve 'age' private yapılacak (Encapsulation).
- 'haveBirthday()' metodu void (geriye değer döndürmeyen) olacak çünkü sadece 
  objenin kendi içindeki (Heap'teki) veriyi güncelleyecek.
================================================================================
*/

public class Person {

    // 1. ATTRIBUTES (State)
    private String name;
    private int age;

    // 2. CONSTRUCTOR
    public Person(String name, int age) {
        this.name = name;
        
        // Yaş negatif olamaz, basit bir validation ekleyelim.
        if (age >= 0) {
            this.age = age;
        } else {
            System.out.println("Age cannot be negative. Setting to 0.");
            this.age = 0;
        }
    }

    // 3. BEHAVIOR / MUTATOR METHOD (Soru bizden bunu istiyor)
    // Parametre almaz, çünkü kimin doğum günü olduğunu biliyor ('this' objesinin).
    public void haveBirthday() {
        // Mevcut yaşı 1 artırır.
        this.age++; 
        
        // Alternatif yazımlar:
        // this.age = this.age + 1;
        // this.age += 1;
        
        System.out.println("Happy Birthday " + this.name + "! You are now " + this.age + " years old.");
    }

    // 4. GETTERS (Test sınıfından okuyabilmek için)
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
- TUZAK 1: Sınavda hocalar "have_birthday(int age)" şeklinde bir parametre 
  koyup koymadığını test edebilir. Doğum günü metodu parametre ALMAMALIDIR! 
  Çünkü obje kendi yaşını (this.age) zaten bilmektedir. Dışarıdan bilgiye ihtiyacı yoktur.
- KRİTİK NOKTA: 'this.age++' işlemi bir obje referansı değiştirmez, sadece Heap 
  içindeki o küçük 'int' kutusundaki sayıyı değiştirir. Bu yüzden Garbage Collector 
  bu işlemde hiçbir şeyi silmez (Çünkü eski bir string veya obje çöpe atılmadı, 
  sayı yerinde güncellendi).
================================================================================
*/