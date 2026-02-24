package question_set01.question_10;

/*
================================================================================
🔹 A) QUESTION
(Attributes)
Create a Student class with a grades list attribute. Write a method that updates this list and
calculates the student's average grade.
================================================================================

🔹 B) QUESTION ANALYSIS
- Bu soru bizden bir objenin içinde, birden fazla veri tutabilen dinamik bir 
  yapı (List/Collection) barındırmamızı istiyor.
- Ölçülen OOP Kavramları:
  1. Object Composition (Objelerin içinde obje tutma).
  2. Collection Framework Kullanımı (ArrayList).
  3. Method Implementation (Listeye eleman ekleme ve liste üzerinde iterasyon 
     yaparak matematiksel hesaplama).
  4. Constructor Initialization (Koleksiyonların memory'de başlatılması).
================================================================================

🔹 C) THEORETICAL BACKGROUND
- Neden Array (Dizi) değil de ArrayList? Öğrencinin kaç tane not alacağı baştan 
  belli değildir. Sabit boyutlu diziler (Array) yerine, eleman eklendikçe boyutu 
  otomatik büyüyen 'ArrayList' sınıfını kullanmak en profesyonel yaklaşımdır.
- NullPointerException Tehlikesi: Sınıfın içine "private ArrayList<Double> grades;" 
  yazmak sadece kumandayı (referansı) oluşturur. Eğer constructor içinde 
  "grades = new ArrayList<>();" demezseniz, kumanda boşluğa (null) bakar. 
  Not eklemeye çalıştığınızda program çöker.
================================================================================

🔥 🔥 🔥 D) MEMORY AND RUNTIME (DEEP DIVE: REFERENCE CHAINING)

KOD:
Student s1 = new Student("Ozan");
s1.addGrade(90.5);

Bu kod çalıştığında RAM'de "Referans Zinciri" (Reference Chaining) dediğimiz 
olay gerçekleşir. Stack'ten Heap'e, Heap'teki objeden Heap'teki başka bir 
objeye oklar çizilir.

--- STEP 1: new Student("Ozan") Çağrısı ---
1. Stack'te 's1' referansı oluşur.
2. Heap'te Student objesi oluşur.
3. Constructor içinde 'grades = new ArrayList<>();' çalıştığı an, Heap'te 
   Student objesinden BAĞIMSIZ, yepyeni bir ArrayList objesi daha oluşur. 
   Student objesinin içindeki 'grades' değişkeni, bu listeyi işaret eder.

STACK:                                 HEAP:
------------------------               ------------------------------------------------
[ main() Method Frame ]                [ Student Object @ 0x111 ]
s1 = [Adres: 0x111] -----------------> private String name = [Adres: 0x222] -> "Ozan"
------------------------               private ArrayList grades = [Adres: 0x333] --+
                                                                                 |
                                       [ ArrayList Object @ 0x333 ] <------------+
                                       (Henüz içi boş, eleman yok)
                                       ------------------------------------------------

--- STEP 2: s1.addGrade(90.5) Çağrısı ---
Metot tetiklenir. Öğrenci objesi, kendi içindeki 'grades' kumandasını kullanarak 
0x333 adresindeki Listeye gider ve içine 90.5 değerini ekler.

STACK:                                 HEAP:
------------------------               ------------------------------------------------
[ main() Method Frame ]                [ Student Object @ 0x111 ]
s1 = [Adres: 0x111] -----------------> private String name = [Adres: 0x222] -> "Ozan"
------------------------               private ArrayList grades = [Adres: 0x333] --+
                                                                                 |
                                       [ ArrayList Object @ 0x333 ] <------------+
                                       | Index 0: 90.5 | (Eleman eklendi!)
                                       ------------------------------------------------
================================================================================

🔹 E) SOLUTION STRATEGY
- Import: ArrayList kullanmak için 'java.util.ArrayList' import edilmelidir.
- Soru bizden listeyi güncelleyen ve ortalamayı hesaplayan metot(lar) istiyor. 
  "Single Responsibility" (Tek Sorumluluk) prensibi gereği bunları iki ayrı 
  metot yapacağız: addGrade(double grade) ve calculateAverage().
- calculateAverage() metodu listeyi bir "for-each" döngüsü ile gezecek ve toplamı 
  eleman sayısına bölecek.
================================================================================
*/

import java.util.ArrayList;

public class Student {

    // 1. ATTRIBUTES
    private String name;
    
    // grades referansı. Sadece bir ok, henüz obje değil!
    private ArrayList<Double> grades; 

    // 2. CONSTRUCTOR
    public Student(String name) {
        this.name = name;
        
        // 🚨 KRİTİK NOKTA: Listeyi hafızada (Heap) yaratıyoruz!
        // Eğer bunu yazmazsak, 'grades' null kalır ve addGrade metodunda program çöker.
        this.grades = new ArrayList<>(); 
    }

    // 3. BEHAVIOR: Listeyi Güncelleyen Metot (Soru İstek 1)
    public void addGrade(double grade) {
        if (grade >= 0.0 && grade <= 100.0) {
            // grades referansı üzerinden ArrayList objesinin 'add' metoduna ulaşıyoruz.
            this.grades.add(grade);
            System.out.println("Grade " + grade + " added for " + this.name);
        } else {
            System.out.println("Invalid grade! Must be between 0 and 100.");
        }
    }

    // 4. BEHAVIOR: Ortalamayı Hesaplayan Metot (Soru İstek 2)
    public double calculateAverage() {
        // Eğer liste boşsa, 0'a bölme hatası (ArithmeticException) almamak için kontrol ediyoruz.
        if (this.grades.isEmpty()) {
            System.out.println("No grades available to calculate average.");
            return 0.0;
        }

        double totalSum = 0;
        
        // For-each döngüsü: 'grades' listesinin içindeki her bir 'grade' için dön.
        for (double grade : this.grades) {
            totalSum += grade; // totalSum = totalSum + grade;
        }

        // Ortalama = Toplam Not / Not Sayısı
        // grades.size() metodu listenin kaç elemanlı olduğunu döndürür.
        return totalSum / this.grades.size();
    }

    // 5. GETTERS
    public String getName() {
        return this.name;
    }

    // Tüm listeyi görmek isteyenler için Getter
    public ArrayList<Double> getGrades() {
        return this.grades;
    }
}

/*
================================================================================
🔹 F) EXAM TIPS (Sınav ve Mülakat İpuçları)
- TUZAK 1 (NullPointerException): Sınavda kağıda kod yazarken, constructor 
  içinde "this.grades = new ArrayList<>();" demeyi unutursan hoca direkt puan kırar. 
  Çünkü o liste hafızada oluşmadan içine not ekleyemezsin.
- TUZAK 2 (Division by Zero): Ortalama hesaplarken, eğer öğrencinin hiç notu yoksa 
  listenin boyutu (size) 0'dır. Bir sayıyı 0'a bölersen program çöker (veya NaN döner). 
  Bu yüzden calculateAverage metodunun en başına "isEmpty()" kontrolü koymak 
  seni standart bir öğrenciden ayırıp "iyi bir mühendis" yapar.
================================================================================
*/