package sk.wlio.sx2.unit.readers;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.unit.readers.mock.MockReaders;

public abstract class AbstractReaderTest {

    protected StringBuffer sb;
    protected MockReaders mr;

    @BeforeMethod
    public void before() {
        sb = new StringBuffer();
        Readers.recreateReaders( mr = new MockReaders(sb));
    }

    @AfterMethod
    public void after() {
        Readers.recreate();
    }
}
