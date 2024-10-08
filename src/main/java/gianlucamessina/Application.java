package gianlucamessina;

import com.github.javafaker.Faker;
import gianlucamessina.dao.DocumentoDao;
import gianlucamessina.dao.PrestitoDao;
import gianlucamessina.dao.UtenteDao;
import gianlucamessina.entities.*;
import gianlucamessina.enums.Periodicita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.Locale;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliograficoInJPA");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);

        DocumentoDao docDao = new DocumentoDao(em);
        UtenteDao userDao = new UtenteDao(em);
        PrestitoDao prestDao = new PrestitoDao(em);


        //*********************************** SAVE DI LIBRI E RIVISTE**********************************

        Libro blood = new Libro(faker.book().hashCode(), faker.book().title(), 2000, 300, "Tolomeo Fabbri", faker.book().genre());
        //docDao.save(blood);


        Libro francoiseSagan = new Libro(faker.book().hashCode(), faker.book().title(), 2012, 250, faker.book().author(), faker.book().genre());
        //docDao.save(francoiseSagan);

        Libro seaHorse = new Libro(faker.book().hashCode(), faker.book().title(), 1992, 500, faker.book().author(), faker.book().genre());
        //docDao.save(seaHorse);

        Libro cabbagesKings = new Libro(faker.book().hashCode(), faker.book().title(), 1982, 395, faker.book().author(), faker.book().genre());
        //docDao.save(cabbagesKings);

        Rivista vogue = new Rivista(faker.book().hashCode(), "Vogue", 2024, 80, Periodicita.MENSILE);
        //docDao.save(vogue);

        Rivista pokemon = new Rivista(faker.book().hashCode(), "pokemon", 2020, 60, Periodicita.SETTIMANALE);
        //docDao.save(pokemon);

        Rivista birra = new Rivista(faker.book().hashCode(), faker.beer().name(), 2019, 130, Periodicita.SEMESTRALE);
        //docDao.save(birra);

        //*********************************** SAVE DI UTENTI  **********************************

        Utente eusebio = new Utente(faker.name().firstName(), faker.name().lastName(), LocalDate.now().minusYears(25));
        //userDao.save(eusebio);
        Utente olimpia = new Utente(faker.name().firstName(), faker.name().lastName(), LocalDate.now().minusYears(40));
        //userDao.save(olimpia);
        Utente umberto = new Utente(faker.name().firstName(), faker.name().lastName(), LocalDate.now().minusYears(33));
        //userDao.save(umberto);

        //*********************************** SAVE DI PRESTITI  **********************************

        Utente olimpiaFromDb = userDao.getByTesseraId(2);
        Documento vogueFromDb = docDao.getByIsbn(757660418);

        Utente eusebioFromDb = userDao.getByTesseraId(1);
        Documento seaHorseFromDb = docDao.getByIsbn(1267168782);
        //System.out.println(seaHorseFromDb);

        Prestito p1 = new Prestito(olimpiaFromDb, vogueFromDb, LocalDate.now(), LocalDate.now().plusDays(45));
        //prestDao.save(p1);

        Prestito p2 = new Prestito(eusebioFromDb, seaHorseFromDb, LocalDate.now().minusMonths(4), LocalDate.now().minusMonths(2));
        //prestDao.save(p2);

        //*********************************** RICERCA PER ANNO DI PUBBLICAZIONE  **********************************
        System.out.println("************* RICERCA PER ANNO DI PUBBLICAZIONE = 2020 *************");
        docDao.getByYearOfPublication(2020).forEach(System.out::println);


        //*********************************** RICERCA PER AUTORE  **********************************

        System.out.println("************* RICERCA LIBRO PER AUTORE *************");
        docDao.getBooksByAuthor("Tolomeo Fabbri").forEach(System.out::println);

        //*********************************** RICERCA PER TITOLO  **********************************

        System.out.println("************* RICERCA PER TITOLO *************");
        //ricerca per titolo o parte di esso case insensitive
        docDao.getByTitle("sea").forEach(System.out::println);

        //*********************************** RICERCA LISTA PRESTITI DATO ID DELLA TESSERA DELL'UTENTE  **********************************

        System.out.println("************* RICERCA PRESTITI DI UN UTENTE *************");
        System.out.println("ricerca dei prestiti dell'utente con id della carta: '1'");
        if (prestDao.getCurrentLoansByUserCardId(1).isEmpty()) {
            System.out.println("Nessun prestito in corso trovato per l'utente cercato");
        } else if (!prestDao.getCurrentLoansByUserCardId(1).isEmpty()) {
            prestDao.getCurrentLoansByUserCardId(1).forEach(System.out::println);
        }

        //*********************************** RICERCA LISTA PRESTITI SCADUTI E NON ANCORA RESTITUITI  **********************************

        System.out.println("************* RICERCA LISTA PRESTITI SCADUTI E NON ANCORA RESTITUITI *************");
        if (prestDao.getLoansExpiredAndNotReturned(LocalDate.now()).isEmpty()) {
            System.out.println("Nessun prestito scaduto e non restituito trovato");
        }
        prestDao.getLoansExpiredAndNotReturned(LocalDate.now()).forEach(System.out::println);
    }
}
