package be.pxl.student.dao;

import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.entity.Label;

import java.util.List;

public interface LabelDao {
    List<Label> getLabels();
    Label createLabel(Label label);
    boolean deleteLabel(int id);
    boolean findNameInLabels(LabelResource labelResource);
    Label getLabelById(int id);

}
