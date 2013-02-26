package sk.wlio.sx2.unit.readers.vyraz;

import sk.wlio.sx2.unit.readers.AbstractReaderTest;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

@Deprecated
public class VyrazBoolReaderTest extends AbstractReaderTest {

//    @Test
//    @Ignore
//    public void testVyrazVzatvorke()  {
//        new TestTemplate<IVyraz>(sb, new VyrazBoolReader()) {
//            @Override
//            public void nastavReader() {
//                mr.vrzVzatvorke().setPosun(new Pozicia(3, 0));
//                mr.vrzVzatvorke().setVystup(new TestVyraz(Enums.VyrazTyp.NEURCENY));
//            }
//        }.run("(a)", "vrzZatvorka;");
//    }
//
//    @Test
//    @Ignore
//    public void testVyrazPorovanie()   {
//        new TestTemplate<IVyraz>(sb, new VyrazBoolReader()) {
//            @Override
//            public void nastavReader() {
//                mr.vrzAritm().setPosun(new Pozicia(1, 0), new Pozicia(2,0));
//                mr.vrzAritm().setVystup(new TestVyraz());
//                mr.opPorovnanie().setPosun(new Pozicia(1, 0));
//            }
//        }.run("a>33", "vrzAritm;opPorovnania;vrzAritm;");
//    }
//
//    @Test
//    @Ignore
//    public void testVyrazJednoduchy()   {
//        new TestTemplate<IVyraz>(sb, new VyrazBoolReader()) {
//            @Override
//            public void nastavReader() {
//                mr.vrzAritm().setPosun(new Pozicia(1, 0) );
//                mr.vrzAritm().setVystup(new TestVyraz( Enums.VyrazTyp.NEURCENY));
//            }
//        }.run("a", "vrzAritm;");
//    }
//
//
//    @Test
//    @Ignore
//    public void testVyraz()  {
//        new TestTemplate<IVyraz>(sb, new VyrazBoolReader()) {
//            @Override
//            public void nastavReader() {
//                mr.vrzAritm().setPosun(new Pozicia(1, 0), new Pozicia(2,0));
//                mr.vrzAritm().setVystup(new TestVyraz(), new TestVyraz());
//                mr.opPorovnanie().setPosun(new Pozicia(1, 0));
//                mr.opBool().setPosun(new Pozicia(1, 0));
//                mr.vrzVzatvorke().setPosun(new Pozicia(6, 0));
//                mr.vrzVzatvorke().setVystup( new TestVyraz(Enums.VyrazTyp.NEURCENY));
//            }
//        }.run("a>33&(ahoj)", "vrzAritm;opPorovnania;vrzAritm;opBool;vrzZatvorka;");
//    }
//
//    @Test
//    @Ignore
//    public void testVyrazCakalKorektnyKoniec() {
//        TextContext tC = new TextContext("a>33&(ahoj)aa");
//        mr.vrzAritm().setPosun(new Pozicia(1, 0), new Pozicia(2,0));
//        mr.vrzAritm().setVystup(new TestVyraz(), new TestVyraz());
//        mr.opPorovnanie().setPosun(new Pozicia(1, 0));
//        mr.opBool().setPosun(new Pozicia(1, 0));
//        mr.vrzVzatvorke().setPosun(new Pozicia(6, 0));
//        mr.vrzVzatvorke().setVystup(new TestVyraz( Enums.VyrazTyp.NEURCENY));
//        try {
//            new VyrazBoolReader().citaj(tC);
//            fail();
//        } catch (SxException e) {
//            assertEquals(SxExTyp.CAKAL_OPERATOR, e.getTyp());
//            assertEquals("vrzAritm;opPorovnania;vrzAritm;opBool;vrzZatvorka;", sb.toString());
//        }
//    }

}
