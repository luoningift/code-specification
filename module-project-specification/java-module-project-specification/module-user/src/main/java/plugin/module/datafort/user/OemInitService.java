package plugin.module.datafort.user;

public interface OemInitService {
    
    /**
     * 初始化配置信息
     */
    void initConfig();
    
    /**
     * 初始化程序
     */
    void initProgram();
    
    /**
     * 其他初始化信息
     */
    void initOther();
}
