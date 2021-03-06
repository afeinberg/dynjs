/**
 *  Copyright 2012 Douglas Campos, and individual contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.dynjs.parser.statement;

import me.qmx.jitescript.CodeBlock;
import org.antlr.runtime.tree.Tree;
import org.dynjs.compiler.DynJSCompiler;
import org.dynjs.parser.Statement;
import org.dynjs.runtime.DynThreadContext;
import org.dynjs.runtime.RT;

import static me.qmx.jitescript.util.CodegenUtils.sig;

public class ResolveIdentifierStatement extends BaseStatement implements Statement {

    private final String name;

    public ResolveIdentifierStatement(final Tree tree, final String name) {
        super(tree);
        this.name = name;
    }

    @Override
    public CodeBlock getCodeBlock() {
        return new CodeBlock() {{
            aload(DynJSCompiler.Arities.CONTEXT);
            aload(DynJSCompiler.Arities.THIS);
            aload(DynJSCompiler.Arities.SELF);
            invokedynamic("getScope", sig(Object.class, DynThreadContext.class, Object.class, Object.class), RT.BOOTSTRAP_2, RT.BOOTSTRAP_ARGS);
            ldc(name);
            invokedynamic("dyn:getProp", DynJSCompiler.Signatures.ARITY_2, RT.BOOTSTRAP, RT.BOOTSTRAP_ARGS);
        }};
    }

    public String getName() {
        return name;
    }
}
