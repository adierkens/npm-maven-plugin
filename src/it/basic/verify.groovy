/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

def modules = ["colors", "jshint", "underscore"];

modules.each() {
    def f = new File(basedir, "src/main/resources/META-INF/" + it)
    assert f.exists()
    assert f.isDirectory()

    def f2 = new File(f, "package");
    assert !f2.exists();

    def f3 = new File(f, "src/main/resources/META-INF/" + it + "_tmp")
    assert !f3.exists();
}

true