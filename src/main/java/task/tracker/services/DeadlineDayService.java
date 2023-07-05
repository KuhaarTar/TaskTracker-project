package task.tracker.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import task.tracker.models.DeadlineDay;
import task.tracker.repositories.DeadlineDayRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeadlineDayService {

    private final DeadlineDayRepository deadlineDayRepository;

    public List<DeadlineDay> getAll(){
        return deadlineDayRepository.findAll();
    }

}
