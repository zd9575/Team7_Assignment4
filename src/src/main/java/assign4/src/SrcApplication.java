package assign4.src;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "assign4.src.Models")
public class SrcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SrcApplication.class, args);
    }
}