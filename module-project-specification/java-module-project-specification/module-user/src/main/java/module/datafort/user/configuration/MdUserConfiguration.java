package module.datafort.user.configuration;

import module.datafort.user.oem.MdUserOemImportSelector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@MapperScan("module.datafort.user.dao")
@ComponentScan({"module.datafort.user"})
@Import(MdUserOemImportSelector.class)
public class MdUserConfiguration {
}
