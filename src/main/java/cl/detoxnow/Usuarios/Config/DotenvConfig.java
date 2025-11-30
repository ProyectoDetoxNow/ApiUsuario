package cl.detoxnow.usuarios.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DotenvConfig {

    @Bean
    public Dotenv dotenv() {
        return Dotenv.configure()
                .directory("./")  // donde está el .env
                .ignoreIfMissing()
                .load();
    }
}
