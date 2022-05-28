package cc.mrbird.febs.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cc.mrbird.febs.system.entity.Invoice;



@Repository
public interface UserDetailRepository extends JpaRepository<Invoice, Long>{



}