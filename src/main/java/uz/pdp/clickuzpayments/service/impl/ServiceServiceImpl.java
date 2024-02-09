package uz.pdp.clickuzpayments.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.response.ServiceDto;
import uz.pdp.clickuzpayments.exception.NotFoundException;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;
import uz.pdp.clickuzpayments.model.Field;
import uz.pdp.clickuzpayments.model.Form;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;
import uz.pdp.clickuzpayments.repository.ServiceRepository;
import uz.pdp.clickuzpayments.service.ServiceService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        if (serviceDto.getName() == null)
            throw new NullOrEmptyException("Name");
        if (serviceDto.getUrl() == null)
            throw new NullOrEmptyException("Url");
        if (serviceDto.getCategory() == null)
            throw new NullOrEmptyException("Category");
        return new ServiceDto(serviceRepository.save(
                uz.pdp.clickuzpayments.model.Service.builder()
                        .name(serviceDto.getName())
                        .form(new Form(
                                serviceDto.getFields().stream().map(
                                        f -> Field.builder()
                                                .name(f.getName())
                                                .fullText(f.getFullText())
                                                .regex(f.getRegex())
                                                .values(f.getValues())
                                                .format(f.getFormat())
                                                .build()
                                ).toList()
                        ))
                        .url(serviceDto.getUrl())
                        .isMaintenance(true)
                        .category(serviceDto.getCategory())
                        .build()
        ));
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) {

    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        serviceRepository.delete(serviceRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Service")
        ));
    }

    @Override
    public ServiceDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new ServiceDto(serviceRepository.findById(id).orElseThrow(
                ()->new NotFoundException("Service")
        ));
    }

    @Override
    public List<ServiceDto> getAll() {
        return serviceRepository.findAll().stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllByName(String name) {
        if (name == null)
            throw new NullOrEmptyException("Name");
        return serviceRepository.findAllByName(name).stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllByCategory(ServiceCategory category) {
        if (category == null)
            throw new NullOrEmptyException("Category");
        return serviceRepository.findAllByCategory(category).stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllByIsMaintenance(Boolean isMaintenance) {
        if (isMaintenance == null)
            throw new NullOrEmptyException("isMaintenance");
        return serviceRepository.findAllByIsMaintenance(isMaintenance).stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllByCashback(Integer cashback) {
        if (cashback == null)
            throw new NullOrEmptyException("Cashback");
        return serviceRepository.findAllByCashback(cashback).stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllWithCashback() {
        return serviceRepository.findAllWithCashback().stream().map(ServiceDto::new).toList();
    }

    @Override
    public List<ServiceDto> getAllByLocation(Double latitude, Double longitude) {
        if (longitude == null || latitude == null)
            throw new NullOrEmptyException("Location");
        return serviceRepository.findAllByLocation(latitude,longitude).stream().map(ServiceDto::new).toList();
    }
}
