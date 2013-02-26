package sk.wlio.sx2.integra.deklaracia;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.integra.TestAbstract;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.fail;

public class DeklaraciaTriedaReaderTest extends TestAbstract<Program> {

    public DeklaraciaTriedaReaderTest() {
        super( new ProgramReader());
    }

    @Override
    protected String[] getDobreVety() {
        return new String[] {
             " bool ahoj() { vrat 3; } cislo ahoj;"
        };
    }

    @Override
    protected String[] getChybneVety() {
        return new String[] {
            " bool ahoj() { vrat 3; } cislo ahoj { nie = 3 ;"
        };
    }
}
