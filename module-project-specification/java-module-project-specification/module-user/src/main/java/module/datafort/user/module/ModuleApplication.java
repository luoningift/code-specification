package module.datafort.user.module;

import module.datafort.user.module.oem.OemImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import java.util.TimeZone;


@SpringBootApplication(scanBasePackages = {"cn.datafort"})
@Import(OemImportSelector.class)
public class ModuleApplication extends SpringBootServletInitializer {
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ModuleApplication.class);
    }

    public static void main(String[] args) {
    
        // 设置时区
        TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
        TimeZone.setDefault(timeZone);
        SpringApplication.run(ModuleApplication.class, args);
    }
}
