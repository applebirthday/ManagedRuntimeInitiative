/*
 * Copyright 2005-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/*
 * @test
 * @bug 6249843 6705893
 * @summary Test invoking script function or method from Java
 */

import javax.script.*;
import java.io.*;

public class Test8 {
        public static void main(String[] args) throws Exception {
            System.out.println("\nTest8\n");
            ScriptEngineManager m = new ScriptEngineManager();
            ScriptEngine e  = Helper.getJsEngine(m);
            if (e == null) {
                System.out.println("Warning: No js engine found; test vacuously passes.");
                return;
            }
            e.eval(new FileReader(
                new File(System.getProperty("test.src", "."), "Test8.js")));
            Invocable inv = (Invocable)e;
            inv.invokeFunction("main", "Mustang");
            // use method of a specific script object
            Object scriptObj = e.get("scriptObj");
            inv.invokeMethod(scriptObj, "main", "Mustang");
        }
}