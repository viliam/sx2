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

package sk.wlio.sx2.readers.rezervedwords;

import sk.wlio.sx2.Enums;
import sk.wlio.sx2.TextContext;
import sk.wlio.sx2.beans.reservedwords.DataType;
import sk.wlio.sx2.beans.reservedwords.enums.RezervedWordsEnum;
import sk.wlio.sx2.exception.SxExTyp;
import sk.wlio.sx2.readers.ReserverdWordAbstractReader;

public class DataTypeReader extends ReserverdWordAbstractReader<DataType> {

    public DataType read(TextContext tC)  {
        DataType dt = new DataType( super.read(tC) );
        dt.setTyp( Enums.ExpType.getDatovyTyp(dt));

        return dt;
    }

    @Override
    protected SxExTyp getSxExceptionTyp() {
        return SxExTyp.EXPECTED_DATA_TYPE;
    }

    @Override
    protected RezervedWordsEnum getZakazaneSlova() {
        return RezervedWordsEnum.DATA_TYPE;
    }
}
