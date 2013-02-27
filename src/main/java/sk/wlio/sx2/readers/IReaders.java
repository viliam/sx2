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
import sk.wlio.sx2.beans.rezervovaneslova.DatovyTyp;
import sk.wlio.sx2.beans.Premenna;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instrukcia.*;
import sk.wlio.sx2.beans.symbol.Ciarka;
import sk.wlio.sx2.beans.symbol.Operator;
import sk.wlio.sx2.beans.symbol.Zatvorka;
import sk.wlio.sx2.beans.vyraz.Cislo;
import sk.wlio.sx2.beans.rezervovaneslova.InstrukciaSlovo;
import sk.wlio.sx2.rozhrania.Instrukcia;
import sk.wlio.sx2.rozhrania.TextReader;
import sk.wlio.sx2.rozhrania.IVyraz;

public interface IReaders {
    
    public TextReader<Slovo> slovo();
    public TextReader<Cislo> cislo();
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
    public TextReader<Zatvorka> zatvorka();
    public TextReader<Ciarka> ciarka();

    public TextReader<DatovyTyp> datovyTyp();

    public TextReader<Instrukcia> instrukcia();
    public TextReader<Priradenie> priradenie();
    public TextReader<Blok> blok();
    public TextReader<Premenna> premenna();
    public TextReader<DeklaraciaPremennej> dekPremennej();
    public TextReader<Prikaz> prikaz();
    public TextReader<DeklaraciaPrikaz> dekPrikaz();
    public TextReader<Program> dekTrieda();
    public TextReader<Parametre> parametre();
    public TextReader<DeklaraciaParameter> dekParameter();
    public TextReader<Vrat> vrat();

    public TextReader<InstrukciaSlovo> instrukciaSlovo();


}
