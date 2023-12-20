package cn.datafort.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

/**
 * @author 胡鑫虎
 * @version 1.0
 * @date 2023/12/20 11:34
 */
@Slf4j
@Api(tags = "i18n示例")
@RestController
@RequestMapping(value = "/i18n/demo",produces = MediaType.APPLICATION_JSON_VALUE)
public class SwaggerI18nDemoController {

    @Autowired MessageSource messageSource;

    @ApiOperation(value = "API_JOB_I18n",notes = "API_JOB_I18n_DESC")
    @GetMapping("/i18n")
    public ResponseEntity<String> i18n(){
        Locale locale = LocaleContextHolder.getLocale();
        return ResponseEntity.ok(messageSource.getMessage("API_JOB_TAG",null, locale));
    }
}
