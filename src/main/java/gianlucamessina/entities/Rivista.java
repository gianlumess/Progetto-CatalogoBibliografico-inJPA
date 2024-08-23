package gianlucamessina.entities;

import gianlucamessina.enums.Periodicita;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "riviste")
public class Rivista extends Documento {
    private Periodicita periodicita;

    public Rivista() {
    }

    public Rivista(long codiceIsbn, String titolo, int annoPubblicazione, int numeroPagine, Periodicita periodicita) {
        super(codiceIsbn, titolo, annoPubblicazione, numeroPagine);
        this.periodicita = periodicita;
    }

    public Periodicita getPeriodicita() {
        return periodicita;
    }

    public void setPeriodicita(Periodicita periodicita) {
        this.periodicita = periodicita;
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "periodicita=" + periodicita +
                '}';
    }
}
