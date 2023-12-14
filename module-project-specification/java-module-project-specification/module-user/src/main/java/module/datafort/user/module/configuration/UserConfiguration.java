package module.datafort.user.module.configuration;

import module.datafort.user.module.oem.OemImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("module.datafort.user.module.dao")
@ComponentScan({"module.datafort.user.module"})
@Import(OemImportSelector.class)
public class UserConfiguration {
}
