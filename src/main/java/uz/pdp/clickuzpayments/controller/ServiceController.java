package uz.pdp.clickuzpayments.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.clickuzpayments.dto.CustomResponseEntity;
import uz.pdp.clickuzpayments.dto.LocationDto;
import uz.pdp.clickuzpayments.dto.Response;
import uz.pdp.clickuzpayments.dto.ServiceDto;
import uz.pdp.clickuzpayments.service.ServiceService;

@RestController
@RequestMapping("/service")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @PostMapping("/create")
    public CustomResponseEntity<?> create(@RequestBody ServiceDto serviceDto) {
        return CustomResponseEntity.ok(serviceService.create(serviceDto));
    }

    @PatchMapping("/update")
    public CustomResponseEntity<?> update(@RequestBody ServiceDto serviceDto) {
        return CustomResponseEntity.ok(serviceService.update(serviceDto));
    }

    @DeleteMapping("/delete/{id}")
    public CustomResponseEntity<?> delete(@PathVariable Long id) {
        serviceService.delete(id);
        return CustomResponseEntity.ok(new Response("Successfully deleted"));
    }

    @GetMapping("/{id}")
    public CustomResponseEntity<?> getById(@PathVariable Long id) {
        return CustomResponseEntity.ok(serviceService.getById(id));
    }

    @GetMapping("/all")
    public CustomResponseEntity<?> getAll() {
        return CustomResponseEntity.ok(serviceService.getAll());
    }

    @GetMapping("/all/name/{name}")
    public CustomResponseEntity<?> getAllByName(@PathVariable String name) {
        return CustomResponseEntity.ok(serviceService.getAllByName(name));
    }

    @GetMapping("/all/category/{category}")
    public CustomResponseEntity<?> getAllByCategory(@PathVariable String category) {
        return CustomResponseEntity.ok(serviceService.getAllByCategory(category));
    }

    @GetMapping("/all/maintenance/{isMaintenance}")
    public CustomResponseEntity<?> getAllByIsMaintenance(@PathVariable Boolean isMaintenance) {
        return CustomResponseEntity.ok(serviceService.getAllByIsMaintenance(isMaintenance));
    }

    @GetMapping("/all/cashback/{cashback}")
    public CustomResponseEntity<?> getAllByCashback(@PathVariable Integer cashback) {
        return CustomResponseEntity.ok(serviceService.getAllByCashback(cashback));
    }

    @GetMapping("/all/cashback")
    public CustomResponseEntity<?> getAllWithCashback() {
        return CustomResponseEntity.ok(serviceService.getAllWithCashback());
    }

    @GetMapping("/all/location")
    public CustomResponseEntity<?> getAllByLocation(@RequestBody LocationDto locationDto) {
        return CustomResponseEntity.ok(serviceService.getAllByLocation(locationDto));
    }
}
