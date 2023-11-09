package sams2024.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "sams2024.src.Models.Users")
public class SrcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrcApplication.class, args);
    }
}