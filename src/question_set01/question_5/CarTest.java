package question_set01.question_5;

/*
================================================================================
🔹 TEST & DRIVER CLASS (CarTest.java)
Bu sınıfta Encapsulation kuralının hayatımızı nasıl kurtardığını test edeceğiz.
Setter metodunun içindeki "validation" (doğrulama) sayesinde verimizin 
nasıl güvende kaldığını göreceğiz.
================================================================================
*/

public class CarTest {

    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: OBJECT CREATION ---");
        
        // Obje hafızada (Heap) yaratılıyor. Constructor kilometreyi default olarak 0 atadı.
        Car myCar = new Car("Ford Mustang", "Blue", 2023);
        
        // Getter kullanarak başlangıç değerini okuyalım.
        System.out.println("Initial Mileage: " + myCar.getMileage() + " km");
        System.out.println();

        System.out.println("--- STEP 2: USING SETTER (VALID DATA) ---");
        
        // Arabayla biraz yol yaptık, kilometreyi güncelliyoruz.
        // Bu çağrı Stack'te yeni bir frame açar, kontrolü yapar ve Heap'i günceller.
        myCar.setMileage(5000); 
        System.out.println("Current Mileage is now: " + myCar.getMileage() + " km");
        System.out.println();

        System.out.println("--- STEP 3: THE POWER OF ENCAPSULATION (INVALID DATA) ---");
        
        // 🚨 Kötü niyetli biri arabayı satmadan önce kilometreyi düşürmeye çalışıyor!
        // Eğer değişken "public" olsaydı: myCar.mileage = 1000; yazarak bunu kolayca yapardı.
        // Ancak private olduğu için Setter'dan geçmek zorunda:
        System.out.println("Attempting to roll back the odometer to 1000 km...");
        myCar.setMileage(1000); 
        
        // Setter içindeki "if (mileage >= this.mileage)" bloğu sayesinde saldırı engellendi.
        System.out.println("Final Verification. Mileage remains: " + myCar.getMileage() + " km");
        
        /*
        FINAL MEMORY STATE (HEAP):
        [ Car Object ]
        model = "Ford Mustang"
        mileage = 5000 (1000 yapılmasına izin verilmedi!)
        */
    }
}