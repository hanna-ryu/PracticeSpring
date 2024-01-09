package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    public void PrototypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean PrototypeBean1 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean1 = " + PrototypeBean1);
        PrototypeBean PrototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("PrototypeBean2 = " + PrototypeBean2);

        Assertions.assertThat(PrototypeBean1).isNotSameAs(PrototypeBean2);
        // 프로토타입빈을 소멸시키고싶으면 적절하게 각각 처리해줘야함.
        //PrototypeBean1.destroy();
        //PrototypeBean2.destroy();

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }

    }

}
