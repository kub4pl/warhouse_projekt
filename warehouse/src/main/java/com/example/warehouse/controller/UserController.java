package com.example.warehouse.controller;

import com.example.warehouse.dto.UserSearch;
import com.example.warehouse.model.Role;
import com.example.warehouse.model.User;
import com.example.warehouse.repository.UserRepository;
import com.example.warehouse.repository.repositoryInterface.AccidentShortDescription;
import com.example.warehouse.repository.repositoryInterface.RoleDescription;
import com.example.warehouse.repository.repositoryInterface.UserNameDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user")
    public Page<User> getUserPage(@RequestParam("page") int page,
                                  @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("username"));
        return userRepository.findAll(pageable);

    }
    @GetMapping("/user/username")
    public Page<String> getUserNamePage(@RequestParam("page") int page,
                                                     @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("username"));
        return userRepository.findUserName(pageable);
    }

    @GetMapping("/user/username2")
    public Page<UserNameDescription> getUserNamePage2(@RequestParam("page") int page,
                                                    @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("username"));
        return userRepository.findUserNameAndId(pageable);
    }

    @GetMapping("/user/username3")
    public Page<RoleDescription> getUserNamePage3(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {

        PageRequest pageable = PageRequest.of(page, size, Sort.by("username"));
        return userRepository.findUserNameAndIdAndRole(pageable);
    }
    @GetMapping("/auth/{id}/accident/list")
    public Page<AccidentShortDescription> getAccidentPage(@PathVariable Long id,
                                                          @RequestParam("page") int page,
                                                          @RequestParam("size") int size,
                                                          @RequestParam(value = "username", required = false) String username,
                                                          @RequestParam(value = "roleType", required = false) String roleType) {

        UserSearch searchUser = new UserSearch(username, roleType);


        return userRepository.findAll(searchUser.getUsername(), searchUser.getRoleType(), PageRequest.of(page, size));
    }



//    @Query("select a from Accident a left join Company c on c.id= a.company.id " +
//            "where (:companyId is null or c.id= :companyId) " +
//            "and (:accidentType is null or a.accidentType = :accidentType) " +
//            "and (:status is null or a.status = :status) ")
//    Page<AccidentShortDescription> findAll(@Param("companyId") Long companyId, @Param("accidentType") AccidentType accidentType, @Param("status") AccidentStatus status, Pageable pageable);
//
////    @GetMapping("/user/username4")
//    public Page<RoleDescription> getUserNamePage4(@RequestParam("page") int page,
//                                                  @RequestParam("size") int size) {
//
//        PageRequest pageable = PageRequest.of(page, size, Sort.by("username"));
//        return userRepository.findUserNameAndIdAndRole(pageable);
//    }

}
