package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import se331.lab.util.StorageFileDto;
import se331.lab.util.SupabaseStorageService;

@RestController
@RequiredArgsConstructor
public class SupabaseController {

    final SupabaseStorageService supabaseStorageService;

    @PostMapping("/uploadFile")
    public ResponseEntity<String> uploadFile(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "media", required = false) MultipartFile media
    ) {
        try {
            MultipartFile fileToUpload = file;
            if (fileToUpload == null) fileToUpload = image;
            if (fileToUpload == null) fileToUpload = media;

            if (fileToUpload == null || fileToUpload.isEmpty()) {
                return ResponseEntity.badRequest().body("No file provided");
            }

            String fileUrl = supabaseStorageService.uploadFile(fileToUpload);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @GetMapping("/presignedUrl")
    public ResponseEntity<String> getPresignedUrl(@RequestParam("fileName") String fileName) {
        try {
            String presignedUrl = supabaseStorageService.getPresignedUrl(fileName);
            return ResponseEntity.ok(presignedUrl);
        } catch (Exception e) {    
            return ResponseEntity.status(500).body("Error generating presigned url: " + e.getMessage());
        }
    }

    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file) {
        try {
            StorageFileDto fileUrl = supabaseStorageService.uploadImage(file);
            return ResponseEntity.ok(fileUrl);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }
}