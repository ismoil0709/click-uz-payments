package uz.pdp.clickuzpayments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.pdp.clickuzpayments.model.Service;
import uz.pdp.clickuzpayments.model.enums.ServiceCategory;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    List<Service> findAllByName(String name);
    List<Service> findAllByCategory(ServiceCategory category);
    List<Service> findAllByIsMaintenance(Boolean isMaintenance);
    List<Service> findAllByCashback(Integer cashback);
    @Query("SELECT s FROM Service s WHERE s.cashback != 0")
    List<Service> findAllWithCashback();
    @Query("SELECT s FROM Service s WHERE s.latitude=?1 AND s.longitude=?2")
    List<Service> findAllByLocation(Double latitude,Double longitude);
}
