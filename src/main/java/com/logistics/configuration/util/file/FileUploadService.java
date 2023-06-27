package com.logistics.configuration.util.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


// FileSystemStorageService > FileUploadServiceImpl 
// StorageService           > FileUploadService
// StorageException        > FileUploadException 
// StorageProperties       > FileUploadProperties 
// StorageFileNotFoundException > FileUploadFileNotFoundException

@Service
public class FileUploadService { 

	private final Path rootLocation;

	@Autowired
	public FileUploadService(FileUploadProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	//
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new FileUploadException("Failed to store empty file " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new FileUploadException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	//
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1)
					.filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new FileUploadException("Failed to read stored files", e);
		}

	}

	//
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	//
	public Resource loadAsResource(String filename) {
		try {
			Path file = load(filename);
			Resource resource = new UrlResource(file.toUri());
			if(resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new FileUploadFileNotFoundException("Could not read file: " + filename);

			}
		} catch (MalformedURLException e) {
			throw new FileUploadFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	//
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	//
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new FileUploadException("Could not initialize storage", e);
		}
	}
}
