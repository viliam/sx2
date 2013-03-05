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

package sk.wlio.sx2.beans;

import sk.wlio.sx2.beans.statement.DeclarationCommand;
import sk.wlio.sx2.beans.statement.DeclarationVariable;

import java.util.HashMap;
import java.util.Map;

public class Program extends WordAbstract {

    final Map<String, DeclarationVariable> mapVariable = new HashMap<String, DeclarationVariable>();
    final Map<String, DeclarationCommand> mapCommand = new HashMap<String, DeclarationCommand>();

    public Program(Position position) {
        super(position);
    }

    public void addVariable(DeclarationVariable declarationVariable) {
        mapVariable.put(declarationVariable.getName().getContent(), declarationVariable);
    }

    public void addCommand(DeclarationCommand declarationCommand) {
        mapCommand.put(declarationCommand.getName().getContent(), declarationCommand);
    }

    public Map<String, DeclarationCommand> getMapCommand() {
        return mapCommand;
    }

    public Map<String, DeclarationVariable> getMapVariable() {
        return mapVariable;
    }
}
