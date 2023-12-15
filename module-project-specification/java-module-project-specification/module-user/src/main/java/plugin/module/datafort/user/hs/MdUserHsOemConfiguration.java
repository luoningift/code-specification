package plugin.module.datafort.user.hs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("plugin.module.datafort.user.hs.dao")
@ComponentScan({"plugin.module.datafort.user.hs"})
public class MdUserHsOemConfiguration {
}
