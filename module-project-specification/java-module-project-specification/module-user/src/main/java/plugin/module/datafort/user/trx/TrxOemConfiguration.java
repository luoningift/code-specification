package plugin.module.datafort.user.trx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("plugin.datafort.afw.trx.dao")
@ComponentScan({"plugin.datafort.afw.trx"})
public class TrxOemConfiguration {
}
