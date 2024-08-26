package com.sonpro.final_test.controllers;

import com.sonpro.final_test.dto.request.StaffRequest;
import com.sonpro.final_test.entities.*;
import com.sonpro.final_test.services.*;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/staffs")
public class StaffController {
    private final StaffService staffService;
    private final MajorService majorService;
    private final DeparmentService deparmentService;
    private final FacilityService facilityService;
    private final StaffMajorFacilityService staffMajorFacilityService;

    public StaffController(StaffService staffService,
                           MajorService majorService,
                           DeparmentService deparmentService,
                           FacilityService facilityService,
                           StaffMajorFacilityService staffMajorFacilityService) {
        this.staffService = staffService;
        this.majorService = majorService;
        this.deparmentService = deparmentService;
        this.facilityService = facilityService;
        this.staffMajorFacilityService = staffMajorFacilityService;
    }

    @GetMapping
    public String getAllStaff(@RequestParam(defaultValue = "0") int page,
                               Model model) {
        Pageable pageable = PageRequest.of(page, 5);
        Page<Staff> staffs = staffService.getAllPageStaff(pageable);
        model.addAttribute("staffs", staffs.getContent());
        model.addAttribute("page", page);
        model.addAttribute("totalPage", staffs.getTotalPages());

        return "staff";
    }

    @GetMapping("/add")
    public String addStaff(Model model) {
        model.addAttribute("addStaff", new StaffRequest());
        return "addStaff";
    }

    @GetMapping("{id}")
    public String update(@PathVariable("id") UUID id, Model model) {
        StaffRequest staff = staffService.findStaffById(id);
        model.addAttribute("updatestaff", staff);
        return "updateStaff";
    }
    @PostMapping("/update")
    public String updateStaff(
            @Valid
            @ModelAttribute("addStaff") StaffRequest request,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "addStaff";
        }
        request.setStatus(Short.parseShort("1"));
        staffService.updateStaff(request);
        return "redirect:/staffs";
    }

    @PostMapping("/addd")
    public String addStaff(
            @Valid
            @ModelAttribute("addStaff") StaffRequest request,
            final BindingResult result) {
        if (result.hasErrors()) {
            return "addStaff";
        }
        boolean exists = false;
        if (staffService.existsByStaffCode(request.getStaffCode())) {
            result.rejectValue("staffCode", "error.addStaff", "Staff code is required");
            exists = true;
        }
        if (staffService.existsByAccountFe(request.getAccountFe())) {
            result.rejectValue("accountFe", "error.addStaff", "Account FE is required");
            exists = true;
        }
        if (staffService.existsByAccountFpt(request.getAccountFpt())) {
            result.rejectValue("accountFpt", "error.addStaff", "Account FPT is required");
            exists = true;
        }
        if (exists) {
            return "addStaff";
        }
        request.setStatus(Short.parseShort("1"));
        staffService.createStaff(request);
        return "redirect:/staffs";
    }

    @PostMapping("/tthai/{id}")
    public String update_tt(@PathVariable("id") UUID id) {
        StaffRequest request = staffService.findStaffById(id);
        staffService.update_tt(request);
        return "redirect:/staffs";
    }

    @GetMapping("/bm-cn/{id}")
    public String bm_cn(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("staff", staffService.getStaffById(id));
        model.addAttribute("cosos", facilityService.getAllFacilities());
        model.addAttribute("bomons", deparmentService.getAllDepartments());
        model.addAttribute("chuyennganhs", majorService.getAllMajors());
        model.addAttribute("bn_cn", staffMajorFacilityService.getStaffMajorFacilityByStaff_Id(id));

        return "bomon";
    }

    @PostMapping("/bm-cn")
    public String bm_cn(@ModelAttribute("staff") StaffRequest request,
                        @RequestParam("bomon") Department bomon,
                        @RequestParam("coso") Facility coso,
                        @RequestParam("chuyennganh") Major chuyennganh) {
        Staff staff = staffService.getStaffById(request.getId());
        staffMajorFacilityService.addBM_CN(staff, bomon, chuyennganh, coso);

        return "redirect:/staffs/bm-cn/" + request.getId();
    }

}
