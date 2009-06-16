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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Clayton A. Smith <uhclay@gmail.com>
 */
public class EndOfLineConvertor {
    private File inputFile;
    private File outputFile;
    private EndOfLine eol;


    public static void main(String[] args) throws IOException, IllegalAccessException {
        EndOfLineConvertor endOfLineConvertor = new EndOfLineConvertor();

        if (args.length > 3) {
            endOfLineConvertor.usage();
        } else {
            endOfLineConvertor.setInputFile(new File(args[0]));
            endOfLineConvertor.setOutputFile(new File(args[1]));
            endOfLineConvertor.setEol(EndOfLine.valueOf(args[2]));
            endOfLineConvertor.run();
        }
    }

    public void run() throws IOException, IllegalAccessException {
        
        if (!fileExists(inputFile)) {
            throw new FileNotFoundException("File not found: " + inputFile);
        }

        BufferedReader reader = new BufferedReader(new FileReader(getInputFile()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(getOutputFile()));

        try {
            String l;
            while ((l = reader.readLine()) != null) {
                writer.write(l + getEol().getValue());                
            }
        } catch (IOException ex) {
            System.out.println("Aw, snap! Something bad happened while copying the file!");    
        } finally {
            reader.close();
            writer.close();
        }
    }

    private boolean fileExists(File file) {
        return file != null && file.exists();
    }

    private void usage() {
        System.out.println("usage: java -jar fixeol.jar file-in file-out eol");
        System.out.println("");
        System.out.println("Available eol types are [cr, lf, crlf, mac, unix, dos]");
    }

    // Properties

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public EndOfLine getEol() {
        return eol;
    }

    public void setEol(EndOfLine eol) {
        this.eol = eol;
    }
}
