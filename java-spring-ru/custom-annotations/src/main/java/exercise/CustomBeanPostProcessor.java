package exercise;

import java.lang.reflect.Proxy;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// BEGIN
import exercise.calculator.CalculatorImpl;


@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

    private Map<String, String> beans = new HashMap<>();
    private Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            String param = bean.getClass().getAnnotation(Inspect.class).level();
            beans.put(beanName, param);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            return Proxy.newProxyInstance(
                    CalculatorImpl.class.getClassLoader(),
                    CalculatorImpl.class.getInterfaces(),
                    (proxy, method, args) -> {
                        if (method.getName().equals("sum") || method.getName().equals("mult")) {
                            String message = "Was called method: " + method.getName() + "() with arguments: " + Arrays.toString(args);
                            Logger.class.getMethod(beans.get(beanName), String.class)
                                    .invoke(LOGGER, message);
                            return method.invoke(bean, args);
                        } else {
                            throw new UnsupportedOperationException(
                                    "Unsupported method: " + method.getName()
                            );
                        }
                    }
            );
        }
        return bean;
    }
}
// END
