package com.tuxt.mytest.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FileFinder2 {

	public static void main(String[] args) throws IOException {
		Path path = Paths.get("D:/Documents/Pictures/测试用照片/演示照片/");
		FindFileVisitor visitor=new FindFileVisitor(".jpg");
		Files.walkFileTree(path, visitor);
		for (String fileName : visitor.getFileNames()) {
			System.out.println(fileName);
		}

	}
}
class FindFileVisitor extends SimpleFileVisitor<Path>{
	String fileNameSuffix=null;
	List<String> fileNames=new ArrayList<>();
	public List<String> getFileNames() {
		return fileNames;
	}
	public void setFileNames(List<String> fileNames) {
		this.fileNames = fileNames;
	}
	public FindFileVisitor(String fileNameSuffix) {
		this.fileNameSuffix=fileNameSuffix;
	}
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (file.toString().endsWith(fileNameSuffix)) {
			fileNames.add(file.toString());
		}
		return FileVisitResult.CONTINUE;
	}
}