package question_set01.question_7_8;

/*
================================================================================
🔹 A) QUESTION
7. Create a Circle class. This class should have a radius attribute and a calculate_area()
method. The method should calculate and return the area of the circle.
8. Add a "pi number" constant and a static method calculate_circumference(radius) to the
Circle class. The method should calculate and return the circumference of the circle.
================================================================================

🔹 B) QUESTION ANALYSIS
- Bu soru seti, nesneye ait (instance) özellikler ile sınıfa ait (static) özelliklerin
  farkını ölçmektedir.
- Ölçülen Kavramlar:
  1. Instance Variable (Nesne Değişkeni): Her nesne için bellekte ayrı yer kaplar (radius).
  2. Instance Method: O nesneye ait verileri (radius) kullanarak işlem yapar (calculateArea).
  3. Class Constant (Sınıf Sabiti): 'static final' keywordleri ile oluşturulur.
  4. Static Method (Sınıf Metodu): Nesne oluşturulmadan (new denmeden) doğrudan 
     sınıf adıyla çağrılabilen metotlardır.
================================================================================

🔹 C) THEORETICAL BACKGROUND
- "Static" Ne Demektir? OOP'de 'static' kelimesi, o özelliğin veya metodun belirli 
  bir objeye DEĞİL, şablonun (Sınıfın) KENDİSİNE ait olduğunu belirtir.
- Analoji: Fizik veya Calculus problemlerini düşünün. Çizeceğiniz her çemberin 
  yarıçapı (radius) farklı olabilir. Yarıçap, o spesifik çembere (objeye) aittir. 
  Ancak Pi sayısı (3.14159) evrensel bir sabittir. Bin tane çember de çizseniz, Pi 
  sayısı değişmez. Bu yüzden Pi sayısını her çember objesinin içine tekrar tekrar 
  yazıp RAM'i israf etmek yerine, onu 'static' yaparak Sınıfın tahtasına bir kez yazarız.
- Constants (Sabitler): Java'da değişmesini istemediğimiz değerleri 'final' 
  keyword'ü ile koruma altına alırız. 
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE: METASPACE / METHOD AREA)
Şimdiye kadar Stack ve Heap'i gördük. İşin içine 'static' girince, JVM'in üçüncü 
bir bellek alanı devreye girer: Metaspace (Eski adıyla Method Area / PermGen).

Metaspace, sınıfların kodlarının ve 'static' (evrensel) değişkenlerin tutulduğu yerdir.

DETAILED MEMORY LAYOUT:

KOD:
System.out.println(Circle.PI); // Obje yaratmadan PI sayısını okuduk!
Circle c1 = new Circle(5.0);
c1.calculateArea();
Circle.calculateCircumference(10.0);

--- STEP 1: Programın Başlaması ve Sınıfın Yüklenmesi ---
JVM, Circle sınıfını gördüğü an, daha ortada hiçbir obje ('new' komutu) yokken 
Circle sınıfını Metaspace'e yükler ve 'static' değişkenleri belleğe kazır.

METASPACE (Sınıf Alanı):               STACK:
-----------------------------          ------------------------
[ Circle Class Blueprint ]             [ main() Method Frame ]
static final double PI = 3.14          (Henüz c1 objesi yok)
static calculateCircumference()        ------------------------
-----------------------------

--- STEP 2: new Circle(5.0) Çalıştığında ---
Heap'te yeni bir obje oluşur. DİKKAT: Bu objenin içinde PI sayısı YOKTUR! 
Obje sadece kendi kişisel değişkenini (radius) taşır. PI'ye ihtiyacı olursa 
gidip Metaspace'e bakar.

HEAP:                                  METASPACE:
-----------------------------          -----------------------------
[ Circle Object @ 0x303 ]              [ Circle Class Blueprint ]
private double radius = 5.0            static final double PI = 3.14
-----------------------------          -----------------------------
(Not: Objenin içinde sadece 5.0 var, PI tek bir yerde, Metaspace'de yaşıyor)

--- STEP 3: Statik Metot Çağrısı: Circle.calculateCircumference(10.0) ---
Statik metotlar obje üzerinden çağrılmaz (c1.calculateCircumference yapılmaz). 
Sınıfın adı üzerinden çağrılır. Stack'te açılan frame'in içinde bir 'this' (objeyi 
işaret eden referans) YOKTUR. Çünkü ortada obje olmak zorunda değildir.

STACK:
---------------------------------
[ calculateCircumference() Frame]
radius = 10.0 (Parametre)
(this referansı YOK!)
---------------------------------
[ main() Method Frame ]
c1 = [Adres: 0x303]
---------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Naming Convention: calculate_area -> calculateArea, calculate_circumference -> calculateCircumference.
- PI Sabiti: 'public static final double PI = 3.14159;' olarak tanımlanacak.
- calculateArea() bir instance (nesne) metodu olduğu için içerde 'this.radius' kullanabilir.
  Formül: $$ Area = \pi \cdot r^2 $$
- calculateCircumference(radius) bir static (sınıf) metodu olduğu için bir parametre alacak 
  ve o parametreyle işlem yapacak.
  Formül: $$ Circumference = 2 \cdot \pi \cdot r $$
================================================================================
*/

