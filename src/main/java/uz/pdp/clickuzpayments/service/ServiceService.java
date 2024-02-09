package uz.pdp.clickuzpayments.service;

import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.response.ServiceDto;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;

import java.util.List;

@Service
public interface ServiceService {
    ServiceDto create(ServiceDto serviceDto);
    ServiceDto update(ServiceDto serviceDto);
    void delete(Long id);
    ServiceDto getById(Long id);
    List<ServiceDto> getAll();
    List<ServiceDto> getAllByName(String name);
    List<ServiceDto> getAllByCategory(ServiceCategory category);
    List<ServiceDto> getAllByIsMaintenance(Boolean isMaintenance);
    List<ServiceDto> getAllByCashback(Integer cashback);
    List<ServiceDto> getAllWithCashback();
    List<ServiceDto> getAllByLocation(Double latitude,Double longitude);
}
