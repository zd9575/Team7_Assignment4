package assign4.src.Services;

import assign4.src.Models.FormData;

import assign4.src.Repositories.FormDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormDataService {

    @Autowired
    private FormDataRepository formDataRepository;

    public FormData createFormData(FormData data) {


            FormData newData = new FormData(data.getTextAreaValue(), data.getEmailValue());
            formDataRepository.save(newData);

            return newData;

    }
}
