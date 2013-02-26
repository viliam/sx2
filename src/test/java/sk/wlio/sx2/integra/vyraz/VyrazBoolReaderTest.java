package sk.wlio.sx2.integra.vyraz;


@Deprecated
public class VyrazBoolReaderTest {
//
//
//    @Test
//    public void testVyrazBoolProvnanie()  {
//        IVyraz vyraz = citajBoolVyraz("  ahoj & 5<4 ");
//        assertEquals( "pozicia" , new Pozicia(2,0), vyraz.getPozicia());
//    }
//
//    @Test
//    public void testVyrazBoolAndOr()  {
//        IVyraz vyraz = citajBoolVyraz("  5<4 & 4>3 | 6!=4");
//        assertEquals( "pozicia" , new Pozicia(2,0), vyraz.getPozicia());
//        assertTrue( vyraz instanceof VyrazBool);
//        VyrazBool vBool = (VyrazBool) vyraz;
//        assertTrue( vBool.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool.getV2() instanceof VyrazBool);
//        assertTrue( vBool.getOp() instanceof Operator);
//        assertEquals( vBool.getOp().getSymbol(), "&");
//
//        VyrazBool vBool2 = (VyrazBool) vBool.getV2();
//        assertTrue( vBool2.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool2.getV2() instanceof VyrazPorovnanie);
//        assertEquals( vBool2.getOp().getSymbol(), "|");
//
//    }
//
//    @Test
//    public void testVyrazBoolZatvorky()  {
//        IVyraz vyraz = citajBoolVyraz("  5<4 & (4>3 || 6!=4)");
//        assertEquals( "pozicia" , new Pozicia(2,0), vyraz.getPozicia());
//        assertTrue( vyraz instanceof VyrazBool);
//        VyrazBool vBool = (VyrazBool) vyraz;
//        assertTrue( vBool.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool.getV2() instanceof VyrazVzatvorke);
//        assertTrue( vBool.getOp() instanceof Operator );
//        assertEquals( vBool.getOp().getSymbol(), "&");
//
//        VyrazVzatvorke vZatvorke = (VyrazVzatvorke) vBool.getV2();
//        assertTrue( vZatvorke.getV()  instanceof VyrazBool);
//        VyrazBool vBool2 = (VyrazBool) vZatvorke.getV();
//        assertTrue( vBool2.getV1() instanceof VyrazPorovnanie);
//        assertTrue( vBool2.getV2() instanceof VyrazPorovnanie);
//        assertEquals( vBool2.getOp().getSymbol(), "||");
//
//    }
//
//
//    private IVyraz citajBoolVyraz(String tento)  {
//        TextContext tC = new TextContext(tento);
//        VyrazBoolReader vBoolReader = new VyrazBoolReader();
//        IVyraz vyraz = vBoolReader.citaj(tC);
//
//        assertNotNull(vyraz);
//
//        return vyraz;
//    }

}
