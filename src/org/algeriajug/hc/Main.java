package org.algeriajug.hc;

import org.algeriajug.hc.domain.DataCenter;

import java.io.*;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        String outfile = System.getProperty("java.io.tmpdir") + File.separator + "dc.out";
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outfile));
        } catch (Exception e) {
            System.out.println(e);
        }

        DataCenter.newInstance()
                .load(getStream())
                .build()
                .optimize()
                .writeTo(writer);

        writer.close();


        Scanner scanner = new Scanner(new FileInputStream(outfile), "UTF-8").useDelimiter("\n");
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
    }

    private static Scanner getStream() {
        return new Scanner(Main.class.getClassLoader().getResourceAsStream("dc.in"), "UTF-8").useDelimiter("\n");
    }
}
