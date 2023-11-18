package assign4.src.Controllers;


import assign4.src.Models.FormData;
import assign4.src.Models.Member;
import assign4.src.Repositories.FormDataRepository;
import assign4.src.Services.FormDataService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes("currentMember") // Add this annotation
@Controller
public class FormDataController {

    @Autowired
    private FormDataService formDataService;

    @PostMapping("/formData")
    public String postformData(@Valid @ModelAttribute("data") FormData data) {
        formDataService.createFormData(data);
        return "memberDash";
    }

}
