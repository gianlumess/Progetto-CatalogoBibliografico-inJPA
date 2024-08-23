package gianlucamessina.dao;

import gianlucamessina.entities.Prestito;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PrestitoDao {
    private final EntityManager em;


    public PrestitoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Prestito prestito) {
        //NEL PROCESSO DI SCRITTURA BISOGNA UTILIZZARE UNA TRANSAZIONE PER ASSICURARSI CHE AVVENGA IN SICUREZZA

        //1. chiedo all'entity manager di fornire una transazione
        EntityTransaction transaction = em.getTransaction();

        //2.avviamo la transazione
        transaction.begin();

        //3.aggiungo l'evento al persistence context
        em.persist(prestito);

        //4.concludiamo la transazione salvando l'evento nel DB
        transaction.commit();

        System.out.println("Il prestito effettuato dall'utente : " + prestito.getUtente().getNome() + " " + prestito.getUtente().getCognome() + " con numero di tessera: " + prestito.getUtente().getNumeroTessera() + " è stato salvato con successo!");
    }
}
