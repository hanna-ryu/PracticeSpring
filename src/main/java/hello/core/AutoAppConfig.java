package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //탐색할 경로를 지정해줌. 시간 절약.
        basePackages = "hello.core.member",
        basePackageClasses = AutoAppConfig.class,
        //컴포넌트 스캔에서 뺄 거 지정하는것.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
