package question_set01.question_1_2_3;
/*
================================================================================
🔹 TEST & DRIVER CLASS (CarTest.java)
Bu dosya, Car.java isimli Model sınıfını test etmek için yazılmıştır.
Yazılım mühendisliğinde "Separation of Concerns" (İşlerin Ayrılığı) prensibi 
gereği, objeyi tanımlayan sınıf ile programı çalıştıran (main metodu içeren) 
sınıf her zaman birbirinden ayrı dosyalarda tutulur.
================================================================================
*/

public class CarTest {

    // Programın giriş noktası (Entry Point). 
    // Java programı çalıştırıldığında Stack'te ilk olarak main metodu için bir "Frame" açılır.
    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: OBJECT CREATION ---");
        
        // 1. SORUNUN TESTİ: Obje Yaratımı
        // Stack'te 'myDreamCar' adında bir referans değişkeni oluşturuldu.
        // Heap'te yeni bir Car objesi oluşturuldu (model="Tesla Model S", color="Black", year=2024).
        // myDreamCar referansı, Heap'teki bu objeye bağlandı.
        Car myDreamCar = new Car("Tesla Model S", "Black", 2024);
        
        // Getter metotları sayesinde private verilere güvenli (read-only) erişim sağlıyoruz.
        System.out.println("Car Model: " + myDreamCar.getModel());
        System.out.println("Car Year : " + myDreamCar.getYear());
        System.out.println("Car Color: " + myDreamCar.getColor());
        System.out.println(); // Boşluk

        System.out.println("--- STEP 2: METHOD DEFINITION (BEHAVIOR) ---");
        
        // 2. SORUNUN TESTİ: Davranış Tetikleme
        // Stack'teki myDreamCar kumandasına basıyoruz. Heap'teki objenin startEngine metodu çalışıyor.
        myDreamCar.startEngine();
        System.out.println(); // Boşluk

        System.out.println("--- STEP 3: ATTRIBUTE MODIFICATION (STATE CHANGE) ---");
        
        // 3. SORUNUN TESTİ: Durum Değişikliği ve Memory Update
        System.out.println("I want to change the color of my car. Current color: " + myDreamCar.getColor());
        
        // paintCar() metodu çağrılıyor. Parametre olarak "Midnight Red" gönderiliyor.
        // Heap belleğindeki "Black" string'inin referansı kopuyor (Garbage Collector silecek).
        // Yerine "Midnight Red" string'inin referansı bağlanıyor.
        // Metot, güncel rengi return ediyor ve biz de bunu 'newColorResult' değişkenine alıyoruz.
        String newColorResult = myDreamCar.paintCar("Midnight Red");
        
        // Sonucu konsola yazdırıyoruz.
        System.out.println("Car successfully painted! Returned value from method: " + newColorResult);
        
        // Objenin gerçekten güncellenip güncellenmediğini Getter ile tekrar kontrol edelim:
        System.out.println("Verifying in memory... Current color is now: " + myDreamCar.getColor());
        
        /*
        BURADA MEMORY'DE NE OLDU? (FINAL STATE)
        STACK:                                 HEAP:
        main()                                 [Car Object]
        myDreamCar --------------------------> model ---> "Tesla Model S"
        newColorResult -----> "Midnight Red"   year  ---> 2024
                                               color ---> "Midnight Red"
        */
    }
}