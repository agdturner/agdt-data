/*
 * Copyright (C) 2016 geoagdt.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package uk.ac.leeds.ccg.andyt.data.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.ac.leeds.ccg.andyt.generic.io.Generic_IO;

/**
 * For reading ASCII text files (such as CSV format files) into a collection of
 * Strings (one for each line in the file).
 *
 * @author geoagdt
 */
public class Data_ReadCSV {

    public static boolean testRead(File f, File testDir, int syntax) {
        ArrayList<String> result;
        File test;
        test = new File(testDir, "test" + syntax + ".csv");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(test);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Data_ReadCSV.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        if (f.exists()) {
            try {
                BufferedReader br;
                StreamTokenizer st;
                br = Generic_IO.getBufferedReader(f);
                if (br != null) {
                    result = new ArrayList<>();
                    st = new StreamTokenizer(br);
                    switch (syntax) {
                        case 1:
                            Generic_IO.setStreamTokenizerSyntax1(st);
                            break;
                        case 2:
                            Generic_IO.setStreamTokenizerSyntax2(st);
                            break;
                        case 3:
                            Generic_IO.setStreamTokenizerSyntax3(st);
                            break;
                        case 4:
                            Generic_IO.setStreamTokenizerSyntax4(st);
                            break;
                        case 5:
                            Generic_IO.setStreamTokenizerSyntax5(st);
                            break;
                        case 6:
                            Generic_IO.setStreamTokenizerSyntax6(st);
                            break;
                        default:
                            System.out.println("No Special Syntax!");

                    }
                    int token = st.nextToken();
//                    long RecordID = 0;
                    String line = "";
                    while (!(token == StreamTokenizer.TT_EOF)) {
                        switch (token) {
                            case StreamTokenizer.TT_EOL:
                                result.add(line);
                                pw.println(line);
                                line = "";
//                                if (RecordID % 100 == 0) {
//                                    System.out.println(line);
//                                }
//                                RecordID++;
                                break;
                            case StreamTokenizer.TT_WORD:
                                line += st.sval;
                                break;
                            case StreamTokenizer.TT_NUMBER:
                                break;
                            default:
                                if (token == 26 || token == 160) {
                                    // A type of space " ". It is unusual as st 
                                    // probably already set to parse space as
                                    // words.
                                    line += (char) token;
                                }
                                if (token == 13) {
                                    // These are returns or tabs or something...
                                    //line += (char) token;
                                }
                                System.out.println("line so far " + line);
                                System.out.println("Odd token " + token
                                        + " \"" + (char) token
                                        + "\" encountered.");
                        }
                        token = st.nextToken();
                    }
                    br.close();
                }
                pw.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("File " + f + " does not exist!");
        }
        long length0;
        length0 = test.length();
        System.out.println("length of test file = " + length0);
        long length1;
        length1 = f.length();
        System.out.println("length of original file = " + length1);
        if (length0 == length1) {
            System.out.println("length0 == length1");
            return true;
        } else {
            return false;
        }
        //return result;
    }

    /**
     *
     * @param f The file to be read in and returned as an ArrayList with each
     * element being a line.
     * @param testDir If this is null then a test file is not written out.
     * Otherwise a test file is written out in testDir
     * @param syntax @see forexample,
 Generic_IO.setStreamTokenizerSyntax1(st);
     * @return
     */
    public static ArrayList<String> read(File f, File testDir, int syntax) {
        ArrayList<String> result = null;
        PrintWriter pw = null;
        // Initialise pw
        if (testDir != null) {
            testDir.mkdirs();
            File test = null;
            test = new File(testDir, "test" + syntax + ".csv");
            try {
                pw = new PrintWriter(test);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Data_ReadCSV.class.getName()).log(Level.SEVERE,
                        null, ex);
            }
        }

        if (f.exists()) {
            try {
                BufferedReader br;
                StreamTokenizer st;
                br = Generic_IO.getBufferedReader(f);
                if (br != null) {
                    result = new ArrayList<>();
                    st = new StreamTokenizer(br);
                    switch (syntax) {
                        case 1:
                            Generic_IO.setStreamTokenizerSyntax1(st);
                            break;
                        case 2:
                            Generic_IO.setStreamTokenizerSyntax2(st);
                            break;
                        case 3:
                            Generic_IO.setStreamTokenizerSyntax3(st);
                            break;
                        case 4:
                            Generic_IO.setStreamTokenizerSyntax4(st);
                            break;
                        case 5:
                            Generic_IO.setStreamTokenizerSyntax5(st);
                            break;
                        case 6:
                            Generic_IO.setStreamTokenizerSyntax6(st);
                            break;
                        case 7:
                            Generic_IO.setStreamTokenizerSyntax7(st);
                            break;
                        default:
                            System.out.println("No Special Syntax!");

                    }
                    int token = st.nextToken();
//                    long RecordID = 0;
                    String line = "";
                    while (!(token == StreamTokenizer.TT_EOF)) {
                        switch (token) {
                            case StreamTokenizer.TT_EOL:
                                result.add(line);
                                if (pw != null) {
                                    pw.println(line);
                                }
                                line = "";
//                                if (RecordID % 100 == 0) {
//                                    System.out.println(line);
//                                }
//                                RecordID++;
                                break;
                            case StreamTokenizer.TT_WORD:
                                line += st.sval;
                                break;
                            case StreamTokenizer.TT_NUMBER:
                                break;
                            default:
                                if (token == 26 || token == 160) {
                                    // A type of space " ". It is unusual as st 
                                    // probably already set to parse space as
                                    // words.
                                    line += (char) token;
                                }
                                if (token == 13) {
                                    // These are returns or tabs or something...
                                    //line += (char) token;
                                }
//                                System.out.println("line so far " + line);
//                                System.out.println("Odd token " + token 
//                                        +  " \"" + (char) token 
//                                        + "\" encountered.");
                        }
                        token = st.nextToken();
                    }
                    br.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } else {
            System.out.println("File " + f + " does not exist!");
        }
        return result;
    }

    /**
     * 
     * @param st The StreamTokenizer for parsing the stream.
     * @param pw If this is not null then the line returned is printed to it.
     * @return The next line or null.
     */
    public static String readLine(StreamTokenizer st, PrintWriter pw) {
        try {
            int token = st.nextToken();
//                    long RecordID = 0;
            String line = "";
            while (!(token == StreamTokenizer.TT_EOF)) {
                switch (token) {
                    case StreamTokenizer.TT_EOL:
                        if (pw != null) {
                            pw.println(line);
                        }
                        return line;
                    case StreamTokenizer.TT_WORD:
                        line += st.sval;
                        break;
                    case StreamTokenizer.TT_NUMBER:
                        break;
                    default:
                        if (token == 26 || token == 160) {
                            // A type of space " ". It is unusual as st
                            // probably already set to parse space as
                            // words.
                            line += (char) token;
                        }
                        if (token == 13) {
                            // These are returns or tabs or something...
                            //line += (char) token;
                        }
//                                System.out.println("line so far " + line);
//                                System.out.println("Odd token " + token
//                                        +  " \"" + (char) token 
//                                        + "\" encountered.");
                }
                token = st.nextToken();
            }
        } catch (IOException ex) {
            Logger.getLogger(Data_ReadCSV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}