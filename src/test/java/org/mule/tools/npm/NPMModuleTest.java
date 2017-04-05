/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.tools.npm;


import org.apache.maven.plugin.logging.Log;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.mockito.Mockito.mock;

public class NPMModuleTest {

	@Before
	public void setUp() {
		NPMModule.npmUrl = "http://registry.npmjs.org/%s";
	}
	
    @Test
    public void testDownloadColors() throws Exception {
        NPMModule npmModule = NPMModule.fromNameAndVersion(mock(Log.class), "colors", "1.1.2");
        npmModule.saveToFile(new File("target/colors-test"));
    }

    @Test
    public void testDownloadUnderscore() throws Exception {
        NPMModule npmModule2 = NPMModule.fromName(mock(Log.class), "underscore");
        npmModule2.saveToFileWithDependencies(new File("target/underscore-test"));
    }

    @Test
    public void testDownloadJshint() throws Exception {
        NPMModule npmModule3 = NPMModule.fromQueryString(mock(Log.class), "jshint:0.8.1");
        npmModule3.saveToFileWithDependencies(new File("target/jshint-test"));
    }

    @Test
    public void testDownloadPrerelease() throws Exception {
        NPMModule npmModule4 = NPMModule.fromQueryString(mock(Log.class), "minnow-gpio:2.0.0-0");
        npmModule4.saveToFileWithDependencies(new File("target/minnow-gpio"));
    }
}
