package it.discovery.config;

import java.util.Map;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class AppProfileCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String currentProfile = conditionContext.getEnvironment().getProperty("current.profile", AppProfile.PROD.name());

        Map<String, Object> attributes = annotatedTypeMetadata.getAnnotationAttributes(ConditionalOnAppProfile.class.getName());
        AppProfile value = (AppProfile) attributes.get("value");
        return currentProfile.equalsIgnoreCase(value.name());
    }
}