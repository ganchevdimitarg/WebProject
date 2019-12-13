package softuni.webproject.services.services.feedback;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import softuni.webproject.data.models.Animal;
import softuni.webproject.data.models.Feedback;
import softuni.webproject.data.repositories.FeedbackRepository;
import softuni.webproject.services.base.TestBase;
import softuni.webproject.services.models.FeedbackServiceModel;

import static org.junit.jupiter.api.Assertions.*;

class FeedbackImplTest extends TestBase {
    @MockBean
    FeedbackRepository feedbackRepository;

    @Autowired
    FeedbackService feedbackService;

    @Test
    void save() {
        FeedbackServiceModel model = new FeedbackServiceModel("Ivan", "abc@abv.bg", "Test", "neshto si");

        feedbackService.save(model);

        ArgumentCaptor<Feedback> argument = ArgumentCaptor.forClass(Feedback.class);
        Mockito.verify(feedbackRepository).saveAndFlush(argument.capture());

        Feedback Feedback = argument.getValue();
        assertNotNull(Feedback);
    }
}