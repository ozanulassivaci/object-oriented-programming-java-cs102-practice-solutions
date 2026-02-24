package question_set01.question_9;

/*
================================================================================
🔹 TEST & DRIVER CLASS (PersonTest.java)
Bu sınıfta, yaratılan bir objenin kendi durumunu (state) spesifik bir 
metot üzerinden nasıl değiştirdiğini (ve yaşlandığını) test edeceğiz.
================================================================================
*/

public class PersonTest {

    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: OBJECT CREATION ---");
        
        // Stack'te person referansı açılır, Heap'te yeni bir Person objesi oluşur.
        Person myPerson = new Person("Ozan", 20);
        
        System.out.println("Person created.");
        System.out.println("Name: " + myPerson.getName());
        System.out.println("Initial Age: " + myPerson.getAge());
        System.out.println();

        System.out.println("--- STEP 2: STATE MODIFICATION (BEHAVIOR CALL) ---");
        
        // Zaman geçiyor...
        System.out.println("One year later...");
        
        // haveBirthday() metodu çağrılıyor.
        // Bu metot arka planda Heap'e gidip 'age' değişkenini 21 yapacak.
        myPerson.haveBirthday();
        System.out.println();

        System.out.println("--- STEP 3: VERIFICATION ---");
        
        // Gerçekten değişip değişmediğini kontrol etmek için Getter ile tekrar okuyoruz.
        System.out.println("Verifying from memory...");
        System.out.println("Current Age: " + myPerson.getAge());
        
        /*
        FINAL MEMORY STATE:
        [ Person Object ]
        name ---> "Ozan"
        age  = 21 (Başarıyla güncellendi)
        */
    }
}