package softuni.webproject.services.services.feedback;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.webproject.data.models.Feedback;
import softuni.webproject.data.repositories.FeedbackRepository;
import softuni.webproject.services.models.FeedbackServiceModel;

@Service
public class FeedbackImpl implements FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public FeedbackImpl(FeedbackRepository feedbackRepository, ModelMapper modelMapper) {
        this.feedbackRepository = feedbackRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void save(FeedbackServiceModel feedbackServiceModel) {
        feedbackRepository.saveAndFlush(modelMapper.map(feedbackServiceModel, Feedback.class));
    }
}
