package question_set01.question_5;

/*
================================================================================
🔹 A) QUESTION
(Private Attributes And Getter/setter Methods)
Define a private attribute mileage in the Car class. Provide two methods get_mileage() 
and set_mileage(mileage) to access and update this attribute.
================================================================================

🔹 B) QUESTION ANALYSIS
- Soru bizden "Encapsulation" (Kapsülleme) prensibini doğrudan uygulamamızı istiyor.
- Ölçülen OOP Kavramları:
  1. Data Hiding (Veri Gizleme): 'private' access modifier kullanımı.
  2. Getter (Accessor) Methods: Gizli veriyi dışarıya güvenli şekilde okutma.
  3. Setter (Mutator) Methods: Gizli veriyi dışarıdan kontrollü şekilde güncelleme.
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Encapsulation Neden Var? Gerçek dünya senaryosu: Örneğin bir Teknofest Hava 
  Savunma Sistemleri projesinin yazılım tasarımını (software design) yaparken, 
  füzenin hedef koordinatlarını (targetCoordinates) herkesin değiştirebileceği 
  şekilde 'public' bırakamazsınız. Bu veriye sadece güvenlik kontrollerinden geçen, 
  yetkilendirilmiş metotlar (Setter) aracılığıyla erişilmelidir.
- Arabadaki Kilometre (Mileage) Analojisi: Gerçek hayatta bir arabanın kilometresini 
  geriye saramazsınız (yasal değildir). Eğer 'mileage' public olsaydı, herkes 
  arabaObjesi.mileage = 0 diyerek sayacı sıfırlayabilirdi. Setter metodu kullanarak 
  içine "Eğer yeni kilometre, eskisinden küçükse hata ver" mantığını kurabiliriz.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE)

KOD: 
Car myCar = new Car();
myCar.setMileage(15000);

Stack ve Heap üzerinde metot çağrımı sırasında neler oluyor?

--- STEP 1: setMileage(15000) Metodu Çağrıldığında ---
Metot çağrıldığında Stack'te "setMileage" için YENİ BİR FRAME açılır. 
Parametre olarak gönderilen 15000 değeri (primitive olduğu için) direkt bu frame 
içine kopyalanır. 'this' referansı da Heap'teki arabamızı işaret eder.

STACK:
---------------------------------
[ setMileage() Method Frame ]
this    = [Adres: 0x888] --------+
mileage = 15000 (Local param)    |
---------------------------------|
[ main() Method Frame ]          |
myCar = [Adres: 0x888] ----------|--(İkisi de aynı objeye bakıyor)
---------------------------------V

HEAP:
--------------------------------------------
[ Car Object @ 0x888 ]
private int mileage = 0 (Eski Değer)
...diğer değişkenler
--------------------------------------------

--- STEP 2: Atama ve Doğrulama (this.mileage = mileage) ---
Metot içindeki kod çalışır. Local değişken olan 15000, 'this'in gösterdiği Heap 
adresindeki asıl mileage kutusuna yazılır (Eğer güvenlik duvarını -if şartını- geçerse).
İşlem bitince setMileage() frame'i Stack'ten SİLİNİR.

STACK:                                 HEAP:
------------------------               --------------------------------------------
(setMileage frame POP!)                [ Car Object @ 0x888 ]
------------------------               private int mileage = 15000 (YENİ DEĞER!)
[ main() Method Frame ]                ...diğer değişkenler
myCar = [Adres: 0x888] --------------> --------------------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Naming Convention: get_mileage -> getMileage, set_mileage -> setMileage.
- Güvenlik (Validation): setMileage metodunun içine basit bir "if" şartı 
  ekleyerek kilometrenin geriye sarılmasını engelleyeceğiz. Bu, Encapsulation'ın 
  neden gerekli olduğunu anlatan en güzel profesyonel dokunuştur.
================================================================================
*/

public class Car {

    // 1. ATTRIBUTES
    private String model;
    private String color;
    private int year;
    
    // YENİ EKLENEN ÖZELLİK (Sadece bu sınıfın içinden erişilebilir)
    private int mileage;

    // 2. CONSTRUCTOR
    public Car(String model, String color, int year) {
        this.model = model;
        this.color = color;
        this.year = year;
        this.mileage = 0; // Fabrikadan yeni çıkan arabanın kilometresi 0'dır.
    }

    // 3. GETTER (Accessor) - Okuma İzni
    // Java Standardı: get + DegiskenAdi (CamelCase)
    public int getMileage() {
        return this.mileage; // Objenin bellekteki güncel kilometresini döndürür.
    }

    // 4. SETTER (Mutator) - Yazma/Değiştirme İzni
    // Parametre olarak gelen yeni kilometreyi alır ve objeninkine atar.
    public void setMileage(int mileage) {
        // Profesyonel Dokunuş: Veri Doğrulama (Validation)
        // Arabanın kilometresi geriye sarılamaz ve negatif olamaz!
        if (mileage >= this.mileage) {
            this.mileage = mileage; // Güvenlik kontrolünden geçti, Heap'i güncelle.
            System.out.println("Mileage updated successfully to: " + this.mileage);
        } else {
            // Güvenlik ihlali!
            System.out.println("ERROR: You cannot roll back the odometer or set a negative value!");
        }
    }

    // Eski metotlarımız (Testlerde kullanmak üzere tutuyoruz)
    public void startEngine() {
        System.out.println("Engine started.");
    }

    public String paintCar(String newColor) {
        this.color = newColor;
        return this.color;
    }
}

/*
================================================================================
🔹 F) EXAM TIPS (Sınav Odaklı Özet)
- Özyeğin CS 102 sınavlarında hocaların en çok dikkat ettiği detaylardan biri 
  isimlendirme standartları ve 'this' kullanımıdır.
- TUZAK: Parametre ismi ile sınıf değişkeni ismi aynıysa (ikisi de 'mileage'), 
  "mileage = mileage;" yazarsan Java hangi mileage olduğunu bilemez (Shadowing). 
  Objeye ait olanı belirtmek için daima "this.mileage" kullanmak ZORUNDASIN.
- KURAL: Getter metotları asla parametre almaz ve veri tipini 'return' eder.
  Setter metotları her zaman parametre alır ve genellikle 'void' döner (return etmez).
================================================================================
*/