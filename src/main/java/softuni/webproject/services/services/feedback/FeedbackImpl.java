package softuni.webproject.services.services.feedback;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Feedback;
import softuni.webproject.data.repositories.FeedbackRepository;
import softuni.webproject.services.models.FeedbackServiceModel;

@Service
@AllArgsConstructor
public class FeedbackImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Override
    public void save(FeedbackServiceModel feedbackServiceModel) {
        feedbackRepository.saveAndFlush(modelMapper.map(feedbackServiceModel, Feedback.class));
    }
}
