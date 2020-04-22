package be.pxl.student;

import be.pxl.student.REST.resource.LabelResource;
import be.pxl.student.dao.LabelDao;
import be.pxl.student.dao.impl.LabelDaoImpl;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Label;
import be.pxl.student.entity.Payment;
import be.pxl.student.service.LabelService;
import be.pxl.student.util.InvalidPaymentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.MapKeyColumn;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class LabelServiceCreateLabel {
    private static final long USER_ID = 5l;

    @Mock
    private LabelDao labelDao;
    @InjectMocks
    private LabelService labelService;
    private Label label;
    private LabelResource badLabelResource;
    private LabelResource labelResource;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        label = new Label("Tv", "The hunger games");
        labelResource = new LabelResource();
        labelResource.setName("TV");
        labelResource.setDescription("The hunger games 3");
    }

    @Test
    public void returnsLabelWhenCreatingALabelWithGoodInstance() throws Exception {
        when(labelDao.createLabel(label)).thenReturn(label);
        //labelService.createLabel(labelResource);
        verify(labelDao, never()).createLabel(label);
    }

    @Test
    public void returnsExceptionWhenCreatingALabelWithWrongInstance() throws Exception {

    }
}
