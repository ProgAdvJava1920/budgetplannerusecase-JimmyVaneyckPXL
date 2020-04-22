package be.pxl.student.service;

import be.pxl.student.REST.resource.AccountResource;
import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.dao.impl.AccountDaoImpl;
import be.pxl.student.dao.impl.LabelDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Label;

import java.util.List;

public class LabelService {
    private LabelDaoImpl labelDao;

    public LabelService() {
        labelDao = new LabelDaoImpl("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "test");
    }

    public List<Label> getAllLabels() {
        return labelDao.getLabels();
    }

    public void createLabel(LabelResource labelResource) throws Exception {
        Label label = new Label(labelResource.getName(), labelResource.getDescription());

        if (labelDao.findNameInLabels(labelResource)) {
            throw new Exception("Label bestaat al!");
        } else {
            labelDao.createLabel(label);
        }
    }

    public void deleteLabel(int id) throws Exception {
        if (labelDao.getLabelById(id) != null) {
            labelDao.deleteLabel(id);
        } else {
            throw new Exception("Label bestaat niet!");
        }
    }
}
