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
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Variable;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.InstructionWord;
import sk.wlio.sx2.beans.symbol.Bracket;
import sk.wlio.sx2.beans.symbol.Comma;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.vyraz.Int;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public interface IReaders {
    
    public TextReader<Word> slovo();
    public TextReader<Int> cislo();
    public TextReader<Operator> opAritm();
    public TextReader<Operator> opBool();
    public TextReader<Operator> opPorovnanie();
    public TextReader<Operator> opPriradenia();
    public TextReader<IVyraz> vyraz();
    public TextReader<IVyraz> vrzAritm();
    public TextReader<IVyraz> vrzBool();
    public TextReader<IVyraz> vrzJednduchy();
    public TextReader<IVyraz> vrzVzatvorke();
    public TextReader<IVyraz> vrzAritmVzatvorke();
    public TextReader<Bracket> zatvorka();
    public TextReader<Comma> ciarka();

    public TextReader<DataType> datovyTyp();

    public TextReader<Instrukcia> instrukcia();
    public TextReader<Assignment> priradenie();
    public TextReader<Block> blok();
    public TextReader<Variable> premenna();
    public TextReader<DeclarationVariable> dekPremennej();
    public TextReader<Command> prikaz();
    public TextReader<DeclarationCommand> dekPrikaz();
    public TextReader<Program> dekTrieda();
    public TextReader<Parameters> parametre();
    public TextReader<DeclarationParameter> dekParameter();
    public TextReader<Return> vrat();

    public TextReader<InstructionWord> instrukciaSlovo();


}
