import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The type Shop test.
 */
class ShopTest {

    /**
     * Test for methode Verkauf.
     *
     * @throws Exception the exception
     */
    @Test
    void verkauf() throws Exception{
        Customer client=new Customer();
        client.name="Oumayma";
        Book book=new Book("Summer",5,423,"Fantasy");
        Shop shop=new Shop("",new ArrayList<>(),3000);
        assertThrows(Exception.class , () -> {shop.verkauf(client,book);},"Das Buch ist nicht verfügbar in der Bücherei!");

        client.geldSumme=0;
        shop.books.add(book);
        assertThrows(Exception.class , () -> {shop.verkauf(client,book);},"Die Kunde hat nicht genügend Geld!");

        client.geldSumme=50;
        assertEquals(1,shop.books.size());
        assertEquals(shop.books.get(0),book);
        assertEquals(0,client.books.size());
        shop.verkauf(client,book);
        assertEquals(0,shop.books.size());
        assertEquals(3005,shop.umsatz);
        assertEquals(1,client.books.size());
        assertEquals(book,client.books.get(0));
    }

    /**
     * Test for methode Filtern.
     */
    @Test
    void filtern() {
        Shop shop=new Shop("",new ArrayList<>(),4500);
        assertEquals(shop.books,shop.filtern(shop.books));
        Book ataro=new Book("ataro",5,400,"Fantasy");
        Book alix=new Book("alix",5,400,"Fantasy");
        Book sturmwelten=new Book("sturmwelten",5,400,"Adventure");
        Book erebos=new Book("erebos",5,400,"Adventure");
        Book spare=new Book("spare",5,400,"Biography");
        Book surrender=new Book("surrender",5,400,"Biography");
        Book maus=new Book("maus",5,400,"Comic");
        Book squire=new Book("squire",5,400,"Comic");
          ArrayList<Book> Books = new ArrayList<>(
                  Arrays.asList(ataro,alix,sturmwelten,erebos,spare,surrender,maus,squire));
         shop=new Shop("",Books,4500);


        assertEquals(8,shop.books.size());
        assertEquals(2,shop.filtern(shop.books).size());
        assertNotEquals(shop.books,shop.filtern(shop.books));
        assertEquals(sturmwelten,shop.filtern(shop.books).get(0));
        assertEquals(erebos,shop.filtern(shop.books).get(1));
    }

    /**
     * Test for methode Entfernen von duplikaten.
     */
    @Test
    void entfernenVonDuplikaten() {
        Book book1=new Book("ataro",3,260,"Fantasy");
        Book book2=new Book("erebos",4.6,315,"Adventure");
        Book book3=new Book("ataro",3,260,"Fantasy");
        Book book4=new Book("erebos",4.6,315,"Adventure");
        ArrayList<Book> Books = new ArrayList<>(
                Arrays.asList(book1,book2,book3,book4));
        Shop shop=new Shop("",Books,3050);

        assertEquals(4,shop.books.size());
        assertEquals(book1,shop.books.get(0));
        assertEquals(book2,shop.books.get(1));
        assertEquals(book3,shop.books.get(2));
        assertEquals(book4,shop.books.get(3));
        shop.entfernenVonDuplikaten(shop.books);
        assertEquals(2,shop.books.size());
        assertEquals(book1,shop.books.get(0));
        assertEquals(book2,shop.books.get(1));

        shop.entfernenVonDuplikaten(shop.books);
        assertEquals(2,shop.books.size());
        assertEquals(book1,shop.books.get(0));
        assertEquals(book2,shop.books.get(1));
    }

    /**
     * Test for methode Vergleichen.
     */
    @Test
    void vergleichen() {
        Book book1=new Book("ataro",3,260,"Fantasy");
        Book book2=new Book("erebos",4.6,315,"Adventure");
        Book book3=new Book("ataro",3,260,"Fantasy");
        Book book4=new Book("erebos",4.6,315,"Adventure");
        Book book5=new Book("alix",3,260,"Fantasy");

        ArrayList<Book> thaliaBooks = new ArrayList<>(
                        Arrays.asList(book1,book2,book5));
        Shop shop1=new Shop("Thalia",thaliaBooks,3000);

        ArrayList<Book> mayerscheBooks = new ArrayList<>(
                Arrays.asList(book4,book3));
        Shop shop2=new Shop("Mayersche",mayerscheBooks,4750);
        assertFalse(shop1.vergleichen(shop1,shop2));

        Book book6=new Book("alix",3,260,"Fantasy");
        shop2.books.add(book6);

        assertTrue(shop1.vergleichen(shop1,shop2));

        Book book7=new Book("ataro",3,260,"Fantasy");
        thaliaBooks.add(book7);
        assertTrue(shop1.vergleichen(shop1,shop2));
    }

    /**
     * Test for methode Hinzufuegen.
     */
    @Test
    void hinzufuegen(){
        Book book1=new Book("ataro",3,260,"Fantasy");
        Book book2=new Book("ataro",3,260,"Fantasy");
        Book book3=new Book("ataro",3,260,"Fantasy");
        ArrayList<Book> Books = new ArrayList<>(
                Arrays.asList(book1,book2,book3));
        Shop shop=new Shop("",Books,3050);

        Book book4=new Book("ataro",3,260,"Fantasy");
        book4.ISBN_13="978-3608963762";
        shop.hinzufuegen(shop.books,book4);
        assertEquals(4,shop.books.size());
        Book book5=new Book("erebos",4.6,315,"Adventure");
        book5.ISBN_13="978-3442267747";
        shop.hinzufuegen(shop.books,book5);
        Book book6=new Book("ataro",3,260,"Fantasy");
        book6.ISBN_13="978-758245159";
        shop.hinzufuegen(shop.books,book6);
        Book book7=new Book("erebos",4.6,315,"Adventure");
        book7.ISBN_13="978-3841335180";
        shop.hinzufuegen(shop.books,book7);
        assertEquals(5,shop.books.size());
        Book book8=new Book("alix",3,260,"Fantasy");
        book8.ISBN_13="978-3442267819";
        shop.hinzufuegen(shop.books,book8);
        assertEquals(5,shop.books.size());

    }

}