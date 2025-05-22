package virtualhealth.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import virtualhealth.dao.CalendarDao;
import virtualhealth.model.Calendar;
import virtualhealth.service.CalendarService;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CalendarServiceImp implements CalendarService {

    private CalendarDao calendarDao;

    @Override
    public List<Calendar> findAllCalendar(){
        return calendarDao.findAll();
    }
    @Override
    public void addCalendar(Calendar calendar){
        calendarDao.save(calendar);
    }
    @Override
    public void updateCalendar(Calendar calendar){
        calendarDao.save(calendar);
    }
    @Override
    public Calendar findCalendarById(Long id){
        return calendarDao.findById(id).orElseThrow(() -> new EntityNotFoundException("Calendar not found with id: " + id));
    }
    @Override
    public List<Calendar> findByDate(LocalDate date){
        return calendarDao.findByDate(date);
    }
    @Override
    public List<Calendar> findByIdDoctor(Long idDoctor){
        return calendarDao.findAllByDoctor_IdDoctor(idDoctor);
    }
    @Override
    public List<Calendar> findByIdClient(Long idClient){
        return calendarDao.findAllByClient_IdClient(idClient);
    }
    @Override
    @Transactional
    public void deleteCalendarById(Long id){
        calendarDao.deleteById(id);
    }
}
