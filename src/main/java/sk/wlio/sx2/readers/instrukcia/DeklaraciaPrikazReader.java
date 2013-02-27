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

package sk.wlio.sx2.readers.instrukcia;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.Slovo;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.rozhrania.TextReader;

public class DeklaraciaPrikazReader implements TextReader<DeclarationCommand>  {

    public DeclarationCommand citaj(TextContext tC)  {
       DataType dataType = Readers.datovyTyp().citaj( tC);
       String sDatovyTyp = dataType.toString();
       if (!RezervedWordsEnum.DATA_TYPE.is(sDatovyTyp))
           throw SxException.create(SxExTyp.CAKAL_DEKLARACIU_PRIKAZU, tC);

       if ( !tC.jePrefixPrikaz())
          throw SxException.create(SxExTyp.ZLY_NAZOV_PRIKAZU, tC);

       Slovo nazov = Readers.slovo().citaj( tC);
       //odkusnem parametre
       DeclarationParameter dekParam = Readers.dekParameter().citaj(tC);
       Block block = Readers.blok().citaj( tC);

       return new DeclarationCommand(dataType, nazov, dekParam, block);
    }
}
