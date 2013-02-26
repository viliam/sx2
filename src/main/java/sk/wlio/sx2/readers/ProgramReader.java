package sk.wlio.sx2.readers;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.rozhrania.TextReader;

public class ProgramReader implements TextReader<Program> {

    public Program citaj(TextContext tC)  {
        Program program = new Program( tC.getPozicia());

        boolean nasiel;
        do {
           nasiel = false;
           if ( tC.jePrefixDeklaraciaPremennej()) {
               program.pridajPremennu( Readers.dekPremennej().citaj( tC));
               nasiel = true;
           }

           if ( tC.jePrefixDeklaraciaPrikaz() ) {
               program.pridajPrikaz( Readers.dekPrikaz().citaj( tC));
               nasiel = true;
           }
        }  while ( nasiel);

        if (!tC.jeKoniec())
            throw SxException.create(SxExTyp.CAKAL_DEKLARACIU_PRIKAZU_ALEBO_PREMENNEJ, tC);

        return program;
    }
}