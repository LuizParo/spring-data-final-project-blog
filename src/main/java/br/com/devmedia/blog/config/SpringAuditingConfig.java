package br.com.devmedia.blog.config;

import java.io.Serializable;

import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Component;

@Component
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class SpringAuditingConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Bean
    public AuditorAware<String> auditorAware() {
        return new SpringSecurityAuditor();
    }
}