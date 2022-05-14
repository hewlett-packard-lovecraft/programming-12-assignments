package com.haoming.databaseAssignment;

import java.io.File;
import java.util.Iterator;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

public class Directory {
    public ArrayList<RetrievedFile> fileList = null;
    public String dirName = "";
    private File dir = null;

    public Directory(String dirPath) {
        if (!FileUtils.isDirectory(new File(dirPath))) {
            throw new IllegalArgumentException(dirPath + " is not a directory");
        } else {
            dir = new File(dirPath);
            dirName = dir.getName();
            readFileList();
        }
    }

    private void readFileList() {
        for (Iterator<File> it = FileUtils.iterateFiles(dir, null, true); it.hasNext(); ) {
            File file = it.next();

            fileList.add(new RetrievedFile(file));
        }

    }
}
