package plugin.module.datafort.user.trx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("plugin.module.datafort.user.trx.dao")
@ComponentScan({"plugin.module.datafort.user.trx"})
public class MdUserTrxOemConfiguration {
}
