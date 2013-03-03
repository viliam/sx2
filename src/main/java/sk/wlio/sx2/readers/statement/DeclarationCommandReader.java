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

package sk.wlio.sx2.readers.statement;

import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.Word;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.instruction.*;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.exception.SxException;
import sk.wlio.sx2.readers.Readers;
import sk.wlio.sx2.interfaces.TextReader;

public class DeclarationCommandReader implements TextReader<DeclarationCommand>  {

    public DeclarationCommand read(TextContext tC)  {
       DataType dataType = Readers.datovyTyp().read(tC);
       String sDataType = dataType.toString();
       if (!RezervedWordsEnum.DATA_TYPE.is(sDataType))
           throw SxException.create(SxExTyp.CAKAL_DEKLARACIU_PRIKAZU, tC);

       if ( !tC.isPrefixCommand())
          throw SxException.create(SxExTyp.ZLY_NAZOV_PRIKAZU, tC);

       Word name = Readers.slovo().read(tC);
       //odkusnem parametre
       DeclarationParameter decParam = Readers.dekParameter().read(tC);
       Block block = Readers.blok().read(tC);

       return new DeclarationCommand(dataType, name, decParam, block);
    }
}
