/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package fixeol;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @goal convert
 * @author Clayton A. Smith <uhclay@gmail.com>
 */
public class EndOfLineMojo extends AbstractMojo {

    /**
     * @parameter ${inputFile}
     * @required
     */
    public File inputFile;

    /**
     * @parameter ${outputFile}
     * @required
     */
    public File outputFile;

    /**
     * @parameter ${eol}
     * @required
     */
    public String eol;

    /**
     * @throws MojoExecutionException
     * @throws MojoFailureException
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        EndOfLineConvertor util = new EndOfLineConvertor();
        util.setInputFile(inputFile);
        util.setOutputFile(outputFile);
        util.setEol(EndOfLine.valueOf(eol));
        
        try {
            util.run();
        } catch (IOException e) {
            throw new MojoExecutionException("Failed to convert file", e);
        } catch (IllegalAccessException e) {
            throw new MojoExecutionException("Failed to convert file", e);
        }
    }
}