public class Circle {

    // 1. CLASS CONSTANT (Sınıf Sabiti - Metaspace'de tutulur)
    // - public: Her yerden erişilebilir (Evrensel bir matematik sabiti olduğu için gizlemeye gerek yok)
    // - static: Objelere değil, doğrudan Circle sınıfına aittir.
    // - final: Değeri bir daha asla değiştirilemez (Constant).
    // İsimlendirme Kuralı: Constant (Sabit) değişkenler tamamen BÜYÜK HARF ve alt tire ile yazılır.
    public static final double PI = 3.14159265359;

    // 2. INSTANCE ATTRIBUTE (Nesne Değişkeni - Heap'te her obje için ayrı tutulur)
    private double radius;

    // 3. CONSTRUCTOR
    public Circle(double radius) {
        // Validation: Yarıçap negatif olamaz.
        if (radius >= 0) {
            this.radius = radius;
        } else {
            System.out.println("Radius cannot be negative! Setting to 0.");
            this.radius = 0;
        }
    }

    // 4. INSTANCE METHOD (Nesne Metodu - Soru 7)
    // Sadece yaratılmış bir çemberin alanı hesaplanabilir. 
    // Bu yüzden bu metot "static" DEĞİLDİR ve "this.radius"a erişebilir.
    public double calculateArea() {
        // Area = PI * r^2
        return PI * (this.radius * this.radius);
    }

    // 5. STATIC METHOD (Sınıf Metodu - Soru 8)
    // Sınıfa aittir. Sınıf adı kullanılarak çağrılır.
    // İçerisinde 'this.radius' KULLANILAMAZ çünkü hangi çember objesinden 
    // bahsedildiğini bilmez (belki de henüz hiç çember yaratılmamıştır).
    // Bu yüzden parametre olarak dışarıdan 'r' değeri almak zorundadır.
    public static double calculateCircumference(double r) {
        // Circumference = 2 * PI * r
        return 2 * PI * r;
    }

    // Getter ve Setter (Encapsulation için)
    public double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        if (radius >= 0) {
            this.radius = radius;
        }
    }
}

/*
================================================================================
🔹 F) EXAM TIPS (Sınav Odaklı Özet - 🚨 DİKKAT)
- TUZAK 1: Statik bir metodun içinden 'this' keyword'ü KULLANILAMAZ! 
  Hoca sınavda statik bir metodun içine "this.radius" yazar ve "Bu kod derlenir mi?" 
  diye sorar. CEVAP: "Derlenmez (Compile Error). Çünkü static metotlar objeye değil 
  sınıfa aittir ve 'this' referansına sahip değildirler."
- TUZAK 2: Static değişkenler (PI gibi) 'SınıfIsmi.DEGISKEN_ISMI' şeklinde çağrılır 
  (Örn: Circle.PI). Obje üzerinden (myCircle.PI) çağırmak teknik olarak çalışsa da 
  kötü bir pratiktir ve sınavlarda puan kaybettirebilir.
- EZBERLE: Bir metot veya değişken sınıfa aitse (evrenselse veya yardımcı bir fonksiyonsa) 
  STATIC, doğrudan nesnenin fiziksel durumunu ilgilendiriyorsa INSTANCE (non-static) olur.
================================================================================
*/