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

package sk.wlio.sx2.exception;

public enum SxExTyp {
    END_OF_FILE,
    UNEXPECTED_PREFIX,
    CAKAL_ZATVORKU,
    CAKAL_ZATVORKU_ALEBO_CIARKU,
    CAKAL_OPERATOR,
    CAKAL_OPERATOR_POROVNANIA,
    CAKAL_CISLO,
    CAKAL_CIARKU,
    PRAZDNE_SLOVO,
    CAKAL_INSTRUKCIU,
    CAKAL_VRAT,
    CAKAL_AK,
    ZLY_DATOVY_TYP,
    ZLY_NAZOV_PRIKAZU,
    CAKAL_DEKLARACIU_PRIKAZU,
    CAKAL_DATOVY_TYP,
    CAKAL_DATOVU_HODNOTU,
    CAKAL_BOOL,
    CAKAL_DEKLARACIU_PRIKAZU_ALEBO_PREMENNEJ,
    NEZNAMY_PRIKAZ,
    NESPRAVNY_POCET_PARAMETROV,
    NEZNAMA_PREMENNA,
    CAKAL_INSTRUKCIA_SLOVO,
    PREMENNA_UZ_EXISTUJE,
    PRIKAZ_UZ_EXISTUJE
}
