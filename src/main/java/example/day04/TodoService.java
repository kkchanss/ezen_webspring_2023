package example.day04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoDao todoDao;

    public boolean doPost(TodoDto dto)
    {
        System.out.println("TodoService.doPost");
        System.out.println("dto = " + dto);

        // return todoDao.doPost(dto);
        return todoDao.doPost(dto);
    }

    public List<TodoDto> doGet()
    {
        System.out.println("TodoService.doGet");
        return todoDao.doGet();
    }
    public boolean doPut(TodoDto dto)
    {
        System.out.println("TodoService.doPut");
        System.out.println("dto = " + dto);
        return todoDao.doPut(dto);
    }
    public boolean doDelete(int tno)
    {
        System.out.println("TodoService.doDelete");
        System.out.println("tno = " + tno);
        return todoDao.doDelete(tno);
    }


}