/**
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 *
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */

package org.mule.tools.npm;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.settings.Settings;

import java.io.File;

/**
 * Goal that offers Recess support in Maven builds.
 *
 * @goal fetch-modules
 * @phase generate-sources
 */
public class NPMMojo extends AbstractJavascriptMojo {

    private static final String NPM_REPO_URL_TAIL = "%s";

    /**
     * Where the resulting files will be downloaded.
     *
     * @parameter expression="${recess.outputDirectory}" default-value="${basedir}/src/main/resources/META-INF"
     */
    private File outputDirectory;

    /**
     * The identifiers of the packages to download. Use the following syntax: package:version
     *
     * @parameter expression="${recess.packages}
     * @required
     */
    private String [] packages;

    /**
     * The Maven Settings.
     * 
     * @parameter default-value="${settings}"
     * @required
     * @readonly
     */
    private Settings settings;

    /**
     * Base URL for NPM repository - update this if you're using a cache/proxy in a corporate environment.
     *
     * @parameter expression="${recess.npmRepositoryBase}" default-value="http://registry.npmjs.org"
     */
    private String npmRepositoryBase;


    public void execute() throws MojoExecutionException {
        Log log = getLog();

        NPMModule.proxy = settings.getActiveProxy();

        String base = npmRepositoryBase.endsWith("/") ? npmRepositoryBase : npmRepositoryBase + "/";
        NPMModule.npmUrl = base + NPM_REPO_URL_TAIL;

        for (String aPackage : packages) {
            NPMModule.fromQueryString(log,aPackage).saveToFileWithDependencies(outputDirectory);
        }
    }
}
