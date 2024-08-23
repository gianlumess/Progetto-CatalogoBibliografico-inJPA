package gianlucamessina.dao;

import gianlucamessina.entities.Utente;
import gianlucamessina.exceptions.NotFoundExceptionLong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDao {
    private final EntityManager em;


    public UtenteDao(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente) {
        //NEL PROCESSO DI SCRITTURA BISOGNA UTILIZZARE UNA TRANSAZIONE PER ASSICURARSI CHE AVVENGA IN SICUREZZA

        //1. chiedo all'entity manager di fornire una transazione
        EntityTransaction transaction = em.getTransaction();

        //2.avviamo la transazione
        transaction.begin();

        //3.aggiungo l'evento al persistence context
        em.persist(utente);

        //4.concludiamo la transazione salvando l'evento nel DB
        transaction.commit();

        System.out.println("L'utente:  " + utente.getNome() + " " + utente.getCognome() + " è stato salvato con successo!");
    }

    public Utente getByTesseraId(long tesseraId) {
        Utente found = em.find(Utente.class, tesseraId);
        if (found == null) throw new NotFoundExceptionLong(tesseraId);
        return found;
    }
}
