package gianlucamessina;

import com.github.javafaker.Faker;
import gianlucamessina.dao.DocumentoDao;
import gianlucamessina.entities.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Locale;

public class Application {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("catalogoBibliograficoInJPA");

    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        Faker faker = new Faker(Locale.ITALY);

        DocumentoDao docDao = new DocumentoDao(em);
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

    }
}
