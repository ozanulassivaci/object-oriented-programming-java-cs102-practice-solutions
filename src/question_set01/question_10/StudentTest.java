package question_set01.question_10;

/*
================================================================================
🔹 TEST & DRIVER CLASS (StudentTest.java)
Bu sınıfta, Student objesinin ve onun içindeki ArrayList objesinin hafızadaki 
etkileşimini test edeceğiz. Notlar ekleyip, matematiksel işlemlerin doğruluğunu 
onaylayacağız.
================================================================================
*/

public class StudentTest {

    public static void main(String[] args) {
        
        System.out.println("--- STEP 1: OBJECT AND LIST INITIALIZATION ---");
        
        // Stack'te 'student' referansı açıldı.
        // Heap'te Student objesi ve onun içinde yepyeni, boş bir ArrayList oluştu.
        Student student = new Student("Ozan Ulaş Sıvacı");
        System.out.println("Student created: " + student.getName());
        
        // Listede henüz hiç not yokken ortalama hesaplamayı deniyoruz (Zero division test).
        System.out.println("Initial Average: " + student.calculateAverage());
        System.out.println();

        System.out.println("--- STEP 2: UPDATING THE LIST (ADDING GRADES) ---");
        
        // Heap'teki ArrayList objesine veri ekleniyor.
        // student referansı üzerinden ArrayList'in içine gidip değer yazıyoruz.
        student.addGrade(95.5);
        student.addGrade(90.0);
        student.addGrade(88.5);
        
        // Hatalı veri testi (Validation)
        student.addGrade(150.0); // Bu eklenmeyecek, hata mesajı verecek.
        System.out.println();

        System.out.println("--- STEP 3: CALCULATING AVERAGE ---");
        
        // ArrayList'in içindeki veriler (95.5, 90.0, 88.5) toplanıp 3'e bölünecek.
        double avg = student.calculateAverage();
        System.out.println(student.getName() + "'s Final Average: " + avg);
        System.out.println("All grades: " + student.getGrades());

        /*
        FINAL MEMORY STATE (HEAP):
        [ Student Object ]
        name   = "Ozan Ulaş Sıvacı"
        grades = [Adres: 0x999] -----------> [ ArrayList Object ]
                                             | Index 0: 95.5 |
                                             | Index 1: 90.0 |
                                             | Index 2: 88.5 |
        */
    }
}