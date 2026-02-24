package question_set01.question_7_8;

/*
================================================================================
🔹 TEST & DRIVER CLASS (CircleTest.java)
Bu sınıfta Instance (nesne üzerinden çağrılan) metotlar ile Static 
(sınıf üzerinden doğrudan çağrılan) metotların çağrılış farklılıklarını test edeceğiz.
================================================================================
*/

public class CircleTest {

    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: USING STATIC CONSTANTS AND METHODS ---");
        
        // 🚨 ÖNEMLİ: Ortada henüz hiçbir 'new Circle()' yok!
        // Sınıfın tahtasına yazılmış evrensel sabiti (PI) okuyabiliyoruz.
        System.out.println("Universal PI constant: " + Circle.PI);
        
        // Soru 8'in Testi: calculateCircumference() static olduğu için,
        // ortada bir obje olmamasına rağmen hesaplama yapabiliriz.
        // Yarıçapı 10 olan hayali bir çemberin çevresini sınıf aracılığıyla hesaplatıyoruz.
        double circumferenceResult = Circle.calculateCircumference(10.0);
        System.out.println("Circumference of a circle with radius 10.0: " + circumferenceResult);
        System.out.println();

        System.out.println("--- STEP 2: CREATING AN OBJECT (INSTANCE) ---");
        
        // Şimdi Heap'te fiziksel bir obje (instance) oluşturuyoruz.
        // Yarıçapı 5.0 olan bir çember...
        Circle myCircle = new Circle(5.0);
        System.out.println("A circle object is created with radius: " + myCircle.getRadius());
        System.out.println();

        System.out.println("--- STEP 3: USING INSTANCE METHODS ---");
        
        // Soru 7'nin Testi: calculateArea() non-static (instance) bir metottur.
        // Sadece 'myCircle' objesi üzerinden (nokta operatörüyle) çağrılabilir.
        // Çünkü içerde "benim yarıçapımı kullan" (this.radius) mantığı vardır.
        double areaResult = myCircle.calculateArea();
        System.out.println("Area of myCircle object: " + areaResult);
        
        /*
        BURADA MEMORY'DE NE OLDU? (FINAL STATE)
        STACK:
        main() 
        |-- circumferenceResult = 62.8318...
        |-- myCircle = [Adres: 0x999]
        |-- areaResult = 78.5398...
        
        HEAP:
        [ Circle Object @ 0x999 ] ---> radius = 5.0
        
        METASPACE (Method Area):
        [ Circle Class ] ---> PI = 3.14159..., calculateCircumference()
        */
    }
}