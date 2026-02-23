package src.question_set01.question_1_2_3.without_getter_setter;

/*
================================================================================
🔹 A) QUESTION
(Same as previous: Create Car class with model, color, year. Add constructor, 
startEngine() and paintCar() methods. NO ENCAPSULATION / NO GETTERS-SETTERS).
================================================================================

🔹 B) QUESTION ANALYSIS
- Bu çözümde OOP'nin en temel direklerinden biri olan "Encapsulation" (Kapsülleme) 
  bilerek TERK EDİLMİŞTİR.
- Amacımız: Verilere (attributes) doğrudan erişimin (direct access) nasıl çalıştığını 
  ve bellek (memory) üzerinde metotlar olmadan nasıl değişiklik yapıldığını anlamaktır.
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Public vs Private Access Modifiers: 
  Bir değişkene 'public' (veya hiçbir şey yazmayıp default) erişim belirleyicisi 
  verirseniz, bu değişkenlere projedeki herhangi bir sınıftan doğrudan "objeAdi.degiskenAdi" 
  şeklinde ulaşılabilir ve değerleri değiştirilebilir.
- Kapsüllemesiz Model (Anemic Domain Model): Değişkenlerin tamamen dışarıya açık 
  olduğu, veri güvenliğinin olmadığı sınıflardır. Gerçek hayatta bir arabanın kaputunu 
  açık bırakıp, yoldan geçen herkesin motor ayarlarına dokunmasına izin vermek gibidir.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE)
Değişkenlerin public veya private olması, objenin HEAP bellekteki yerleşimini (layout) 
DEĞİŞTİRMEZ. Obje yine aynı boyuttadır, değişkenler yine aynı şekilde tutulur.
Değişen tek şey, STACK'teki referansın bu değişkenlere ULAŞMA YETKİSİDİR (Access Control).

DETAILED MEMORY LAYOUT:

STEP 1: Car c1 = new Car("BMW", "White", 2022);
(Heap'te obje yaratılır, referans Stack'te tutulur. Private versiyonla tamamen aynıdır.)

STACK:                                 HEAP:
------------------------               --------------------------------------------
main()                                 [Car Object @ 0x555]
c1 [Adres: 0x555] -------------------> |  (public) year = 2022                    |
------------------------               |  (public) model = [Adres: 0xAA] -> "BMW" |
                                       |  (public) color = [Adres: 0xBB] -> "White"|
                                       --------------------------------------------

STEP 2: DIRECT ACCESS (Doğrudan Erişim) -> c1.year = -500;
İşte kapsülleme yapmamanın tehlikesi burada başlar!
Eğer setter metodumuz olsaydı içine "if(year < 1886) { hata ver }" yazabilirdik. 
Ancak public olduğu için, Stack'teki referans doğrudan Heap'teki değeri ezer.

STACK:                                 HEAP:
------------------------               --------------------------------------------
main()                                 [Car Object @ 0x555]
c1 [Adres: 0x555] -------------------> |  (public) year = -500  <-- MANTIK HATASI!|
------------------------               |  (public) model = [Adres: 0xAA] -> "BMW" |
                                       |  (public) color = [Adres: 0xBB] -> "White"|
                                       --------------------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Değişkenler 'public' olarak tanımlanacak.
- Constructor, startEngine ve paintCar metotları aynı kalacak.
- Test sınıfında okuma işlemleri metot (getter) çağrısı ile değil, doğrudan 
  alanlara (fields) erişilerek yapılacak.
================================================================================
*/

public class Car {

    // 1. ATTRIBUTES (Public / Direct Access)
    // DİKKAT: Gerçek projelerde veri güvenliğini bozduğu için bu yöntem tercih EDİLMEZ.
    public String model;
    public String color;
    public int year;

    // 2. CONSTRUCTOR
    public Car(String model, String color, int year) {
        this.model = model; 
        this.color = color;
        this.year = year;
    }

    // 3. BEHAVIOR
    public void startEngine() {
        System.out.println("Engine started. Vroom vroom!");
    }

    // 4. STATE MODIFICATION
    public String paintCar(String newColor) {
        this.color = newColor; 
        return this.color;     
    }
}

/*
================================================================================
🔹 F) EXAM TIPS (Sınav ve Mülakat İpuçları)
- MÜLAKAT SORUSU: "Değişkenleri neden public yapıp doğrudan erişmiyoruz da, 
  private yapıp getter/setter yazarak kodu uzatıyoruz?"
  CEVAP: "Çünkü public yaparsak (direct access), nesnenin durumunu (state) kontrol 
  edemeyiz. Biri arabanın yılına negatif değer atayabilir veya bir banka hesabı 
  objesinin bakiyesini dışarıdan direkt sıfırlayabilir. Encapsulation, veriyi 
  doğrulama (validation) imkanı sunar."
================================================================================
*/