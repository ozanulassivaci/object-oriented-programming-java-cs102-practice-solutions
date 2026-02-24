package question_set01.question_1_2_3;
/*
================================================================================
🔹 A) QUESTION
1. Create a simple Car class. This class should have three attributes: model, color, and year.
Define a constructor that initializes these attributes and create a Car object.
2. Add a start_engine() method to the Car class you created above. When this method is
called, it should print "Engine started".
3. Add a paint_car(new_color) method to the Car class. This method should update the color
attribute of the car to the new_color provided and return the new color.
================================================================================

🔹 B) QUESTION ANALYSIS
- Soru bizden temel bir obje modellemesi istiyor.
- Ölçülen OOP Kavramları:
  1. Class & Object Creation: Kalıp oluşturma ve o kalıptan nesne üretme.
  2. Constructor: Nesnenin hafızada ilk oluştuğu anı yönetme.
  3. State Definition & Encapsulation: Nesnenin özelliklerini (attributes) saklama.
  4. Method Definition (Behavior): Nesneye aksiyon ekleme.
  5. State Modification: Nesnenin var olan bir özelliğini (rengini) dışarıdan gelen
     bir veriyle güncelleme.
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Class (Sınıf): Bir nesnenin nasıl görüneceğini ve ne yapabileceğini tanımlayan 
  soyut bir şablondur. Gerçek hayattan analoji: Bir arabanın AutoCAD çizimidir. 
  Çizime binip gidemezsiniz, sadece arabanın özelliklerini belirtir.
- Object (Nesne / Instance): Sınıf şablonu kullanılarak bellekte (RAM) yaratılmış 
  somut varlıktır. Galeride duran, anahtarı olan gerçek arabadır.
- Attribute (Özellik / Field): Arabanın modeli, yılı, rengi gibi durumlarını (State) 
  tutan değişkenlerdir.
- Constructor (Kurucu Metot): Fabrikadaki üretim bandıdır. "new" anahtar kelimesi ile 
  çağrıldığında, arabanın ilk ayarlarını (başlangıç değerlerini) yapar.
- Method (Metot / Davranış): Arabanın motorunu çalıştırmak gibi eylemleridir.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE)
Java'da bellek temel olarak Stack (Yığın) ve Heap (Öbek) olarak ikiye ayrılır.

Primitive (İlkel) vs Reference (Referans) Type Farkı:
- 'int year' bir primitive tiptir. Değeri (örn: 2023) direkt objenin içinde tutulur.
- 'String model' ve 'String color' referans tiplerdir. Objenin içinde metnin 
  kendisi değil, metnin bellekteki adresi tutulur.

DETAILED MEMORY LAYOUT:

STEP 1: Program başladı, nesne henüz yaratılmadı.
STACK:
---------------------------------
main() metodu çalışıyor...
(Henüz Car referansı yok)
---------------------------------
HEAP:
---------------------------------
(Boş)
---------------------------------

STEP 2: Car myCar = new Car("Honda", "Red", 2023); komutu çalıştı.
1. 'new' kelimesi Heap'te bir Car objesi için yer açar (Adres: 0x101).
2. Constructor çalışır ve bu objenin içini doldurur.
3. Stack'te 'myCar' adında bir referans (kumanda) oluşur ve 0x101 adresini gösterir.

STACK:                                 HEAP:
------------------------               --------------------------------------------
main()                                 [Car Object @ 0x101]
myCar [Adres: 0x101] ----------------> |  year = 2023 (Primitive, direkt burada)  |
------------------------               |  model = [Adres: 0xA1] -----> "Honda"    |
                                       |  color = [Adres: 0xB2] -----> "Red"      |
                                       --------------------------------------------

STEP 3: myCar.paintCar("Blue"); komutu çalıştı.
1. myCar referansı üzerinden 0x101 adresindeki objeye gidilir.
2. 'color' değişkeninin işaret ettiği "Red" adresi koparılır.
3. Heap'te yeni bir "Blue" String'i oluşur (veya String Pool'dan alınır) ve 
   color artık orayı işaret eder.

STACK:                                 HEAP:
------------------------               --------------------------------------------
main()                                 [Car Object @ 0x101]
myCar [Adres: 0x101] ----------------> |  year = 2023                             |
------------------------               |  model = [Adres: 0xA1] -----> "Honda"    |
                                       |  color = [Adres: 0xC3] -----> "Blue"     |
                                       --------------------------------------------
                                       
Garbage Collection (GC) Mantığı:
Eski "Red" string'ini gösteren hiçbir ok (referans) kalmadıysa, Java'nın 
Garbage Collector'ü (Çöp Toplayıcısı) arka planda sessizce gelir ve "Red" 
yazısını bellekten silerek hafızayı temizler.
================================================================================

🔹 E) SOLUTION STRATEGY
- Naming Conventions: Soruda 'start_engine' (C++/Python tarzı) istenmiş ancak 
  Java'da endüstri standardı camelCase'dir. Bu yüzden metotları 'startEngine' ve 
  'paintCar' olarak yazacağız. Bu, kodu okuyan hocaya veya mühendise dili 
  profesyonelce bildiğimizi gösterir.
- Encapsulation: Özellikler (attributes) 'private' yapılacak. Sadece sınıf 
  içinden erişilebilecekler. Dışarıdan okumak için Getter metotları eklenecek.
================================================================================
*/

