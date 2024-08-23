# Progetto-CatalogoBibliografico-inJPA

COMMENTO DEL DIAGRAMMA

ho pensato in primis ad un entità astratta che non è presente nel diagramma che viene estesa da riviste e libri, infatti
nel
diagramma i primi attributi sono praticamente uguali per questo motivo.
Per quanto riguarda le relazioni tra le tabelle le ho pensate tutte con un ManyToOne dal punto di vista dei prestiti in
quanto
un solo libro può nel corso del tempo può essere prestato molte volte, così come le riviste e dall'altro lato il
prestito
prevede un solo elemento che sia libro o rivista.
Medesimo ragionamento per gli utenti in quanto un utente può richiedere diversi prestiti, mentre un prestito non può
avere più di una persona.
