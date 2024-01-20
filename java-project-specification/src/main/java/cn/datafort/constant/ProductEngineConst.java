package cn.datafort.constant;

public enum ProductEngineConst {
       
       NEED(1, "需要选择引擎数量"),
       NOT_NEED(2, "不需要");
        
        private final int code;
        private final String message;
    
        ProductEngineConst(int code, String desc) {
            this.code = code;
            this.message = desc;
        }
        
        public String getMessage() {
            return message;
        }
        
        public int getCode() {
            return code;
        }
}
