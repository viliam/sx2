package sk.wlio.sx2.integra.visitors;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.readers.ProgramReader;
import sk.wlio.sx2.visitors.DeklaracieVisitor;
import sk.wlio.sx2.integra.TestAbstract;

@Deprecated
public class DeklaracieVisitorTest {

    class DeklaracieVisitorTestImpl extends TestAbstract<Program>  {

        public DeklaracieVisitorTestImpl() {
            super(new ProgramReader(), new TestVisitor<Program>() {
                public void visit(TextContext tC, Program slovo) {
                    DeklaracieVisitor visitor = new DeklaracieVisitor();
                    visitor.visit( slovo);
                }
            });
        }

        @Override
        protected String[] getDobreVety() {
            return new String[] {
                "cislo a;  cislo b() { vrat a + 3; }",
                "cislo s() { vrat 3+3; } cislo a = s();",
                "cislo sum(cislo n) { vrat 3; } cislo a = sum(3);"

            };
        }

        @Override
        protected String[] getChybneVety() {
            return new String[] {
                "cislo a;  cislo b() { vrat c + 3; }"
            };
        }
    }
}
