package question_set01.question_4;

/*
================================================================================
🔹 TEST & DRIVER CLASS (PersonTest.java)
Bu sınıf, oluşturduğumuz Person sınıfını memory'e yükleyen ve test eden sınıftır.
================================================================================
*/

public class PersonTest {

    public static void main(String[] args) {
        
        System.out.println("--- MEMORY LAYOUT TEST ---");
        
        // Stack'te 'person' referansı açıldı.
        // Heap'te Person objesi için alan açıldı.
        // Constructor, String Pool'dan "Ozan" referansını ve 21 primitive değerini objeye yazdı.
        Person person = new Person("Ozan", 21);
        
        // Doğrulama: Heap'teki objenin içine gidip verileri çekiyoruz.
        System.out.println("Person Name: " + person.getName());
        System.out.println("Person Age : " + person.getAge());
        
        /*
        Kodun burasında çalışırken RAM durumu:
        person ---------> [ Person Object ]
                          |-- name -------> "Ozan"
                          |-- age  = 21
        */
    }
}