import java.util.ArrayList;

/**
 * The type Shop.
 */
public class Shop {
    String name;
    int umsatz;
    ArrayList<Book> books;

    /**
     * Instantiates a new Shop.
     *
     * @param name    represents the name of the shop
     * @param books   represents the books in the shop
     * @param umsatz  represents the sales volume of the shop
     */
    public Shop(String name, ArrayList<Book> books, int umsatz) {
        this.name = name;
        this.books = books;
        this.umsatz = umsatz;
    }

    /**
     * Selling a specific book from a shop to a customer
     *
     * @param client represents the client who will buy the book
     * @param book   represents the book to buy
     * @throws Exception the exception
     */
    public void verkauf(Customer client, Book book) throws Exception {
        Exception e1 = new Exception("Die Kunde hat nicht genügend Geld!");
        Exception e2 = new Exception("Das Buch ist nicht verfügbar in der Bücherei!");
            if (!books.contains(book))
                throw e2;
            else if (client.geldSumme < book.preis)
                throw e1;
            else{
                books.remove(book);
                client.books.add(book);
                umsatz += book.preis;
            }
    }

    /**
     * Filtering books
     *
     * @param books represents the books to filter
     * @return all books with genre: Adventure from books
     */
    public ArrayList<Book> filtern(ArrayList<Book> books) {
        ArrayList<Book> adventureBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.genre.equals("Adventure")) {
                adventureBooks.add(book);
            }
        }
        return adventureBooks;
    }

    /**
     * Remove duplicates.
     *
     * @param books represents the books after removing duplicates
     */
    public void entfernenVonDuplikaten(ArrayList<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = i + 1; j < books.size(); j++) {
                if (books.get(i).genre.equals(books.get(j).genre) &&
                        books.get(i).titel.equals(books.get(j).titel) &&
                        books.get(i).preis == books.get(j).preis &&
                        books.get(i).seitenZahl == books.get(j).seitenZahl) {
                    books.remove(books.get(j));
                }
            }
        }
    }

    /**
     * Vergleichen boolean.
     *
     * @param shop1 represents one of the shops
     * @param shop2 represents one of the shops
     * @return true if shop1 equals shop2 else false
     */
    public boolean vergleichen(Shop shop1, Shop shop2) {
       entfernenVonDuplikaten(shop1.books);
       entfernenVonDuplikaten(shop2.books);
        if (shop1.books.size() != shop2.books.size()) return false;
        for (int i = 0; i < shop1.books.size(); i++) {
            for (int j = 0; j < shop2.books.size(); j++) {
                if (shop1.books.get(i) == shop2.books.get(j)) break;
                if (j == shop2.books.size()-1 && !shop1.books.get(i).ISBN_13.equals(shop2.books.get(j).ISBN_13) &&
                        !shop1.books.get(i).genre.equals(shop2.books.get(j).genre) &&
                        !shop1.books.get(i).titel.equals(shop2.books.get(j).titel) &&
                        shop1.books.get(i).preis != shop2.books.get(j).preis &&
                        shop1.books.get(i).seitenZahl != shop2.books.get(j).seitenZahl){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * A shop stocks new books
     *
     * @param books represents all the book in the library
     * @param book  represents the book to add to the library
     */
    public void hinzufuegen(ArrayList<Book> books, Book book) {
        if (isISBN13(book.ISBN_13)) {
            books.add(book);
        }
    }

    /**
     * Is isbn 13 boolean.
     *
     * @param s represents the ISBN_13 of the book
     * @return true if ISBN_13 is valid else false
     */
    boolean isISBN13(String s) {
        String ss = s.replaceAll("[^\\d]", "");
        if (ss.length() != 13)
            return false;
        int sum = 0;
        for (int i = 0; i < ss.length() - 1; ++i) {
            int i1 = Integer.parseInt(String.valueOf(ss.charAt(i)));
            if (i % 2 == 0)
                sum += i1;
            else
                sum += 3 * i1;
        }
        return Integer.parseInt(String.valueOf(ss.charAt(12))) == (10 - sum % 10) || (sum % 10) == 0;
    }
}
