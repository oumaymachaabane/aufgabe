/**
 * The type Book.
 */
public class Book {
    String titel;
    double preis;
    int seitenZahl;
    String genre;
    String ISBN_13;

    /**
     * Instantiates a new Book.
     *
     * @param titel      represents the titel of the book
     * @param preis      represents the price of the book
     * @param seitenZahl represents the number of pages of the book
     * @param genre      represents the genre of the book
     */
    public Book(String titel,double preis,int seitenZahl,String genre){
        this.titel=titel;
        this.preis=preis;
        this.seitenZahl=seitenZahl;
        this.genre=genre;
    }
}
