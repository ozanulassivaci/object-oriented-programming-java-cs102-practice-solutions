package src.question_set01.question_1_2_3.without_getter_setter;

/*
================================================================================
🔹 TEST & DRIVER CLASS (CarTest.java)
Bu sınıfta, Car objesinin 'public' olan özelliklerine GETTER olmadan 
doğrudan (direct access) nasıl eriştiğimizi ve bu durumun yaratabileceği 
tehlikeleri (yanlış veri ataması) göreceğiz.
================================================================================
*/

public class CarTest {

    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: OBJECT CREATION ---");
        
        // Obje hafızada (Heap) yaratılıyor.
        Car myDreamCar = new Car("Porsche 911", "Silver", 1974);
        
        // 🚨 ÖNEMLİ FARK: GETTER KULLANILMIYOR!
        // Değişkenler public olduğu için doğrudan nokta (.) ile içlerindeki veriyi okuyabiliriz.
        System.out.println("Car Model: " + myDreamCar.model);
        System.out.println("Car Year : " + myDreamCar.year);
        System.out.println("Car Color: " + myDreamCar.color);
        System.out.println();

        System.out.println("--- STEP 2: BEHAVIOR & METHOD USE ---");
        
        myDreamCar.startEngine();
        System.out.println();

        System.out.println("--- STEP 3: ATTRIBUTE MODIFICATION ---");
        
        // Metot kullanarak rengi değiştirme (Sorunun bizden istediği)
        String newColorResult = myDreamCar.paintCar("Yellow");
        System.out.println("Car painted via method. New color: " + newColorResult);
        
        // Metot kullanmadan, doğrudan erişimle rengi değiştirme (Public olduğu için mümkün)
        myDreamCar.color = "Neon Green";
        System.out.println("Car painted via direct access! New color: " + myDreamCar.color);
        System.out.println();

        System.out.println("--- STEP 4: THE DANGER OF NO ENCAPSULATION ---");
        
        // 🚨 ENCAPSULATION OLMAMASININ TEHLİKESİ:
        // Herhangi birisi dışarıdan objenin kritik bir verisini mantıksız bir şekilde değiştirebilir.
        // Heap bellekteki "year" kutucuğunun içine doğrudan müdahale ediyoruz.
        myDreamCar.year = -1500; 
        
        System.out.println("Someone hacked the car! The year is now: " + myDreamCar.year);
        // Çıktı: -1500 olacak. Eğer setter kullansaydık, metot içine bir "if" 
        // koyarak bu mantıksız değişikliği engelleyebilirdik.
    }
}