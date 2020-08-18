package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<File> sources, File target) {
            try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
                for (File file : sources) {
                    zip.putNextEntry(new ZipEntry(file.getPath()));
                    try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                        zip.write(out.readAllBytes());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<File> dataSearch(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        List<File> listFiles = new ArrayList<>();
        if (argZip.valid()) {
            SearchFiles searcher = new SearchFiles(p -> p.getFileName().toString().endsWith(argZip.exclude()));
            Files.walkFileTree(Paths.get(argZip.directory()), searcher);
            List<Path> listPaths = searcher.getList();
            for (Path path : listPaths) {
                listFiles.add(path.toFile());
            }
        }
        return listFiles;
    }

    public static void main(String[] args) throws IOException {
        ArgZip argZip = new ArgZip(args);
        List<File> listFiles = dataSearch(args);
        new Zip().packFiles(listFiles, new File(argZip.output()));
    }
}
