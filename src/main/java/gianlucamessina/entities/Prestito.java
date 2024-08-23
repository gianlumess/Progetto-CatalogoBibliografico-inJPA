package gianlucamessina.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prestiti")
public class Prestito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "numero_tessera_utente")
    private Utente utente;
    @ManyToOne
    @JoinColumn(name = "codice_isbn_elemento_prestato")
    private Documento elementoPrestato;
    @Column(name = "data_inizio_prestito")
    private LocalDate inizioPrestito;
    @Column(name = "data_restituzione_prevista")
    private LocalDate RestituzionePrevista;
    @Column(name = "data_restituzione_effettiva")
    private LocalDate restituzioneEffettiva;

    public Prestito() {
    }

    public Prestito(Utente utente, Documento elementoPrestato, LocalDate inizioPrestito, LocalDate restituzioneEffettiva) {
        this.utente = utente;
        this.elementoPrestato = elementoPrestato;
        this.inizioPrestito = inizioPrestito;
        this.RestituzionePrevista = inizioPrestito.plusDays(30);
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    public long getId() {
        return id;
    }


    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Documento getElementoPrestato() {
        return elementoPrestato;
    }

    public void setElementoPrestato(Documento elementoPrestato) {
        this.elementoPrestato = elementoPrestato;
    }

    public LocalDate getInizioPrestito() {
        return inizioPrestito;
    }

    public void setInizioPrestito(LocalDate inizioPrestito) {
        this.inizioPrestito = inizioPrestito;
    }

    public LocalDate getRestituzionePrevista() {
        return RestituzionePrevista;
    }


    public LocalDate getRestituzioneEffettiva() {
        return restituzioneEffettiva;
    }

    public void setRestituzioneEffettiva(LocalDate restituzioneEffettiva) {
        this.restituzioneEffettiva = restituzioneEffettiva;
    }

    @Override
    public String toString() {
        return "Prestito{" +
                "id=" + id +
                ", utente=" + utente +
                ", elementoPrestato=" + elementoPrestato +
                ", inizioPrestito=" + inizioPrestito +
                ", RestituzionePrevista=" + RestituzionePrevista +
                ", restituzioneEffettiva=" + restituzioneEffettiva +
                '}';
    }
}
