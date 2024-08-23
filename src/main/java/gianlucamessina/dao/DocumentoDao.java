package gianlucamessina.dao;

import gianlucamessina.entities.Documento;
import gianlucamessina.exceptions.NotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class DocumentoDao {
    private final EntityManager em;


    public DocumentoDao(EntityManager em) {
        this.em = em;
    }

    public void save(Documento documento) {
        //NEL PROCESSO DI SCRITTURA BISOGNA UTILIZZARE UNA TRANSAZIONE PER ASSICURARSI CHE AVVENGA IN SICUREZZA

        //1. chiedo all'entity manager di fornire una transazione
        EntityTransaction transaction = em.getTransaction();

        //2.avviamo la transazione
        transaction.begin();

        //3.aggiungo l'evento al persistence context
        em.persist(documento);

        //4.concludiamo la transazione salvando l'evento nel DB
        transaction.commit();

        System.out.println("Il libro/rivista " + documento.getTitolo() + " è stato salvato con successo!");
    }

    public Documento getByIsbn(String isbn) {
        Documento found = em.find(Documento.class, isbn);
        if (found == null) throw new NotFoundException(isbn);
        return found;
    }
}
