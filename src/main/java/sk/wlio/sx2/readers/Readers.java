/*
 * Copyright viliam.kois@gmail.com Kois Viliam
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package sk.wlio.sx2.readers;

import sk.wlio.sx2.beans.Program;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Parenthesis;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public class Readers {

    private static IReaders readers = new ReadersImpl();

    public static void recreate() {
        Readers.readers = new ReadersImpl();
    }

    public static void recreateReaders(IReaders readers) {
        if (readers == null) throw new IllegalArgumentException("readers can't be null..");
        Readers.readers = readers;
    }

    public static TextReader<Slovo> slovo() { return readers.slovo(); }
    public static TextReader<Cislo> cislo() { return readers.cislo(); }
    public static TextReader<Operator> opAritm() { return readers.opAritm(); }
    public static TextReader<Operator> opBool() { return readers.opBool(); }
    public static TextReader<Operator> opPorovnanie() { return readers.opPorovnanie(); }
    public static TextReader<Operator> opPriradenia() { return readers.opPriradenia(); }
    public static TextReader<IVyraz> vyraz() { return readers.vyraz(); }
    public static TextReader<IVyraz> vrzAritm() { return readers.vrzAritm(); }
    public static TextReader<IVyraz> vrzBool() { return readers.vrzBool(); }
    public static TextReader<IVyraz> vrzJednduchy() { return readers.vrzJednduchy(); }
    public static TextReader<IVyraz> vrzVzatvorke() { return readers.vrzVzatvorke(); }
    public static TextReader<IVyraz> vrzAritmVzatvorke() { return readers.vrzAritmVzatvorke(); }
    public static TextReader<Parenthesis> zatvorka() { return readers.zatvorka(); }
    public static TextReader<Comma> ciarka() { return readers.ciarka(); }

    public static TextReader<DataType> datovyTyp() { return readers.datovyTyp(); }
    public static TextReader<InstructionWord> instrukciaSlovo() { return readers.instrukciaSlovo();}

    public static TextReader<Instrukcia> instrukcia() { return readers.instrukcia(); }
    public static TextReader<Assignment> priradenie() { return readers.priradenie(); }
    public static TextReader<Block> blok() { return readers.blok(); }
    public static TextReader<Premenna> premena() { return readers.premenna(); }
    public static TextReader<DeclarationVariable> dekPremennej() { return readers.dekPremennej(); }
    public static TextReader<Command> prikaz() { return readers.prikaz(); }
    public static TextReader<DeclarationCommand> dekPrikaz() { return readers.dekPrikaz(); }
    public static TextReader<Parameters> parametre() { return readers.parametre(); }
    public static TextReader<DeclarationParameter> dekParameter() { return readers.dekParameter(); }
    public static TextReader<Program> dekTrieda() { return readers.dekTrieda();}
    public static TextReader<Return> vrat() { return readers.vrat(); }


}
