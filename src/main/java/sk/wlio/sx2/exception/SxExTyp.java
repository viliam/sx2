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
    EXPECTED_BRACKET,
    EXPECTED_BRACKET_OR_COMMA,
    EXPECTED_OPERATOR,
    EXPECTED_INT,
    EXPECTED_COMMA,
    EMPTY_WORD,
    EXPECTED_STATEMENT,
    EXPECTED_RETURN,
    EXPECTED_IF,
    WRONG_DATA_TYPE,
    WRONG_COMMAND_NAME,
    EXPECTED_COMMAND_DECLARATION,
    EXPECTED_DATA_TYPE,
    EXPECTED_DATA_VALUE,
    EXPECTED_BOOL,
    EXPECTED_DECLARATION,
    UNKNOWN_COMMAND,
    WRONG_PARAMETER_COUNT,
    UNKNOWN_VARIABLE,
    EXPECTED_STATEMENT_WORD,
    DUPLICATED_VARIABLE,
    DUPLICATED_COMMAND
}
