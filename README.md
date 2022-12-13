# FinalProjectPBO
Final Project PBO

Nama  : Sarah Nurhasna Khairunnisa

NRP   : 5025211105

Kelas : PBO B

## Penjelasan Game

  Untuk final project, saya ingin mengimpelentasikan materi OOP dengan menggunakan GUI dalam membuat game dengan judul "Collect The Blue Blocks". Dalam game ini player harus mengumpulkan balok biru. Awalnya player memiliki satu balok berwarna biru yang terus bergerak dan player juga bisa mengatur arah gerak balok ke atas, bawah, kanan, dan kiri. Balok berwarna biru, merah, hijau, dan ungu akan muncul di tempat yang random dan player harus mengumpulkan balok biru. Ketika balok biru didapatkan maka balok biru lainnya akan muncul di tempat yang lain dan balok berwarna lain juga akan ikut berpindah tempat. Semakin banyak balok biru yang didapatkan maka poin akan bertambah dan balok biru juga semakin panjang. Apabila player mengambil balok dengan warna berbeda, maka game akan berakhir. Selain itu, game juga akan berakhir ketika balok biru bergerak ke luar board dan jika balok biru menabrak dirinya sendiri. Saat game over, player bisa melihat total poin yang didapatkan dan highscore sebelumnya.

## Link sources

* https://zetcode.com/javagames/

* https://youtu.be/8gMd0ftWp_Y

* https://codereview.stackexchange.com/questions/156187/snake-game-in-java

## Aspek OOP

1. Casting/Conversion

Pada class GamePanel saya melakukan beberapa casting dalam beberapa perhitungan,
salah satunya pada code berikut.

![image](https://user-images.githubusercontent.com/114993457/207313482-07f4e742-da9b-4b2a-b2cb-eaccbddd1d72.png)

        g.drawString("Score: "+score, 
           (SCREEN_WIDTH - metr1.stringWidth("Score: "+score))/2, (int)(SCREEN_HEIGHT/1.6)); //casting

pada code tersebut saya melakukan casting untuk posisi koordinat y dari tulisan "score: " karena
saya melakukan pembagian dengan bilangan desimal sehingga saya melakukan casting (int) agar hasilnya
menjadi bilangan integer.

2. Constructor

Pada class GamePanel terdapat beberapa constructor salah satunya yaitu sebagai berikut.

    public void checkBlocks(){
        if( (x[0]==block_x) && (y[0]==block_y) ){
            block++;
            score++;
            newBlock();
            newRedBlock();
            newGreenBlock();
            checkScore();
        }
    }


Constructor tersebut berfungsi untuk mengecek jika balok warna biru berhasil di dapatkan, maka poin akan bertambah dan balok akan semakin panjang. Selain itu constructor ini juga menjalankan fungsi dari constructor lainnya yaitu newBlock(), newRedBlock(), newGreenBlock(), checkScore();

3. Overloading

Pada class GamePanel terdapat 2 constructor yang memiliki nama dan methods yang sama, namu
parameter yang berbeda yaitu sebagai berikut.

    public String highScore(String name){
        return name;
    }
    
    public int highScore(int total){
        return total;
    }
   

4. Overriding

Pada program ini saya banyak melakukan overriding class dari java.awt, berikut salah satunya

    public void actionPerformed(ActionEvent e){
        if(running){
            move(blockDirection); 
            checkBlocks();
            checkCollisions();
        }
        repaint();
    }

5. Encapsulation

Pada project ini saya menggunakan public, private dan final, salah satunya yaitu sebagai berikut.

    private final int x[] = new int[GAME_UNITS];
    private final int y[] = new int[GAME_UNITS];
    private int block = 1;
    private int score = 0;

6. Inheritance

Pada class GamePanel saya menerapkan Inheritance pada constructor public void paintComponent(Graphics g) sebagai childclass dan memiliki methods dari superclass pada JComponent di java.swing.

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

7. Polymorphism

Class GameFrame memiliki subclass yaitu Class PlayGame. Selain itu saya juga sudah menerapkan polymorphism statis (overloading) dan polymorphism dinamis (overriding)

    public class PlayGame extends GameFrame{
    
      public static void main(String[] args) {
          //GameFrame frame = new GameFrame(); 
        new GameFrame();
        }  
    }

8. ArrayList 

Saya menggunakan ArrayList untuk menyimpan data highscore pemain di dalam file.

    public ArrayList<Integer> HighScore = new ArrayList<>();

9. Exception Handling

Saya mengaplikasikan exception handling ketika memasukkan input data highscore ke dalam file dan juga ketika melakukan run program. Berikut salah satunya.

     @Override
      public void run(){
          while(true){
              try{
                  Thread.sleep(50);
             }
             catch(Exception e){
                 e.printStackTrace();
                  break;
              }
          }
     }

10. GUI

GUI yang saya gunakan yaitu Jframe, Jpanel dengan memanfaatkan java.util, java.awt, dan java.swing, saya menggunakan paintComponent untuk menghasilkan gambar balok dan tulisan.

    import javax.swing.*;
    import javax.swing.JPanel;
    import java.awt.*;
    import java.awt.event.*;
    import java.util.Random;
    
    g.setColor(new Color(0,191,255));
            g.fillRect(block_x, block_y, UNIT_SIZE, UNIT_SIZE);

11. Generics

Generics yang saya gunakan adalah enumeration pada class Direction yaitu sebagai berikut.

      public enum Directions {
        LEFT, 
        RIGHT, 
        UP, 
        DOWN;
    
        public boolean compatibleWith(Directions newDirection) {
            if (this.equals(LEFT) || this.equals(RIGHT)) {
                return UP.equals(newDirection) || DOWN.equals(newDirection); 
            } else {
                return LEFT.equals(newDirection) || RIGHT.equals(newDirection);
            }
        }
      }

12. Collection

Collection yang saya gunakan yaitu list menggunakan ArrayList untuk menginput dan menyimpan data high score pemain.

    public ArrayList<Integer> HighScore = new ArrayList<>();

    HighScore.add(score);

13. Input Output 

Input Output saya gunakan agar high score pemain bisa tersimpan di dalam file.

      public int GetHighScore(){
        FileReader readFile = null;
        BufferedReader reader = null;
        try{
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            return reader.read();
        }
        catch(Exception e){
            return 0;
        }
        finally{
            try{
                if(reader != null){
                   reader.close(); 
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
