/*
 * Copyright 2005 Sun Microsystems, Inc.  All Rights Reserved.
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
 *
 *
 * A test "application" used by unit test LocalManagementTest.sh. This
 * application binds to some random port, prints the port number to
 * standard output, waits for somebody to connect, and then shuts down.
 */
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TestApplication {
    public static void main(String[] args) throws IOException {
        // Some tests require the application to exit immediately
        if (args.length > 0 && args[0].equals("-exit")) {
            return;
        }

        // bind to a random port
        ServerSocket ss = new ServerSocket(0);
        int port = ss.getLocalPort();

        // signal test that we are started - do not remove this line!!
        System.out.println(port);
        System.out.flush();

        // wait for manager to connect
        Socket s = ss.accept();
        s.close();
        ss.close();
    }
}
