package uz.pdp.clickuzpayments.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import uz.pdp.clickuzpayments.model.Field;
import uz.pdp.clickuzpayments.model.Service;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class ServiceDto {
    private Long id;
    private String name;
    private ServiceCategory category;
    @Builder.Default
    private Boolean isMaintenance = true;
    private List<FieldDto> fields;
    private String url;

    public ServiceDto(Service service) {
        this.id = service.getId();
        this.name = service.getName();
        this.category = service.getCategory();
        this.isMaintenance = service.getIsMaintenance();
        this.url = service.getUrl();
        this.fields = service.getForm().getFields().stream().map(FieldDto::new).toList();
    }

    @AllArgsConstructor
    @Getter
    @Builder
    public static class FieldDto {
        private String name;
        private String fullText;
        private String regex;
        private List<String> values;
        private String format;
        public FieldDto(Field field) {
            this.name = field.getName();
            this.fullText = field.getFullText();
            this.regex = field.getRegex();
            this.values = field.getValues();
            this.format = field.getFormat();
        }
    }
}
