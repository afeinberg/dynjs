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
package org.dynjs.runtime;

import java.io.PrintStream;
import java.util.LinkedHashSet;
import java.util.Set;

import org.dynjs.runtime.loader.Builtin;

public class DynJSConfig {

    private boolean debug;
    private Set<Builtin> builtins = new LinkedHashSet<>();
    private final DynamicClassLoader classLoader;
    private PrintStream outputStream = System.out;
    private PrintStream errorStream = System.err;

    public DynJSConfig() {
        this.classLoader = new DynamicClassLoader();
    }

    public DynJSConfig(ClassLoader parentClassLoader) {
        this.classLoader = new DynamicClassLoader(parentClassLoader);
    }

    public void enableDebug() {
        this.debug = true;
    }

    public boolean isDebug() {
        return debug;
    }

    public void addBuiltin(String bindingName, Object boundObject) {
        builtins.add(new Builtin(bindingName, boundObject));
    }

    public Set<Builtin> getBuiltins() {
        return builtins;
    }

    public DynamicClassLoader getClassLoader() {
        return classLoader;
    }
    
    public void setOutput(PrintStream outputStream) {
        this.outputStream = outputStream;
    }
    
    public PrintStream getOutputStream() {
        return this.outputStream;
    }
    
    public void setErrorStream(PrintStream errorStream) {
        this.errorStream = errorStream;
    }
    
    public PrintStream getErrorStream() {
        return this.errorStream;
    }
    
}
