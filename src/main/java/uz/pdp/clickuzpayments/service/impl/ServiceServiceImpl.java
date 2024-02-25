package uz.pdp.clickuzpayments.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.clickuzpayments.dto.LocationDto;
import uz.pdp.clickuzpayments.dto.ServiceDto;
import uz.pdp.clickuzpayments.exception.NotFoundException;
import uz.pdp.clickuzpayments.exception.NullOrEmptyException;
import uz.pdp.clickuzpayments.model.Field;
import uz.pdp.clickuzpayments.model.Form;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;
import uz.pdp.clickuzpayments.repository.ServiceRepository;
import uz.pdp.clickuzpayments.service.ServiceService;
import uz.pdp.clickuzpayments.util.Validations;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;

    @Override
    public ServiceDto create(ServiceDto serviceDto) {
        if (Validations.isNullOrEmpty(serviceDto.getName()))
            throw new NullOrEmptyException("Name");
        if (Validations.isNullOrEmpty(serviceDto.getUrl()))
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
                                                .values(f.getValues())
                                                .format(f.getFormat())
                                                .build()
                                ).toList()
                        ))
                        .url(serviceDto.getUrl())
                        .isMaintenance(true)
                        .category(serviceDto.getCategory())
                        .cashback(serviceDto.getCashback())
                        .build()
        ));
    }

    @Override
    public ServiceDto update(ServiceDto serviceDto) {
        if (serviceDto.getId() == null)
            throw new NullOrEmptyException("Id");
        uz.pdp.clickuzpayments.model.Service service = serviceRepository.findById(serviceDto.getId()).orElseThrow(
                () -> new NotFoundException("Service")
        );
        List<Field> fields = new ArrayList<>();
        if (serviceDto.getFields() != null) {
            fields = serviceDto.getFields().stream().map(
                    f -> {
                        Field field = new Field();
                        service.getForm().getFields().forEach(f1 -> field.setName(Validations.requireNonNullElse(f.getName(), f1.getName())));
                        return field;
                    }
            ).toList();
        }
        return new ServiceDto(serviceRepository.save(
                uz.pdp.clickuzpayments.model.Service.builder()
                        .id(service.getId())
                        .name(Validations.requireNonNullElse(serviceDto.getName(), service.getName()))
                        .category(Validations.requireNonNullElse(serviceDto.getCategory(), service.getCategory()))
                        .isMaintenance(Validations.requireNonNullElse(serviceDto.getIsMaintenance(), service.getIsMaintenance()))
                        .url(Validations.requireNonNullElse(serviceDto.getUrl(), service.getUrl()))
                        .cashback(Validations.requireNonNullElse(serviceDto.getCashback(), service.getCashback()))
                        .longitude(Validations.requireNonNullElse(serviceDto.getLongitude(), service.getLongitude()))
                        .latitude(Validations.requireNonNullElse(serviceDto.getLatitude(), service.getLatitude()))
                        .form(new Form(Validations.requireNonNullElse(fields,service.getForm().getFields())))
                        .build()
        ));
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        serviceRepository.delete(serviceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Service")
        ));
    }

    @Override
    public ServiceDto getById(Long id) {
        if (id == null)
            throw new NullOrEmptyException("Id");
        return new ServiceDto(serviceRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Service")
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
    public List<ServiceDto> getAllByCategory(String category) {
        if (category == null)
            throw new NullOrEmptyException("Category");
        return serviceRepository.findAllByCategory(ServiceCategory.valueOf(category.toUpperCase())).stream().map(ServiceDto::new).toList();
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
    public List<ServiceDto> getAllByLocation(LocationDto locationDto) {
        if (locationDto.getLatitude() == null || locationDto.getLongitude() == null)
            throw new NullOrEmptyException("Location");
        return serviceRepository.findAllByLocation(locationDto.getLatitude(), locationDto.getLongitude()).stream().map(ServiceDto::new).toList();
    }
}
