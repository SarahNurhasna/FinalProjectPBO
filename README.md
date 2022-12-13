# FinalProjectPBO
Final Project PBO

Nama  : Sarah Nurhasna Khairunnisa

NRP   : 5025211105

Kelas : PBO B

Penjelasan Game:

Untuk final project, saya ingin mengimpelentasikan materi OOP dengan menggunakan GUI dalam membuat game dengan judul "Collect The Blue Blocks". Dalam game ini player harus mengumpulkan balok biru. Awalnya player memiliki satu balok berwarna biru yang terus bergerak dan player juga bisa mengatur arah gerak balok ke atas, bawah, kanan, dan kiri. Balok berwarna biru, merah, hijau, dan ungu akan muncul di tempat yang random dan player harus mengumpulkan balok biru. Ketika balok biru didapatkan maka balok biru lainnya akan muncul di tempat yang lain dan balok berwarna lain juga akan ikut berpindah tempat. Semakin banyak balok biru yang didapatkan maka poin akan bertambah dan balok biru juga semakin panjang. Apabila player mengambil balok dengan warna berbeda, maka game akan berakhir. Selain itu, game juga akan berakhir ketika balok biru bergerak ke luar board dan jika balok biru menabrak dirinya sendiri. Saat game over, player bisa melihat total poin yang didapatkan dan highscore sebelumnya.

Link sources: 

https://zetcode.com/javagames/

https://youtu.be/8gMd0ftWp_Y

https://codereview.stackexchange.com/questions/156187/snake-game-in-java

Aspek OOP:

1. Casting/Conversion

Pada class GamePanel saya melakukan beberapa casting dalam beberapa perhitungan,
salah satunya pada code berikut.

pada code tersebut saya melakukan casting untuk posisi koordinat y dari tulisan "score: " karena
saya melakukan pembagian dengan bilangan desimal sehingga saya melakukan casting (int) agar hasilnya
menjadi bilangan integer.

2. Constructor

Pada class GamePanel terdapat beberapa constructor salah satunya yaitu sebagai berikut.

Constructor tersebut berfungsi untuk mengecek jika balok warna biru berhasil di dapatkan, maka poin akan bertambah dan balok akan semakin panjang. Selain itu constructor ini juga menjalankan fungsi dari constructor lainnya yaitu newBlock(), newRedBlock(), newGreenBlock(), checkScore();

3. Overloading

Pada class GamePanel terdapat 2 constructor yang memiliki nama dan methods yang sama, namu
parameter yang berbeda yaitu sebagai berikut.

4. Overriding

Pada program ini saya banyak melakukan overriding class dari

5. Encapsulation

Pada project ini saya menggunakan public, private dan final, salah satunya yaitu sebagai berikut.


6. Inheritance
Pada class GamePanel saya menerapkan Inheritance pada constructor public void paintComponent(Graphics g) sebagai childclass dan memiliki methods dari superclass pada JComponent di java.swing.

7. Polymorphism

Class GameFrame memiliki subclass yaitu Class PlayGame. Selain itu saya juga sudah menerapkan polymorphism statis (overloading) dan polymorphism dinamis (overriding)

8. ArrayList 

Saya menggunakan ArrayList untuk menyimpan data highscore pemain di dalam file.

9. Exception Handling

GUI 

Interface

Abstract Class

Generics

Collection 

Input Output 








