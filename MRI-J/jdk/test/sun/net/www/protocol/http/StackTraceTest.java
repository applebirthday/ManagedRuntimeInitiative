/*
 * Copyright 2002-2004 Sun Microsystems, Inc.  All Rights Reserved.
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

/**
 * @test
 * @bug 4773417 5003746
 * @summary  HttpURLConnection.getInputStream() produces IOException with
 *           bad stack trace; HttpURLConnection.getInputStream loses
 *           exception message, exception class
 */
import java.net.*;
import java.io.IOException;

public class StackTraceTest {
    public static void main(String[] args) {
        try {
            URL url = new URL("http://localhost:8080/");
            URLConnection uc = url.openConnection();
            System.out.println("key = "+uc.getHeaderFieldKey(20));
            uc.getInputStream();
        } catch (IOException ioe) {
            ioe.printStackTrace();

            if (!(ioe instanceof ConnectException)) {
                throw new RuntimeException("Expect ConnectException, got "+ioe);
            }
            if (ioe.getMessage() == null) {
                throw new RuntimeException("Exception message is null");
            }

            // this exception should be a chained exception
            if (ioe.getCause() == null) {
                throw new RuntimeException("Excepting a chained exception, but got: ", ioe);
            }
        }
    }
}