public class Car {

    // 1. ATTRIBUTES (State / Fields)
    // Encapsulation kuralı: Sınıfın durumu dışarıdan doğrudan değiştirilemez, private olmalı.
    private String model;
    private String color;
    private int year;

    // 2. CONSTRUCTOR (Kurucu Metot)
    // - Sınıf ile BİREBİR aynı isimde olmalıdır.
    // - Return type'ı YOKTUR (void bile yazılmaz).
    // - Parametre olarak gelen değişkenlerle, sınıfa ait (this) değişkenler eşleştirilir.
    public Car(String model, String color, int year) {
        this.model = model; // "this", o an yaratılmakta olan objeyi (Heap'teki alanı) işaret eder.
        this.color = color;
        this.year = year;
    }

    // 3. BEHAVIOR (Methods) - Soru 2
    // Motoru çalıştıran ve ekrana yazı yazdıran, geriye değer döndürmeyen (void) metot.
    public void startEngine() {
        System.out.println("Engine started.");
    }

    // 4. STATE MODIFICATION - Soru 3
    // Arabanın rengini güncelleyen ve yeni rengi (String) olarak geri döndüren metot.
    public String paintCar(String newColor) {
        this.color = newColor; // Mevcut rengi, parametre olarak gelen yeni renkle ez (Memory'de referans değişti).
        return this.color;     // Yeni rengi çağrıldığı yere gönder.
    }

    // --- EKSTRA: GETTER METOTLARI ---
    // Encapsulation yaptığımız (değişkenleri private kıldığımız) için,
    // Test sınıfında arabanın modelini okuyabilmek adına Getter'lar yazmalıyız.
    public String getModel() {
        return this.model;
    }

    public String getColor() {
        return this.color;
    }

    public int getYear() {
        return this.year;
    }
}

/*
================================================================================
🔹 F) ALTERNATIVE APPROACHES (Constructor Overloading)
Eğer galeriye bazı arabalar rengi belli olmadan (sadece şasi olarak) gelseydi, 
ikinci bir constructor yazabilirdik. Buna "Constructor Overloading" (Aşırı Yükleme) denir.

public Car(String model, int year) {
    this.model = model;
    this.year = year;
    this.color = "Unpainted"; // Default değer ataması
}
================================================================================

🔹 G) EXAM TIPS (Sınav ve Mülakat İpuçları)
- TUZAK 1: "public void Car()" şeklinde yazarsan, Java bunun bir constructor 
  olduğunu anlamaz. Normal bir metot sanır. Sınavda return type (void) EKLEME!
- TUZAK 2: "String newColor" gibi bir parametre aldığında ve onu private color'a 
  atadığında, eğer isimleri aynıysa (ikisi de color olsaydı) "this.color = color;" 
  kullanmak ZORUNDASIN. Buna "Variable Shadowing" (Değişken Gölgeleme) denir.
- MEMORY BİLGİSİ: "new" keyword'ünü gördüğün an kağıda/zihnine bir HEAP kutusu çiz.
================================================================================
*/