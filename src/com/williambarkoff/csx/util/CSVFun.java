package com.williambarkoff.csx.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class CSVFun {
    private File file;
    private EscapeType escapeType;
    private String[] headers;
    private String[][] dataArray;

    @SuppressWarnings("unchecked")
    private HashMap<String, String>[] data;

    public CSVFun(File file) throws FileNotFoundException, InvalidCSVFormatException {
        this(file, EscapeType.NO_ESCAPE);
    }

    public CSVFun(File file, EscapeType escapeType) throws FileNotFoundException, InvalidCSVFormatException {
        this.file = file;
        this.escapeType = escapeType;

        Scanner fs = new Scanner(file);

        if (!fs.hasNextLine()) {
            throw new InvalidCSVFormatException();
        }

        headers = splitLine(fs.nextLine());

        ArrayList<String[]> dataArrayList = new ArrayList<>();
        ArrayList<HashMap<String, String>> dataHashMaps = new ArrayList<>();

        while (fs.hasNextLine()) {
            HashMap<String, String> line = new HashMap<>();
            String[] cells = splitLine(fs.nextLine());
            for (int i = 0; i < cells.length; i++) {
                line.put(headers[i], cells[i]);
            }
            dataArrayList.add(cells);
            dataHashMaps.add(line);
        }

        dataArray = dataArrayList.toArray(new String[0][]);
        data = dataHashMaps.toArray(new HashMap[0]);
    }

    private String[] splitLine(String line) {
        String regex = "(?<!" + Pattern.quote("" + escapeType.getEscapeChar()) + Pattern.quote("" + escapeType.getEscapeChar()) + ")" + Pattern.quote(",");

        String[] columns = line.split(regex);

        for (int i = 0; i < columns.length; i++) {
            columns[i] = columns[i].replace("" + escapeType.getEscapeChar(), "");
        }

        return columns;
    }

    public EscapeType getEscapeType() {
        return escapeType;
    }

    public void setEscapeType(EscapeType escapeType) {
        this.escapeType = escapeType;
    }

    public File getFile() {
        return file;
    }

    public HashMap<String, String>[] getData() {
        return data;
    }

    public String[] getHeaders() {
        return headers;
    }

    public String[][] getDataArray() {
        return dataArray;
    }

    public enum EscapeType {
        NO_ESCAPE((char) 255),
        BACKTICK_ESCAPE('`'),
        BACKSLASH_ESCAPE('\\');

        private char escapeChar;

        EscapeType(char escapeChar) {
            this.escapeChar = escapeChar;
        }

        public char getEscapeChar() {
            return escapeChar;
        }
    }

    public static class InvalidCSVFormatException extends Exception {
    }
}
