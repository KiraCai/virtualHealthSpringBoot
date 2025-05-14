package virtualhealth.service;

import java.time.LocalDate;
import java.util.List;

public interface DataService<D> {
    List<D> findAllData(); //работает
    List<D> findAllByIdHistory(Long idHistory); //работает
    D findTestById(Long id);  //работает
    List<D> findAllByIdDoctor(Long idDoctor); // работает
    List<D> findAllByDate(LocalDate localDate); //работает
    void addData(D d);  //работает
    D updateData(D d);  //работает
    void deleteDataById(Long id);
}
