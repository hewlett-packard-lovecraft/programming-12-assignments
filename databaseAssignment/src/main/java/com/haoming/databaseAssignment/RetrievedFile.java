package com.haoming.databaseAssignment;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class RetrievedFile {
    public String name = "";
    public String path = "";
    public String extension = "";
    public long sizeInBytes = 0;

    public RetrievedFile(File file) {
        name = file.getName();
        path = file.getPath();
        extension = FilenameUtils.getExtension(String.valueOf(file));
        sizeInBytes = FileUtils.sizeOf(file);
    }

    public boolean isFlagged() {
        return name.isEmpty() || path.isEmpty() || extension.isEmpty() || sizeInBytes == 0;
    }
}
