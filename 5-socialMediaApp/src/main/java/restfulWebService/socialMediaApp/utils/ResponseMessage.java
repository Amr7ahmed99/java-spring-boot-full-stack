package restfulWebService.socialMediaApp.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ResponseMessage {
    private final MessageSource messageSource;

    public ResponseMessage(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(String key, String defaultMessage, Object... args) {
        return messageSource.getMessage(key, args, defaultMessage, LocaleContextHolder.getLocale());
    }
}
