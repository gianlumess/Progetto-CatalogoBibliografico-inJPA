package gianlucamessina.dao;

import gianlucamessina.entities.Documento;
import gianlucamessina.entities.Libro;
import gianlucamessina.exceptions.NotFoundExceptionLong;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

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

    public Documento getByIsbn(long isbn) {
        Documento found = em.find(Documento.class, isbn);
        if (found == null) throw new NotFoundExceptionLong(isbn);
        return found;
    }

    public void deleteByIsbn(long codiceIsbn) {
        Documento found = this.getByIsbn(codiceIsbn);

        EntityTransaction transaction = em.getTransaction();

        transaction.begin();

        em.remove(found);

        transaction.commit();

        System.out.println("La rivista/libro con codice ISBN: " + codiceIsbn + " è stato rimosso con successo!");
    }

    public List<Documento> getByYearOfPublication(int anno) {
        TypedQuery<Documento> query = em.createQuery("SELECT d FROM Documento d WHERE d.annoPubblicazione=:y", Documento.class);
        query.setParameter("y", anno);
        List<Documento> lista = query.getResultList();
        if (lista.isEmpty()) {
            System.out.println("Nessun risultato trovato per l'anno di pubblicazione: " + anno);
        }
        return lista;
    }

    public List<Libro> getBooksByAuthor(String autore) {
        TypedQuery<Libro> query = em.createQuery("SELECT b FROM Libro b WHERE b.autore=:author", Libro.class);
        query.setParameter("author", autore);
        List<Libro> libri = query.getResultList();
        if (libri.isEmpty()) {
            System.out.println("Nessun libro trovato per l'autore: " + autore);
        }
        return libri;
    }

    public List<Documento> getByTitle(String titolo) {
        TypedQuery<Documento> query = em.createQuery("SELECT d FROM Documento d WHERE LOWER(d.titolo) LIKE LOWER(:title)", Documento.class);
        query.setParameter("title", "%" + titolo + "%");
        List<Documento> lista = query.getResultList();
        if (lista.isEmpty()) {
            System.out.println("Nessun risultato trovato con il titolo: " + titolo);
        }
        return lista;
    }

    
}
