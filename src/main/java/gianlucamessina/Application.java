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
        System.out.println("Hello World!");

        //*********************************** SAVE DI LIBRI E RIVISTE**********************************

        Libro downToASunlessSea = new Libro(faker.book().hashCode(), faker.book().title(), 2000, 300, faker.book().author(), faker.book().genre());
        //docDao.save(downToASunlessSea);

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
        Documento vogueFromDb = docDao.getByIsbn("757660418");

        Prestito p1 = new Prestito(olimpiaFromDb, vogueFromDb, LocalDate.now(), LocalDate.now().plusDays(45));
        //prestDao.save(p1);

        
    }
}
