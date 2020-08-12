package ru.job4j.io;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class SearchFiles implements FileVisitor<Path> {
    private String partOfName;
    List<Path> list = new ArrayList<>();

    public SearchFiles(String partOfName) {
        this.partOfName = partOfName;
    }

    public List<Path> getList() {
        return list;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean containsName = true;
                if (partOfName != null && !file.getFileName().toString().endsWith(partOfName)) {
            containsName = false;
        }
        if (containsName) {
         //   System.out.println(file.getFileName());
            list.add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
        return CONTINUE;
    }
}
