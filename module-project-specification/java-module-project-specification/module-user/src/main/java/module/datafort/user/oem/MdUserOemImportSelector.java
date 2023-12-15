package module.datafort.user.oem;

import module.datafort.user.info.ModuleInfo;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import plugin.module.datafort.user.OemInitService;

public class MdUserOemImportSelector implements ImportSelector {
    
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        
        //根据配置 获取oem信息
        String oem = "trx";
        String moduleName = ModuleInfo.moduleName;
        //MdUserHsOemInitServiceImpl.java
        String oemInitClass = "plugin.module.datafort." + moduleName + ".Md" + Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1) + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemInitServiceImpl";
        
        try {
            final Class<? extends OemInitService> initClass = Class.forName(oemInitClass).asSubclass(OemInitService.class);
            final OemInitService oemInitService = initClass.newInstance();
            oemInitService.initConfig();
            oemInitService.initProgram();
            oemInitService.initOther();
        } catch (Exception ignore) {
        }
    
        String oemConfigClass = "plugin.module.datafort." + moduleName + ".Md" + Character.toUpperCase(moduleName.charAt(0)) + moduleName.substring(1) + Character.toUpperCase(oem.charAt(0)) + oem.substring(1) + "OemConfiguration";
        return new String[]{oemConfigClass};
    }
}
