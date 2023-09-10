package com.example.warehouse.repository;

import com.example.warehouse.model.Part;
import com.example.warehouse.repository.repositoryInterface.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

//        Optional<Part> findById(Long id);
//        Page<Part> findAll(Pageable pageable);

        @Query("select p.partDescription as partDescription, p.modelPart as modelPart, t.quantity as quantity, t.workOrderNumber as workOrderNumber from Part p inner join Transaction t on p.id=t.id")
        Page<PartDescription> findPartDescriptionAndModelPartAndQuantityAndWorkOrderNumber (Pageable pageable);

        @Query("select p.partDescription as partDescription, p.modelPart as modelPart, p.supplier as supplier, t.quantity as quantity, t.workOrderNumber as workOrderNumber  from Transaction t inner join Part p on t.part.id=p.id")
        Page<PartDescriptionOneToMany> findPartDescriptionAndModelPartAndSupplierAndQuantityAndWorkOrderNumber (Pageable pageable);

        @Query("select p.partDescription as partDescription, p.modelPart as modelPart, l.partsLocation as partsLocation, l.amount as amount  from Location l inner join Part p on l.part.id=p.id")
        Page<PartToLocationOneToMany> findPartDescriptionAndModelPartAndPartsLocationAndAmount (Pageable pageable);

        @Query("select p.partDescription as partDescription, p.supplier as supplier, p.department as department, p.modelPart as modelPart from Part p " +
                "where (:partDescription is null or upper(p.partDescription) like CONCAT('%',:partDescription,'%')) "+
                "and (:supplier is null or upper(p.supplier) like CONCAT('%',:supplier,'%')) " +
                "and (:department is null or upper(p.department) like CONCAT('%',:department,'%')) " +
                "and (:modelPart is null or upper(p.modelPart) like CONCAT('%',:modelPart,'%')) ")
        Page<PartDescriptionNewList> findAll(@Param("partDescription") String partDescription, @Param("supplier") String supplier, @Param("modelPart") String modelPart, @Param("department") String department, Pageable pageable);


//        public byte[] getPartDataById(Long id) {
//                String sql = "SELECT partDescription FROM part WHERE id = ?";
//                return jdbcTemplate.queryForObject(sql, new Object[]{id}, byte[].class);
//        }

//        @Query("select u.username as username, r.type as roleType from User u left join Role r on u.role.id= r.id " +
//                "where (:username is null or upper(u.username) like CONCAT('%',:username,'%')) "+
//                "and (:roleType is null or upper(r.type) like CONCAT('%',:roleType,'%')) ")
//        Page<PartDescriptionNewList> findAll(@Param("partDescription") String partDescription, @Param("supplier") String supplier, @Param("department") String department, @Param("modelPart") String modelPart, Pageable pageable);

//        @Query("select a from Accident a left join Company c on c.id= a.company.id " +
//                "where (:companyId is null or c.id= :companyId) " +
//                "and (:accidentType is null or a.accidentType = :accidentType) " +
//                "and (:status is null or a.status = :status) ")
//        Page<AccidentShortDescription> findAll(@Param("companyId") Long companyId, @Param("accidentType") AccidentType accidentType, @Param("status") AccidentStatus status, Pageable pageable);

}
