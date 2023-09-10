package com.example.warehouse.controller;
import com.example.warehouse.dto.PartDto;
import com.example.warehouse.dto.PartWorkerDto;
import com.example.warehouse.dto.WorkerDto;
import com.example.warehouse.model.Part;
import com.example.warehouse.repository.PartRepository;
import com.example.warehouse.repository.repositoryInterface.*;
import com.example.warehouse.service.WorkerFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PartController {

    @Autowired
    private PartRepository partRepository;
    @Autowired
    private WorkerFeignClient workerFeignClient;

    @PostMapping("/part")
    public PartDto createPart(@RequestBody PartDto partDto) {
        Part part = new Part();
        part.setPartDescription(partDto.getPartDescription());
        part.setTypePart(partDto.getTypePart());
        part.setModelPart(partDto.getModelPart());
        part.setDepartment(partDto.getDepartment());
        part.setSupplier(partDto.getSupplier());
        part.setUnit(partDto.getUnit());
        partRepository.save(part);
        return partDto;
}
    @PutMapping("part/{id}")
    public PartDto editPart (@RequestBody PartDto partDto, @PathVariable Long id){
        Part part = partRepository.getReferenceById(id);
        part.setPartDescription(partDto.getPartDescription());
        part.setTypePart(partDto.getTypePart());
        part.setModelPart(partDto.getModelPart());
        part.setDepartment(partDto.getDepartment());
        part.setSupplier(partDto.getSupplier());
        part.setUnit(partDto.getUnit());
        partRepository.save(part);
        return partDto;

    }
//    @GetMapping("/part/{id}")
//    public ResponseEntity<PartDto> getPart(@PathVariable Long id) throws RuntimeException {
//        Part part;
//        Optional<Part> optionalPart = partRepository.findById(id);
//        if(optionalPart.isPresent()){
//            part = optionalPart.get();
//        }else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            throw new RuntimeException("Can not found id");
//
//        }
//        PartDto partDto = new PartDto();
//
//        partDto.setPartDescription(part.getPartDescription());
//        partDto.setTypePart(part.getTypePart());
//        partDto.setModelPart(part.getModelPart());
//        partDto.setDepartment(part.getDepartment());
//        partDto.setSupplier(part.getSupplier());
//        partDto.setUnit(part.getUnit());
//
//        return ResponseEntity.status(HttpStatus.OK).body(partDto);
//    }

    @GetMapping("/part/page")
    public Page<Part> getPartPage() {

//        Pageable firstPageWithTenElements = PageRequest.of(0, 9);
//        Pageable secondPageWithTenElements = PageRequest.of(10, 19);
//        List<Part> secondPart = partRepository.findAllByPartDescription(10L);
//        Page<Part> firstPart = partRepository.findAll(firstPageWithTenElements);
//        Page<Part> sort = partRepository.findAll(Sort.by("part_description"));

        PageRequest pageable = PageRequest.of(0, 10, Sort.by("partDescription")); // Pierwsza strona, 10 rekordów na stronę
        return partRepository.findAll(pageable);
    }



    @DeleteMapping("/part/{id}")
    public Boolean deletePart(@PathVariable Long id) {

        partRepository.deleteById(id);
        return true;
    }
    @GetMapping("/part/list")
    public List<PartDto> getPartList() {
        List<Part> partList = partRepository.findAll();
        List<PartDto> partDtoList = new ArrayList<>();
        for (Part part : partList) {
            PartDto partDto = new PartDto();
            partDto.setPartDescription(part.getPartDescription());
            partDto.setTypePart(part.getTypePart());
            partDto.setModelPart(part.getModelPart());
            partDto.setDepartment(part.getDepartment());
            partDto.setSupplier(part.getSupplier());
            partDto.setUnit(part.getUnit());
            partDtoList.add(partDto);


        }
        return partDtoList;
    }

    @GetMapping("/part/item")
    public Page<PartDescription> getPartPage(@RequestParam("page") int page,
                                             @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("partDescription"));
        return partRepository.findPartDescriptionAndModelPartAndQuantityAndWorkOrderNumber(pageable);
    }
    @GetMapping("/part/item2")
    public Page<PartDescriptionOneToMany> getPartPage2(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("partDescription"));
        Page<PartDescriptionOneToMany> parts = partRepository.findPartDescriptionAndModelPartAndSupplierAndQuantityAndWorkOrderNumber(pageable);
        return parts;
    }
    @GetMapping("/part/location")
    public Page<PartToLocationOneToMany> getLocationPage(@RequestParam("page") int page,
                                                       @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("partDescription"));
        Page<PartToLocationOneToMany> parts = partRepository.findPartDescriptionAndModelPartAndPartsLocationAndAmount(pageable);
        return parts;
    }
    @GetMapping("/part/part/list/new")
    public Page<PartDescriptionNewList> getAccidentPage(@RequestParam("page") int page,
                                                        @RequestParam("size") int size,
                                                        @RequestParam(value = "partDescription", required = false) String partDescription,
                                                        @RequestParam(value = "supplier", required = false) String supplier,
                                                        @RequestParam(value = "modelPart", required = false) String modelPart,
                                                        @RequestParam(value = "department", required = false) String department) {

//        PartSearch searchPart = new PartSearch(partDescription, modelPart, supplier, department);


        return partRepository.findAll(partDescription, supplier, modelPart, department, PageRequest.of(page, size));
    }


    @GetMapping("/part/worker/{id}")
    public PartWorkerDto getPartWorker(@PathVariable Long id){

        Part part = partRepository.findById(id).orElse(null);


        WorkerDto workerDto = workerFeignClient.getWorkerPart(part.getPESEL());

        PartWorkerDto partWorkerDto = PartWorkerDto.builder()
                .surname(workerDto.getSurname())
                .name(workerDto.getName()).partDescription(part.getPartDescription())
                .modelPart(part.getModelPart())
                .supplier(part.getSupplier()).build();
        return partWorkerDto;
    }
}
//    @GetMapping("/part/{id}")
//    public ResponseEntity<PartDto> getPart(@PathVariable Long id) throws RuntimeException {
//        Part part;
//        Optional<Part> optionalPart = partRepository.findById(id);
//        if(optionalPart.isPresent()){
//            part = optionalPart.get();
//        }else {
////            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//            throw new RuntimeException("Can not found id");
//
//        }
//        PartDto partDto = new PartDto();
//
//        partDto.setPartDescription(part.getPartDescription());
//        partDto.setTypePart(part.getTypePart());
//        partDto.setModelPart(part.getModelPart());
//        partDto.setDepartment(part.getDepartment());
//        partDto.setSupplier(part.getSupplier());
//        partDto.setUnit(part.getUnit());
//
//        return ResponseEntity.status(HttpStatus.OK).body(partDto);
//    }